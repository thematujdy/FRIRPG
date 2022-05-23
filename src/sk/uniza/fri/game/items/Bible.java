package sk.uniza.fri.game.items;

import sk.uniza.fri.game.IInteractable;
import sk.uniza.fri.game.entities.player.Player;
import sk.uniza.fri.game.world.tile.ITile;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Bible extends StandardItem implements IInteractable {

    /**
     * Konštruktor Itemu triedy Biblia
     * @param tile políčke kde sa zobrazí
     */
    public Bible(ITile tile) {
        super(tile);
        this.setName("Bible");
        this.setPower(1000);
        this.setArmor(1000);
        this.setImg("textures/item/Bible.png");
    }

    /**
     * metóda Interact, zabezpečuje že sa zdvihne zo zeme
     * @param player inštancia hráča
     * @return vracá či prebehla interakcia
     */
    @Override
    public boolean interact(Player player) {
        this.removeFromTile();
        player.addItem(this);
        return true;
    }
}
