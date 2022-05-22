package sk.uniza.fri.game.run;

import sk.uniza.fri.engine.window.KeyManager;
import sk.uniza.fri.engine.window.MusicButton;
import sk.uniza.fri.engine.window.Window;
import sk.uniza.fri.game.Frame;
import sk.uniza.fri.game.IUpdatable;
import sk.uniza.fri.game.entities.player.Player;
import sk.uniza.fri.game.items.IItem;
import sk.uniza.fri.game.world.Room;
import sk.uniza.fri.game.world.RoomGenerator;
import sk.uniza.fri.game.world.tile.ITile;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Game implements Runnable {

    private final JPanel gamePanel;
    private final JLayeredPane layeredPane;
    private final ArrayList<IUpdatable> updatables;
    private final int tileSize;
    private final KeyManager keyListener;
    private final Window window;
    private int pauseCount;
    private boolean paused;
    private Thread gameThread;
    private Graphics g;
    private final ArrayList<JComponent> pausePanel;
    private final ArrayList<JComponent> inventoryComponents;
    private Player player;
    private FRICanvas canvas;
    private JList<IItem> inventoryPane;
    private DefaultListModel<IItem> inventory;
    private Room currRoom;
    private RoomGenerator roomGenerator;

    public Game(int graphicTileSize, int maxColTiles, int maxRowTiles,
                KeyManager keyListener, Window window) {
        this.gamePanel = new JPanel(null);
        this.canvas = new FRICanvas(0, 0, graphicTileSize * maxRowTiles,
                graphicTileSize * maxColTiles);
        this.updatables = new ArrayList<>();
        this.tileSize = graphicTileSize;
        this.layeredPane = new JLayeredPane();
        this.keyListener = keyListener;
        this.pauseCount = 0;
        this.paused = false;
        this.window = window;
        this.pausePanel = new ArrayList<>();
        this.genereatePausePanel();
        this.gamePanel.addKeyListener(this.keyListener);
        this.inventoryComponents = new ArrayList<>();
        this.roomGenerator = new RoomGenerator();

        this.canvas.setFocusable(false);

        this.canvas.setBackground(Color.BLACK);

        this.gamePanel.setPreferredSize(new Dimension(maxColTiles * graphicTileSize,
                maxRowTiles * graphicTileSize));
        this.layeredPane.setSize(this.gamePanel.getPreferredSize());
        this.canvas.setSize(this.gamePanel.getPreferredSize());

        this.gamePanel.add(this.layeredPane);
        this.layeredPane.add(this.canvas);
        this.layeredPane.setLayer(this.canvas, 0);


        //mapa
        this.currRoom = this.roomGenerator.generateRoom(13, 15, 48, this);
        this.canvas.setTiles(this.currRoom);
        this.player = new Player(this, this.keyListener);
        this.updatables.add(this.player);
        this.inventory = this.player.getInventory();
        this.repaintLabels();

        this.gamePanel.grabFocus();
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

    public void endGame() {
        this.gameThread.stop();
        this.gameThread = null;
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
        for (IUpdatable entity : this.updatables) {
            if (entity != null) {
                entity.update();
                entity.repaint();
            }
        }
    }

    public void keyListening() {

        if (this.keyListener.isExit()) {
            if (this.pauseCount == 0) {
                this.pauseCount = 100;
                if (!this.paused) {
                    this.genereatePausePanel();
                    for (int i = 0; i < this.pausePanel.size() - 1; i++) {
                        this.layeredPane.add(this.pausePanel.get(i));
                        this.layeredPane.setLayer(this.pausePanel.get(i), 11);
                    }
                    this.layeredPane.setLayer(this.pausePanel.get(0), 10);
                } else {
                    if (this.inventoryPane != null) {
                        this.layeredPane.remove(this.inventoryPane);
                    }
                    for (JComponent component : this.inventoryComponents) {
                        this.layeredPane.remove(component);
                    }
                    this.inventoryComponents.clear();
                    for (int i = 0; i < this.pausePanel.size() - 1; i++) {
                        this.layeredPane.remove(this.pausePanel.get(i));
                    }
                    this.canvas.repaint();
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

    public Room getCurrentRoom() {
        return this.currRoom;
    }

    public RoomGenerator getRoomGenerator() {
        return this.roomGenerator;
    }

    public void setCurrRoom(Room room) {
        this.currRoom = room;
        this.canvas.setTiles(room);
        this.canvas.repaint();
    }

    public void setPlayerCords(int x, int y) {
        this.player.setX(x);
        this.player.setY(y);
    }

    public void genereatePausePanel() {
        Frame f = new Frame(580, 300, 0, 0, "PAUSED");
        JLabel label = new JLabel(new ImageIcon(f.getFrame()));
        label.setBounds(20, 20, 300, 580);
        this.pausePanel.add(label);

        MusicButton musicButton = new MusicButton(this.window, this.window.getMusicPlayer(), this.window.getConfig());
        musicButton.setBounds(20, 20, 50, 50);
        this.pausePanel.add(musicButton.getMusicButton());

        JButton menuButton = new JButton("MENU");
        menuButton.setBackground(Color.WHITE);
        menuButton.setBounds(115, 120, 120, 50);
        menuButton.setFocusable(false);
        menuButton.addActionListener(a -> {
            this.endGame();
            this.window.goToMenu();
        });
        this.pausePanel.add(menuButton);

        JButton closeInvButton = new JButton("X");
        closeInvButton.setFocusable(false);
        closeInvButton.setBounds(520, 20, 50, 50);
        closeInvButton.setBackground(Color.RED);
        closeInvButton.addActionListener(a -> {
            this.layeredPane.remove(this.inventoryPane);
            this.gamePanel.grabFocus();
            this.layeredPane.remove(closeInvButton);
            this.inventoryComponents.remove(closeInvButton);
            this.canvas.repaint();
        });

        JButton inventoryButton = new JButton("INVENTORY");
        inventoryButton.setBackground(Color.WHITE);
        inventoryButton.setBounds(115, 170, 120, 50);
        inventoryButton.setFocusable(false);
        inventoryButton.addActionListener(a -> {
            this.inventoryPane = new JList<>();
            this.inventoryPane.setBounds(20, 20, 500, 350);
            this.inventoryPane.setModel(this.inventory);
            this.layeredPane.add(this.inventoryPane);
            this.layeredPane.setLayer(this.inventoryPane, 20);
            this.inventoryComponents.add(closeInvButton);
            this.layeredPane.add(closeInvButton);
            this.layeredPane.setLayer(closeInvButton, 20);
        });
        this.pausePanel.add(inventoryButton);
    }

    public void repaintLabels() {
        for (IUpdatable updatable : this.updatables) {
            if (updatable != null) {
                updatable.piantLabel(this.layeredPane);
            }
        }
    }

    public static class FRICanvas extends JPanel {

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
                this.tiles.addAll(Arrays.asList(tileArr));

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
