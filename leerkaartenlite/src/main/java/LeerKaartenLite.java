import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * Deze code is geschreven voordat de programmeur het MCV-model kende en nog weinig verstand had
 * van object-oriented programmeren.
 * <p>
 * In de maand maart 2021 zal de code daarom helemaal gerefactord worden en voor demo-doeleinden gedocumenteerd
 * <p>
 * Deze versie is alleen geschikt om iets te laten zien van het werken met methods.
 * <p>
 * <p>
 * Klasse LeerKaarten
 * Studeren met LeerKaarten
 * Een leerkaart is een kaart met een voorkant en een achterkant. De voorkant kan gezien worden als een vraag, de
 * achterkant als een antwoord. Bij het leren van woorden van een vreemde taal, staat op de ene kant van de kaart
 * de taal in taal1 en op de andere kant van de kaart de vertaling in taal2.
 * <p>
 * Versie 1.8
 * Guido Dulos
 * maart 2018
 * <p>
 * Functionaliteiten
 * Basis tonen "vraag" en daarna tonen "antwoord"
 * Basis kunnen aangeven welke "goed" is (gekend) en welke nog niet (gekend) is.
 * Leren van modules (groepen kaarten)
 * Leren van als "nog niet gekend" genoemde kaarten
 * Leren vanuit voorkant kaart / leren vanuit achterkant kaart
 * Leren vanuit random over hele bestand
 * Leren vanuit random over modules
 * <p>
 * De GUI-manager is "FLOW" en dat betekent dat de plaatsing van de componenten afhankelijk is de schermgrootte.
 * Standaard is alles netjes uitgelijnd op de laptop, maar nog niet getest op andere pc's.
 * <p>
 * De GUI maakt gebruik van de SWING-componenten. Dit zijn windowscomponenten. Er wordt veel met knoppen gewerkt.
 * <p>
 * Klasse LeerKaarten
 * Studeren met LeerKaarten
 * Een leerkaart is een kaart met een voorkant en een achterkant. De voorkant kan gezien worden als een vraag, de
 * achterkant als een antwoord. Bij het leren van woorden van een vreemde taal, staat op de ene kant van de kaart
 * de taal in taal1 en op de andere kant van de kaart de vertaling in taal2.
 * <p>
 * Versie 1.8
 * Guido Dulos
 * maart 2018
 * <p>
 * Functionaliteiten
 * Basis tonen "vraag" en daarna tonen "antwoord"
 * Basis kunnen aangeven welke "goed" is (gekend) en welke nog niet (gekend) is.
 * Leren van modules (groepen kaarten)
 * Leren van als "nog niet gekend" genoemde kaarten
 * Leren vanuit voorkant kaart / leren vanuit achterkant kaart
 * Leren vanuit random over hele bestand
 * Leren vanuit random over modules
 * <p>
 * De GUI-manager is "FLOW" en dat betekent dat de plaatsing van de componenten afhankelijk is de schermgrootte.
 * Standaard is alles netjes uitgelijnd op de laptop, maar nog niet getest op andere pc's.
 * <p>
 * De GUI maakt gebruik van de SWING-componenten. Dit zijn windowscomponenten. Er wordt veel met knoppen gewerkt.
 */

/**
 * Klasse LeerKaarten
 * Studeren met LeerKaarten
 * Een leerkaart is een kaart met een voorkant en een achterkant. De voorkant kan gezien worden als een vraag, de
 * achterkant als een antwoord. Bij het leren van woorden van een vreemde taal, staat op de ene kant van de kaart 
 * de taal in taal1 en op de andere kant van de kaart de vertaling in taal2. 
 *
 * Versie 1.8
 * Guido Dulos
 * maart 2018 
 */

/**
 * Functionaliteiten
 * Basis tonen "vraag" en daarna tonen "antwoord"
 * Basis kunnen aangeven welke "goed" is (gekend) en welke nog niet (gekend) is. 
 * Leren van modules (groepen kaarten)
 * Leren van als "nog niet gekend" genoemde kaarten
 * Leren vanuit voorkant kaart / leren vanuit achterkant kaart
 * Leren vanuit random over hele bestand
 * Leren vanuit random over modules
 *
 * De GUI-manager is "FLOW" en dat betekent dat de plaatsing van de componenten afhankelijk is de schermgrootte. 
 * Standaard is alles netjes uitgelijnd op de laptop, maar nog niet getest op andere pc's. 
 *
 * De GUI maakt gebruik van de SWING-componenten. Dit zijn windowscomponenten. Er wordt veel met knoppen gewerkt.
 */

/**
 * Public class LeerKaarten
 * Deze class bevat alleen private methodes die dus alleen gebruikt worden vanuit deze class zelf. 
 */
public class LeerKaartenLite extends JFrame implements ActionListener {

