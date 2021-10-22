import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * todo: setModule filter werkt niet goed.
 *
 */
public class Controller {

    private static final int VRAAG_ANTWOORD = 1;
    private static final int ANTWOORD_VRAAG = 2;
    private static final int VRAAG_ANTWOORD_SCHRIJVEN = 3;
    private static final int ANTWOORD_VRAAG_SCHRIJVEN = 4;
    private final KaartenGui view;
    private final Kaartenbak kaarten;
    private final Leersessie leersessie;
    private final LeersessieState state;
    private Kaart huidigeKaart = new Kaart("", "");



    public Controller(KaartenGui view, Kaartenbak kaarten) {
        this.view = view;
        this.kaarten = kaarten;
        state = new LeersessieState();
        leersessie = new Leersessie();

        this.view.buttonHandler(new LeerSessieButtonHandler());
        this.view.textFieldHandler(new FileKaartenBakHandler());
        this.view.gaNaarHandler(new GaNaarHandler());
        this.view.windowsListener(new WindowsHandler());
        this.view.moduleHandler(new ModulesHandler());
        this.view.randomHandler(new RandomHandler());
        this.view.totenMetHandler(new TotHandler());
    }

    public void initNieuweKaarten(String filename) {
        kaarten.setFileName(filename);
        String result = loadKaartenbak();
        if (!result.equals("")) {
            view.showMessageCode(result);
            return;
        }
        maakModules();
        state.init();
        view.showGaNaarKaart(Integer.toString(state.getModuleStart()+1));
        view.showTotEnMet(Integer.toString(state.getModuleEinde()));
        showStanden();
    }

    public void maakModules() {
        ArrayList<String> modules = kaarten.getModules();
        view.vulModules(modules);
    }

    public void showStanden() {
        int aantalGoed = 0;
        int aantalNietGoed = 0;
        int aantalNeutraal = 0;

        if (state.getLeervorm() == VRAAG_ANTWOORD || state.getLeervorm() == VRAAG_ANTWOORD_SCHRIJVEN) {
            aantalGoed = kaarten.getAantalGoedV();
            aantalNietGoed = kaarten.getAantalNogNietV();
            aantalNeutraal = kaarten.getAantalNeutraalV();
        }
        if (state.getLeervorm() == ANTWOORD_VRAAG || state.getLeervorm() == ANTWOORD_VRAAG_SCHRIJVEN) {
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

   public void moduleAfhandeling (String module, int keuze) {
        view.showSelectieModule(module);
        if (keuze == 0) {
            leersessie.setModule("");
       } else {
            leersessie.setModule(module);
        }
   }

    public String loadKaartenbak() {
        return kaarten.loadKaartenbak();
    }

   public void gaNaar(int positie) {
        state.gaNaar(positie);
        toonKaart();

   }

   public void totEnMetKaart(int positie) {
        state.totEnMetKaart(positie);
        toonKaart();
   }

   public void volgendeKaart() {
        state.volgendeKaart();
        toonKaart();
   }



    public void vorigeKaart() {
        state.vorigeKaart();
        toonKaart();
    }

     public void toonKaart() {
        huidigeKaart = kaarten.getKaart(state.getIndex());
        String kaartTekst = "";
        String info = "";
        String kaartnummer = Integer.toString(state.getIndex() + 1);
        String module = huidigeKaart.getModule();
        String buttontekst = "";

        if (leersessie.getLeervorm() == VRAAG_ANTWOORD) {
            if (kaarten.getIsVraag()) {
                kaartTekst = huidigeKaart.getVoorkant();
                info = "vraag:";
                buttontekst = "    antwoord      ";
            } else {
                kaartTekst = huidigeKaart.getAchterkant();
                info = " antwoord:";
                buttontekst = "volgende kaart";
            }
        }

        view.showKleur(huidigeKaart.getGekendVoorkant());
        view.setButtonTekst(buttontekst);
        view.showInfo(info + " " + kaartnummer);
        view.showKaartTekst(kaartTekst);
        view.showSelectieModule(huidigeKaart.getModule());
        setBeginEnEinde();
    }

    public void setBeginEnEinde() {
        state.setModuleStart(Integer.parseInt(view.getGaNaarKaart())-1);
        state.setModuleEinde(Integer.parseInt(view.getTotEnMet()));
    }


    public void setKaartGekend() {
        huidigeKaart.setGekendVoorkant("goed");
        kaarten.setKaart(huidigeKaart, state.getIndex());
        kaarten.telStanden();
        showStanden();
    }

    public void reset() {
        kaarten.resetLeeruitslagen();
        toonKaart();
        showStanden();
    }

    public void setKaartNietGekend() {
        huidigeKaart.setGekendVoorkant("niet");
        kaarten.setKaart(huidigeKaart, state.getIndex());
        kaarten.telStanden();
        showStanden();
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
            JComboBox modules = (JComboBox) e.getSource();
            String module = (String) modules.getSelectedItem();
            int pointer = (int) modules.getSelectedIndex();
            moduleAfhandeling(module, pointer);
        }
    }

    class RandomHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = (JCheckBox)  e.getSource();
            state.setIsRandom(checkBox.isSelected());
        }
    }

    class WindowsHandler implements WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            kaarten.saveFile();
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
