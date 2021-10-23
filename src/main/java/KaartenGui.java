import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

public class KaartenGui extends JFrame {
    private JTextArea txtVraagAntwoord, txtAantalGoed, txtAantalNogNiet, txtAantalNeutraal, txtTotaal;
    private JButton btnVolgende, btnVorige, btnGoed, btnNietGoed, btnReset;
    private JTextField naamFileTextField;
    private JTextField naarKaartTextField, totKaartTextField;
    private JTextField txtInfo;
    private JTextField modulesTextField;
    private JComboBox<String> modulesCombo;
    private JCheckBox chkRandom, chkNogNiet, chkAutocue;
    private JRadioButton achterkantRadioButton, voorkantRadioButton;


    public KaartenGui() {
        initGui();
    }

    private void initGui() {
        setSize(780, 400);
        setFocusable(true);
        setLocation(400, 250);
        setLocation(400, 300);
        getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.lightGray));
        requestFocusInWindow();
        createGui();
        setTitle("Leren met flashcards -23-10 2021- Guido Dulos  versie 4");
        setVisible(true);
        repaint();
    }

    public void createGui() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel naamFileLabel = new JLabel("naam kaartenbestand: ");
        window.add(naamFileLabel);

        naamFileTextField = new JTextField(15);
        naamFileTextField.setBackground(Color.white);
        window.add(naamFileTextField);

        JLabel totaalKaartenTextAreaLabel = new JLabel("kaarten:");
        window.add(totaalKaartenTextAreaLabel);

        txtTotaal = new JTextArea("", 1, 4);
        txtTotaal.setBackground(Color.getHSBColor(100, 86, 96));
        txtTotaal.setEditable(false);
        window.add(txtTotaal);

        JLabel aantalNeutraalLabel = new JLabel("neutraal");
        window.add(aantalNeutraalLabel);

        txtAantalNeutraal = new JTextArea("", 1, 4);
        txtAantalNeutraal.setBackground(Color.getHSBColor(100, 86, 96));
        txtAantalNeutraal.setEditable(false);
        window.add(txtAantalNeutraal);

        JLabel aantalGoedLabel = new JLabel("goed:");
        window.add(aantalGoedLabel);
        txtAantalGoed = new JTextArea("", 1, 4);
        txtAantalGoed.setBackground(Color.getHSBColor(100, 86, 96));
        txtAantalGoed.setEditable(false);
        window.add(txtAantalGoed);

        JLabel aantalNogNietLabel = new JLabel("nog niet:");
        window.add(aantalNogNietLabel);

        txtAantalNogNiet = new JTextArea("", 1, 4);
        txtAantalNogNiet.setBackground(Color.getHSBColor(100, 86, 96));
        txtAantalNogNiet.setEditable(false);
        window.add(txtAantalNogNiet);

        JLabel lblVanaf = new JLabel("leren vanaf kaartnr:      ");
        window.add(lblVanaf);

        naarKaartTextField = new JTextField(5);
        naarKaartTextField.setBackground(Color.white);
        naarKaartTextField.setText("1");
        naarKaartTextField.setName("ganaar");
        window.add(naarKaartTextField);

        JLabel lblTM = new JLabel("t/m");
        window.add(lblTM);

        totKaartTextField = new JTextField(5);
        totKaartTextField.setBackground(Color.white);
        totKaartTextField.setText("999");
        totKaartTextField.setName("totAan");
        window.add(totKaartTextField);

        txtInfo = new JTextField(15);
        txtInfo.setBackground(Color.getHSBColor(100, 86, 96));
        txtInfo.setEditable(false);
        window.add(txtInfo);

        modulesTextField = new JTextField(15);
        modulesTextField.setBackground(Color.getHSBColor(100, 86, 96));
        modulesTextField.setEditable(false);
        modulesTextField.setText("alle modules");
        window.add(modulesTextField);

        txtVraagAntwoord = new JTextArea("", 8, 40);  //voor beamer 63
        JScrollPane scrollPane = new JScrollPane(txtVraagAntwoord);
        txtVraagAntwoord.setLineWrap(true);
        txtVraagAntwoord.setWrapStyleWord(true);
        txtVraagAntwoord.setEditable(false);
        Font font = new Font("Default", Font.PLAIN, 20);
        txtVraagAntwoord.setFont(font);
        window.add(scrollPane);

        btnVolgende = new JButton("volgende kaart");
        btnVolgende.setName("nextCard");
        window.add(btnVolgende);

        btnGoed = new JButton("goed");
        btnGoed.setName("correct");
        window.add(btnGoed);

        btnNietGoed = new JButton("nog niet");
        btnNietGoed.setName("incorrect");
        window.add(btnNietGoed);

        btnVorige = new JButton("vorige kaart");
        btnVorige.setName("formerCard");
        window.add(btnVorige);

        btnReset = new JButton("reset scores");
        btnReset.setName("reset");
        window.add(btnReset);

        modulesCombo = new JComboBox<>();
        window.add(modulesCombo);

        JLabel lblVulling = new JLabel("                                ");
        window.add(lblVulling);
        voorkantRadioButton = new JRadioButton("start met voorkant");
        window.add(voorkantRadioButton);
        voorkantRadioButton.setSelected(true);

        JRadioButton achterkantRadioButton = new JRadioButton("start met achterkant");
        window.add(achterkantRadioButton);
        achterkantRadioButton.setSelected(false);

        ButtonGroup volgorde = new ButtonGroup();
        volgorde.add(voorkantRadioButton);
        volgorde.add(achterkantRadioButton);

        chkRandom = new JCheckBox("random");
        window.add(chkRandom);
        chkRandom.setSelected(false);

        chkNogNiet = new JCheckBox("nog niet");
        window.add(chkNogNiet);
        chkNogNiet.setSelected(false);

        chkAutocue = new JCheckBox("autocue");
        window.add(chkAutocue);
        chkAutocue.setSelected(false);


        JCheckBox schrijvenCheckBox = new JCheckBox("schrijven");
        window.add(schrijvenCheckBox);
        schrijvenCheckBox.setSelected(false);
    }

    public void vulModules(ArrayList<String> modules) {
        modulesCombo.addItem("Alle");
        for (String e : modules) {
            modulesCombo.addItem(e);
        }
    }


    public void setButtonTekst(String waarde) {
        btnVolgende.setText(waarde);
    }

    public void windowsListener(WindowListener windowListener) {
        this.addWindowListener(windowListener);
    }


    public String maakMessage(String code) {
        return switch (code) {
            case "EC fileNotFound" -> "Bestand niet gevonden";
            case "EC fillArrayError" -> "Bestand met kaarten bevat errors";
            case "EC cardsNotInFilter" -> "Er zijn 0 kaarten in het filter. \nWijzig het filter";
            default -> "Onbekende fout";
        };
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

    public boolean getStartIsVoorkant() {
        return voorkantRadioButton.isSelected();
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
}



