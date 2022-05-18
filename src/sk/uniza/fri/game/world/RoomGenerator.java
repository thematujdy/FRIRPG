package sk.uniza.fri.game.world;

import sk.uniza.fri.game.world.tile.BrickWallTile;
import sk.uniza.fri.game.world.tile.GrassTile;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class RoomGenerator {

    private Map map;

    public RoomGenerator(Map map) {
        this.map = map;
    }

    public Room generateRoom(int rows, int cols, int tileSize) {
        Room room = new Room(cols, rows, tileSize);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                room.setTile(new GrassTile(), j, i);
            }

        }
        for (int i = 0; i < rows; i++) {
            room.setTile(new BrickWallTile(), 0, i);
        }
        for (int i = 0; i < rows; i++) {
            room.setTile(new BrickWallTile(), cols - 1, i);
        }
        for (int i = 0; i < cols; i++) {
            room.setTile(new BrickWallTile(), i, 0);
        }
        for (int i = 0; i < cols; i++) {
            room.setTile(new BrickWallTile(), i, rows - 1);
        }
        return room;
    }

}
