public class ControlSchrijf extends Controller {

    // private KaartenLeersessieGuiSchrijven viewSchrijven = new KaartenLeersessieGuiSchrijven();
    public ControlSchrijf(KaartenGui view, Kaartenbak kaarten) {
      super(view, kaarten);
      initHandlers();
    }

    public void initHandlers() {

          this.view.buttonHandler(new SchrijfButtonHandler());

//        this.view.textFieldHandler(new FileKaartenBakHandler());
//        this.view.gaNaarHandler(new GaNaarHandler());
//        this.view.windowsListener(new WindowsHandler());
//        this.view.moduleHandler(new ModulesHandler());
//        this.view.randomHandler(new RandomHandler());
//        this.view.totenMetHandler(new TotHandler());
//        this.view.nogNietHandler(new NogNietHandler());
//        this.view.autocueHandler(new AutocueHandler());
//        this.view.isVoorkantHandler(new IsVoorkantHandler());
//        this.view.tabHandler((new TabHandler()));
//        this.view.beheerButtonHandler(new BeheerButtonHandler());
    }

    public void checkSchrijven() {
        System.out.println("YES HIJ IS ER");
    }

    public void volgendeKaart() {
        super.volgendeKaart();
        System.out.println(state.getNoCards());
    }


    public void toonKaart() {
        if (state.getNoCards()) {
            view.showMessageCode("EC cardsNotInFilter");
        }
        view.showGaNaarKaart(Integer.toString(state.getModuleStart() + 1));
        view.showTotEnMet(Integer.toString(state.getModuleEinde() + 1));
        huidigeKaart = kaarten.getKaart(state.getIndex());
        String kaartTekst;
        String info;
        String kaartnummer = Integer.toString(state.getIndex() + 1);
        String buttontekst;



        if (state.getIsVraag()) {
            kaartTekst = huidigeKaart.getVoorkant();
            info = "vraag:";
            buttontekst = "    antwoord      ";
            kaartTekst = kaartTekst.replaceAll("@@", "\n");
        } else {
            kaartTekst = huidigeKaart.getAchterkant();
            kaartTekst = kaartTekst.replaceAll("@@", "\n");
            info = " antwoord:";
            buttontekst = "volgende kaart";
        }

        if (state.getIsVoorkant()) {
            view.showKleur(huidigeKaart.getGekendVoorkant());
        } else {
            view.showKleur(huidigeKaart.getGekendAchterkant());
        }
        view.setButtonTekst(buttontekst);
        view.showInfo(info + " " + kaartnummer);
        view.showKaartTekst(kaartTekst);
        view.showSelectieModule(huidigeKaart.getModule());
        view.showTotaalInFilter(Integer.toString(state.getAantalInfilter()));
    }
}
