package PaooGame.Entities;

import PaooGame.Level.Level;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/*! \class EntityManager
    \brief Implementeaza notiunea de manager de entitati in joc
 */

public class EntityManager {

    /// entitatile din joc se refera la erou, inamic, respectiv itemi ce trebuie colectati
    private RefLinks refLink;           /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private Hero hero;                  /*!< O referinta catre o entitate de tip erou*/
    private ArrayList<Entity>entities;  /*!< ArrayList cu toate entitatile din joc*/
    //public boolean[][] visibleMatrix = new boolean[17][33];


    /*! \fn EntityManager(RefLinks refLink, Hero hero)
                \brief Constructorul cu parametri al clasei EntityManager. Adauga eroul la lista de entitati.

                \param reflink Referinta catre un obiect "shortcut"
                \param hero Eroul (entitatea principala)
    */
    public EntityManager(RefLinks refLink, Hero hero){
        this.refLink=refLink;
        this.hero=hero;
        entities=new ArrayList<Entity>();
        addEntity(hero);

    }


    /*! \fn private Comparator<Entity> renderSorter
                \brief Se ocupa de ordinea desenarii entitatilor pe harta.

    */
    private Comparator<Entity> renderSorter=new Comparator<Entity>() {
        @Override
        public int compare(Entity a, Entity b) {
            if(a.getName().equals("Hero") || a.getName().equals("Enemy") && (b.getName().equals("Door")||b.getName().equals("Button")||b.getName().equals("Door")))
                return 1;
            if((a.getName().equals("Button") || a.getName().equals("Door")) && b.getName().equals("Button"))
                return 1;
            return -1;
        }
    };


    /*! \fn public void Update()
             \brief Actualizarea starii curente
 */
    public void Update(){
        Iterator<Entity> it= entities.iterator();
        while(it.hasNext())
        {
            Entity e=it.next();
            e.Update();

            // daca viata entitatii respective s-a terminat, aceasta va fi eliminata de pe harta
            if(!e.isActive())
                it.remove();
        }

        entities.sort(renderSorter);
    }

    /*! \fn public void Render(Graphics g)
          \brief Desenarea starii curente starii curente.

          \param g Contextl grafic in care se realizeaza desenarea.
*/
    public void Render(Graphics g){

        for(Entity e:entities){
            e.Render(g);
        }
    }



    /*! \fn public void addEntity(Entity e)
      \brief Adauga entitatea la lista entitatilor deja existente

      \param e Entitatea care se doreste a fi adaugata
*/
    public void addEntity(Entity e){
        entities.add(e);
    }

    ///Getters & Setters pentru atribute
    public RefLinks getRefLink() {
        return refLink;
    }

    public void setRefLink(RefLinks refLink) {
        this.refLink = refLink;
    }

    public Hero getHero() {
        return hero;
    }

    public boolean isDoor(){
        for (Entity e:entities)
        {
            if (e.getName().equals("Door"))
                return true;
        }
        return false;
    }

    public Door getDoor()
    {
        for (Entity e:entities)
        {
            if (e.getName().equals("Door"))
                return (Door)e;

        }
        return (Door)entities.get(0);//nu se ajunge aici
    }

    public StartFinishDoor getStartFinishDoor()
    {
        for (Entity e:entities)
        {
            if (e.getName().equals("StartFinishDoor"))
                return (StartFinishDoor)e;

        }
        return (StartFinishDoor) entities.get(0);//nu se ajunge aici
    }


    public boolean isButton(){
        for (Entity e:entities)
        {
            if (e.getName().equals("Button"))
                return true;
        }
        return false;
    }

    public Button getButton()
    {
        for (Entity e:entities)
        {
            if (e.getName().equals("Button"))
                return (Button)e;

        }
        return (Button)entities.get(0);//nu se ajunge aici
    }

    public boolean isKey()
    {
        for (Entity e:entities)
        {
            if (e.getName().equals("Key"))
                return true;
        }
        return false;
    }

    public Key getKey(){
        for (Entity e:entities)
        {
            if (e.getName().equals("Key"))
                return (Key)e;
        }
        return (Key)entities.get(0);//nu se ajunge aici
    }

    public boolean isEnemy(){
        for (Entity e:entities)
        {
            if (e.getName().equals("Enemy"))
                return true;
        }
        return false;
    }
    public Enemy getEnemy()
    {
        for (Entity e:entities)
        {
            if (e.getName().equals("Enemy"))
                return (Enemy)e;
        }
        return (Enemy)entities.get(0);//nu se ajunge aici
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public boolean isLever(){
        for (Entity e:entities)
        {
            if (e.getName().equals("Lever"))
                return true;
        }
        return false;
    }

    public ArrayList<Lever> getLever()
    {
        ArrayList<Lever> levers = new ArrayList<Lever>();
        for (Entity e:entities)
        {
            if (e.getName().equals("Lever"))
                levers.add((Lever)e);
        }
        return levers;//nu se ajunge aici
    }

    public boolean isMasterDoor(){
        for (Entity e:entities)
        {
            if (e.getName().equals("MasterDoor"))
                return true;
        }
        return false;
    }

    public MasterDoor getMasterDoor(){
        for (Entity e:entities)
        {
            if (e.getName().equals("MasterDoor"))
                return (MasterDoor)e;
        }
        return (MasterDoor) entities.get(0);//nu se ajunge aici
    }

    public ArrayList<Level3Enemy> getCars()
    {
        ArrayList<Level3Enemy> cars = new ArrayList<Level3Enemy>();
        for (Entity e:entities)
        {
            if (e instanceof Level3Enemy)
                cars.add((Level3Enemy) e);
        }
        return cars;//nu se ajunge aici
    }


    public ArrayList<Lever> getLevers()
    {
        ArrayList<Lever> levers = new ArrayList<>();
        for (Entity e:entities)
        {
            if (e instanceof Lever)
                levers.add((Lever) e);
        }
        return levers;//nu se ajunge aici

    }
    public ArrayList<Enemy> getEnemies()
    {
        ArrayList<Enemy> enemies = new ArrayList<>();
        for (Entity e:entities)
        {
            if (e instanceof Enemy)
                enemies.add((Enemy) e);
        }
        return enemies;//nu se ajunge aici

    }


    @Override
    public String toString() {
        //salvam datele, prima data pentru obiectele statice, in ordine: buton, usa, cheie, StartFinishDoor, Lever? si MasterDoor?
        StringBuilder toReturn = new StringBuilder();
        toReturn.append(getButton().isPressed()).append('/')
                .append(getDoor().isSolid()).append('/')
                .append(getKey().isColected()).append('/')
                .append(getStartFinishDoor().isSolid()).append('/');
        if(Level.getInstance().getLevelNr()==3)
        {
            ArrayList<Lever> levers = getLevers();
            for (Lever lever:levers){
                toReturn.append(lever.isPulled()).append('/');
            }

        }
        ArrayList<Enemy> enemies = getEnemies();
        for (Enemy e : enemies)
        {
            toReturn.append(e.toString());
        }
        return toReturn.toString();
    }




}
