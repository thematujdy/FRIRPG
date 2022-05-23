package sk.uniza.fri.engine.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Trieda KeyManager, ide o Listener klaves nasteaveny pre moju hru
 */
public class KeyManager implements KeyListener {

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean a;
    private boolean exit;

    /**
     * Tuto implementovanu metodu nevyuzivam
     * @param e KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     *  Metoda keyPressed(KeyEvent e) slzii na zachytenie toho ci bola stlacena klavesa
     * @param e KeyEvent
     */
    @Override
    public void keyPressed (KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            this.up = true;
        }

        if (key == KeyEvent.VK_DOWN) {
            this.down = true;
        }

        if (key == KeyEvent.VK_LEFT) {
            this.left = true;
        }

        if (key == KeyEvent.VK_RIGHT) {
            this.right = true;
        }

        if (key == KeyEvent.VK_Y) {
            this.a = true;
        }

        if (key == KeyEvent.VK_Q) {
            this.exit = true;
        }
    }

    /**
     *  Metoda keyReleased(KeyEvent e) sluzi na zachytenie toho ci bola pustena klavesa
     * @param e KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            this.up = false;
        }

        if (key == KeyEvent.VK_DOWN) {
            this.down = false;
        }

        if (key == KeyEvent.VK_LEFT) {
            this.left = false;
        }

        if (key == KeyEvent.VK_RIGHT) {
            this.right = false;
        }

        if (key == KeyEvent.VK_Y) {
            this.a = false;
        }

        if (key == KeyEvent.VK_Q) {
            this.exit = false;
        }
    }


    /**
     *  metoda isUp() sluzi na navratenie booleanu ci je stlacena sipka hore
     * @return up
     */
    public boolean isUp() {
        return this.up;
    }

    /**
     *  metoda isDown() sluzi na navratenie booleanu ci je stlacena sipka dole
     * @return down
     */
    public boolean isDown() {
        return this.down;
    }

    /**
     *  metoda isLeft() sluzi na navratenie booleanu ci je stlacena sipka vlavo
     * @return left
     */
    public boolean isLeft() {
        return this.left;
    }

    /**
     *  metoda isRight() sluzi na navratenie booleanu ci je stlacena sipka vpravo
     * @return right
     */
    public boolean isRight() {
        return this.right;
    }

    /**
     *  metoda isA() sluzi na navratenie booleanu ci je stlacena klavesa A
     * @return a
     */
    public boolean isA() {
        return this.a;
    }

    /**
     *  metoda isExit() sluzi na navratenie booleanu ci je stlacena klavesa Q
     * @return exit
     */
    public boolean isExit() {
        return this.exit;
    }
}
