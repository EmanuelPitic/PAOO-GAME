package PaooGame.Entities;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;



public class Key extends StaticEntity {

    /*! \fn public Door(RefLinks refLink, float x, float y)
          \brief Constructorul cu parametri al clasei Key

           \param reflink Referinta catre un obiect "shortcut"
           \param x Pozitia pe axa X a entitatii statice
           \param y Pozitia pe axa Y a entitatii statice
*/

    private boolean colected;
    public Key(RefLinks refLink, float x, float y, boolean colected) {
        super(refLink, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT,"Key", false);
        bounds.x=0;
        bounds.y=0;
        bounds.width=width;
        bounds.height=height;
        setColected(false);
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
        if (!isColected())
            g.drawImage(Assets.key[0],(int)x,(int)y,width,height,null);
        else
            g.drawImage(Assets.key[1],(int)x,(int)y,width,height,null);

    }




    public boolean isColected() {return colected;}

    public void setColected(boolean colected) {this.colected= colected;}
}
