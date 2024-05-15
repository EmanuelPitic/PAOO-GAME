package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

public class Fog extends Tile
{

    public Fog(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.fog, id,false);
    }
}