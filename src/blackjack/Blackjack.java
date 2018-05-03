package blackjack;

import javax.swing.JOptionPane;

public class Blackjack {
    
    /** Käytettävä pelimoottori-olio */
    static PeliMoottori pelimoottori;

    /** Kysytään pelaajan nimeä
      * Nimi ei saa olla tyhjä
      * Merkkijonon alusta ja lopusta poistetaan whitespace-merkit trim()-metodilla. Tällöin estetään nimet, joissa on mm.
      * ylimääräisiä välilyöntejä tai koostuu pelkistä välilyönneistä
      */
    public static String kysyNimi() {
        String nimi;
        
        do {
            nimi = JOptionPane.showInputDialog(null, "Kerro pelinimesi");
            nimi = nimi.trim();
        } while (nimi.isEmpty());
        
        return nimi;
    }

    public static void main(String[] args) {
        String pelaajanNimi = null;
        try {
            pelaajanNimi = kysyNimi();
        } catch (NullPointerException npe) {
            System.exit(0);
        }
        pelimoottori = new PeliMoottori(pelaajanNimi);
    }
}
