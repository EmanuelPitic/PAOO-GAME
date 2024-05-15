package PaooGame.Entities;

import PaooGame.Graphics.Animations;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \public class Hero extends Creature
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea
        inventory
        animatiile
 */

public class Hero extends DynamicEntity {

    int health = 30;
    //private PlayerState playerState;
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    private String direction; //referinta catre pozitia curenta a eroului
    private Animations aDown, aUp, aRight, aLeft;  /*!< Animatiile prezente la deplasarea eroului.*/
    private Animations aDownIdle, aUpIdle, aRightIdle, aLeftIdle;

    /*! \fn public Hero(RefLinks refLink, float x, float y)
               \brief Constructorul cu parametri al clasei DynamicEntity

                \param reflink Referinta catre un obiect "shortcut"
                \param x Pozitia pe axa X a imaginii eroului
                \param y Pozitia pe axa Y a imaginii eroului
   */
    public Hero(RefLinks refLink, float x, float y) {
        super(refLink, x, y, DynamicEntity.DEFAULT_DynamicEntity_WIDTH, DynamicEntity.DEFAULT_DynamicEntity_HEIGHT, "Hero");

        bounds.x = 7;
        bounds.y = 15;
        bounds.width = 16;
        bounds.height = 16;

        aUp = new Animations(600, Assets.heroUp);
        aUpIdle = new Animations(600, Assets.heroUpIdle);
        aDown = new Animations(600, Assets.heroDown);
        aDownIdle = new Animations(10, Assets.heroDownIdle);
        aRight = new Animations(600, Assets.heroRight);
        aRightIdle = new Animations(600, Assets.heroRightIdle);
        aLeft = new Animations(600, Assets.heroLeft);
        aLeftIdle = new Animations(600, Assets.heroLeftIdle);


        image = aDown.getCurrentFrame();
        direction = "Down";

    }


    /// actualizarea pozitiei curente , precum si a animatiilor
    @Override
    public void Update() {
        if (health == 0)
            System.out.println("GAME OVER U ARE DED");
        GetInput();
        if(xMove!=0 || yMove!=0)
            {
                Move();

            }
        else
        {
            switch(direction)
            {
                case "Up": aUpIdle.Update();
                image = aUpIdle.getCurrentFrame();
                break;
                case "Down": aDownIdle.Update();
                image = aDownIdle.getCurrentFrame();
                break;
                case "Left": aLeftIdle.Update();
                image = aLeftIdle.getCurrentFrame();
                break;
                case "Right": aRightIdle.Update();
                image = aRightIdle.getCurrentFrame();
                break;

            }
        }
        refLink.GetWorld().getFogOfWar().setPlayerPosition((int)x/32,(int) y/32);
        aDown.Update();
        aDownIdle.Update();
        aLeft.Update();
        aLeftIdle.Update();
        aUp.Update();
        aUpIdle.Update();
        aRight.Update();
        aRightIdle.Update();
        /// System.out.println(x+","+y);

        if (refLink.GetKeyManager().left) {
            image = aLeft.getCurrentFrame();
            direction = "Left";
        }
        if (refLink.GetKeyManager().right) {
            image = aRight.getCurrentFrame();
            direction = "Right";
        }
        if (refLink.GetKeyManager().up) {
            image = aUp.getCurrentFrame();
            direction = "Up";
        }
        if (refLink.GetKeyManager().down) {
            image = aDown.getCurrentFrame();
            direction = "Down";
        }
    }


    /*! \fn  private void GetInput()
                      \brief Asteapta input de la tastatura pentru a actualiza pozitia eroului pe harta
          */
    private void GetInput() {
        xMove = 0;
        yMove = 0;

        if (refLink.GetKeyManager().up)
            yMove = -speed;
        if (refLink.GetKeyManager().down)
            yMove += speed;
        if (refLink.GetKeyManager().left)
            xMove -= speed;
        if (refLink.GetKeyManager().right)
            xMove += speed;
        // System.out.println("xmove: " + xMove+"; ymove: " + yMove);

    }


    /*! \fn public void Render()
                      \brief Deseneaza starea curenta a eroului pe harta.

                      \param g Contextul grafic in care se realizeaza desenarea.
    */
    @Override
    public void Render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, width, height, null);
    }
    @Override
    public boolean isStatic(){
        return false;
    }
    public void damgaTaken(int damage){
        health -= damage;
        System.out.println(health);
    }

}
