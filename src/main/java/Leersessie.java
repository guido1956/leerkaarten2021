/**
 * todo: nadenken over het switchmechanisme
 */
public class Leersessie {
    private static final int VRAAG_ANTWOORD = 1;
    private static final int ANTWOORD_VRAAG = 2;
    private static final int VRAAG_ANTWOORD_SCHRIJVEN = 3;
    private static final int ANTWOORD_VRAAG_SCHRIJVEN = 4;

    private int leervorm = VRAAG_ANTWOORD;
    private String naamKaartenbak;
    private int oefentijd;
    private boolean isVraag = true;
    private String module = "";

    public Leersessie() {
        setLeervorm(VRAAG_ANTWOORD);
    }

    public void init() {

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


    public int getOefentijd() {
        return oefentijd;
    }

    public void setOefentijd(int oefentijd) {
        this.oefentijd = oefentijd;
    }

    public boolean getIsVraag() {
        return isVraag;
    }

    public void switchgetIsVraag() {
        isVraag = !isVraag;
    }

    public boolean isVraag() {
        return isVraag;
    }

    public void setVraag(boolean vraag) {
        isVraag = vraag;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
