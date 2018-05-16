package blackjack;

import java.sql.*;

public class Tietokanta {
    
    // Tietokantayhteys, tulokset sekä haku- ja lisäyslauseet
    private static Connection tkYhteys;
    private static ResultSet rs;
    private static PreparedStatement pelaajanEtsiminen;
    private static PreparedStatement pelaajanLisays;
    private static PreparedStatement pelaajaKysely;
    private static PreparedStatement tallenna;
    
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
    public static Connection luoYhteys() throws Exception {
        try {
            Class.forName(ajuri).newInstance();
            return DriverManager.getConnection(tkOsoite, kayttajanimi, passu);
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
            tkYhteys = luoYhteys();
            pelaajanEtsiminen = tkYhteys.prepareStatement(etsiPelaaja);
            pelaajanEtsiminen.setString(1, nimi);
            rs = pelaajanEtsiminen.executeQuery();
            rs.next();
            
            if (rs.getInt("lkm") == 0) {
                return luoUusiPelaaja(nimi);
            } else {
                return haePelaaja(nimi);
            }
            
        } catch (Exception e) {
            throw new Exception("Ongelma pelaajan etsimisessä", e);
        } finally {
            pelaajanEtsiminen.close();
            rs.close();
        }
    }
    
    // Luo uusi pelaaja tietokantaan
    // Virhetilanteessa palautuu 'null' PelaajanKasi-olio
    public static PelaajanKasi luoUusiPelaaja(String nimi) throws Exception {
        // luodaan uusi pelaaja tietokantaan
        int pelaajaID;
        PelaajanKasi pk = null;
        try {
            // Lisätään pelaaja
            pelaajanLisays = tkYhteys.prepareStatement(lisaaPelaaja);
            pelaajanLisays.setString(1, nimi);
            pelaajanLisays.setInt(2, 200);
            pelaajanLisays.execute();
            
            // Haetaan pelaajaID ja luodaan PelaajanKasi -olio.
            pelaajaKysely = tkYhteys.prepareStatement(haePelaaja);
            pelaajaKysely.setString(1, nimi);
            rs = pelaajaKysely.executeQuery();
            rs.next();
            pelaajaID = rs.getInt("pelaajaID");
            pk = new PelaajanKasi(nimi, pelaajaID, 200);
        } catch (SQLException e) {
            throw new Exception("Ongelma uuden pelajaan luomisessa", e);
        } finally {
            pelaajanLisays.close();
            pelaajaKysely.close();
            rs.close();
            tkYhteys.close();
            return pk;
        }
        
    }
    
    // hae käyttäjän tiedot tietokannasta
    // Virheiden sattuessa palautuu 'null' PelaajanKasi-olio
    public static PelaajanKasi haePelaaja(String nimi) throws Exception {
        int saldo, pelaajaID;
        PelaajanKasi pk = null;
        try {
            pelaajaKysely = tkYhteys.prepareStatement(haePelaaja);
            pelaajaKysely.setString(1, nimi);
            rs = pelaajaKysely.executeQuery();
            rs.next();
            saldo = rs.getInt("saldo");
            pelaajaID = rs.getInt("pelaajaID");
            //System.out.println("Pelaaja löytyi!");
            pk = new PelaajanKasi(nimi, pelaajaID, saldo);
        } catch (SQLException e) {
            throw new Exception("Ongelma pelaaja hakemisessa", e);
        } finally {
            rs.close();
            pelaajaKysely.close();
            tkYhteys.close();
            return pk;
        }
    }

    // Tallenna tiedot
    // Haetaan pelaajan saldo ja id
    // Suoritetaan muokkauslause
    public static void tallennaTilanne(PelaajanKasi pelaaja) throws Exception {
        try {
            tkYhteys = luoYhteys();
            tallenna = tkYhteys.prepareStatement(tallennaVoittoHavio);
            tallenna.setInt(1, pelaaja.haeSaldo());
            tallenna.setInt(2, pelaaja.haeId());
            tallenna.execute();
        } catch (SQLException e) {
            throw new Exception("Ongelma tilanteen tallentamisessa", e);
        } finally {
            tallenna.close();
            tkYhteys.close();
        }
    }
}
