import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public Controller(KaartenGui view, Kaartenbak kaarten) {
        this.view = view;
        this.kaarten= kaarten;
        leersessie = new Leersessie();
         loadKaartenbak("ConGusto.txt");

    }

    public void loadKaartenbak(String filename) {
        System.out.println("Hoera");
        System.out.println(kaarten.loadKaartenbak(filename));
    }

    public void volgendeKaart() {
        kaarten.volgendeKaart();
        huidigeKaart = kaarten.getHuidigeKaart();
        //view.toonKaart()
    }

    public void vorigeKaart() {

    }

    public void volgendeKaartNietGoed() {

    }

    public void setKaartGekend() {

    }

    public void setKaartNietGekend() {

    }




class LeerSessieButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Button button  = (Button) e.getSource();
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
            loadKaartenbak(filename);


        }
    }

}
