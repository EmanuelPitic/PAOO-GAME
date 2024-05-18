package PaooGame.States;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.Input.NameInputManager;
import PaooGame.RefLinks;
import PaooGame.UI.ClickListener;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;
import PaooGame.UI.UIObject;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * StateSetPlayerName handles the state where the player can input their name.
 */
public class StateSetPlayerName extends State {
   // private NameInputManager nameInputManager; // Manages keyboard input for player name
    private String playerName; // Stores the current player name
    private NameInputManager nameInputManager; // Manages keyboard input for player name


    /**
     * Constructor initializes the state and input manager.
     * @param refLink Reference to shortcut object with useful references
     */
    public StateSetPlayerName(RefLinks refLink) {
        super(refLink);
        nameInputManager = refLink.GetGame().nameInputManager;
        playerName = "";

        uiManager=new UIManager(refLink);
        refLink.GetMouseManager().setUIManager(uiManager);

        // Button to confirm the entered name
        uiManager.addObject(new UIImageButton(450, 400, 192, 64, Assets.ok_btn, new ClickListener() {
            @Override
            public void onClick() {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().getUiManager());
                playerName = nameInputManager.getName();
                //System.out.println(nameInputManager.getName());
                // Proceed to next state, for example, the game state
                //refLink.GetMouseManager().setUIManager(null);
                State.SetState(refLink.GetGame().playState = new PlayState(refLink, false, ""));
            }
        }));

    }

    /**
     * Updates the state by updating the name input manager and UI manager.
     */
    @Override
    public void Update() {

        audioPlayer.Update(gameSettings);
        uiManager.Update();
        nameInputManager.Update();
    }

    /**
     * Renders the state including the input box and current name.
     * @param g Graphics context for rendering
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

            // Draw the text box
            g.setColor(Color.WHITE);
            g.fillRect(300, 200, 500, 50);
            g.setColor(Color.BLACK);
            g.drawRect(300, 200, 500, 50);

            // Draw the current name
            g.setFont(new Font("Arial", Font.PLAIN, 40));
           Text.drawString(g, nameInputManager.getName(), 310, 240, Color.BLACK);

            uiManager.Render(g);
        }
    }

    /**
     * Gets the player name entered.
     * @return Player name
     */
    public String getPlayerName() {
        return playerName;
    }
    @Override
    public String toString()
    {
        return playerName;
    }

}
