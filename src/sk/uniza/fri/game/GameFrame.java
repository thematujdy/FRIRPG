package sk.uniza.fri.game;

import sk.uniza.fri.engine.window.KeyListener;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class GameFrame extends JPanel implements Runnable {

    private final int graphicTileSize;
    private final int finalTileSize;

    private int playerXPos;
    private int playerYPos;

    private final KeyListener keyListener = new sk.uniza.fri.engine.window.KeyListener();
    private Thread gameThread;

    public GameFrame(int graphicTileSize, int multiplicator, int maxColTiles,
                     int maxRowTiles, int startingPlayerX, int startingPlayerY) {
        this.playerXPos = startingPlayerX;
        this.playerYPos = startingPlayerY;

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

        int fps = 60;
        double interval = 1000000000 / (double)fps;
        double delta = 0;
        long prevTime = System.nanoTime();
        long curTime;
        long timer = 0;
        int count = 0;

        while (this.gameThread != null) {
            curTime = System.nanoTime();
            delta += (curTime - prevTime) / interval;
            timer += (curTime - prevTime);
            prevTime = curTime;

            if (delta >= 1) {
                this.update();
                this.repaint();
                delta--;
                count++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS:" + count);
                count = 0;
                timer = 0;
            }
        }

    }

    public void update () {
        if (this.keyListener.isUp()) {
            this.playerXPos -= this.finalTileSize;
        } else if (this.keyListener.isDown()) {
            this.playerXPos += this.finalTileSize;
        } else if (this.keyListener.isLeft()) {
            this.playerYPos -= this.finalTileSize;
        } else if (this.keyListener.isRight()) {
            this.playerYPos += this.finalTileSize;
        }
    }

    public void paintComponent (Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(this.playerYPos, this.playerXPos, this.graphicTileSize, this.graphicTileSize);
        graphics2D.dispose();

    }
}
