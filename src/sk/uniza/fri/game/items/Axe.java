package sk.uniza.fri.game.items;

import sk.uniza.fri.game.world.tile.ITile;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Axe extends StandardItem {
    /**
     * Konštruktor Itemu triedy Axe
     * @param tile políčke kde sa zobrazí
     */
    public Axe(ITile tile) {
        super(tile);
        this.setName("Lumberjack's Axe");
        this.setPower(5);
    }
}
