package PaooGame.Entities;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;



public class Button extends StaticEntity {

    /*! \fn public Door(RefLinks refLink, float x, float y)
          \brief Constructorul cu parametri al clasei Door

           \param reflink Referinta catre un obiect "shortcut"
           \param x Pozitia pe axa X a entitatii statice
           \param y Pozitia pe axa Y a entitatii statice
*/

    private boolean pressed;
    public Button(RefLinks refLink, float x, float y, boolean solid) {
        super(refLink, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT,"Button", false);
        bounds.x=0;
        bounds.y=0;
        bounds.width=width;
        bounds.height=height;
        setPressed(false);
    }

    /// actualizarea starii curente
    @Override
    public void Update() {

    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a starii curente.

        \param g Contextul grafic in care se realizeaza desenarea.
     */
    @Override
    public void Render(Graphics g) {
        if (!isPressed())
            g.drawImage(Assets.button[0],(int)x,(int)y,width,height,null);
        else
            g.drawImage(Assets.button[1],(int)x,(int)y,width,height,null);
    }


    /*! \fn public void die()
        \brief Defineste notiunea de distrugere a entitatii pt obiecte de tip linie de finish
*/
    @Override
    public void dissapear()
    {

    }

    public boolean isPressed() {return pressed;}

    public void setPressed(boolean pressed) {this.pressed = pressed;}
}
