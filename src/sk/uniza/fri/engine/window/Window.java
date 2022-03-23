package sk.uniza.fri.engine.window;

import sk.uniza.fri.engine.menu.Menu;
import sk.uniza.fri.game.GameFrame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Window {

    private final int graphicTitleSize;
    private final int multiplicator;
    private final int maxColTitles;
    private final int maxRowTitles;
    private final JFrame win;
    private GameFrame gameFrame;
    private Menu menu;

    public Window (String name, String imgPath, int graphicTitleSize, int multiplicator, int maxColTitles, int maxRowTitles) {
        this.graphicTitleSize = graphicTitleSize;
        this.multiplicator = multiplicator;
        this.maxColTitles = maxColTitles;
        this.maxRowTitles = maxRowTitles;
        this.win = new JFrame(name);
        this.setIcon(imgPath);
        this.setSize(500, 500);
        this.win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.win.setLocationRelativeTo(null);
    }

    public void setSize (int width, int height) {
        this.win.setSize(width, height);
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
        if (this.menu != null) {
            this.quitMenu();
        }
        this.gameFrame = new GameFrame(this.graphicTitleSize, this.multiplicator, this.maxColTitles,
                this.maxRowTitles, this);
        this.win.add(this.gameFrame);
        this.win.pack();
        this.gameFrame.startGame();
    }

    public void closeGameFrame () {
        this.gameFrame.stop();
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
