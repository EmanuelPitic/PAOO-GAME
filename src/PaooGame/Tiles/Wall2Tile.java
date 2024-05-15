package PaooGame.Tiles;

import PaooGame.Graphics.Assets;


public class Wall2Tile extends Tile
{

    public Wall2Tile(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.wall2, id,true);
    }
}