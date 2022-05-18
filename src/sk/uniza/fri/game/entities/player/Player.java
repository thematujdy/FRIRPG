package sk.uniza.fri.game.entities.player;

import sk.uniza.fri.engine.window.KeyManager;
import sk.uniza.fri.game.run.Game;
import sk.uniza.fri.game.IUpdatable;
import sk.uniza.fri.game.entities.Entity;

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
    private JLabel label;

    private BufferedImage test;

    public Player(Game game, KeyManager keyListener) {
        this.game = game;
        this.keyListener = keyListener;
        this.isMoving = false;
        this.getImage();
        this.setDirection(1);
        this.label = new JLabel();
        game.getLayeredPane().add(this.label);
        game.getLayeredPane().setLayer(this.label, 1);
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
    public void repaint () {
        this.label.setLocation(this.getX(), this.getY());
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

}
