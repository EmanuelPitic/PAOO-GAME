package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.Level.Level;
import PaooGame.RefLinks;
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

public class
PlayState extends State{

    private World world; /*!< O referinta catre harta jocului.*/
    boolean isLoaded = false;
    String cuv;
    public static final int DEFAULT_COUNTDOWN=45;
    public int countdown=DEFAULT_COUNTDOWN; /*!< Numarul de secunde alocate pt fiecare nivel.*/
    private Timer timer;            /*!< Referinta catre un obiect de tip timer ce contorizeaza scurgerea timpului pt fiecare nivel.*/
    Level lvl=Level.getInstance();  /*!< Referinta catre un obiect de tip nivel.*/
    boolean isLoadedForEndGame= false;

    public int timeInGame = 0;
    public int score = 0;

    /*! \Constructorul pentru Playstate
    \brief are ca parametrii reflink, un boolean care verifica daca e incarcat din Load si stringul sepcific daca e
 */

    public PlayState(RefLinks refLink, boolean load, String cuv){
        super(refLink);
        isLoaded=load;
        isLoadedForEndGame=load;
        timer=new Timer(1000);
        if(load) {

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

        uiManager=new UIManager(refLink);
        refLink.GetMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(300, 0, 192, 32, Assets.pause_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getPauseState().getUiManager());
                State.SetState(refLink.GetGame().getPauseState());
            }
        }));


        uiManager.addObject(new UIImageButton(500, 0, 192, 32, Assets.quit_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getMenuState().getUiManager());
                State.SetState(refLink.GetGame().getMenuState());
            }
        }));


        uiManager.addObject(new UIImageButton(700, 0, 192, 32, Assets.help_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getHelpState().getUiManager());
                State.SetState(refLink.GetGame().getHelpState());


            }
        }));
        uiManager.addObject(new UIImageButton(900, 0, 192, 32, Assets.settings_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getSettingsState().getUiManager());
                State.SetState(refLink.GetGame().getSettingsState());
            }
        }));
    }


    /*! \ Seteaza lumea
    \brief verifica daca are parametru sau nu, pentru a apela constructorul specific
 */
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

        }
        else if(refLink.GetWorld().getEntityManager().getHero().getHealth()<=0  )
        {
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
            else
            {
                lvl.setChangeLevel(false);
            }
            State.SetState(refLink.GetGame().gameOverState = new GameOverState(refLink, isLoadedForEndGame));

        }

    }


    /// functie ce verifica trecerea la un alt nivel

    /*! \fn private void verify()
         \brief Se verifica daca jocul s-a terminat sau ,in caz contrar, se trece la urmatorul nivel.
   */
    private void verify()
    {
        if (lvl.isChangeLevel() ) {
            isLoaded=false;
            lvl.incLevel();
            if(lvl.getLevelNr()==1)
            {
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

                //jocul a fost finalizat cu succes si se revine in MenuState pentru a se relua, la cerere
                score+=100;
                if(!isLoadedForEndGame)
                    SQL.getInstance().insertScore(refLink.GetGame().getStateSetPlayerState().toString(), score);
                else {
                    SQL.getInstance().insertScore(cuv.substring(0, cuv.indexOf('/')), score);
                }
                State.SetState(refLink.GetGame().gameWonState = new GameWonState(refLink, isLoadedForEndGame));
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getGameWonState().getUiManager());
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


    public int getScore()
    {
        return score;
    }

}
