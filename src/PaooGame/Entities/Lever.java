package PaooGame.Entities;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;



public class Lever extends StaticEntity {

    /*! \fn public Lever(RefLinks refLink, float x, float y)
          \brief Constructorul cu parametri al clasei Lever

           \param reflink Referinta catre un obiect "shortcut"
           \param x Pozitia pe axa X a entitatii statice
           \param y Pozitia pe axa Y a entitatii statice
*/

    private boolean pulled;
    public Lever(RefLinks refLink, float x, float y, boolean solid) {
        super(refLink, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT,"Lever", false);
        bounds.x=0;
        bounds.y=0;
        bounds.width=width;
        bounds.height=height;
        setPulled(false);
    }

    /// actualizarea starii curente
    @Override
    public void Update() {

    }

    @Override
    public void Render(Graphics g) {
        if (!isPulled())
            g.drawImage(Assets.lever[0],(int)x,(int)y,width,height,null);
        else
            g.drawImage(Assets.lever[1],(int)x,(int)y,width,height,null);

    }



    public boolean isPulled() {return pulled;}

    public void setPulled(boolean pulled) {this.pulled = pulled;}
}