    final int MAX_KAARTEN = 10000;  //@@@ dit gaat vervangen worden door een arraylist 
    final int GOED = 1;             //markeringsfile
    final int NOGNIET = 2;          //markeringsfile
    final int NEUTRAAL = 3;         //markeringsFile
    final int TIJDVOORRANDOM = 3;   //default tijd in seconden bij continu random serie
    final int MAX_MODULES = 1000;    //aantal groepen kaarten die kunnen worden geleerd


    // WINDOWCOMPONENTEN
    private JTextArea kaartenTextArea, naamAantalGoedTextArea, naamAantalGoedNogNietTextArea, totaalKaartenTextArea,
            startTijdTextArea, eindTijdTextArea, leerTijdTextArea;
    private JButton volgendeKaartButton, vorigeKaartButton, vanafKaartButton,
            leegButton, goedButton, nogNietButton, volgendeNogNietButton,
            resetButton, onthoudScoresButton, resetTijdButton;
    private JTextField naamFileTextField, naarKaartTextField, snelheidTextField, modulesTextField, startModuleTextField,
            eindModuleTextField;
    private JLabel naamFileLabel, naarKaartNrLabel, aantalGoedLabel, aantalNogNietLabel, totaalKaartenTextAreaLabel,
            startTijdLabel, eindTijdLabel, leerTijdLabel, minuutLabel, snelheidLabel, modulesLabel;
    private JCheckBox kleurCheckBox, randomCheckBox, schrijvenCheckBox;
    private JRadioButton voorkantRadioButton, achterkantRadioButton;
    private JComboBox<String> modulesCombo = new JComboBox<String>();


    //WINDOWCOMPONENTEN einde
    private int regelTeller = 0;     // houd bij welke kaartop scherm staat
    private int goedTeller = 0;      // houd bij hoeveel kaarten als "goed" zijn gemarkeerd
    private int nogNietTeller = 0;  //  houd bij hoeveel kaarten als "nog niet" zijn gemarkeerd
    private BufferedReader inKaartFile, inScoreFile, inScenesFile;   // filehandlers voor files
    private boolean isVoorkant, isAchterkant; //vlaggen voor opties om door de tekst te bladeren
    private boolean isModuleActief, isRandom, isSchrijven;
    private int[] leerKaarten = new int[MAX_KAARTEN]; //array om goed / niet goed bij te houden 
    private int[] nietGoedScoreV = new int[MAX_KAARTEN];
    private int[] nietGoedScoreA = new int[MAX_KAARTEN];
    private LocalTime startTijd, eindTijd;            //variabelen om oefentijd te berekenen
    private boolean continu = false;
    private String[] modules = new String[MAX_MODULES];
    private int startModule, eindModule;
    private int oefenTijd;
    private ArrayList<String> arKaarten = new ArrayList<String>();
    private ArrayList<String> arModules = new ArrayList<String>();

    private ArrayList<Integer> arScoresV = new ArrayList<Integer>();
    private ArrayList<Integer> arScoresA = new ArrayList<Integer>();

    /**
     * main - maakt het frame aan
     * Daarna is het programma alleen nog maar aan het luisteren en gaat steeds naar actionPerformed (automatisch)
     * om user-events af te handelen. 
     * @param args
     */

    public static void main(String[] args) {

        LeerKaartenLite frame = new LeerKaartenLite();

        frame.setSize(1500, 850);
        // frame.setSize(800,700);

        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.createGui();
        frame.setTitle("Leren met flashcards -10-10 2021- Guido Dulos  versie 1.09");
        frame.setVisible(true);
        frame.repaint();
        frame.resetGemarkeerd();  // array met goed / niet goed wordt gevuld met neutrale waarden
    }

    /**
     * createGUI
     * MAKEN VAN DE WINDOWSPAGINA  
     * Er is slechts één windowspagina 
     * Zie voor schermafdruk document Leerkaartentrainer User Stories en design versie 0.1
     * Alle teksten van buttons en labels op het scherm zijn met kleine letters.
     * Gebruik van FLOWmanager en dat betekent dat de volgorde waarin de componenten toegevoegd worden (add()) de 
     * volgorde op het scherm bepaalt.
     * @param
     */

    private void createGui() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        naamFileLabel = new JLabel("naam kaartenbestand: ");
        window.add(naamFileLabel);

        naamFileTextField = new JTextField(15);
        naamFileTextField.setBackground(Color.white);
        window.add(naamFileTextField);
        naamFileTextField.addActionListener(this);

        naarKaartNrLabel = new JLabel("start vanaf:");
        window.add(naarKaartNrLabel);

        naarKaartTextField = new JTextField(5);
        naarKaartTextField.setBackground(Color.white);
        naarKaartTextField.setText("1");
        window.add(naarKaartTextField);

