package sk.uniza.fri.game.entities;

/**
 * 12. 3. 2022 - 12:07
 *
 * @author matus
 */
public interface IEntity {
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);
    void setDirection(int direction);
    int getDirection();
}
