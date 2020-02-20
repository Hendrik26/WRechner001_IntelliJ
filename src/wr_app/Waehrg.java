package wr_app; ///

class Waehrg{
 /* public static void main(String[] args){
    //System.out.println("Hello World");
       };*/

private String kurzName = "";
private String langName = "";
private double umrechKurs = 1.0; //Ein $kurzName$ sind  $umrechKurs$ US-Dollar

 
private String intToStr(int myInt){//konveretiert Integer in Strings
  return Integer.toString(myInt);
} 

Waehrg(String myKurzName, String myLangName, double myUmrechKurs){
    this.kurzName = myKurzName;
    this.langName = myLangName;
    this.umrechKurs = myUmrechKurs;
	System.out.println("Neue Waehrung erzeugt! ");
  }
  

//private Button button1;//Hier definieren //Syntax-Bsp

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
