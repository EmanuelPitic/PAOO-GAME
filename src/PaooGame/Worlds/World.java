package PaooGame.Worlds;
import PaooGame.Entities.*;
import PaooGame.Entities.Button;
import PaooGame.Entities.EntityManager;
import PaooGame.Graphics.FogOfWar;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileManager;
import PaooGame.RefLinks;
import PaooGame.Level.Level;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class World {
    private final int width;          /*!< Latimea hartii in numar de dale.*/
    private final int height;         /*!< Inaltimea hartii in numar de dale.*/
    private final TileManager tileManager;
    private final RefLinks refLink;
    private final EntityManager entityManager; /*!<Referinta catre Entity manager.*/
    private final FogOfWar fogOfWar;  /*!<Referinta catre un fogOfWar.*/
    boolean isFogOfWar = false;      /*!<Referinta catre un bool ce verifica daca exista sau nu fogOfWar.*/
    private int[][] mapTiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/
    private float transparency; /*!<Transparenta fog-ului*/

    public World(RefLinks refLink) {
        tileManager = new TileManager();
        this.refLink = refLink;
        width = 33;
        height = 17;
        Hero hero = new Hero(refLink, 32, 32);
        entityManager = new EntityManager(refLink, hero);
        this.fogOfWar = new FogOfWar(width, height, 1, 1);
        switch (Level.getInstance().getLevelNr()) {
            case 1: {

                isFogOfWar = false;
                Door door = new Door(refLink, 736, 192, true);
                Button button = new Button(refLink, 448, 416, false);
                Key key = new Key(refLink, 736, 64, false);
                StartFinishDoor startFinishDoor = new StartFinishDoor(refLink, 0, 32, true);
                entityManager.addEntity(startFinishDoor);
                entityManager.addEntity(door);
                entityManager.addEntity(button);
                entityManager.addEntity(key);
                transparency = 0.0F;

                /*!<harta este aici.*/
                try {
                    mapTiles = new int[height][width];
                    File fisier = new File("map1.txt");
                    FileReader fr = new FileReader(fisier);
                    BufferedReader br = new BufferedReader(fr);
                    String linie;
                    int row = 0;

                    /*!<Citirea fiecărei linii și conversia într-o matrice de întregi.*/
                    while (row < 17 && (linie = br.readLine()) != null) {
                        String[] elemente = linie.split(" ");
                        for (int col = 0; col < elemente.length; col++) {
                            mapTiles[row][col] = Integer.parseInt(elemente[col]);
                        }
                        row++;
                    }

                    linie = br.readLine();/*!< Se citeste numarul de enemies.*/
                    int numberOfEnemies = Integer.parseInt(linie);

                    for (int i = 0; i < numberOfEnemies; i++) {
                        linie = br.readLine();/*!< TypeOfEnemy.*/
                        int typeOfEnemy = Integer.parseInt(linie);
                        Enemies enemyType = Enemies.values()[typeOfEnemy];
                        linie = br.readLine(); /*!<x and y spawn.*/
                        String[] intStrings = linie.trim().split("\\s+"); /*!< Split by whitespace.*/
                        int x = Integer.parseInt(intStrings[0]);
                        int y = Integer.parseInt(intStrings[1]);
                        linie = br.readLine();/*!< Number of Enemies*/
                        int numberOfMoves = Integer.parseInt(linie);
                        float[] movesX = new float[numberOfMoves];
                        float[] movesY = new float[numberOfMoves];
                        linie = br.readLine();
                        int j = 0;
                        String[] floatStrings = linie.trim().split("\\s+");/*!< Split by whitespace.*/
                        for (String floatString : floatStrings) {
                            try {
                                if (j < movesX.length - 1) {
                                    float value = Float.parseFloat(floatString);
                                    movesX[j++] = value;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(e);
                                /*!< Nu e parsable ca float.*/
                            }
                        }
                        linie = br.readLine();
                        j = 0;
                        String[] floatStrings1 = linie.trim().split("\\s+"); /*!< Split by whitespace.*/
                        for (String floatString : floatStrings1) {
                            try {
                                float value = Float.parseFloat(floatString);
                                movesY[j++] = value;
                            } catch (NumberFormatException e) {
                                e.printStackTrace(); /*!< Nu e parsable ca float.*/
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

                isFogOfWar = true;
                transparency = 0.25F;
                Door door = new Door(refLink, 704, 384, true);
                Button button = new Button(refLink, 514, 352, false);
                Key key = new Key(refLink, 672, 384, false);
                StartFinishDoor startFinishDoor = new StartFinishDoor(refLink, 0, 32, true);
                entityManager.addEntity(startFinishDoor);
                entityManager.addEntity(door);
                entityManager.addEntity(button);
                entityManager.addEntity(key);
                try {
                    mapTiles = new int[height][width];
                    File fisier = new File("map2.txt");
                    FileReader fr = new FileReader(fisier);
                    BufferedReader br = new BufferedReader(fr);
                    String linie;
                    int row = 0;

                    /*!<Citirea fiecărei linii și conversia într-o matrice de întregi.*/
                    while (row < 17 && (linie = br.readLine()) != null) {
                        String[] elemente = linie.split(" ");
                        for (int col = 0; col < elemente.length; col++) {
                            mapTiles[row][col] = Integer.parseInt(elemente[col]);
                        }
                        row++;
                    }

                    linie = br.readLine();
                    int numberOfEnemies = Integer.parseInt(linie);
                    for (int i = 0; i < numberOfEnemies; i++) {
                        linie = br.readLine();
                        int typeOfEnemy = Integer.parseInt(linie);
                        Enemies enemyType = Enemies.values()[typeOfEnemy];
                        linie = br.readLine();
                        String[] intStrings = linie.trim().split("\\s+");
                        int x = Integer.parseInt(intStrings[0]);
                        int y = Integer.parseInt(intStrings[1]);
                        linie = br.readLine();
                        int numberOfMoves = Integer.parseInt(linie);
                        float[] movesX = new float[numberOfMoves];
                        float[] movesY = new float[numberOfMoves];
                        linie = br.readLine();
                        int j = 0;
                        String[] floatStrings2 = linie.trim().split("\\s+");
                        for (String floatString : floatStrings2) {
                            try {
                                float value = Float.parseFloat(floatString);
                                movesX[j++] = value;
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                        linie = br.readLine();
                        j = 0;
                        String[] floatStrings3 = linie.trim().split("\\s+");
                        for (String floatString : floatStrings3) {
                            try {
                                float value = Float.parseFloat(floatString);
                                movesY[j++] = value;
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
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

            case 3: {

                isFogOfWar = true;
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
                try {
                    mapTiles = new int[height][width];
                    File fisier = new File("map3.txt");
                    FileReader fr = new FileReader(fisier);
                    BufferedReader br = new BufferedReader(fr);
                    String linie;
                    int row = 0;
                    while (row < 17 && (linie = br.readLine()) != null) {
                        String[] elemente = linie.split(" ");
                        for (int col = 0; col < elemente.length; col++) {
                            mapTiles[row][col] = Integer.parseInt(elemente[col]);
                        }
                        row++;
                    }
                    linie = br.readLine();
                    int numberOfEnemies = Integer.parseInt(linie);
                    for (int i = 0; i < numberOfEnemies; i++) {
                        linie = br.readLine();
                        int typeOfEnemy = Integer.parseInt(linie);
                        Enemies enemyType = Enemies.values()[typeOfEnemy];
                        linie = br.readLine();
                        String[] intStrings = linie.trim().split("\\s+");
                        int x = Integer.parseInt(intStrings[0]);
                        int y = Integer.parseInt(intStrings[1]);
                        linie = br.readLine();
                        int numberOfMoves = Integer.parseInt(linie);
                        float[] movesX = new float[numberOfMoves];
                        float[] movesY = new float[numberOfMoves];
                        linie = br.readLine();
                        int j = 0;
                        String[] floatStrings = linie.trim().split("\\s+");
                        for (String floatString : floatStrings) {
                            try {
                                if (j < movesX.length - 1) {
                                    float value = Float.parseFloat(floatString);
                                    movesX[j++] = value;
                                }

                            } catch (NumberFormatException e) {
                                System.out.println(e);
                            }
                        }

                        linie = br.readLine();
                        j = 0;
                        String[] floatStrings1 = linie.trim().split("\\s+");
                        for (String floatString : floatStrings1) {
                            try {
                                float value = Float.parseFloat(floatString);
                                movesY[j++] = value;
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
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
        }

    }

    /*!<
    Constructorul acesta este folosit pentru incarcarea lumii din load state
    .*/
    public World(RefLinks refLink, String LoadWorld) {
        String[] words = LoadWorld.split("/");
        refLink.GetGame().getPlayState2().setCountdown(Integer.parseInt(words[2]));
        tileManager = new TileManager();
        this.refLink = refLink;
        width = 33;
        height = 17;
        Hero hero = new Hero(refLink, Float.parseFloat(words[4]), Float.parseFloat(words[5]));
        hero.setHealth(Integer.parseInt(words[6]));
        entityManager = new EntityManager(refLink, hero);
        this.fogOfWar = new FogOfWar(width, height, (int) Float.parseFloat(words[4]) / 32, (int) Float.parseFloat(words[5]) / 32);
        Level.getInstance().setLevel(Integer.parseInt(words[1]));

        switch (Level.getInstance().getLevelNr()) {

            case 1: {
                isFogOfWar = false;
                Button button = new Button(refLink, 448, 416, false);
                button.setPressed(Boolean.parseBoolean(words[7]));
                Door door = new Door(refLink, 736, 192, Boolean.parseBoolean(words[8]));
                Key key = new Key(refLink, 736, 64, false);
                key.setColected(Boolean.parseBoolean(words[9]));
                StartFinishDoor startFinishDoor = new StartFinishDoor(refLink, 0, 32, true);
                startFinishDoor.setSolid(Boolean.parseBoolean(words[10]));
                entityManager.addEntity(startFinishDoor);
                entityManager.addEntity(door);
                entityManager.addEntity(button);
                entityManager.addEntity(key);
                transparency = 0.0F;


                try {
                    mapTiles = new int[height][width];

                    File fisier = new File("map1.txt");
                    FileReader fr = new FileReader(fisier);
                    BufferedReader br = new BufferedReader(fr);

                    String linie;
                    int row = 0;


                    while (row < 17 && (linie = br.readLine()) != null) {
                        String[] elemente = linie.split(" ");
                        for (int col = 0; col < elemente.length; col++) {
                            mapTiles[row][col] = Integer.parseInt(elemente[col]);
                        }
                        row++;
                    }
                    linie = br.readLine();
                    int numberOfEnemies = Integer.parseInt(linie);
                    for (int i = 0; i < numberOfEnemies; i++) {
                        linie = br.readLine();
                        int typeOfEnemy = Integer.parseInt(linie);
                        Enemies enemyType = Enemies.values()[typeOfEnemy];
                        linie = br.readLine();
                        String[] intStrings = linie.trim().split("\\s+");
                        int x = (int) Float.parseFloat(words[(i + 1) * 11]);
                        int y = (int) Float.parseFloat(words[(i + 1) * 11 + 1]);
                        linie = br.readLine();
                        int numberOfMoves = Integer.parseInt(linie);
                        float[] movesX = new float[numberOfMoves];
                        float[] movesY = new float[numberOfMoves];
                        linie = br.readLine();
                        int j = 0;
                        String[] floatStrings = linie.trim().split("\\s+");
                        for (String floatString : floatStrings) {
                            try {
                                if (j < movesX.length - 1) {
                                    float value = Float.parseFloat(floatString);
                                    movesX[j++] = value;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(e);
                            }
                        }

                        linie = br.readLine();
                        j = 0;
                        String[] floatStrings1 = linie.trim().split("\\s+");
                        for (String floatString : floatStrings1) {
                            try {
                                float value = Float.parseFloat(floatString);
                                movesY[j++] = value;
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                        Enemy auxiliar = EnemyFactory.createEnemy(enemyType, refLink, x, y, movesX, movesY);
                        auxiliar.setIndex(Integer.parseInt(words[(i + 1) * 11 + 2]));
                        auxiliar.setAsc(Boolean.parseBoolean(words[(i + 1) * 11 + 3]));
                        auxiliar.setLastMove(Integer.parseInt(words[(i + 1) * 11 + 4]));
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
                transparency = 0.25F;
                isFogOfWar = true;
                Button button = new Button(refLink, 514, 352, false);
                button.setPressed(Boolean.parseBoolean(words[7]));
                Door door = new Door(refLink, 704, 384, Boolean.parseBoolean(words[8]));
                Key key = new Key(refLink, 672, 384, false);
                key.setColected(Boolean.parseBoolean(words[9]));
                StartFinishDoor startFinishDoor = new StartFinishDoor(refLink, 0, 32, true);
                startFinishDoor.setSolid(Boolean.parseBoolean(words[10]));
                entityManager.addEntity(startFinishDoor);
                entityManager.addEntity(door);
                entityManager.addEntity(button);
                entityManager.addEntity(key);
                transparency = 0.0F;
                entityManager.addEntity(startFinishDoor);
                entityManager.addEntity(door);
                entityManager.addEntity(button);
                entityManager.addEntity(key);
                try {
                    mapTiles = new int[height][width];
                    File fisier = new File("map2.txt");
                    FileReader fr = new FileReader(fisier);
                    BufferedReader br = new BufferedReader(fr);
                    String linie;
                    int row = 0;
                    while (row < 17 && (linie = br.readLine()) != null) {
                        String[] elemente = linie.split(" ");
                        for (int col = 0; col < elemente.length; col++) {
                            mapTiles[row][col] = Integer.parseInt(elemente[col]);
                        }
                        row++;
                    }
                    linie = br.readLine();
                    int numberOfEnemies = Integer.parseInt(linie);
                    for (int i = 0; i < numberOfEnemies; i++) {
                        linie = br.readLine();
                        int typeOfEnemy = Integer.parseInt(linie);
                        Enemies enemyType = Enemies.values()[typeOfEnemy];
                        linie = br.readLine();
                        String[] intStrings = linie.trim().split("\\s+");
                        int x = Integer.parseInt(intStrings[0]);
                        int y = Integer.parseInt(intStrings[1]);
                        linie = br.readLine();
                        int numberOfMoves = Integer.parseInt(linie);
                        float[] movesX = new float[numberOfMoves];
                        float[] movesY = new float[numberOfMoves];
                        linie = br.readLine();
                        int j = 0;
                        String[] floatStrings2 = linie.trim().split("\\s+");
                        for (String floatString : floatStrings2) {
                            try {
                                float value = Float.parseFloat(floatString);
                                movesX[j++] = value;
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                        linie = br.readLine();
                        j = 0;
                        String[] floatStrings3 = linie.trim().split("\\s+");
                        for (String floatString : floatStrings3) {
                            try {
                                float value = Float.parseFloat(floatString);
                                movesY[j++] = value;
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
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

            case 3: {
                isFogOfWar = true;
                MasterDoor masterDoor = new MasterDoor(refLink, 672, 480, true);
                Button button = new Button(refLink, 896, 416, false);
                button.setPressed(Boolean.parseBoolean(words[7]));
                Door door = new Door(refLink, 864, 96, Boolean.parseBoolean(words[8]));
                Key key = new Key(refLink, 864, 64, false);
                key.setColected(Boolean.parseBoolean(words[9]));
                StartFinishDoor startFinishDoor = new StartFinishDoor(refLink, 0, 32, true);
                startFinishDoor.setSolid(Boolean.parseBoolean(words[10]));
                entityManager.addEntity(startFinishDoor);
                entityManager.addEntity(door);
                entityManager.addEntity(button);
                entityManager.addEntity(key);
                entityManager.addEntity(masterDoor);
                Lever lever1 = new Lever(refLink, 608, 64, false);
                Lever lever2 = new Lever(refLink, 480, 64, false);
                Lever lever3 = new Lever(refLink, 416, 480, false);
                lever1.setPulled(Boolean.parseBoolean(words[11]));
                lever2.setPulled(Boolean.parseBoolean(words[12]));
                lever3.setPulled(Boolean.parseBoolean(words[13]));
                entityManager.addEntity(lever1);
                entityManager.addEntity(lever2);
                entityManager.addEntity(lever3);
                transparency = 0.5F;
                try {
                    mapTiles = new int[height][width];
                    File fisier = new File("map3.txt");
                    FileReader fr = new FileReader(fisier);
                    BufferedReader br = new BufferedReader(fr);
                    String linie;
                    int row = 0;
                    while (row < 17 && (linie = br.readLine()) != null) {
                        String[] elemente = linie.split(" ");
                        for (int col = 0; col < elemente.length; col++) {
                            mapTiles[row][col] = Integer.parseInt(elemente[col]);
                        }
                        row++;
                    }
                    linie = br.readLine();
                    int numberOfEnemies = Integer.parseInt(linie);
                    for (int i = 0; i < numberOfEnemies; i++) {
                        linie = br.readLine();
                        int typeOfEnemy = Integer.parseInt(linie);
                        Enemies enemyType = Enemies.values()[typeOfEnemy];
                        linie = br.readLine();
                        String[] intStrings = linie.trim().split("\\s+");
                        int x = Integer.parseInt(intStrings[0]);
                        int y = Integer.parseInt(intStrings[1]);
                        linie = br.readLine();
                        int numberOfMoves = Integer.parseInt(linie);
                        float[] movesX = new float[numberOfMoves];
                        float[] movesY = new float[numberOfMoves];
                        linie = br.readLine();
                        int j = 0;
                        String[] floatStrings = linie.trim().split("\\s+");
                        for (String floatString : floatStrings) {
                            try {
                                if (j < movesX.length - 1) {
                                    float value = Float.parseFloat(floatString);
                                    movesX[j++] = value;
                                }

                            } catch (NumberFormatException e) {
                                System.out.println(e);
                            }
                        }
                        linie = br.readLine();
                        j = 0;
                        String[] floatStrings1 = linie.trim().split("\\s+");
                        for (String floatString : floatStrings1) {
                            try {
                                float value = Float.parseFloat(floatString);
                                movesY[j++] = value;
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
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
        }
    }


    public Tile GetTile(int x, int y) {

        return tileManager.getTile(mapTiles[x][y]);
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

    /*! \fn public void Render(Graphics g)
         \brief Functia de desenare a hartii.

         \param g Contextl grafic in care se realizeaza desenarea.
      */
    public void Render(Graphics g) {
        /*!<Randare  tiles.*/
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                GetTile(x, y).Draw(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
            }
        }
        entityManager.Render(g);
        if (isFogOfWar) {
            if (Level.getInstance().getLevelNr() == 2) transparency = 0.25F;
            if (Level.getInstance().getLevelNr() == 3) transparency = 0.75F;
            for (int x = 1; x < height - 1; x++) {
                for (int y = 1; y < width - 1; y++) {
                    if (!fogOfWar.isVisible(y, x)) {
                        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency);
                        ((Graphics2D) g).setComposite(ac);
                        tileManager.getTile(0).Draw(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
                        ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F);
                        ((Graphics2D) g).setComposite(ac);
                    }
                }

            }

        }
        /*!<Se randeaza masinile ultimele, pentru a fi deasupra cetii.*/
        ArrayList<Level3Enemy> cars = entityManager.getCars();
        for (Level3Enemy car : cars) {
            car.Render(g);
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