        startTijd = LocalTime.now();
        eindTijd = LocalTime.now();
        leerTijdLabel = new JLabel("geoefend minuten:");
        window.add(leerTijdLabel);
        leerTijdTextArea = new JTextArea("", 1, 2);
        leerTijdTextArea.setBackground(Color.yellow);
        leerTijdTextArea.setEditable(false);
        leerTijdTextArea.setText(Integer.toString(tijdVerstreken(startTijd, eindTijd)));
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

        totaalKaartenTextArea = new JTextArea("", 1, 4);
        totaalKaartenTextArea.setBackground(Color.yellow);
        totaalKaartenTextArea.setEditable(false);
        window.add(totaalKaartenTextArea);

        aantalGoedLabel = new JLabel("goed:");
        window.add(aantalGoedLabel);

        naamAantalGoedTextArea = new JTextArea("", 1, 4);
        naamAantalGoedTextArea.setBackground(Color.yellow);
        naamAantalGoedTextArea.setEditable(false);
        window.add(naamAantalGoedTextArea);

        aantalNogNietLabel = new JLabel("nog niet:");
        window.add(aantalNogNietLabel);

        naamAantalGoedNogNietTextArea = new JTextArea("", 1, 4);
        naamAantalGoedNogNietTextArea.setBackground(Color.yellow);
        naamAantalGoedNogNietTextArea.setEditable(false);
        window.add(naamAantalGoedNogNietTextArea);

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

        /**
         * kaartenTextArea is de TextArea die de teksten van de kaarten gaat bevatten.
         * Scrollable - terugloop - op hele woord afbreken - lettergrootte 17
         */
        kaartenTextArea = new JTextArea("", 17, 82);  //voor beamer 63
        JScrollPane scrollPane = new JScrollPane(kaartenTextArea);
        kaartenTextArea.setLineWrap(true);
        kaartenTextArea.setWrapStyleWord(true);
        kaartenTextArea.setEditable(false);
        Font font = new Font("Default", Font.PLAIN, 20);
        kaartenTextArea.setFont(font);
        window.add(scrollPane);


        volgendeKaartButton = new JButton("volgende kaart");
        window.add(volgendeKaartButton);
        volgendeKaartButton.addActionListener(this);

        goedButton = new JButton("goed");
        window.add(goedButton);
        goedButton.addActionListener(this);

        nogNietButton = new JButton("nog niet");
        window.add(nogNietButton);
        nogNietButton.addActionListener(this);

        vorigeKaartButton = new JButton("vorige kaart");
        window.add(vorigeKaartButton);
        vorigeKaartButton.addActionListener(this);

        volgendeNogNietButton = new JButton("volgende \"nog niet\"");
        window.add(volgendeNogNietButton);
        volgendeNogNietButton.addActionListener(this);

        vanafKaartButton = new JButton("vanaf kaartnr");
        window.add(vanafKaartButton);
        vanafKaartButton.addActionListener(this);

        onthoudScoresButton = new JButton("onthoud scores");
        window.add(onthoudScoresButton);
        onthoudScoresButton.addActionListener(this);


        for (int x = 0; x < MAX_MODULES - 1; x++) {
            modules[x] = "module " + x + " ";
            modulesCombo.addItem(modules[x]);
        }

        modulesCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index;
                if (inKaartFile != null) {
                    String test = (String) modulesCombo.getSelectedItem();
                    modulesTextField.setText(test.substring(0, 3));
                    if (test.substring(0, 2).equals("  ")) {
                        index = Integer.parseInt(test.substring(0, 3));
                    } else {
                        index = Integer.parseInt(test.substring(0, 3));
                    }
                    if (index == 0) {
                        isModuleActief = false;
                    } else {
                        isModuleActief = true;
                    }
                    String start = modules[index].substring(0, 4);
                    String einde = modules[index].substring(5, 9);
                    startModuleTextField.setText(start);
                    eindModuleTextField.setText(einde);
                    naarKaartTextField.setText(start);
                    hertelScores();
                    gaNaarKaart();


                }
            }
        });

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
        isVoorkant = true;

        achterkantRadioButton = new JRadioButton("start met achterkant");
        window.add(achterkantRadioButton);
        achterkantRadioButton.setSelected(false);
        achterkantRadioButton.addActionListener(this);
        isAchterkant = false;

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
     *  AFHANDELING EVENTS VAN DE GEBRUIKER
     *  AFHANDELING BEPALEN VERSTREKEN OEFENTIJD
     *  De belangrijkste interactiemanier is het klikken op knoppen  
     */
    public void actionPerformed(ActionEvent event) {
        /*
         * Afhandelen tijdsverloop (leertijd)
         */

        tijdsVerloopLeertijd();


        /**
         * Zorgen voor onmiddelijke kleurverandering als checkbox wordt getoggeld
         */
        if (event.getSource() == kleurCheckBox && inKaartFile != null) {
            kleurOmzetting();
        }


        if (event.getSource() == voorkantRadioButton) {
            isVoorkant = true;
            isAchterkant = false;
            haalOpGeleerd();
            hertelScores();
            lijstNogNiet();

            if (regelTeller % 2 == 0) {
                regelTeller = regelTeller - 1;
                toonHuidigeRegel();
            }
        }

        if (event.getSource() == achterkantRadioButton) {
            isAchterkant = true;
            isVoorkant = false;
            haalOpGeleerd();
            hertelScores();
            lijstNogNiet();
            if (regelTeller % 2 == 1) {
                regelTeller = regelTeller + 1;
                toonHuidigeRegel();
            }
        }

        /**
         * afhandeling invoerveld textfield
         * Het openen van de file waarvan de gebruiker de tekst heeft opgegeven
         * Na het openen wordt de regelTeller op 0 gezet.
         */
        if (event.getSource() == naamFileTextField) {

            inKaartFile = null;
            inScoreFile = null;
            inScenesFile = null;
            haalModulesOp();
            openArKaartenBestand();
        }

        /**
         * afhandeling knop "volgende regel"
         * In het venster wordt de volgende kaart getoond.
         */
        if (event.getSource() == volgendeKaartButton && inKaartFile != null) {
            if (randomCheckBox.isSelected()) {
                volgendeRandomKaart();

            } else {
                eerstvolgendeKaart();
            }
        }
        /**
         * afhandeling knop "Volgende nog niet
         * In het venster wordt,het paar getoond dat
         * als "nog niet" (gekend) is gemarkeerd
         */
        if (event.getSource() == volgendeNogNietButton && inKaartFile != null) {
            if (randomCheckBox.isSelected()) {
                toonVolgendeRandomNogNiet();
            } else {
                toonVolgendeNogNiet();
            }
        }

        /**
         * afhandeling knop "vorige regel"
         * terugbladeren - de voorgaande kaart wordt getoond-
         * Het gaat hierbij altijd om de voorgaande kaart. Er wordt juist geen rekening gehouden met de 
         * opties van de radiobuttons.
         */
        if (event.getSource() == vorigeKaartButton && inKaartFile != null) {
            toonVorigeKaart();
        }

        /**
         * afhandeling knop "vanafKaartButton"
         * Er wordt naar de waarde die in veld "vanafKaartButton" staat gegaan. De tekst van 
         * die regel wordt getoond.
         */
        if (event.getSource() == vanafKaartButton && inKaartFile != null) {
            gaNaarKaart();
        }


        /**
         * Het markeren van een kaartpaar als "goed" (gekend) 
         */
        if (event.getSource() == goedButton) {
            markeerKaart(GOED);

        }

        /**
         * Het markeren van een kaartpaar als "nog niet" (gekend) 
         */
        if (event.getSource() == nogNietButton && inKaartFile != null) {
            markeerKaart(NOGNIET);

        }

        /**
         * Alle markeringen goed en nog niet weghalen
         */
        if (event.getSource() == resetButton && inKaartFile != null) {
            resetGemarkeerd();
            if (isVoorkant) {
                arScoresV.clear();
            } else {
                arScoresA.clear();
            }
        }

        /**
         * Alle markeringen goed en nog niet wegschrijven naar bestand
         */
        if (event.getSource() == onthoudScoresButton && inKaartFile != null) {
            schrijfWegGeleerd();
        }

        if (event.getSource() == resetTijdButton) {
            startTijd = LocalTime.now();
            leerTijdTextArea.setText(Integer.toString(tijdVerstreken(startTijd, eindTijd)));
            leerTijdTextArea.update(leerTijdTextArea.getGraphics());
        }


    }
