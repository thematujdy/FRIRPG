package sk.uniza.fri.game;

import sk.uniza.fri.engine.window.KeyListener;
import sk.uniza.fri.engine.window.Window;

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

    private final int finalTileSize;

    private int playerXPos;
    private int playerYPos;

    private Thread gameThread;

    private boolean isMoving;

    private final Window window;

    private boolean exit;

    public GameFrame(int graphicTileSize, int multiplicator, int maxColTiles, int maxRowTiles, Window window) {
        this.playerXPos = 0;
        this.playerYPos = 0;

        this.finalTileSize = graphicTileSize * multiplicator;

        final int frameWidth = this.finalTileSize * maxColTiles;
        final int frameHeight = this.finalTileSize * maxRowTiles;

        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.window = window;
        this.isMoving = false;
        this.exit = false;
    }

    public void setCustomStartingCords (int startingPlayerX, int startingPlayerY) {
        this.playerYPos = startingPlayerY;
        this.playerXPos = startingPlayerX;
    }

    public void moveToTile (int colTile, int rowTile) {
        try {
            this.playerXPos = (rowTile * this.finalTileSize) - this.finalTileSize;
            this.playerYPos = (colTile * this.finalTileSize) - this.finalTileSize;
        } catch (Exception e) {
            System.out.println("Invalid starting cords by titles! " + e);
        }
    }

    public void startGame () {
        this.exit = false;
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    public void stop () {
        this.exit = true;
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

        while (this.gameThread != null && !this.exit) {
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

        //keylistener zmenit na keybinding

        if (this.window.getKeyListener().isUp() && !this.isMoving) {
            this.movePlayer("up");
        } else if (this.window.getKeyListener().isDown() && !this.isMoving) {
            this.movePlayer("down");
        } else if (this.window.getKeyListener().isLeft() && !this.isMoving) {
            this.movePlayer("left");
        } else if (this.window.getKeyListener().isRight() && !this.isMoving) {
            this.movePlayer("right");
        }

        if (this.window.getKeyListener().isExit()) {
            this.window.closeGameFrame();
        }
    }

    public void paintComponent (Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(this.playerYPos, this.playerXPos, this.finalTileSize, this.finalTileSize);
        graphics2D.dispose();

    }

    private void movePlayer (String where) {
        int milisecondsToWait = 200;

        Thread waitUp = new Thread(() -> {
            this.isMoving = true;
            int x = 0;
            while (x < this.finalTileSize) {
                try {
                    Thread.sleep(milisecondsToWait / this.finalTileSize);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                switch (where) {
                    case "up" -> this.playerXPos--;
                    case "down" -> this.playerXPos++;
                    case "left" -> this.playerYPos--;
                    case "right" -> this.playerYPos++;
                }

                x++;
            }
            this.isMoving = false;
        });

        waitUp.start();
    }
}
