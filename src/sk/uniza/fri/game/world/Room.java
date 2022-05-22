package sk.uniza.fri.game.world;

import sk.uniza.fri.game.world.tile.ITile;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Room {

    private final ITile[][] layout;
    private final int tileSize;

    public Room(int rows, int cols, int tileSize) {
        this.layout = new ITile[rows][cols];
        this.tileSize = tileSize;
    }

    public void setTile(ITile tile, int row, int col) {
        tile.setLocation(this.calculateTileX(row), this.calculateTileY(col));
        this.layout[row][col] = tile;
    }

    public ITile[][] getLayout() {
        return this.layout;
    }

    public int calculateTileX(int row) {
        return row * this.tileSize;
    }

    public int calculateTileY(int col) {
        return col * this.tileSize;
    }

    public ITile getTile(int x, int y) {
        return this.layout[x][y];
    }
}
