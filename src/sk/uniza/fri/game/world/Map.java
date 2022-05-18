package sk.uniza.fri.game.world;

import java.util.ArrayList;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Map {

    private final RoomGenerator generator;
    private final ArrayList<Room> rooms;
    private final int tileSize;

    public Map(int tileSize) {
        this.generator = new RoomGenerator(this);
        this.rooms = new ArrayList<>();
        this.tileSize = tileSize;
    }

    public void addRoom(int rows, int cols) {
        this.rooms.add(this.generator.generateRoom(rows, cols, this.tileSize));
    }

    public Room getRoom(int index) {
        return this.rooms.get(index);
    }
}
