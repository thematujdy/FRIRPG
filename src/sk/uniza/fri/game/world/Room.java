package sk.uniza.fri.game.world;

import sk.uniza.fri.game.items.StandardItem;
import sk.uniza.fri.game.run.Game;
import sk.uniza.fri.game.world.tile.ITile;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Room {

    private final ITile[][] layout;
    private final int tileSize;

    /**
     * Konstruktor triedy Room, ide o miestnost
     * @param rows riadky
     * @param cols stlpce
     * @param tileSize velkost policka
     */
    public Room(int rows, int cols, int tileSize) {
        this.layout = new ITile[rows][cols];
        this.tileSize = tileSize;
    }

    /**
     * Metoda setTile nastavy policko na danu suradnicu
     * @param tile policko
     * @param row riadok
     * @param col stlpec
     */
    public void setTile(ITile tile, int row, int col) {
        tile.setLocation(this.calculateTileX(row), this.calculateTileY(col));
        this.layout[row][col] = tile;
    }

    /**
     * Metoda getLAyout vrati vsetky policka
     * @return vsetky policka
     */
    public ITile[][] getLayout() {
        return this.layout;
    }

    /**
     * Metoda calculateTileX vrati suradnicu x riadku
     * @param row riadok
     * @return suradnica x
     */
    public int calculateTileX(int row) {
        return row * this.tileSize;
    }

    /**
     * Metoda calculateTileY vrati suradnicu y stlpca
     * @param col stlpec
     * @return suradnica y
     */
    public int calculateTileY(int col) {
        return col * this.tileSize;
    }

    /**
     * Metoda getTile vrati policko podla parametrov
     * @param x suracnica x
     * @param y suradnica y
     * @return policko z miestnosti
     */
    public ITile getTile(int x, int y) {
        return this.layout[x][y];
    }

    /**
     * Metoda cleatItems zmaze vsetky itemy z miestnosti
     * @param game instancia hry
     */
    public void clearItems(Game game) {
        for (ITile[] tileArr: this.layout) {
            for (ITile tile : tileArr) {
                StandardItem item = (StandardItem)tile.getItem();
                if (item != null) {
                    game.getLayeredPane().remove(item.getJLabel());
                }
            }
        }
    }
}
