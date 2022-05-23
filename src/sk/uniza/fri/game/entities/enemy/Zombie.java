package sk.uniza.fri.game.entities.enemy;

import sk.uniza.fri.game.IUpdatable;
import sk.uniza.fri.game.run.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Zombie extends Enemy implements IUpdatable {

    private boolean isMoving;

    public Zombie(Game game) {
        super(game);
        this.isMoving = false;
        this.setImage();
        this.setHP(20);
        this.addPower(5);
    }

    public void setImage() {
        try {
            switch (this.getDirection()) {
                case 0 -> this.setImage(ImageIO.read(new File("sprites/zombie/back.png")));
                case 1 -> this.setImage(ImageIO.read(new File("sprites/zombie/front.png")));
                case 2 -> this.setImage(ImageIO.read(new File("sprites/zombie/left.png")));
                case 3 -> this.setImage(ImageIO.read(new File("sprites/zombie/right.png")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void move() {
        if (!this.isMoving) {

        }
    }

    @Override
    public void update() {
    }

    @Override
    public void repaint() {
        this.getJLabel().setLocation(this.getX(), this.getY());
    }

    @Override
    public void piantLabel(JLayeredPane layeredPane) {
        this.getJLabel().setFocusable(false);
        layeredPane.add(this.getJLabel());
        layeredPane.setLayer(this.getJLabel(), 2);
    }

    @Override
    public JLabel getJLabel() {
        return this.getLabel();
    }
}
