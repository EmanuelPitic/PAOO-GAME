package PaooGame.Entities;

import PaooGame.Graphics.Animations;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends DynamicEntity {
    private boolean damageGiven;
    private int damageCounter;
    public Animations animUp, animDown, animRight, animLeft;
    public BufferedImage image;
    public float[] movex;
    public float[] movey;
    int lastMove;
    int index;
    boolean asc;
    int damage;
    int lastx, lasty; //pentru coliziune
    int lastHit = 0;


    public Enemy(RefLinks refLink, float x, float y, float[] moveX, float[] moveY, int damage) {
        super(refLink, x, y, DynamicEntity.DEFAULT_DynamicEntity_WIDTH, DynamicEntity.DEFAULT_DynamicEntity_HEIGHT, "Enemy");
        movex = moveX.clone();
        movey= moveY.clone();
        animDown = new Animations(600, Assets.enemyDown);
        animUp = new Animations(600, Assets.enemyUp);
        animLeft = new Animations(600, Assets.enemyLeft);
        animRight = new Animations(600, Assets.enemyRight);
        this.damage = damage;
        image = animLeft.getCurrentFrame();
        index = 0;
        asc = true;
        damageGiven = false;
        lastMove = 0;
        damageCounter = 0;
        bounds.x = 8;
        bounds.y = 16;
        bounds.width = 16;
        bounds.height = 16;
        lastx= (int)x/16;
        lasty= (int)y/16; //calculeaza pentru coliziune
    }

    @Override
    protected boolean collisionWithTile(int y, int x)
    {
        return !(refLink.GetWorld().GetTile(x,y).GetId() == 2);
    }



    @Override
    public void Update() {
        //aici ne ocupam cu parcurgerea traseului predefinit si cu verificarea interactiunii cu eroul
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

//ii stupid dar merge, cum se face damage-ul la erou
        if(lastx != (int)x/32 ||  lasty != (int)y/32 || lastHit==0 || lastHit>=refLink.GetGame().getPlayState2().countdown+2){
            lastx = (int)x/32;
            lasty = (int)y/32;
            if (  (int)refLink.GetWorld().getEntityManager().getHero().getX()/16 == (int)x/16 && (int)refLink.GetWorld().getEntityManager().getHero().getY()/16 == (int)y/16)
            {
                refLink.GetWorld().getEntityManager().getHero().damgaTaken(damage);
                lastHit=refLink.GetGame().getPlayState2().countdown;
            }
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

    public void setIndex(int index){
        this.index = index;
    }
    public void setLastMove(int lastMove){
        this.lastMove = lastMove;
    }

    public void setAsc(boolean asc){
        this.asc = asc;
    }

    @Override
    public void Render(Graphics g) {

        g.drawImage(image, (int) x, (int) y, width, height, null);


    }

    @Override
    public String toString()
    {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append(x).append('/').append(y).append('/');
        toReturn.append(index).append('/').append(asc).append('/').append(lastMove).append('/');
        return toReturn.toString();
    }

}
