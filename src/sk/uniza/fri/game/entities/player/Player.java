package sk.uniza.fri.game.entities.player;

import sk.uniza.fri.engine.window.KeyManager;
import sk.uniza.fri.game.IInteractable;
import sk.uniza.fri.game.items.IItem;
import sk.uniza.fri.game.run.Game;
import sk.uniza.fri.game.IUpdatable;
import sk.uniza.fri.game.entities.Entity;
import sk.uniza.fri.game.world.Room;
import sk.uniza.fri.game.world.tile.BrickWallTile;
import sk.uniza.fri.game.world.tile.ITile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Player extends Entity  implements IUpdatable {

    private final Game game;
    private final KeyManager keyListener;

    private boolean isMoving;
    private final JLabel label;

    private BufferedImage test;

    private final DefaultListModel<IItem> inventory;
    private ITile currentTile;
    private Room currRoom;
    private int roomX;
    private int roomY;
    private boolean isInteracting;
    private int interactingCount;
    private final IItem[] equipement;

    public Player(Game game, KeyManager keyListener) {
        this.game = game;
        this.currRoom = this.game.getCurrentRoom();
        this.setX(this.currRoom.calculateTileX(1));
        this.setY(this.currRoom.calculateTileY(1));
        this.keyListener = keyListener;
        this.isMoving = false;
        this.getImage();
        this.setDirection(1);
        this.label = new JLabel();
        this.label.setBounds(this.getX(), this.getY(), this.test.getWidth(), this.test.getHeight());
        this.label.setIcon(new ImageIcon(this.test));
        this.inventory = new DefaultListModel<>();
        this.roomX = 1;
        this.roomY = 1;
        this.moveTile();
        this.isInteracting = false;
        this.interactingCount = 0;
        this.equipement = new IItem[3];
    }

    public void setCurrentTile(int x, int y) {
        this.currRoom = this.game.getCurrentRoom();
        this.currentTile = this.currRoom.getTile(x, y);
        this.roomX = x;
        this.roomY = y;
    }

    public void removeItem(int index) {
        this.inventory.remove(index);
    }

    public void removeEquipement(int index) {
        if (index <= this.equipement.length) {
            this.equipement[index] = null;
        }
    }

    public void setEquipement(IItem item) {
        if (this.equipement[0] == null) {
            this.equipement[0] = item;
        } else if (this.equipement[1] == null) {
            this.equipement[1] = item;
        } else if (this.equipement[2] == null) {
            this.equipement[2] = item;
        }

        System.out.println(Arrays.toString(this.equipement));
    }

    public IItem getEquipement(int index) {
        if (index <= this.equipement.length) {
            return this.equipement[index];
        } else {
            return null;
        }
    }

    public void getImage () {
        try {
            switch (this.getDirection()) {
                case 0 -> this.test = ImageIO.read(new File("sprites/player/back.png"));
                case 1 -> this.test = ImageIO.read(new File("sprites/player/front.png"));
                case 2 -> this.test = ImageIO.read(new File("sprites/player/left.png"));
                case 3 -> this.test = ImageIO.read(new File("sprites/player/right.png"));
            }
            if (this.label != null) {
                this.label.setIcon(new ImageIcon(this.test));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update () {
        if (this.keyListener.isUp() && !this.isMoving) {
            this.setDirection(0);
            this.getImage();
            if (!(this.currRoom.getTile(this.roomX, this.roomY - 1) instanceof BrickWallTile)) {
                this.roomY--;
                this.moveTile();
                this.move();
            }
        } else if (this.keyListener.isDown() && !this.isMoving) {
            this.setDirection(1);
            this.getImage();
            if (!(this.currRoom.getTile(this.roomX, this.roomY + 1) instanceof BrickWallTile)) {
                this.roomY++;
                this.moveTile();
                this.move();
            }
        } else if (this.keyListener.isLeft() && !this.isMoving) {
            this.setDirection(2);
            this.getImage();
            if (!(this.currRoom.getTile(this.roomX - 1, this.roomY) instanceof BrickWallTile)) {
                this.roomX--;
                this.moveTile();
                this.move();
            }
        } else if (this.keyListener.isRight() && !this.isMoving) {
            this.setDirection(3);
            this.getImage();
            if (!(this.currRoom.getTile(this.roomX + 1, this.roomY) instanceof BrickWallTile)) {
                this.roomX++;
                this.moveTile();
                this.move();
            }
        } else if (this.keyListener.isA() && !this.isMoving && !this.isInteracting) {
            int x = 0;
            int y = 0;
            switch (this.getDirection()) {
                case 0 -> y--;
                case 1 -> y++;
                case 2 -> x--;
                case 3 -> x++;
            }
            ITile tile = this.currRoom.getTile(this.roomX + x, this.roomY + y);
            if (tile.getItem() instanceof IInteractable) {
                tile.getItem().removeFromMap(this.game.getLayeredPane());
                this.game.repaintCanvas();
                if (((IInteractable) tile.getItem()).interact(this)) {
                    System.out.println("interacted");
                    this.game.repaintLabels();
                }
            }
            Thread pause = new Thread(() -> {
                this.isInteracting = true;
                this.interactingCount = 300;
                while (this.interactingCount != 0) {
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    this.interactingCount--;
                }
                this.isInteracting = false;
            });
            pause.start();
        }

    }

    @Override
    public void repaint () {
        this.label.setLocation(this.getX(), this.getY());
    }

    @Override
    public void piantLabel(JLayeredPane layeredPane) {
        this.label.setFocusable(false);
        layeredPane.add(this.label);
        layeredPane.setLayer(this.label, 2);
    }

    @Override
    public JLabel getJLabel() {
        return this.label;
    }

    private void move () {
        int waitTime = 200;

        Thread waitMove = new Thread(() -> {
            this.isMoving = true;
            int x = 0;
            while (x < this.game.getTileSize()) {
                try {
                    Thread.sleep(waitTime / this.game.getTileSize());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                switch (this.getDirection()) {
                    case 2 -> this.setX(this.getX() - 1);
                    case 3 -> this.setX(this.getX() + 1);
                    case 0 -> this.setY(this.getY() - 1);
                    case 1 -> this.setY(this.getY() + 1);
                }

                x++;
            }
            this.isMoving = false;
        });

        waitMove.start();
    }

    public DefaultListModel<IItem> getInventory() {
        return this.inventory;
    }

    public void moveTile() {
        this.currentTile = this.currRoom.getTile(this.roomX, this.roomY);
    }

    public void addItem(IItem item) {
        this.inventory.addElement(item);
    }
}
