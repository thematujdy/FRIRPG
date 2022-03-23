package sk.uniza.fri.engine.window;

import sk.uniza.fri.engine.menu.Menu;
import sk.uniza.fri.game.GameFrame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Canvas;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Window {

    private String name;
    private final int graphicTitleSize;
    private final int multiplicator;
    private final int maxColTitles;
    private final int maxRowTitles;
    private final JFrame win;
    private GameFrame gameFrame;
    private Canvas canvas;
    private Menu menu;

    public Window (String name, String imgPath, int graphicTitleSize, int multiplicator, int maxColTitles, int maxRowTitles) {
        this.name = name;
        this.graphicTitleSize = graphicTitleSize;
        this.multiplicator = multiplicator;
        this.maxColTitles = maxColTitles;
        this.maxRowTitles = maxRowTitles;
        this.win = new JFrame(this.name);
        this.setIcon(imgPath);
        this.setSize(500, 500);
        this.win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.win.setLocationRelativeTo(null);
    }

    public void setSize (int width, int height) {
        this.win.setSize(width, height);
    }

    public void setName (String name) {
        this.name = name;
        this.win.setTitle(name);
    }

    public void setIcon (String path) {
        ImageIcon icon = new ImageIcon(path);
        this.win.setIconImage(icon.getImage());
    }

    public void setVisible (boolean visible) {
        this.win.setVisible(visible);
    }

    public void makeUnresizable () {
        this.win.setResizable(false);
    }

    public void startGameFrame () {
        this.quitMenu();
        this.gameFrame = new GameFrame(this.graphicTitleSize, this.multiplicator, this.maxColTitles,
                this.maxRowTitles, this);
        this.win.add(this.gameFrame);
        this.win.pack();
    }


    //oddelit engine a game setteri
    public void startGameThread () {
        if (this.gameFrame != null) {
            this.gameFrame.startGame();
        } else {
            System.out.println("Game thread has not been initialized!");
        }
    }

    public void setTileStartingCords (int colTile, int rowTile) {
        if (this.gameFrame != null) {
            this.gameFrame.moveToTile(colTile, rowTile);
        } else {
            System.out.println("Game thread has not been initialized!");
        }
    }

    public void addCanvas () {
        this.canvas = new Canvas();
    }

    public Canvas getCanvas () {
        return this.canvas;
    }

    public void closeGameFrame () {
        this.win.getContentPane().remove(this.gameFrame);
        this.win.pack();
        this.goToMenu();
    }

    public void goToMenu () {
        this.menu = new Menu(this.graphicTitleSize, this.multiplicator, this.maxColTitles,
                this.maxRowTitles, this);
        this.win.add(this.menu);
        this.win.pack();
    }

    public void quitMenu () {
        this.win.getContentPane().remove(this.menu);
        this.win.pack();
    }
}
