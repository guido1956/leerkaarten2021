import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.SECONDS;


// todo 1 voor schrijfmodule t/m en random inbouwen
public class Controller {

    protected final KaartenGui view;
    protected final Kaartenbak kaarten;
    protected final LeersessieState state;
    protected Kaart huidigeKaart = new Kaart("", "");
    protected ControlSchrijf controlSchrijf;
    private int tabkeuze = 0;

    public Controller(KaartenGui view, Kaartenbak kaarten, LeersessieState state) {
        this.view = view;
        this.kaarten = kaarten;
        this.state = state;
        initHandlers();
    }

    public void initHandlers() {
        this.view.buttonHandler(new LeerSessieButtonHandler());
        this.view.textFieldHandler(new FileKaartenBakHandler());
        this.view.gaNaarHandler(new GaNaarHandler());
        this.view.gaNaarHandlerSchrijf(new GaNaarHandlerSchrijf());
        this.view.windowsListener(new WindowsHandler());
        this.view.moduleHandler(new ModulesHandler());
        this.view.moduleHandlerSchrijf(new ModulesHandlerSchrijf());
        this.view.randomHandler(new RandomHandler());
        this.view.randomHandlerSchrijf(new RandomHandlerSchrijf());
        this.view.totenMetHandler(new TotHandler());
        this.view.totenMetHandlerSchrijf(new TotHandlerSchrijf());
        this.view.nogNietHandler(new NogNietHandler());
        this.view.goedHandler(new GoedHandler());
        this.view.nogNietHandlerSchrijf(new NogNietHandlerSchrijf());
        this.view.goedHandlerSchrijf(new GoedHandlerSchrijf() );
        this.view.neutraalHandler(new NeutraalHandler());
        this.view.neutraalHandlerSchrijf(new NeutraalHandlerSchrijf());
        this.view.autocueHandler(new AutocueHandler());
        this.view.isVoorkantHandler(new IsVoorkantHandler());
        this.view.isVoorkantHandlerSchrijf(new IsVoorkantHandlerSchrijf());
        this.view.tabHandler((new TabHandler()));
        this.view.buttonHandlerBeheer(new BeheerButtonHandler());
        this.view.buttonHandlerSchrijf(new SchrijfButtonHandler());
        this.view.enterToetsHandlerSchrijf(new EnterToetsHandlerSchrijf());
        controlSchrijf = new ControlSchrijf(view, kaarten, state);
    }

    public void verwerkKaartenBestand(String filename) {
        String tijdelijk = kaarten.getFileName();
        kaarten.setFileName(filename);
        String result = loadKaartenbak();
        if (!result.equals("")) {
            geenInitNieuweKaarten(result, tijdelijk);
            return;
        }
        initKaarten();
        state.bouwFilter();
        toonKaart();
    }

    public void initKaarten() {
        state.setKaarten(kaarten.getKaarten());
        kaarten.telStanden(state.getIsVoorkant() , state.getLeervorm());
        state.init();
        initSettings();
        maakModules();
        tabkeuze = 0;
    }

    public void initSettings() {
        view.setChkAutocue(false);
        view.setChkRandom(false);
        view.setChkNogNiet(true);
        view.setChkNeutraal(true);
        view.setVoorkantRadioButton(true);
        view.showGaNaarKaart(Integer.toString(state.getModuleStart() + 1));
        view.showTotEnMet(Integer.toString(state.getModuleEinde()));
    }

    public void geenInitNieuweKaarten(String result, String tijdelijk) {
        view.showMessageCode(result);
        kaarten.setFileName(tijdelijk);
        view.showFileName(tijdelijk);
    }

    public void maakModules() {
        ArrayList<String> modules = kaarten.getModules();
        view.vulModules(modules);
        view.showVulling("");
    }

