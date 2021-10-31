import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

public class KaartenLeersessieGui extends JPanel {

    private JTextArea txtVraagAntwoord, txtAantalGoed, txtAantalNogNiet, txtAantalNeutraal, txtTotaal;
    private JButton btnVolgende, btnVorige, btnGoed, btnNietGoed, btnReset;
    private JTextField naamFileTextField;
    private JTextField naarKaartTextField, totKaartTextField;
    private JTextField txtInfo, txtTotaalinfilter;
    private JTextField modulesTextField;
    private JComboBox<String> modulesCombo;
    private JCheckBox chkRandom, chkNogNiet, chkAutocue;
    private JRadioButton achterkantRadioButton, voorkantRadioButton;


    public void createGuiLeersessie() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel naamFileLabel = new JLabel("naam kaartenbestand: ");
        this.add(naamFileLabel);

        naamFileTextField = new JTextField(13);
        naamFileTextField.setBackground(Color.white);
        this.add(naamFileTextField);

        JLabel totaalKaartenTextAreaLabel = new JLabel("aantal:  ");
        this.add(totaalKaartenTextAreaLabel);

        txtTotaal = new JTextArea("", 1, 4);
        txtTotaal.setBackground(Color.getHSBColor(100, 86, 96));
        txtTotaal.setEditable(false);
        this.add(txtTotaal);

        JLabel aantalNeutraalLabel = new JLabel("neutraal:");
        this.add(aantalNeutraalLabel);

        txtAantalNeutraal = new JTextArea("", 1, 4);
        txtAantalNeutraal.setBackground(Color.getHSBColor(100, 86, 96));
        txtAantalNeutraal.setEditable(false);
        this.add(txtAantalNeutraal);

        JLabel aantalGoedLabel = new JLabel("goed:");
        this.add(aantalGoedLabel);
        txtAantalGoed = new JTextArea("", 1, 4);
        txtAantalGoed.setBackground(Color.getHSBColor(100, 86, 96));
        txtAantalGoed.setEditable(false);
        this.add(txtAantalGoed);

        JLabel aantalNogNietLabel = new JLabel("nog niet:");
        this.add(aantalNogNietLabel);

        txtAantalNogNiet = new JTextArea("", 1, 4);
        txtAantalNogNiet.setBackground(Color.getHSBColor(100, 86, 96));
        txtAantalNogNiet.setEditable(false);
        this.add(txtAantalNogNiet);

        JLabel lblVanaf = new JLabel("leren vanaf kaartnr:     ");
        this.add(lblVanaf);

        naarKaartTextField = new JTextField(5);
        naarKaartTextField.setBackground(Color.white);
        naarKaartTextField.setText("");
        naarKaartTextField.setName("ganaar");
        this.add(naarKaartTextField);

        JLabel lblTM = new JLabel("t/m");
        this.add(lblTM);

        totKaartTextField = new JTextField(5);
        totKaartTextField.setBackground(Color.white);
        totKaartTextField.setText("");
        totKaartTextField.setName("totAan");
        this.add(totKaartTextField);

        JLabel lblInFilter = new JLabel("in filter:");
        this.add(lblInFilter);

        txtTotaalinfilter = new JTextField(5);
        txtTotaalinfilter.setBackground(Color.getHSBColor(100, 86, 96));
        txtTotaalinfilter.setText("");
        txtTotaalinfilter.setEditable(false);
        this.add(txtTotaalinfilter);

        txtInfo = new JTextField(15);
        txtInfo.setBackground(Color.getHSBColor(100, 86, 96));
        txtInfo.setEditable(false);
        this.add(txtInfo);

        modulesTextField = new JTextField(15);
        modulesTextField.setBackground(Color.getHSBColor(100, 86, 96));
        modulesTextField.setEditable(false);
        modulesTextField.setText("");
        this.add(modulesTextField);

        txtVraagAntwoord = new JTextArea("", 8, 40);  //voor beamer 63
        JScrollPane scrollPane = new JScrollPane(txtVraagAntwoord);
        txtVraagAntwoord.setLineWrap(true);
        txtVraagAntwoord.setWrapStyleWord(true);
        txtVraagAntwoord.setEditable(false);
        Font font = new Font("Default", Font.PLAIN, 20);
        txtVraagAntwoord.setFont(font);
        this.add(scrollPane);

        btnVolgende = new JButton("volgende kaart");
        btnVolgende.setName("nextCard");
        this.add(btnVolgende);

        btnGoed = new JButton("goed");
        btnGoed.setName("correct");
        this.add(btnGoed);

        btnNietGoed = new JButton("nog niet");
        btnNietGoed.setName("incorrect");
        this.add(btnNietGoed);

        btnVorige = new JButton("vorige kaart");
        btnVorige.setName("formerCard");
        this.add(btnVorige);

        btnReset = new JButton("reset scores");
        btnReset.setName("reset");
        this.add(btnReset);

        modulesCombo = new JComboBox<>();
        this.add(modulesCombo);

        JLabel lblVulling = new JLabel("                                ");
        this.add(lblVulling);
        voorkantRadioButton = new JRadioButton("start met voorkant");
        voorkantRadioButton.setSelected(true);
        voorkantRadioButton.setName("radioVoorkant");
        this.add(voorkantRadioButton);


        achterkantRadioButton = new JRadioButton("start met achterkant");
        achterkantRadioButton.setSelected(false);
        achterkantRadioButton.setName("radioAchterkant");
        this.add(achterkantRadioButton);

        ButtonGroup volgorde = new ButtonGroup();
        volgorde.add(voorkantRadioButton);
        volgorde.add(achterkantRadioButton);

        chkRandom = new JCheckBox("random");
        this.add(chkRandom);
        chkRandom.setSelected(false);

