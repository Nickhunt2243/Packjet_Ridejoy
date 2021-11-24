package org.example.Controller;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnect{
    /**
     * This will be the connection instance of the high scores database created through AWS.
     */
    private static Connection conn;
    /**
     * This method is used to receive a Connection to the database using the jdbcUrl. Once it is called and receives
     * the jdbcUrl it used the DriverManagers getConnection() method to receive the connection. If it does not
     * receive a connection then it will return null and that is handled in  the next method.
     *
     * If the path of the database is incorrect or the database is offline then no high scores are currently displayed.
     * Thinking of changing this to display a message such as:
     *
     * "Error mapping to the specified. Could not receive high scores."
     * Or
     * "Cannot connect to server. High scores could not be collected."
     *
     *
     * @param jdbcUrl: The URL used to map to the database.
     * @return         The Connection of the database or null if no connection was made.
     */
    public static Connection getDBConnection(String jdbcUrl){
        try {
            conn =
                    DriverManager.getConnection(jdbcUrl);
            return conn;
        } catch (SQLException ex) {
            // handle the error
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }
    /**
     * This method is used when adding a score to the database. It is used in the HighScores class to add the score of
     * the player when they die. It takes in their name and score and puts them into the appropriate columns.
     *
     * @param name:  The name of the player who is playing.
     * @param score: The score of the player when they die.
     */
    public void AddScore(String name, int score){
        PreparedStatement stmt;

        try{
            String sql = "insert into Hscore (score, Name)\n" +
                    "values (?, ?)";
            assert conn != null;
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, score);
            stmt.setString(2, name);
            stmt.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
    /**
     * This will get the top five high scores from the database to display them on the HighScoresMenu class and
     * the pause menu. This is meant to be like an arcade type feeling how the players name is only up to 4 char
     * and only the top five are displayed.
     *
     * @return The User class holding all of the names and respective scores.
     */
    public User getTopScores(){
        Statement stmt;
        ResultSet rs;

        ArrayList<Integer> scores = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        User user;
        try{
            if (conn != null){
                stmt = conn.createStatement();
                String sql = "SELECT * \n" +
                        "from Hscore h \n" +
                        "order by h.score DESC\n" +
                        "LIMIT 5";
                rs = stmt.executeQuery(sql);
                int i=0;
                while (rs.next()){
                    scores.add(i, rs.getInt("score"));
                    names.add(i, rs.getString("Name"));
                    i++;
                }
                user = new User(scores, names);
                return user;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
    /**
     * This method is used to connect to the database. Used as the constructor. After it is called it runs the
     * getDBConnection() method and establishes a connection with the database.
     */
    public DatabaseConnect() {
        String jdbcUrl = "jdbc:mysql://" + "packjetridejoy.cksdeyokmxzd.us-east-1.rds.amazonaws.com" + ":" +
                "3306" + "/" + "packjetridejoy" + "?user=" + "admin" + "&password=" + "Nickhunt2243";
        conn = getDBConnection(jdbcUrl);
    }

}

