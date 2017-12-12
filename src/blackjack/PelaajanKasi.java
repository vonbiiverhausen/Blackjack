package blackjack;

import java.util.ArrayList;

public class PelaajanKasi {
    final private ArrayList<Kortti> kortit;
    private boolean onBlackJack;
    
    public PelaajanKasi() {
        this.kortit = new ArrayList<>();
        this.onBlackJack = false;
    }
    
    public void otaKortti(Kortti kortti) {
        this.kortit.add(kortti);
    }
    
    public int selvitaSumma() {
    // kortin arvo 2-10:        käden summaan lisätään kortin numeroa vastaava arvo(2 lisätään 2, 3 lisätään 3.)
    // kortin arvo 11-13:       käden summaan lisätään 10
    // kortin arvo 1 (ässä):    käden summaan lisätään 11
        int summa = 0;
        
        for (Kortti kortti : kortit) {
            if (kortti.getArvo() == 1) {
                summa += 11;
            } else if (kortti.getArvo() >= 11 && kortti.getArvo() <= 13) {
                summa += 10;
            } else {
                summa += kortti.getArvo();
            }
        }
        
        if (kortit.size() == 2 && summa == 22) {
            summa -= 10;
        }
        
        return summa;
    }
//    Blackjackiksi kutsutaan tilannetta, jolla pelaajalla on kaksi korttia, joista toinen on arvoltaan 10-13
//    ja toinen ässä. Toteuta luokkaan PelaajanKasi, metodi joka tutkii onko kätenä blackjack.
    private void onkoBlackjack() {
        // kaksi korttia = kortit.size() == 2
        // kaksi korttia; yksi ässä, yksi arvoltaa 10-13
        // Näiden summa on 21
        if (kortit.size()==2 && this.selvitaSumma() == 21) {
            this.onBlackJack = true;
        }
    }
    
    // palauttaa pelaajan käden kortin indeksissä 'indeksi'
    public String testaaKortti(int indeksi) {
        return this.kortit.get(indeksi).toString();
    }
    
    public String naytaKasi() {
        String kasi = "";
        for (Kortti kortti : kortit) {
            kasi += kortti.getMaa()+" ";
        }
        return kasi;
    }
    
    public void nollaaKasi() {
        this.kortit.clear();
        this.onBlackJack = false;
    }
}
