package sk.uniza.fri.game.world.tile;

import javax.imageio.ImageIO;
import java.io.File;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class BrickWallTile extends StandardTile {

    /**
     * Konstruktor triedy BrickWallTile, ide o policko
     */
    public BrickWallTile() {
        try {
            this.setTileImg(ImageIO.read(new File("textures/tile/wall.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
