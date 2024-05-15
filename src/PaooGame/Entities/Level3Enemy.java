package PaooGame.Entities;

import PaooGame.Graphics.Animations;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

public class Level3Enemy extends Enemy{
    public Level3Enemy(RefLinks refLink, float x, float y, float[] moveX, float[] moveY )
    {
        super(refLink, x, y, moveX, moveY, 30);
        animDown = new Animations(600, Assets.enemyDown);
        animUp = new Animations(600, Assets.enemyUp);
        animLeft = new Animations(600, Assets.enemyLeft);
        animRight = new Animations(600, Assets.enemyRight);
    }

}
