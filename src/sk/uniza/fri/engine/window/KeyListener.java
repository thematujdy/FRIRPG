package sk.uniza.fri.engine.window;

import java.awt.event.KeyEvent;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class KeyListener implements java.awt.event.KeyListener {

    boolean up, down, left, right, a, b, exit;

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
            this.up = true;
        }

        if (key == KeyEvent.VK_DOWN) {
            System.out.println("down");
            this.down = true;
        }

        if (key == KeyEvent.VK_LEFT) {
            System.out.println("left");
            this.left = true;
        }

        if (key == KeyEvent.VK_RIGHT) {
            System.out.println("right");
            this.right =true;
        }

        if (key == KeyEvent.VK_Y) {
            System.out.println("A");
            this.a = true;
        }

        if (key == KeyEvent.VK_X) {
            System.out.println("B");
            this.b = true;
        }

        if (key == KeyEvent.VK_Q) {
            System.out.println("Exit");
            this.exit = true;
        }
    }

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
            this.right =false;
        }

        if (key == KeyEvent.VK_Y) {
            this.a = false;
        }

        if (key == KeyEvent.VK_X) {
            this.b = false;
        }

        if (key == KeyEvent.VK_Q) {
            this.exit = false;
        }
    }
}
