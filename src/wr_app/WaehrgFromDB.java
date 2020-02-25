package wr_app;

public class WaehrgFromDB {
    private String kurzName = "";
    private String langName = "";
    private String basisWKurzName = "";
    private String basisWLangName = "";

    private double umrechKursZuUSD = 1.0; //Ein $kurzName$ sind  $umrechKurs$ US-Dollar
    private double umrechKursZuBasisW = 1.0; //Ein $kurzName$ sind  $umrechKurs$ $basisWKurzName$

    private String intToStr(int myInt){//konveretiert Integer in Strings
        return Integer.toString(myInt);
    }

    WaehrgFromDB(String myKurzName, String myLangName, String basisWKurzName, String basisWLangName,
                 double myUmrechKursZuUSD, double umrechKursZuBasisW){
        this.setNames(myKurzName, myLangName, basisWKurzName, basisWLangName);
        this.setUmrechKurse(myUmrechKursZuUSD, umrechKursZuBasisW);
        System.out.println("Neue Waehrung_DB erzeugt! ");
    }

    private void setUmrechKurse(double myUmrechKursZuUSD, double umrechKursZuBasisW) {
        this.umrechKursZuUSD = myUmrechKursZuUSD;
        this.umrechKursZuBasisW = umrechKursZuBasisW;
    }

    private void setNames(String myKurzName, String myLangName, String basisWKurzName, String basisWLangName) {
        this.kurzName = myKurzName;
        this.langName = myLangName;
        this.basisWKurzName = basisWKurzName;
        this.basisWLangName = basisWLangName;
    }


//private Button button1;//Hier definieren //Syntax-Bsp

    public String getKurzName(){
        return kurzName;
    }

    public String getLangName(){
        return langName;
    }

    public String getBasisWKurzName(){
        return this.basisWKurzName;
    }

    public String getBasisWLangName(){
        return basisWLangName;
    }

    public double getUmrechKursZuUSD(){
        return this.umrechKursZuUSD;
    }

    public double getUmrechKursuBasisW(){ return this.umrechKursZuBasisW; }

    public void setUmrechKursZuUSD(double umrechKursZuUSD){
        this.umrechKursZuUSD = umrechKursZuUSD;
    }

    public void setUmrechKursZuBasisW(double umrechKursZuBasisW){
        this.umrechKursZuBasisW = umrechKursZuBasisW;
    }

}
