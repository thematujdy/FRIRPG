package sk.uniza.fri.engine.window;

import sk.uniza.fri.game.GameFrame;

import javax.swing.*;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Window {

    private final KeyManager keyManager;
    private final JFrame window;
    private GameFrame gameFrame;

    public Window () {


        this.window = new JFrame("FRIRPG");
        this.window.setIconImage(new ImageIcon("fri.png").getImage());
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setLocationRelativeTo(null);
        this.window.setResizable(false);
        this.window.setLocationRelativeTo(null);
        this.window.setVisible(true);

        this.keyManager = new KeyManager();

        this.gameFrame = null;


    }

    public void startGameFrame (int graphicTileSize, int multiplicator, int maxColTiles, int maxRowTiles) {
        this.gameFrame = new GameFrame(graphicTileSize, multiplicator, maxColTiles, maxRowTiles, this.keyManager);
        this.window.add(this.gameFrame);
        this.window.pack();

        this.window.addKeyListener(this.keyManager);

        this.gameFrame.startGame();
    }

}
