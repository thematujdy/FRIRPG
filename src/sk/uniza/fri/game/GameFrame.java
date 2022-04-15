package sk.uniza.fri.game;

import sk.uniza.fri.engine.window.KeyManager;
import sk.uniza.fri.engine.window.Window;
import sk.uniza.fri.game.entities.IEntity;
import sk.uniza.fri.game.entities.player.Player;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class GameFrame extends JPanel implements Runnable {

    private final int finalTileSize;

    private Thread gameThread;

    private final KeyManager keyListener;

    private final Window window;

    private ArrayList<IEntity> entities;

    public GameFrame(int graphicTileSize, int multiplicator, int maxColTiles, int maxRowTiles,
                     KeyManager keyListener, Window window) {
        this.window = window;

        this.entities = new ArrayList<>();

        this.finalTileSize = graphicTileSize * multiplicator;

        int frameWidth = this.finalTileSize * maxColTiles;
        int frameHeight = this.finalTileSize * maxRowTiles;

        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.keyListener = keyListener;
        this.addKeyListener(keyListener);

        this.setFocusable(true);

    }

    public void newGame () {
        this.entities.add(new Player(this, this.keyListener));
    }

    public void loadGame () {

    }

    public int getFinalTileSize () {
        return this.finalTileSize;
    }

    public int getTileX (int rowTile) {
        return (rowTile * this.finalTileSize) - this.finalTileSize;
    }

    public int getTileY (int colTile) {
        return (colTile * this.finalTileSize) - this.finalTileSize;
    }

    private void stopGame () {
        this.gameThread = null;
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
        if (this.keyListener.isExit()) {
            this.window.goToMenu();
            this.stopGame();
        }
        for (IEntity entity : this.entities) {
            if (entity instanceof IUpdatable updatable) {
                updatable.update();
            }
        }
    }

    public void paintComponent (Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setColor(Color.WHITE);

        for (IEntity entity : this.entities) {
            if (entity instanceof IUpdatable updatable) {
                updatable.paintComponent(graphics2D);
            }
        }

        graphics2D.dispose();
    }
}
