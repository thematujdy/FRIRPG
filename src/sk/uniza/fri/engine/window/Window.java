package sk.uniza.fri.engine.window;

import sk.uniza.fri.game.GameFrame;
import sk.uniza.fri.game.characterCreator.CharacterCreator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Window {

    private final KeyManager keyManager;
    private final JFrame window;
    private JPanel scene;

    private final int graphicTileSize;
    private final int multiplicator;
    private final int maxColTiles;
    private final int maxRowTiles;

    public Window (int graphicTileSize, int multiplicator, int maxColTiles, int maxRowTiles, String name) {

        this.graphicTileSize = graphicTileSize;
        this.multiplicator = multiplicator;
        this.maxColTiles = maxColTiles;
        this.maxRowTiles = maxRowTiles;

        this.window = new JFrame(name);
        this.window.setIconImage(new ImageIcon("fri.png").getImage());
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setLocationRelativeTo(null);
        this.window.setResizable(false);
        this.window.setLocationRelativeTo(null);
        this.window.setVisible(true);

        this.keyManager = new KeyManager();

        this.scene = null;

        this.window.addKeyListener(this.keyManager);
    }

    public void startGameFrame () {
        this.changeScene();
        this.scene = new GameFrame(this.graphicTileSize, this.multiplicator,
                this.maxColTiles, this.maxRowTiles, this.keyManager, this);

        this.window.add(this.scene);
        this.window.pack();
        this.window.setLocationRelativeTo(null);

        GameFrame gameFrame = (GameFrame)this.scene;
        gameFrame.startGame();
    }

    public void goToCharCreator () {
        this.changeScene();
        this.scene = new CharacterCreator(this.graphicTileSize * this.multiplicator * this.maxColTiles,
                this.graphicTileSize * this.multiplicator * this.maxRowTiles,
                this);

        this.window.add(this.scene);
        this.window.pack();
        this.window.setLocationRelativeTo(null);
    }

    private void changeScene () {
        if (this.scene != null) {
            this.window.remove(this.scene);
        }
    }

}
