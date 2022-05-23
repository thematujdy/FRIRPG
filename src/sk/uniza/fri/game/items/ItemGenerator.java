package sk.uniza.fri.game.items;

import java.util.Random;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class ItemGenerator {
    /**
     * Metóda generateItem vytvorí a vráti Item
     * @return nový Item
     */
    public static synchronized IItem generateItem() {
        Random ran = new Random();
        int index = ran.nextInt(5);
        switch (index) {
            case 0 -> {
                return new Axe(null);
            }
            case 1 -> {
                return new Helmet(null);
            }
            case 2 -> {
                return new Chestplate(null);
            }
            case 3 -> {
                return new Sword(null);
            }
            case 4 -> {
                return new Boots(null);
            }
        }

        return null;
    }
}
