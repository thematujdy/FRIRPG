package sk.uniza.fri.game.items;

import sk.uniza.fri.game.IInteractable;
import sk.uniza.fri.game.entities.player.Player;
import sk.uniza.fri.game.run.Game;
import sk.uniza.fri.game.world.Room;
import sk.uniza.fri.game.world.tile.ITile;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class WoodenDoor extends StandardItem implements IInteractable {

    private final Game game;

    public WoodenDoor(ITile tile, Game game) {
        super(tile);
        this.game = game;
    }

    @Override
    public boolean interact(Player player) {
        this.removeFromTile();
        Room room = this.game.getRoomGenerator().generateRoom(13, 15, 48, this.game);
        this.game.setCurrRoom(room);
        this.game.setPlayerCords(48, 48);
        return true;
    }
}
