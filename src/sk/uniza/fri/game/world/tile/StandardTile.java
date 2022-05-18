package sk.uniza.fri.game.world.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class StandardTile implements ITile {

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

    @Override
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public BufferedImage getImage() {
        return this.tileImg;
    }

    @Override
    public Graphics getGraphics() {
        return this.tileImg.getGraphics();
    }

    protected void setTileID(int id) {
        this.tileID = id;
    }

    protected void setTileImg(BufferedImage img) {
        this.tileImg = img;
    }
}
