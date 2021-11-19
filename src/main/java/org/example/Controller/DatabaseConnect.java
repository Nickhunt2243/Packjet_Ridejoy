package org.example.Controller;

import java.sql.*;
import java.util.ArrayList;



public class DatabaseConnect{

    private final String jdbcUrl;
    Connection conn = null;

    public DatabaseConnect() {
        jdbcUrl = "jdbc:mysql://" + "packjetridejoy.cksdeyokmxzd.us-east-1.rds.amazonaws.com" + ":" +
                "3306" + "/" + "packjetridejoy" + "?user=" + "admin" + "&password=" + "Nickhunt2243";

        LoadDriver();
    }
    public static Connection getDBConnection(String jdbcUrl){
        try {
            Connection conn =
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

    public static void LoadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    public void AddScore(String name, int score){
        conn = getDBConnection(jdbcUrl);
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            String sql = "insert into Hscore (score, Name)\n" +
                    "values (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, score);
            stmt.setString(2, name);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public User getTopScores(){
        conn = getDBConnection(jdbcUrl);
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Integer> scores = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        User user;

        try{
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return null;
    }
}

