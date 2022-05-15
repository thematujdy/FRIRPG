package sk.uniza.fri.game;

import sk.uniza.fri.engine.window.KeyManager;
import sk.uniza.fri.engine.window.MusicButton;
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

    private boolean paused;
    private int pauseCount;

    /***
     * prerob pomocou JLayeredPane
     * @param graphicTileSize
     * @param maxColTiles
     * @param maxRowTiles
     * @param keyListener
     * @param window
     */
    public GameFrame(int graphicTileSize, int maxColTiles, int maxRowTiles,
                     KeyManager keyListener, Window window) {
        this.setLayout(null);

        this.window = window;

        this.entities = new ArrayList<>();

        this.finalTileSize = graphicTileSize;

        int frameWidth = this.finalTileSize * maxColTiles;
        int frameHeight = this.finalTileSize * maxRowTiles;

        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.keyListener = keyListener;
        this.addKeyListener(keyListener);

        this.setFocusable(true);
        this.paused = false;
        this.pauseCount = 0;
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
        this.paused = false;
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
                this.keyListening();
                if (!this.paused) {
                    this.update();
                }
                this.repaint();
                delta--;
                count++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + count);
                count = 0;
                timer = 0;
            }


        }

    }

    public void update () {
        for (IEntity entity : this.entities) {
            if (entity instanceof IUpdatable updatable) {
                updatable.update();
            }
        }
    }

    public void keyListening() {
        if (this.keyListener.isExit()) {
            if (this.pauseCount == 0) {
                this.pauseCount = 100;
                Thread pause = new Thread(() -> {
                    this.paused = !this.paused;
                    while (this.pauseCount != 0) {
                        try {
                            Thread.sleep(1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.pauseCount--;
                    }
                });
                pause.start();
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

        if (this.paused) {
            Frame f = new Frame(584, 680 / 2, 360, 20, "PAUSED");
            f.paintComponent(graphics2D);
            /*
            MusicButton musicButton = new MusicButton(this.window, this.window.getMusicPlayer(), this.window.getConfig());
            musicButton.setBounds(0, 0, 50, 50);
            this.add(musicButton.getMusicButton());
             */
        }

        graphics2D.dispose();
    }
}
