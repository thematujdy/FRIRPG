package sk.uniza.fri.game.items;

import sk.uniza.fri.game.world.tile.ITile;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Boots extends StandardItem {
    /**
     * Konštruktor Itemu triedy Boots
     * @param tile políčke kde sa zobrazí
     */
    public Boots(ITile tile) {
        super(tile);
        this.setName("Ragged Boots");
        this.setArmor(1);
    }
}
