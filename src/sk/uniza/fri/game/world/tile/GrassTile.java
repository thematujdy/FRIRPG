package sk.uniza.fri.game.world.tile;

import javax.imageio.ImageIO;
import java.io.File;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class GrassTile extends StandardTile {

    public GrassTile() {
        this.setTileID(1);
        try {
            this.setTileImg(ImageIO.read(new File("textures/tile/grass.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
