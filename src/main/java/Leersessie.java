/**
 * todo: nadenken over het swithmechanisme
 */
public class Leersessie {
    private static final int VRAAG_ANTWOORD = 1;
    private static final int ANTWOORD_VRAAG = 2;
    private static final int VRAAG_ANTWOORD_SCHRIJVEN = 3;
    private static final int ANTWOORD_VRAAG_SCHRIJVEN = 4;

    private int leervorm = VRAAG_ANTWOORD;
    private String naamKaartenbak;
    private int aantalgekend;
    private int aantalnogNiet;
    private int oefentijd;

    public Leersessie() {

    }

    public void naamKaartenBak(String name) {
        this.naamKaartenbak = name;
    }

    public String getNaamKaartenbak() {
        return naamKaartenbak;
    }

    public int getLeervorm() {
        return leervorm;
    }

    public void setLeervorm(int leervorm) {
        this.leervorm = leervorm;
    }

    public void setNaamKaartenbak(String naamKaartenbak) {
        this.naamKaartenbak = naamKaartenbak;
    }

    public int getAantalgekend() {
        return aantalgekend;
    }

    public void setAantalgekend(int aantalgekend) {
        this.aantalgekend = aantalgekend;
    }

    public int getAantalnogNiet() {
        return aantalnogNiet;
    }

    public void setAantalnogNiet(int aantalnogNiet) {
        this.aantalnogNiet = aantalnogNiet;
    }

    public int getOefentijd() {
        return oefentijd;
    }

    public void setOefentijd(int oefentijd) {
        this.oefentijd = oefentijd;
    }
}
