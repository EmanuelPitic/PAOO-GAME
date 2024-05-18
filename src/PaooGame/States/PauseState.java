package PaooGame.States;


import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.Level.Level;
import PaooGame.RefLinks;
import PaooGame.UI.ClickListener;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;

import java.awt.*;
import java.lang.management.PlatformLoggingMXBean;

/*! \public class PauseState extends State
    \brief Implementeaza notiunea de pauza pentru joc.
 */
public class PauseState extends State
{
    /*! \fn public PauseState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PauseState(RefLinks refLink)
    {
        super(refLink);
        uiManager=new UIManager(refLink);
        uiManager.addObject(new UIImageButton(190, 420, 128, 32, Assets.resume, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(State.GetPreviousState().getUiManager());
                State.SetState(State.GetPreviousState());
               // refLink.GetGame().getDisplay().GetFrame().requestFocusInWindow();

            }
        }));
        uiManager.addObject(new UIImageButton(380, 420, 128, 32, Assets.save_btn, new ClickListener() {
            @Override
            public void onClick() {
                //refLink.GetMouseManager().setUIManager(State.GetPreviousState().getUiManager());
                //State.SetState(State.GetPreviousState());
                // refLink.GetGame().getDisplay().GetFrame().requestFocusInWindow();
                StringBuilder toSave=new StringBuilder();
                //salvam prima data numele in string
                toSave.append(refLink.GetGame().getStateSetPlayerState().toString()).append('/')
                        .append(Level.getInstance().getLevelNr()).append('/') //nr nivelului
                        .append(refLink.GetGame().getPlayState2().getTimeInGame()).append('/')//scorul
                        .append(refLink.GetGame().getPlayState2().getCountdown()).append('/')
                        .append(refLink.GetWorld().getEntityManager().getHero().toString())//despre unde e eroul
                        .append(refLink.GetWorld().getEntityManager().toString());
                System.out.println(toSave.toString());

            }
        }));

    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta pentru Pause State.
     */
    @Override
    public void Update()
    {
        //audioPlayer.Update(gameSettings);
        uiManager.Update();
    }



    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta .

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Render(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 60));
        try{
            g.drawImage(Assets.menuBackground,-100,0,2000,544,null);
            g.drawImage(Assets.thiefImage,875,300,200,200,null);
        }
        catch (Exception e){
            System.out.println("Exception occured "+e);
        }
        finally {
            uiManager.Render(g);
            Text.drawString(g,"YOUR GAME IS PAUSED",100,200,Color.BLACK);

            g.setFont(new Font("Arial", Font.BOLD, 40));
            Text.drawString(g,"Time remained: "+refLink.GetGame().getPlayState2().getCountdown(),100,300,Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            Text.drawString(g,"RESUME ->",80,450,Color.BLACK);
        }


    }
}
