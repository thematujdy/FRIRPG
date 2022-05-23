package sk.uniza.fri.game.items;

import sk.uniza.fri.game.IInteractable;
import sk.uniza.fri.game.entities.player.Player;
import sk.uniza.fri.game.run.Game;
import sk.uniza.fri.game.world.tile.ITile;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class WoodenDoor extends StandardItem implements IInteractable {
    private final Game game;

    /**
     * Konštruktor Itemu triedy WoodenDoor
     * @param tile políčke kde sa zobrazí
     * @param game Inštancia hry
     */
    public WoodenDoor(ITile tile, Game game) {
        super(tile);
        this.setName("Wooden Door");
        this.game = game;
        this.setImg("textures/item/WoodenDoor.png");
    }

    /**
     * metóda Interact, zabezpečuje zmenu aktualnej miestnosti
     * @param player inštancia hráča
     * @return vracá či prebehla interakcia
     */
    @Override
    public boolean interact(Player player) {
        //this.removeFromTile();
        this.game.generateRoom();
        return true;
    }
}
