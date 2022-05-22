package sk.uniza.fri.game.world;

import sk.uniza.fri.game.items.Bible;
import sk.uniza.fri.game.items.WoodenDoor;
import sk.uniza.fri.game.run.Game;
import sk.uniza.fri.game.world.tile.BrickWallTile;
import sk.uniza.fri.game.world.tile.GrassTile;
import sk.uniza.fri.game.world.tile.SandTile;
import sk.uniza.fri.game.world.tile.SnowTile;

import java.util.Random;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class RoomGenerator {

    public Room generateRoom(int rows, int cols, int tileSize, Game game) {
        Room room = new Room(cols, rows, tileSize);
        Random random = new Random();
        int seed = random.nextInt(3);
        switch (seed) {
            case 0 -> {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        room.setTile(new GrassTile(), j, i);
                    }
                }
            }
            case 1 -> {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        room.setTile(new SandTile(), j, i);
                    }
                }
            }
            case 2 -> {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        room.setTile(new SnowTile(), j, i);
                    }
                }
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
        room.getTile(0, 2).setItem(new WoodenDoor(room.getTile(0, 2), game));
        room.getTile(0, 3).setItem(new Bible(room.getTile(0, 3)));
        return room;
    }

}
