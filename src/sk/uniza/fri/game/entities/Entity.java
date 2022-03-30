package sk.uniza.fri.game.entities;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public class Entity implements IEntity {

    private int x;
    private int y;
    // 0 = up
    // 1 = down
    // 2 = left
    // 3 = right
    private int direction;

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setDirection(int direction) {
        if (direction == 0 || direction == 1 || direction == 2 || direction == 3) {
            this.direction = direction;
        } else {
            System.out.println("Wrong direction!");
        }
    }

    @Override
    public int getDirection() {
        return this.direction;
    }
}
