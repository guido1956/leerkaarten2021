import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class Kaartenbak {
    private ArrayList<Kaart> kaarten;
    private String filename = "";
    private int aantalGoedV = 0;
    private int aantalNogNietV = 0;
    private int aantalNeutraalV = 0;
    private int aantalGoedA = 0;
    private int aantalNogNietA = 0;
    private int aantalNeutraalA = 0;

    public Kaartenbak(ArrayList<Kaart> kaarten) {
        this.kaarten = kaarten;
     }

    public Kaartenbak() {
        kaarten = new ArrayList<>();
    }

    public void setFileName(String filename) {
        this.filename = filename;
    }

    public String getFileName() {
        return filename;
    }

    public String loadKaartenbak() {
        BufferedReader inKaartFile = openBestand();
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

    private BufferedReader openBestand() {
        BufferedReader inFile;
        try {
            inFile = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filename), StandardCharsets.UTF_8));
            return (inFile);
        } catch (IOException e) {
            return null;
        }
    }

    private BufferedWriter createBestand() {
        BufferedWriter inFile;
        try {
            inFile = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(filename), StandardCharsets.UTF_8));
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

    public ArrayList<String> getModules() {
        ArrayList<String> modules = new ArrayList<>();
        if (kaarten.size() == 0) {
            return modules;
        }

        for (Kaart e : kaarten) {
            String module = e.getModule();
            if (!(module.equals("") || modules.contains(module))) {
                modules.add(module);
            }
        }
        Collections.sort(modules);
        return modules;
    }

    /**
     * todo: scheiden voorkant / achterkant
     */
    public void resetLeeruitslagen() {
        for (int x = 0; x < kaarten.size(); x++) {
            kaarten.get(x).setGekendVoorkant("neutraal");
            kaarten.get(x).setGekendAchterkant("neutraal");
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


    public Kaart getKaart(int index) {
        return kaarten.get(index);
    }

    public void setKaart(Kaart kaart, int index) {
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


    public ArrayList<Kaart> vulArrayList(BufferedReader inKaartfile) {
        ArrayList<Kaart> temporaly = new ArrayList<>();
        String tijdelijk;
        boolean isKaart = true;
        try {
            while (isKaart) {
                tijdelijk = inKaartfile.readLine();
                if (!(tijdelijk == null)) {
                    String[] fields = tijdelijk.split(":");
                    int lengte = fields.length;
                    for (int x = 0; x < fields.length; x++) {
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

    public void saveFile() {
        try {
            BufferedWriter outfile = createBestand();
            for (Kaart e : kaarten) {
                String a = e.getVoorkant() + ":" + e.getAchterkant() + ":" + e.getModule() + ":" +
                        e.getGekendVoorkant() + ":" + e.getGekendAchterkant() + "\n";
                assert outfile != null;
                outfile.write(a);
            }
            assert outfile != null;
            outfile.close();
        } catch (Exception e) {
        }
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

    public void setAantalNeutraalV(int aantalNeutraalV) {
        this.aantalNeutraalV = aantalNeutraalV;
    }

    public int getAantal() {
        return kaarten.size();
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

    /**
     * todo: filter inbouwen
     */
    class Filter {
        ArrayList<Integer> filter = new ArrayList<>();
        int pointer = 0;

        public void maakFilter() {
            filter.clear();
            int index = 0;
            for (Kaart e : kaarten) {
                boolean voegtoe = true;
                filter.add((index));
                index++;
            }
        }
    }
}
