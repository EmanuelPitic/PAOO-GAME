package PaooGame.Tiles;

import PaooGame.Graphics.Assets;


public class Wall3Tile extends Tile
{

    public Wall3Tile(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.wall3, id,true);
    }
}