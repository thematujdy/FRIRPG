package sk.uniza.fri.engine.window;

import java.awt.event.KeyEvent;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class KeyListener implements java.awt.event.KeyListener {

    public KeyListener() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed (KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            System.out.println("up");
        }

        if (key == KeyEvent.VK_DOWN) {
            System.out.println("down");
        }

        if (key == KeyEvent.VK_LEFT) {
            System.out.println("left");
        }

        if (key == KeyEvent.VK_RIGHT) {
            System.out.println("right");
        }

        if (key == KeyEvent.VK_Y) {
            System.out.println("A");
        }

        if (key == KeyEvent.VK_X) {
            System.out.println("B");
        }

        if (key == KeyEvent.VK_Q) {
            System.out.println("Menu");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
