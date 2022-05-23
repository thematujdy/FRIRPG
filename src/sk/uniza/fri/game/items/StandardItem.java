package sk.uniza.fri.game.items;

import sk.uniza.fri.game.IUpdatable;
import sk.uniza.fri.game.world.tile.ITile;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class StandardItem implements IItem, IUpdatable {
    private final ITile tile;
    private BufferedImage img;
    private String name;
    private int power;
    private int armor;
    private final JLabel jLabel;

    public StandardItem(ITile tile) {
        this.tile = tile;
        try {
            this.img = ImageIO.read(new File("textures/err.png"));
        } catch (Exception e) {
            e.printStackTrace();
            this.img = null;
        }
        assert this.img != null;
        this.jLabel = new JLabel(new ImageIcon(this.img));
        this.jLabel.setFocusable(false);
        this.jLabel.setBounds(0, 0, 48, 48);
    }

    public void setLocation(int x, int y) {
        this.jLabel.setLocation(x, y);
    }

    @Override
    public void update() {

    }

    @Override
    public void repaint() {

    }

    @Override
    public void piantLabel(JLayeredPane layeredPane) {
        this.jLabel.setFocusable(false);
        layeredPane.add(this.jLabel);
        layeredPane.setLayer(this.jLabel, 1);
    }

    @Override
    public JLabel getJLabel() {
        return this.jLabel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void removeFromTile() {
        this.tile.removeItem();
    }

    public int getPower() {
        return this.power;
    }

    public int getArmor() {
        return this.armor;
    }

    @Override
    public String toString() {
        String desc = this.name;
        if (this.power != 0) {
            desc = desc + "     Power:" + this.power;
        }
        if (this.armor != 0) {
            desc = desc + "     Armor:" + this.armor;
        }
        return desc;
    }

    @Override
    public void removeFromMap(JLayeredPane layeredPane) {
        layeredPane.remove(this.jLabel);
    }

    @Override
    public BufferedImage getImage() {
        return this.img;
    }

    protected void setImg(String path) {
        try {
            this.img = ImageIO.read(new File(path));
            this.jLabel.setIcon(new ImageIcon(this.img));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