// EINDE EVENTAFHANDELING 

    // PRIVATE METHODES
    private void haalModulesOp() {
        String line = "";
        String naamFile;
        String[] parser = new String[2];

        naamFile = naamFileTextField.getText();
        parser = naamFile.split("[.]");
        naamFile = parser[0] + "modules.txt";
        inScenesFile = openBestand(naamFile, false);
        if (inScenesFile != null) {
            modulesCombo.removeAllItems();
            for (int x = 0; x < MAX_MODULES; x++) {
                try {
                    line = inScenesFile.readLine();
                    if (line != null) {

                        modules[x] = line.substring(4, 13);
                        modulesCombo.addItem(line.substring(0, 3) + " " + line.substring(14));


                    } else {
                        modules[x] = "0000 9999";
                    }
                } catch (Exception e) {

                }

            }
        } else {

            String eindeKaart = "";
            if (arKaarten.size() < 100) {
                eindeKaart = "0";
            }
            if (arKaarten.size() < 10) {
                eindeKaart = "00";
            }
            for (int x = 0; x < MAX_MODULES; x++) {
                modules[x] = "0000" + "9999";
            }

        }
    }

    /**
     * Tonen van de volgende regel na buttondruk
     */
    private void toonVolgendeRegel() {

        kaartenTextArea.setText("");
        String line = "";

        if (regelTeller - 1 >= arKaarten.size() || regelTeller == 0) {
            if (isVoorkant) {
                regelTeller = 1;
            }
            if (isAchterkant) {
                regelTeller = 2;
            }
        }

        if (regelTeller < 0) {
            regelTeller = 2;
        }
        line = arKaarten.get(regelTeller - 1);

        laatTekstZien(line, true);
    }

    private void laatTekstZien(String line, boolean plaatsNummer) {

        String kaartTekst = "";
        kaartenTextArea.setBackground(Color.getHSBColor(100, 86, 96));
        if (plaatsNummer) {
            kaartTekst = "" + regelTeller + " " + line;
        } else {
            kaartTekst = line;
        }

        if (kleurCheckBox.isSelected()) {
            if (leerKaarten[regelTeller] == 2) {
                kaartenTextArea.setBackground(Color.getHSBColor(0f, 0.551f, 0.804f));
                kaartenTextArea.setBackground(Color.getHSBColor(0.033f, 0.6f, 1f));
            }

            if (leerKaarten[regelTeller] == 1) {
                kaartenTextArea.setBackground(Color.getHSBColor(154, 254, 25));
            }
        }

        if (kaartTekst.length() != 0) {
            String[] parts = kaartTekst.split("#");
            int aantal = parts.length;
            String testje = "";
            for (int x = 0; x < aantal; x = x + 1) {
                testje = testje + parts[x] + "\n";
            }
            kaartenTextArea.append(testje);
        }
    }


    private void hertelScores() {
        goedTeller = 0;
        nogNietTeller = 0;


        int start = Integer.parseInt(startModuleTextField.getText());
        int einde = Integer.parseInt(eindModuleTextField.getText());
        for (int x = start; x <= einde; x++) {
            if ((leerKaarten[x] == 1)) {
                goedTeller = goedTeller + 1;
            }
            if ((leerKaarten[x] == 2)) {
                nogNietTeller = nogNietTeller + 1;
            }
        }
        goedTeller = goedTeller / 2;
        nogNietTeller = nogNietTeller / 2;
        naamAantalGoedTextArea.setText("" + goedTeller);
        naamAantalGoedNogNietTextArea.setText("" + nogNietTeller);
        totaalKaartenTextArea.setText("" + (1 + einde - start) / 2);
        if (einde == 9999) {
            int aantal = bepaalHoeveelKaarten();
            if (aantal % 2 == 1) {
                aantal = aantal - 1;
            }
            totaalKaartenTextArea.setText("" + aantal / 2);
        }

    }

    private int bepaalHoeveelKaarten() {
        return arKaarten.size();
    }


    private void vulArKaarten() {
        String tijdelijk = " ";
        int x = 0;
        try {
            for (x = 0; (!(tijdelijk == null) && (x < MAX_KAARTEN - 1) && !(tijdelijk.equals(""))); x++) {
                tijdelijk = inKaartFile.readLine();
                if (!(tijdelijk == null)) {
                    arKaarten.add(tijdelijk);
                }
            }
            inKaartFile.close();
        } catch (IOException e) {

            try {
                inKaartFile.close();
            } catch (IOException b) {
            }
        }
    }

    /**
     * initialiseer alles op nog niet geleerd van de module die je aan het leren bent
     *
     */
    private void resetGemarkeerd() {

        for (int x = 0; x < MAX_KAARTEN; x++) {
            leerKaarten[x] = 0;
            if (isVoorkant) {
                nietGoedScoreV[x] = 0;
            }
            if (isAchterkant) {
                nietGoedScoreA[x] = 0;
            }
        }
        goedTeller = 0;
        nogNietTeller = 0;
        naamAantalGoedTextArea.setText("" + goedTeller);
        naamAantalGoedNogNietTextArea.setText("" + nogNietTeller);
        kleurOmzetting();
    }

    /**
     * schrijfWegGeleerd()
     * Scores worden weggeschreven naar een file. Deze file krijgt de naam van het leerbestand met aanvulling score.txt
     */
    private void schrijfWegGeleerd() {
        String naamFile;
        PrintWriter writer;

        naamFile = naamFileTextField.getText();
        String[] parser = naamFile.split("[.]");   //van de naam van leerbestand de . en de extensie afhalen

        if (isAchterkant) {
            naamFile = parser[0] + "scoreA.txt";   //naam van file zonder extensie staat in parser[0] dit aanvullen. 
        }
        if (isVoorkant) {
            naamFile = parser[0] + "scoreV.txt";
        }
        try {

            writer = new PrintWriter(new FileOutputStream(naamFile, false));  // PrintWriter heeft methode append
            for (int x = 0; x < MAX_KAARTEN - 1; x++) {
                writer.append("" + leerKaarten[x] + "\n");
            }

            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Kan bestand niet openen");

        }
    }


    private void haalOpGeleerd() {
        String line = "";
        String naamFile;
        String[] parser = new String[2];
        naamFile = naamFileTextField.getText();
        parser = naamFile.split("[.]");
        if (isVoorkant) {
            naamFile = parser[0] + "scoreV.txt";
        }
        if (isAchterkant) {
            naamFile = parser[0] + "scoreA.txt";
        }


        inScoreFile = openBestand(naamFile, true);

        if (inScoreFile != null) {
            int teller = 0;
            for (int x = 0; x < MAX_KAARTEN - 1; x++) {
                try {
                    line = inScoreFile.readLine();
                    leerKaarten[x] = Integer.parseInt(line);
                    if ((isVoorkant) && leerKaarten[x] == 2) {
                        nietGoedScoreV[teller] = x;
                        teller++;
                    }
                    if ((isAchterkant) && leerKaarten[x] == 2) {
                        nietGoedScoreA[teller] = x;
                        teller++;
                    }

                } catch (Exception e) {
                    resetGemarkeerd();

                }
            }
        } else {
            resetGemarkeerd();
        }
    }

    private void toonVolgendeRandomNogNiet() {
        boolean isNietGoedAanwezig = false;
        int begin = 0;
        int einde = 9999;
        int eerste = -1;
        int laatste = 0;
        int totaal = 0;
        Random randomKaartNogNiet = new Random();

        if (isVoorkant && (regelTeller % 2 == 1) && (leerKaarten[regelTeller] != GOED)) {
            regelTeller = regelTeller + 1;
            naarVolgendeRegel();
            return;
        }

        if (isAchterkant && (regelTeller % 2 == 0) && (leerKaarten[regelTeller] != GOED)) {
            regelTeller = regelTeller - 1;
            naarVolgendeRegel();
            return;
        }

        if (isModuleActief) {
            begin = Integer.parseInt(startModuleTextField.getText());
            einde = Integer.parseInt(eindModuleTextField.getText());
        }

        if (isVoorkant) {
            totaal = arScoresV.size();
            for (int x = 0; x < totaal; x = x + 1) {
                if (arScoresV.get(x) >= begin && arScoresV.get(x) <= einde) {
                    if (eerste == -1) {
                        eerste = x;

                    }
                    isNietGoedAanwezig = true;
                    laatste = x;


                }
            }
            if (isNietGoedAanwezig && laatste - eerste != 0) {
                int index = randomKaartNogNiet.nextInt(1 + laatste - eerste);
                regelTeller = arScoresV.get(eerste + index);
                regelTeller = regelTeller - 1;
                naarVolgendeRegel();

            }
        }

        if (isAchterkant) {
            totaal = arScoresA.size();
            for (int x = 0; x < totaal; x++) {
                if (arScoresA.get(x) >= begin && arScoresA.get(x) <= einde) {
                    if (eerste == -1) {
                        eerste = x;
                    }
                    isNietGoedAanwezig = true;
                    laatste = x;

                }
            }
            if (isNietGoedAanwezig) {
                int index = randomKaartNogNiet.nextInt(1 + laatste - eerste);
                regelTeller = arScoresA.get(eerste + index);
                regelTeller = regelTeller;
                naarVolgendeRegel();

            }
        }

    }

    private void toonVolgendeNogNiet() {

        boolean isNietGoedAanwezig = false;
        int begin = 0;
        int einde = 9999;
        int eerste = -1;
        int laatste = 0;
        if (isModuleActief) {
            begin = Integer.parseInt(startModuleTextField.getText());
            einde = Integer.parseInt(eindModuleTextField.getText());
        }

        if (isVoorkant) {
            if (regelTeller % 2 == 1 && (leerKaarten[regelTeller] != GOED)) {
                regelTeller = regelTeller + 1;
                toonVolgendeRegel();
                return;
            }

            int totaal = arScoresV.size();
            for (int x = 0; x < totaal && !isNietGoedAanwezig; x = x + 1) {
                if (arScoresV.get(x) > regelTeller + 1 && arScoresV.get(x) <= einde) {
                    regelTeller = arScoresV.get(x) - 1;
                    isNietGoedAanwezig = true;

                }
            }
            if (isNietGoedAanwezig) {
                toonVolgendeRegel();
            } else {
                for (int x = 0; x < totaal && !isNietGoedAanwezig; x = x + 1)
                    if (arScoresV.get(x) >= begin && arScoresV.get(x) <= einde) {
                        regelTeller = arScoresV.get(x) - 1;
                        toonVolgendeRegel();
                        isNietGoedAanwezig = true;
                    }
            }

        }

        if (isAchterkant) {
            if (regelTeller % 2 == 0 && (leerKaarten[regelTeller] != GOED)) {
                regelTeller = regelTeller - 1;
                toonVolgendeRegel();
                return;
            }

            int totaal = arScoresA.size();
            for (int x = 0; x < totaal && !isNietGoedAanwezig; x = x + 1) {
                if (arScoresA.get(x) > regelTeller + 1 && arScoresA.get(x) <= einde) {
                    regelTeller = arScoresA.get(x);
                    isNietGoedAanwezig = true;

                }
            }
            if (isNietGoedAanwezig) {
                toonVolgendeRegel();
            } else {
                for (int x = 0; x < totaal && !isNietGoedAanwezig; x = x + 1)
                    if (arScoresA.get(x) >= begin && arScoresA.get(x) <= einde) {
                        regelTeller = arScoresA.get(x);
                        toonVolgendeRegel();
                        isNietGoedAanwezig = true;
                    }
            }

        }
    }

    private int laatsteKaart() {
        if (!isModuleActief) {
            return (MAX_KAARTEN - 1);
        }

        if (isModuleActief) {
            return Integer.parseInt(eindModuleTextField.getText());
        }

        return MAX_KAARTEN - 1;  // dummy
    }

    private int tijdVerstreken(LocalTime start, LocalTime eind) {
        return (int) (start.until(eind, MINUTES));

    }

    private int intervalVerstreken(LocalTime start, LocalTime eind) {
        return (int) (start.until(eind, SECONDS));
    }


    private void openArKaartenBestand() {

        inKaartFile = openBestand(naamFileTextField.getText(), true);

        if (inKaartFile != null) {
            arKaarten.clear();
            vulArKaarten();


            regelTeller = 0;
            haalOpGeleerd();
            hertelScores();
            lijstNogNiet();
            toonVolgendeRegel();
        }

    }


    private void toonVorigeKaart() {
        int begin = Integer.parseInt(startModuleTextField.getText());
        if (!(regelTeller == 1) && !(regelTeller - 1 < begin)) {
            regelTeller = regelTeller - 1;  // verlagen van de regelTeller met 1 
        }

        String line = arKaarten.get(regelTeller - 1);
        kaartenTextArea.setText("");
        laatTekstZien(line, true);
    }

    private void gaNaarKaart() {

        int start = Integer.parseInt(startModuleTextField.getText()) - 1;
        int eind = Integer.parseInt(eindModuleTextField.getText()) - 1;

        regelTeller = Integer.parseInt(naarKaartTextField.getText());


        if (regelTeller < start || regelTeller > eind) {
                regelTeller = start;
        }

        if (regelTeller <= 0) {
            regelTeller = 1;
        }

        String line = arKaarten.get(regelTeller - 1);
        kaartenTextArea.setText("");
        laatTekstZien(line, true);
    }

    private void markeerKaart(int marker) {
        if (regelTeller % 2 == 0) {
            leerKaarten[regelTeller] = marker;
            leerKaarten[regelTeller - 1] = marker;
        } else {
            leerKaarten[regelTeller] = marker;
            leerKaarten[regelTeller + 1] = marker;
        }


        lijstNogNiet();


        hertelScores();

        naamAantalGoedTextArea.setText("" + goedTeller);
        naamAantalGoedNogNietTextArea.setText("" + nogNietTeller);
        String line = kaartenTextArea.getText();
        kaartenTextArea.setText("");
        laatTekstZien(line, false);
    }

    private void lijstNogNiet() {
        if (isVoorkant) {
            arScoresV.clear();
        } else {
            arScoresA.clear();
        }

        for (int x = 0; x < MAX_KAARTEN; x = x + 2) {
            if (leerKaarten[x] != GOED) {
                if (isVoorkant) {
                    arScoresV.add(x);

                } else {
                    arScoresA.add(x);

                }
            }
        }
    }


    private void toonHuidigeRegel() {
        if (regelTeller < 1) {
            regelTeller = 1;
        }
        String line = arKaarten.get(regelTeller - 1);
        kaartenTextArea.setText("");
        laatTekstZien(line, true);
    }

    private void kleurOmzetting() {
        String line = kaartenTextArea.getText();
        kaartenTextArea.setText("");
        laatTekstZien(line, false);
    }

    private void naarVolgendeRegel() {
        toonVolgendeRegel();

        return;
    }


    private void eerstvolgendeKaart() {
        // @@@ grenswaarden uitzoeken
        int grens = Integer.parseInt(eindModuleTextField.getText());
        if ((regelTeller > arKaarten.size()) || ((isModuleActief) && (regelTeller >= grens) && (isVoorkant)
                || (isModuleActief) && (regelTeller > grens - 2) && (regelTeller % 2 == 1) && (isAchterkant))) {


            regelTeller = Integer.parseInt(startModuleTextField.getText()) - 1;


        }


        if (isAchterkant) {
            if (regelTeller % 2 == 1) {
                regelTeller = regelTeller + 3;
            } else {
                regelTeller = regelTeller - 1;
            }
        }
        if (isVoorkant) {
            regelTeller = regelTeller + 1;
        }
        naarVolgendeRegel();
    }

    /**
     * Verstreken oefentijd wordt berekend door de starttijd af te trekken van de huidige tijd
     * In een vensterveldje wordt de verstreken tijd getoond sinds opstarten programma of sinds reset van de tijd. 
     *
     */
    private void tijdsVerloopLeertijd() {
        eindTijd = LocalTime.now();
        oefenTijd = intervalVerstreken(startTijd, eindTijd);
        int minuten = oefenTijd / 60;
        int seconden = oefenTijd % 60;
        String sSeconden = "";
        sSeconden = String.format("%02d", seconden);

        String tijd = "" + Integer.toString(minuten) + ":" + sSeconden;
        leerTijdTextArea.setText(tijd);
        leerTijdTextArea.update(leerTijdTextArea.getGraphics());
    }

    /**
     *  afhandeling van het openen van een tekstbestand van formaat UTF8
     */
    private BufferedReader openBestand(String naamBestand, boolean toonFout) {
        try {
            BufferedReader inFile = new BufferedReader(
                    new InputStreamReader(

                            new FileInputStream(naamBestand), "UTF8"));
            return (inFile);
        } catch (IOException e) {
            if (toonFout) {
                JOptionPane.showMessageDialog(null, "File Error: " + e.toString());

            }
        }
        return (null);
    }

    private void haalOpModules() {

        String naamFile = naamFileTextField.getText();
        String[] parser = naamFile.split("[.]");   //van de naam van leerbestand de . en de extensie afhalen
        String naamSceneBestand = parser[0].substring(0, ((parser[0].length()))) + "scenes" + "." + parser[1];
        String line = "";
        inScenesFile = openBestand(naamSceneBestand, false);

        if (inScenesFile != null) {
            for (int x = 0; x < MAX_MODULES - 1; x++) {
                try {
                    modules[x] = inScenesFile.readLine();

                } catch (IOException e) {
                }
            }
        }

    }

    /**
     * Indien checkbox isModule is aangevinkt toont het programma bij "volgende kaart" een random kaart uit
     * het bestand of uit een module binnen het bestand. 
     * Er wordt rekening gehouden of er "een vraag" wordt gesteld of "een antwoord". Na een random vraag wordt altijd 
     * daarna het bij de random vraag behorende antwoord getoond. Wat een vraag / antwoord is wordt bepaald door de 
     * radiobutton isVoorkant / isAchterkant. Voorkant van een kaart heeft een oneven nummer. Achterkant is even. 
     */
    private void volgendeRandomKaart() {
        Random randomKaart = new Random();
        if (isAchterkant) {
            if (regelTeller % 2 == 0) {
                regelTeller = regelTeller - 1;
                toonVolgendeRegel();
            } else {
                if (!isModuleActief) {
                    regelTeller = randomKaart.nextInt(bepaalHoeveelKaarten()) + 1;
                } else {
                    regelTeller = randomBinnenModule();
                }
                if (regelTeller % 2 == 1) {
                    regelTeller = regelTeller + 1;
                    toonVolgendeRegel();
                } else {
                    toonVolgendeRegel();

                }
            }
        }

        if (isVoorkant) {
            if (regelTeller % 2 == 1) {
                regelTeller = regelTeller + 1;
                toonVolgendeRegel();
            } else {
                if (!isModuleActief) {
                    regelTeller = randomKaart.nextInt(bepaalHoeveelKaarten()) + 1;
                } else {
                    regelTeller = randomBinnenModule();
                }

                if (regelTeller % 2 == 0) {
                    regelTeller = regelTeller - 1;
                    toonVolgendeRegel();
                } else {
                    toonVolgendeRegel();
                }
            }
        }
    }

    /**
     * Deze methode geeft een random kaartnummer terug binnen de range van een module.
     * De methode wordt aangeroepen als "volgende kaart" wordt gekozen en er is een module actief en checkbox
     * random is aangevinkt. En dan alleen als "de vraag" wordt opgeroepen en niet het antwoord.
     *
     **/
    private int randomBinnenModule() {
        Random randomKaartInModule = new Random();
        int getal = 0;
        int start = Integer.parseInt(startModuleTextField.getText()) - 1;
        int eind = Integer.parseInt(eindModuleTextField.getText()) - 1;
        getal = start + randomKaartInModule.nextInt(eind - start) + 1;
        return (getal);

    }
}
