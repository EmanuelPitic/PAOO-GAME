package PaooGame;
import PaooGame.Audio.AudioPlayer;
import PaooGame.Exceptions.NullContentException;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Input.KeyManager;
import PaooGame.Input.MouseManager;
import PaooGame.Input.NameInputManager;
import PaooGame.Settings.GameSettings;
import PaooGame.States.*;
import PaooGame.Tiles.Tile;
import PaooGame.Worlds.World;

import java.awt.*;
import java.awt.image.BufferStrategy;


/*! \class Game
    \brief Clasa principala a intregului proiect. Implementeaza Game - Loop (Update -> Draw)

                ------------
                |           |
                |     ------------
    60 times/s  |     |  Update  |  -->{ actualizeaza variabile, stari, pozitii ale elementelor grafice etc.
        =       |     ------------
     16.7 ms    |           |
                |     ------------
                |     |   Draw   |  -->{ deseneaza totul pe ecran
                |     ------------
                |           |
                -------------
    Implementeaza interfata Runnable:

        public interface Runnable {
            public void run();
        }

    Interfata este utilizata pentru a crea un nou fir de executie avand ca argument clasa Game.
    Clasa Game trebuie sa aiba definita metoda "public void run()", metoda ce va fi apelata
    in noul thread(fir de executie). Mai multe explicatii veti primi la curs.

    In mod obisnuit aceasta clasa trebuie sa contina urmatoarele:
        - public Game();            //constructor
        - private void init();      //metoda privata de initializare
        - private void update();    //metoda privata de actualizare a elementelor jocului
        - private void draw();      //metoda privata de desenare a tablei de joc
        - public run();             //metoda publica ce va fi apelata de noul fir de executie
        - public synchronized void start(); //metoda publica de pornire a jocului
        - public synchronized void stop()   //metoda publica de oprire a jocului
 */
public class Game implements Runnable {
    //Input
    private final KeyManager keyManager;  //!< Referinta catre obiectul care gestioneaza intrarile de la tastatura din partea utilizatorului./
    public final NameInputManager nameInputManager;
    private final MouseManager mouseManager;//!< Referinta catre obiectul care gestioneaza intrarile de la mouse din partea utilizatorului./
    private GameWindow wnd;       //!< Fereastra in care se va desena tabla jocului/
    private boolean runState;   //!< Flag ce starea firului de executie./

    /// Sunt cateva tipuri de "complex buffer strategies", scopul fiind acela de a elimina fenomenul de
    /// flickering (palpaire) a ferestrei.
    /// Modul in care va fi implementata aceasta strategie in cadrul proiectului curent va fi triplu buffer-at

    ///                         |------------------------------------------------>|
    ///                         |                                                 |
    ///                 ****************          *****************        ***************
    ///                 *              *   Show   *               *        *             *
    /// [ Ecran ] <---- * Front Buffer *  <------ * Middle Buffer * <----- * Back Buffer * <---- Draw()
    ///                 *              *          *               *        *             *
    ///                 ****************          *****************        ***************

    private Thread gameThread; //!< Referinta catre thread-ul de update si draw al ferestrei/
    private BufferStrategy bs;         //!< Referinta catre un mecanism cu care se organizeaza memoria complexa pentru un canvas./
    private Graphics g;          //!< Referinta catre un context grafic./

    public AudioPlayer audioPlayer;  /*!< Referinta catre un obiect de tip AudioPlayer utilizat pentru redarea sunetelor.*/
    public GameSettings gameSettings; /*!< Referinta catre setarile jocului.*/

    //States
    public State gameOverState;
    public State playState; /*!< Referinta catre joc.*/
    private State menuState; /*!< Referinta catre meniu.*/
    private State helpState; /*!< Referinta catre help.*/
    private State pauseState;/*!< Referinta catre pause.*/
    private State settingsState;/*!< Referinta catre settings.*/
    public State gameWonState; /*!< Referinta catre Game won.*/
    public State stateSetPlayerState; /*!< Referinta catre Set player name.*/
    public State stateSelectSave; /*!< Referinta catre Select Save.*/
    public State leaderBoardState;  /*!< Referinta catre Leaderboard.*/


    private RefLinks refLink;   //!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program./
    private World world; //!< O referinta catre harta jocului./

    public Game(String title, int width, int height) {
        /// Obiectul GameWindow este creat insa fereastra nu este construita
        /// Acest lucru va fi realizat in metoda init() prin apelul
        /// functiei BuildGameWindow();
        wnd = new GameWindow(title, width, height);
        /// Resetarea flagului runState ce indica starea firului de executie (started/stoped)
        runState = false;

        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        nameInputManager= new NameInputManager();
        audioPlayer=new AudioPlayer();
        gameSettings=new GameSettings();
        try{
            audioPlayer.playMusic("sound1.wav");
        }catch (NullContentException e){
            System.out.println("Nu se poate activa muzica de fundal.");
        }
    }

