import java.util.ArrayList;
import java.util.Random;

public class LeersessieState {
    private int leervorm;
    private boolean startVoorkant;
    private int index;
    private int moduleStart;
    private int moduleEinde;
    private boolean isVraag = true;
    private boolean isVoorkant = true;
    private String module;
    private boolean niet;
    private boolean isRandom;
    private ArrayList<Integer> filterKaarten = new ArrayList<>();
    private ArrayList<Kaart> kaarten = new ArrayList<>();

    public LeersessieState() {

    }

    public void init() {
        index = 0;
        moduleStart = 0;
        moduleEinde = kaarten.size() - 1;
    }

    public int getIndex() {
        return index;
    }

    public int volgendeKaart() {
        return 0;
    }

    public int vorigeKaart() {
        return 0;
    }

    public void switchIsVraag() {
        isVraag = !isVraag;
    }

    public void setIsRandom(boolean x) {
        isRandom = x;
    }

  //  public int volgendeKaart() {
   //     int index = huidig;

  //      index = huidig;
  //      return index;

  //  }

 //   public int vorigeKaart() {
  //      int index = huidig;
///
  //      return index;
  //  }

    public void setDefault() {

    }

//    public Kaart volgendeKaart(String filter) {
//        if (isVraag) {
//            switchIsVraag();
//            return (getHuidigeKaart());
//        }
//
//        switchIsVraag();
//
//        index++;
//        if(index >= moduleEinde) {
//            index = moduleStart;
//        }
//        int pointer = index;
//        do {
//            if (kaarten.get(pointer).getModule().equals(filter)) {
//                index = pointer;
//                return getHuidigeKaart();
//            }
//
//            pointer++;
//            if (pointer >= moduleEinde) {
//                pointer = moduleStart;
//            }
//
//        } while (pointer != index);
//        if (index !=0) {
//            index--;
//        }
//        return getHuidigeKaart();
//    }
//
//    public Kaart volgendeKaart() {
//        if (isVraag) {
//            switchIsVraag();
//            return (getHuidigeKaart());
//        }
//
//        switchIsVraag();
//
//        index++;
//        if (index >= moduleEinde) {
//            index = moduleStart;
//        }
//        return getHuidigeKaart();
//    }
//
//    public Kaart vorigeKaart() {
//        if (isVraag) {
//            switchIsVraag();
//            return getHuidigeKaart();
//        }
//        switchIsVraag();
//        index--;
//        if (index < moduleStart) {
//            index = moduleStart;
//        }
//        return getHuidigeKaart();
//    }

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

    public boolean isNiet() {
        return niet;
    }

    public void setNiet(boolean niet) {
        this.niet = niet;
    }

        public ArrayList<Integer> getFilterKaarten() {
        return filterKaarten;
    }

    public void setFilterKaarten(ArrayList<Integer> filterKaarten) {
        this.filterKaarten = filterKaarten;
    }

    public ArrayList<Kaart> getKaarten() {
        return kaarten;
    }

    public void setKaarten(ArrayList<Kaart> kaarten) {
        this.kaarten = kaarten;
    }

    public void gaNaar(int positie) {
        if (positie <= kaarten.size()) {
            index = positie - 1;
            moduleStart = positie-1;
            if (!isVraag) {
                switchIsVraag();
            }
        }
    }

    public void totEnMetKaart(int positie) {

        if (positie <= kaarten.size()) {
            index = moduleStart;
            moduleEinde = positie;
            if (!isVraag) {
                switchIsVraag();
            }
        }
    }

//    public void volgendeKaart() {
//        if (isRandom && !(kaarten.getIsVraag())){
//            randomKaart();
//            toonKaart();
//            return;
//        }
//        String filter = leersessie.getModule();
//
//        if (!filter.equals("")) {
//            state.volgendeKaart(filter);
//            toonKaart();
//            return;
//        }
//        state.volgendeKaart();
//        toonKaart();
//    }
//
//    public void randomKaart() {
//        Random random = new Random();
//        kaarten.setIndex(random.nextInt(state.getModuleEinde()));
//        kaarten.switchIsVraag();
//    }
}
