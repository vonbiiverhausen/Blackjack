package blackjack;

import java.util.ArrayList;

public class PelaajanKasi {
    final private ArrayList<Kortti> kortit;
    private boolean onBlackJack, yli21;
    private String nimi;
    private int saldo,
            id,
            panos;
    private Korttipakka pakka;
    
    public PelaajanKasi(String nimi, int id, int saldo) {
        this.kortit = new ArrayList<>();
        this.onBlackJack = false;
        this.yli21 = false;
        this.nimi = nimi;
        this.saldo = saldo;
        this.id = id;
    }
    
    public void setPakka(Korttipakka pakka) {
        this.pakka = pakka;
    }
    
    public String haeNimi() {
        return this.nimi;
    }
    
    public int haeId() {
        return this.id;
    }
    
    public int haeSaldo() {
        return this.saldo;
    }
    
    public boolean onBlackjack() {
        return this.onBlackJack;
    }
    
    public boolean onYli21() {
        return this.yli21;
    }
    
    public void otaKortti() {
        this.kortit.add(pakka.jaaKortti());
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
        
        if (kortit.size()==2 && summa == 21) {
            this.onBlackJack = true;
        }
        
        return summa;
    }
    
    // palauttaa pelaajan käden kortin indeksissä 'indeksi'
    public String testaaKortti(int indeksi) {
        return this.kortit.get(indeksi).toString();
    }
    
    public String naytaKasi() {
        return kortit.get(kortit.size()-1).toString();
    }
    
    public void nollaaKasi() {
        this.kortit.clear();
        this.onBlackJack = false;
        this.yli21 = false;
    }
    
    public void saiYli21() {
        this.yli21 = true;
    }
    
    public void asetaPanos(int panos) {
        this.panos = panos;
        this.saldo -= panos;
    }
    
    public void voita() {
        saldo += 2*panos;
    }
}
