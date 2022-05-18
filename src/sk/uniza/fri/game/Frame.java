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
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private BufferedImage img;

    public Frame(int width, int height, int x, int y) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB) ;
        this.g = this.img.createGraphics();
        this.g.setColor(Color.WHITE);
        this.g.drawRect(0, 0, height - 1, width - 1);
        this.g.dispose();
    }

    public Frame(int width, int height, int x, int y, String title) {
        title.toUpperCase();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
        this.g = this.img.createGraphics();
        this.g.setColor(Color.WHITE);
        this.g.drawRect(0, 0, height - 1, width - 1);
        this.g.drawString(title, (height / 2) - (title.length() * 4), 20);
        this.g.dispose();
    }

    public void paintComponent(Graphics2D g) {
        g.drawImage(this.img, this.x, this.y, this.height, this.width, null);
    }

    public BufferedImage getFrame() {
        return this.img;
    }

}
