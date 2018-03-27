package blackjack;

import java.sql.*;

public class Tietokanta {
    private static final String etsiPelaaja = "select count(nimi) as lkm from pelaaja where nimi=?";
    private static final String haePelaaja = "select pelaajaID, nimi, saldo from pelaaja where nimi=?";
    private static final String lisaaPelaaja = "insert into pelaaja (nimi, saldo) values (?, ?)";
    private static final String tallennaVoittoHavio = "insert into voittohavio (pelaajaID, voittohavio) values(?, ?)";
    
    // luo yhteys
    public static Connection luoYhteys(String kayttajanimi, String passu, String tkOsoite, String ajuri) throws Exception {
        try {
            Class.forName(ajuri).newInstance();
            return DriverManager.getConnection(tkOsoite, kayttajanimi, passu);
        } catch (SQLException e) {
            throw new Exception("Yhteyden avaaminen ei onnistu", e);
        }
    }
    
    // katso onko pelaaja tietokannassa
    // jos pelaaja löytyy tietokannasta, niin count(nimi) palauttaa arvon 1
    // -> haetaan pelaajan nimellä tiedot ja luodaan uusi PelaajanKasi-olio tietojen pohjalta
    // jos pelaajaa ei löydy, niin count(nimi) palauttaa arvon 0
    // -> luodaan uusi pelaaja tietokantaan käyttäen syötettyä nimeä
    public static PelaajanKasi etsiPelaaja(String nimi, Connection yhteys) throws Exception {
        try {
            PreparedStatement pelaajanEtsiminen = yhteys.prepareStatement(etsiPelaaja);
            pelaajanEtsiminen.setString(1, nimi);
            ResultSet rs = pelaajanEtsiminen.executeQuery();
            rs.next();
            
            if (rs.getInt("lkm") == 0) {
                return luoUusiPelaaja(nimi, yhteys);
            } else {
                return haePelaaja(nimi, yhteys);
            }
            
        } catch (Exception e) {
            throw new Exception("Ongelma pelaajan etsimisessä", e);
        }
    }
    
    // luo uusi pelaaja tietokantaan
    public static PelaajanKasi luoUusiPelaaja(String nimi, Connection yhteys) throws Exception {
        // luodaan uusi pelaaja tietokantaan
        int pelaajaID;
        PelaajanKasi pk = null;
        try {
            // Lisätään pelaaja
            PreparedStatement pelaajanLisays = yhteys.prepareStatement(lisaaPelaaja);
            pelaajanLisays.setString(1, nimi);
            pelaajanLisays.setInt(2, 200);
            pelaajanLisays.execute();
            pelaajanLisays.close();
            
            // haetaan pelaajaID ja luodaan PelaajanKasi -olio.
            PreparedStatement pelaajaKysely = yhteys.prepareStatement(haePelaaja);
            pelaajaKysely.setString(1, nimi);
            ResultSet rs = pelaajaKysely.executeQuery();
            rs.next();
            pelaajaID = rs.getInt("pelaajaID");
            pk = new PelaajanKasi(nimi, pelaajaID, Blackjack.pakka, 200);
        } catch (SQLException e) {
            throw new Exception("Ongelma uuden pelajaan luomisessa", e);
        }
        return pk;
    }
    
    // hae käyttäjän tiedot tietokannasta
    public static PelaajanKasi haePelaaja(String pelaaja, Connection yhteys) throws Exception {
        int saldo, pelaajaID;
        PelaajanKasi pk = null;
        try {
            PreparedStatement pelaajaKysely = yhteys.prepareStatement(haePelaaja);
            pelaajaKysely.setString(1, pelaaja);
            ResultSet rs = pelaajaKysely.executeQuery();
            rs.next();
            saldo = rs.getInt("saldo");
            pelaajaID = rs.getInt("pelaajaID");
            System.out.println("Pelaaja löytyi!");
            pk = new PelaajanKasi(pelaaja, pelaajaID, Blackjack.pakka, 200);

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
    public static void tallennaTilanne(PelaajanKasi pelaaja, Blackjack.Tilanne tilanne, Connection yhteys) throws Exception {
        try {
            PreparedStatement tallenna = yhteys.prepareStatement(tallennaVoittoHavio);
            tallenna.setInt(1, pelaaja.haeId());
            tallenna.setString(2, tilanne.toString());
            tallenna.execute();
            tallenna.close();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
}
