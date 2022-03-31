package sk.uniza.fri.game.characterCreator;

import sk.uniza.fri.engine.window.Window;

import javax.swing.JPanel;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class CharacterCreator extends JPanel {

    public CharacterCreator (int width, int height, Window window) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setFocusable(false);

        Button save = new Button("Save Character");
        save.setFocusable(false);
        save.addActionListener(a -> window.startGameFrame());

        this.add(save);
    }
}
