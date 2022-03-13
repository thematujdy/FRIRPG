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
        Window window = new Window("RPG","fri.png");
        window.setVisible(true);
        window.makeUnresizable();
        window.addKeyListener(new sk.uniza.fri.engine.window.KeyListener());
        window.addGameFrame(48, 1, 16, 12);
        window.startGameThread();
    }
}
