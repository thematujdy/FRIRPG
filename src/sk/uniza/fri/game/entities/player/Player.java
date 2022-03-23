package sk.uniza.fri.game.entities.player;

import sk.uniza.fri.game.GameFrame;
import sk.uniza.fri.game.entities.Entity;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Player extends Entity {

    private final GameFrame gameFrame;

    private boolean isMoving;

    public Player(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.isMoving = false;
    }

    public void setDefaultTile (int rowTile, int colTile) {
        this.setX(this.gameFrame.getTileX(rowTile));
        this.setY(this.gameFrame.getTileY(colTile));
    }

    public void update () {

    }

    public void paintComponent () {

    }

    private void move (String where) {
        int milisecondsToWait = 200;

        Thread waitUp = new Thread(() -> {
            this.isMoving = true;
            int x = 0;
            while (x < this.gameFrame.getFinalTileSize()) {
                try {
                    Thread.sleep(milisecondsToWait / this.gameFrame.getFinalTileSize());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                switch (where) {
                    case "up" -> this.setX(this.getX() - 1);
                    case "down" -> this.setX(this.getX() + 1);
                    case "left" -> this.setY(this.getY() - 1);
                    case "right" -> this.setY(this.getY() + 1);
                }

                x++;
            }
            this.isMoving = false;
        });

        waitUp.start();
    }

}
