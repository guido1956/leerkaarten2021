public class Start {
    public static void main(String[] args) {
        KaartenGui view = new KaartenGui();
        Kaartenbak kaarten = new Kaartenbak();
        Controller control = new Controller(view, kaarten);
    }
}
