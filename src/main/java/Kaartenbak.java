import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Kaartenbak {
    private ArrayList<Kaart> kaarten;
    private int index;
    private boolean isVraag = true;
    private int moduleStart;
    private int moduleEinde;
    private int aantalGoedV = 0;
    private int aantalNogNietV= 0;
    private int aantalNeutraalV = 0;
    private int aantalGoedA = 0;
    private int aantalNogNietA= 0;
    private int aantalNeutraalA = 0;

    public Kaartenbak(ArrayList<Kaart> kaarten) {
        this.kaarten = kaarten;
        index = 0;
        moduleStart = 0;
        moduleEinde = kaarten.size() - 1;
    }


    public Kaartenbak() {
        kaarten = new ArrayList<>();
        index = 0;
        moduleStart = 0;
        moduleEinde = 0;
    }

    public void init() {
        index = 0;
        moduleStart = 0;
        moduleEinde = kaarten.size();
        telStanden();
    }

    public String loadKaartenbak(String filename) {
        BufferedReader inKaartFile = openBestand(filename);
        if (inKaartFile == null) {
            return ("EC fileNotFound");
        }

        ArrayList<Kaart> temporaly = vulArrayList(inKaartFile);
        if (temporaly == null) {
            return ("EC fillArrayError");
        }

        kaarten = temporaly;
        return "";
    }

    private BufferedReader openBestand(String naamBestand) {
        BufferedReader inFile;
        try {
            inFile = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(naamBestand), StandardCharsets.UTF_8));
            return (inFile);
        } catch (IOException e) {
            return null;
        }
    }

    private BufferedWriter createBestand(String naamBestand) {
        BufferedWriter inFile;
        try {
            inFile = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(naamBestand), StandardCharsets.UTF_8));
            return (inFile);
        } catch (IOException e) {
            return null;
        }
    }

    public void telStanden() {
        resetTellers();
        for (Kaart kaart : kaarten) {
            if (kaart.getGekendVoorkant().equals("")) {
                kaart.setGekendVoorkant("neutraal");
            }
            if (kaart.getGekendVoorkant().equals("neutraal")) {
                aantalNeutraalV++;
            }
            if (kaart.getGekendVoorkant().equals("goed")) {
                aantalGoedV++;
            }
            if (kaart.getGekendVoorkant().equals("niet")) {
                aantalNogNietV++;
            }


            if (kaart.getGekendAchterkant().equals("")) {
                kaart.setGekendVoorkant("neutraal");
            }
            if (kaart.getGekendAchterkant().equals("neutraal")) {
                aantalNeutraalA++;
            }
            if (kaart.getGekendAchterkant().equals("goed")) {
                aantalGoedA++;
            }
            if (kaart.getGekendAchterkant().equals("niet")) {
                aantalNogNietA++;
            }

        }

    }

    /**
     * todo: scheiden voorkant / achterkant
     */
    public void resetLeeruitslagen() {
        for (int x = 0; x < kaarten.size(); x++) {
            kaarten.get(x).setGekendVoorkant("neutraal") ;
            kaarten.get(x).setGekendAchterkant("neutraal") ;
        }
        telStanden();

    }

    /**
     * todo: scheiden voorkant / achterkant
     */
    public void resetTellers() {
        aantalGoedV = 0;
        aantalNeutraalV = 0;
        aantalNogNietV = 0;

        aantalGoedA = 0;
        aantalNeutraalA = 0;
        aantalNogNietA = 0;
    }

    public Kaart volgendeKaart(String filter) {
        int circle = index;
        for (int x = index + 1; x <= moduleEinde + 1; x++) {
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
        if (isVraag) {
            switchIsVraag();
            return getHuidigeKaart();
        }
        switchIsVraag();
        index++;
        if (index >= moduleEinde) {
            index = moduleStart;
        }
        return getHuidigeKaart();
    }

    public Kaart vorigeKaart() {
        if (isVraag) {
            switchIsVraag();
            return getHuidigeKaart();
        }
        switchIsVraag();
        index--;
        if (index < moduleStart) {
            index = moduleStart;
        }
        return getHuidigeKaart();
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
        moduleEinde = kaarten.size() - 1;
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

    public void switchIsVraag() {
        isVraag = !isVraag;
    }


    public ArrayList<Kaart> vulArrayList(BufferedReader inKaartfile) {
        ArrayList<Kaart> temporaly = new ArrayList<>();
        String tijdelijk;
        boolean isKaart = true;
        try {
            while (isKaart) {
                tijdelijk = inKaartfile.readLine();
                System.out.println(tijdelijk);
                if (!(tijdelijk == null)) {
                    String[] fields = tijdelijk.split(":");
                    int lengte = fields.length;
                    for (int x = 0 ; x < fields.length ; x++) {
                        fields[x] = fields[x].trim();
                    }

                    if (lengte < 2) {
                       return null;
                    }
                    Kaart kaart = new Kaart(fields[0], fields[1]);
                    if (lengte >= 3) {
                        kaart.setModule(fields[2]);
                                           }
                    if (lengte >= 4) {
                        kaart.setGekendVoorkant(fields[3]);
                    }
                    if (lengte >= 5) {
                        kaart.setGekendAchterkant(fields[4]);
                    }
                    temporaly.add(kaart);
                } else {
                    isKaart = false;
                }
            }
        } catch (Exception e) {
             return null;
        }
        return temporaly;
    }

    public void saveFile(String name) {
        try {
            BufferedWriter outfile = createBestand(name);
            System.out.println("YES");
            for (Kaart e : kaarten) {
                String a = e.getVoorkant() + ":" + e.getAchterkant() + ":" + e.getModule() + ":" +
                        e.getGekendVoorkant() + ":" + e.getGekendAchterkant() + "\n";
                assert outfile != null;
                outfile.write(a);
            }
            assert outfile != null;
            outfile.close();
        } catch (Exception e) {
            System.out.println("NO");

        }
    }

    public boolean getIsVraag() {
        return isVraag;
    }


    public int getAantalGoedV() {
        return aantalGoedV;
    }

    public void setAantalGoedV(int aantalGoedV) {
        this.aantalGoedV = aantalGoedV;
    }

    public int getAantalNogNietV() {
        return aantalNogNietV;
    }

    public void setAantalNogNietV(int aantalNogNietV) {
        this.aantalNogNietV = aantalNogNietV;
    }

    public int getAantalNeutraalV() {
        return aantalNeutraalV;
    }

    public int getAantal() {
        return kaarten.size();
    }

    public void setAantalNeutraalV(int aantalNeutraalV) {
        this.aantalNeutraalV = aantalNeutraalV;
    }

    public int getAantalGoedA() {
        return aantalGoedA;
    }

    public void setAantalGoedA(int aantalGoedA) {
        this.aantalGoedA = aantalGoedA;
    }

    public int getAantalNogNietA() {
        return aantalNogNietA;
    }

    public void setAantalNogNietA(int aantalNogNietA) {
        this.aantalNogNietA = aantalNogNietA;
    }

    public int getAantalNeutraalA() {
        return aantalNeutraalA;
    }

    public void setAantalNeutraalA(int aantalNeutraalA) {
        this.aantalNeutraalA = aantalNeutraalA;
    }
}
