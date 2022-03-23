package sk.uniza.fri.engine.window;

import java.awt.event.KeyEvent;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class KeyListener implements java.awt.event.KeyListener {

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean a;
    private boolean b;
    private boolean exit;

    public KeyListener() {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

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

        if (key == KeyEvent.VK_X) {
            this.b = true;
        }

        if (key == KeyEvent.VK_Q) {
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
            this.right = false;
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


    public boolean isUp() {
        return this.up;
    }

    public boolean isDown() {
        return this.down;
    }

    public boolean isLeft() {
        return this.left;
    }

    public boolean isRight() {
        return this.right;
    }

    public boolean isA() {
        return this.a;
    }

    public boolean isB() {
        return this.b;
    }

    public boolean isExit() {
        return this.exit;
    }
}
