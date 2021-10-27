import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalTime;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.SECONDS;

public class Controller {

    private final KaartenGui view;
    private final Kaartenbak kaarten;
    private final LeersessieState state;
    private Kaart huidigeKaart = new Kaart("", "");


    public Controller(KaartenGui view, Kaartenbak kaarten) {
        this.view = view;
        this.kaarten = kaarten;
        state = new LeersessieState();

        this.view.buttonHandler(new LeerSessieButtonHandler());
        this.view.textFieldHandler(new FileKaartenBakHandler());
        this.view.gaNaarHandler(new GaNaarHandler());
        this.view.windowsListener(new WindowsHandler());
        this.view.moduleHandler(new ModulesHandler());
        this.view.randomHandler(new RandomHandler());
        this.view.totenMetHandler(new TotHandler());
        this.view.nogNietHandler(new NogNietHandler());
        this.view.autocueHandler(new AutocueHandler());
        this.view.isVoorkantHandler(new IsVoorkantHandler());
    }

    public void initNieuweKaarten(String filename) {
        String tijdelijk = kaarten.getFileName();
        kaarten.setFileName(filename);
        String result = loadKaartenbak();
        if (!result.equals("")) {
            view.showMessageCode(result);
            kaarten.setFileName(tijdelijk);
            view.showFileName(tijdelijk);
            return;
        }
        state.setKaarten(kaarten.getKaarten());
        kaarten.telStanden(state.getIsVoorkant());
        state.init();
        maakModules();
        view.setChkAutocue(false);
        view.setChkRandom(false);
        view.setChkNogNiet(false);
        view.setVoorkantRadioButton(true);
        view.showGaNaarKaart(Integer.toString(state.getModuleStart() + 1));
        view.showTotEnMet(Integer.toString(state.getModuleEinde()));
        showStanden();
        state.bouwFilter();
        toonKaart();

    }

    public void maakModules() {
        ArrayList<String> modules = kaarten.getModules();
        view.vulModules(modules);
    }

    public void showStanden() {
        int aantalGoed;
        int aantalNietGoed;
        int aantalNeutraal;

        if (state.getIsVoorkant()) {
            aantalGoed = kaarten.getAantalGoedV();
            aantalNietGoed = kaarten.getAantalNogNietV();
            aantalNeutraal = kaarten.getAantalNeutraalV();
        } else {
            aantalGoed = kaarten.getAantalGoedA();
            aantalNietGoed = kaarten.getAantalNogNietA();
            aantalNeutraal = kaarten.getAantalNeutraalA();
        }

        view.showAantalGoed(Integer.toString(aantalGoed));
        view.showAantalNietGoed(Integer.toString(aantalNietGoed));
        view.showAantalNeutraal(Integer.toString(aantalNeutraal));
        view.showAantalTotaal(Integer.toString(kaarten.getAantal()));
        toonKaart();
    }

    public void moduleAfhandeling(String module, int keuze) {
        view.showSelectieModule(module);
        if (keuze == 0) {
            state.setModule("");
        } else {
            state.setModule(module);
        }
        state.setTotenmet(kaarten.getAantal());
        state.setVanaf(0);
        state.bouwFilter();
        state.setTotenmet(state.getModuleEinde());
        view.showGaNaarKaart(Integer.toString(state.getModuleStart() + 1));
        view.showTotEnMet(Integer.toString(state.getModuleEinde() + 1));
        toonKaart();
    }

    public String loadKaartenbak() {
        return kaarten.loadKaartenbak();
    }

    public void gaNaar(int positie) {
        state.gaNaar(positie);
        state.setVanaf(positie);
        state.setRange(true);
        state.bouwFilter();
        toonKaart();
    }

    public void totEnMetKaart(int positie) {
        int tijdelijk = state.getTotenMet() + 1;

        if (state.getVanaf() > positie - 1) {
            positie = tijdelijk;
        }

        //state.totEnMetKaart(positie-1);
        state.setTotenmet((positie) - 1);
        state.setRange(true);
        state.bouwFilter();
        toonKaart();
    }

    public void volgendeKaart() {
        if (view.getIsAutoCue()) {
            volgendeKaartContinue();
        } else {
            state.volgendeKaart();
            toonKaart();
        }

    }

    public void volgendeKaartContinue() {

        new Thread(() -> {

            while (view.getIsAutoCue()) {
                state.volgendeKaart();
                view.herteken();
                toonKaart();
                LocalTime time = LocalTime.now();
                boolean wait = true;
                while (wait) {
                    LocalTime time2 = LocalTime.now();
                    int seconds = (int) (time.until(time2, SECONDS));
                    if (seconds > 4) {
                        wait = false;
                    }
                }
            }
        }).start();
    }


    public void vorigeKaart() {
        state.vorigeKaart();
        toonKaart();
    }

