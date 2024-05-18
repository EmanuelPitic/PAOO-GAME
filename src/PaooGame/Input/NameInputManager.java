package PaooGame.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Manages keyboard input for the player's name.
 */
public class NameInputManager implements KeyListener {
    private StringBuilder currentName;
    private boolean[] keys;
    private Queue<Character> inputQueue;

    /**
     * Constructor initializes the current name, key states, and input queue.
     */
    public NameInputManager() {
        currentName = new StringBuilder();
        keys = new boolean[256]; // Array to keep track of key states
        inputQueue = new LinkedList<>();
    }

    /**
     * Updates the input state and processes the queued inputs.
     */
    public void Update() {
        //System.out.println("Input Name: " + currentName.toString()+' '+getName());
        while (!inputQueue.isEmpty()) {
            char keyChar = inputQueue.poll();
            if (Character.isLetterOrDigit(keyChar) || Character.isSpaceChar(keyChar)) {
                currentName.append(keyChar);
            } else if (keyChar == '\b' && currentName.length() > 0) {
                // Handle backspace
                currentName.deleteCharAt(currentName.length() - 1);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No implementation needed for keyTyped
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode < 0 || keyCode >= keys.length) {
            return;
        }
        keys[keyCode] = true;

        // Queue the character for processing in the update method
        char keyChar = e.getKeyChar();
        inputQueue.offer(keyChar);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode < 0 || keyCode >= keys.length) {
            return;
        }
        keys[keyCode] = false;
    }

    /**
     * Gets the current player name.
     * @return The current player name as a string.
     */
    public String getName() {
        return currentName.toString();
    }
}
