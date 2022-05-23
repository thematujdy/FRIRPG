package sk.uniza.fri.game.run;

import sk.uniza.fri.engine.window.KeyManager;
import sk.uniza.fri.engine.window.MusicButton;
import sk.uniza.fri.engine.window.Window;
import sk.uniza.fri.game.Frame;
import sk.uniza.fri.game.IUpdatable;
import sk.uniza.fri.game.entities.enemy.Enemy;
import sk.uniza.fri.game.entities.enemy.Zombie;
import sk.uniza.fri.game.entities.player.Player;
import sk.uniza.fri.game.items.IItem;
import sk.uniza.fri.game.items.ItemGenerator;
import sk.uniza.fri.game.items.StandardItem;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

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
    private final ArrayList<JComponent> pausePanel;
    private final ArrayList<JComponent> inventoryComponents;
    private final Player player;
    private final FRICanvas canvas;
    private JList<IItem> inventoryPane;
    private final DefaultListModel<IItem> inventory;
    private Room currRoom;
    private boolean isFighting;
    private final ArrayList<Enemy> enemies;

    /**
     * Konstruktor triedy Game, ide o hlavnu triedu hry kde sa deje skoro vsetko
     * @param graphicTileSize velkost policok
     * @param maxColTiles pocet stlpcov
     * @param maxRowTiles pocet riadkov
     * @param keyListener key listener
     * @param window hlavne okno
     */
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
        this.isFighting = false;
        this.enemies = new ArrayList<>();

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
        this.generateRoom();
        this.canvas.setTiles(this.currRoom);
        this.player = new Player(this, this.keyListener);
        this.updatables.add(this.player);
        this.inventory = this.player.getInventory();
        this.repaintLabels();

        this.gamePanel.grabFocus();
    }

    /**
     * Metoda startFight vytvory prostredie boja a stara sa o jeho priebeh
     * @param enemy Nepriatel s ktorym sa bojuje
     */
    public void startFight(Enemy enemy) {
        AtomicBoolean turn = new AtomicBoolean(true);
        this.isFighting = true;
        AtomicInteger defense = new AtomicInteger();

        for (int i = 0; i < this.player.getEquipementLength(); i++) {
            if (this.player.getEquipement(i) != null && ((StandardItem)this.player.getEquipement(i)).getArmor() != 0) {
                defense.addAndGet(((StandardItem)this.player.getEquipement(i)).getArmor());
            }
        }

        Frame f = new Frame(584, 680);
        JLabel frame = new JLabel(new ImageIcon(f.getFrame()));
        frame.setBounds(20, 20, 680, 584);
        frame.setFocusable(false);
        this.layeredPane.add(frame);
        this.layeredPane.setLayer(frame, 50);

        JButton defendButton = new JButton("DEFEND");
        defendButton.setBackground(Color.WHITE);
        defendButton.setFocusable(false);
        defendButton.setBounds(120, 554, 100, 50);
        defendButton.addActionListener(a -> {
            turn.set(false);
            defense.addAndGet(3);
        });
        this.layeredPane.add(defendButton);
        this.layeredPane.setLayer(defendButton, 51);

        JLabel enemyLabel = new JLabel(enemy.getLabel().getIcon());
        enemyLabel.setFocusable(false);
        enemyLabel.setBounds(400, 20, 48, 48);
        this.layeredPane.add(enemyLabel);
        this.layeredPane.setLayer(enemyLabel, 51);

        JLabel palyerLabel = new JLabel(this.player.getJLabel().getIcon());
        palyerLabel.setFocusable(false);
        palyerLabel.setBounds(100, 200, 48, 48);
        this.layeredPane.add(palyerLabel);
        this.layeredPane.setLayer(palyerLabel, 51);

        JLabel playerHP = new JLabel(String.valueOf(this.player.getHP()));
        playerHP.setFocusable(false);
        playerHP.setBounds(100, 248, 50, 50);
        playerHP.setForeground(Color.GREEN);
        this.layeredPane.add(playerHP);
        this.layeredPane.setLayer(playerHP, 51);

        JLabel enemyHP = new JLabel(String.valueOf(enemy.getHP()));
        enemyHP.setFocusable(false);
        enemyHP.setBounds(400, 68, 50, 50);
        enemyHP.setForeground(Color.RED);
        this.layeredPane.add(enemyHP);
        this.layeredPane.setLayer(enemyHP, 51);

        JButton attackButton = new JButton("ATTACK");
        attackButton.setBackground(Color.WHITE);
        attackButton.setFocusable(false);
        attackButton.setBounds(20, 554, 100, 50);
        attackButton.addActionListener(a -> {
            int add = 0;
            for (int i = 0; i < this.player.getEquipementLength(); i++) {
                if (this.player.getEquipement(i) != null && ((StandardItem)this.player.getEquipement(i)).getPower() != 0) {
                    add += ((StandardItem)this.player.getEquipement(i)).getPower();
                }
            }
            turn.set(false);
            enemy.addHP(-this.player.getPower() - add);
            enemyHP.setText(String.valueOf(enemy.getHP()));
        });
        this.layeredPane.add(attackButton);
        this.layeredPane.setLayer(attackButton, 51);

        while (this.player.getHP() > 0 && enemy.getHP() > 0) {
            if (!turn.get()) {
                if (defense.get() != 0) {
                    if (defense.get() - enemy.getPower() <= 0) {
                        this.player.addHP(defense.get() - enemy.getPower());
                    }
                    defense.addAndGet(-3);
                } else {
                    this.player.addHP(-enemy.getPower());
                }
                turn.set(true);
                playerHP.setText(String.valueOf(this.player.getHP()));
            }
        }
        if (enemy.getHP() <= 0) {
            this.layeredPane.remove(enemy.getLabel());
            this.layeredPane.remove(playerHP);
            this.layeredPane.remove(enemyHP);
            this.layeredPane.remove(enemyLabel);
            this.layeredPane.remove(palyerLabel);
            this.layeredPane.remove(frame);
            this.layeredPane.remove(attackButton);
            this.layeredPane.remove(defendButton);
            this.isFighting = false;
            this.enemies.remove(enemy);
            this.repaintCanvas();
            this.player.addItem(ItemGenerator.generateItem());
        } else {
            JOptionPane.showMessageDialog(frame,
                    "You Died.",
                    "FRIRPG",
                    JOptionPane.PLAIN_MESSAGE);
            this.endGame();
            this.window.goToMenu();
        }
        this.setPlayerCords(this.currRoom.calculateTileX(this.player.getRoomX()),
                this.currRoom.calculateTileY(this.player.getRoomY()));
    }

    /**
     * Metoda repaintCanvas prekresli JPanel "canvas" aby bol aktualny
     */
    public void repaintCanvas() {
        this.canvas.repaint();
    }

    /**
     * Metóda zavola RoomGenerator a po vytvoreni novej miestnosti ju nastavy ako aktualnu
     */
    public void generateRoom() {
        if (this.currRoom != null) {
            this.currRoom.clearItems(this);
        }
        this.currRoom = RoomGenerator.generateRoom(13, 15, 48, this);
        this.canvas.setTiles(this.currRoom);
        if (this.player != null) {
            this.setPlayerCords(this.currRoom.calculateTileX(1), this.currRoom.calculateTileY(1));
            this.player.setCurrentTile(1, 1);
        }
        for (ITile[] tileArr : this.currRoom.getLayout()) {
            for (ITile tile : tileArr) {
                if (tile.getItem() != null) {
                    JLabel item = ((IUpdatable)tile.getItem()).getJLabel();
                    System.out.println(item);
                    this.layeredPane.add(item);
                    this.layeredPane.setLayer(item, 2);
                    System.out.println(tile.getItem());
                }
            }
        }
        Zombie zombie = new Zombie(this);
        zombie.setLocation(2, 1);
        zombie.piantLabel(this.layeredPane);
        this.enemies.add(zombie);
        this.repaintLabels();
    }

    /**
     * Metoda getTileSize navrati velkost policka
     * @return velkosť policka
     */
    public int getTileSize() {
        return this.tileSize;
    }

    /**
     * Metoda getGamePanel navrati JPanel hry
     * @return JPanel hry
     */
    public JPanel getGamePanel() {
        return this.gamePanel;
    }

    /**
     * Metoda getLayerdPane navrati JLayerdPane
     * @return vrstvy
     */
    public JLayeredPane getLayeredPane() {
        return this.layeredPane;
    }

    /**
     * Metoda startGame spusti novy Thread hry
     */
    public void startGame () {
        this.gameThread = new Thread(this);
        this.gameThread.start();
        this.paused = false;
    }

    /**
     * Metoda endGame zmaze Thread hry
     */
    public void endGame() {
        this.gameThread = null;
    }

    /**
     * Metoda run je "srdcom" hry pohyby a aktualizacie bezia tu
     */
    @Override
    public void run() {
        int fps = 60;
        double interval = 1000000000 / (double)fps;
        double delta = 0;
        long prevTime = System.nanoTime();
        long curTime;

        while (this.gameThread != null) {
            curTime = System.nanoTime();
            delta += (curTime - prevTime) / interval;
            prevTime = curTime;

            if (delta >= 1) {
                this.keyListening();
                if (!this.paused && !this.isFighting) {
                    this.update();
                }
                delta--;
            }
        }
    }

    /**
     * Metoda aktualizuje vsetko co sa da
     */
    public void update () {
        for (IUpdatable entity : this.updatables) {
            if (entity != null) {
                entity.update();
                entity.repaint();
            }
        }
        for (Enemy enemy : this.enemies) {
            if (enemy.getCurrentTile() == this.player.getCurrentTile()) {
                this.player.setDirection(0);
                this.player.getImage();
                enemy.setDirection(1);
                ((Zombie)enemy).setImage();
                this.startFight(enemy);
                break;
            }
        }
    }

    /**
     * Metoda sluzi na zapnutie a vypnutie Pauzoveho menu
     */
    public void keyListening() {

        if (this.keyListener.isExit() && !this.isFighting) {
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
                            TimeUnit.MILLISECONDS.sleep(1);
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

    /**
     * Metoda getCurrentRoom navrati actualnu miestnost
     * @return aktualna miestnost
     */
    public Room getCurrentRoom() {
        return this.currRoom;
    }

    /**
     * Metoda setPlayerCords nastavi hracovi suradnice
     * @param x suradnica x
     * @param y suradnica y
     */
    public void setPlayerCords(int x, int y) {
        this.player.setX(x);
        this.player.setY(y);
    }

    /**
     * Metoda generatePausePanel vygeneruje Pauzový panel a všetky jeho prvky s ich logikou
     */
    public void genereatePausePanel() {
        Frame f = new Frame(580, 300, "PAUSED");
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

        JButton equipement1 = new JButton();
        equipement1.setFocusable(false);
        equipement1.setBounds(570, 80, 50, 50);
        equipement1.setBackground(Color.WHITE);
        equipement1.addActionListener(a -> {
            this.player.addItem(this.player.getEquipement(0));
            this.player.removeEquipement(0);
            equipement1.setIcon(null);
        });

        JButton equipement2 = new JButton();
        equipement2.setFocusable(false);
        equipement2.setBounds(570, 130, 50, 50);
        equipement2.setBackground(Color.WHITE);
        equipement2.addActionListener(a -> {
            this.player.addItem(this.player.getEquipement(1));
            this.player.removeEquipement(1);
            equipement2.setIcon(null);
        });


        JButton equipement3 = new JButton();
        equipement3.setFocusable(false);
        equipement3.setBounds(570, 180, 50, 50);
        equipement3.setBackground(Color.WHITE);
        equipement3.addActionListener(a -> {
            this.player.addItem(this.player.getEquipement(2));
            this.player.removeEquipement(2);
            equipement3.setIcon(null);
        });

        JButton equipButton = new JButton("EQUIP");
        equipButton.setFocusable(false);
        equipButton.setBounds(520, 320, 100, 50);
        equipButton.setBackground(Color.WHITE);
        equipButton.setEnabled(false);
        equipButton.addActionListener(a -> {
            if (this.player != null) {
                this.player.setEquipement(this.inventoryPane.getSelectedValue());
                this.player.removeItem(this.inventoryPane.getSelectedIndex());
            }
            if (this.player != null && this.player.getEquipement(0) != null) {
                equipement1.setIcon(new ImageIcon(this.player.getEquipement(0).getImage()));
            }
            if (this.player != null && this.player.getEquipement(1) != null) {
                equipement2.setIcon(new ImageIcon(this.player.getEquipement(1).getImage()));
            }
            if (this.player != null && this.player.getEquipement(2) != null) {
                equipement3.setIcon(new ImageIcon(this.player.getEquipement(2).getImage()));
            }
        });

        JButton closeInvButton = new JButton("X");
        closeInvButton.setFocusable(false);
        closeInvButton.setBounds(520, 20, 50, 50);
        closeInvButton.setBackground(Color.RED);
        closeInvButton.addActionListener(a -> {
            this.layeredPane.remove(equipement1);
            this.layeredPane.remove(equipement2);
            this.layeredPane.remove(equipement3);
            this.inventoryComponents.remove(equipement1);
            this.inventoryComponents.remove(equipement2);
            this.inventoryComponents.remove(equipement3);
            this.layeredPane.remove(equipButton);
            this.inventoryComponents.remove(equipButton);
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
            this.inventoryPane.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.inventoryPane.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    equipButton.setEnabled(this.inventoryPane.getSelectedIndex() != -1);
                }
            });

            this.inventoryComponents.add(closeInvButton);
            this.layeredPane.add(closeInvButton);
            this.layeredPane.setLayer(closeInvButton, 20);

            this.inventoryComponents.add(equipement1);
            this.layeredPane.add(equipement1);
            this.layeredPane.setLayer(equipement1, 20);

            this.inventoryComponents.add(equipement2);
            this.layeredPane.add(equipement2);
            this.layeredPane.setLayer(equipement2, 20);

            this.inventoryComponents.add(equipement3);
            this.layeredPane.add(equipement3);
            this.layeredPane.setLayer(equipement3, 20);

            this.inventoryComponents.add(equipButton);
            this.layeredPane.add(equipButton);
            this.layeredPane.setLayer(equipButton, 20);
        });
        this.pausePanel.add(inventoryButton);
    }

    /**
     * Metoda repaintLabels prekresli všetky JLabeli
     */
    public void repaintLabels() {
        for (IUpdatable updatable : this.updatables) {
            if (updatable != null) {
                this.layeredPane.remove(updatable.getJLabel());
            }
        }
        for (IUpdatable updatable : this.updatables) {
            if (updatable != null) {
                updatable.piantLabel(this.layeredPane);
            }
        }
    }

    public static class FRICanvas extends JPanel {

        private final ArrayList<ITile> tiles;

        /**
         * Konštruktor triedy FRICanvas, ide o upravený JPanel na rýchle kreslenie pozadia
         * @param x suradnica x
         * @param y suradnica y
         * @param height výška
         * @param width šírka
         */
        public FRICanvas(int x, int y, int height, int width) {
            this.setBackground(Color.BLACK);
            this.setFocusable(false);
            this.setBounds(x, y, width, height);
            this.tiles = new ArrayList<>();
        }

        /**
         * Metoda setTiles nastavý šetky políčka do ArrayListu
         * @param room inštancia miestnosti
         */
        public void setTiles(Room room) {
            this.tiles.clear();
            for (ITile[] tileArr:room.getLayout()) {
                this.tiles.addAll(Arrays.asList(tileArr));

            }
            this.repaint();
        }

        /**
         * Metoda paint nakreslí pozadie
         * @param g grafika
         */
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
