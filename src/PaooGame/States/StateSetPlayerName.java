package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.Input.NameInputManager;
import PaooGame.RefLinks;
import PaooGame.UI.ClickListener;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;

import java.awt.*;

/*! \public class StateSetPlayerName extends State
    \brief Implementează State-ul de unde se citește numele
*/
public class StateSetPlayerName extends State {

    private String playerName; /*! \brief Numele curent al jucătorului */
    private NameInputManager nameInputManager; /*! \brief Gestionarul pentru inputul numelui */

    /*! \fn public StateSetPlayerName(RefLinks refLink)
        \brief Constructorul clasei care inițializează variabilele și UIManager-ul.
        \param refLink Referință către clasa principală a jocului.
    */
    public StateSetPlayerName(RefLinks refLink) {
        super(refLink);
        nameInputManager = refLink.GetGame().nameInputManager;
        playerName = "";

        uiManager = new UIManager(refLink);
        refLink.GetMouseManager().setUIManager(uiManager);

        /*! \brief Adaugă butonul pentru confirmarea numelui introdus */
        uiManager.addObject(new UIImageButton(450, 400, 192, 64, Assets.ok_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().getUiManager());
                playerName = nameInputManager.getName();
                State.SetState(refLink.GetGame().playState = new PlayState(refLink, false, ""));
            }
        }));
    }

    /*! \fn public void Update()
        \brief Actualizează starea curentă prin actualizarea gestionării inputului și UIManager-ului.
    */
    @Override
    public void Update() {
        refLink.GetGame().getAudioPlayer().Update(refLink.GetGame().getGameSettings());
        uiManager.Update();
        nameInputManager.Update();
    }

    /*! \fn public void Render(Graphics g)
        \brief Randează starea curentă, inclusiv caseta de input și numele curent.
        \param g Contextul grafic pentru randare.
    */
    @Override
    public void Render(Graphics g) {
        try {
            g.drawImage(Assets.menuBackground, -100, 0, 2000, 544, null);
        } catch (Exception m) {
            System.out.println("Exception occurred: " + m);
        } finally {
            g.setFont(new Font("Algerian", Font.PLAIN, 80));
            Text.drawString(g, "Enter Player Name", 1056 / 6, 100, Color.BLACK);

            /*! \brief Desenează caseta de text */
            g.setColor(Color.WHITE);
            g.fillRect(300, 200, 500, 50);
            g.setColor(Color.BLACK);
            g.drawRect(300, 200, 500, 50);

            /*! \brief Desenează numele curent */
            g.setFont(new Font("Arial", Font.PLAIN, 40));
            Text.drawString(g, nameInputManager.getName(), 310, 240, Color.BLACK);

            uiManager.Render(g);
        }
    }

    /*! \fn public String getPlayerName()
        \brief Returnează numele jucătorului introdus.
        \return Numele jucătorului
    */
    public String getPlayerName() {
        return playerName;
    }

    /*! \fn public String toString()
        \brief Suprascrie metoda toString pentru a returna numele jucătorului.
        \return Numele jucătorului ca șir de caractere
    */
    @Override
    public String toString() {
        return playerName;
    }
}
