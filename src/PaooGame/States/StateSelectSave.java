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

/**
 * StateSelectSave handles the state where the player can select a saved game.
 */
public class StateSelectSave extends State {
    //  private UIManager uiManager; // Manages UI elements
    private List<String> saveNames; // List of saved game names

    /**
     * Constructor initializes the state and UI manager.
     * @param refLink Reference to shortcut object with useful references
     */
    public StateSelectSave(RefLinks refLink) {
        super(refLink);
        uiManager = new UIManager(refLink);
        refLink.GetMouseManager().setUIManager(uiManager);

        // Retrieve the list of saved game names (dummy data for this example)
        saveNames = new ArrayList<>();
        saveNames.add("1");
        saveNames.add("2");
        saveNames.add("3");
        saveNames.add("4");
        ArrayList<String> gameStates = SQL.getInstance().getLastGameStates();

        // Add UI buttons for each saved game
        int yOffset = 200;
        for (String gameState : gameStates) {
            uiManager.addObject(new UIImageButton(450, yOffset, 192, 64, Assets.ok_btn, new ClickListener() {
                @Override
                public void onClick() {
                    //.getInstance().setLevel(0);
                    //Level.getInstance().setChangeLevel(true);
                    refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().getUiManager());
                    //ArrayList<String> gameStates = SQL.getInstance().getLastGameStates();
                    State.SetState(refLink.GetGame().playState = new PlayState(refLink, true, gameState));
                    //System.out.println("Loaded save: " + saveName);
                    //Level.getInstance().setLevel(0);
                    //Level.getInstance().setChangeLevel(true);
                    refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().getUiManager());
                    //State.SetState(refLink.GetGame().playState = new PlayState(refLink, false, ""));
                    // Proceed to next state, for example, the game state
                    // State.SetState(refLink.GetGame().getPlayState());
                }
            }));
            yOffset += 80;
        }
    }

    /**
     * Updates the state by updating the UI manager.
     */
    @Override
    public void Update() {
        uiManager.Update();
    }

    /**
     * Renders the state including the list of saved games.
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
            Text.drawString(g, "Select a Save", 1056 / 6, 100, Color.BLACK);
            uiManager.Render(g);

            // Render save names
            g.setFont(new Font("Arial", Font.PLAIN, 40));
            int yOffset = 200;
            for (String saveName : saveNames) {
                Text.drawString(g, saveName, 310, yOffset + 40, Color.BLACK);
                yOffset += 80;
            }


        }
    }
}