    public void toonKaart() {
        if (state.getNoCards()) {
            view.showMessageCode("EC cardsNotInFilter");
        }
        view.showGaNaarKaart(Integer.toString(state.getModuleStart() + 1));
        view.showTotEnMet(Integer.toString(state.getModuleEinde() + 1));
        huidigeKaart = kaarten.getKaart(state.getIndex());
        String kaartTekst;
        String info;
        String kaartnummer = Integer.toString(state.getIndex() + 1);
        String buttontekst;


        if (state.getIsVraag()) {
            kaartTekst = huidigeKaart.getVoorkant();
            info = "vraag:";
            buttontekst = "    antwoord      ";
        } else {
            kaartTekst = huidigeKaart.getAchterkant();
            info = " antwoord:";
            buttontekst = "volgende kaart";
        }

        if (state.getIsVoorkant()) {
            view.showKleur(huidigeKaart.getGekendVoorkant());
        } else {
            view.showKleur(huidigeKaart.getGekendAchterkant());
        }
        view.setButtonTekst(buttontekst);
        view.showInfo(info + " " + kaartnummer);
        view.showKaartTekst(kaartTekst);
        view.showSelectieModule(huidigeKaart.getModule());
        view.showTotaalInFilter(Integer.toString(state.getAantalInfilter()));
    }

    public void setKaartGekend() {
        if (state.getIsVoorkant()) {
            huidigeKaart.setGekendVoorkant("goed");
        } else {
            huidigeKaart.setGekendAchterkant("goed");
        }
        kaarten.setKaart(huidigeKaart, state.getIndex());
        kaarten.telStanden(state.getIsVoorkant());
        showStanden();
        state.setKaarten(kaarten.getKaarten());
        state.setRange(true);
        state.bouwFilter();
        toonKaart();
    }

    public void reset() {
        kaarten.resetLeeruitslagen(state.getIsVoorkant());
        // state.setKaarten(kaarten.getKaarten());
        state.bouwFilter();
        toonKaart();
        showStanden();
    }

    public void setKaartNietGekend() {

        if (state.getIsVoorkant()) {
            huidigeKaart.setGekendVoorkant("niet");
        } else {
            huidigeKaart.setGekendAchterkant("niet");
        }
        kaarten.setKaart(huidigeKaart, state.getIndex());
        kaarten.telStanden(state.getIsVoorkant());
        showStanden();
        state.setKaarten(kaarten.getKaarten());
        state.setRange(true);
        state.bouwFilter();
        toonKaart();
    }

    public void saveFile() {
        if (!state.getIsVoorkant()) {
            state.flipKaarten();
        }
        kaarten.saveFile();
    }

    public void flipVoorkantAchterkant(String name) {
        if (name.equals("radioVoorkant") && !state.getIsVoorkant()) {
            state.setIsVoorkant(true);
            state.flipKaarten();
            state.setKaarten(kaarten.getKaarten());
            kaarten.telStanden(state.getIsVoorkant());
            view.showGaNaarKaart(Integer.toString(state.getModuleStart() + 1));
            view.showTotEnMet(Integer.toString(state.getModuleEinde()));
            showStanden();
            state.bouwFilter();
            view.herteken();
            toonKaart();
            return;
        }
        if (name.equals("radioAchterkant") && state.getIsVoorkant()) {
            state.setIsVoorkant(false);
            state.flipKaarten();
            state.setKaarten(kaarten.getKaarten());
            kaarten.telStanden(state.getIsVoorkant());
            view.showGaNaarKaart(Integer.toString(state.getModuleStart() + 1));
            view.showTotEnMet(Integer.toString(state.getModuleEinde()));
            showStanden();
            state.bouwFilter();
            view.herteken();
            toonKaart();
        }

    }

    class LeerSessieButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            JButton button = (JButton) e.getSource();
            String name = button.getName();
            // controlLeervorm1.leersessieKaart();
            switch (name) {
                case "nextCard" -> volgendeKaart();
                case "reset" -> reset();
                case "formerCard" -> vorigeKaart();
                case "correct" -> setKaartGekend();
                case "incorrect" -> setKaartNietGekend();
            }
        }
    }

    class FileKaartenBakHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            saveFile();
            JTextField name = (JTextField) e.getSource();
            String filename = name.getText();
            initNieuweKaarten(filename);

        }
    }

    class GaNaarHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField name = (JTextField) e.getSource();
            String gaNaarKaart = name.getText();
            gaNaar(Integer.parseInt(gaNaarKaart));
        }
    }


    class TotHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField name = (JTextField) e.getSource();
            String totEnMetKaart = name.getText();
            totEnMetKaart(Integer.parseInt(totEnMetKaart));
        }
    }

    class ModulesHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox modules =   (JComboBox) e.getSource();
            String module = (String) modules.getSelectedItem();
            int pointer = modules.getSelectedIndex();
            moduleAfhandeling(module, pointer);
        }
    }

    class RandomHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            state.setIsRandom(checkBox.isSelected());
        }
    }

    class NogNietHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            state.setIsnietGoed(checkBox.isSelected());
            state.bouwFilter();
            toonKaart();
        }
    }

    class AutocueHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
        }
    }

    class IsVoorkantHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton radioButton = (JRadioButton) e.getSource();
            String name = radioButton.getName();
            flipVoorkantAchterkant(name);


        }
    }

    class WindowsHandler implements WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            saveFile();

        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }
    }
}


