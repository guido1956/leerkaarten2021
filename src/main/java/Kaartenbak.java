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

    private int aantalGoedSV = 0;
    private int aantalNogNietSV = 0;
    private int aantalNeutraalSV = 0;
    private int aantalGoedSA = 0;
    private int aantalNogNietSA = 0;
    private int aantalNeutraalSA = 0;

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

    public void telStanden(boolean isVoorkant, int leersessie) {
        resetTellers(isVoorkant, leersessie);
        for (Kaart kaart : kaarten) {
            if (leersessie == 0) {
            if (isVoorkant) {
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

            }
            if (!isVoorkant) {
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
            if (leersessie == 1) {
                if (isVoorkant) {
                    if (kaart.getGekendSchrijfVoorkant().equals("")) {
                        kaart.setGekendSchrijfVoorkant("neutraal");
                    }
                    if (kaart.getGekendSchrijfVoorkant().equals("neutraal")) {
                        aantalNeutraalSV++;
                    }
                    if (kaart.getGekendSchrijfVoorkant().equals("goed")) {
                        aantalGoedSV++;
                    }
                    if (kaart.getGekendSchrijfVoorkant().equals("niet")) {
                        aantalNogNietSV++;
                    }

                }
                if (!isVoorkant) {
                    if (kaart.getGekendSchrijfAchterkant().equals("")) {
                        kaart.setGekendAchterkant("neutraal");
                    }
                    if (kaart.getGekendSchrijfAchterkant().equals("neutraal")) {
                        aantalNeutraalSA++;
                    }
                    if (kaart.getGekendSchrijfAchterkant().equals("goed")) {
                        aantalGoedSA++;
                    }
                    if (kaart.getGekendSchrijfAchterkant().equals("niet")) {
                        aantalNogNietSA++;
                    }
                }


            }

            }

    }

    public ArrayList<String> getModules() {
        ArrayList<String> modules = new ArrayList<>();
        if (kaarten.size() == 0) {
            return modules;
        }

        modules.clear();
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
    public void resetLeeruitslagen(boolean isVoorkant, int leersessie, String module) {
        if (leersessie == 0) {
            for (Kaart kaart : kaarten) {
                if (isVoorkant) {
                    if (module.equals(kaart.getModule()) || module.equals("")) {
                        kaart.setGekendVoorkant("neutraal");
                    }
                } else {
                    if (module.equals(kaart.getModule()) || module.equals("")) {
                        kaart.setGekendAchterkant("neutraal");
                    }
                }
            }
        }
        if (leersessie == 1) {
            for (Kaart kaart : kaarten) {
                if (isVoorkant) {
                    if (module.equals(kaart.getModule()) || module.equals("")) {
                        kaart.setGekendSchrijfVoorkant("neutraal");
                    }
                } else {
                    if (module.equals(kaart.getModule()) || module.equals("")) {
                        kaart.setGekendSchrijfAchterkant("neutraal");
                    }
                }
            }

        }
        telStanden(isVoorkant, leersessie);
    }

    /**
     * todo: scheiden voorkant / achterkant
     */
    public void resetTellers(boolean isVoorkant, int leersessie) {

        if (leersessie == 0) {
            if (isVoorkant) {
               aantalGoedV = 0;
                aantalNeutraalV = 0;
                aantalNogNietV = 0;

            } else {
                aantalGoedA = 0;
                aantalNeutraalA = 0;
                aantalNogNietA = 0;
            }
        }

        if (leersessie == 1) {
            if (isVoorkant) {
                aantalGoedSV = 0;
                aantalNeutraalSV = 0;
                aantalNogNietSV = 0;

            } else {
                aantalGoedSA = 0;
                aantalNeutraalSA = 0;
                aantalNogNietSA = 0;
            }
        }
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

    public void verwijderKaart(int index) {
        kaarten.remove(index);
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
                    String[] fields = tijdelijk.split("&&");
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
                    if (lengte >= 6) {
                        kaart.setGekendSchrijfVoorkant(fields[5]);
                    }
                    if (lengte >= 7) {
                        kaart.setGekendSchrijfAchterkant(fields[6]);
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
        if (kaarten.size() == 0) {
            return;
        }
        try {
            BufferedWriter outfile = createBestand();
            for (Kaart e : kaarten) {
                String a = e.getVoorkant() + "&&" + e.getAchterkant() + "&&" + e.getModule() + "&&" +
                        e.getGekendVoorkant() + "&&" + e.getGekendAchterkant() +
                        "&&" + e.getGekendSchrijfVoorkant() + "&&" + e.getGekendSchrijfAchterkant() + "\n";
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

    public int getAantalNeutraalSV() {
        return aantalNeutraalSV;
    }

    public int getAantalGoedSV() {
        return aantalGoedSV;
    }
    public int getAantalNogNietSV() {
        return aantalNogNietSV;
    }

    public int getAantalNeutraalSA() {
        return aantalNeutraalSA;
    }

    public int getAantalGoedSA() {
        return aantalGoedSA;
    }
    public int getAantalNogNietSA() {
        return aantalNogNietSA;
    }


}
