package sk.uniza.fri.game.world;

import java.util.ArrayList;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Map {

    private RoomGenerator generator;
    private ArrayList<Room> rooms;

    public Map() {
        this.generator = new RoomGenerator(this);
        this.rooms = new ArrayList<>();
    }
}
