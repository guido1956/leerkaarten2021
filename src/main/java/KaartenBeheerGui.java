import javax.swing.*;
import java.awt.*;

public class KaartenBeheerGui extends JPanel{
    private JTextField txtKaartnr;
    private JTextArea txtFilename, txtInvoerKaart;
    private JButton btnNieuw, btnVerwijder, btnSave;
    private static final int BREEDTE = 200;

    public void createGuiBeheer() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel lblkaartnr = new JLabel("kaartnr: ");
        JLabel lblonzichtbaar = new JLabel(" ".repeat(BREEDTE));
        JLabel lblonzichtbaar2 = new JLabel(" ".repeat(BREEDTE + 20));
        txtKaartnr = new JTextField(4);
        this.add(lblkaartnr);
        this.add(txtKaartnr);
        this.add(lblonzichtbaar);
        this.add(lblonzichtbaar2);


        JLabel lblonzichtbaar3 = new JLabel(" ".repeat(BREEDTE + 20));
        JLabel lblfilename = new JLabel("naam file: ");
        txtFilename = new JTextArea(1,BREEDTE);
        this.add(lblfilename);
        this.add(txtFilename);
        this.add(lblonzichtbaar3);

        JLabel lblInvoerKaart = new JLabel("vraag-antwoord-module");
        txtInvoerKaart = new JTextArea(4,BREEDTE);
        this.add(lblInvoerKaart);
        this.add(txtInvoerKaart);

        for (int x= 1; x<4;x++) {
            this.add(new JLabel(" ".repeat(BREEDTE + 20)));
        }

        btnNieuw = new JButton("nieuw");
        btnNieuw.setName("nieuw");
        this.add(btnNieuw);

        btnVerwijder = new JButton("verwijder");
        btnVerwijder.setName("verwijder");
        this.add(btnVerwijder);

        btnSave = new JButton("save");
        btnSave.setName("save");
        this.add(btnSave);
    }

    public void showKaartnummer(String waarde) {
        txtKaartnr.setText(waarde);
    }

    public void showKaartgegevens(String waarde) {
        txtInvoerKaart.setText(waarde);

    }

    public void showBeheerFileNaam(String waarde) {
        txtFilename.setText(waarde);
    }

    public String getBeheerKaartnummer() {
        return txtKaartnr.getText();
    }

    public String getBeheerKaart() {
        return txtInvoerKaart.getText();
    }

    public void beheerMaakLeeg(String waarde) {
        txtKaartnr.setText(waarde);
        txtInvoerKaart.setText("");
    }




}
