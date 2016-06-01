package Model;


import App.App;
import App.Config;

import java.sql.*;


public class DB {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://" + Config.DATABASE_HOST + "/"+ Config.DATABASE;

    static Connection conn = null;
    static Connection conn1 = null;
    static Connection conn2 = null;
    static Connection conn3 = null;
    static Connection conn4 = null;

    static Statement stmt = null;
    static Statement stmt1 = null;
    static Statement stmt2 = null;
    static Statement stmt3 = null;
    static Statement stmt4 = null;


    /*public static void main(String[] args) {


        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");


            ResultSet rs = stmt.executeQuery("");

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", First Name: " + firstName);
                System.out.print(", Last Name: " + lastName);
                System.out.println("");

            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

    }*/


    public static ResultSet query(String query) throws Exception {

        connect();
        ResultSet rs = stmt.executeQuery(query);
        //stmt.close();
        //conn.close();

        return rs;
    }

    public static ResultSet query1(String query) throws Exception {

        connect1();
        ResultSet rs = stmt1.executeQuery(query);
        //stmt1.close();
        //conn1.close();
        return rs;
    }

    public static ResultSet query2(String query) throws Exception {

        connect2();
        ResultSet rs = stmt2.executeQuery(query);
        //stmt2.close();
        //conn2.close();
        // conn.close();
        return rs;
    }

    public static ResultSet query3(String query) throws Exception {

        connect3();
        ResultSet rs = stmt3.executeQuery(query);
        //stmt3.close();
        //conn3.close();
        return rs;
    }

    public static ResultSet query4(String query) throws Exception {

        connect4();
        ResultSet rs = stmt4.executeQuery(query);
        //stmt4.close();
        //conn4.close();
        return rs;
    }

    private static void connect() throws Exception {

        conn = DriverManager.getConnection(DB_URL, Config.DATABASE_USERNAME, Config.DATABASE_PASSWORD);

        stmt = conn.createStatement();


    }

    private static void connect1() throws Exception {

        conn1 = DriverManager.getConnection(DB_URL, Config.DATABASE_USERNAME, Config.DATABASE_PASSWORD);

        stmt1 = conn1.createStatement();

    }

    private static void connect2() throws Exception {

        conn2 = DriverManager.getConnection(DB_URL, Config.DATABASE_USERNAME, Config.DATABASE_PASSWORD);

        stmt2 = conn2.createStatement();

    }

    private static void connect3() throws Exception {

        conn3 = DriverManager.getConnection(DB_URL, Config.DATABASE_USERNAME, Config.DATABASE_PASSWORD);

        stmt3 = conn3.createStatement();

    }

    private static void connect4() throws Exception {

        if(conn4!=null){
            conn4.close();
        }

        conn4 = DriverManager.getConnection(DB_URL, Config.DATABASE_USERNAME, Config.DATABASE_PASSWORD);

        stmt4 = conn4.createStatement();

    }


}



