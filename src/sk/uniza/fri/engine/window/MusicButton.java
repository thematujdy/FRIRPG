package sk.uniza.fri.engine.window;

import sk.uniza.fri.engine.sound.MusicPlayer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.util.Properties;

public class MusicButton {

    private ImageIcon musicon;
    private ImageIcon musicoff;
    private final JButton musicButton;


    /**
     * Konstruktor triedy MusicButton, ide o tlacitko ktore sluzi na zapnutie a vypnutie hudby
     */
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
        this.musicButton.addActionListener(a -> {
            if (window.getMusic()) {
                window.setMusic(false);
                musicPlayer.stop();
                this.musicButton.setIcon(this.musicoff);
                prop.setProperty("music", "0");
                window.saveCfg();
            } else {
                window.setMusic(true);
                musicPlayer.start();
                this.musicButton.setIcon(this.musicon);
                prop.setProperty("music", "1");
                window.saveCfg();
            }
        });
    }

    public void setBounds (int x, int y, int width, int height) {
        this.musicButton.setBounds(x, y, width, height);
    }

    public JButton getMusicButton() {
        return this.musicButton;
    }
}