        chkNogNiet = new JCheckBox("nog niet");
        this.add(chkNogNiet);
        chkNogNiet.setSelected(false);

        chkAutocue = new JCheckBox("autocue");
        this.add(chkAutocue);
        chkAutocue.setSelected(false);
        JCheckBox schrijvenCheckBox = new JCheckBox("schrijven");
        //  window.add(schrijvenCheckBox);
        schrijvenCheckBox.setSelected(false);
    }

    public void vulModules(ArrayList<String> modules) {
        int aantal = modulesCombo.getItemCount();
        if (modulesCombo.getItemCount() > 0) {
            for (int x = 0; x < aantal; x++) {
                System.out.println(x);
                modulesCombo.removeItem(modulesCombo.getItemAt(1));
            }
        } else {
            modulesCombo.addItem("Alle");
        }

        for (String e : modules) {
            modulesCombo.addItem(e);

        }
    }

    public void showFileName(String name) {

        naamFileTextField.setText(name);
    }
    public void setButtonTekst(String waarde) {
        btnVolgende.setText(waarde);
    }

    public void setChkNogNiet(boolean check) {
        chkNogNiet.setSelected(check);
    }

    public void setChkAutocue(boolean check) {
        chkAutocue.setSelected(check);
    }

    public void setVoorkantRadioButton(boolean check) {
        voorkantRadioButton.setSelected(check);
    }

    public void setAchterkanRadioButton(boolean check) {
        achterkantRadioButton.setSelected(check);
    }

    public void setChkRandom(boolean check) {
        chkRandom.setSelected(check);
    }

    public void showMessageCode(String code) {
        String message = maakMessage(code);
        showMessageDialog(null, message);
    }

    public void showGaNaarKaart(String waarde) {
        naarKaartTextField.setText(waarde);
    }

    public String getGaNaarKaart() {
        return naarKaartTextField.getText();
    }

    public void showTotaalInFilter(String message) {
        txtTotaalinfilter.setText(message);
    }

    public boolean getIsNietGoed() {
        return false; //@@ aanpassen
    }

    public void showTotEnMet(String waarde) {
        totKaartTextField.setText(waarde);
    }

    public String getTotEnMet() {
        return totKaartTextField.getText();
    }

    public boolean getIsNogNiet() {
        return chkNogNiet.isSelected();
    }


    public void showAantalGoed(String waarde) {
        txtAantalGoed.setText(waarde);
    }

    public void showInfo(String waarde) {
        txtInfo.setText(waarde);
        repaint();
    }

    public void herteken() {
        txtVraagAntwoord.update(txtVraagAntwoord.getGraphics());
        txtInfo.update(txtInfo.getGraphics());
        totKaartTextField.update(txtInfo.getGraphics());
        naarKaartTextField.update(txtInfo.getGraphics());
        txtTotaalinfilter.update(txtTotaalinfilter.getGraphics());
    }

    public void showAantalNeutraal(String waarde) {
        txtAantalNeutraal.setText(waarde);
    }

    public void showAantalNietGoed(String waarde) {
        txtAantalNogNiet.setText(waarde);
    }

    public void showAantalTotaal(String waarde) {
        txtTotaal.setText(waarde);
    }

    public void showKleur(String kleur) {
        if (kleur.equals("goed")) {
            txtVraagAntwoord.setBackground(Color.getHSBColor(154, 254, 25));
        }
        if (kleur.equals("niet")) {
            txtVraagAntwoord.setBackground(Color.getHSBColor(0.028f, 0.4f, 1f));
        }
        if (kleur.equals("neutraal")) {
            txtVraagAntwoord.setBackground(Color.getHSBColor(100, 86, 96));
        }
    }

    public boolean getIsRandom() {
        return chkRandom.isSelected();
    }

    public boolean getIsAutoCue() {
        return chkAutocue.isSelected();
    }

    public void setIsAutoCue(boolean check) {
        chkAutocue.setSelected(check);
    }


    public void showSelectieModule(String waarde) {
        modulesTextField.setText(waarde);
    }

    public void showKaartTekst(String waarde) {
        txtVraagAntwoord.setText(waarde);
    }

    public void buttonHandler(ActionListener actionListener) {
        btnVolgende.addActionListener(actionListener);
        btnReset.addActionListener(actionListener);
        btnVorige.addActionListener(actionListener);
        btnNietGoed.addActionListener(actionListener);
        btnGoed.addActionListener(actionListener);
    }

    public void textFieldHandler(ActionListener actionListener) {
        naamFileTextField.addActionListener(actionListener);
    }

    public void moduleHandler(ActionListener actionListener) {
        modulesCombo.addActionListener(actionListener);
    }

    public void gaNaarHandler(ActionListener actionListener) {
        naarKaartTextField.addActionListener(actionListener);
    }

    public void totenMetHandler(ActionListener actionListener) {
        totKaartTextField.addActionListener(actionListener);
    }

    public void randomHandler(ActionListener actionListener) {
        chkRandom.addActionListener(actionListener);
    }

    public void nogNietHandler(ActionListener actionListener) {
        chkNogNiet.addActionListener(actionListener);
    }

    public void autocueHandler(ActionListener actionListener) {
        chkAutocue.addActionListener(actionListener);
    }

    public void isVoorkantHandler(ActionListener actionListener) {
        voorkantRadioButton.addActionListener(actionListener);
        achterkantRadioButton.addActionListener(actionListener);
    }

    private String maakMessage(String code) {
        return switch (code) {
            case "EC fileNotFound" -> "Bestand niet gevonden";
            case "EC fillArrayError" -> "Bestand met kaarten bevat errors";
            case "EC cardsNotInFilter" -> "Er zijn 0 kaarten in het filter. \nWijzig het filter";
            default -> "Onbekende fout";
        };
    }
}
