package sk.uniza.fri.game.world.tile;

import sk.uniza.fri.game.items.IItem;

import java.awt.image.BufferedImage;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public interface ITile {
    void setLocation(int x, int y);
    int getX();
    int getY();
    BufferedImage getImage();
    IItem getItem();
    void setItem(IItem item);
    void removeItem();
}
