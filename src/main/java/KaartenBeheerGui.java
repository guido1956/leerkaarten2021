import javax.swing.*;
import java.awt.*;

public class KaartenBeheerGui extends JPanel{
    private JTextArea txtEditVraag, txtEditAntwoord, txtEditModule;
    private JButton btnNieuw;
    private KaartenLeersessieGui leersessie;

    public void createGuiBeheer() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblEditVoorkant = new JLabel("Vraag: ");
        txtEditVraag = new JTextArea(4,800);
        this.add(lblEditVoorkant);
        this.add(txtEditVraag);
        JLabel lblEditAchterkant = new JLabel("Antwoord: ");
        txtEditAntwoord = new JTextArea(4,80);
        this.add(lblEditAchterkant);
        this.add(txtEditAntwoord);


        JLabel lblEditModule = new JLabel("Module: ");
        txtEditModule = new JTextArea(1,80);
        this.add(lblEditModule);
        this.add(txtEditModule);
    }
}
