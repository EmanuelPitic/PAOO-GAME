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


    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    private Animations aDown, aUp, aRight, aLeft;  /*!< Animatiile prezente la deplasarea eroului.*/

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
        aDown = new Animations(600, Assets.heroDown);
        aRight = new Animations(600, Assets.heroRight);
        aLeft = new Animations(600, Assets.heroLeft);


        image = aDown.getCurrentFrame();

    }


    /// actualizarea pozitiei curente , precum si a animatiilor
    @Override
    public void Update() {
        GetInput();
        Move();

        aDown.Update();
        aLeft.Update();
        aUp.Update();
        aRight.Update();
       /// System.out.println(x+","+y);

        if (refLink.GetKeyManager().left) {
            image = aLeft.getCurrentFrame();
        }
        if (refLink.GetKeyManager().right) {
            image = aRight.getCurrentFrame();
        }
        if (refLink.GetKeyManager().up) {
            image = aUp.getCurrentFrame();
        }
        if (refLink.GetKeyManager().down) {
            image = aDown.getCurrentFrame();
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

    }


    /*! \fn public void Render()
                      \brief Deseneaza starea curenta a eroului pe harta.

                      \param g Contextul grafic in care se realizeaza desenarea.
    */
    @Override
    public void Render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, width, height, null);
    }

}
