package PaooGame.Worlds;

import PaooGame.Entities.*;
import PaooGame.Entities.Button;
import PaooGame.Entities.EntityManager;
import PaooGame.Graphics.FogOfWar;
import PaooGame.Input.KeyManager;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileManager;
import PaooGame.RefLinks;
import PaooGame.Level.Level;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
//import java.awt.image.BufferedImage;

public class World {
    private final int spawnX = 0;/*!< Pozitia de start a jucatorului pe axa X*/
    private final int spawnY = 32;    /*!< Pozitia de start a jucatorului pe harta Y*/
    private final int width;          /*!< Latimea hartii in numar de dale.*/
    private final int height;         /*!< Inaltimea hartii in numar de dale.*/
    private final TileManager tileManager;
    private final RefLinks refLink;
    private final EntityManager entityManager;
    private final FogOfWar fogOfWar;
    boolean isFogOfWar = false;
    private int[][] mapTiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/
    private KeyManager keyManager;
    private float transparency;

    public World(RefLinks refLink) {
        tileManager = new TileManager();
        this.refLink = refLink;
        width = 33;
        height = 17;
        Hero hero = new Hero(refLink, 32, 32);
        entityManager = new EntityManager(refLink, hero);
        this.fogOfWar = new FogOfWar(width, height, 1, 1);
        switch (Level.getInstance().getLevelNr()) {
            case 1:
            {

                //Hero hero = new Hero(refLink, 32, 32);
                isFogOfWar = true;
                //hero.setSpeed(8);
                Door door = new Door(refLink, 864, 96, true);
                Button button = new Button(refLink, 896, 416, false);
                MasterDoor masterDoor = new MasterDoor(refLink, 672, 480, true);
                Key key = new Key(refLink, 864, 64, false);
                StartFinishDoor startFinishDoor = new StartFinishDoor(refLink, 0, 32, true);
                entityManager.addEntity(startFinishDoor);
                entityManager.addEntity(door);
                entityManager.addEntity(button);
                entityManager.addEntity(key);
                entityManager.addEntity(masterDoor);
                Lever lever1 = new Lever(refLink, 608, 64, false);
                Lever lever2 = new Lever(refLink, 480, 64, false);
                Lever lever3 = new Lever(refLink, 416, 480, false);
                entityManager.addEntity(lever1);
                entityManager.addEntity(lever2);
                entityManager.addEntity(lever3);
                transparency = 0.5F;
                //harta este aici

                try {
                    mapTiles = new int[height][width];

                    File fisier = new File("map3.txt");
                    FileReader fr = new FileReader(fisier);
                    BufferedReader br = new BufferedReader(fr);

                    String linie;
                    int row = 0;

                    // Citirea fiecărei linii și conversia într-o matrice de întregi
                    while (row < 17 && (linie = br.readLine()) != null) {
                        String[] elemente = linie.split(" ");
                        for (int col = 0; col < elemente.length; col++) {
                            mapTiles[row][col] = Integer.parseInt(elemente[col]);
                        }
                        row++;
                    }
                    linie = br.readLine();//number of enemies
                    int numberOfEnemies = Integer.parseInt(linie);
                    for (int i = 0; i < numberOfEnemies; i++) {
                        linie = br.readLine();//TypeOfEnemy
                        int typeOfEnemy = Integer.parseInt(linie);
                        Enemies enemyType = Enemies.values()[typeOfEnemy];
                        linie = br.readLine(); //x and y spawn
                        String[] intStrings = linie.trim().split("\\s+"); // Split by whitespace
                        int x = Integer.parseInt(intStrings[0]);
                        int y = Integer.parseInt(intStrings[1]);
                        linie = br.readLine();//numberOfMoves
                        int numberOfMoves = Integer.parseInt(linie);
                        float[] movesX = new float[numberOfMoves];
                        float[] movesY = new float[numberOfMoves];
                        linie = br.readLine();
                        int j = 0;
                        String[] floatStrings = linie.trim().split("\\s+"); // Split by whitespace
                        for (String floatString : floatStrings) {
                            try {
                                if (j < movesX.length - 1) {
                                    float value = Float.parseFloat(floatString);
                                    movesX[j++] = value;
                                }

                            } catch (NumberFormatException e) {
                                System.out.println(e);
                                // Handle the case where the string cannot be parsed to a float
                            }
                        }

                        linie = br.readLine();
                        j = 0;
                        String[] floatStrings1 = linie.trim().split("\\s+"); // Split by whitespace
                        for (String floatString : floatStrings1) {
                            try {
                                float value = Float.parseFloat(floatString);
                                movesY[j++] = value;
                            } catch (NumberFormatException e) {
                                e.printStackTrace(); // Handle the case where the string cannot be parsed to a float
                            }
                        }

                        Enemy auxiliar = EnemyFactory.createEnemy(enemyType, refLink, x, y, movesX, movesY);
                        entityManager.addEntity(auxiliar);
                    }

                    br.close();
                    fr.close();
                } catch (Exception e) {
                    System.out.println(e);
                    System.exit(1);
                }

            }


            break;
            case 2: {
                //tileManager = new TileManager();
                // this.refLink = refLink;
                // Hero hero = new Hero(refLink, 32, 32);
                isFogOfWar = true;
                transparency = 0.25F;
                // entityManager = new EntityManager(refLink, hero);
                Door door = new Door(refLink, 704, 384, true);
                Button button = new Button(refLink, 514, 352, false);
                Key key = new Key(refLink, 672, 384, false);
                StartFinishDoor startFinishDoor = new StartFinishDoor(refLink, 0, 32, true);
                entityManager.addEntity(startFinishDoor);
                entityManager.addEntity(door);
                entityManager.addEntity(button);
                entityManager.addEntity(key);
                //harta este aici
                //width = 33;
                // height = 17;
                try {
                    mapTiles = new int[height][width];

                    File fisier = new File("map2.txt");
                    FileReader fr = new FileReader(fisier);
                    BufferedReader br = new BufferedReader(fr);

                    String linie;
                    int row = 0;

                    // Citirea fiecărei linii și conversia într-o matrice de întregi
                    while (row < 17 && (linie = br.readLine()) != null) {
                        String[] elemente = linie.split(" ");
                        for (int col = 0; col < elemente.length; col++) {
                            mapTiles[row][col] = Integer.parseInt(elemente[col]);
                        }
                        row++;
                    }
                    linie = br.readLine();//number of enemies
                    int numberOfEnemies = Integer.parseInt(linie);
                    for (int i = 0; i < numberOfEnemies; i++) {
                        linie = br.readLine();//TypeOfEnemy
                        int typeOfEnemy = Integer.parseInt(linie);
                        Enemies enemyType = Enemies.values()[typeOfEnemy];
                        linie = br.readLine(); //x and y spawn
                        String[] intStrings = linie.trim().split("\\s+"); // Split by whitespace
                        int x = Integer.parseInt(intStrings[0]);
                        int y = Integer.parseInt(intStrings[1]);
                        linie = br.readLine();//numberOfMoves
                        int numberOfMoves = Integer.parseInt(linie);
                        float[] movesX = new float[numberOfMoves];
                        float[] movesY = new float[numberOfMoves];
                        linie = br.readLine();
                        int j = 0;
                        String[] floatStrings2 = linie.trim().split("\\s+"); // Split by whitespace
                        for (String floatString : floatStrings2) {
                            try {
                                float value = Float.parseFloat(floatString);
                                movesX[j++] = value;
                            } catch (NumberFormatException e) {
                                e.printStackTrace(); // Handle the case where the string cannot be parsed to a float
                            }
                        }


                        linie = br.readLine();
                        j = 0;
                        String[] floatStrings3 = linie.trim().split("\\s+"); // Split by whitespace
                        for (String floatString : floatStrings3) {
                            try {
                                float value = Float.parseFloat(floatString);
                                movesY[j++] = value;
                            } catch (NumberFormatException e) {
                                e.printStackTrace(); // Handle the case where the string cannot be parsed to a float
                            }
                        }

                        Enemy auxiliar = EnemyFactory.createEnemy(enemyType, refLink, x, y, movesX, movesY);
                        entityManager.addEntity(auxiliar);
                    }

                    br.close();
                    fr.close();
                } catch (Exception e) {
                    System.out.println(e);
                    System.exit(1);
                }
                // this.fogOfWar = new FogOfWar(width, height, 1, 1);
            }
            break;
            case 3:
            {

                //Hero hero = new Hero(refLink, 32, 32);
                isFogOfWar = true;
                //hero.setSpeed(8);
                Door door = new Door(refLink, 864, 96, true);
                Button button = new Button(refLink, 896, 416, false);
                MasterDoor masterDoor = new MasterDoor(refLink, 672, 480, true);
                Key key = new Key(refLink, 864, 64, false);
                StartFinishDoor startFinishDoor = new StartFinishDoor(refLink, 0, 32, true);
                entityManager.addEntity(startFinishDoor);
                entityManager.addEntity(door);
                entityManager.addEntity(button);
                entityManager.addEntity(key);
                entityManager.addEntity(masterDoor);
                Lever lever1 = new Lever(refLink, 608, 64, false);
                Lever lever2 = new Lever(refLink, 480, 64, false);
                Lever lever3 = new Lever(refLink, 416, 480, false);
                entityManager.addEntity(lever1);
                entityManager.addEntity(lever2);
                entityManager.addEntity(lever3);
                transparency = 0.5F;
                //harta este aici

                try {
                    mapTiles = new int[height][width];

                    File fisier = new File("map3.txt");
                    FileReader fr = new FileReader(fisier);
                    BufferedReader br = new BufferedReader(fr);

                    String linie;
                    int row = 0;

                    // Citirea fiecărei linii și conversia într-o matrice de întregi
                    while (row < 17 && (linie = br.readLine()) != null) {
                        String[] elemente = linie.split(" ");
                        for (int col = 0; col < elemente.length; col++) {
                            mapTiles[row][col] = Integer.parseInt(elemente[col]);
                        }
                        row++;
                    }
                    linie = br.readLine();//number of enemies
                    int numberOfEnemies = Integer.parseInt(linie);
                    for (int i = 0; i < numberOfEnemies; i++) {
                        linie = br.readLine();//TypeOfEnemy
                        int typeOfEnemy = Integer.parseInt(linie);
                        Enemies enemyType = Enemies.values()[typeOfEnemy];
                        linie = br.readLine(); //x and y spawn
                        String[] intStrings = linie.trim().split("\\s+"); // Split by whitespace
                        int x = Integer.parseInt(intStrings[0]);
                        int y = Integer.parseInt(intStrings[1]);
                        linie = br.readLine();//numberOfMoves
                        int numberOfMoves = Integer.parseInt(linie);
                        float[] movesX = new float[numberOfMoves];
                        float[] movesY = new float[numberOfMoves];
                        linie = br.readLine();
                        int j = 0;
                        String[] floatStrings = linie.trim().split("\\s+"); // Split by whitespace
                        for (String floatString : floatStrings) {
                            try {
                                if (j < movesX.length - 1) {
                                    float value = Float.parseFloat(floatString);
                                    movesX[j++] = value;
                                }

                            } catch (NumberFormatException e) {
                                System.out.println(e);
                                // Handle the case where the string cannot be parsed to a float
                            }
                        }

                        linie = br.readLine();
                        j = 0;
                        String[] floatStrings1 = linie.trim().split("\\s+"); // Split by whitespace
                        for (String floatString : floatStrings1) {
                            try {
                                float value = Float.parseFloat(floatString);
                                movesY[j++] = value;
                            } catch (NumberFormatException e) {
                                e.printStackTrace(); // Handle the case where the string cannot be parsed to a float
                            }
                        }

                        Enemy auxiliar = EnemyFactory.createEnemy(enemyType, refLink, x, y, movesX, movesY);
                        entityManager.addEntity(auxiliar);
                    }

                    br.close();
                    fr.close();
                } catch (Exception e) {
                    System.out.println(e);
                    System.exit(1);
                }

            }
            break;
            default:
                System.out.println("Ai castigat!");
        }

    }

