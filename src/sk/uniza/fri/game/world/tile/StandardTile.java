package sk.uniza.fri.game.world.tile;

import sk.uniza.fri.game.items.IItem;
import java.awt.image.BufferedImage;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class StandardTile implements ITile {

    private BufferedImage tileImg;
    private int x;
    private int y;
    private IItem item;

    /**
     * Konstruktor triedy StandardTile, ide o zakladne policko
     */
    public StandardTile() {
        this.item = null;
    }

    /**
     * Metoda setLocation nastavy poziciu policka podla parametrov
     * @param x suradnica x
     * @param y suradnica y
     */
    @Override
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Metoda getX vrati polohu x policka
     * @return poloha x
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * Metoda getY vrati polohu y policka
     * @return poloha y
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * Metoda getImage vrati obrazok policka
     * @return obrazok policka
     */
    @Override
    public BufferedImage getImage() {
        return this.tileImg;
    }

    /**
     * Metoda getItem vrati Item na policku
     * @return Item na policku
     */
    @Override
    public IItem getItem() {
        return this.item;
    }

    /**
     * Metoda setItem nastavi item na policko
     * @param item instancia itemu
     */
    @Override
    public void setItem(IItem item) {
        this.item = item;
    }

    /**
     * Metoda removeItem odstrani item z policka
     */
    @Override
    public void removeItem() {
        this.item = null;
    }

    /**
     * Metoda setTileImg nastavy obrazok policka
     * @param img Obrazok
     */
    protected void setTileImg(BufferedImage img) {
        this.tileImg = img;
    }
}
