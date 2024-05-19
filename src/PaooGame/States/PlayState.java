package PaooGame.States;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.Level.Level;
import PaooGame.RefLinks;
//import PaooGame.Timer.Timer;
import PaooGame.SQLite.SQL;
import PaooGame.Timer.Timer;
import PaooGame.UI.ClickListener;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;
import PaooGame.Worlds.World;

import java.awt.*;

/*! \public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */

public class PlayState extends State{

    private World world; /*!< O referinta catre harta jocului.*/
    boolean isLoaded = false;
    String cuv;
    public static final int DEFAULT_COUNTDOWN=45;
    public int countdown=DEFAULT_COUNTDOWN; /*!< Numarul de secunde alocate pt fiecare nivel.*/
    private Timer timer;            /*!< Referinta catre un obiect de tip timer ce contorizeaza scurgerea timpului pt fiecare nivel.*/
    Level lvl=Level.getInstance();  /*!< Referinta catre un obiect de tip nivel.*/
    boolean isLoadedForEndGame= false;


    public static Timer timer2;           /*!< Timer pentru afisare text inainte de trecerea la alt nivel.*/
    public int timeInGame = 0;

    public int score = 0;
    private int freezeCountdown;
    private int freezeLevel;



    public PlayState(RefLinks refLink, boolean load, String cuv){
        super(refLink);
        isLoaded=load;
        isLoadedForEndGame=load;
        timer2=new Timer(0);
        timer=new Timer(1000);
        if(load) {
            //cuv = "asd/3/38/75/24.0/373.0/30/false/true/false/true/false/true/false/384.0/32.0/23/true/3/328.0/32.0/95/true/3/224.0/460.0/1/false/1/160.0/52.0/1/false/3/96.0/460.0/1/false/1/64.0/52.0/1/false/3/640.0/408.0/47/true/1/784.0/180.0/209/false/2/992.0/88.0/65/false/1/480.0/480.0/281/false/1/576.0/296.0/33/false/3/";
            this.cuv=cuv;
            String[] words=cuv.split("/");
            timeInGame = Integer.parseInt(words[2]);
            countdown =  Integer.parseInt(words[3]);

            world = new World(refLink, cuv);
            refLink.SetWorld(world);
        }
        else
        {
            world=new World(refLink);
            refLink.SetWorld(world);
        }

       // countdown=42;
       // timeInGame=82;
        uiManager=new UIManager(refLink);
        refLink.GetMouseManager().setUIManager(uiManager);


        uiManager.addObject(new UIImageButton(300, 0, 192, 32, Assets.pause_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getPauseState().getUiManager());
                State.SetState(refLink.GetGame().getPauseState());
                //refLink.GetGame().getDisplay().GetFrame().requestFocusInWindow();
            }
        }));


        uiManager.addObject(new UIImageButton(500, 0, 192, 32, Assets.quit_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getMenuState().getUiManager());
                State.SetState(refLink.GetGame().getMenuState());
                /// atunci cand butonul "Exit" este apasat, va fi afisat meniul,
                /// dupa care o eventuala apasare a butonului  "Start" va relua
                /// jocul de la primul nivel
                //level=0;
                //changeLevel=true;
                //lvl.setLevel(0);
               // lvl.setChangeLevel(true);


                // refLink.GetGame().getDisplay().GetFrame().requestFocusInWindow();
            }
        }));


        uiManager.addObject(new UIImageButton(700, 0, 192, 32, Assets.help_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getHelpState().getUiManager());
                State.SetState(refLink.GetGame().getHelpState());
                //  refLink.GetGame().getDisplay().GetFrame().requestFocusInWindow();

            }
        }));
        uiManager.addObject(new UIImageButton(900, 0, 192, 32, Assets.settings_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getSettingsState().getUiManager());
                State.SetState(refLink.GetGame().getSettingsState());
                //  refLink.GetGame().getDisplay().GetFrame().requestFocusInWindow();

            }
        }));

    }


    /*! \fn public void SetWorld()
        \brief Reseteaza harta (stabileste o noua harta) si o ataseaza jocului prin scurtatura reflink.
     */
    public void SetWorld()
    {
        world=new World(refLink);
        refLink.SetWorld(world);
    }

    public void SetWorld(boolean isLoad)
    {
        if(isLoad)
        {
            world=new World(refLink, cuv);
            refLink.SetWorld(world);
        }
        else{
            world=new World(refLink);
            refLink.SetWorld(world);
        }
    }


    @Override
    public void Update() {
        uiManager.Update();
        refLink.GetGame().getAudioPlayer().Update(refLink.GetGame().getGameSettings());
        verify();
        world.Update();

       if(timer.TimePassed()){
           if (isLoadedForEndGame) {
               score=100*(lvl.getLevelNr())-timeInGame;
               //score+=(lvl.getLevelNr()-1)*100;
           }
           else{
               score=100*(lvl.getLevelNr())-timeInGame;
           }
            countdown--;
            timeInGame++;
            timer.SetDelay(1000);
        }

        if(countdown==0)
        {
            //System.out.println(timeInGame);
            if(!isLoadedForEndGame)
                SQL.getInstance().insertScore(refLink.GetGame().getStateSetPlayerState().toString(), score);
            else {
                SQL.getInstance().insertScore(cuv.substring(0, cuv.indexOf('/')), score);
            }
            refLink.GetMouseManager().setUIManager(refLink.GetGame().getGameOverState().getUiManager());
            if(!isLoadedForEndGame)
            {
                lvl.setLevel(0);
                lvl.setChangeLevel(true);
            }
            else {
                lvl.setChangeLevel(false);
            }
            State.SetState(refLink.GetGame().gameOverState = new GameOverState(refLink, isLoadedForEndGame));
            //refLink.GetGame().getDisplay().GetFrame().requestFocusInWindow();
        }
        else if(refLink.GetWorld().getEntityManager().getHero().getHealth()<=0  )
        {
            if(!isLoadedForEndGame)
                SQL.getInstance().insertScore(refLink.GetGame().getStateSetPlayerState().toString(), score);
            else {
                SQL.getInstance().insertScore(cuv.substring(0, cuv.indexOf('/')), score);
            }
            System.out.println(timeInGame);
            refLink.GetMouseManager().setUIManager(refLink.GetGame().getGameOverState().getUiManager());
            if(!isLoadedForEndGame)
            {
                lvl.setLevel(0);
                lvl.setChangeLevel(true);
            }
            else
            {
                lvl.setChangeLevel(false);
            }
            State.SetState(refLink.GetGame().gameOverState = new GameOverState(refLink, isLoadedForEndGame));
          //  refLink.GetGame().getDisplay().GetFrame().requestFocusInWindow();
        }

    }


    /// functie ce verifica trecerea la un alt nivel

    /*! \fn private void verify()
         \brief Se verifica daca jocul s-a terminat sau ,in caz contrar, se trece la urmatorul nivel.
   */


    private void verify()
    {
        if (lvl.isChangeLevel() ) {
            if(lvl.getLevelNr()!=0){
                //SQL.getInstance().addScore(Level.getInstance().getLevelNr(), countdown);
                freezeCountdown=countdown;
                freezeLevel=lvl.getLevelNr();
            }
            isLoaded=false;
            lvl.incLevel();
            if(lvl.getLevelNr()==1)
            {
              //  System.out.println(timeInGame);
                timeInGame=0;
                SetWorld(isLoaded);
                countdown=60;
                lvl.setChangeLevel(false);
                score+=100;
            }
            else if (lvl.getLevelNr() == 2) {
                System.out.println(timeInGame);
                SetWorld(isLoaded);
                countdown=60;
                lvl.setChangeLevel(false);
                score+=100;

            }
            else if (lvl.getLevelNr() == 3) {
                System.out.println(timeInGame);
                SetWorld(isLoaded);
                countdown=100;
                lvl.setChangeLevel(false);
                score+=100;
            }
            else {
                //System.out.println(timeInGame);
                //jocul a fost finalizat cu succes si se revine in MenuState pentru a se relua, la cerere
                score+=100;
                if(!isLoadedForEndGame)
                    SQL.getInstance().insertScore(refLink.GetGame().getStateSetPlayerState().toString(), score);
                else {
                    SQL.getInstance().insertScore(cuv.substring(0, cuv.indexOf('/')), score);
                }
               // System.out.println(timeInGame);
                State.SetState(refLink.GetGame().gameWonState = new GameWonState(refLink, isLoadedForEndGame));
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getGameWonState().getUiManager());
               // refLink.GetGame().getDisplay().GetFrame().requestFocusInWindow();

            }
        }
    }



    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Render(Graphics g) {


        world.Render(g);
        uiManager.Render(g);

        lvl.Render(g);
        timer.Render(g);
        try{
            g.drawImage(Assets.heart,200,0,32,32,null);
        }
        catch (Exception e){
            System.out.println("Exception occured "+e);
        }

 /*       if(notify){
           // System.out.println("Old record: "+SQL.getInstance().getHighScore(Level.getInstance().getLevelNr()));
            //System.out.println("New score record at level: "+Level.getInstance().getLevelNr()+" : "+PlayState.countdown);

            int timeDisplay=DEFAULT_COUNTDOWN-freezeCountdown;
            Text.drawString(g,"Old record at level "+freezeLevel+" : "+SQL.getInstance().getHighScore(Level.getInstance().getLevelNr()),500,520,Color.WHITE);
            Text.drawString(g,"New score record at level: "+freezeLevel+" : "+timeDisplay,500,530,Color.WHITE);



        }*/
        if(timer2.TimePassed())
            notify=false;
        Text.drawString(g,""+countdown,30,20,Color.WHITE);
        Text.drawString(g,"Score: "+score,50,20,Color.WHITE);
        Text.drawString(g,""+refLink.GetWorld().getEntityManager().getHero().getHealth(),240,20,Color.WHITE);
    }


    /// Getters & Setters pt atribute
    public void setCountdown(int value){
        countdown=value;
    }

    public int getCountdown(){
        return countdown;
    }

    public int getTimeInGame(){
        return timeInGame;
    }

    public void setTimeInGame(int time){
        timeInGame = time;
    }

    public int getScore()
    {
        return score;
    }

}
