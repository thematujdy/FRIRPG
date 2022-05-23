package sk.uniza.fri.game.entities.enemy;

import sk.uniza.fri.game.entities.Entity;
import sk.uniza.fri.game.run.Game;
import sk.uniza.fri.game.world.tile.ITile;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Enemy extends Entity {

    private final JLabel label;
    private final Game game;
    private int roomX;
    private int roomY;
    private int direction;
    private ITile currTile;

    public Enemy(Game game) {
        this.label = new JLabel();
        this.label.setBounds(0, 0, 48, 48);
        this.game = game;
        this.direction = 0;
    }

    public ITile getCurrentTile() {
        return this.currTile;
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
        this.currTile = this.game.getCurrentRoom().getTile(x, y);
        this.roomX = x;
        this.roomY = y;
    }

    public void setLocation(int x, int y) {
        this.setTile(x, y);
        this.getLabel().setLocation(this.currTile.getX(), this.currTile.getY());
    }

    public JLabel getLabel() {
        return this.label;
    }
}
