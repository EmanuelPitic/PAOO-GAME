package PaooGame.Entities;

import PaooGame.Graphics.Animations;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends DynamicEntity {
    private Animations animUp, animDown, animRight, animLeft;
    private BufferedImage image;
    private float[] movex;
    private float[] movey;
    int lastMove;
    int index;
    boolean asc;
    public Enemy(RefLinks refLink, float x, float y, float[] moveX, float[] moveY) {
        super(refLink, x, y, DynamicEntity.DEFAULT_DynamicEntity_WIDTH, DynamicEntity.DEFAULT_DynamicEntity_HEIGHT, "Enemy");
        movex = moveX.clone();
        movey= moveY.clone();
        animDown = new Animations(600, Assets.enemyDown);
        animUp = new Animations(600, Assets.enemyUp);
        animLeft = new Animations(600, Assets.enemyLeft);
        animRight = new Animations(600, Assets.enemyRight);

        image = animLeft.getCurrentFrame();
        index = 0;
        asc = true;

        lastMove = 0;

        bounds.x = 8;
        bounds.y = 16;
        bounds.width = 16;
        bounds.height = 16;




    }

    @Override
    protected boolean collisionWithTile(int y, int x)
    {
        return !(refLink.GetWorld().GetTile(x,y).GetId() == 2);
    }



    @Override
    public void Update() {

        if (asc)
        {
            if(index < movex.length)
            {
                //System.out.println("Am ajuns aici");

                if(Math.abs(movex[index]) > Math.abs(movey[index]))
                {
                    if (movex[index] > 0)
                        lastMove = 0;
                    else
                        lastMove = 2;
                }
                else {
                    if (movey[index] > 0)
                        lastMove = 1;
                    else
                        lastMove = 3;
                }

                x+=movex[index];

                y+=movey[index];

                index++;
            }
            else
            {
                asc = false;
                index--;
            }
        }
        else
        {
            if (index > 0)
            {
                x-=movex[index];
                y-=movey[index];
                index--;
                if(Math.abs(movex[index]) > Math.abs(movey[index]))
                {
                    if (movex[index] > 0)
                        lastMove = 2;
                    else
                        lastMove = 0;
                }
                else {
                    if (movey[index] > 0)
                        lastMove = 3;
                    else
                        lastMove = 1;
                }
            }
            else
            {
                asc = true;
                index++;
            }
        }
        //System.out.println(x+" "+refLink.GetWorld().getEntityManager().getHero().getX());
        if ( (int)refLink.GetWorld().getEntityManager().getHero().getX()/16 == (int)x/16 && (int)refLink.GetWorld().getEntityManager().getHero().getY()/16 == (int)y/16)
        {
            refLink.GetGame().GameOver();
            System.out.println("Coliziune");
        }


        animDown.Update();
        animLeft.Update();
        animUp.Update();
        animRight.Update();
        /// System.out.println(x+","+y);

        switch (lastMove)
        {
            case 0: image = animRight.getCurrentFrame();
            break;
            case 1: image = animDown.getCurrentFrame();
            break;
            case 2: image = animLeft.getCurrentFrame();
            break;
            case 3: image = animUp.getCurrentFrame();
        }
    }

    @Override
    public void Render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, width, height, null);
    }
}
