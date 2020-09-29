package wr_app; ///

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.text.DecimalFormat;


public class FxWaehrungsrechner extends Application {

    TextField eingabe, ausgabe, kursAendern, neuWLangName, neuWKurzName,
            neuWBasisKurz, neuWKurs;
    String cBox1Value, cBox2Value, cBox3Value;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        WInterface wI = new Waehrungsrechner(this);
        System.out.println("WInterface wI created!!!");
        //Waehrungsrechner rechner = new Waehrungsrechner(this);

		///////////////////////////////////////////////////////////////////
        // Tab01
        TabPane tabPane = new TabPane();
        Tab tab01 = new Tab();
        tab01.setText("Waehrungen umrechnen");
        //tab.setContent(new Rectangle(200,200, Color.LIGHTSTEELBLUE));
        Label waehrung1Label = new Label("AusgangsWaehrung:");
        Label waehrung2Label = new Label("ZielWaehrung:");

        GridPane gridPane01 = new GridPane();
        gridPane01.setAlignment(Pos.CENTER);
        gridPane01.setHgap(50);
        gridPane01.setVgap(15);
        gridPane01.add(waehrung1Label, 0, 0);
        gridPane01.add(waehrung2Label, 1, 0);
        
        /*
        

        ObservableList<String> options = FXCollections.observableArrayList("US-Dollar",
                "australischer Dollar", "britische Pfund", "europaeischer Euro", "kanadische Dollar",
                "Polnischer Zloty", "russischer Rubel", "tschechische Krone", "ungarischer Forint");
        */
        ObservableList<String> options = FXCollections.observableArrayList();
        options.addAll(wI.getWaehrungsNameList()); //letzte Aenderung  

/////////////////////////////////////////////////////////////////////////////		
        ComboBox<String> comboBox1 = new ComboBox<>();
        comboBox1.setItems(options);
        comboBox1.setValue(comboBox1.getItems().get(0));

        ComboBox<String> comboBox2 = new ComboBox<>();
        comboBox2.setItems(options);
        comboBox2.setValue(comboBox2.getItems().get(0));

        gridPane01.add(comboBox1, 0, 2);
        gridPane01.add(comboBox2, 1, 2);

        eingabe = new TextField("0.00");
        gridPane01.add(eingabe, 0, 3);

        ausgabe = new TextField("0.00");
        ausgabe.setDisable(true);
        ausgabe.setOpacity(1.0);
        gridPane01.add(ausgabe, 1, 3);

        BorderPane borderPane01 = new BorderPane();
        borderPane01.setCenter(gridPane01);
        Label seitenEndeLabel = new Label("Ende der Seite");
        borderPane01.setBottom(seitenEndeLabel);
        tab01.setContent(borderPane01);
        //HH
        tabPane.getTabs().add(tab01);

        // Tab02
        Tab tab02 = new Tab();
        tab02.setText("Umrechnungskurs vorhandener Waehrung aendern");

        Label kursAenderLabel = new Label("Kurs aendern: AusgangsWaehrg:");

        GridPane gridPane02 = new GridPane();
        gridPane02.setAlignment(Pos.CENTER);
        gridPane02.setHgap(50);
        gridPane02.setVgap(15);
        gridPane02.add(kursAenderLabel, 0, 0);

        ComboBox<String> comboBoxAendern = new ComboBox<>();
        comboBoxAendern.setValue("europaeischer Euro");
        comboBoxAendern.setItems(options);
        gridPane02.add(comboBoxAendern, 0, 3);

        kursAendern = new TextField("");
        gridPane02.add(kursAendern, 0, 6);

        Button buttonAendern = new Button("Bestaetigen");
        gridPane02.add(buttonAendern, 0, 9);

        BorderPane borderPane02 = new BorderPane();
        borderPane02.setCenter(gridPane02);
        Label seitenEndeLabel02 = new Label("Ende der Seite");
        borderPane02.setBottom(seitenEndeLabel02);
        tab02.setContent(borderPane02);

        //HH
        tabPane.getTabs().add(tab02);

        // Tab03
        Tab tab03 = new Tab();
        tab03.setText("Neue Waehrung hinzufüpgen");
        //tab.setContent(new Rectangle(200,200, Color.LIGHTSTEELBLUE));
        Label neuWaehrgLangName = new Label("Neue Waehrung: LangName");
        Label neuWaehrgKurzName = new Label("Neue Waehrung: KurzName");
        Label neuWaehrgBasisKurz = new Label("Neue Waehrung: BasisKurzName");
        Label neuWaehrgKurs
                = new Label("Neue Waehrung: Wechselkurs zu Basiswaehrung");


        GridPane gridPane03 = new GridPane();
        gridPane03.setAlignment(Pos.CENTER);
        gridPane03.setHgap(50);
        gridPane03.setVgap(15);
        gridPane03.add(neuWaehrgLangName, 0, 0);
        gridPane03.add(neuWaehrgKurzName, 1, 0);
        gridPane03.add(neuWaehrgBasisKurz, 2, 0);
        gridPane03.add(neuWaehrgKurs, 3, 0);


        neuWLangName = new TextField("");
        gridPane03.add(neuWLangName, 0, 6);
        
        neuWKurzName = new TextField("");
        gridPane03.add(neuWKurzName, 1, 6);


