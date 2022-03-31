package sk.uniza.fri;

import sk.uniza.fri.engine.window.Window;

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
        String name = "FRIRPG";

        Window window = new Window(tilePixels, tileMultiplicator, colTiles, rowTiles, name);
        //window.startGameFrame();
        window.goToCharCreator();
    }
}
