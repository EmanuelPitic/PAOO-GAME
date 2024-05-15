package PaooGame.Entities;

import PaooGame.Graphics.Animations;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.FogOfWar;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Level3Enemy extends Enemy{
    public FogOfWar carFogOfWar;
    public Level3Enemy(RefLinks refLink, float x, float y, float[] moveX, float[] moveY )
    {
        super(refLink, x, y, moveX, moveY, 30);
        animDown = new Animations(1, Assets.policeCarDown);
        animUp = new Animations(1, Assets.policeCarUp);
        animLeft = new Animations(1, Assets.policeCarLeft);
        animRight = new Animations(1, Assets.policeCarRight);
        bounds.x = 7;
        bounds.y = 15;
        bounds.width = 16;
        bounds.height = 16;

    }
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


        // coliziuni dreapta

        //System.out.println(x+xMove);
        //coliziuni colt dreapta sus si jos ale dreptunghiului de coliziun
        Rectangle heroBounds = refLink.GetWorld().getEntityManager().getHero().getCollisionBounds(0,0);
  /*      System.out.println(heroBounds);
        System.out.println("x : " +x +" y : " +y);*/
        //System.out.println();

        if((heroBounds.x+heroBounds.width  >= x -5) && (heroBounds.x-heroBounds.width  <= x + 15) && ((float)heroBounds.y+(float) heroBounds.height - 1 >= y  ) && (heroBounds.y <= y - 1.0) )
        {
            refLink.GetWorld().getEntityManager().getHero().damgaTaken(damage);
            //System.out.println("Colizioneee "+(float)heroBounds.y+'+'+(float)heroBounds.height +'-'+y);
        }
        //System.out.println();

       /* if (  (int)refLink.GetWorld().getEntityManager().getHero().getX()/25 == (int)x/25 && (int)refLink.GetWorld().getEntityManager().getHero().getY()/25 == (int)y/25)
            {
                refLink.GetWorld().getEntityManager().getHero().damgaTaken(damage);
                //System.out.println("Coliziune intre caracter si enemy, trebuie implementat ecranul cu Game Lost");
            }*/

        //System.out.println(x+" "+refLink.GetWorld().getEntityManager().getHero().getX());




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

}
