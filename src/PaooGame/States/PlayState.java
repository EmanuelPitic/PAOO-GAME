package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.Level.Level;
import PaooGame.RefLinks;
//import PaooGame.Timer.Timer;
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

    public static final int DEFAULT_COUNTDOWN=60;
    public static int countdown=DEFAULT_COUNTDOWN; /*!< Numarul de secunde alocate pt fiecare nivel.*/
    //private Timer timer;            /*!< Referinta catre un obiect de tip timer ce contorizeaza scurgerea timpului pt fiecare nivel.*/
    Level lvl=Level.getInstance();  /*!< Referinta catre un obiect de tip nivel.*/

  // public static Timer timer2;           /*!< Timer pentru afisare text inainte de trecerea la alt nivel.*/
    private int freezeCountdown;
    private int freezeLevel;


    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink){

        super(refLink);
        //timer2=new Timer(0);
       // timer=new Timer(1000);
        world=new World(refLink);
        refLink.SetWorld(world);

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
                lvl.setLevel(0);
                lvl.setChangeLevel(true);


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


    /*! \fn public void Update()
       \brief Actualizeaza starea curenta a jocului.
    */
    @Override
    public void Update() {
        uiManager.Update();
        audioPlayer.Update(gameSettings);
        verify();
        world.Update();

/*        if(timer.TimePassed()){
            countdown--;
            timer.SetDelay(1000);
        }

        if(countdown==0)
        {
            refLink.GetMouseManager().setUIManager(refLink.GetGame().getGameOverState().getUiManager());
            lvl.setLevel(0);
            lvl.setChangeLevel(true);
            State.SetState(refLink.GetGame().getGameOverState());
            refLink.GetGame().getDisplay().GetFrame().requestFocusInWindow();
        }*/
        if(refLink.GetWorld().getEntityManager().getHero().isDead())
        {
            refLink.GetMouseManager().setUIManager(refLink.GetGame().getGameOverState().getUiManager());
            lvl.setLevel(0);
            lvl.setChangeLevel(true);
            State.SetState(refLink.GetGame().getGameOverState());
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

            lvl.incLevel();
            if (lvl.getLevelNr() < 4) {
                SetWorld();
                countdown=60;
                lvl.setChangeLevel(false);
            } else {
                /// jocul a fost finalizat cu succes si se revine in MenuState pentru a se relua, la cerere
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getMenuState().getUiManager());
                lvl.setLevel(0);
                State.SetState(refLink.GetGame().getMenuState());
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
        //timer.Render(g);


/*        if(notify){
           // System.out.println("Old record: "+SQL.getInstance().getHighScore(Level.getInstance().getLevelNr()));
            //System.out.println("New score record at level: "+Level.getInstance().getLevelNr()+" : "+PlayState.countdown);

            int timeDisplay=DEFAULT_COUNTDOWN-freezeCountdown;
            Text.drawString(g,"Old record at level "+freezeLevel+" : "+SQL.getInstance().getHighScore(Level.getInstance().getLevelNr()),500,520,Color.WHITE);
            Text.drawString(g,"New score record at level: "+freezeLevel+" : "+timeDisplay,500,530,Color.WHITE);



        }
        if(timer2.TimePassed())
            notify=false;*/

        Text.drawString(g,""+refLink.GetWorld().getEntityManager().getHero().getHealth(),240,20,Color.WHITE);
    }


    /// Getters & Setters pt atribute
    public void setCountdown(int value){
        countdown=value;
    }

    public int getCountdown(){
        return countdown;
    }

}
