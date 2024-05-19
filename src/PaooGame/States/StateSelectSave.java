package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.Level.Level;
import PaooGame.RefLinks;
import PaooGame.SQLite.SQL;
import PaooGame.UI.ClickListener;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/*! \public class StateSelectSave extends State
    \brief Implementează State-ul cu loads
*/

public class StateSelectSave extends State {
    private List<String> saveNames; /*!< \brief Lista numelor jocurilor salvate */

    /*! \fn public StateSetPlayerName(RefLinks refLink)
         \brief Constructorul clasei care inițializează variabilele și UIManager-ul.
         \param refLink Referință către clasa principală a jocului.
     */
    public StateSelectSave(RefLinks refLink) {
        super(refLink);
        uiManager = new UIManager(refLink);
        refLink.GetMouseManager().setUIManager(uiManager);

        /*! \brief Recuperează lista numelor jocurilor salvate */
        saveNames = new ArrayList<>();
        ArrayList<String> gameStates = SQL.getInstance().getLastGameStates();

        /*! \brief Adaugă butoane UI pentru fiecare joc salvat */
        int yOffset = 200;
        for (String gameState : gameStates) {
            uiManager.addObject(new UIImageButton(650, yOffset, 192, 64, Assets.ok_btn, new ClickListener() {
                @Override
                public void onClick() {
                    refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().getUiManager());
                    State.SetState(refLink.GetGame().playState = new PlayState(refLink, true, gameState));
                    refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().getUiManager());
                }
            }));
            yOffset += 80;
        }

        /*! \brief Adaugă un buton pentru a reveni la starea anterioară */
        uiManager.addObject(new UIImageButton(900, 460, 128, 32, Assets.back_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(State.GetPreviousState().getUiManager());
                State.SetState(State.GetPreviousState());
            }
        }));
    }


    @Override
    public void Update() {
        uiManager.Update();
    }


    @Override
    public void Render(Graphics g) {
        try {
            g.drawImage(Assets.menuBackground, -100, 0, 2000, 544, null);
        } catch (Exception m) {
            System.out.println("Exception occurred: " + m);
        } finally {
            g.setFont(new Font("Algerian", Font.PLAIN, 80));
            Text.drawString(g, "Select a Save", 1056 / 6, 100, Color.BLACK);
            uiManager.Render(g);

            /*! \brief Recuperează ultimele nume și nivele salvate din baza de date */
            saveNames = SQL.getInstance().getLastNames();
            List<String> lastLevels = SQL.getInstance().getLastLevels();

            int yOffset = 200;
            g.setFont(new Font("Arial", Font.PLAIN, 40));
            for (int i = 0; i < 4; i++) {
                Text.drawString(g, saveNames.get(i) + ";", 90, yOffset + 40, Color.BLACK);
                Text.drawString(g, "Level: " + lastLevels.get(i), 280, yOffset + 40, Color.BLACK);
                yOffset += 80;
            }
        }
    }
}
