public class Kaart {
    private String voorkant;
    private String achterkant;
    private String module = "";
    private String gekendVoorkant = "neutraal";
    private String gekendAchterkant = "neutraal";
    private String gekendSchrijfVoorkant = "neutraal";
    private String gekendSchrijfAchterkant = "neutraal";

    public Kaart(String voorkant, String achterkant) {
        this.voorkant = voorkant;
        this.achterkant = achterkant;
    }

    public Kaart(String voorkant, String achterkant, String module) {
        this.voorkant = voorkant;
        this.achterkant = achterkant;
        this.module = module;
    }


    public Kaart(String voorkant, String achterkant, String module, String gekendVoorkant, String gekendAchterkant) {
        this.voorkant = voorkant;
        this.achterkant = achterkant;
        this.module = module;
        this.gekendVoorkant = gekendVoorkant;
        this.gekendAchterkant = gekendAchterkant;
    }

    public Kaart(String voorkant, String achterkant, String module, String gekendVoorkant, String gekendAchterkant,
                 String gekendSchrijfVoorkant, String gekendSchrijfAchterkant) {
        this.voorkant = voorkant;
        this.achterkant = achterkant;
        this.module = module;
        this.gekendVoorkant = gekendVoorkant;
        this.gekendAchterkant = gekendAchterkant;
        this.gekendSchrijfVoorkant = gekendSchrijfVoorkant;
        this.gekendSchrijfAchterkant = gekendSchrijfAchterkant;
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

    public void setGekendVoorkant(String gekend) {
        this.gekendVoorkant = gekend;
    }

    public String getGekendAchterkant() {
        return gekendAchterkant;
    }

    public void setGekendAchterkant(String gekend) {
        this.gekendAchterkant = gekend;
    }

    public String getGekendSchrijfVoorkant() {
        return gekendSchrijfVoorkant;
    }

    public void setGekendSchrijfVoorkant(String gekendSchrijfVoorkant) {
        this.gekendSchrijfVoorkant = gekendSchrijfVoorkant;
    }

    public String getGekendSchrijfAchterkant() {
        return gekendSchrijfAchterkant;
    }

    public void setGekendSchrijfAchterkant(String gekendSchrijfAchterkant) {
        this.gekendSchrijfAchterkant = gekendSchrijfAchterkant;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Kaart{");
        sb.append("voorkant='").append(voorkant).append('\'');
        sb.append(", achterkant='").append(achterkant).append('\'');
        sb.append(", module='").append(module).append('\'');
        sb.append(", gekendVoorkant='").append(gekendVoorkant).append('\'');
        sb.append(", gekendAchterkant='").append(gekendAchterkant).append('\'');
        sb.append(", gekendSchrijfVoorkant='").append(gekendSchrijfVoorkant).append('\'');
        sb.append(", gekendSchrijfAchterkant='").append(gekendSchrijfAchterkant).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
