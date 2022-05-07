package sk.uniza.fri.engine.window;

import sk.uniza.fri.engine.menu.Menu;
import sk.uniza.fri.engine.sound.MusicPlayer;
import sk.uniza.fri.game.GameFrame;
import sk.uniza.fri.game.characterCreator.CharacterCreator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Window {

    private final KeyManager keyManager;
    private final JFrame window;
    private JPanel scene;
    private final MusicPlayer musicPlayer;
    private boolean music;

    private final int graphicTileSize;
    private final int maxColTiles;
    private final int maxRowTiles;

    private final Properties prop;

    public Window (int graphicTileSize, int maxColTiles, int maxRowTiles, String name) {
        this.prop = new Properties();
        try (FileInputStream fis = new FileInputStream("frirpg.config")) {
            this.prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.musicPlayer = new MusicPlayer();
        this.music = Integer.parseInt(this.prop.get("frirpg.music").toString()) == 1;

        try {
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!this.music) {
            this.musicPlayer.stop();
        } else {
            this.musicPlayer.start();
        }

        this.graphicTileSize = graphicTileSize;
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
        this.scene = new GameFrame(this.graphicTileSize, this.maxColTiles,
                this.maxRowTiles, this.keyManager, this);
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
        this.scene = new CharacterCreator(this.graphicTileSize * this.maxColTiles,
                this.graphicTileSize * this.maxRowTiles,
                this);

        this.window.add(this.scene);
        this.window.pack();
    }

    public void goToMenu () {
        this.disposeScene();
        this.scene = new Menu(this.graphicTileSize * this.maxColTiles,
                this.graphicTileSize * this.maxRowTiles,
                this, this.musicPlayer, this.prop);
        this.window.add(this.scene);
        this.window.pack();
    }

    private void disposeScene () {
        if (this.scene != null) {
            this.window.remove(this.scene);
        }
    }

    public boolean getMusic() {
        return this.music;
    }

    public void setMusic(boolean m) {
        this.music = m;
    }

    public void saveCfg() {
        try {
            FileWriter writer = new FileWriter(new File("frirpg.config"));
            this.prop.store(writer, "");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
