package PaooGame.Graphics;

import PaooGame.Tiles.Floor1;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets {
    /// Referinte catre elementele grafice (dale) utilizate in joc.

    public static BufferedImage wall1;
    public static BufferedImage wall2;
    public static BufferedImage wall3;
    public static BufferedImage fog;// pentru nivelul urmator
    public static BufferedImage floor1;
    public static BufferedImage floor2;
    public static BufferedImage floor3;
    public static BufferedImage floor4;
    public static BufferedImage[] door;
    public static BufferedImage[] heroLeft;
    public static BufferedImage[] heroLeftIdle;
    public static BufferedImage[] heroRight;
    public static BufferedImage[] heroRightIdle;
    public static BufferedImage[] heroUp;
    public static BufferedImage[] heroUpIdle;
    public static BufferedImage[] heroDown;
    public static BufferedImage[] heroDownIdle;
    public static BufferedImage[] button;
    public static BufferedImage[] key;

    public static BufferedImage[] startFinishDoor;

    public static BufferedImage[] enemyLeft;
    public static BufferedImage[] enemyRight;
    public static BufferedImage[] enemyUp;
    public static BufferedImage[] enemyDown;


    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init() {
        SpriteSheet sheet1 = new SpriteSheet(ImageLoader.LoadImage("/textures/new_img.png"));
        SpriteSheet sheet2 = new SpriteSheet(ImageLoader.LoadImage("/textures/closed_door.png"));
        SpriteSheet sheet3 = new SpriteSheet(ImageLoader.LoadImage("/textures/open_door.png"));
        SpriteSheet sheet4 = new SpriteSheet(ImageLoader.LoadImage("/textures/BUTTON.png"));
        SpriteSheet sheet5 = new SpriteSheet(ImageLoader.LoadImage("/textures/cheie.png"));
        SpriteSheet sheet6 = new SpriteSheet(ImageLoader.LoadImage("/textures/enemy.png"));
        SpriteSheet sheet7 = new SpriteSheet(ImageLoader.LoadImage("/textures/StartFinishDoor.png"));
        SpriteSheet sheet8 = new SpriteSheet(ImageLoader.LoadImage("/textures/fog.png"));
        SpriteSheet sheet9 = new SpriteSheet(ImageLoader.LoadImage("/textures/tiles.png"));
        SpriteSheet sheet10 = new SpriteSheet(ImageLoader.LoadImage("/textures/player.png"));

        fog = sheet8.crop(0,0);
        wall1 = sheet9.crop(4, 0);
        wall2 = sheet9.crop(3, 0);
        floor1 = sheet9.crop(0, 0);
        floor2 = sheet9.crop(1, 0);
        floor3= sheet9.crop(8, 0);//enemyfloor
        floor4= sheet9.crop(7, 0);
        wall3 = sheet9.crop(6, 0);
        door = new BufferedImage[2];
        door[0] = sheet2.crop(0, 0);
        door[1] = sheet3.crop(0, 0);

        startFinishDoor = new BufferedImage[2];
        startFinishDoor[0] = sheet7.crop(0, 0);
        startFinishDoor[1] = sheet7.crop(1, 0);


        heroUp = new BufferedImage[30];
        heroUpIdle = new BufferedImage[30];
        heroDown = new BufferedImage[30];
        heroDownIdle = new BufferedImage[30];
        heroRight = new BufferedImage[30];
        heroRightIdle = new BufferedImage[30];
        heroLeft = new BufferedImage[30];
        heroLeftIdle = new BufferedImage[30];

        enemyUp = new BufferedImage[30];
        enemyDown = new BufferedImage[30];
        enemyLeft = new BufferedImage[30];
        enemyRight = new BufferedImage[30];

        button = new BufferedImage[2];
        button[0] = sheet4.crop(0, 0);
        button[1] = sheet4.crop(1, 0);

        key = new BufferedImage[2];
        key[0] = sheet5.crop(0, 0);
        key[1] = sheet5.crop(1, 0);

        for (int i = 0; i < 30; i++) {
            heroLeft[i] = sheet10.crop(i, 0);
            heroLeftIdle[i]=sheet10.crop(i,1);
            enemyUp[i] = sheet6.crop(i, 0);
        }
        for (int i = 0; i < 30; i++) {
            heroDown[i] = sheet10.crop(i, 2);
            heroDownIdle[i]=sheet10.crop(i,3);
            enemyDown[i] = sheet6.crop(i, 1);
        }
        for (int i = 0; i < 30; i++) {
            heroUp[i] = sheet10.crop(i, 4);
            heroUpIdle[i]=sheet10.crop(i,5);
            enemyLeft[i] = sheet6.crop(i, 2);
        }
        for (int i = 0; i < 30; i++) {
            heroRight[i] = sheet10.crop(i, 6);
            heroRightIdle[i]=sheet10.crop(i,7);
            enemyRight[i] = sheet6.crop(i, 3);

        }


        /// Se obtin subimaginile corespunzatoare elementelor necesare.

    }
}
