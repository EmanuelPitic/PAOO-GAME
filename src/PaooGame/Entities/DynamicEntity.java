package PaooGame.Entities;

import PaooGame.Entities.*;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

/*! \class Creature extends Entity
    \brief Defineste notiunea abstracta de creatura/individ/fiinta din joc.

    Notiunea este definita doar de  viteza de deplasare si distanta cu care trebuie sa se
    miste/deplaseze in urma calculelor.
 */

public abstract class DynamicEntity extends Entity{


    protected float speed;/*!< Viteza unui caracter.*/
    protected float xMove;/*!< Distanta cu care trebuie sa se miste eroul pe axa X.*/
    protected float yMove;/*!< Distanta cu care trebuie sa se miste eroul pe axa Y.*/


    public static final float DEFAULT_SPEED         = 4.0f; /*!< Viteza implicita a unu caracter.*/
    public static final int DEFAULT_DynamicEntity_WIDTH  = 32;   /*!< Latimea implicita a imaginii caracterului.*/
    public static final int DEFAULT_DynamicEntity_HEIGHT = 32;   /*!< Inaltimea implicita a imaginii caracterului.*/



    /*! \fn public DynamicEntity(RefLinks refLink, float x, float y, int width, int height,String name)
               \brief Constructorul cu parametri al clasei DynamicEntity

                \param reflink Referinta catre un obiect "shortcut"
                \param x Pozitia pe axa X a imaginii entitatii
                \param y Pozitia pe axa Y a imaginii entitatii
                \param width Latimea imaginii entitatii
                \param height Inaltimea imaginii entitatii
                \param name Numele Entitatii
   */
    public DynamicEntity(RefLinks refLink, float x, float y, int width, int height,String name) {
        super(refLink, x, y,width,height,name);
        speed=DEFAULT_SPEED;
        xMove=0;
        yMove=0;
    }

/*
Realistic, functia Move() si in special movx si movy vor fi mutate in clasa erou, dar pentru ca le am impelementat asa de la inceput
trebuie rescris mult cod, ramane pentru etapa urmatoare
 */
    public void Move()
    {
        MoveX();
        MoveY();
    }

    /*! \fn public void MoveX()
                   \brief Implementeaza miscarea caracterului pe axa X

       */
    public void MoveX(){
        if(xMove>0){

            // coliziuni dreapta
            int posx=(int)(x+xMove+bounds.x+bounds.width)/ Tile.TILE_WIDTH;
            //System.out.println(x+xMove);
            //coliziuni colt dreapta sus si jos ale dreptunghiului de coliziune
            if(!collisionWithTile(posx,(int)(y+bounds.y)/Tile.TILE_HEIGHT) && !collisionWithTile(posx,(int)(y+ bounds.y+bounds.height)/Tile.TILE_HEIGHT) && x+xMove>=0){
                x+=xMove;
            }

        }else if (xMove<0){
            int posx=(int)(x+xMove+bounds.x)/ Tile.TILE_WIDTH;
            //coliziuni colt stanga sus si jos ale dreptunghiului de coliziune
            //System.out.println(x+xMove);
            if(!collisionWithTile(posx,(int)(y+bounds.y)/Tile.TILE_HEIGHT) && !collisionWithTile(posx,(int)(y+ bounds.y+bounds.height)/Tile.TILE_HEIGHT) && x+xMove>=0){
                x+=xMove;
            }
        }

    }


