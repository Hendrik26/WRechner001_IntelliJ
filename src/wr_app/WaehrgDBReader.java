package wr_app;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class WaehrgDBReader {
    private Connection connMaria = null;
    private Statement stmt = null;
    private int myInt = -1;

    public WaehrgDBReader(){
        this.myInt = -2;
        this.connMaria = null;
        this.stmt = null;
    }

    // public /*WaehrgFromDB[]*/ void getFromDB(){
    public static int testWDBConnection(){
        int ret = -11;
        Connection connTestMaria = null;
        try {

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            int i = -1;
            connTestMaria = ConnectionFactoryMariaDb.createConnectionMariaDb();
            if (connTestMaria != null) {
                ret = 1;
            } else {
                ret = -1;
            }

        }catch(Exception myE){
            ret = -2;
        }finally{
            //finally block used to close resources

            try{
                if(connTestMaria !=null)
                    connTestMaria.close();
            }catch(SQLException mySe3){
                ret = -3;
            }//end finally try
        }//end try
    System.out.println("Goodbye!");
    return ret;
    }
}
