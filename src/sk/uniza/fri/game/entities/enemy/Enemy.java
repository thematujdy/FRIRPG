package sk.uniza.fri.game.entities.enemy;

import sk.uniza.fri.game.entities.Entity;
import sk.uniza.fri.game.run.Game;
import sk.uniza.fri.game.world.tile.ITile;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;

public class Enemy extends Entity {

    private final JLabel label;
    private final Game game;
    private int direction;
    private ITile currTile;

    /**
     * Konštruktor triedy Enemy, táto trieda slúži ako základ pre všetky treidy nepriateľov
     * @param game Inštancia hry
     */
    public Enemy(Game game) {
        this.label = new JLabel();
        this.label.setBounds(0, 0, 48, 48);
        this.game = game;
        this.direction = 0;
    }

    /**
     *  Metóda getCurrentTile() vracia aktuálne políčko
     * @return Aktuálne políčko
     */
    public ITile getCurrentTile() {
        return this.currTile;
    }

    /**
     * Metóda setDirection(int direction) nastavuje smer neriatela
     * @param direction smer
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Metóda getDirection() vracia hodnotu aktuálneho smeru
     * @return int aktuálneho smeru
     */
    public int getDirection() {
        return this.direction;
    }

    /**
     * Metóda setImage(Buffered img) natavý imageIcon pre JLabel this.label
     * @param img obrázok
     */
    public void setImage(BufferedImage img) {
        this.label.setIcon(new ImageIcon(img));
    }

    /**
     * Metóda setTile(int x, int y) nastavý aktuálne políčko podla vložených súradníc
     * @param x súradnica x
     * @param y súradnica y
     */
    public void setTile(int x, int y) {
        this.currTile = this.game.getCurrentRoom().getTile(x, y);
    }

    /**
     * Metóda setLocation(int x, int y) nastaví polohu JLabelu podla vložených súradníc
     * @param x súradnica x
     * @param y súradnica y
     */
    public void setLocation(int x, int y) {
        this.setTile(x, y);
        this.getLabel().setLocation(this.currTile.getX(), this.currTile.getY());
    }

    /**
     * Metóda getLabel() vráti hodnotu JLabelu this.label
     * @return JLabel tohto nepriatela
     */
    public JLabel getLabel() {
        return this.label;
    }
}
