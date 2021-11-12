import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlSchrijf extends Controller {

    public ControlSchrijf(KaartenGui view, Kaartenbak kaarten) {
      super(view, kaarten);
      initHandlers();
    }

    public void initHandlers() {
          this.view.buttonHandler(new SchrijfButtonHandler());
//        this.view.buttonHandler(new LeerSessieButtonHandler());
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



    class SchrijfButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Groente");
            JButton button = (JButton) e.getSource();
            String name = button.getName();
            switch (name) {
                case "volgende" -> volgendeKaart();
                case "vorige" -> vorigeKaart();
                case "check" -> checkSchrijven();
                case "reset" -> reset();
            }
        }
    }


}
