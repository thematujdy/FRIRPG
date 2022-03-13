package sk.uniza.fri.engine.window;

import javax.swing.JPanel;
import java.awt.*;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class GameFrame extends JPanel implements Runnable {

    private final int graphicTileSize;
    private final int finalTileSize;
    private int maxColTiles;
    private int maxRowTiles;

    private int playerXPos;
    private int playerYPos;

    int FPS = 60;

    KeyListener keyListener = new sk.uniza.fri.engine.window.KeyListener();
    Thread gameThread;

    public GameFrame(int graphicTileSize, int multiplicator, int maxColTiles,
                     int maxRowTiles, int startingPlayerX, int startingPlayerY) {
        this.playerXPos = startingPlayerX;
        this.playerYPos = startingPlayerY;

        this.maxRowTiles = maxRowTiles;
        this.maxColTiles = maxColTiles;

        this.graphicTileSize = graphicTileSize;

        this.finalTileSize = graphicTileSize * multiplicator;

        final int frameWidth = this.finalTileSize * this.maxColTiles;
        final int frameHeight = this.finalTileSize * this.maxRowTiles;

        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.keyListener);
        this.setFocusable(true);
    }

    public GameFrame(int graphicTileSize, int multiplicator, int maxColTiles, int maxRowTiles) {
        this.playerXPos = 100;
        this.playerYPos = 100;

        this.graphicTileSize = graphicTileSize;

        this.finalTileSize = graphicTileSize * multiplicator;

        final int frameWidth = this.finalTileSize * maxColTiles;
        final int frameHeight = this.finalTileSize * maxRowTiles;

        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.keyListener);
        this.setFocusable(true);
    }

    public void setCustomStartingCords (int startingPlayerX, int startingPlayerY) {
        this.playerYPos = startingPlayerY;
        this.playerXPos = startingPlayerX;
    }

    public void setTileStartingCords (int colTile, int rowTile) {
        try {
            this.playerXPos = (rowTile * this.finalTileSize) - this.finalTileSize;
            this.playerYPos = (colTile * this.finalTileSize) - this.finalTileSize;
        } catch (Exception e) {
            System.out.println("Invalid starting cords by titles! " + e);
        }
    }

    public void startGame () {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    @Override
    public void run() {

        double interval = 1000000000/(double)this.FPS;
        double delta = 0;
        long prevTime = System.nanoTime();
        long curTime;

        while (this.gameThread != null) {

            System.out.println("Game is running");

            curTime = System.nanoTime();
            delta += (curTime - prevTime) / interval;
            prevTime = curTime;

            if (delta >= 1) {
                this.update();
                this.repaint();
                delta--;
            }
        }

    }

    public void update () {
        if (this.keyListener.up) {
            this.playerXPos -= this.finalTileSize;
        } else if (this.keyListener.down) {
            this.playerXPos += this.finalTileSize;
        } else if (this.keyListener.left) {
            this.playerYPos -= this.finalTileSize;
        } else if (this.keyListener.right) {
            this.playerYPos += this.finalTileSize;
        }
    }

    public void paintComponent (Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(this.playerYPos, this.playerXPos, this.graphicTileSize, this.graphicTileSize);
        graphics2D.dispose();

    }
}
