import javax.swing.*;

public class LeerkaartenStart {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                KaartenGui view = new KaartenGui();
                Kaartenbak kaarten = new Kaartenbak();
                Controller control = new Controller(view, kaarten);
                view.setVisible(true);
            }
        });
    }
}

