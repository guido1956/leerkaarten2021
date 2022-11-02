import java.util.ArrayList;
import java.util.Random;

public class LeersessieState {
    private int leervorm = 0;
    private int vanaf;
    private int totenmet;
    private int index;
    private int filterPointer;
    private int aantalInFilter;
    private int moduleStart;
    private int moduleEinde;
    private boolean isRange = false;
    private boolean isVraag = true;
    private boolean isVoorkant = true;
    private String module;
    private boolean isnietGoed;
    private boolean isRandom;
    private boolean isNeutraal;
    private boolean isGoed;
    private boolean noCards = true;
    private ArrayList<Integer> filterKaarten = new ArrayList<>();
    private ArrayList<Kaart> kaarten = new ArrayList<>();

    public LeersessieState() {

    }

    public void init() {
        index = 0;
        vanaf = 0;
        totenmet = kaarten.size() - 1;
        module = "";
        this.isRandom = false;
        this.isnietGoed = true;
        this.isNeutraal = true;
        this.isGoed = true;
        this.isVoorkant = true;
        bouwFilter();
    }

    public int getIndex() {
        return index;
    }

    public void volgendeKaart() {


        if (noCards) {
            return;
        }

        if (isVraag) {
            switchIsVraag();
            return;
        }

        if (isRandom) {
            Random random = new Random();
            int x = random.nextInt(filterKaarten.size());
            index = filterKaarten.get(x);
            filterPointer = x;
            switchIsVraag();
            return;
        }

        filterPointer++;
        if (filterPointer >= filterKaarten.size()) {
            filterPointer = 0;
        }

        index = filterKaarten.get(filterPointer);
        switchIsVraag();
    }

    public int getAantalInfilter() {
        return aantalInFilter;
    }

    public void vorigeKaart() {
        if (noCards) {
            return;
        }
        if (isVraag) {
            switchIsVraag();
            return;
        }

        filterPointer--;
        if (filterPointer < 0) {
            filterPointer = 0;
        }
        index = filterKaarten.get(filterPointer);
        switchIsVraag();
    }

    public void switchIsVraag() {
        isVraag = !isVraag;
    }

    public int getVanaf() {
        return vanaf;
    }

    public void setVanaf(int vanaf) {
        this.vanaf = vanaf;
    }

    public void setTotenmet(int totenmet) {
        this.totenmet = totenmet;
    }

    public int getTotenMet() {
        return totenmet;
    }

    public boolean getIsVoorkant() {
        return isVoorkant;
    }

    public void setIsVoorkant(boolean isVoorkant) {
        this.isVoorkant = isVoorkant;
    }

    public void setIsRandom(boolean x) {
        isRandom = x;
    }

    public void setRange(boolean check) {
        isRange = check;
    }

