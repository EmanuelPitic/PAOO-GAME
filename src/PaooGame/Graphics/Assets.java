package PaooGame.Graphics;

import PaooGame.Tiles.Floor1;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage playerLeft;

    public static BufferedImage wall1;
    public static BufferedImage wall2;
    public static BufferedImage floor1;
    public static BufferedImage floor2;
    public static BufferedImage floor3;
    public static BufferedImage[] door;
    public static BufferedImage[] heroLeft;
    public static BufferedImage[] heroRight;
    public static BufferedImage[] heroUp;
    public static BufferedImage[] heroDown;



    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
        SpriteSheet sheet1= new SpriteSheet(ImageLoader.LoadImage("/textures/img.png"));
        wall1 = sheet1.crop(4, 6);
        floor1=sheet1.crop(0,6);
        floor2=sheet1.crop(1,6);

        heroUp=new BufferedImage[30];
        heroDown=new BufferedImage[30];
        heroRight=new BufferedImage[30];
        heroLeft=new BufferedImage[30];

        for (int i = 0; i < 30; i++)
        {
            heroLeft[i]=sheet1.crop(i,0);
        }
        for (int i = 0; i < 30; i++)
        {
            heroDown[i]=sheet1.crop(i,2);
        }
        for (int i = 0; i < 30; i++)
        {
            heroUp[i]=sheet1.crop(i,4);
        }
        for (int i = 0; i < 30; i++)
        {
            heroRight[i]=sheet1.crop(i,7);
        }




            /// Se obtin subimaginile corespunzatoare elementelor necesare.

    }
}
