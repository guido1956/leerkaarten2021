import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

public class KaartenLeersessieGui extends JPanel {

    protected JTextArea txtVraagAntwoord, txtAantalGoed, txtAantalNogNiet, txtAantalNeutraal, txtTotaal;
    protected JButton  btnVolgende, btnVorige, btnCheck, btnNietGoed, btnReset, btnFlip;
    protected JTextField naamFileTextField;
    protected JTextField naarKaartTextField, totKaartTextField, zoekWoordField;
    protected JTextField txtInfo, txtTotaalinfilter;
    protected JTextField modulesTextField;
    protected JComboBox<String> modulesCombo;
    protected JCheckBox chkRandom, chkNeutraal,chkGoed, chkNogNiet, chkAutocue;
    protected JRadioButton achterkantRadioButton, voorkantRadioButton;
    protected JLabel lblVulling;

    public void createGuiLeersessie() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        createFileField();
        createStatisticFields();
        createNavigateFields();
        createFilterFields();
        createInfoFields();
        createModuleFields();
        createKaartFields();
        createButtons();
        createComboBoxFields();
        createVulling();
        createRadioButtons();
        createCheckFields();
    }

    public void createFileField() {
        JLabel naamFileLabel = new JLabel("name card database: ");
        this.add(naamFileLabel);
        naamFileTextField = new JTextField(13);
        naamFileTextField.setBackground(Color.white);
        this.add(naamFileTextField);
    }

    public void createStatisticFields() {
        JLabel totaalKaartenTextAreaLabel = new JLabel("amount:  ");
        this.add(totaalKaartenTextAreaLabel);
        txtTotaal = new JTextArea("", 1, 4);
        txtTotaal.setBackground(Color.getHSBColor(100, 86, 96));
        txtTotaal.setEditable(false);
        this.add(txtTotaal);

        JLabel aantalNeutraalLabel = new JLabel("neutral:");
        this.add(aantalNeutraalLabel);
        txtAantalNeutraal = new JTextArea("", 1, 4);
        txtAantalNeutraal.setBackground(Color.getHSBColor(100, 86, 96));
        txtAantalNeutraal.setEditable(false);
        this.add(txtAantalNeutraal);

        JLabel aantalGoedLabel = new JLabel("correct:");
        this.add(aantalGoedLabel);

        txtAantalGoed = new JTextArea("", 1, 4);
        txtAantalGoed.setBackground(Color.getHSBColor(100, 86, 96));
        txtAantalGoed.setEditable(false);
        this.add(txtAantalGoed);

        JLabel aantalNogNietLabel = new JLabel("not yet:");
        this.add(aantalNogNietLabel);
        txtAantalNogNiet = new JTextArea("", 1, 4);
        txtAantalNogNiet.setBackground(Color.getHSBColor(100, 86, 96));
        txtAantalNogNiet.setEditable(false);
        this.add(txtAantalNogNiet);

        JLabel lblVanaf = new JLabel("learning from cardnr:     ");
        this.add(lblVanaf);
    }

    public void createNavigateFields() {
        naarKaartTextField = new JTextField(5);
        naarKaartTextField.setBackground(Color.white);
        naarKaartTextField.setText("");
        naarKaartTextField.setName("go to");
        this.add(naarKaartTextField);

        JLabel lblTM = new JLabel("up to");
        this.add(lblTM);

        totKaartTextField = new JTextField(5);
        totKaartTextField.setBackground(Color.white);
        totKaartTextField.setText("");
        totKaartTextField.setName("till");
        this.add(totKaartTextField);

        zoekWoordField = new JTextField(9);
        zoekWoordField.setName("search word");
        this.add(zoekWoordField);
    }

    public void createFilterFields() {
        JLabel lblInFilter = new JLabel("in filter:");
        this.add(lblInFilter);
        txtTotaalinfilter = new JTextField(4);
        txtTotaalinfilter.setBackground(Color.getHSBColor(100, 86, 96));
        txtTotaalinfilter.setText("");
        txtTotaalinfilter.setEditable(false);
        this.add(txtTotaalinfilter);
    }

    public void createInfoFields() {
        txtInfo = new JTextField(9);
        txtInfo.setBackground(Color.getHSBColor(100, 86, 96));
        txtInfo.setEditable(false);
        this.add(txtInfo);
    }

    public void createModuleFields() {
        modulesTextField = new JTextField(15);
        modulesTextField.setBackground(Color.getHSBColor(100, 86, 96));
        modulesTextField.setEditable(false);
        modulesTextField.setText("");
        this.add(modulesTextField);
    }

    public void createKaartFields() {
        txtVraagAntwoord = new JTextArea("", 8, 40);  //voor beamer 63
        JScrollPane scrollPane = new JScrollPane(txtVraagAntwoord);
        txtVraagAntwoord.setLineWrap(true);
        txtVraagAntwoord.setWrapStyleWord(true);
        txtVraagAntwoord.setEditable(false);
        Font font = new Font("Default", Font.PLAIN, 20);
        txtVraagAntwoord.setFont(font);
        this.add(scrollPane);
    }

    public void createButtons() {
        btnVolgende = new JButton("next card");
        btnVolgende.setName("nextCard");
        this.add(btnVolgende);

        btnCheck = new JButton("correct");
        btnCheck.setName("correct");
        this.add(btnCheck);

        btnNietGoed = new JButton("not yet");
        btnNietGoed.setName("incorrect");
        this.add(btnNietGoed);

        btnVorige = new JButton("former card");
        btnVorige.setName("formerCard");
        this.add(btnVorige);

        btnFlip = new JButton("flip card");
        btnFlip.setName("flip");
        this.add(btnFlip);

        btnReset = new JButton("reset scores");
        btnReset.setName("reset");
        this.add(btnReset);
    }

    public void createComboBoxFields() {
        modulesCombo = new JComboBox<>();
        this.add(modulesCombo);
    }

       public void createVulling() {
          lblVulling = new JLabel("                                ");
          this.add(lblVulling);
    }

    public void createRadioButtons() {
        ButtonGroup volgorde = new ButtonGroup();
        voorkantRadioButton = new JRadioButton("start with front");
        voorkantRadioButton.setSelected(true);
        voorkantRadioButton.setName("radioFront");
        this.add(voorkantRadioButton);

        achterkantRadioButton = new JRadioButton("start met back");
        achterkantRadioButton.setSelected(false);
        achterkantRadioButton.setName("radioBack");
        this.add(achterkantRadioButton);
        volgorde.add(voorkantRadioButton);
        volgorde.add(achterkantRadioButton);
    }

    public void createCheckFields() {
        chkRandom = new JCheckBox("random");
        this.add(chkRandom);
        chkRandom.setSelected(false);

        chkNeutraal = new JCheckBox("neutral");
        this.add(chkNeutraal);
        chkNeutraal.setSelected(true);

        chkGoed = new JCheckBox("correct");
        this.add(chkGoed);
        chkGoed.setSelected(true);

        chkNogNiet = new JCheckBox("not yet");
        this.add(chkNogNiet);
        chkNogNiet.setSelected(true);

        chkAutocue = new JCheckBox("autocue");
        this.add(chkAutocue);
        chkAutocue.setSelected(false);
        JCheckBox schrijvenCheckBox = new JCheckBox("writing");
        //  window.add(schrijvenCheckBox);
        schrijvenCheckBox.setSelected(false);
    }

    public void vulModules(ArrayList<String> modules) {
        int aantal = modulesCombo.getItemCount();
        if (modulesCombo.getItemCount() > 0) {
            for (int x = 0; x < aantal; x++) {
                modulesCombo.removeItem(modulesCombo.getItemAt(1));
            }
        } else {
            modulesCombo.addItem("All");
        }

        for (String e : modules) {
            modulesCombo.addItem(e);
        }
    }

    public void showVulling(String waarde) {
        lblVulling.setText(waarde);
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

    public void setChkGoed(boolean check) {
        chkGoed.setSelected(check);
    }

    public void setChkNeutraal(boolean check) {
        chkNeutraal.setSelected(check);
    }

    public void setChkAutocue(boolean check) {
        chkAutocue.setSelected(check);
    }

    public boolean getVoorkantRadioButton() {
       return voorkantRadioButton.isSelected();
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

    public void showTotEnMet(String waarde) {
        totKaartTextField.setText(waarde);
    }

    public String getTotEnMet() {
        return totKaartTextField.getText();
    }

    public boolean getIsNogNiet() {
        return chkNogNiet.isSelected();
    }

    public boolean getNeutraal() {
        return chkNeutraal.isSelected();
    }

    public boolean getGoed() {
        return chkGoed.isSelected();
    }

    public void setVoorkantRadioButton(boolean check) {
        voorkantRadioButton.setSelected(check);
        achterkantRadioButton.setSelected(!check);
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
        txtVraagAntwoord.setBackground(Color.getHSBColor(100, 86, 96));
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

   public int getModulesCombo() {
        return modulesCombo.getSelectedIndex();
   }

    public void setModulesCombo(int index) {
        modulesCombo.setSelectedIndex(index);
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
        btnFlip.addActionListener((actionListener));
        btnCheck.addActionListener(actionListener);
    }

    public void textFieldHandler(ActionListener actionListener) {
        naamFileTextField.addActionListener(actionListener);
    }

    public void moduleHandler(ActionListener actionListener) {
        modulesCombo.addActionListener(actionListener);
    }

    public void zoekwoordHandler(ActionListener actionListener) {
        zoekWoordField.addActionListener(actionListener);
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

    public void neutraalHandler(ActionListener actionListener) {
        chkNeutraal.addActionListener(actionListener);
    }

    public void goedHandler(ActionListener actionListener) {
        chkGoed.addActionListener(actionListener);
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
            case "EC fileNotFound" -> "File not found";
            case "EC fillArrayError" -> "Database with cards consists errors";
            case "EC cardsNotInFilter" -> "There are zero card in the filter. \nchange filter";
            default -> "unknown error";
        };
    }
}
