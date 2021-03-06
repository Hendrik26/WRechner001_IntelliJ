package wr_test;

import wr_app.WInterface;
import wr_app.WaehrgDBReader;
import wr_app.Waehrungsrechner;

import javax.swing.*;

public class TestWaehrungsrechner {

	private static void calculatingTest() {
		WInterface wI
				= new Waehrungsrechner("europaeischer Euro",
				"US-Dollar", 1.0);
		wI.anzeige();
		angenommeneGleich(wI.getBetragUmgerechnet(), 1.09);

		wI.setWaehrung1(4);
		wI.setWaehrung2(1);
		wI.setBetrag(23.0);
		wI.umrechnen();
		wI.anzeige();
		angenommeneGleich(wI.getBetragUmgerechnet(), 15.64);

		wI.setWaehrung1(1);
		wI.setWaehrung2(8);
		wI.setBetrag(35.0);
		wI.umrechnen();
		wI.anzeige();
		angenommeneGleich(wI.getBetragUmgerechnet(), 151.107);

		wI.setWaehrung1(1);
		wI.setWaehrung2(1);
		wI.setBetrag(35.0);
		wI.umrechnen();
		wI.anzeige();
		angenommeneGleich(wI.getBetragUmgerechnet(), 35.0);

		wI.setWaehrung1(0);
		wI.setWaehrung2(0);
		wI.setBetrag(35.0);
		wI.umrechnen();
		wI.anzeige();
		angenommeneGleich(wI.getBetragUmgerechnet(), 35.0);

		wI.setWaehrung1(0);
		wI.setWaehrung2(1);
		wI.setBetrag(1000.0);
		wI.umrechnen();
		wI.anzeige();
		angenommeneGleich(wI.getBetragUmgerechnet(), 910.5);

		System.out.println("");
		System.out.println("Betrag negativ: zusaetzlicher Fehler moeglich");
		wI.setWaehrung1(0);
		wI.setWaehrung2(0);
		wI.setBetrag(-35.0);
		wI.umrechnen();
		wI.anzeige();
		angenommeneGleich(wI.getBetragUmgerechnet(), -35.0);

		System.out.println("");
		System.out.println("Betrag negativ: zus�tzlicher Fehler moeglich");
		wI.setWaehrung1(1);//europaeischer Euro
		wI.setWaehrung2(0);//US-Dollar
		wI.setBetrag(-100);
		wI.umrechnen();
		wI.anzeige();
		angenommeneGleich(wI.getBetragUmgerechnet(), -109.825);

		System.out.println("");
		System.out.println("Umrechng Europaeischer Euron in Griechischer Euro: Vorzeichenwechsel!!!");
		wI.setWaehrung1(1);//europaeischer Euro
		wI.setWaehrung2(2);//griechischer Euro
		wI.setBetrag(137);
		wI.umrechnen();
		wI.anzeige();
		angenommeneGleich(wI.getBetragUmgerechnet(), -137);
	}


	public static void main(String args[]) {
		// calculatingTest();
		// testHtml();
		testWaehrgDBConnection();
	}

	private static void angenommeneGleich(double a, double b) {
		if (Math.abs(a - b)  < 0.0005) {
			System.out.println(a + " = " + b + " Test positiv");
		} else {
			System.out.println(a + " != " + b + " Test negativ");
		}
		System.out.println("///--------------------------------------");
		System.out.println("                              ");
	}

	private static void infoBox(String titleBar, String infoMessage)
	{
		JOptionPane.showMessageDialog(null, infoMessage,
				"InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	private static boolean objectInstanceExists(Object obj){
		String name = String.format("Object-Instance of %s ",
				obj.getClass().getName());
		// parameterized String
		if (obj != null){
			System.out.println(name + "exists!!!");
			System.out.println("-------------------------------\r\n\r\n");
			return true;
		} else {
			System.out.println(name + "not exists!!!");
			System.out.println("--------------------------------\r\n\r\n");
			return false;
		}
	}

	private static void testHtml(){
		System.out.println("\r\n\r\n-------------------------------------");
		System.out.println("Testing HTML!!!");
		String testHtmlString = "<HTML><p style=\"color:red;\"><b>This is a" +
				" red bold HtmlString!!!</b></p></HTML>";
		System.out.println(testHtmlString);
		infoBox("TestHtmlString", testHtmlString);
		infoBox("TestHtmlString", "HTML:\r\n" + testHtmlString);
		// JEditorPane ed1=new JEditorPane("text/html", testHtmlString);
		System.out.println("Tested HTML!!!");
		System.out.println("Testing Text-formatting!!!");
		// System.out.print("\033[0;1m" + "This text is Bold!!!");
		// System.out.println("\033[0;1m" + "This text is Bold!!!");
		String testFormattedString = "\033[31;1;4mThis text is red, bold " +
				"and underlined. \033[0m";
		System.out.println(testFormattedString);
		infoBox("testFormattedString", testFormattedString);
		System.out.println("Tested Text-formatting!!!");
		System.out.println("--------------------------\r\n\r\n");
	}

	private static void testWaehrgDBConnection(){
		infoBox("TestTitleBar", "Test-InfoMessage");

		System.out.println("Begin Test WaehrgDBConnection!!! \r\n");

		int tc = WaehrgDBReader.testWDBConnection();
		angenommeneGleich(tc, 1);

		System.out.println("Test constructor WaehrgDBReader!!! \r\n");
		WaehrgDBReader wDBR = new WaehrgDBReader();
		objectInstanceExists(wDBR);
		wDBR.closeConnection();
		wDBR.reConnect();

		System.out.println("Get currencies from DB!!! \r\n");
		try {
			wDBR.getCurrenciesStandardizedFromDB();
		} catch (Exception e) {
			System.out.println("\"\\033[31;1;4mExceotion getting "
					+ "currencies from MariaDB. \\033[0m\"");
		}
		System.out.println("End Test WaehrgDBConnection!!! \r\n");
		System.out.println("// -------------------------------------------------------- \r\n\r\n");
	}
}
