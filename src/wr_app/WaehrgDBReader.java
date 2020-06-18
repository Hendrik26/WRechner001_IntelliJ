package wr_app;

import javax.swing.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class WaehrgDBReader {
    private Connection connMaria = null;
    private int myInt = -1;

    public WaehrgDBReader(){
        this.myInt = -2;
        // if (testWDBConnection() == 1){
            this.firstConnect();
        // }
    }

    // public /*WaehrgFromDB[]*/ void getFromDB(){
    public void getCurrenciesStandardizedFromDB(){
        Statement stmt = null;
        try{
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = this.connMaria.createStatement();
            String sql;
            sql = "SELECT id, Waehrgs_Name FROM tbl_waehrgs_name";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                String waehrgsName = rs.getString("Waehrgs_Name");

                //Display values
                String row = "ID: " + id + "; WaehrungsName: " + waehrgsName + ";\r\n";
                System.out.print(row);

            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
                se2.printStackTrace();
            }
            //end finally try
        }//end try
        System.out.println("Goodbye!!!!!!!!!!!!");
    }

    private void firstConnect() {
        try {
            //STEP 3Maria: Open a connection
            System.out.println("First Connecting to database MariaDB ...");
            this.connMaria = ConnectionFactoryMariaDb.createConnectionMariaDb();
            System.out.println("First Connected to database MariaDB ...");
        } catch (Exception e) {
            infoBox("Exception first connecting to MariaDB!",
                    exceptionText(e));
            System.out.println("Printing Exception.StackTrace ...");
            e.printStackTrace();
            System.out.println("Printed Exception.StackTrace ...\r\n\r\n");
        }
    }

    public void reConnect() {
        try {
            //STEP 3Maria: Open a connection
            System.out.println("ReConnecting to database MariaDB ...");
            this.connMaria = ConnectionFactoryMariaDb.createConnectionMariaDb();
            System.out.println("ReConnected to database MariaDB ...");
        } catch (Exception e) {
            infoBox("Exception reConnecting to MariaDB!",
                    exceptionText(e));
            System.out.println("Printing Exception.StackTrace ...");
            e.printStackTrace();
            System.out.println("Printed Exception.StackTrace ...\r\n\r\n");
        }
    }

    public void closeConnection() {
        try {
            //STEP XY1Maria: Close the current connection
            System.out.println("Closing Connection to database MariaDB ...");
            if(this.connMaria != null)
                this.connMaria.close();
            System.out.println("Closed Connection to database MariaDB ...");
        } catch (Exception e) {
            infoBox("Exception closing connection to MariaDB!",
                    exceptionText(e));
            System.out.println("Printing Exception.StackTrace ...");
            e.printStackTrace();
            System.out.println("Printed Exception.StackTrace ...\r\n\r\n");
        }
    }

    private static void infoBox(String titleBar, Object infoMessage)
    {
        JOptionPane.showMessageDialog(null, infoMessage,
                "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    private String redBold(String inText){
        String formatHtmlString = "<HTML><p style=\"color:red;\"><b>%s</b></p></HTML>";
        return String.format(formatHtmlString, inText);
    }

    private String greenBold(String inText){
        String formatHtmlString = "<HTML><p style=\"color:green;\"><b>%s</b></p></HTML>";
        return String.format(formatHtmlString, inText);
    }

    private String blueBold(String inText){
        String formatHtmlString = "<HTML><p style=\"color:blue;\"><b>%s</b></p></HTML>";
        return String.format(formatHtmlString, inText);
    }

    private String exceptionText(Exception e){
         String stackTraceString = "";
         for (StackTraceElement s : e.getStackTrace()){
             stackTraceString += s + " \r\n";
         }
         return redBold("Exception-Message === \"")
                + " \r\n"
                + e.getMessage()
                + redBold("\" !!!!!!!!!!!!!!!!!!!!!")
                + "\r\n\r\n"
                + blueBold("-------------------------------------------")
                + "\r\n\r\n"
                + greenBold("Exception-StackTrace === ")
                + "\r\n"
                + stackTraceString
                + greenBold("!!!!!!!!!!!!!!!!!!!!!");
    }

    public static int testWDBConnection(){
        int ret = -11;
        Connection connTestMaria = null;
        try {

            //STEP 3: Open a connection
            System.out.println("Test-Connecting to database...");
            int i = -1;
            connTestMaria = ConnectionFactoryMariaDb.createConnectionMariaDb();
            if (connTestMaria != null) {
                ret = 1;
            } else {
                infoBox("Failure Test-DB-Connection",
                        "Test-Connecting to MariaDB failed!!!");
                ret = -1;
            }

        }catch(Exception myE){
            infoBox("Failure Test- DB-Connection",
                    "Test-Connecting to MariaDB failed!\r\n"
                            + "Exception was thrown while connecting!!!");
            ret = -2;
        }finally{
            //finally block used to close resources

            try{
                if(connTestMaria !=null)
                    connTestMaria.close();
            }catch(SQLException mySe3){
                infoBox("Failure Test-DB-Connection",
                        "Closing Test-Connection to MariaDB failed!\r\n"
                                + "Exception was thrown while closing!!!");
                ret = -3;
            }//end finally try
        }//end try
    System.out.println("Goodbye!");
    return ret;
    }
}
