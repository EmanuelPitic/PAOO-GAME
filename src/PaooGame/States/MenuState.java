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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


        uiManager.addObject(new UIImageButton(450, 160, 192, 64, Assets.newGame_btn, new ClickListener() {
            @Override
            public void onClick() {

                //refLink.SetWorld(new World(refLink));
                Level.getInstance().setLevel(0);
                Level.getInstance().setChangeLevel(true);
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getStateSetPlayerState().getUiManager());
                State.SetState(refLink.GetGame().stateSetPlayerState = new StateSetPlayerName(refLink) );
            }
        }));

        uiManager.addObject(new UIImageButton(450, 230, 192, 64, Assets.loadGame_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetGame().stateSelectSave = new StateSelectSave(refLink);
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().getUiManager());
                State.SetState(refLink.GetGame().stateSelectSave = new StateSelectSave(refLink));
            }
        }));

        uiManager.addObject(new UIImageButton(450, 300, 192, 64, Assets.leaderboard_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetGame().leaderBoardState = new LeaderBoardState(refLink);
                refLink.GetMouseManager().setUIManager(refLink.GetGame().leaderBoardState.getUiManager());
                State.SetState(refLink.GetGame().leaderBoardState = new LeaderBoardState(refLink));
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
        refLink.GetGame().getAudioPlayer().Update(refLink.GetGame().getGameSettings());
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
