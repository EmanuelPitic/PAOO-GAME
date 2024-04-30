package PaooGame.Tiles;

public class TileManager
{
    private static final int NO_TILES   = 32;
    public static Tile[] tiles          = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/

    /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
    /// o singura data in memorie
    public TileManager()
    {
        tiles[0] = new Fog(0);
        tiles[1] = new Floor2(1);
        tiles[2] = new Floor3(2);
        tiles[3] = new Floor1(3);
        tiles[5] = new Wall1Tile(5);     /*!< Dala de tip perete1*/

    }

    public Tile getTile(int id)
    {
        return tiles[id];
    }
}