    /*! \fn private void init()
        \brief  Metoda construieste fereastra jocului, initializeaza aseturile, listenerul de tastatura etc.

        Fereastra jocului va fi construita prin apelul functiei BuildGameWindow();
        Sunt construite elementele grafice (assets): dale, player, elemente active si pasive.

     */
    private void InitGame() {
        wnd = new GameWindow("THE GOLDEN GLOVE", 1056, 544);
        /// Este construita fereastra grafica.
        wnd.BuildGameWindow();

        wnd.GetCanvas().addKeyListener(nameInputManager);
        wnd.GetCanvas().addKeyListener(keyManager);
        wnd.GetCanvas().addMouseListener(mouseManager);
        wnd.GetCanvas().addMouseMotionListener(mouseManager);

        /// Se incarca toate elementele grafice (dale)
        Assets.Init();

        refLink = new RefLinks(this);

        playState = new PlayState(refLink, false, "");
        menuState = new MenuState(refLink);
        helpState = new HelpState(refLink);
        pauseState = new PauseState(refLink);
        gameOverState = new GameOverState(refLink, false);
        settingsState = new SettingsState(refLink);
        gameWonState = new GameWonState(refLink, false);
        stateSetPlayerState = new StateSetPlayerName(refLink);
        leaderBoardState = new LeaderBoardState(refLink);

        refLink.GetMouseManager().setUIManager(menuState.getUiManager());

        State.SetState(menuState);
    }

    /*! \fn public void run()
        \brief Functia ce va rula in thread-ul creat.

        Aceasta functie va actualiza starea jocului si va redesena tabla de joc (va actualiza fereastra grafica)
     */
    public void run() {
        /// Initializeaza obiectul game
        InitGame();
        long oldTime = System.nanoTime();   //!< Retine timpul in nanosecunde aferent frame-ului anterior./
        long curentTime;                    //!< Retine timpul curent de executie./

        /// Apelul functiilor Update() & Draw() trebuie realizat la fiecare 16.7 ms
        /// sau mai bine spus de 60 ori pe secunda.

        final int framesPerSecond = 60; //!< Constanta intreaga initializata cu numarul de frame-uri pe secunda./
        final double timeFrame = 1000000000 / framesPerSecond; //!< Durata unui frame in nanosecunde./

        /// Atat timp timp cat threadul este pornit Update() & Draw()
        while (runState) {
            /// Se obtine timpul curent
            curentTime = System.nanoTime();
            /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            if ((curentTime - oldTime) > timeFrame) {
                /// Actualizeaza pozitiile elementelor
                Update();
                /// Deseneaza elementele grafica in fereastra.
                Draw();
                oldTime = curentTime;
            }
        }


    }

    /*! \fn public synchronized void start()
        \brief Creaza si starteaza firul separat de executie (thread).

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StartGame() {
        if (!runState) {
            /// Se actualizeaza flagul de stare a threadului
            runState = true;
            /// Se construieste threadul avand ca parametru obiectul Game. De retinut faptul ca Game class
            /// implementeaza interfata Runnable. Threadul creat va executa functia run() suprascrisa in clasa Game.
            gameThread = new Thread(this);
            /// Threadul creat este lansat in executie (va executa metoda run())
            gameThread.start();
        } else {
            /// Thread-ul este creat si pornit deja
        }
    }

    /*! \fn public synchronized void stop()
        \brief Opreste executie thread-ului.

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */


    public synchronized void StopGame() {
        if (runState) {
            /// Actualizare stare thread
            runState = false;
            /// Metoda join() arunca exceptii motiv pentru care trebuie incadrata intr-un block try - catch.
            try {
                /// Metoda join() pune un thread in asteptare panca cand un altul isi termina executie.
                /// Totusi, in situatia de fata efectul apelului este de oprire a threadului.
                gameThread.join();
            } catch (InterruptedException ex) {
                /// In situatia in care apare o exceptie pe ecran vor fi afisate informatii utile pentru depanare.
                ex.printStackTrace();
            }
        } else {
            /// Thread-ul este oprit deja.
        }
    }

    /*! \fn private void Update()
        \brief Actualizeaza starea elementelor din joc.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    private void Update() {
        nameInputManager.Update();
        keyManager.Update();
        if (State.GetState() != null) State.GetState().Update();
    }

    /*! \fn private void Draw()
        \brief Deseneaza elementele grafice in fereastra coresponzator starilor actualizate ale elementelor.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */

    private void Draw() {
        /// Returnez bufferStrategy pentru canvasul existent
        bs = wnd.GetCanvas().getBufferStrategy();
        /// Verific daca buffer strategy a fost construit sau nu
        if (bs == null) {
            /// Se executa doar la primul apel al metodei Draw()
            try {
                /// Se construieste tripul buffer
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            } catch (Exception e) {
                /// Afisez informatii despre problema aparuta pentru depanare.
                e.printStackTrace();
            }
        }
        /// Se obtine contextul grafic curent in care se poate desena.
        g = bs.getDrawGraphics();
        /// Se sterge ce era
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        if (State.GetState() != null) State.GetState().Render(g);
        bs.show();

        /// Elibereaza resursele de memorie aferente contextului grafic curent (zonele de memorie ocupate de
        /// elementele grafice ce au fost desenate pe canvas).
        g.dispose();
    }

    public KeyManager GetKeyManager() {
        return keyManager;
    }

    public MouseManager GetMouseManager() {
        return mouseManager;
    }


    public State getPlayState() {
        return playState;
    }

    public PlayState getPlayState2(){
        return (PlayState) playState;
    }

    public State getMenuState() {
        return menuState;
    }

    public State getHelpState() {
        return helpState;
    }

    public State getPauseState() {
        return pauseState;
    }

    public State getGameOverState() {
        return gameOverState;
    }

    public State getGameWonState(){
        return gameWonState;
    }

    public State getSettingsState() {
        return settingsState;
    }

    public State getStateSetPlayerState(){
        return stateSetPlayerState;
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

}