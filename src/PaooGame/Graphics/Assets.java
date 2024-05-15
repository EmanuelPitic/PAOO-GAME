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
    public static BufferedImage[] masterDoor;
    public static BufferedImage[] lever;

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
    public static BufferedImage finalKey;

    public static BufferedImage[] startFinishDoor;

    public static BufferedImage[] enemyLeft;
    public static BufferedImage[] enemyRight;
    public static BufferedImage[] enemyUp;
    public static BufferedImage[] enemyDown;

    public static BufferedImage[] dogLeft;
    public static BufferedImage[] dogRight;
    public static BufferedImage[] dogUp;
    public static BufferedImage[] dogDown;

    public static BufferedImage[] policeCarDown;
    public static BufferedImage[] policeCarUp;
    public static BufferedImage[] policeCarLeft;
    public static BufferedImage[] policeCarRight;


    public static BufferedImage menuBackground;
    public static BufferedImage thiefImage;
    public static BufferedImage gloveImage;

    public static BufferedImage[] pause_btn;
    public static BufferedImage[] help_btn;
    public static BufferedImage[] quit_btn;
    public static BufferedImage[] reload;
    public static BufferedImage[] resume;
    public static BufferedImage[] back_btn;
    public static BufferedImage[] loadGame_btn;
    public static BufferedImage[] newGame_btn;
    public static BufferedImage[] settings_btn;
    public static BufferedImage[] musicOn_btn;
    public static BufferedImage[] musicOff_btn;

    public static BufferedImage ar_UP;
    public static BufferedImage ar_DOWN;
    public static BufferedImage ar_RIGHT;
    public static BufferedImage ar_LEFT;


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
        SpriteSheet sheet11 = new SpriteSheet(ImageLoader.LoadImage("/textures/level2Enemy.png"));
        SpriteSheet buttons= new SpriteSheet(ImageLoader.LoadImage("/textures/buttons.png"));
        SpriteSheet resumeSprite= new SpriteSheet(ImageLoader.LoadImage("/textures/resume.png"));
        SpriteSheet arrows = new SpriteSheet(ImageLoader.LoadImage("/textures/arrows.png"));
        SpriteSheet masterDoorSprite = new SpriteSheet(ImageLoader.LoadImage("/textures/masterDoor1.png"));
        SpriteSheet leverSprite = new SpriteSheet(ImageLoader.LoadImage("/textures/lever.png"));
        SpriteSheet policeCarSprite = new SpriteSheet(ImageLoader.LoadImage("/textures/policeCar.png"));

        fog = sheet8.crop(0,0);

        wall1 = sheet9.crop(4, 0);
        wall2 = sheet9.crop(3, 0);
        wall3 = sheet9.crop(6, 0);

        floor1 = sheet9.crop(0, 0);
        floor2 = sheet9.crop(1, 0);
        floor3= sheet9.crop(8, 0);//enemyfloor
        floor4= sheet9.crop(7, 0);

        door = new BufferedImage[2];
        door[0] = sheet2.crop(0, 0);
        door[1] = sheet3.crop(0, 0);

        finalKey=ImageLoader.LoadImage("/textures/finalKey.png");


        masterDoor = new BufferedImage[2];
        masterDoor[0] = masterDoorSprite.crop(0,0);
        masterDoor[1] = masterDoorSprite.crop(1,0);

        lever = new BufferedImage[2];
        lever[0] = leverSprite.crop(0,0);
        lever[1] = leverSprite.crop(1,0);

        menuBackground=ImageLoader.LoadImage("/textures/bgg.png");
        thiefImage=ImageLoader.LoadImage("/textures/thiefImage.png");
        gloveImage=ImageLoader.LoadImage("/textures/gloveImage.png");

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

        dogLeft = new BufferedImage[3];
        dogRight = new BufferedImage[3];
        dogUp = new BufferedImage[3];
        dogDown = new BufferedImage[3];

        dogDown[0] = sheet11.crop(0,0);
        dogDown[1] = sheet11.crop(1, 0);
        dogDown[2] = sheet11.crop(2, 0);

        dogLeft[0] = sheet11.crop(3, 0);
        dogLeft[1] = sheet11.crop(4, 0);
        dogLeft[2] = sheet11.crop(5, 0);

        dogRight[0]= sheet11.crop(6, 0);
        dogRight[1]= sheet11.crop(7, 0);
        dogRight[2] = sheet11.crop(8,0);

        dogUp[0] = sheet11.crop(9,0);
        dogUp[1] = sheet11.crop(10,0);
        dogUp[2] = sheet11.crop(11,0);

        policeCarDown = new BufferedImage[2];
        policeCarUp = new BufferedImage[2];
        policeCarLeft = new BufferedImage[2];
        policeCarRight = new BufferedImage[2];

        policeCarDown[0] = policeCarSprite.crop(0,0);
        policeCarDown[1] = policeCarSprite.crop(1,0);

        policeCarUp[0] = policeCarSprite.crop(2,0);
        policeCarUp[1] = policeCarSprite.crop(3,0);

        policeCarLeft[0] = policeCarSprite.crop(4,0);
        policeCarLeft[1] = policeCarSprite.crop(5,0);

        policeCarRight[0] = policeCarSprite.crop(6,0);
        policeCarRight[1] = policeCarSprite.crop(7,0);




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

        resume=new BufferedImage[2];
        pause_btn=new BufferedImage[2];
        help_btn=new BufferedImage[2];
        quit_btn=new BufferedImage[2];
        back_btn=new BufferedImage[2];
        reload=new BufferedImage[2];
        loadGame_btn=new BufferedImage[2];
        newGame_btn=new BufferedImage[2];
        settings_btn=new BufferedImage[2];
        musicOn_btn=new BufferedImage[2];
        musicOff_btn=new BufferedImage[2];

        resume[0] = resumeSprite.Crop(0,0,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);
        resume[1] = resumeSprite.Crop(4,0,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);

        pause_btn[0]=buttons.Crop(0,0,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);
        pause_btn[1]=buttons.Crop(4,0,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);

        help_btn[0]=buttons.Crop(0,1,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);
        help_btn[1]=buttons.Crop(4,1,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);

        quit_btn[0]= buttons.Crop(0,2,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);
        quit_btn[1]= buttons.Crop(4,2,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);

        back_btn[0]= buttons.Crop(0,3,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);
        back_btn[1]= buttons.Crop(4,3,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);

        newGame_btn[0]= buttons.Crop(0,4,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);
        newGame_btn[1]= buttons.Crop(4,4,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);

        loadGame_btn[0]= buttons.Crop(0,5,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);
        loadGame_btn[1]= buttons.Crop(4,5,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);

        settings_btn[0]=buttons.Crop(0,6,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);
        settings_btn[1]=buttons.Crop(4, 6,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);

        musicOn_btn[0]= buttons.Crop(0,7,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);
        musicOn_btn[1]= buttons.Crop(4,7,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);

        musicOff_btn[0]=buttons.Crop(0,8,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);
        musicOff_btn[1]=buttons.Crop(4,8,SpriteSheet.tileWidth*4,SpriteSheet.tileHeight);

        ar_RIGHT=arrows.Crop(0,0,SpriteSheet.tileWidth*2,SpriteSheet.tileHeight*2);
        ar_UP=arrows.Crop(2,0,SpriteSheet.tileWidth*2,SpriteSheet.tileHeight*2);
        ar_LEFT=arrows.Crop(4,0,SpriteSheet.tileWidth*2,SpriteSheet.tileHeight*2);
        ar_DOWN=arrows.Crop(6,0,SpriteSheet.tileWidth*2,SpriteSheet.tileHeight*2);


        /// Se obtin subimaginile corespunzatoare elementelor necesare.

    }
}
