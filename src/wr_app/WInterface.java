package wr_app;

import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hendr_8nkbeie
 */
public interface WInterface {
  public void writeWaehrgList();
  public boolean readWaehrgList();
  public void setUmrechkursInList(int myPos, double myUmrechKurs);
  public void addWaehrgToList(String myKurzName, String myLangName, double myUmrechKurs);
  public List<String> getWaehrungsNameList();
  public void umrechnen();
  public void anzeige();
  public void setWaehrung1(int myWaehrung);
  public void setWaehrung2(int myWaehrung);
  public void setBetrag(double myBetrag);
  public double getBetrag();
  public double getBetragUmgerechnet();
  public boolean insertCurrencyLikeDBToMariaDb(CurrencyLikeDB currency);
    
}
