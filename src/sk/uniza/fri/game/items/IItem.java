package sk.uniza.fri.game.items;

import javax.swing.JLayeredPane;
import java.awt.image.BufferedImage;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public interface IItem {
    void removeFromMap(JLayeredPane layeredPane);
    BufferedImage getImage();
}
