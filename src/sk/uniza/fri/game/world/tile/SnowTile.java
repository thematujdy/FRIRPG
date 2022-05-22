package sk.uniza.fri.game.world.tile;

import javax.imageio.ImageIO;
import java.io.File;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class SnowTile extends StandardTile {

    public SnowTile() {
        this.setTileID(4);
        try {
            this.setTileImg(ImageIO.read(new File("textures/tile/snow.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
