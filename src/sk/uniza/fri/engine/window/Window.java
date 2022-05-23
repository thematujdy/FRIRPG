package sk.uniza.fri.engine.window;

import sk.uniza.fri.engine.menu.Menu;
import sk.uniza.fri.engine.sound.MusicPlayer;
import sk.uniza.fri.game.run.Game;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;

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

    /**
     * Konstruktor triedy Window, slúži na vytvorenie JFrameu a riadenie JPanelov
     * @param graphicTileSize velkosť políčok
     * @param maxColTiles maximálny počet stĺpcov
     * @param maxRowTiles maximálny počet riadkov
     * @param name názov okna
     */
    public Window (int graphicTileSize, int maxColTiles, int maxRowTiles, String name) {
        this.prop = new Properties();
        try (FileInputStream fis = new FileInputStream("frirpg.config")) {
            this.prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.musicPlayer = new MusicPlayer();
        try {
            this.music = Integer.parseInt(this.prop.get("music").toString()) == 1;
        } catch (Exception e) {
            this.prop.setProperty("music", "1");
            e.printStackTrace();
        }


        try {
            Thread.sleep(2000);
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

    /**
     *  Metoda startGameFrame(boolean load) slúži na vytvorenie inštancie triedy Game
     * @param load zistenie či sa načítava hra zo súboru
     */
    public void startGameFrame (boolean load) {
        this.disposeScene();
        Game game = new Game(this.graphicTileSize, this.maxColTiles,
                this.maxRowTiles, this.keyManager, this);
        this.scene = game.getGamePanel();
        this.window.add(this.scene);
        this.window.pack();
        game.startGame();
    }

    /**
     * Metoda goToMenu slúži na vytvorenie a prejdenie do inštancie triedy Menu
     */
    public void goToMenu () {
        this.disposeScene();
        this.scene = new Menu(this.graphicTileSize * this.maxColTiles,
                this.graphicTileSize * this.maxRowTiles,
                this, this.musicPlayer, this.prop);
        this.window.add(this.scene);
        this.window.pack();
    }

    /**
     * Metoda slúži na odstránenie aktuálneho JPanelu
     */
    private void disposeScene () {
        if (this.scene != null) {
            this.window.remove(this.scene);
        }
    }

    /**
     * Metoda getMusic() slúže na navrátenie toho či muzika aktuálne hrá
     * @return music
     */
    public boolean getMusic() {
        return this.music;
    }

    /**
     * Metoda setMusic(boolena m) slúží na nastavenie toho či muzika v aktuálnej dobe hrá
     * @param m boolean
     */
    public void setMusic(boolean m) {
        this.music = m;
    }

    /**
     * Metoda saveCfg() slúži na uloženie config súboru
     */
    public void saveCfg() {
        try {
            FileWriter writer = new FileWriter("frirpg.config");
            this.prop.store(writer, "");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  Metoda getConfig() slúži na navrátenie configu
     * @return prop
     */
    public Properties getConfig() {
        return this.prop;
    }

    /**
     *  Metoda getMusicPlayer() slúži na navrátenie prehrávača hudby, čiže inštancie triedy MusicPlayer
     * @return musicPlayer
     */
    public MusicPlayer getMusicPlayer() {
        return this.musicPlayer;
    }
}
