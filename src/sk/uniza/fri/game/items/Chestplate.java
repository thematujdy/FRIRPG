package sk.uniza.fri.game.items;

import sk.uniza.fri.game.world.tile.ITile;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Chestplate extends StandardItem {
    /**
     * Konštruktor Itemu triedy Chestplate
     * @param tile políčke kde sa zobrazí
     */
    public Chestplate(ITile tile) {
        super(tile);
        this.setName("Knight's Chestplate");
        this.setArmor(3);
    }
}
