package sk.uniza.fri.game.world.tile;

import javax.imageio.ImageIO;
import java.io.File;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class BrickWallTile extends StandardTile {

    public BrickWallTile() {
        this.setTileID(2);
        try {
            this.setTileImg(ImageIO.read(new File("textures/tile/wall.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
