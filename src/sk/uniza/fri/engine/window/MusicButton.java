package sk.uniza.fri.engine.window;

import sk.uniza.fri.engine.sound.MusicButtonActionListener;
import sk.uniza.fri.engine.sound.MusicPlayer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.util.Properties;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class MusicButton {

    private ImageIcon musicon;
    private ImageIcon musicoff;
    private final JButton musicButton;


    public MusicButton(Window window, MusicPlayer musicPlayer, Properties prop) {
        try {
            Image musiconimg = ImageIO.read(new File("icons/soundon.png"));
            this.musicon = new ImageIcon(musiconimg);
            Image musicoffimg = ImageIO.read(new File("icons/soundoff.png"));
            this.musicoff = new ImageIcon(musicoffimg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.musicButton = new JButton();
        this.musicButton.setFocusable(false);
        if (window.getMusic()) {
            this.musicButton.setIcon(this.musicon);
        } else {
            this.musicButton.setIcon(this.musicoff);
        }
        this.musicButton.setBackground(Color.WHITE);
        this.musicButton.addActionListener(new MusicButtonActionListener(window, musicPlayer, prop,
                this.musicButton, this.musicon, this.musicoff));
    }

    public void setBounds (int x, int y, int width, int height) {
        this.musicButton.setBounds(x, y, width, height);
    }

    public JButton getMusicButton() {
        return this.musicButton;
    }
}
