package sk.uniza.fri.game.world.tile;

import sk.uniza.fri.game.IInteractable;
import sk.uniza.fri.game.world.tile.StandardTile;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class WoodenDoorTile extends StandardTile implements IInteractable {

    public WoodenDoorTile() {
        this.setTileID(3);
    }

    @Override
    public boolean interact() {
        return true;
    }
}