        neuWBasisKurz = new TextField("");
        gridPane03.add(neuWBasisKurz, 2, 6);

        neuWKurs = new TextField("");
        gridPane03.add(neuWKurs, 3, 6);

        Button buttonNeu = new Button("Bestaetigen");
        gridPane03.add(buttonNeu, 0, 9);

        BorderPane borderPane03 = new BorderPane();
        borderPane03.setCenter(gridPane03);
        Label seitenEndeLabel03 = new Label("Ende der Seite");
        borderPane03.setBottom(seitenEndeLabel03);

        tab03.setContent(borderPane03);
        //HH
        tabPane.getTabs().add(tab03);
        //////////////////////////////////////////////////////////////////////////////
        comboBox1.setOnAction((event) -> {
            int cBox1Pos = comboBox1.getSelectionModel().getSelectedIndex();
            int cBox2Pos = comboBox2.getSelectionModel().getSelectedIndex();
            wI.setWaehrung1(cBox1Pos);
            wI.setWaehrung2(cBox2Pos);
            String myString = eingabe.getText();
            Double myDouble = Double.parseDouble(myString);
            wI.setBetrag(myDouble);
            wI.umrechnen();
            feld2Write(wI.getBetragUmgerechnet());

        });

        comboBox2.setOnAction((event) -> {
            int cBox1Pos = comboBox1.getSelectionModel().getSelectedIndex();
            int cBox2Pos = comboBox2.getSelectionModel().getSelectedIndex();
            wI.setWaehrung1(cBox1Pos);
            wI.setWaehrung2(cBox2Pos);
            String myString = eingabe.getText();
            Double myDouble = Double.parseDouble(myString);
            wI.setBetrag(myDouble);
            wI.umrechnen();
            feld2Write(wI.getBetragUmgerechnet());

        });
        
        //US-Dollar ist Leitwährung, daher darf keine Ändedrung des Kurses möglich sein!
        comboBoxAendern.setOnAction((event) -> {
           String selectedWaehrg = comboBoxAendern.getSelectionModel().getSelectedItem().trim() ;
           //boolean waehrungAendern = (selectedItem == "US-Dollar "); // funktioniert nicht 
           boolean waehrungAendern = selectedWaehrg.equals("US-Dollar"); // bessere Variante für Stringvergleich
           //buttonAendern.setVisible(!waehrungAendern);
           //kursAendern.setVisible(!waehrungAendern);
           buttonAendern.setDisable(waehrungAendern);
           kursAendern.setDisable(waehrungAendern);
        });

        eingabe.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("")) {
                wI.setBetrag(0.0);
                wI.umrechnen();
                feld2Write(wI.getBetragUmgerechnet());
            } else {
                wI.setBetrag(Double.parseDouble(newValue));
                wI.umrechnen();
                feld2Write(wI.getBetragUmgerechnet());
            }
            System.out.println(wI.getBetragUmgerechnet());

        });

        buttonAendern.setOnAction((event) -> {
            int cBoxAendernPos = comboBoxAendern.getSelectionModel().getSelectedIndex();
            String kursAendernTxt = kursAendern.getText();
            Double kursAendernDbl = Double.parseDouble(kursAendernTxt);
            wI.setUmrechkursInList(cBoxAendernPos, kursAendernDbl);
        });
        
        buttonNeu.setOnAction((event) -> {
            String waehrungNeuTxt = neuWLangName.getText();
            String waehrungNeuKurzTxt = neuWKurzName.getText();
            String waehrungNeuBasisKurz = neuWBasisKurz.getText();
            String kursNeuTxt = neuWKurs.getText();
            Double kursNeuDbl = Double.parseDouble(kursNeuTxt);

            /*
            CurrencyLikeDB currencyLikeDB = new CurrencyLikeDB(waehrungNeuKurzTxt, waehrungNeuTxt,
                    waehrungNeuBasisKurz, kursNeuDbl);
            wI.insertCurrencyLikeDBToMariaDb(currencyLikeDB);*/


            wI.addWaehrgToList(waehrungNeuKurzTxt,waehrungNeuTxt, kursNeuDbl);
            options.add(waehrungNeuTxt);
            //options.clear();
            //options.addAll(wI.getWaehrungsListe());
        });

//////////////////////////////////////////////////////////////////////////////////////////////		
        primaryStage.setTitle("HHs Waehrungsrechner");
        BorderPane root = new BorderPane();
		//root.setTop(tabPane);	
        //&primaryStage.setScene(scene1);
        Scene scene = new Scene(/*root*/tabPane);
        primaryStage.setScene(scene);

        primaryStage.show();
        
        primaryStage.setOnCloseRequest((event) -> { // Ereignis beim Schließen des Fensters
           System.out.println("Ende"); 
           wI.writeWaehrgList();
        });
    
    }
    private void feld1Write(double wert) {
        eingabe.setText(format(wert));
    }

    private void feld2Write(double myWert) {
        String myString = format(myWert);
        ausgabe.setText(myString);
    }

    public static String format(double i) {
        DecimalFormat f = new DecimalFormat("#0.00");
        double toFormat = ((double) Math.round(i * 100)) / 100;
        return f.format(toFormat);
    }
}

	///______________________________________________________________________

