package blackjack;

public class Kortti {
    private int arvo;
    private String maa;
    
    public Kortti(int arvo, String maa) {
        this.arvo = arvo;
        this.maa = maa;
    }
    
    public int getArvo() {
        return this.arvo;
    }
    
    public void setArvo(int uusiArvo) {
        this.arvo = uusiArvo;
    }
    
    public String getMaa() {
        return this.maa;
    }
    
    public void setMaa(String uusiMaa) {
        this.maa = uusiMaa;
    }
    
    @Override
    public String toString() {
        return this.maa;
    }
}
