package PaooGame.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;

/*! \public class KeyManager implements KeyListener
    \brief Gestioneaza intrarea (input-ul) de tastatura.
    Clasa citeste daca au fost apasata o tasta, stabiliteste ce tasta a fost actionata si adauga la string asta
 */
public class NameInputManager implements KeyListener {
    private StringBuilder currentName;
    private boolean[] keys;
    private Queue<Character> inputQueue;

    public NameInputManager() {
        currentName = new StringBuilder();
        keys = new boolean[256]; // Array to keep track of key states
        inputQueue = new LinkedList<>();
    }


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
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode < 0 || keyCode >= keys.length) {
            return;
        }
        keys[keyCode] = true;
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

    public String getName() {
        return currentName.toString();
    }
}
