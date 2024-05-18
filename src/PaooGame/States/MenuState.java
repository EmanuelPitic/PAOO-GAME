package PaooGame.States;


import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.Level.Level;
import PaooGame.RefLinks;
import PaooGame.UI.ClickListener;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;
import PaooGame.Worlds.World;

import java.awt.*;

/*! \public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State
{

    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */

    public MenuState(RefLinks refLink)
    {
        super(refLink);
        uiManager=new UIManager(refLink);
        refLink.GetMouseManager().setUIManager(uiManager);


        uiManager.addObject(new UIImageButton(450, 230, 192, 64, Assets.newGame_btn, new ClickListener() {
            @Override
            public void onClick() {
                //refLink.SetWorld(new World(refLink));
                Level.getInstance().setLevel(0);
                Level.getInstance().setChangeLevel(true);
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().getUiManager());
                State.SetState(refLink.GetGame().playState = new PlayState(refLink, false));
            }
        }));

        uiManager.addObject(new UIImageButton(450, 300, 192, 64, Assets.loadGame_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().getUiManager());
                //refLink.SetWorld(new World(refLink, "dfas/3/7/73/132.0/380.0/30/false/true/false/true/false/false/false/576.0/444.0/70/false/3/508.0/288.0/158/false/2/992.0/120.0/58/true/3/704.0/224.0/86/false/1/640.0/432.0/50/true/1/64.0/292.0/26/true/1/96.0/220.0/26/true/3/160.0/292.0/26/true/1/224.0/220.0/26/true/3/288.0/292.0/26/true/1/424.0/32.0/118/false/1/"));
                //refLink.SetWorld(new World(refLink, "as/1/30/15/24.0/77.0/20/true/false/true/false/608.0/340.0/109/true/1/"));
                State.SetState(refLink.GetGame().playState = new PlayState(refLink, true));
                //refLink.SetWorld(new World(refLink, "dfas/3/7/73/132.0/380.0/30/false/true/false/true/false/false/false/576.0/444.0/70/false/3/508.0/288.0/158/false/2/992.0/120.0/58/true/3/704.0/224.0/86/false/1/640.0/432.0/50/true/1/64.0/292.0/26/true/1/96.0/220.0/26/true/3/160.0/292.0/26/true/1/224.0/220.0/26/true/3/288.0/292.0/26/true/1/424.0/32.0/118/false/1/"));
                //Level.getInstance().setChangeLevel(false);
              //  refLink.GetGame().getDisplay().GetFrame().requestFocusInWindow();

            }
        }));
        uiManager.addObject(new UIImageButton(450, 370, 192, 64, Assets.help_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getHelpState().getUiManager());
                State.SetState(refLink.GetGame().getHelpState());
               // refLink.GetGame().getDisplay().GetFrame().requestFocusInWindow();

            }
        }));

        uiManager.addObject(new UIImageButton(450, 440, 192, 64, Assets.settings_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getSettingsState().getUiManager());
                State.SetState(refLink.GetGame().getSettingsState());
               // refLink.GetGame().getDisplay().GetFrame().requestFocusInWindow();

            }
        }));





    }


    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update()
    {
        audioPlayer.Update(gameSettings);
        uiManager.Update();
    }



    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Render(Graphics g) {
        try{
            g.drawImage(Assets.menuBackground,-100,0,2000,544,null);
            g.drawImage(Assets.thiefImage,875,300,200,200,null);
        }
        catch (Exception m)
        {
            System.out.println("Exception occured "+m);
        }
        finally {

            g.setFont(new Font("Algerian", Font.PLAIN, 80));
            Text.drawString(g,"The Golden Glove",1056/6,100,Color.BLACK);
            uiManager.Render(g);
        }


    }
}
