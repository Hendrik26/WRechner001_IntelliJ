package wr_app;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Waehrungsrechner implements WInterface {

    // Klassenvariablen
    int waehrung_1;
    int waehrung_2;
    double betrag;
    double betragUmgerechnet;
    FxWaehrungsrechner fx;
    List<CurrencyStandardized> waehrungsArrayList = new ArrayList<CurrencyStandardized>();

/////////////////////////////////////////////////////////////////////////////
//Konstruktoren
    Waehrungsrechner(FxWaehrungsrechner fx) {
        //this.waehrung1 = "euE";
        //this.waehrung2 = "usD";
        this.betrag = 0.0;
        this.fx = fx;
        //Lesen der Standardwaehrungen aus Datei  
        // oder Setzen der Standardwaehrungen falls das Lesen erfolglos ist
        if (!readWaehrgList())  setWaehrgList(); 
        umrechnen();
        anzeige();
    }
    
    	public Waehrungsrechner(String waehrung1, String waehrung2, double betrag) {
	      ////
              this.betrag = betrag;
             //this.fx = fx;
             //Lesen der Standardwaehrungen aus Datei  
            // oder Setzen der Standardwaehrungen falls das Lesen erfolglos ist
            if (!readWaehrgList())  setWaehrgList(); 
            umrechnen();
            anzeige();
            
	}

////////////////////////////////////////////////////////////////////////////
    private void setWaehrgList() {  //Setzen der Standardwaehrungen

        CurrencyStandardized newWaehrung = new CurrencyStandardized("USD", "US-Dollar", 1.0);//0
        waehrungsArrayList.add(newWaehrung);
        newWaehrung = new CurrencyStandardized("euEu", "europaeischer Euro", 1.09825);//1
        waehrungsArrayList.add(newWaehrung);
        newWaehrung = new CurrencyStandardized("euEu", "griechischer Euro", -1.09825);//2
        waehrungsArrayList.add(newWaehrung);
        newWaehrung = new CurrencyStandardized("brP", "britische Pfund", 1.42021);//3
        waehrungsArrayList.add(newWaehrung);
        newWaehrung = new CurrencyStandardized("kaD", "kanadische Dollar", 0.74994);//4
        waehrungsArrayList.add(newWaehrung);
        newWaehrung = new CurrencyStandardized("auD", "australischer Dollar", 0.74260);//5
        waehrungsArrayList.add(newWaehrung);
        newWaehrung = new CurrencyStandardized("ruR", "russischer Rubel", 0.01397);//6
        waehrungsArrayList.add(newWaehrung);
        newWaehrung = new CurrencyStandardized("unF", "ungarischer Forint", 0.00355);//7
        waehrungsArrayList.add(newWaehrung);
        newWaehrung = new CurrencyStandardized("poZ", "Polnischer Zloty", 0.25368);//8
        waehrungsArrayList.add(newWaehrung);
        newWaehrung = new CurrencyStandardized("tsK", "tschechische Krone", 0.04058);//9
        waehrungsArrayList.add(newWaehrung);
        System.out.println("Alle Standard-Waehrung erzeugt! ");

    }

    public void writeWaehrgList() {  //Schreiben der Standardwaehrungen in Datei
        // nach Bsp. von http://blog.mynotiz.de/programmieren/java-text-in-eine-datei-schreiben-450/
        File waehrungFile = new File("Waehrungen.txt");
        try {
       // new FileWriter(file ,true) - falls die Datei bereits existiert
            // werden die Bytes an das Ende der Datei geschrieben

       // new FileWriter(file) - falls die Datei bereits existiert
            // wird diese überschrieben
            FileWriter writer = new FileWriter(waehrungFile);

            for (CurrencyStandardized wObj : waehrungsArrayList) { //entspricht "foreach" (VB, C#)
                String zeile = wObj.getLangName() + "; ";
                zeile += wObj.getKurzName() + "; ";
                zeile += new Double(wObj.getUmrechKurs()).toString();
                writer.write(zeile);
                writer.write(System.getProperty("line.separator"));
            }
       // Schreibt den Stream in die Datei
            // Sollte immer am Ende ausgeführt werden, sodass der Stream 
            // leer ist und alles in der Datei steht.
            writer.flush();

            // Schließt den Stream
            writer.close();
            System.out.println("Alle Standard-Waehrung in Datei geschrieben! ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean readWaehrgList() {  //Lesen der Standardwaehrungen aus Datei
        // nach Bsp. von http://javabeginners.de/Ein-_und_Ausgabe/Eine_Datei_zeilenweise_auslesen.php
        BufferedReader br = null;
        boolean fileLesenOk = false;
        try {
            br = new BufferedReader(new FileReader(new File("Waehrungen.txt")));
            String line = null;
            while ((line = br.readLine()) != null) {
              // Ganze Zeile:
                // System.out.println(line);                
                String[] parts = line.split(";"); 
                String langName = parts[0].trim();
                String kurzName = parts[1].trim();
                Double umrechKurs = Double.parseDouble(parts[2].trim());
                CurrencyStandardized newWaehrung = new CurrencyStandardized(kurzName, langName, umrechKurs);
                waehrungsArrayList.add(newWaehrung);
            }
            System.out.println("Alle Standard-Waehrung aus Datei gelesen! ");
            fileLesenOk = true;
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der Waehrungsdatei!");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Fehler beim Lesen der Waehrungsdatei!");
                }
            }
        }
        return fileLesenOk;
    }

    public void setUmrechkursInList(int myPos, double myUmrechKurs) {
        waehrungsArrayList.get(myPos).setUmrechKurs(myUmrechKurs);
    }

    public void addWaehrgToList(String myKurzName, String myLangName, double myUmrechKurs) {
        CurrencyStandardized newWaehrung = new CurrencyStandardized(myKurzName, myLangName, myUmrechKurs);
        waehrungsArrayList.add(newWaehrung);
    }

    public List<String> getWaehrungsNameList() {
        List<String> waehrungsListe = new ArrayList<String>();
        for (CurrencyStandardized wObj : waehrungsArrayList) {
            waehrungsListe.add(wObj.getLangName());
        }
        return waehrungsListe;
    }

    public void umrechnen() {
        CurrencyStandardized tempWaehrg;
        double usDZwischen;

        System.out.println("Methode umrechnen: Waehrungen gesetzt! ");

        usDZwischen = betrag * waehrungsArrayList.get(waehrung_1).getUmrechKurs();
        System.out.println("Methode umrechnen: In Zwischenwaehrg USD umgerechnet! ");

        betragUmgerechnet = usDZwischen / waehrungsArrayList.get(waehrung_2).getUmrechKurs();;
        System.out.println("Methode umrechnen: Komplett umgerechnet! ");
    }

    public void anzeige() {
        System.out.println("");
        System.out.println("Waehrung 1: " + waehrungsArrayList.get(waehrung_1).getLangName());
        System.out.println("Waehrung 2: " + waehrungsArrayList.get(waehrung_2).getLangName());
        System.out.println("Betrag-Eingabe: " + betrag);
        System.out.println("Betrag-Ausgabe: " + betragUmgerechnet);
    }

      // wichtige Setter und Getter
    public void setWaehrung1(int myWaehrung) {
        waehrung_1 = myWaehrung;
    }

    public void setWaehrung2(int myWaehrung) {
        waehrung_2 = myWaehrung;
    }

    public void setBetrag(double myBetrag) {
        betrag = myBetrag;
    }

    public double getBetrag() {
        return betrag;
    }

    public double getBetragUmgerechnet() {
        return betragUmgerechnet;
    }
}
