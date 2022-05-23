package sk.uniza.fri.game.entities.enemy;

import sk.uniza.fri.game.IUpdatable;
import sk.uniza.fri.game.entities.Entity;
import sk.uniza.fri.game.run.Game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.image.BufferedImage;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Enemy extends Entity implements IUpdatable {

    private boolean isMoving;
    private final JLabel label;
    private final Game game;
    private int roomX;
    private int roomY;
    private int direction;

    public Enemy(Game game) {
        this.label = new JLabel();
        this.label.setBounds(0, 0, 48, 48);
        this.game = game;
        this.direction = 0;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return this.direction;
    }

    public void setImage(BufferedImage img) {
        this.label.setIcon(new ImageIcon(img));
    }

    public void setTile(int x, int y) {
        this.game.getCurrentRoom().getTile(x, y);
        this.roomX = x;
        this.roomY = y;
    }

    @Override
    public void update() {

    }

    @Override
    public void repaint() {
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
}
