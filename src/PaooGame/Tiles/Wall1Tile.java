package PaooGame.Tiles;

import PaooGame.Graphics.Assets;


public class Wall1Tile extends Tile
{

    public Wall1Tile(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.wall1, id,true);
    }
}