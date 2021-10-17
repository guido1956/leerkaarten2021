public class Kaart {
    private String voorkant;
    private String achterkant;
    private String module = "";
    private String gekendVoorkant = "neutraal";
    private String gekendAchterkant = "neutraal";

    public Kaart(String voorkant, String achterkant) {
        this.voorkant = voorkant;
        this.achterkant = achterkant;
    }

    public Kaart(String voorkant, String achterkant, String module) {
        this.voorkant = voorkant;
        this.achterkant = achterkant;
        this.module = module;
    }

    public String getVoorkant() {
        return voorkant;
    }

    public void setVoorkant(String voorkant) {
        this.voorkant = voorkant;
    }

    public String getAchterkant() {
        return achterkant;
    }

    public void setAchterkant(String achterkant) {
        this.achterkant = achterkant;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getGekendVoorkant() {
        return gekendVoorkant;
    }

    public void setGekendAchterkant(String gekend) {
        this.gekendAchterkant = gekend;
    }

    public String getGekendAchterkant() {
        return gekendAchterkant;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Kaart{");
        sb.append("voorkant='").append(voorkant).append('\'');
        sb.append(", achterkant='").append(achterkant).append('\'');
        sb.append(", module='").append(module).append('\'');
        sb.append(", gekend='").append(gekendVoorkant).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
