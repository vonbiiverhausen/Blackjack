package blackjack;

import java.sql.*;

public class Tietokanta {
    
    // Tietokantayhteys
    private static Connection tkYhteys;
    
    // Tiedot yhteyden luomiseen
    private static String ajuri = "com.mysql.jdbc.Driver",
            tkOsoite = "jdbc:mysql://localhost:3306/blackjack",
            passu = "8(GNbr7y",
            kayttajanimi = "blackjack";
    
    // Tietokannan hakulauseet
    private static final String etsiPelaaja = "select count(nimi) as lkm from pelaaja where nimi=?",
            haePelaaja = "select pelaajaID, nimi, saldo from pelaaja where nimi=?",
            lisaaPelaaja = "insert into pelaaja (nimi, saldo) values (?, ?)",
            tallennaVoittoHavio = "UPDATE pelaaja set saldo = ? where pelaajaID = ?";
    
    
    // Tietokantayhteyden luonti
    public static void luoYhteys() throws Exception {
        try {
            Class.forName(ajuri).newInstance();
            tkYhteys = DriverManager.getConnection(tkOsoite, kayttajanimi, passu);
        } catch (SQLException e) {
            throw new Exception("Yhteyden avaaminen ei onnistu", e);
        }
    }
    
    // Katsotaan onko pelaaja tietokannassa
    // jos pelaaja löytyy tietokannasta, niin count(nimi) (alias 'lkm') palauttaa arvon 1
    // -> haetaan pelaajan nimellä tiedot ja luodaan uusi PelaajanKasi-olio tietojen pohjalta
    // jos pelaajaa ei löydy, niin count(nimi) (alias 'lkm') palauttaa arvon 0
    // -> luodaan uusi pelaaja tietokantaan käyttäen syötettyä nimeä
    public static PelaajanKasi etsiPelaaja(String nimi) throws Exception {
        try {
            PreparedStatement pelaajanEtsiminen = tkYhteys.prepareStatement(etsiPelaaja);
            pelaajanEtsiminen.setString(1, nimi);
            ResultSet rs = pelaajanEtsiminen.executeQuery();
            rs.next();
            
            if (rs.getInt("lkm") == 0) {
                return luoUusiPelaaja(nimi);
            } else {
                return haePelaaja(nimi);
            }
            
        } catch (Exception e) {
            throw new Exception("Ongelma pelaajan etsimisessä", e);
        }
    }
    
    // luo uusi pelaaja tietokantaan
    public static PelaajanKasi luoUusiPelaaja(String nimi) throws Exception {
        // luodaan uusi pelaaja tietokantaan
        int pelaajaID;
        PelaajanKasi pk = null;
        try {
            // Lisätään pelaaja
            PreparedStatement pelaajanLisays = tkYhteys.prepareStatement(lisaaPelaaja);
            pelaajanLisays.setString(1, nimi);
            pelaajanLisays.setInt(2, 200);
            pelaajanLisays.execute();
            pelaajanLisays.close();
            
            // haetaan pelaajaID ja luodaan PelaajanKasi -olio.
            PreparedStatement pelaajaKysely = tkYhteys.prepareStatement(haePelaaja);
            pelaajaKysely.setString(1, nimi);
            ResultSet rs = pelaajaKysely.executeQuery();
            rs.next();
            pelaajaID = rs.getInt("pelaajaID");
            pk = new PelaajanKasi(nimi, pelaajaID, 200);
        } catch (SQLException e) {
            throw new Exception("Ongelma uuden pelajaan luomisessa", e);
        }
        return pk;
    }
    
    // hae käyttäjän tiedot tietokannasta
    public static PelaajanKasi haePelaaja(String nimi) throws Exception {
        int saldo, pelaajaID;
        PelaajanKasi pk = null;
        try {
            PreparedStatement pelaajaKysely = tkYhteys.prepareStatement(haePelaaja);
            pelaajaKysely.setString(1, nimi);
            ResultSet rs = pelaajaKysely.executeQuery();
            rs.next();
            saldo = rs.getInt("saldo");
            pelaajaID = rs.getInt("pelaajaID");
            //System.out.println("Pelaaja löytyi!");
            pk = new PelaajanKasi(nimi, pelaajaID, saldo);

            rs.close();
            pelaajaKysely.close();
        } catch (SQLException e) {
            throw new Exception("Ongelma pelaaja hakemisessa", e);
            
        }
        return pk;
    }

    // tallenna tiedot
    // voitot versus häviöt?
    // panos-järjestelmä?
    public static void tallennaTilanne(PelaajanKasi pelaaja) throws Exception {
        try {
            PreparedStatement tallenna = tkYhteys.prepareStatement(tallennaVoittoHavio);
            tallenna.setInt(1, pelaaja.haeSaldo());
            tallenna.setInt(2, pelaaja.haeId());
            tallenna.execute();
            tallenna.close();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
}
