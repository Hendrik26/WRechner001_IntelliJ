package wr_app;

import javax.swing.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class WaehrgDBReader {
    private Connection connMaria = null;
    private int myInt = -1;

    public WaehrgDBReader(){
        this.myInt = -2;
        // if (testWDBConnection() == 1){
            this.firstConnect();
        // }
    }

    public static String format(double i) {
        DecimalFormat f = new DecimalFormat("#0.00");
        double toFormat = ((double) Math.round(i * 100)) / 100;
        return f.format(toFormat).replace(',', '.');
    }

    // public /*WaehrgFromDB[]*/ void getFromDB(){
    public ArrayList<CurrencyStandardized> getCurrenciesStandardizedFromDB()
    throws Exception {
        Statement stmt = null;
        ArrayList<CurrencyStandardized> waehrungsArrayList = new ArrayList<CurrencyStandardized>();
        try{
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = this.connMaria.createStatement();
            String sql;
            // sql = "SELECT id, Waehrgs_Name FROM tbl_waehrgs_name";
            sql = "SELECT Langname, Kurzname, Umrechnungskurs " +
                    "FROM v_wname_rek_umrechkurs_ext";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                DecimalFormat df = new DecimalFormat("0.00##");
                //Retrieve by column name
                CurrencyStandardized cs = new CurrencyStandardized(
                        rs.getString("Kurzname"),
                        rs.getString("Langname"),
                        rs.getDouble("Umrechnungskurs")
                );

                String row = "WaehrungsLangname: " + cs.getLangName()
                        + "; WaehrungsKurzname: " + cs.getKurzName()
                        + "; Umrechnungskurs zu US-Dollar: "
                        + df.format(cs.getUmrechKurs())
                        + ";\r\n";
                System.out.print(row);
                waehrungsArrayList.add(cs);
                System.out.print("Currency added to List!!!\r\n");
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            // return waehrungsArrayList;
        }catch(SQLException se){
            waehrungsArrayList = null;
            String msg = "SQL-Exception getting currencies from MariaDB!";
            infoBox(msg,
                    exceptionText(se));
            se.printStackTrace();
            throw new Exception(msg);
        }catch(Exception e){
            waehrungsArrayList = null;
            String msg = "Exception getting currencies from MariaDB!";
            infoBox(msg,
                    exceptionText(e));
            e.printStackTrace();
            throw new Exception(msg);
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
        return waehrungsArrayList;
    }//end method

    public void insertCurrencyToDB(CurrencyLikeDB currency)
            throws Exception {
        Statement stmt = null;

        try {
            // String sql = "CALL p_insert_currency002('Warschauer Taler', 'WaT', 'PLZ', 0.888);";
            String sqlForm = "CALL p_insert_currency002('%s', '%s', '%s', %s);";
            String umrechStr = format(currency.getUmrechKursuBasisW());
            String sql = String.format(sqlForm, currency.getLangName(), currency.getKurzName(),
                    currency.getBasisWKurzName(), umrechStr );
            System.out.println("//////////////////////////////////////////////////////////");
            System.out.println("//////////////////////////////////////////////////////////");
            System.out.println("//////////////////////////////////////////////////////////");
            System.out.println(sql);
            System.out.println("//////////////////////////////////////////////////////////");
            System.out.println("//////////////////////////////////////////////////////////");
            System.out.println("//////////////////////////////////////////////////////////");

            stmt = this.connMaria.createStatement();
            // ResultSet rs = stmt.executeQuery(sql);
            // stmt.executeQuery(sql);
            System.out.println("Currency written to MariaDB");
        } catch(Exception e){
            System.out.println("Exception writing Currency to MariaDB");
        } finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
                se2.printStackTrace();
            }
            //end finally try
        }//end try
        System.out.println("Goodbye, inserting currency!!!");
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
