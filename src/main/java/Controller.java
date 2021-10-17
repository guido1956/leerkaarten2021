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

    public static void main(String[] args) {
        KaartenGui view = new KaartenGui();
        Kaartenbak kaarten = new Kaartenbak();
        Controller control = new Controller(view, kaarten);

    }

    private KaartenGui view;
    private Kaartenbak kaarten;
    private Leersessie leersessie;

    Kaart huidigeKaart = new Kaart("", "");

    public Controller(KaartenGui view, Kaartenbak kaarten) {
        this.view = view;
        this.kaarten= kaarten;
        leersessie = new Leersessie();

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




class LeerSessieHandler implements ActionListener {
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

}
