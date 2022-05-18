package sk.uniza.fri.game.world.tile;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public interface ITile {
    int getTileID();
    void setLocation(int x, int y);
    int getX();
    int getY();
    BufferedImage getImage();
    Graphics getGraphics();
}
