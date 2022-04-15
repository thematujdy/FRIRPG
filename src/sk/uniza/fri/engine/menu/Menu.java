package sk.uniza.fri.engine.menu;

import sk.uniza.fri.engine.window.Window;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Button;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Menu extends JPanel {

    public Menu (int width, int height, Window window) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.WHITE);
        this.setFocusable(false);
        this.setLayout(null);

        Button newGameButton = new Button("New Game");
        newGameButton.setFocusable(false);
        newGameButton.addActionListener(a -> window.goToCharCreator());
        newGameButton.setBounds(width / 2 - 50, 200, 100, 50);
        this.add(newGameButton);

        Button loadGameButton = new Button("Load Game");
        loadGameButton.setFocusable(false);
        loadGameButton.addActionListener(a -> {

        });
        loadGameButton.setBounds(width / 2 - 50, 150, 100, 50);
        this.add(loadGameButton);

        Button optionsButton = new Button("Options");
        optionsButton.setFocusable(false);
        optionsButton.addActionListener(a -> {

        });
        optionsButton.setBounds(width / 2 - 50, 250, 100, 50);
        this.add(optionsButton);

        Button exitButton = new Button("Exit Game");
        exitButton.setFocusable(false);
        exitButton.addActionListener(a -> {
            System.exit(0);
        });
        exitButton.setBounds(width / 2 - 50, 300, 100, 50);
        this.add(exitButton);
    }
}
