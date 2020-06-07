package wr_app;

import javax.swing.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class WaehrgDBReader {
    private Connection connMaria = null;
    private Statement stmt = null;
    private int myInt = -1;

    public WaehrgDBReader(){
        this.myInt = -2;
        if (testWDBConnection() == 1){
            this.firstConnect();
        }
    }

    private void firstConnect() {
        this.connMaria = null;
        this.stmt = null;
    }

    private static void infoBox(String titleBar, String infoMessage)
    {
        JOptionPane.showMessageDialog(null, infoMessage,
                "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
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
                infoBox("Failure DB-Connection",
                        "Test-Connecting to MariaDB failed!!!");
                ret = -1;
            }

        }catch(Exception myE){
            infoBox("Failure DB-Connection",
                    "Test-Connecting to MariaDB failed!\r\n"
                            + "Exception was thrown while connecting!!!");
            ret = -2;
        }finally{
            //finally block used to close resources

            try{
                if(connTestMaria !=null)
                    connTestMaria.close();
            }catch(SQLException mySe3){
                infoBox("Failure DB-Connection",
                        "Closing Test-Connection to MariaDB failed!\r\n"
                                + "Exception was thrown while closing!!!");
                ret = -3;
            }//end finally try
        }//end try
    System.out.println("Goodbye!");
    return ret;
    }
}
