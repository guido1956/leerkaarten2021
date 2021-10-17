import java.util.ArrayList;

public class Kaartenbak {
    private ArrayList<Kaart> kaarten;
    private int index;
    private boolean isVoorkant = true;
    private int moduleStart;
    private int moduleEinde;

    public Kaartenbak(ArrayList<Kaart> kaarten) {
        this.kaarten = kaarten;
        index = 0;
        moduleStart = 0;
        moduleEinde = kaarten.size() -1;
    }

    public Kaartenbak() {
        kaarten = new ArrayList<>();
        index = 0;
        moduleStart = 0;
        moduleEinde = 0;
    }

    public Kaart  volgendeKaart(String filter) {
        int circle = index;
        for (int x = index +1 ; x <= moduleEinde + 1; x++ ) {
            if (x > moduleEinde) {
                x = moduleStart;
            }
            if (x == circle) {
                return kaarten.get(x);
            }
            if (kaarten.get(x).getGekendVoorkant().equals(filter)) {
                index = x;
                return kaarten.get(x);
            }

            if (kaarten.get(x).getGekendAchterkant().equals(filter)) {
                index = x;
                return kaarten.get(x);
            }
        }
        return kaarten.get(index);  // komt niet voor
    }

    public Kaart volgendeKaart() {
        index++;
        if (index > moduleEinde ) {
            index = moduleStart;
        }
        return kaarten.get(index);
    }

    public Kaart getHuidigeKaart() {
        return kaarten.get(index);
    }

    public void setKaart(Kaart kaart) {
        kaarten.set(index, kaart);
    }

    public void addKaart(Kaart kaart) {
        kaarten.add(kaart);
    }

    public ArrayList<Kaart> getKaarten() {
        return kaarten;
    }

    public void setKaarten(ArrayList<Kaart> kaarten) {
        this.kaarten = kaarten;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getModuleStart() {
        return moduleStart;
    }

    public void setModuleStart(int moduleStart) {
        this.moduleStart = moduleStart;
    }

    public int getModuleEinde() {
        return moduleEinde;
    }

    public void setModuleEinde(int moduleEinde) {
        this.moduleEinde = moduleEinde;
    }

    public void setModule() {
        moduleStart = 0;
        moduleEinde = kaarten.size()-1;
        if (moduleEinde < 0) {
            moduleEinde = 0;
        }
    }

    public void setModule(int start, int einde) {
        moduleStart = start;
        moduleEinde = einde;
    }

    public void vulKaarten(String naam) {


    }

    public void switchisVoorkant() {
        isVoorkant = !isVoorkant;
    }

    public boolean getIsVoorkant() {
        return isVoorkant;
    }

}
