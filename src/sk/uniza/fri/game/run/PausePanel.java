package sk.uniza.fri.game.run;

import sk.uniza.fri.engine.window.MusicButton;
import sk.uniza.fri.engine.window.Window;
import sk.uniza.fri.game.Frame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class PausePanel {
    private final JPanel panel;

    public PausePanel(int x, int y, int width, int height, Window window) {
        this.panel = new JPanel(null);
        this.panel.setBounds(x, y, width, height);
        this.panel.setSize(width, height);
        Frame f = new Frame(width, height, 0, 0, "PAUSED");
        JLabel label = new JLabel(new ImageIcon(f.getFrame()));
        label.setBounds(x, y, width, height);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.add(label);
        layeredPane.setLayer(label, 0);
        MusicButton musicButton = new MusicButton(window, window.getMusicPlayer(), window.getConfig());
        musicButton.setBounds(0, 0, 50, 50);
        layeredPane.add(musicButton.getMusicButton());
        layeredPane.setLayer(musicButton.getMusicButton(), 0);
        this.panel.add(layeredPane);
        this.panel.repaint();
    }

    public JPanel getPanel() {
        return this.panel;
    }
}