    public void bouwFilter() {
        filterKaarten.clear();
        if (!isRange) {
            vanaf = 0;
            totenmet = kaarten.size();
        }
        isRange = false;
        for (int x = 0; x < kaarten.size(); x++) {
            Kaart e = kaarten.get(x);
            boolean inFilter = true;
            if (x < vanaf - 1 || x > totenmet) {
                inFilter = false;
            }
            if (isVoorkant) {
                if (leervorm == 0) {
                    if (!isnietGoed && e.getGekendVoorkant().equals("niet")) {
                        inFilter = false;
                    }
                    if (!isNeutraal && e.getGekendVoorkant().equals("neutraal")) {
                        inFilter = false;
                    }
                    if (!isGoed && e.getGekendVoorkant().equals("goed")) {
                        inFilter = false;

                    }
                }
                if (leervorm == 1) {
                        if (!isnietGoed && e.getGekendSchrijfVoorkant().equals("niet")) {
                            inFilter = false;
                        }
                        if (!isNeutraal && e.getGekendSchrijfVoorkant().equals("neutraal")) {
                            inFilter = false;
                        }
                        if (!isGoed && e.getGekendSchrijfVoorkant().equals("goed")) {
                            inFilter = false;
                        }
                    }
                }

                if (!isVoorkant) {
                if (leervorm == 0) {
                    if (!isnietGoed && e.getGekendAchterkant().equals("niet")) {
                        inFilter = false;
                    }

                    if (!isNeutraal && e.getGekendAchterkant().equals("neutraal")) {
                        inFilter = false;
                    }

                    if (!isGoed && e.getGekendAchterkant().equals("goed")) {
                        inFilter = false;
                    }
                }

                if (leervorm == 1) {
                    if (!isnietGoed && e.getGekendSchrijfAchterkant().equals("niet")) {
                        inFilter = false;
                    }

                    if (!isNeutraal && e.getGekendSchrijfAchterkant().equals("neutraal")) {
                        inFilter = false;
                    }

                    if (!isGoed && e.getGekendSchrijfAchterkant().equals("goed")) {
                        inFilter = false;
                    }
                }

            }

            if (!module.equals("") && !module.equals(e.getModule())) {
                inFilter = false;
            }
            if (inFilter) {
                filterKaarten.add(x);
            }
        }
        if (filterKaarten.size() != 0) {
            filterPointer = filterKaarten.indexOf(index);
            boolean gevonden = false;
            if (filterPointer == -1) {
                isVraag = true;
                if (isRandom) {
                    Random random = new Random();
                    filterPointer = random.nextInt(filterKaarten.size());
                    index = filterKaarten.get(filterPointer);
                    moduleStart = filterKaarten.get(0);
                    moduleEinde = filterKaarten.get(filterKaarten.size() - 1);
                    aantalInFilter = filterKaarten.size();
                    return;
                }
                filterPointer = 0;
                for (int x = 0; x < filterKaarten.size() && !gevonden; x++) {
                    if (filterKaarten.get(x) > index) {
                        filterPointer = x;
                        gevonden = true;
                    }
                }
            }
            index = filterKaarten.get(filterPointer);
            noCards = false;
            moduleStart = filterKaarten.get(0);
            moduleEinde = filterKaarten.get(filterKaarten.size() - 1);
            aantalInFilter = filterKaarten.size();
        } else {
            noCards = true;
            aantalInFilter = 0;

        }
    }

    public boolean zoekKaart(String zoekString) {
        boolean gevonden = false;
        int kaartIndex = -1;
        for (int x = 0; x < filterKaarten.size() && !gevonden; x++) {
            kaartIndex = filterKaarten.get(x);
            if (kaarten.get(x).getVoorkant().indexOf(zoekString) != -1 ||
                    kaarten.get(x).getAchterkant().indexOf(zoekString) != -1) {
                gevonden = true;
            }
        }

        if (gevonden) {
            index = kaartIndex;
            return true;
        }
        return false;
    }

    public void setModule() {
        moduleStart = 0;
        moduleEinde = kaarten.size() - 1;
        if (moduleEinde < 0) {
            moduleEinde = 0;
        }
    }

    public void setModule(int start, int einde) {
        moduleStart = start;
        moduleEinde = einde;
    }

    public int getLeervorm() {
        return leervorm;
    }

    public void setLeervorm(int leervorm) {
        this.leervorm = leervorm;
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

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public boolean isIsnietGoed() {
        return isnietGoed;
    }

    public void setIsNietGoed(boolean isnietGoed) {
        this.isnietGoed = isnietGoed;
    }

    public void setNeutraal (boolean isNeutraal) {
        this.isNeutraal = isNeutraal;
    }

    public void setGoed (boolean isGoed) {
        this.isGoed = isGoed;
    }


    public ArrayList<Integer> getFilterKaarten() {
        return filterKaarten;
    }


    public ArrayList<Kaart> getKaarten() {
        return kaarten;
    }

    public void setKaarten(ArrayList<Kaart> kaarten) {
        this.kaarten = kaarten;
    }

    public boolean getIsVraag() {
        return isVraag;
    }

    public void gaNaar(int positie) {
        if (positie < kaarten.size()) {
            index = positie - 1;
            moduleStart = positie - 1;
            if (!isVraag) {
                switchIsVraag();
            }
        }
        bouwFilter();
    }

    public boolean getNoCards() {
        return noCards;
    }

    public void totEnMetKaart(int positie) {

        if (positie <= kaarten.size()) {
            index = moduleStart;
            moduleEinde = positie + 1; //@@laatste wijzijging
            if (!isVraag) {
                switchIsVraag();
            }
        }
        bouwFilter();
    }

    public void flipKaarten() {
        for (Kaart e : kaarten) {
            String tijdelijk = e.getAchterkant();
            e.setAchterkant(e.getVoorkant());
            e.setVoorkant(tijdelijk);
        }
    }

    public void setIsVraag(boolean isVraag) {
        this.isVraag = isVraag;
    }


}
