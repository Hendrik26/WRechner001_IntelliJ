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

        }catch(Exception myE){
            ret = -2;
        }finally{
            //finally block used to close resources

            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException mySe3){
                ret = -3;
            }//end finally try
        }//end try
    System.out.println("Goodbye!");
    return ret;
    }
}
