package PaooGame.Worlds;

import PaooGame.Entities.*;
import PaooGame.Entities.EntityManager;
import PaooGame.Input.KeyManager;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileManager;
import PaooGame.RefLinks;

import java.awt.*;
//import java.awt.image.BufferedImage;

public class World {
    private int width;          /*!< Latimea hartii in numar de dale.*/
    private int height;         /*!< Inaltimea hartii in numar de dale.*/
    private int[][] mapTiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/
    private final int spawnX = 0;/*!< Pozitia de start a jucatorului pe axa X*/
    private final int spawnY = 32;    /*!< Pozitia de start a jucatorului pe harta Y*/
    private KeyManager keyManager;
    private TileManager tileManager;
    private RefLinks refLink;
    private EntityManager entityManager;
    public World(RefLinks refLink) {
        tileManager = new TileManager();
        this.refLink=refLink;
        Hero hero = new Hero(refLink, 0, 32);
        entityManager = new EntityManager(refLink, hero);
        //LEVEL1
        {
            width = 33;
            height = 17;
            mapTiles = new int[][]{
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {3, 3, 3, 3, 3, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 5, 5, 5, 3, 3, 5, 1, 1, 1, 1, 5, 3, 3, 3, 3, 3, 5},
                    {5, 3, 5, 5, 5, 5, 5, 3, 5, 5, 5, 5, 3, 5, 5, 3, 5, 5, 5, 3, 3, 5, 1, 1, 1, 1, 5, 3, 5, 5, 5, 3, 5},
                    {5, 3, 5, 3, 5, 3, 5, 3, 3, 3, 3, 5, 3, 3, 5, 3, 5, 3, 3, 3, 3, 5, 1, 1, 1, 1, 5, 3, 5, 5, 3, 3, 5},
                    {5, 3, 5, 3, 5, 3, 5, 5, 5, 5, 5, 5, 3, 3, 5, 3, 5, 5, 5, 5, 3, 5, 5, 1, 1, 1, 5, 3, 5, 5, 3, 3, 5},
                    {5, 3, 3, 3, 5, 3, 3, 3, 3, 3, 5, 5, 5, 3, 5, 3, 3, 3, 5, 3, 3, 3, 5, 1, 1, 1, 5, 3, 5, 5, 5, 3, 5},
                    {5, 3, 5, 3, 5, 3, 5, 5, 5, 3, 5, 5, 3, 3, 5, 5, 5, 3, 5, 3, 5, 5, 5, 3, 5, 5, 5, 3, 5, 5, 3, 3, 5},
                    {5, 3, 5, 3, 5, 3, 5, 3, 5, 3, 3, 3, 3, 3, 3, 3, 5, 3, 5, 3, 5, 3, 3, 3, 3, 5, 5, 3, 5, 3, 3, 3, 5},
                    {5, 3, 5, 3, 5, 3, 5, 3, 5, 3, 5, 5, 5, 5, 5, 5, 5, 3, 5, 3, 5, 3, 5, 5, 5, 5, 5, 3, 5, 3, 5, 3, 5},
                    {5, 3, 5, 3, 5, 3, 5, 3, 5, 3, 5, 3, 3, 3, 3, 3, 3, 3, 5, 3, 5, 3, 3, 3, 3, 3, 3, 3, 5, 3, 5, 3, 5},
                    {5, 3, 5, 3, 3, 3, 5, 3, 3, 3, 5, 5, 5, 3, 5, 5, 5, 5, 5, 3, 5, 3, 3, 3, 3, 3, 5, 5, 5, 3, 5, 3, 5},
                    {5, 3, 5, 5, 5, 5, 5, 3, 5, 3, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 5, 5, 5, 5, 5, 5, 5, 5, 3, 3, 5, 3, 5},
                    {5, 3, 5, 3, 3, 3, 3, 3, 5, 3, 5, 3, 5, 5, 5, 5, 5, 5, 5, 3, 5, 5, 5, 5, 5, 3, 3, 3, 3, 3, 3, 3, 5},
                    {5, 3, 3, 3, 3, 3, 3, 3, 5, 3, 5, 3, 3, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 5, 5, 5, 5},
                    {5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 3, 3, 5, 5, 3, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {5, 3, 3, 3, 3, 3, 3, 3, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}};

        }
    }

    public Tile GetTile(int x, int y) {

        return tileManager.getTile(mapTiles[x][y]);
    }

    public KeyManager GetKeyManager() {return keyManager;}

    public boolean IsSolid(int x, int y) {
        return tileManager.getTile(mapTiles[x][y]).isSolid();
    }


    /*! \fn public  void Update()
            \brief Actualizarea hartii in functie de evenimente (un obiect a fost colectat)
         */
    public void Update() {
        entityManager.Update();
    }


    /*! \fn public void Render(Graphics g)
         \brief Functia de desenare a hartii.

         \param g Contextl grafic in care se realizeaza desenarea.
      */
    public void Render(Graphics g) {


        /// randare tiles
        for (int x = 0; x < height; x++){
            for (int y = 0; y < width; y++) {
            GetTile(x, y).Draw(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
            }
        //System.out.println();
        }
        entityManager.Render(g);

    }
}