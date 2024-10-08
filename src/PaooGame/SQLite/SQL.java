package PaooGame.SQLite;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/*! \public class SQL
    \brief Gestioneaza baza de date
    Aceasta clasa utilizeaza Singleton Pattern.
 */

public class SQL {

    private static SQL instance = null;
    private static Connection c = null;
    private static Statement stmt = null;

    /*! \fn  private SQL()
        \brief Constructorul privat al clasei SQL ; creeaza conexiunea cu baza de date
                si creeaza tabelele pentru scoruri , respectiv pentru setari
    */
    private SQL() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:javaGame.db");
            c.setAutoCommit(false);


            stmt = c.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS PLAYER (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NAME TEXT NOT NULL," +
                    "LEVEL INT NOT NULL, " +
                    "GAMESTATE VARCHAR(350) NOT NULL" +
                    ")";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS SCORE (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NAME VARCHAR(50) NOT NULL," +
                     "SCORE INT NOT NULL" +
                    ")";
            stmt.executeUpdate(sql);



            c.commit();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }


    /*! \fn public static SQL getInstance()
            \brief Returneaza instanta bazei de date daca aceasta exista. In caz contrar, creeaza o noua
                    instanta, pe care apoi o returneaza.
    */
    public static SQL getInstance() {
        if (instance == null)
            instance = new SQL();
        return instance;
    }


    public void insertPlayer(String name, int level, String gameState){
        try{
            String sql = "INSERT INTO PLAYER (NAME, LEVEL, GAMESTATE) VALUES (?,?,?)";

            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, level);
            pstmt.setString(3, gameState);
            pstmt.executeUpdate();
            c.commit();

        }
        catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void insertScore(String name, int score){
        try{
            String sql = "INSERT INTO SCORE (NAME, SCORE) VALUES (?,?)";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, score);
            preparedStatement.executeUpdate();
            c.commit();
        }
        catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public ArrayList<String> getLastGameStates() {
        ArrayList<String> gameStates = new ArrayList<>();
        String query = "SELECT GAMESTATE FROM PLAYER ORDER BY ID DESC LIMIT 4";

        try (PreparedStatement pstmt = c.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                gameStates.add(rs.getString("GAMESTATE"));
            }

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return gameStates;
    }

    public ArrayList<String> getLastNames() {
        ArrayList<String> gameStates = new ArrayList<>();
        String query = "SELECT NAME FROM PLAYER ORDER BY ID DESC LIMIT 4";

        try (PreparedStatement pstmt = c.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                gameStates.add(rs.getString("NAME"));
            }

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return gameStates;
    }

    public ArrayList<String> getLastLevels() {
        ArrayList<String> gameStates = new ArrayList<>();
        String query = "SELECT LEVEL FROM PLAYER ORDER BY ID DESC LIMIT 4";

        try (PreparedStatement pstmt = c.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                gameStates.add(rs.getString("LEVEL"));
            }

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return gameStates;
    }

    public ArrayList<String> getBestSCores() {
        ArrayList<String> gameStates = new ArrayList<>();
        String query = "SELECT NAME, SCORE FROM SCORE ORDER BY SCORE DESC LIMIT 3";

        try (PreparedStatement pstmt = c.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                gameStates.add(rs.getString("NAME")+" "+rs.getString("SCORE"));
            }

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return gameStates;
    }

}
