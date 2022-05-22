package sk.uniza.fri.game.world.tile;

import sk.uniza.fri.game.items.IItem;

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
    private IItem item;

    public StandardTile() {
        this.tileID = 0;
        this.item = null;
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

    @Override
    public IItem getItem() {
        return this.item;
    }

    @Override
    public void setItem(IItem item) {
        this.item = item;
    }

    @Override
    public void removeItem() {
        this.item = null;
    }

    protected void setTileID(int id) {
        this.tileID = id;
    }

    protected void setTileImg(BufferedImage img) {
        this.tileImg = img;
    }
}
