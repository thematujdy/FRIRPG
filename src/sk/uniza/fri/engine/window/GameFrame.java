package sk.uniza.fri.engine.window;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class GameFrame extends JPanel implements Runnable {

    Thread gameThread;

    public GameFrame(int graphicTitleSize, int multiplicator, int maxColTitles, int maxRowTitles) {
        final int finalTitleSize = graphicTitleSize * multiplicator;

        final int frameWidth = finalTitleSize * maxColTitles;
        final int frameHeight = finalTitleSize * maxRowTitles;

        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGame () {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    @Override
    public void run() {

        while (this.gameThread != null) {
            System.out.println("Running");
        }

    }
}
