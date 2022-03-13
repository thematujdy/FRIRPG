package sk.uniza.fri.engine.window;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Canvas;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Window {

    private String name;
    private final JFrame win;
    private Canvas canvas;

    public Window (String name, int width, int height, String imgPath) {
        this.name = name;
        this.win = new JFrame(this.name);
        this.win.setSize(width, height);
        this.setIcon(imgPath);
        this.win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.canvas = null;
        this.win.setLocationRelativeTo(null);
    }

    public Window (String name, int width, int height) {
        this.name = name;
        this.win = new JFrame(this.name);
        this.win.setSize(width, height);
        this.win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.canvas = null;
        this.win.setLocationRelativeTo(null);
    }

    public Window (int width, int height, String imgPath) {
        this.name = "Window";
        this.win = new JFrame(this.name);
        this.win.setSize(width, height);
        this.setIcon(imgPath);
        this.win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.canvas = null;
        this.win.setLocationRelativeTo(null);
    }

    public Window (int width, int height) {
        this.name = "Window";
        this.win = new JFrame(this.name);
        this.win.setSize(width, height);
        this.win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.canvas = null;
        this.win.setLocationRelativeTo(null);
    }

    public Window (String name, String imgPath) {
        this.name = name;
        this.win = new JFrame(this.name);
        this.setIcon(imgPath);
        this.setSize(500, 500);
        this.win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.canvas = null;
        this.win.setLocationRelativeTo(null);
    }

    public Window (String name) {
        this.name = name;
        this.win = new JFrame(this.name);
        this.setSize(500, 500);
        this.win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.canvas = null;
        this.win.setLocationRelativeTo(null);
    }

    public Window () {
        this.name = "Window";
        this.win = new JFrame(this.name);
        this.setSize(500, 500);
        this.win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.canvas = null;
        this.win.setLocationRelativeTo(null);
    }

    public void setSize (int width, int height) {
        this.win.setSize(width, height);
    }

    public void setName (String name) {
        this.name = name;
        this.win.setTitle(name);
    }

    public JFrame getWindow () {
        return this.win;
    }

    public void setIcon (String path) {
        ImageIcon icon = new ImageIcon(path);
        this.win.setIconImage(icon.getImage());
    }

    public void setVisible (boolean visible) {
        this.win.setVisible(visible);
    }

    public void addCanvas () {
        this.canvas = new Canvas();
        this.win.add(this.canvas);
    }

    public void makeUnresizable () {
        this.win.setResizable(false);
    }

    public void addKeyListener (KeyListener keyListener) {
        this.win.addKeyListener(keyListener);
    }

    public Canvas getCanvas () {
        return this.canvas;
    }
}
