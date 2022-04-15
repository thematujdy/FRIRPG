package sk.uniza.fri.game.entities.player;

import sk.uniza.fri.engine.window.KeyManager;
import sk.uniza.fri.game.GameFrame;
import sk.uniza.fri.game.IUpdatable;
import sk.uniza.fri.game.entities.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Player extends Entity  implements IUpdatable {

    private final GameFrame gameFrame;
    private final KeyManager keyListener;

    private boolean isMoving;

    private BufferedImage test;

    public Player(GameFrame gameFrame, KeyManager keyListener) {
        this.gameFrame = gameFrame;
        this.keyListener = keyListener;
        this.isMoving = false;
        this.getImage();
        this.setDirection(1);
        this.setDefaultTile(3, 3);
    }

    public void setDefaultTile (int rowTile, int colTile) {
        this.setX(this.gameFrame.getTileX(rowTile));
        this.setY(this.gameFrame.getTileY(colTile));
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
            this.move();
        } else if (this.keyListener.isDown() && !this.isMoving) {
            this.setDirection(1);
            this.move();
        } else if (this.keyListener.isLeft() && !this.isMoving) {
            this.setDirection(2);
            this.move();
        } else if (this.keyListener.isRight() && !this.isMoving) {
            this.setDirection(3);
            this.move();
        }

    }

    @Override
    public void paintComponent (Graphics2D g2) {
        BufferedImage image = null;

        switch (this.getDirection()) {
            case 0 -> image = this.test;
            default -> image = this.test;
        }

        g2.drawImage(image, this.getX(),this.getY(),
                this.gameFrame.getFinalTileSize(), this.gameFrame.getFinalTileSize(), null);

    }

    private void move () {
        int waitTime = 200;

        Thread waitMove = new Thread(() -> {
            this.isMoving = true;
            int x = 0;
            while (x < this.gameFrame.getFinalTileSize()) {
                try {
                    Thread.sleep(waitTime / this.gameFrame.getFinalTileSize());
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

}
