package sk.uniza.fri;

import sk.uniza.fri.engine.window.KeyManager;
import sk.uniza.fri.game.GameFrame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


/**
 * Created by IntelliJ IDEA.
 * User: matus
 * Date: 12. 3. 2022
 * Time: 12:07
 */
public class Main {

    public static void main(String[] args) {
        int tilePixels = 48;
        int tileMultiplicator = 1;
        int colTiles = 16;
        int rowTiles = 12;

        //Window JFrame
        JFrame window = new JFrame("FRIRPG");
        window.setIconImage(new ImageIcon("fri.png").getImage());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        KeyManager keyManager = new KeyManager();

        GameFrame gameFrame = new GameFrame(tilePixels, tileMultiplicator, colTiles, rowTiles, keyManager);

        window.add(gameFrame);
        window.pack();

        window.addKeyListener(keyManager);

        gameFrame.startGame();
    }
}