    public Tile GetTile(int x, int y) {

        return tileManager.getTile(mapTiles[x][y]);
    }

    public KeyManager GetKeyManager() {
        return keyManager;
    }

    public boolean IsSolid(int x, int y) {
        return tileManager.getTile(mapTiles[x][y]).isSolid();
    }


    /*! \fn public  void Update()
            \brief Actualizarea hartii in functie de evenimente (un obiect a fost colectat)
         */
    public void Update() {
        entityManager.Update();
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    /*! \fn public void Render(Graphics g)
         \brief Functia de desenare a hartii.

         \param g Contextl grafic in care se realizeaza desenarea.
      */
    public void Render(Graphics g) {
        /// randare tiles
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                GetTile(x, y).Draw(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
            }
        }
        entityManager.Render(g);
        if (isFogOfWar) {
            boolean[][] visibleMatrix = new boolean[17][33];

            for (int x = 0; x < height; x++) {
                for (int y = 0; y < width; y++) {
                    //GetTile(x, y).Draw(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
                    if (isFogOfWar){
                        if (!fogOfWar.isVisible(y, x)) {
                            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency);
                            ((Graphics2D) g).setComposite(ac);
                            tileManager.getTile(0).Draw(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
                            ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency);
                            ((Graphics2D) g).setComposite(ac);
                        }
                    }


                }
            }
        }
        entityManager.getKey().Render(g);

    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public FogOfWar getFogOfWar() {
        return fogOfWar;
    }

}

