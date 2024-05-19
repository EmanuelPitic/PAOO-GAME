package PaooGame.States;


import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.RefLinks;
import PaooGame.SQLite.SQL;
import PaooGame.UI.ClickListener;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;

import java.awt.*;
import java.util.ArrayList;

/*! \public class LeaderboardState extends State
    \brief Implementeaza notiunea de LeaderBoard pentru joc
 */
public class LeaderBoardState extends State
{


    /*! \fn public HLeaderBoardState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public LeaderBoardState(RefLinks refLink)
    {
        super(refLink);
        uiManager=new UIManager(refLink);
        refLink.GetMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(190, 460, 128, 32, Assets.back_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(State.GetPreviousState().getUiManager());
                State.SetState(State.GetPreviousState());
            }
        }));
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a starii de help.
     */
    @Override
    public void Update()
    {
        refLink.GetGame().getAudioPlayer().Update(refLink.GetGame().getGameSettings());
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
            ArrayList<String> highestScores = new ArrayList<>();
            highestScores = SQL.getInstance().getBestSCores();

            g.setFont(new Font("Arial", Font.PLAIN, 80));
            Text.drawString(g,"LEADERBOARD",200,100,Color.BLACK);

            g.setFont(new Font("Arial", Font.PLAIN, 40));
            int yOffset = 200;
            int i = 0;
            Text.drawString(g,"Name",100,200,Color.BLACK);
            Text.drawString(g,"Score",600,200,Color.BLACK);
            for ( i = 0; i<3;i++) {

                Text.drawString(g, highestScores.get(i).substring(0,highestScores.get(i).lastIndexOf(' ')), 100, yOffset + 80, Color.BLACK);
                Text.drawString(g, highestScores.get(i).substring(highestScores.get(i).lastIndexOf(' ')) , 600, yOffset +80, Color.BLACK);
                yOffset += 80;
            }
            g.setFont(new Font("Arial", Font.PLAIN, 20));
        }
    }
}
