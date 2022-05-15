package sk.uniza.fri.game.world.tile;

import sk.uniza.fri.game.IUpdatable;
import sk.uniza.fri.game.world.tile.ITile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class StandardTile implements ITile, IUpdatable {

    private int tileID;
    private BufferedImage tileImg;
    private int x;
    private int y;

    public StandardTile() {
        this.tileID = 0;
    }

    @Override
    public int getTileID() {
        return this.tileID;
    }

    protected void setTileID(int id) {
        this.tileID = id;
    }

    protected void setTileImg(BufferedImage img) {
        this.tileImg = img;
    }

    protected void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {

    }

    @Override
    public void paintComponent(Graphics2D graphics2D) {
        graphics2D.drawImage(this.tileImg, this.x, this.y, 48, 48, null);
    }
}
