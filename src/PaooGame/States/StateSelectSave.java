package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Text;
import PaooGame.Level.Level;
import PaooGame.RefLinks;
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

        // Add UI buttons for each saved game
        int yOffset = 200;
        for (String saveName : saveNames) {
            uiManager.addObject(new UIImageButton(450, yOffset, 192, 64, Assets.ok_btn, new ClickListener() {
                @Override
                public void onClick() {
                    //.getInstance().setLevel(0);
                    //Level.getInstance().setChangeLevel(true);
                    refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().getUiManager());
                    if(saveName.equals("2"))
                    {
                        //  Level.getInstance().setLevel(1);
                        State.SetState(refLink.GetGame().playState = new PlayState(refLink, true, "/1/3/57/24.0/336.0/30/false/true/false/true/608.0/364.0/114/false/3/"));
                    }

                    else if(saveName.equals("3"))
                    {
                        // Level.getInstance().setLevel(3);
                        State.SetState(refLink.GetGame().playState = new PlayState(refLink, true, "asd/3/38/75/24.0/373.0/30/false/true/true/false/true/true/true/384.0/32.0/23/true/3/328.0/32.0/95/true/3/224.0/460.0/1/false/1/160.0/52.0/1/false/3/96.0/460.0/1/false/1/64.0/52.0/1/false/3/640.0/408.0/47/true/1/784.0/180.0/209/false/2/992.0/88.0/65/false/1/480.0/480.0/281/false/1/576.0/296.0/33/false/3/"));

                    }
                    else {
                        State.SetState(refLink.GetGame().playState = new PlayState(refLink, true, "/1/17/43/724.0/416.0/20/true/false/false/true/608.0/92.0/17/true/3/"));
                    }
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
