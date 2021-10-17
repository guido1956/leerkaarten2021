/**
 * Guido Dulos
 * Herschrijven van leerkaarten lite
 */
public class LeerkaartenPro {
    public static void main(String[] args) {
        Kaartenbak kaarten = new Kaartenbak();
        Kaart kaart = new Kaart("1", "1");
        kaarten.addKaart(kaart);
        kaart = new Kaart("2", "2");
        kaarten.addKaart(kaart);
        kaart = new Kaart("3", "3");
        kaarten.addKaart(kaart);
        kaarten.setModule();
        kaart = kaarten.getHuidigeKaart();
        System.out.println(kaart);
        for (int x = 0 ; x < kaarten.getModuleEinde() ; x++) {
            System.out.println(kaarten.volgendeKaart());
        }



    }
}
