import java.util.ArrayList;
import java.util.Random;

public class LeersessieState {
    private int leervorm = 1;
    private int vanaf;
    private int totenmet;
    private boolean startVoorkant;
    private int index;
    private int filterPointer;
    private int aantalInFilter;
    private int moduleStart;
    private int moduleEinde;
    private boolean isVraag = true;
    private boolean isVoorkant = true;
    private String module;
    private boolean isnietGoed;
    private boolean isRandom;
    private boolean noCards = true;
    private ArrayList<Integer> filterKaarten = new ArrayList<>();
    private ArrayList<Kaart> kaarten = new ArrayList<>();

    public LeersessieState() {

    }

    public void init(boolean startVoorkant, boolean isRandom, boolean isNietGoed) {
        index = 0;
        vanaf = 0;
        totenmet = kaarten.size() - 1;
        module = "";
        this.startVoorkant = startVoorkant;
        this.isRandom = isRandom;
        this.isnietGoed = isNietGoed;
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

    public void setVanaf(int vanaf) {
        this.vanaf = vanaf;
    }

    public void setTotenmet(int totenmet) {
        this.totenmet = totenmet;
    }

    public void setIsRandom(boolean x) {
        isRandom = x;
    }

    public void bouwFilter() {
        filterKaarten.clear();
        for (int x = 0; x < kaarten.size(); x++) {
            Kaart e = kaarten.get(x);
            boolean inFilter = true;
            if (x < vanaf-1 || x > totenmet) {
                inFilter = false;
            }
            if (isnietGoed && e.getGekendVoorkant().equals("goed")) {
                inFilter = false;
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
                    return;
                }
                filterPointer = 0;
                for (int x = 0; x < filterKaarten.size() && !gevonden;x++) {
                    if (filterKaarten.get(x) > index ) {
                        filterPointer = x;
                        gevonden = true;
                    }
                }
            }
            index = filterKaarten.get(filterPointer);
            noCards = false;
            moduleStart = filterKaarten.get(0);
            moduleEinde = filterKaarten.get(filterKaarten.size()-1);
        } else {
            noCards = true;
        }
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

    public boolean isStartVoorkant() {
        return startVoorkant;
    }

    public void setStartVoorkant(boolean startVoorkant) {
        this.startVoorkant = startVoorkant;
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

    public void setIsnietGoed(boolean isnietGoed) {
        this.isnietGoed = isnietGoed;
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
            moduleStart = positie-1;
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
}
