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
        int colTiles = 15;
        int rowTiles = 13;
        String name = "FRIRPG";

        new Window(tilePixels, colTiles, rowTiles, name);
    }
}
