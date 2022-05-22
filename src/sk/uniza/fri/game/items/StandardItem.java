package sk.uniza.fri.game.items;

import sk.uniza.fri.game.world.tile.ITile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class StandardItem implements IItem {
    private final ITile tile;
    private BufferedImage img;
    private String name;
    private int power;
    private int armor;

    public StandardItem(ITile tile) {
        this.tile = tile;
        try {
            this.img = ImageIO.read(new File("textures/err.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StandardItem(ITile tile, BufferedImage img) {
        this.tile = tile;
        this.img = img;
    }

    public BufferedImage getImg() {
        return this.img;
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
        this.tile.setItem(null);
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
}
