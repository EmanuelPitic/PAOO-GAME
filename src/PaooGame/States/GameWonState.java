package PaooGame.States;


import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.Level.Level;
import PaooGame.RefLinks;
import PaooGame.UI.ClickListener;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;

import java.awt.*;

/*! \public class GameOverState extends State
    \brief Implementeaza notiunea de game over.
 */
public class GameWonState extends State
{


    /*! \fn public GameOverState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public GameWonState(RefLinks refLink, boolean isLoaded)
    {
        super(refLink);
        uiManager=new UIManager(refLink);
        refLink.GetMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(400, 300, 192, 32, Assets.back_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getMenuState().getUiManager());
                State.SetState(refLink.GetGame().getMenuState());

                /// atunci cand butonul "Exit" este apasat, va fi afisat meniul,
                /// dupa care o eventuala apasare a butonului  "Start" va relua
                /// jocul de la primul nivel
                if(!isLoaded)
                {
                    Level.getInstance().setLevel(0);
                    Level.getInstance().setChangeLevel(true);
                }
                else {
                    Level.getInstance().setChangeLevel(false);
                }


                //refLink.GetGame().getDisplay().GetFrame().requestFocusInWindow();

            }
        }));
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta de game over.
     */
    @Override
    public void Update()
    {
        audioPlayer.Update(gameSettings);
        uiManager.Update();
    }



    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a starii de game over.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Render(Graphics g) {

        try {
            g.drawImage(Assets.menuBackground,-100,0,2000,544,null);
            g.drawImage(Assets.thiefImage,875,300,200,200,null);
            g.drawImage(Assets.gloveImage, 0,180,379,421,null);
        }
        catch (Exception e){
            System.out.println("Exception occured "+e);
        }
        finally {
            g.setFont(new Font("Algerian", Font.BOLD, 100));
            Text.drawString(g,"GAME  COMPLETED",50,100,Color.BLACK);

            g.setFont(new Font("Arial", Font.BOLD, 60));
            Text.drawString(g,"You WON!",250,200,Color.BLACK);
            uiManager.Render(g);
        }



    }
}
