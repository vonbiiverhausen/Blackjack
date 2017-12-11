package blackjack;

public class Blackjack {

    public static void main(String[] args) {
        Korttipakka pelipakka = new Korttipakka(1);
        PelaajanKasi pelaaja = new PelaajanKasi();
        
        pelipakka.sekoita();
        System.out.println(pelipakka.testaaKortti(0));
        
        pelaaja.otaKortti(pelipakka.jaaKortti());
        System.out.println(pelaaja.selvitaSumma());
        System.out.println(pelaaja.testaaKortti(0));
    }
    
}
