package sk.uniza.fri.engine.window;

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
    private final JFrame window;
    private final GameFrame gameFrame;

    public Window (String name, String imgPath, int graphicTitleSize, int multiplicator, int maxColTitles, int maxRowTitles) {
        //Tile size
        this.graphicTitleSize = graphicTitleSize;
        this.multiplicator = multiplicator;
        this.maxColTitles = maxColTitles;
        this.maxRowTitles = maxRowTitles;
        //JFrame
        this.window = new JFrame(name);
        //JFrame icon
        ImageIcon icon = new ImageIcon(imgPath);
        this.window.setIconImage(icon.getImage());
        //JFrame settings
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setLocationRelativeTo(null);
        this.window.setVisible(true);
        this.window.setResizable(false);
        //JPanel
        this.gameFrame = new GameFrame(this.graphicTitleSize, this.multiplicator, this.maxColTitles, this.maxRowTitles);
        this.window.add(this.gameFrame);
        this.gameFrame.startGame();
        this.window.pack();
    }
}
