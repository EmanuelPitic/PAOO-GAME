package PaooGame.States;


import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.RefLinks;
import PaooGame.UI.ClickListener;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;

import java.awt.*;

/*! \public class SettingsState extends State
    \brief Implementeaza notiunea de setari ale jocului.

    Aici setarile vor fi salvate intr-o baza de date sqlite.
 */
public class SettingsState extends State
{

    /*! \fn public SettingsState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public SettingsState(RefLinks refLink)
    {
        super(refLink);
        uiManager=new UIManager(refLink);
        refLink.GetMouseManager().setUIManager(uiManager);






        ///start muzica
        uiManager.addObject(new UIImageButton(450, 230, 192, 64, Assets.musicOn_btn, new ClickListener() {
            @Override
            public void onClick() {
                gameSettings.setMusic(true);
                //SQL.getInstance().addAudioStatus("music",1);
            }
        }));


        ///stop muzica
        uiManager.addObject(new UIImageButton(450, 300, 192, 64, Assets.musicOff_btn, new ClickListener() {
            @Override
            public void onClick() {
                gameSettings.setMusic(false);
               // SQL.getInstance().addAudioStatus("music",0);
            }
        }));

        /// back
        uiManager.addObject(new UIImageButton(450, 370, 192, 64, Assets.back_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(State.GetPreviousState().getUiManager());
                State.SetState(State.GetPreviousState());
               // refLink.GetGame().getDisplay().GetFrame().requestFocusInWindow();
            }
        }));
    }


    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a setarilor.
     */
    @Override
    public void Update()
    {
        audioPlayer.Update(gameSettings);
        uiManager.Update();
    }



    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a setarilor.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Render(Graphics g) {

        try {
            g.drawImage(Assets.menuBackground,-100,0,2000,544,null);
            g.drawImage(Assets.thiefImage,875,300,200,200,null);

        }
        catch (Exception e){
            System.out.println("Exception occured "+e);
        }
        finally {
            g.setFont(new Font("Algerian", Font.PLAIN, 100));
            Text.drawString(g,"Music status",1056/5,100,Color.BLACK);

            uiManager.Render(g);
        }

    }
}
