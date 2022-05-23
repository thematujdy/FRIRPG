package sk.uniza.fri.game.items;

import sk.uniza.fri.game.world.tile.ITile;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Sword extends StandardItem {
    /**
     * Konštruktor Itemu triedy Sword
     * @param tile políčke kde sa zobrazí
     */
    public Sword(ITile tile) {
        super(tile);
        this.setName("Standard Sword");
        this.setPower(5);
    }
}
