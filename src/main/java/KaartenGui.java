import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

public class KaartenGui extends JFrame {

    private JTabbedPane tabTabs;
    private KaartenBeheerGui beheerview = new KaartenBeheerGui();
    private KaartenLeersessieGui leersessieview = new KaartenLeersessieGui();

    public KaartenGui() {
        initGui();
    }

    private void initGui() {
        setSize(800, 430);
        setFocusable(true);
        setLocation(400, 250);
        setLocation(400, 300);
        getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.lightGray));
        requestFocusInWindow();
        leersessieview.createGuiLeersessie();
        beheerview.createGuiBeheer();
        setUpTabs();
        setTitle("Leren met flashcards -31-10 2021- Guido Dulos  versie 7");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        repaint();
    }


    public void setUpTabs() {
        tabTabs = new JTabbedPane();
        Border rand = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        tabTabs.setBorder(rand);
        tabTabs.add("leren",  leersessieview );
        tabTabs.add("beheer", beheerview);
        tabTabs.setBounds(50, 50, 200, 200);
        this.add(tabTabs);
    }

    void tabHandler(ChangeListener tabListener) {
        tabTabs.addChangeListener(tabListener);
    }

    // Afhandeling leersessieview


    public void vulModules(ArrayList<String> modules) {
        leersessieview.vulModules(modules);
    }

    public void showFileName(String name) {
        leersessieview.showFileName(name);
    }

    public void setButtonTekst(String waarde) {
        leersessieview.setButtonTekst(waarde);
    }

    public void setChkNogNiet(boolean check) {
        leersessieview.setChkNogNiet(check);
    }

    public void setChkAutocue(boolean check) {
        leersessieview.setChkAutocue(check);
    }

    public void setVoorkantRadioButton(boolean check) {
        leersessieview.setVoorkantRadioButton(check);
    }

    public void setAchterkanRadioButton(boolean check) {
        leersessieview.setAchterkanRadioButton(check);
    }


    public void setChkRandom(boolean check) {
        leersessieview.setChkRandom(check);
    }

    public void showMessageCode(String code) {
        leersessieview.showMessageCode(code);
    }

    public void showGaNaarKaart(String waarde) {
        leersessieview.showGaNaarKaart(waarde);
    }

    public void showTotaalInFilter(String message) {
        leersessieview.showTotaalInFilter(message);
    }

    public String getGaNaarKaart() {
        return leersessieview.getGaNaarKaart();
    }


    public void showTotEnMet(String waarde) {
        leersessieview.showTotEnMet(waarde);
    }

    public String getTotEnMet() {
        return leersessieview.getTotEnMet();
    }

    public boolean getIsNogNiet() {
        return leersessieview.getIsNogNiet();
    }


    public void showAantalGoed(String waarde) {
        leersessieview.showAantalGoed(waarde);
    }

    public void showInfo(String waarde) {
        leersessieview.showInfo(waarde);
    }

    public void herteken() {
        leersessieview.herteken();
    }

    public void showAantalNeutraal(String waarde) {
        leersessieview.showAantalNeutraal(waarde);
    }

    public void showAantalNietGoed(String waarde) {
        leersessieview.showAantalNietGoed(waarde);
    }

    public void showAantalTotaal(String waarde) {
        leersessieview.showAantalTotaal(waarde);
    }

    public void showKleur(String kleur) {
        leersessieview.showKleur(kleur);
    }

    public boolean getIsRandom() {
        return leersessieview.getIsRandom();
    }

    public boolean getIsAutoCue() {
        return leersessieview.getIsAutoCue();
    }

    public void setIsAutoCue(boolean check) {
        leersessieview.setIsAutoCue(check);
    }

    public void showSelectieModule(String waarde) {
        leersessieview.showSelectieModule(waarde);
    }

    public void showKaartTekst(String waarde) {
        leersessieview.showKaartTekst(waarde);
    }

    public void buttonHandler(ActionListener actionListener) {
        leersessieview.buttonHandler(actionListener);
    }

    public void textFieldHandler(ActionListener actionListener) {
        leersessieview.textFieldHandler(actionListener);
    }

    public void moduleHandler(ActionListener actionListener) {
        leersessieview.moduleHandler(actionListener);
    }

    public void gaNaarHandler(ActionListener actionListener) {
        leersessieview.gaNaarHandler(actionListener);
    }

    public void totenMetHandler(ActionListener actionListener) {
        leersessieview.totenMetHandler(actionListener);
    }

    public void randomHandler(ActionListener actionListener) {
        leersessieview.randomHandler(actionListener);
    }

    public void nogNietHandler(ActionListener actionListener) {
        leersessieview.nogNietHandler(actionListener);
    }

    public void autocueHandler(ActionListener actionListener) {
        leersessieview.autocueHandler(actionListener);
    }

    public void isVoorkantHandler(ActionListener actionListener) {
        leersessieview.isVoorkantHandler(actionListener);
    }

    public void windowsListener(WindowListener windowListener) {
        this.addWindowListener(windowListener);
    }

    //Afhandeling beheerview

    public void beheerButtonHandler(ActionListener actionListener) {
        beheerview.buttonBeheerHandler(actionListener);
    }
    public void showBeheerFileNaam(String waarde) {
        beheerview.showBeheerFileNaam(waarde);
    }
    public void showKaartnummer(String waarde) {
        beheerview.showKaartnummer(waarde);
    }

    public void showKaartgegevens(String waarde) {
        beheerview.showKaartgegevens(waarde);
    }

    public String getBeheerKaartnummer() {
        return beheerview.getBeheerKaartnummer();
    }

    public String getBeheerKaart() {
        return beheerview.getBeheerKaart();
    }



}



