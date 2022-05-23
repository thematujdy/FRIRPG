package sk.uniza.fri.game;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Frame extends JPanel {

    private final Graphics2D g;
    private final BufferedImage img;

    /**
     * Konstruktor triedy Frame bez textu, ide o 2D pozadie
     * @param height vyska
     * @param width sirka
     */
    public Frame(int height, int width) {
        this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB) ;
        this.g = this.img.createGraphics();
        this.g.setColor(Color.WHITE);
        this.g.drawRect(0, 0, width - 1, height - 1);
        this.g.dispose();
    }

    /**
     * Konstruktor triedy Frame s textom, ide o 2D pozadie
     * @param height vyska
     * @param width sirka
     * @param title nazov
     */
    public Frame(int height, int width, String title) {
        this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.g = this.img.createGraphics();
        this.g.setColor(Color.WHITE);
        this.g.drawRect(0, 0, width - 1, height - 1);
        this.g.drawString(title, (width / 2) - (title.length() * 4), 20);
        this.g.dispose();
    }

    public BufferedImage getFrame() {
        return this.img;
    }

}
