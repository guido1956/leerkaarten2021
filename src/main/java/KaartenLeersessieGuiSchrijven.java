import javax.swing.*;
import java.awt.*;

public class KaartenLeersessieGuiSchrijven extends KaartenLeersessieGui{

    private JTextArea txtSchrijf;
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
        btnVolgende.setName("nextCard");
        this.add(btnVolgende);

        btnGoed = new JButton("check");
        btnGoed.setName("check");
        this.add(btnGoed);

        btnVorige = new JButton("vorige kaart");
        btnVorige.setName("formerCard");
        this.add(btnVorige);

        btnReset = new JButton("reset scores");
        btnReset.setName("reset");
        this.add(btnReset);
    }

}
