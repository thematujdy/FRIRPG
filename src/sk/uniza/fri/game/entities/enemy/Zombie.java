package sk.uniza.fri.game.entities.enemy;

import sk.uniza.fri.game.IUpdatable;
import sk.uniza.fri.game.run.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

public class Zombie extends Enemy implements IUpdatable {

    /**
     * Konštruktor triedy Zombie
     * @param game Inštancia hry
     */
    public Zombie(Game game) {
        super(game);
        this.setImage();
        this.setHP(20);
        this.addPower(5);
    }

    /**
     * Metóda setImage nastavý obrázok podla aktuálneho smeru
     */
    public void setImage() {
        try {
            switch (this.getDirection()) {
                case 0 -> this.setImage(ImageIO.read(new File("sprites/zombie/back.png")));
                case 1 -> this.setImage(ImageIO.read(new File("sprites/zombie/front.png")));
                case 2 -> this.setImage(ImageIO.read(new File("sprites/zombie/left.png")));
                case 3 -> this.setImage(ImageIO.read(new File("sprites/zombie/right.png")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metóda update v triede Zombie nič nerobí
     */
    @Override
    public void update() {
    }

    /**
     * Metóda repaint() zmení lokáciu JLabelu
     */
    @Override
    public void repaint() {
        this.getJLabel().setLocation(this.getX(), this.getY());
    }

    /**
     * Metóda paintLabel vloží JLabel do JLayerPanelu
     * @param layeredPane JLayeredPanel posláný z inštancie hry
     */
    @Override
    public void piantLabel(JLayeredPane layeredPane) {
        this.getJLabel().setFocusable(false);
        layeredPane.add(this.getJLabel());
        layeredPane.setLayer(this.getJLabel(), 2);
    }

    /**
     * Metóda getJLabel vracia JLabel
     * @return Hodnota JLabelu
     */
    @Override
    public JLabel getJLabel() {
        return this.getLabel();
    }
}
