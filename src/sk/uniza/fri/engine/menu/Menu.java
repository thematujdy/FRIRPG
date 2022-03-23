package sk.uniza.fri.engine.menu;

import sk.uniza.fri.engine.window.Window;

import javax.swing.JPanel;
import java.awt.Dimension;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Menu extends JPanel {

    private Window window;

    public Menu (int tilePixels, int multiplicator, int colTiles, int rowTiles, Window window) {
        int finalTileSize = tilePixels * multiplicator;

        final int frameWidth = finalTileSize * colTiles;
        final int frameHeight = finalTileSize * rowTiles;
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.window = window;
    }
}
