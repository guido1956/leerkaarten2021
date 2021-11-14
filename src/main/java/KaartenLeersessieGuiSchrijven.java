import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class KaartenLeersessieGuiSchrijven extends KaartenLeersessieGui{

    private JTextArea txtSchrijf;
    private JButton btnCheck;

    public void createGuiLeersessie() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        createFileField();
        createStatisticFields();
        createNavigateFields();
        createFilterFields();
        createInfoFields();
        createModuleFields();
        createKaartFields();
        createSchrijfFields();
        createButtons();
        createComboBoxFields();
        createVulling();
        createRadioButtons();
        createCheckFields();

    }

    public void createKaartFields() {
        txtVraagAntwoord = new JTextArea("", 4, 40);  //voor beamer 63
        JScrollPane scrollPane = new JScrollPane(txtVraagAntwoord);
        txtVraagAntwoord.setLineWrap(true);
        txtVraagAntwoord.setWrapStyleWord(true);
        txtVraagAntwoord.setEditable(false);
        Font font = new Font("Default", Font.PLAIN, 20);
        txtVraagAntwoord.setFont(font);
        this.add(scrollPane);
    }


    public void createSchrijfFields() {
        txtSchrijf = new JTextArea("", 4, 40);  //voor beamer 63
        JScrollPane scrollPane = new JScrollPane(txtSchrijf);
        txtSchrijf.setLineWrap(true);
        txtSchrijf.setWrapStyleWord(true);
        Font font = new Font("Default", Font.PLAIN, 20);
        txtSchrijf.setFont(font);
        this.add(scrollPane);
    }

    public void showGaNaarKaart(String waarde) {
        naarKaartTextField.setText(waarde);
    }

    public void showKaartTekst(String waarde) {
        txtVraagAntwoord.setText(waarde);
    }



    public String getSchrijfTekst() {
        return txtSchrijf.getText();
    }

    public void setSchrijfKaart(String waarde) {
        txtSchrijf.setText(waarde);
    }

    public void setBtnCheck(boolean waarde) {
        btnCheck.setEnabled(waarde);
    }

    public void createCheckFields() {
        chkRandom = new JCheckBox("random");
        this.add(chkRandom);
        chkRandom.setSelected(false);

        chkNogNiet = new JCheckBox("nog niet");
        this.add(chkNogNiet);
        chkNogNiet.setSelected(false);
    }

    public void createButtons() {
        btnVolgende = new JButton("volgende kaart");
        btnVolgende.setName("volgende");
        this.add(btnVolgende);

        btnCheck = new JButton("check");
        btnCheck.setName("check");
        this.add(btnCheck);

        btnVorige = new JButton("vorige kaart");
        btnVorige.setName("vorige");
        this.add(btnVorige);

        btnReset = new JButton("reset scores");
        btnReset.setName("reset");
        this.add(btnReset);
    }


    public void schrijfButtonHandler(ActionListener actionListener) {
        btnVolgende.addActionListener(actionListener);
        btnReset.addActionListener(actionListener);
        btnVorige.addActionListener(actionListener);
        btnCheck.addActionListener(actionListener);
    }

    public void enterToetsHandlerSchrijf (KeyAdapter e ) {
        txtSchrijf.addKeyListener(e);
    }



    @Override
    public void createRadioButtons() {
        super.createRadioButtons();
    }
}
