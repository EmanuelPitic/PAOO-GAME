package PaooGame.Entities;

import PaooGame.Graphics.Animations;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

public class Level1Enemy extends Enemy{
    public Level1Enemy(RefLinks refLink, float x, float y, float[] moveX, float[] moveY )
    {
        super(refLink, x, y, moveX, moveY, 10);
        animDown = new Animations(600, Assets.enemyDown);
        animUp = new Animations(600, Assets.enemyUp);
        animLeft = new Animations(600, Assets.enemyLeft);
        animRight = new Animations(600, Assets.enemyRight);
    }

}
