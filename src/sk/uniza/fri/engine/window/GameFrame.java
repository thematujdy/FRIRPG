package sk.uniza.fri.engine.window;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class GameFrame extends JPanel {

    private final int baseTitleSize = 48;
    private final int multiplicator = 1;
    private final int finalTitleSize = this.baseTitleSize * this.multiplicator;

    private final int maxColTitles = 16;
    private final int maxRowTitles = 12;

    private final int frameWidth = this.finalTitleSize * this.maxColTitles;
    private final int frameHeight = this.finalTitleSize * this.maxRowTitles;

    public GameFrame() {
        this.setPreferredSize(new Dimension(this.frameWidth, this.frameHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }
}
