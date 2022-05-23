package sk.uniza.fri.game.world.tile;

import javax.imageio.ImageIO;
import java.io.File;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class SnowTile extends StandardTile {

    /**
     * Konstruktor triedy SnowTile, ide o policko
     */
    public SnowTile() {
        try {
            this.setTileImg(ImageIO.read(new File("textures/tile/snow.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
