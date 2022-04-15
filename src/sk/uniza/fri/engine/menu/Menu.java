package sk.uniza.fri.engine.menu;

import sk.uniza.fri.engine.sound.MusicPlayer;
import sk.uniza.fri.engine.window.Window;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Menu extends JPanel {

    private boolean isMusic;
    private Icon musicon;
    private Icon musicoff;

    public Menu (int width, int height, Window window) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.WHITE);
        this.setFocusable(false);
        this.setLayout(null);
        int buttonWidth = 100;
        int buttonHeight = 50;
        this.isMusic = true;

        Button newGameButton = new Button("New Game");
        newGameButton.setFocusable(false);
        newGameButton.addActionListener(a -> window.goToCharCreator());
        newGameButton.setBounds(width / 2 - buttonWidth / 2, 200, buttonWidth, buttonHeight);
        this.add(newGameButton);

        Button loadGameButton = new Button("Load Game");
        loadGameButton.setFocusable(false);
        loadGameButton.addActionListener(a -> {

        });
        loadGameButton.setBounds(width / 2 - buttonWidth / 2, 150, buttonWidth, buttonHeight);
        this.add(loadGameButton);

        Button optionsButton = new Button("Options");
        optionsButton.setFocusable(false);
        optionsButton.addActionListener(a -> {

        });
        optionsButton.setBounds(width / 2 - buttonWidth / 2, 250, buttonWidth, buttonHeight);
        this.add(optionsButton);

        Button exitButton = new Button("Exit Game");
        exitButton.setFocusable(false);
        exitButton.addActionListener(a -> {
            System.exit(0);
        });
        exitButton.setBounds(width / 2 - buttonWidth / 2, 300, buttonWidth, buttonHeight);
        this.add(exitButton);

        MusicPlayer musicPlayer = new MusicPlayer();
        try {
            Image musiconimg = ImageIO.read(new File("icons/soundon.png"));
            this.musicon = new ImageIcon(musiconimg);
            Image musicoffimg = ImageIO.read(new File("icons/soundoff.png"));
            this.musicoff = new ImageIcon(musicoffimg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton musicButton = new JButton();
        musicButton.setFocusable(false);
        musicButton.setIcon(this.musicon);
        musicButton.setBackground(Color.WHITE);
        musicButton.addActionListener(a -> {
            if (this.isMusic) {
                this.isMusic = false;
                musicPlayer.stop();
                musicButton.setIcon(this.musicoff);
            } else {
                this.isMusic = true;
                musicPlayer.start();
                musicButton.setIcon(this.musicon);
            }
        });
        musicButton.setBounds(width - 1 - 40, 1, 40, 40);
        this.add(musicButton);
    }
}
