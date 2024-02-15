public class Maerchen{

    public static void main(String[] args){
        // erstellen der Karte bzw des Waldes, indem ein Objekt vom Typ Maerchenwelt  erstellt wird
        Maerchenwelt maerchenwelt = new Maerchenwelt (40,10,20,40);
        // Handlungsablauf starten
        maerchenwelt.start();
    }
}