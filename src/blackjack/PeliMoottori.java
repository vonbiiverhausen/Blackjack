package blackjack;

/** Pelimoottori-olio
  * Olion tarkoituksena on huolehtia ei-graafisten elementtien toiminnasta, kuten Korttipakka- ja PelaajanKasi-olioiden luonnista
  * @author Kari Piukka
  */
public class PeliMoottori {
    
    private Korttipakka korttipakka;
    private PelaajanKasi talo, pelaaja;
    private BlackjackIkkuna peliIkkuna;
    static enum Tilanne {voitto, havio};
    
    /** Konstruktori
      * Luodaan Korttipakka-olio yhdellä pakalla
      * Luodaan PelaajanKasi-olio 'talo' ja kerrotaan tälle mitä Korttipakka-oliota käytetään
      * 
      * PelaajanKasi-olion 'pelaaja' kohdalla tehdään tietokantahaku, annetulla nimellä. Jos nimeä ei löydy tietokannasta,
      * niin luodaan tietokantaan uusi pelaaja ja palautetaan PelaajanKasi-olio. Jos nimi löytyy tietokannasta,
      * haetaan tiedot ja luodaan PelaajanKasi-olio näiden tietojen pohjalta
      * Molemmissa tapauksissa 'pelaajalle' kerrotaan mitä Korttipakka-oliota käytetään 
      * 
      * Luodaan BlackjackIkkuna-olio, joka vastaa pelin graafisista elementeistä
      */
    public PeliMoottori(String pelaajanNimi) {
        
        // Luodaan korttipakka
        korttipakka = new Korttipakka(1);
        talo = new PelaajanKasi("Talo", 0, 0);
        talo.setPakka(korttipakka); // Kerrotaan talolle mitä korttipakkaa käytetään
        
        // Yritetään etsiä pelaajan tiedot tietokannasta ja palauttaa PelaajanKasi-olio
        try {
            Tietokanta.luoYhteys();
            pelaaja = Tietokanta.etsiPelaaja(pelaajanNimi);
            pelaaja.setPakka(korttipakka); // Kerrotaan pelaajalle mitä korttipakkaa käytetään
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // Luodaan peli-ikkuna. Lisätään otsikkoon pelaajan nimi. Laitetaan ikkuna näkviin
        peliIkkuna = new BlackjackIkkuna(this);
        peliIkkuna.asetaOtsikko(pelaaja.haeNimi());
        peliIkkuna.setVisible(true);
        
        peliIkkuna.asetaTalonNimi(talo.haeNimi());
        peliIkkuna.naytaSaldo(pelaaja.haeSaldo());
        peliIkkuna.asetaPelaajanNimi(pelaaja.haeNimi());
        peliIkkuna.naytaPelaajanPisteet(String.valueOf(pelaaja.selvitaSumma()));
        peliIkkuna.naytaTalonPisteet(String.valueOf(talo.selvitaSumma()));
    }
    
    /** Metodi nollaa korttipakan, pelaajien kädet ja asettaa uuden panoksen
      * @param panos millä panoksella peli aloitetaan
      */
    public void aloitaPeli(int panos) {
        // Nollataan kortit: Korttipakka ja pelaajien kädet
        korttipakka.nollaaPakka();
        talo.nollaaKasi();
        pelaaja.nollaaKasi();
        
        // Nollaa pisteet
        peliIkkuna.naytaPelaajanPisteet("0");
        peliIkkuna.naytaTalonPisteet("0");
        
        // Nollaa taulu
        peliIkkuna.nollaaListat();
        
        // Päivitä peli-ikkunan panos- ja saldotiedot
        this.pelaaja.asetaPanos(panos);
        peliIkkuna.paivitaSaldo(pelaaja.haeSaldo(), panos);
        
        // Peli alkaa kahden kortin jaolla
        pelaajaOttaaKortin();
        pelaajaOttaaKortin();
        
        // Napit päälle, jotta pelaaja voi pelata
        peliIkkuna.napitPaalle(true);
    }
    
    
    
    public void taloOttaaKortin() {
    // Otetaan uusi kortti
        talo.otaKortti();
        
        // Selvitetään summa kortinoton jälkeen
        int pisteet = talo.selvitaSumma();
        
        peliIkkuna.paivitaTalonKortit(this.talo.naytaKasi());
            if (talo.onBlackjack()) {
                // Merkitään että talo sai blackjackin. Siirrytään pisteiden vertaukseen
                peliIkkuna.naytaTalonPisteet(String.valueOf(pisteet) + " BLACKJACK!");
            } else if (pisteet > 21) {
                // Merkitään että talo ylitti 21 pistettä. Siirrytään pisteiden vertaukseen
                peliIkkuna.naytaTalonPisteet(String.valueOf(pisteet) + " YLI 21!");
                talo.saiYli21();
            } else {
                peliIkkuna.naytaTalonPisteet(String.valueOf(pisteet));
            }
    }
    
    public void pelaajaOttaaKortin() {
        // Otetaan uusi kortti
        pelaaja.otaKortti();

        // Selvitetään summa kortinoton jälkeen
        int pisteet = pelaaja.selvitaSumma();

        peliIkkuna.paivitaPelaajanKortit(this.pelaaja.naytaKasi());
        if (pelaaja.onBlackjack()) {
            // merkitään että on saatu blackjack. Vuoro siirtyy talolle
            peliIkkuna.naytaPelaajanPisteet(String.valueOf(pisteet) + " BLACKJACK!");
            taloPelaa();
        } else if (pisteet > 21) {
            // merkitään että on ylitetty 21 pistettä. Vuoro siirtyy talolle
            peliIkkuna.naytaPelaajanPisteet(String.valueOf(pisteet) + " YLI 21!");
            pelaaja.saiYli21();
            taloPelaa();
        } else {
            peliIkkuna.naytaPelaajanPisteet(String.valueOf(pisteet));
        }

    }
    
    /** Talon tekoäly
      * Peli-ikkunan napit otetaan pois käytettävistä, jotta pelaaja ei voi enää ottaa kortteja tai pelata kättään
      * Aluksi jaetaan kaksi korttia blackjackin sääntöjen mukaisesti
      * Otetaan kortteja kun talon pisteet ovat alle 15
      * Muutoin lopetataan ja siirrytään vertaukseen
      */
    public void taloPelaa() {
        peliIkkuna.napitPaalle(false);
        
        taloOttaaKortin();
        taloOttaaKortin();
        
        while (true) {
            if (talo.selvitaSumma() < 15) {
                // otetaan kortti
                taloOttaaKortin();
            } else {
                // mene vertaamaan
                break;
            }
        }
        
        vertaa(pelaaja, talo);
    }
    
    
    /** Pelaajien pelitilanteen vertaus ja yhteenveto
      * Oletetaan että pelaaja häviää. Pelaaja voittaa jos:
      * - pelaajalla blackjack, mutta talolla ei
      * - talo ylitti 21 pistettä, mutta pelaaja ei
      * - pelaaja lähempänä 21 pistettä kuin talo (kumpikaan ei ole ylittänyt 21 pistettä)
      * 
      * Yhteenvedossa ilmoitetaan pelaajan ja talon pisteet tai viesti Blackjackista
      * Samalla ilmoitetaan onko pelaaja voittanut vai hävinnyt
      * 
      * Yhteenveto luodaan StringBuilder-oliolla, koska yhteenvedon luonnossa syntyy paljon "turhia" String-olioita
      * 
      * Tiedot tallennetaan tietokantaan
      * 
      * @param pelaaja pelaajan PelaajanKasi-olio
      * @param talo talon PelaajanKasi-olio
      */
    public void vertaa(PelaajanKasi pelaaja, PelaajanKasi talo) {
        StringBuilder sb = new StringBuilder();
        Tilanne voittohavio;
        
        if (pelaaja.onBlackjack() && !talo.onBlackjack()) { // pelaajalla blackjack, mutta talolla ei
            voittohavio = Tilanne.voitto;
        } else if (talo.onYli21() && !pelaaja.onYli21()) { // talo ylitti 21 pistettä, mutta pelaaja ei
            voittohavio = Tilanne.voitto;
        } else if (!pelaaja.onYli21() && !talo.onYli21() && (21-pelaaja.selvitaSumma() < 21-talo.selvitaSumma()) ) { // pelaaja lähempänä 21 pistettä kuin talo (kumpikaan ei ole ylittänyt 21 pistettä)
            voittohavio = Tilanne.voitto;
        } else {
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
        
        if (voittohavio == Tilanne.havio) {
            sb.append("Pelaaja hävisi");
            
        } else {
            sb.append("Pelaaja voitti!");
            // pelaajan saldo += 2*panos
            pelaaja.voita();
            peliIkkuna.naytaSaldo(pelaaja.haeSaldo());
        }
        
        javax.swing.JOptionPane.showMessageDialog(null, sb.toString());
        sb = null;
        
        try {
            Tietokanta.tallennaTilanne(pelaaja);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
