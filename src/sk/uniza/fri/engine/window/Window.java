package sk.uniza.fri.engine.window;

import sk.uniza.fri.engine.menu.Menu;
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
        this.window.setVisible(true);

        this.keyManager = new KeyManager();

        this.goToMenu();

        this.window.setLocationRelativeTo(null);

        this.window.addKeyListener(this.keyManager);
    }

    public void startGameFrame (boolean load) {
        this.disposeScene();
        this.scene = new GameFrame(this.graphicTileSize, this.multiplicator,
                this.maxColTiles, this.maxRowTiles, this.keyManager, this);
        GameFrame gameFrame = (GameFrame)this.scene;
        if (load) {
            gameFrame.loadGame();
        } else {
            gameFrame.newGame();
        }
        this.window.add(this.scene);
        this.window.pack();

        gameFrame.startGame();
    }

    public void goToCharCreator () {
        this.disposeScene();
        this.scene = new CharacterCreator(this.graphicTileSize * this.multiplicator * this.maxColTiles,
                this.graphicTileSize * this.multiplicator * this.maxRowTiles,
                this);

        this.window.add(this.scene);
        this.window.pack();
    }

    public void goToMenu () {
        this.disposeScene();
        this.scene = new Menu(this.graphicTileSize * this.multiplicator * this.maxColTiles,
                this.graphicTileSize * this.multiplicator * this.maxRowTiles,
                this);
        this.window.add(this.scene);
        this.window.pack();
    }

    private void disposeScene () {
        if (this.scene != null) {
            this.window.remove(this.scene);
        }
    }
}
