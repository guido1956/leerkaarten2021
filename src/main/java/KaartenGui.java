import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class KaartenGui extends JFrame implements ActionListener{

    private JTextArea txtVraagAntwoord, txtAantalGoed, txtAantalNogNiet, txtAantalNeutraal, txtTotaal,
            startTijdTextArea, eindTijdTextArea, leerTijdTextArea;
    private JButton btnVolgende, btnVorige, btnVanaf,
            leegButton, goedButton, nogNietButton, volgendeNogNietButton,
            resetButton, onthoudScoresButton, resetTijdButton;
    private JTextField naamFileTextField, naarKaartTextField, snelheidTextField, modulesTextField, startModuleTextField,
            eindModuleTextField;
    private JLabel naamFileLabel, naarKaartNrLabel, aantalGoedLabel, aantalNeutraalLabel, aantalNogNietLabel, totaalKaartenTextAreaLabel,
            startTijdLabel, eindTijdLabel, leerTijdLabel, minuutLabel, snelheidLabel, modulesLabel;
    private JCheckBox kleurCheckBox, randomCheckBox, schrijvenCheckBox;
    private JRadioButton voorkantRadioButton, achterkantRadioButton;
    private JComboBox<String> modulesCombo = new JComboBox<>();


    public KaartenGui() {
        initGui();
    }

    private void initGui() {

        setSize(1500, 850);
        setFocusable(true);
        requestFocusInWindow();
        createGui();
        setTitle("Leren met flashcards -17-10 2021- Guido Dulos  versie 2.0");
        setVisible(true);
        repaint();
    }

    public void createGui() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        naamFileLabel = new JLabel("naam kaartenbestand: ");
        window.add(naamFileLabel);

        naamFileTextField = new JTextField(15);
        naamFileTextField.setBackground(Color.white);
        window.add(naamFileTextField);


        naarKaartNrLabel = new JLabel("start vanaf:");
        window.add(naarKaartNrLabel);

        naarKaartTextField = new JTextField(5);
        naarKaartTextField.setBackground(Color.white);
        naarKaartTextField.setText("1");
        window.add(naarKaartTextField);


        leerTijdLabel = new JLabel("geoefend minuten:");
        window.add(leerTijdLabel);
        leerTijdTextArea = new JTextArea("", 1, 2);
        leerTijdTextArea.setBackground(Color.yellow);
        leerTijdTextArea.setEditable(false);
        window.add(leerTijdTextArea);


        modulesLabel = new JLabel("module");
        window.add(modulesLabel);

        modulesTextField = new JTextField(2);
        modulesTextField.setBackground(Color.yellow);
        modulesTextField.setEditable(false);
        modulesTextField.setText("0");
        modulesTextField.addActionListener(this);
        window.add(modulesTextField);

        totaalKaartenTextAreaLabel = new JLabel("kaarten:");
        window.add(totaalKaartenTextAreaLabel);

        txtTotaal = new JTextArea("", 1, 4);
        txtTotaal.setBackground(Color.yellow);
        txtTotaal.setEditable(false);
        window.add(txtTotaal);

        aantalNeutraalLabel = new JLabel("neutraal");
        window.add(aantalNeutraalLabel);

        txtAantalNeutraal = new JTextArea("", 1, 4);
        txtAantalNeutraal.setBackground(Color.yellow);
        txtAantalNeutraal.setEditable(false);
        window.add(txtAantalNeutraal);

        aantalGoedLabel = new JLabel("goed:");
        window.add(aantalGoedLabel);
        txtAantalGoed = new JTextArea("", 1, 4);
        txtAantalGoed.setBackground(Color.yellow);
        txtAantalGoed.setEditable(false);
        window.add(txtAantalGoed);

        aantalNogNietLabel = new JLabel("nog niet:");
        window.add(aantalNogNietLabel);

        txtAantalNogNiet = new JTextArea("", 1, 4);
        txtAantalNogNiet.setBackground(Color.yellow);
        txtAantalNogNiet.setEditable(false);
        window.add(txtAantalNogNiet);

        startModuleTextField = new JTextField(3);
        startModuleTextField.setBackground(Color.yellow);
        startModuleTextField.setEditable(false);
        startModuleTextField.setText("0000");
        startModuleTextField.addActionListener(this);
        window.add(startModuleTextField);

        eindModuleTextField = new JTextField(3);
        eindModuleTextField.setBackground(Color.yellow);
        eindModuleTextField.setEditable(false);
        eindModuleTextField.setText("9999");
        eindModuleTextField.addActionListener(this);
        window.add(eindModuleTextField);


        txtVraagAntwoord = new JTextArea("", 17, 82);  //voor beamer 63
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

        goedButton = new JButton("goed");
        goedButton.setName("correct");
        window.add(goedButton);
        goedButton.addActionListener(this);

        nogNietButton = new JButton("nog niet");
        nogNietButton.setName("incorrect");
        window.add(nogNietButton);
        nogNietButton.addActionListener(this);

        btnVorige = new JButton("vorige kaart");
        btnVorige.setName("formerCard");
        window.add(btnVorige);
        btnVorige.addActionListener(this);

        volgendeNogNietButton = new JButton("volgende \"nog niet\"");
        volgendeNogNietButton.setName("nextCardIncorrect");
        window.add(volgendeNogNietButton);
        volgendeNogNietButton.addActionListener(this);

        btnVanaf = new JButton("vanaf kaartnr");
        window.add(btnVanaf);
        btnVanaf.addActionListener(this);

        onthoudScoresButton = new JButton("onthoud scores");
        window.add(onthoudScoresButton);
        onthoudScoresButton.addActionListener(this);


//        for (int x = 0; x < MAX_MODULES - 1; x++) {
//            modules[x] = "module " + x + " ";
//            modulesCombo.addItem(modules[x]);
//        }

//        modulesCombo.addActionListener(new ActionListener());
//            public void actionPerformed(ActionEvent e) {
//                int index;
//                if (inKaartFile != null) {
//                    String test = (String) modulesCombo.getSelectedItem();
//                    modulesTextField.setText(test.substring(0, 3));
//                    if (test.substring(0, 2).equals("  ")) {
//                        index = Integer.parseInt(test.substring(0, 3));
//                    } else {
//                        index = Integer.parseInt(test.substring(0, 3));
//                    }
//                    if (index == 0) {
//                        isModuleActief = false;
//                    } else {
//                        isModuleActief = true;
//                    }
//                    String start = modules[index].substring(0, 4);
//                    String einde = modules[index].substring(5, 9);
//                    startModuleTextField.setText(start);
//                    eindModuleTextField.setText(einde);
//                    naarKaartTextField.setText(start);
//                    hertelScores();
//                    gaNaarKaart();
//
//
//              }
//      }
    //    });

        window.add(modulesCombo);

        resetButton = new JButton("reset scores");
        window.add(resetButton);
        resetButton.addActionListener(this);

        resetTijdButton = new JButton("reset tijd");
        window.add(resetTijdButton);
        resetTijdButton.addActionListener(this);

        voorkantRadioButton = new JRadioButton("start met voorkant");
        window.add(voorkantRadioButton);
        voorkantRadioButton.setSelected(true);
        voorkantRadioButton.addActionListener(this);


        achterkantRadioButton = new JRadioButton("start met achterkant");
        window.add(achterkantRadioButton);
        achterkantRadioButton.setSelected(false);
        achterkantRadioButton.addActionListener(this);


        ButtonGroup volgorde = new ButtonGroup();
        volgorde.add(voorkantRadioButton);
        volgorde.add(achterkantRadioButton);

        kleurCheckBox = new JCheckBox("kleur");
        window.add(kleurCheckBox);
        kleurCheckBox.setSelected(true);
        kleurCheckBox.addActionListener(this);

        randomCheckBox = new JCheckBox("random");
        window.add(randomCheckBox);
        randomCheckBox.setSelected(false);
        randomCheckBox.addActionListener(this);

        schrijvenCheckBox = new JCheckBox("schrijven");
        window.add(schrijvenCheckBox);
        schrijvenCheckBox.setSelected(false);
        schrijvenCheckBox.addActionListener(this);
    }

    /**
     * todo : parameteriseren via objecten
     */
    public String maakMessage(String code ) {
        return switch (code) {
            case "EC fileNotFound" -> "Bestand niet gevonden";
            case "EC fillArrayError" -> "Bestand met kaarten bevat errors";
            default -> "Onbekende fout";
        };
    }

    public void showMessageCode(String code) {
        String message =  maakMessage(code);
        showMessageDialog(null, message);
    }





    @Override
    public void actionPerformed(ActionEvent e) {

    }

   public  void buttonHandler(ActionListener actionListener) {
       btnVolgende.addActionListener(actionListener);
       volgendeNogNietButton.addActionListener(actionListener);
       btnVorige.addActionListener(actionListener);
       nogNietButton.addActionListener(actionListener);
       goedButton.addActionListener(actionListener);
   }

   public void textFieldHandler(ActionListener actionListener) {
        naamFileTextField.addActionListener(actionListener);
   }

   public void showAantalGoed(String waarde) {
        txtAantalGoed.setText(waarde);
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

    public void showModuleStart (String waarde) {
        startModuleTextField.setText(waarde);
    }

    public void showModuleEinde (String waarde) {
        eindModuleTextField.setText(waarde);
    }

    public void showKaartTekst (String waarde) {
        txtVraagAntwoord.setText(waarde);
    }
}



