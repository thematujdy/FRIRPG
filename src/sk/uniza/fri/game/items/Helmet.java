package sk.uniza.fri.game.items;

import sk.uniza.fri.game.world.tile.ITile;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Helmet extends StandardItem {
    /**
     * Konštruktor Itemu triedy Helmet
     * @param tile políčke kde sa zobrazí
     */
    public Helmet(ITile tile) {
        super(tile);
        this.setName("Miner's Helmet");
        this.setArmor(1);
    }
}
