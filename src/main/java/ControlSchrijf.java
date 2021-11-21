import java.util.ArrayList;

public class ControlSchrijf {

    //private KaartenLeersessieGuiSchrijven viewSchrijven = new KaartenLeersessieGuiSchrijven();
    private KaartenGui view;
    private Kaartenbak kaarten;
    private LeersessieState state;
    private Kaart huidigeKaart = new Kaart("", "");

    public ControlSchrijf(KaartenGui view, Kaartenbak kaarten, LeersessieState state) {
        this.view = view;
        this.kaarten = kaarten;
        this.state = state;
    }

    public void checkSchrijven() {
        String vraag = huidigeKaart.getAchterkant();
        String schrijf = view.getSchrijfTekst();
        if (vraag.equals(schrijf)) {
            setKaartGekend();
            if (view.getIsNogNiet()) {
                state.bouwFilter();
                state.setIsVraag(true);
                showStandenSchrijf();
                toonKaart();
                view.setSchrijfKaart("");
                return;

            }
        } else {
            if (!schrijf.equals("")) {
                setKaartNietGekend();
            }
        }
        volgendeKaart();
        view.hertekenSchrijf();
        showStandenSchrijf();
        toonKaart();
        view.setSchrijfKaart("");

    }

    public void setKaartNietGekend() {
        if (state.getIsVoorkant()) {
            huidigeKaart.setGekendVoorkant("niet");
        } else {
            huidigeKaart.setGekendAchterkant("niet");
        }
        kaarten.setKaart(huidigeKaart, state.getIndex());
        kaarten.telStanden(state.getIsVoorkant());
        state.setKaarten(kaarten.getKaarten());
        state.setRange(true);
        state.bouwFilter();
        toonKaart();
    }

    public void setKaartGekend() {
        if (state.getIsVoorkant()) {
            huidigeKaart.setGekendVoorkant("goed");
        } else {
            huidigeKaart.setGekendAchterkant("goed");
        }
        kaarten.setKaart(huidigeKaart, state.getIndex());
        kaarten.telStanden(state.getIsVoorkant());
        state.setKaarten(kaarten.getKaarten());
        state.setRange(true);
        state.bouwFilter();
        toonKaart();
    }

    public void showStandenSchrijf() {
        int aantalGoed;
        int aantalNietGoed;
        int aantalNeutraal;

        if (state.getIsVoorkant()) {
            aantalGoed = kaarten.getAantalGoedV();
            aantalNietGoed = kaarten.getAantalNogNietV();
            aantalNeutraal = kaarten.getAantalNeutraalV();
        } else {
            aantalGoed = kaarten.getAantalGoedA();
            aantalNietGoed = kaarten.getAantalNogNietA();
            aantalNeutraal = kaarten.getAantalNeutraalA();
        }

        view.showAantalGoedSchrijf(Integer.toString(aantalGoed));
        view.showAantalNietGoedSchrijf(Integer.toString(aantalNietGoed));
        view.showAantalNeutraalSchrijf(Integer.toString(aantalNeutraal));
        view.showAantalTotaalSchrijf(Integer.toString(kaarten.getAantal()));
     }


    public void toonKaart() {
        if (state.getNoCards()) {
            view.showKaartTekstSchrijf("Er voldoet geen enkele kaart aan de selectiecriteria\nWijzig de selectie");

        } else {
            view.showGaNaarKaartSchrijf(Integer.toString(state.getModuleStart() + 1));
            view.showTotEnMet(Integer.toString(state.getModuleEinde() + 1));
            huidigeKaart = kaarten.getKaart(state.getIndex());
            String kaartTekst;
            String info;
            String kaartnummer = Integer.toString(state.getIndex() + 1);
            String buttontekst;

            if (state.getIsVraag()) {
                view.setBtnCheck(true);
                kaartTekst = huidigeKaart.getVoorkant();
                info = "vraag:";
                buttontekst = "    antwoord      ";
                kaartTekst = kaartTekst.replaceAll("@@", "\n");
            } else {
                view.setBtnCheck(false);
                view.setSchrijfKaart("");
                kaartTekst = huidigeKaart.getAchterkant();
                kaartTekst = kaartTekst.replaceAll("@@", "\n");
                info = " antwoord:";
                buttontekst = "volgende kaart";
            }

            if (state.getIsVoorkant()) {
                view.schrijfShowKleur(huidigeKaart.getGekendVoorkant());
            } else {
                view.schrijfShowKleur(huidigeKaart.getGekendAchterkant());
            }
            view.setButtonTekstSchrijf(buttontekst);
            view.showInfoSchrijf(info + " " + kaartnummer);
            view.showKaartTekstSchrijf(kaartTekst);
            view.schrijfShowSelectieModule(huidigeKaart.getModule());
            view.schrijfShowTotaalInFilter(Integer.toString(state.getAantalInfilter()));
        }
    }

    public void volgendeKaart() {
            state.volgendeKaart();
            toonKaart();
    }

    public void vorigeKaart() {
        state.vorigeKaart();
        toonKaart();
    }

    public void reset() {

    }

    public void maakModules() {
        ArrayList<String> modules = kaarten.getModules();
        view.vulModulesSchrijf(modules);
    }

}
