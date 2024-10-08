package PaooGame.Entities;
import PaooGame.RefLinks;


public abstract class StaticEntity extends Entity {

    protected boolean solid;
    ///public abstract void dissapear();
    /*! \fn public StaticEntity(RefLinks refLink, float x, float y, int width, int height,String name)
               \brief Constructorul cu parametri al clasei StaticEntity
                \param reflink Referinta catre un obiect "shortcut"
                \param x Pozitia pe axa X a imaginii entitatii
                \param y Pozitia pe axa Y a imaginii entitatii
                \param width Latimea imaginii entitatii
                \param height Inaltimea imaginii entitatii
                \param name Denumirea entitatii statice
   */

    public StaticEntity(RefLinks refLink, float x, float y, int width, int height, String name, boolean solid) {
        super(refLink, x, y, width, height,name);
        this.solid = solid;
    }
    public boolean isSolid() {return solid;}


}
