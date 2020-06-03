package wr_app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.sql.Driver;
import java.sql.DriverManager;

import java.util.Properties;

import javax.sql.DataSource;

public class WaehrgDBReader {
    private static final String JDBC_DRIVER = "com.mariadb.jdbc.Driver";
    private static final String DB_URL_CONST = "jdbc:mariadb://localhost:3306/db_waehrgs_r";
    private static final String USER_CONST = "root";
    private static final String PW_CONST = "";
    private Connection conn = null;
    private Statement stmt = null;
    private int myInt = -1;

    public WaehrgDBReader(){
        this.myInt = -2;
        this.conn = null;
        this.stmt = null;
    }

    // public /*WaehrgFromDB[]*/ void getFromDB(){
    public int testWDBConnection(){
        int ret = -11;
        try {

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            int i = -1;
            conn = ConnectionFactoryMariaDb.createConnectionMariaDb();
            if (conn != null) {
                ret = 1;
            } else {
                ret = -1;
            }


        }catch(ClassNotFoundException myCnfe){
            //Handle errors for JDBC
            myCnfe.printStackTrace();
            ret = -2;

        }catch(SQLException mySe){
            //Handle errors for JDBC
            mySe.printStackTrace();
            ret = -2;

        }catch(Exception myE){
            //Handle errors for Class.forName
            myE.printStackTrace();
            ret = -2;
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException mySe2){
                mySe2.printStackTrace();
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException mySe3){
                mySe3.printStackTrace();
            }//end finally try
        }//end try
    System.out.println("Goodbye!");
    return ret;
    }
}
