import javax.swing.*;

public class LeerkaartenStart {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                KaartenGui view = new KaartenGui();
                Kaartenbak kaarten = new Kaartenbak();
                LeersessieState state = new LeersessieState();
                Controller control = new Controller(view, kaarten, state);
                view.setVisible(true);
            }
        });
    }
}

