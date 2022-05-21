package sk.uniza.fri.game;

import javax.swing.JLayeredPane;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public interface IUpdatable {
    void update();
    void repaint();
    void piantLabel(JLayeredPane layeredPane);
}
