package wr_app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class WaehrgDBReader {
    // private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_DRIVER = "com.mariadb.jdbc.Driver";
    // private static final String DB_URL_CONST = "jdbc:mysql://localhost/db_waehrgs_r";
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
    public void testWDBConnection(){

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);



            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL_CONST,USER_CONST,PW_CONST);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            System.out.println("Statement created ...");

            String mySqlSelect, mySqlInsert;
            mySqlSelect = "SELECT * FROM tbl_waehrgs_name";


            System.out.println("SQL-Strings erzeugt");

            ResultSet myResultSet = stmt.executeQuery(mySqlSelect);
            //int i = stmt.executeUpdate(sqlInsert);

            //STEP 5: Extract data from result set
            System.out.println("-------------\r\n\r\n");
            while(myResultSet.next()){
                //Retrieve by column name

                // myProduct.setProductID(myResultSet.getInt("product_ID"));////////
                System.out.println("WaehrgsID = " + myResultSet.getInt("id")
                        + "WaehrgsName = " + myResultSet.getString("Waehrgs_Name")
                        +  "\r\n\r\n");
            }

        }catch(ClassNotFoundException myCnfe){
            //Handle errors for JDBC
            myCnfe.printStackTrace();
            // new catch()

        }catch(SQLException mySe){
            //Handle errors for JDBC
            mySe.printStackTrace();
        }catch(Exception myE){
            //Handle errors for Class.forName
            myE.printStackTrace();
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
    }
}
