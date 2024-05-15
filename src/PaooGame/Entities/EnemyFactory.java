package PaooGame.Entities;

import PaooGame.RefLinks;

public class EnemyFactory {

    public static Enemy createEnemy(Enemies type, RefLinks refLink, float x, float y, float[] moveX, float[] moveY) {
        switch (type) {
            case Level1Enemy:
                return new Level1Enemy(refLink, x, y, moveX, moveY);
            case Level2Enemy:
                return new Level2Enemy(refLink, x, y, moveX, moveY);
            case Level3Enemy:
                return new Level3Enemy(refLink, x, y, moveX, moveY);
            default:
                throw new IllegalArgumentException("Unknown enemy type: " + type);
        }
    }
}
