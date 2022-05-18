package sk.uniza.fri.engine.sound;

import sk.uniza.fri.engine.window.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class MusicButtonActionListener implements ActionListener {
    private final Window window;
    private final MusicPlayer musicPlayer;
    private final JButton musicButton;
    private final Properties prop;
    private final Icon musicoff;
    private final Icon musicon;

    public MusicButtonActionListener(Window window, MusicPlayer musicPlayer, Properties prop) {
        this.window = window;
        this.musicPlayer = musicPlayer;
        this.prop = prop;
        this.musicButton = null;
        this.musicoff = null;
        this.musicon = null;
    }

    public MusicButtonActionListener(Window window, MusicPlayer musicPlayer, Properties prop, JButton musicButton,
                                     Icon musicon, Icon musicoff) {
        this.window = window;
        this.musicPlayer = musicPlayer;
        this.prop = prop;
        this.musicButton = musicButton;
        this.musicon = musicon;
        this.musicoff = musicoff;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.window.getMusic()) {
            this.window.setMusic(false);
            this.musicPlayer.stop();
            if (this.musicButton != null) {
                this.musicButton.setIcon(this.musicoff);
            }
            this.prop.setProperty("music", "0");
            this.window.saveCfg();
        } else {
            this.window.setMusic(true);
            this.musicPlayer.start();
            if (this.musicButton != null) {
                this.musicButton.setIcon(this.musicon);
            }
            this.prop.setProperty("music", "1");
            this.window.saveCfg();
        }
    }
}
