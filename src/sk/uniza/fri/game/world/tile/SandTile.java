package sk.uniza.fri.game.world.tile;

import javax.imageio.ImageIO;
import java.io.File;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class SandTile extends StandardTile {

    /**
     * Konstruktor triedy SandTile, ide o policko
     */
    public SandTile() {
        try {
            this.setTileImg(ImageIO.read(new File("textures/tile/sand.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
