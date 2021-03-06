package wr_app; ///

class CurrencyStandardized {

    private String kurzName = "";
    private String langName = "";
    private double umrechKurs = 1.0; //Ein $kurzName$ sind  $umrechKurs$ US-Dollar

    private String intToStr(int myInt){//konveretiert Integer in Strings
      return Integer.toString(myInt);
    }

    CurrencyStandardized(String myKurzName, String myLangName, double myUmrechKurs){
        this.kurzName = myKurzName;
        this.langName = myLangName;
        this.umrechKurs = myUmrechKurs;
        System.out.println("Neue Waehrung erzeugt! ");
      }

    public String getKurzName(){
        return kurzName;
      }

    public String getLangName(){
        return langName;
      }

    public double getUmrechKurs(){
        return umrechKurs;
      }

    public void setUmrechKurs(double myUmrechKurs){
        this.umrechKurs = myUmrechKurs;
      }

}
