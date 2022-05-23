package sk.uniza.fri.engine.menu;

import sk.uniza.fri.engine.sound.MusicPlayer;
import sk.uniza.fri.engine.window.MusicButton;
import sk.uniza.fri.engine.window.Window;

import javax.swing.JPanel;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Properties;

public class Menu extends JPanel {

    /***
     * Konstruktor triedy Menu, vytvara JPanel a tlacitka pre menu
     * @param width šírka panelu
     * @param height výška panelu
     * @param window hlavné okno
     * @param musicPlayer prehrávač hudbny
     * @param prop config súbor
     */
    public Menu (int width, int height, Window window, MusicPlayer musicPlayer, Properties prop) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.WHITE);
        this.setFocusable(false);
        this.setLayout(null);
        int buttonWidth = 100;
        int buttonHeight = 50;

        Button newGameButton = new Button("New Game");
        newGameButton.setFocusable(false);
        newGameButton.addActionListener(a -> window.startGameFrame(false));
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
        exitButton.addActionListener(a -> System.exit(0));
        exitButton.setBounds(width / 2 - buttonWidth / 2, 300, buttonWidth, buttonHeight);
        this.add(exitButton);


        MusicButton musicButton = new MusicButton(window, musicPlayer, prop);
        musicButton.setBounds(width - 51, 1, 50, 50);
        this.add(musicButton.getMusicButton());
    }
}
