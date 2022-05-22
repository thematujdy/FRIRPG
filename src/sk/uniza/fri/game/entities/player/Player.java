package sk.uniza.fri.game.entities.player;

import sk.uniza.fri.engine.window.KeyManager;
import sk.uniza.fri.game.IInteractable;
import sk.uniza.fri.game.items.Bible;
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
    }


    public void getImage () {
        try {
            this.test = ImageIO.read(new File("sprites/head/head_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update () {
        if (this.keyListener.isUp() && !this.isMoving) {
            this.setDirection(0);
            if (!(this.currRoom.getTile(this.roomX, this.roomY - 1) instanceof BrickWallTile)) {
                this.roomY--;
                this.moveTile();
                this.move();
            }
        } else if (this.keyListener.isDown() && !this.isMoving) {
            this.setDirection(1);
            if (!(this.currRoom.getTile(this.roomX, this.roomY + 1) instanceof BrickWallTile)) {
                this.roomY++;
                this.moveTile();
                this.move();
            }
        } else if (this.keyListener.isLeft() && !this.isMoving) {
            this.setDirection(2);
            if (!(this.currRoom.getTile(this.roomX - 1, this.roomY) instanceof BrickWallTile)) {
                this.roomX--;
                this.moveTile();
                this.move();
            }
        } else if (this.keyListener.isRight() && !this.isMoving) {
            this.setDirection(3);
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
                if (((IInteractable) tile.getItem()).interact(this)) {
                    System.out.println("interacted");
                }
            } else {
                System.out.println("couldn't interact");
            }
            Thread pause = new Thread(() -> {
                this.isInteracting = true;
                this.interactingCount = 100;
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
        layeredPane.setLayer(this.label, 1);
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

    public JLabel getLabel() {
        return this.label;
    }

    public DefaultListModel<IItem> getInventory() {
        return this.inventory;
    }

    public void cahngeRoom(Room room) {
        this.currRoom = room;
    }

    public void moveTile() {
        this.currentTile = this.currRoom.getTile(this.roomX, this.roomY);
    }

    public void addItem(IItem item) {
        this.inventory.addElement(item);
    }
}
