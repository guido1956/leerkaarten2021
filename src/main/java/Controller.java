import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * todo: minimal product
 * todo: handling eerste events
 * todo: inlezen file
 * todo: mechanisme antwoord of volgende kaart
 * todo: mechanisme vorige kaart
 *
 */
public class Controller {

    private KaartenGui view;
    private Kaartenbak kaarten;
    private Leersessie leersessie;
    private Kaart huidigeKaart = new Kaart("", "");
    private static final int VRAAG_ANTWOORD = 1;
    private static final int ANTWOORD_VRAAG = 2;
    private static final int VRAAG_ANTWOORD_SCHRIJVEN = 3;
    private static final int ANTWOORD_VRAAG_SCHRIJVEN = 4;

    public Controller(KaartenGui view, Kaartenbak kaarten) {
        this.view = view;
        this.kaarten= kaarten;
        leersessie = new Leersessie();
        this.view.buttonHandler(new LeerSessieButtonHandler());
        this.view.textFieldHandler(new FileKaartenBakHandler());
        this.view.windowsListener(new WindowsHandler());

    }

    public void initNieuweKaarten(String filename) {
        String result = loadKaartenbak(filename);
        if (!result.equals("")) {
            view.showMessageCode(result);
            return;
        }
        leersessie.setNaamKaartenbak(filename);
        kaarten.init();
        leersessie.init();
        showStanden();
    }

    public void showStanden() {
        int aantalGoed = 0;
        int aantalNietGoed = 0;
        int aantalNeutraal = 0;

        if (leersessie.getLeervorm() ==  VRAAG_ANTWOORD || leersessie.getLeervorm() == VRAAG_ANTWOORD_SCHRIJVEN) {
            aantalGoed = kaarten.getAantalGoedV();
            aantalNietGoed = kaarten.getAantalNogNietV();
            aantalNeutraal = kaarten.getAantalNeutraalV();
        }
        if (leersessie.getLeervorm() ==  ANTWOORD_VRAAG || leersessie.getLeervorm() == ANTWOORD_VRAAG_SCHRIJVEN) {
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

    public String loadKaartenbak(String filename) {
        return kaarten.loadKaartenbak(filename);
    }

    public void volgendeKaart() {
        kaarten.volgendeKaart();
        toonKaart();

    }

    public void vorigeKaart() {
        kaarten.vorigeKaart();
        toonKaart();

    }

    public void volgendeKaartNietGoed() {

    }
    public void toonKaart() {
        huidigeKaart = kaarten.getHuidigeKaart();
        String kaartTekst = "";
        String info = "";
        String kaartnummer = Integer.toString(kaarten.getIndex() + 1);

        if (leersessie.getLeervorm() == VRAAG_ANTWOORD) {
            if (kaarten.getIsVraag()){
                kaartTekst = huidigeKaart.getVoorkant();
                info = "Vraag : ";
            } else {
                kaartTekst = huidigeKaart.getAchterkant();
                info = "Antwoord : ";
            }

        }

        view.showKleur(huidigeKaart.getGekendVoorkant());

        view.showKaartTekst(info + " " + kaartnummer + "\n\n" + kaartTekst);
    }



    public void setKaartGekend() {
           huidigeKaart.setGekendVoorkant("goed");
           kaarten.setKaart(huidigeKaart);
           kaarten.telStanden();
           showStanden();
        }



    public void setKaartNietGekend() {
        huidigeKaart.setGekendVoorkant("niet");
        kaarten.setKaart(huidigeKaart);
        kaarten.telStanden();
        showStanden();


    }




class LeerSessieButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            JButton button  = (JButton) e.getSource();
            String name = button.getName();
            // controlLeervorm1.leersessieKaart();
            switch (name) {
                case "nextCard" -> volgendeKaart();
                case "nextCardIncorrect" -> volgendeKaartNietGoed();
                case "formerCard" -> vorigeKaart();
                case "correct" -> setKaartGekend();
                case "incorrect" -> setKaartNietGekend();
            }
        }
    }

    class FileKaartenBakHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField name = (JTextField)  e.getSource();
            String filename = name.getText();
            initNieuweKaarten(filename);
        }
    }

    class WindowsHandler implements WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
           kaarten.saveFile(leersessie.getNaamKaartenbak());

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
