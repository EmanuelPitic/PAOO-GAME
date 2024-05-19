package PaooGame;

//import PaooGame.Maps.Map;

import PaooGame.Input.KeyManager;
import PaooGame.Input.MouseManager;
import PaooGame.Worlds.World;

/*! \public class RefLinks
    \brief Clasa ce retine o serie de referinte ale unor elemente pentru a fi usor accesibile.
    Altfel ar trebui ca functiile respective sa aiba o serie intreaga de parametri si ar ingreuna programarea.
 */
public class RefLinks
{
    private Game game;          /*!< Referinta catre obiectul Game.*/
    private World world;            /*!< Referinta catre harta curenta.*/

    /*! \fn public RefLinks(Game game)
        \brief Constructorul de initializare al clasei.
        \param game Referinta catre obiectul game.
     */

    public RefLinks(Game game)
    {
        this.game = game;
    }

    /*! \fn public KeyManager GetKeyManager()
        \brief Returneaza referinta catre managerul evenimentelor de tastatura.
     */

    public KeyManager GetKeyManager()
    {
        return game.GetKeyManager();
    }

    /*! \fn public KeyManager GetMouseManager()
        \brief Returneaza referinta catre managerul evenimentelor de mouse.
     */
    public MouseManager GetMouseManager()
    {
        return game.GetMouseManager();
    }

    /*! \fn public Game GetGame()
        \brief Intoarce referinta catre obiectul Game.
     */
    public Game GetGame()
    {
        return game;
    }


    /*! \fn public void SetGame(Game game)
        \brief Seteaza referinta catre un obiect Game.

        \param game Referinta obiectului Game.
     */
    public void SetGame(Game game)
    {
        this.game = game;
    }


    /*! \fn public World GetWorld()
        \brief Intoarce referinta catre harta curenta.
     */

    public World GetWorld()
    {
        return world;
    }



    /*! \fn public void SetWorld(World world)
        \brief Seteaza referinta catre harta curenta.

        \param world Referinta catre harta curenta.
     */
    public void SetWorld(World world)
    {
        this.world = world;
    }


}
