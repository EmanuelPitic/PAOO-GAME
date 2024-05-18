package PaooGame.States;


import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.RefLinks;
import PaooGame.UI.ClickListener;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;

import java.awt.*;

/*! \public class HelpState extends State
    \brief Implementeaza notiunea de help pentru joc (explica ce are de facut eroul pentru a castiga)
 */
public class HelpState extends State
{


    /*! \fn public HelpState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public HelpState(RefLinks refLink)
    {
        super(refLink);
        uiManager=new UIManager(refLink);
        refLink.GetMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(190, 420, 128, 32, Assets.resume, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(State.GetPreviousState().getUiManager());
                State.SetState(State.GetPreviousState());
//refLink.GetGame().getDisplay().GetFrame().requestFocusInWindow();

            }
        }));
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a starii de help.
     */
    @Override
    public void Update()
    {
        audioPlayer.Update(gameSettings);
        uiManager.Update();
    }



    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta de help .

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Render(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 20));
        try{
            g.drawImage(Assets.menuBackground,0,0,2000,544,null);
            g.drawImage(Assets.gloveImage,780,180,379,421,null);
        }
        catch (Exception e){
            System.out.println("Exception occured "+e);
        }
        finally {
            uiManager.Render(g);

            Text.drawString(g,"Help the thief steal the most valuable item, the Golden Glove",120,60,Color.BLACK);
            Text.drawString(g,"You need to know that when you touch one of the enemies, your life will decrease.",120,90,Color.BLACK);
            Text.drawString(g,"You can move through the museum / houses using the arrow keys.  ",120,120,Color.BLACK);
            Text.drawString(g,"You have to collect the Key(Glove-on the last level), and return back to spawn to win.  ",120,150,Color.BLACK);
            Text.drawString(g,"If a door is locked, usually you can unlock it by pressing a button ;) ",120,180,Color.BLACK);
            Text.drawString(g,"For the Master Door, you need to pull all the levers. ",120,210,Color.BLACK);
            Text.drawString(g,"To save current state of the game, go in the Pause menu and press SAVE", 120, 240, Color.BLACK);
            g.drawImage(Assets.ar_UP,240,240,null);
            g.drawImage(Assets.ar_DOWN,240,310,null);
            g.drawImage(Assets.ar_LEFT,170,310,null);
            g.drawImage(Assets.ar_RIGHT,310,310,null);

            Text.drawString(g,"RESUME ->",80,450,Color.BLACK);
        }





    }
}