    public void showStanden() {
        int aantalGoed = 0;
        int aantalNietGoed= 0;
        int aantalNeutraal = 0 ;

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
        // toonKaart();
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

    public void moduleAfhandelingSchrijf(String module, int keuze) {
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
        view.showGaNaarKaartSchrijf(Integer.toString(state.getModuleStart() + 1));
        controlSchrijf.toonKaart();
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

    public void gaNaarSchrijf(int positie) {
        state.gaNaar(positie);
        state.setVanaf(positie);
        state.setRange(true);
        state.bouwFilter();
        controlSchrijf.toonKaart();
    }

    public void totEnMetKaart(int positie) {
        int tijdelijk = state.getTotenMet() + 1;

        if (state.getVanaf() > positie - 1) {
            positie = tijdelijk;
        }

        state.setTotenmet((positie) - 1);
        state.setRange(true);
        state.bouwFilter();
        toonKaart();
    }

    public void totEnMetKaartSchrijf(int positie) {
        int tijdelijk = state.getTotenMet() + 1;

        if (state.getVanaf() > positie - 1) {
            positie = tijdelijk;
        }

        state.setTotenmet((positie) - 1);
        state.setRange(true);
        state.bouwFilter();
        controlSchrijf.toonKaart();
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
                    if (seconds > 2) {
                        wait = false;
                    }
                }
            }
        }).start();
    }

    public void flipKaart() {
        state.switchIsVraag();
        toonKaart();
    }

    public void flipKaartSchrijf() {
        state.switchIsVraag();
        if (state.getIsVraag()){
            view.setBtnCheck(true);
        } else {
            view.setBtnCheck(false);
        }
        controlSchrijf.toonKaart();
    }


    public void vorigeKaart() {
        state.vorigeKaart();
        toonKaart();
    }

    public void toonKaart() {
        if (state.getNoCards()) {
            view.showKaartTekst("Er voldoet geen enkele kaart aan de selectiecriteria\nWijzig de selectie");
            return;
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
            kaartTekst = kaartTekst.replaceAll("@@", "\n");
        } else {
            kaartTekst = huidigeKaart.getAchterkant();
            kaartTekst = kaartTekst.replaceAll("@@", "\n");
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
        showStanden();
    }

    public void setKaartGekend() {
        if (state.getIsVoorkant()) {
            huidigeKaart.setGekendVoorkant("goed");
        } else {
            huidigeKaart.setGekendAchterkant("goed");
        }
        kaarten.setKaart(huidigeKaart, state.getIndex());
        kaarten.telStanden(state.getIsVoorkant(), state.getLeervorm());
        state.setKaarten(kaarten.getKaarten());
        state.setRange(true);
        state.bouwFilter();
        toonKaart();
    }

    public void reset() {
        kaarten.resetLeeruitslagen(state.getIsVoorkant(), state.getLeervorm(), state.getModule());
        state.bouwFilter();
        if (state.getLeervorm() == 0) {
            toonKaart();
        }
        if (state.getLeervorm() == 1) {
            controlSchrijf.toonKaart();
        }
    }

    public void setKaartNietGekend() {

        if (state.getIsVoorkant()) {
            huidigeKaart.setGekendVoorkant("niet");
        } else {
            huidigeKaart.setGekendAchterkant("niet");
        }
        kaarten.setKaart(huidigeKaart, state.getIndex());
        kaarten.telStanden(state.getIsVoorkant(), state.getLeervorm());
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
            kaarten.telStanden(state.getIsVoorkant(), state.getLeervorm());
            view.showGaNaarKaart(Integer.toString(state.getModuleStart() + 1));
            view.showTotEnMet(Integer.toString(state.getModuleEinde()));
            state.bouwFilter();
            view.herteken();
            showStanden();
            toonKaart();
            return;
        }
        if (name.equals("radioAchterkant") && state.getIsVoorkant()) {
            state.setIsVoorkant(false);
            state.flipKaarten();
            state.setKaarten(kaarten.getKaarten());
            kaarten.telStanden(state.getIsVoorkant(), state.getLeervorm());
            view.showGaNaarKaart(Integer.toString(state.getModuleStart() + 1));
            view.showTotEnMet(Integer.toString(state.getModuleEinde()));
            state.bouwFilter();
            view.herteken();
            showStanden();
            toonKaart();
        }

    }

    public void flipVoorkantAchterkantSchrijf(String name) {
        if (name.equals("radioVoorkant") && !state.getIsVoorkant()) {
            state.setIsVoorkant(true);
            state.flipKaarten();
            state.setKaarten(kaarten.getKaarten());
            kaarten.telStanden(state.getIsVoorkant(), state.getLeervorm());
            view.showGaNaarKaartSchrijf(Integer.toString(state.getModuleStart() + 1));
            view.showTotEnMet(Integer.toString(state.getModuleEinde()));
            state.bouwFilter();
            //view.herteken();
            controlSchrijf.showStandenSchrijf();
            controlSchrijf.toonKaart();
            return;
        }
        if (name.equals("radioAchterkant") && state.getIsVoorkant()) {
            state.setIsVoorkant(false);
            state.flipKaarten();
            state.setKaarten(kaarten.getKaarten());
            kaarten.telStanden(state.getIsVoorkant(), state.getLeervorm());
            view.showGaNaarKaartSchrijf(Integer.toString(state.getModuleStart() + 1));
            view.showTotEnMet(Integer.toString(state.getModuleEinde()));
            state.bouwFilter();
            // view.herteken();
            controlSchrijf.showStandenSchrijf();
            controlSchrijf.toonKaart();
        }

    }

    public void initBeheerSessie() {
        toonBeheerkaart();
    }

    public void initSchrijfsessie() {
        // todo: schrijfsessie
        String name = kaarten.getFileName();
        controlSchrijf.maakModules();
        view.setModulesComboSchrijf(view.getModulesCombo());
        view.setChkRandomSchrijf(view.getIsRandom());
        view.setChkNogNietSchrijf(view.getIsNogNiet());
        view.showSchrijvenFileName(name);
        controlSchrijf.toonKaart();
    }

    // methods voor beheerKaarten
    public void toonBeheerkaart() {
        view.showBeheerFileNaam(kaarten.getFileName());
        view.showKaartnummer(Integer.toString(state.getIndex() + 1));
        huidigeKaart = state.getKaarten().get(state.getIndex());    // nagaan wat er in schrijf gebeurt met huidige
        String kaartgegevens = huidigeKaart.getVoorkant() + "\n" +
                huidigeKaart.getAchterkant() + "\n" +
                huidigeKaart.getModule() + "\n" +
                huidigeKaart.getGekendVoorkant() + "\n" +
                huidigeKaart.getGekendAchterkant() + "\n" +
                huidigeKaart.getGekendSchrijfVoorkant()  + "\n" +
                huidigeKaart.getGekendSchrijfAchterkant();

                view.showKaartgegevens(kaartgegevens);
    }

    public void nieuweKaart() {
        view.beheerMaakLeeg(Integer.toString(kaarten.getAantal() + 1));

    }

    public void verwijderKaart() {
        int kaartnr = Integer.parseInt(view.getBeheerKaartnummer());
        kaarten.verwijderKaart(kaartnr - 1);
        maakModules();
        kaarten.telStanden(state.getIsVoorkant(), state.getLeervorm());
        state.bouwFilter();
        toonKaart();  //@@ hier stond show standen
        initBeheerSessie();
    }

    public void saveKaart() {
        int kaartnr = Integer.parseInt(view.getBeheerKaartnummer());
        String kaartgegevens = view.getBeheerKaart();
        kaartgegevens = kaartgegevens + " \n".repeat(7);
        String[] fields = kaartgegevens.split("\n");
        Kaart temp = new Kaart(fields[0].trim(), fields[1].trim(), fields[2].trim(), fields[3].trim(), fields[4].trim(),
        fields[5].trim(), fields[6].trim());
        if (kaartnr == state.getIndex() + 1) {
            kaarten.setKaart(temp, state.getIndex());
        } else {
            kaarten.addKaart(temp);
        }
        maakModules();
        kaarten.telStanden(state.getIsVoorkant(), state.getLeervorm());
        state.bouwFilter();
        toonKaart(); // hier stond show standen
        initBeheerSessie();
    }

    public void volgendeSchrijfKaart() {
        volgendeKaart();
        controlSchrijf.toonKaart();
    }

    public void checkSchrijven() {
        controlSchrijf.checkSchrijven();
      controlSchrijf.showStandenSchrijf();
    }


    class TabHandler implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {

            JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
            if (kaarten.getAantal() == 0) {
                sourceTabbedPane.setSelectedIndex(0);
            }
            int index = sourceTabbedPane.getSelectedIndex();
            state.setLeervorm(index);

            if (index == 0 && kaarten.getAantal() != 0) {
                if (tabkeuze == 1) {
                    view.setVoorkantRadioButton(view.getVoorkantRadioButtonSchrijf());
                    view.setChkGoed(view.getGoedSchrijf());
                    view.setChkNeutraal(view.getNeutraalSchrijf());
                    view.setChkNogNiet(view.getIsNogNietSchrijf());
                    view.setChkRandom(view.getIsRandomSchrijf());
                    view.setModulesCombo(view.getModulesComboSchrijf());
                }
                toonKaart();

                tabkeuze = index;

            }
            if (index == 1) {
                if (tabkeuze == 0) {
                    view.setVoorkantRadioButtonSchrijf(view.getVoorkantRadioButton());
                    view.setChkNogNietSchrijf(view.getIsNogNiet());
                    view.setChkNeutraalSchrijf(view.getNeutraal());
                    view.setGoedSchrijf(view.getGoed());
                    view.setChkRandomSchrijf(view.getIsRandom());
                }
                view.setChkAutocue(false);
                initSchrijfsessie();
                kaarten.telStanden(state.getIsVoorkant(),state.getLeervorm());
                controlSchrijf.showStandenSchrijf();
                controlSchrijf.toonKaart();
                tabkeuze = index;

            }
            if (index == 2) {
                view.setChkAutocue(false);
                initBeheerSessie();
            }

        }
    }

    class LeerSessieButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            JButton button = (JButton) e.getSource();
            String name = button.getName();
            // controlLeervorm1.leersessieKaart();
            switch (name) {
                case "nextCard" -> volgendeSchrijfKaart();
                case "reset" -> reset();
                case "formerCard" -> vorigeKaart();
                case "correct" -> setKaartGekend();
                case "incorrect" -> setKaartNietGekend();
                case "flip" -> flipKaart();
            }
        }
    }

    class FileKaartenBakHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            saveFile();
            JTextField name = (JTextField) e.getSource();
            String filename = name.getText();
            verwerkKaartenBestand(filename);

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

    class GaNaarHandlerSchrijf implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField name = (JTextField) e.getSource();
            String gaNaarKaart = name.getText();
            gaNaarSchrijf(Integer.parseInt(gaNaarKaart));
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

    class TotHandlerSchrijf implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField name = (JTextField) e.getSource();
            String totEnMetKaart = name.getText();
            totEnMetKaartSchrijf(Integer.parseInt(totEnMetKaart));
        }
    }

    class ModulesHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox modules = (JComboBox) e.getSource();
            String module = (String) modules.getSelectedItem();
            int pointer = modules.getSelectedIndex();
            moduleAfhandeling(module, pointer);
        }
    }

    class ModulesHandlerSchrijf implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox modules = (JComboBox) e.getSource();
            String module = (String) modules.getSelectedItem();
            int pointer = modules.getSelectedIndex();
            moduleAfhandelingSchrijf(module, pointer);
        }
    }


    class RandomHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            state.setIsRandom(checkBox.isSelected());
        }
    }

    class RandomHandlerSchrijf implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            state.setIsRandom(checkBox.isSelected());
        }
    }

    class GoedHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            state.setGoed(checkBox.isSelected());
            state.bouwFilter();
            toonKaart();
        }
    }

    class NogNietHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            state.setIsNietGoed(checkBox.isSelected());
            state.bouwFilter();
            toonKaart();
        }
    }

    class NeutraalHandler implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            state.setNeutraal(checkBox.isSelected());
            state.bouwFilter();
            toonKaart();

        }
    }

    class NogNietHandlerSchrijf implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            state.setIsNietGoed(checkBox.isSelected());
            state.bouwFilter();
            controlSchrijf.toonKaart();
        }
    }

    class GoedHandlerSchrijf implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            state.setGoed(checkBox.isSelected());
            state.bouwFilter();
            controlSchrijf.toonKaart();
        }
    }

    class NeutraalHandlerSchrijf implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            state.setNeutraal(checkBox.isSelected());
            state.bouwFilter();
            controlSchrijf.toonKaart();
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

    class IsVoorkantHandlerSchrijf implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton radioButton = (JRadioButton) e.getSource();
            String name = radioButton.getName();
            flipVoorkantAchterkantSchrijf(name);
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

    class BeheerButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            JButton button = (JButton) e.getSource();
            String name = button.getName();
            // controlLeervorm1.leersessieKaart();
            switch (name) {
                case "nieuw" -> nieuweKaart();
                case "verwijder" -> verwijderKaart();
                case "save" -> saveKaart();
            }
        }
    }

    class SchrijfButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String name = button.getName();
            switch (name) {
                case "volgende" -> controlSchrijf.volgendeKaart();
                case "vorige" -> controlSchrijf.vorigeKaart();
                case "check" -> checkSchrijven();
                case "flip" -> flipKaartSchrijf();
                case "reset" -> controlSchrijf.reset();
            }
        }
    }


    class EnterToetsHandlerSchrijf implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_F1) {
                controlSchrijf.checkSchrijven();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}