    /*! \fn public void MoveY()
               \brief Implementeaza miscarea caracterului pe axa Y
   */
    public void MoveY(){
        if(yMove<0){
            int posy=(int)(y+yMove+ bounds.y)/Tile.TILE_HEIGHT;

            //coliziuni colt stanga si dreapta sus ale dreptunghiului de coliziune
            if(!collisionWithTile((int)(x+ bounds.x)/Tile.TILE_WIDTH,posy) &&
                    !collisionWithTile((int)(x+ bounds.x+ bounds.width)/Tile.TILE_WIDTH,posy) && y+yMove>=0){
                //System.out.println(y+yMove);
                y+=yMove;
            }else{
                y=posy*Tile.TILE_HEIGHT+Tile.TILE_HEIGHT- bounds.y;
            }
        }
        else if (yMove>0){
            int posy=(int)(y+yMove+ bounds.y+bounds.height)/Tile.TILE_HEIGHT;

            //coliziuni colt stanga si dreapta jos ale dreptunghiului de coliziune
            if(!collisionWithTile((int)(x+ bounds.x)/Tile.TILE_WIDTH,posy) &&
                    !collisionWithTile((int)(x+ bounds.x+ bounds.width)/Tile.TILE_WIDTH,posy) && y+yMove>=0){
                //System.out.println(y+yMove);
                y+=yMove;
            }else{

                y=posy*Tile.TILE_HEIGHT- bounds.y- bounds.height-1;
                //System.out.println(y);
                //System.out.println(y+yMove);
            }
        }
    }



    /*! \fn protected boolean collisionWithTile(int x,int y)
               \brief Semnaleaza coliziunea cu zidurile labirintului

               \param x Pozitia pe axa X a pt acel tile cu care se verifica coliziunea
               \param y Pozitia pe axa Y a pt acel tile cu care se verifica coliziunea

               Totodata, impelementeaza efectul coliziunii cu obiectele statice cu care intra in contact; updateaza state-ul etc
   */
    protected boolean collisionWithTile(int y,int x){
        if (y < 0  || y > 33)
            return true;
        if (x < 0  || x > 17)
            return true;
        //System.out.println(refLink.GetWorld().getEntityManager().getDoor().getX()/32.+" "+refLink.GetWorld().getEntityManager().getDoor().getY()/32);
        if (refLink.GetWorld().getEntityManager().isDoor() && refLink.GetWorld().getEntityManager().getDoor().isSolid() && refLink.GetWorld().getEntityManager().getDoor().getX()/32 == y && refLink.GetWorld().getEntityManager().getDoor().getY()/32 == x)
        {
            return true;
        }
        if (refLink.GetWorld().getEntityManager().getStartFinishDoor().isSolid() && refLink.GetWorld().getEntityManager().getStartFinishDoor().getX()/32 == y && refLink.GetWorld().getEntityManager().getStartFinishDoor().getY()/32 == x)
        {
            return true;
        }
/*        System.out.println();
        System.out.println("Coordonatele Button: "+refLink.GetWorld().getEntityManager().getButton().getY()/32+";"+refLink.GetWorld().getEntityManager().getButton().getX()/32);
        System.out.println("Coordonate Caracter: "+x+";"+y);
        System.out.println();*/
        if (refLink.GetWorld().getEntityManager().isButton() && !refLink.GetWorld().getEntityManager().getButton().isPressed() && (int)refLink.GetWorld().getEntityManager().getButton().getX()/32 == y && (int)refLink.GetWorld().getEntityManager().getButton().getY()/32 == x)
        {
            //System.out.println("Coordonatele Button: "+refLink.GetWorld().getEntityManager().getButton().getY()/32+";"+refLink.GetWorld().getEntityManager().getButton().getX()/32);
            //System.out.println("Am ajuns aici");
            refLink.GetWorld().getEntityManager().getDoor().setSolid(false);
            refLink.GetWorld().getEntityManager().getButton().setPressed(true);

        }

        if (refLink.GetWorld().getEntityManager().isKey() && !refLink.GetWorld().getEntityManager().getKey().isColected() && (int)refLink.GetWorld().getEntityManager().getKey().getY()/32 == x && (int)refLink.GetWorld().getEntityManager().getKey().getX()/32 == y)
        {
            refLink.GetWorld().getEntityManager().getKey().setColected(true);
            refLink.GetWorld().getEntityManager().getStartFinishDoor().setSolid(false);

        }
        return refLink.GetWorld().GetTile(x,y).isSolid();
    }


    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
    public boolean isStatic(){return false;}

}
