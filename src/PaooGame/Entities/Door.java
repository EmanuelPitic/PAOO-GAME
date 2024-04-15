package PaooGame.Entities;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;



public class Door extends StaticEntity {

    /*! \fn public Door(RefLinks refLink, float x, float y)
          \brief Constructorul cu parametri al clasei Door

           \param reflink Referinta catre un obiect "shortcut"
           \param x Pozitia pe axa X a entitatii statice
           \param y Pozitia pe axa Y a entitatii statice
*/
    public Door(RefLinks refLink, float x, float y, boolean solid) {
        super(refLink, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT,"Door", solid);
        bounds.x=0;
        bounds.y=0;
        bounds.width=width;
        bounds.height=height;
    }

    /// actualizarea starii curente
    @Override
    public void Update() {

    }

    @Override
    public void Render(Graphics g) {
        if (isSolid())
            g.drawImage(Assets.door[0],(int)x,(int)y,width,height,null);
        else
            g.drawImage(Assets.door[1],(int)x,(int)y,width,height,null);
    }



    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {this.solid = solid;}
}
