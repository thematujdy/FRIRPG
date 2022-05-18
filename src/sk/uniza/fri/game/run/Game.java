package sk.uniza.fri.game.run;

import sk.uniza.fri.engine.window.KeyManager;
import sk.uniza.fri.engine.window.Window;
import sk.uniza.fri.game.Frame;
import sk.uniza.fri.game.IUpdatable;
import sk.uniza.fri.game.entities.IEntity;
import sk.uniza.fri.game.world.Map;
import sk.uniza.fri.game.world.Room;
import sk.uniza.fri.game.world.tile.ITile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Game implements Runnable {

    private final JPanel gamePanel;
    private final JLayeredPane layeredPane;
    private final ArrayList<IEntity> entities;
    private final int tileSize;
    private final KeyManager keyListener;
    private int pauseCount;
    private boolean paused;
    private Thread gameThread;
    private Graphics g;
    private JLabel pauseLabel;
    private final Map map;

    public Game(int graphicTileSize, int maxColTiles, int maxRowTiles,
                KeyManager keyListener, Window window) {
        this.gamePanel = new JPanel(null);
        FRICanvas canvas = new FRICanvas(0, 0, graphicTileSize * maxRowTiles,
                graphicTileSize * maxColTiles);
        this.entities = new ArrayList<>();
        this.tileSize = graphicTileSize;
        this.layeredPane = new JLayeredPane();
        this.keyListener = keyListener;
        this.pauseCount = 0;
        this.paused = false;
        this.map = new Map(graphicTileSize);

        canvas.setFocusable(false);

        canvas.setBackground(Color.BLACK);

        this.gamePanel.setPreferredSize(new Dimension(maxColTiles * graphicTileSize,
                maxRowTiles * graphicTileSize));
        this.layeredPane.setSize(this.gamePanel.getPreferredSize());
        canvas.setSize(this.gamePanel.getPreferredSize());

        this.gamePanel.add(this.layeredPane);
        this.layeredPane.add(canvas);
        this.layeredPane.setLayer(canvas, 0);

        this.map.addRoom(maxRowTiles, maxColTiles);
        canvas.setTiles(this.map.getRoom(0));
    }

    public int getTileSize() {
        return this.tileSize;
    }

    public JPanel getGamePanel() {
        return this.gamePanel;
    }

    public JLayeredPane getLayeredPane() {
        return this.layeredPane;
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
                if (!this.paused) {
                    this.generatePauseMenu();
                    this.layeredPane.add(this.pauseLabel);
                    this.layeredPane.setLayer(this.pauseLabel, 1);
                } else {
                    this.layeredPane.remove(this.pauseLabel);
                }
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

    public void generatePauseMenu() {
        Frame f = new Frame(580, 300, 0, 0, "PAUSED");
        this.pauseLabel = new JLabel(new ImageIcon(f.getFrame()));
        this.pauseLabel.setBounds(20, 20, 300, 580);
    }

    public static class FRICanvas extends Canvas{

        private final ArrayList<ITile> tiles;

        public FRICanvas(int x, int y, int height, int width) {
            this.setBackground(Color.BLACK);
            this.setFocusable(false);
            this.setBounds(x, y, width, height);
            this.tiles = new ArrayList<>();
        }

        public void setTiles(Room room) {
            this.tiles.clear();
            for (ITile[] tileArr:room.getLayout()) {
                for (ITile tile:tileArr) {
                    this.tiles.add(tile);
                }

            }
            this.repaint();
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2 = (Graphics2D)g;
            for (ITile tile:this.tiles) {
                if (tile != null) {
                    g2.drawImage(tile.getImage(), tile.getX(), tile.getY(), null);
                }
            }
        }
    }
}
