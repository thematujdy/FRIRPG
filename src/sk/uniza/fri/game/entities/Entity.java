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
    private int hp;
    private int power;

    /**
     * Metóda getX navráti aktuálne x
     * @return int x
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * Metóda getY navráti aktuálne y
     * @return int y
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * Metóda setX nastaví aktuálne x
     * @param x int x
     */
    @Override
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Metóda setY nastaví aktuálne y
     * @param y int y
     */
    @Override
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Metóda setDirection nastaví smer podla vstupného intu
     * @param direction int smer
     */
    @Override
    public void setDirection(int direction) {
        if (direction == 0 || direction == 1 || direction == 2 || direction == 3) {
            this.direction = direction;
        } else {
            System.out.println("Wrong direction!");
        }
    }

    /**
     * Metóda getDirection navráti smer
     * @return int smer
     */
    @Override
    public int getDirection() {
        return this.direction;
    }

    /**
     * Metóda getHP vráti hodnotu života
     * @return int hp
     */
    @Override
    public int getHP() {
        return this.hp;
    }

    /**
     * Metóda addHP pridá život
     * @param hp int hp
     */
    @Override
    public void addHP(int hp) {
        this.hp += hp;
    }

    /**
     * Metóda getPower vráti hodnotu sily
     * @return int power
     */
    @Override
    public int getPower() {
        return this.power;
    }

    /**
     * Metóda addPower pridá silu
     * @param power int power
     */
    @Override
    public void addPower(int power) {
        this.power += power;
    }

    /**
     * Metóda setHP nastaví presné životy
     * @param hp int hp
     */
    public void setHP(int hp) {
        this.hp = hp;
    }
}
