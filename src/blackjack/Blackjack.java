package blackjack;

import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Blackjack {
    
    static enum Tilanne {voitto, havio}; // voittiko vai hävisikö pelaaja
    static BlackjackIkkuna peli;
    static PelaajanKasi talo, pelaaja;
    static Korttipakka pakka;
    private static Connection tkYhteys;
    private static String ajuri = "com.mysql.jdbc.Driver",
            tkOsoite = "jdbc:mysql://localhost:3306/blackjack",
            passu = "8(GNbr7y", kayttajanimi = "blackjack";
    
    
    // talon "tekoäly"
    public static void pelaa(PelaajanKasi kasi) {
        peli.napitPaalle(false);
        
        peli.otaKortti(kasi);
        peli.otaKortti(kasi);
        
        while (true) {
            if (kasi.selvitaSumma() < 15) {
                // otetaan kortti
                peli.otaKortti(kasi);
            } else {
                // mene vertaamaan
                break;
            }
        }
        
        vertaa(pelaaja, talo);
    }
    
    
    // verrataan pelaajien käsiä
    public static void vertaa(PelaajanKasi pelaaja, PelaajanKasi talo) {
        StringBuilder sb = new StringBuilder();
        Tilanne voittohavio;
        // katsotan blackjackit
        // metodeiksi?
        
        // Oletetaan että pelaaja häviää
        // Pelaaja voittaa jos:
        // - pelaajalla blackjack, mutta talolla ei
        // - talo ylitti 21 pistettä
        // - pelaaja lähempänä 21 pistettä kuin talo (kumpikaan ei ole ylittänyt 21 pistettä)
        
        if (pelaaja.onBlackjack() && !talo.onBlackjack()) {
            System.out.println("Pelaaja voitti!");
            voittohavio = Tilanne.voitto;
        } else if (talo.onYli21() && !pelaaja.onYli21()) {
            System.out.println("Pelaaja voitti");
            voittohavio = Tilanne.voitto;
        } else if (!pelaaja.onYli21() && !talo.onYli21() && (21-pelaaja.selvitaSumma() < 21-talo.selvitaSumma()) ) {
            System.out.println("Pelaaja voitti!");
            voittohavio = Tilanne.voitto;
        } else {
            System.out.println("Pelaaja hävisi!");
            voittohavio = Tilanne.havio;
        }
        
        
        if (pelaaja.onBlackjack()) {
            sb.append(String.format("%s sai blackjackin!\n", pelaaja.haeNimi()));
        } else {
            sb.append(String.format("%s sai %d pistettä\n", pelaaja.haeNimi(), pelaaja.selvitaSumma()));
        }
        
        if (talo.onBlackjack()) {
            sb.append(String.format("%s sai blackjackin!\n", talo.haeNimi()));
        } else {
            sb.append(String.format("%s sai %d pistettä\n", talo.haeNimi(), talo.selvitaSumma()));
        }
        
        javax.swing.JOptionPane.showMessageDialog(null,
                sb.toString());
        sb = null;
        
        try {
            Tietokanta.tallennaTilanne(pelaaja, voittohavio, tkYhteys);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // Kysytään pelaajan nimi
    public static String kysyNimi() {
        String nimi;
        
        do {
            nimi = JOptionPane.showInputDialog(null, "Kerro pelinimesi");
            nimi = nimi.trim();  // poistetaan whitespace-merkit nimen molemminpuolin
        } while (nimi.isEmpty());
        
        return nimi;
    }

    public static void main(String[] args) {
        String pelaajanNimi = kysyNimi();
        
        pakka = new Korttipakka(1);
        talo = new PelaajanKasi("Talo", 0, pakka, 200);
        
        try {
            tkYhteys = Tietokanta.luoYhteys(kayttajanimi, passu, tkOsoite, ajuri);
            pelaaja = Tietokanta.etsiPelaaja(pelaajanNimi, tkYhteys);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        peli = new BlackjackIkkuna(pelaaja, talo, pakka);
        peli.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        peli.setVisible(true);
    }
}
