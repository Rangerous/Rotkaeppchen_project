import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;

public class Maerchenwelt {

    private int x; 
    private int y; 
    private VerwunschenerWald [][] karte; 
    private Oma oma;
    private Rotkaeppchen rotkaeppchen;
    private Wolf wolf;
    private Gefahr gefahr;

    // Karte des Waldes wird über den Konstruktor initialisiert
    public Maerchenwelt (int x, int y, int gefahrenAnzahl, int baumAnzahl) throws IllegalArgumentException{
        this.x = x;
        this.y = y;
        // illegalargumentexception wenn die Anzahl der Hindernisse zu groß ist
        if ((x*y - 3) < gefahrenAnzahl + baumAnzahl){
            throw new IllegalArgumentException("Reduzieren Sie die Anzahl der Baeume und Gefahren.");
        }
        //illegalargumentexception wenn der Wald zu klein ist
        if (x < 10 || y < 10){
            throw new IllegalArgumentException("Vergroessern Sie den verwunschenen Wald.");
        }
        // karte initialisieren 
        this.karte = new VerwunschenerWald [x][y];
        //rotkäppchen an stelle 0,0 initialisieren
        this.rotkaeppchen = new Rotkaeppchen (new Position(0,0));
        // der Stelle 0,0 der Karte rotkaeppchen zuweisen
        karte [0][0] = rotkaeppchen; 
        
        // Oma in zufälligen Bereich initialisieren
        // und Oma der Karte an dieser Stelle zuweisen
        int omaX = (int) (Math.random() * (x - (x - 8) + 1));
        int omaY = (int) (Math.random() * (y - (y - 8) + 1));
        this.oma = new Oma (new Position(omaX,omaY));
        karte [omaX][omaY] = oma;
        
        // Wolf der Karte hinzufügen, nur wenn die zufällig erstellte Stelle frei ist       
        Random rand = new Random();
        boolean w = true;
        while (w){
            int wolfX = rand.nextInt(x);
            int wolfY = rand.nextInt(y);
            if (karte[wolfX][wolfY] == null){
                this.wolf = new Wolf(new Position(wolfX,wolfY));
                karte [wolfX][wolfY] = wolf;
                w = false;
            }
        }

        //Bäume der Karte hinzufügen, nur wenn die zufällig erstellten Stellen frei sind
        int i = 0;
        while (i < baumAnzahl) {
            int baumX = rand.nextInt(x);
            int baumY = rand.nextInt(y);
            if (karte [baumX][baumY] == null){
                Baum baum = new Baum(new Position(baumX,baumY));
                karte [baumX][baumY]= baum;
                i++;
            }
            
        }
        // Gefahren der Karte hinzufügen, nur wenn die erstellten Stellen frei sind
        int j = 0;
        while(j < gefahrenAnzahl){
            int gefahrX = rand.nextInt(x);
            int gefahrY = rand.nextInt(y);
            if (karte [gefahrX][gefahrY] == null){
                this.gefahr = new Gefahr(new Position(gefahrX,gefahrY));
                karte [gefahrX][gefahrY] = gefahr;
                j++;
            }
        }
    }
    
	public VerwunschenerWald[][] getKarte (){
        return this.karte;
    }

	public Oma getOma() {
		return this.oma;
	}

	public Rotkaeppchen getRotkaeppchen() {
		return this.rotkaeppchen;
	}
    // Bewegung von Rotkäppchen im Wald in zufällige Richtung
    // stets überprüfen ob Rotkäppchen auf andere Objekte des Waldes trifft, mit entsprechenden Folgen
	public ArrayList <Position> wegFinden(Position ziel){
        ArrayList <Position> weg = new ArrayList <Position> ();
        //Anzahl der Schritte
        int schritte = 0; 
        Random randRichtung = new Random();
        boolean bewegen = true;
        while (bewegen){
            int richtung = randRichtung.nextInt(4) + 1; // Zufall in welche Richtung Rotkäppchen geht
            
            switch (richtung){
                // Rotkäppchen geht ein Schritt hoch
                case 1:
                    if (rotkaeppchen.getPosition().getY() > 0 && ! (karte [rotkaeppchen.getPosition().getX()][rotkaeppchen.getPosition().getY()] instanceof Baum)){
                        rotkaeppchen.geheHoch();
                        weg.add(rotkaeppchen.getPosition());
                    }
                    schritte++;
                   
                    break;
                // Rotkäppchen geht einen Schritt runter    
                case 2:
                    if (rotkaeppchen.getPosition().getY() < this.y - 1 && ! (karte [rotkaeppchen.getPosition().getX()][rotkaeppchen.getPosition().getY()] instanceof Baum)){
                        rotkaeppchen.geheRunter();
                        weg.add(rotkaeppchen.getPosition());
                    }
                    schritte++;
                    
                    break;
                // Rotkäppchen geht einen Schritt rechts 
                case 3:
                    if (rotkaeppchen.getPosition().getX() < this.x - 1 && ! (karte [rotkaeppchen.getPosition().getX()][rotkaeppchen.getPosition().getY()] instanceof Baum)){
                        rotkaeppchen.geheRechts();
                        weg.add(rotkaeppchen.getPosition());
                    }
                    schritte++;
                    
                    break;
                // Rotkäppchen geht einen Schritt links
                case 4:
                    if (rotkaeppchen.getPosition().getX() > 0 && ! (karte [rotkaeppchen.getPosition().getX()][rotkaeppchen.getPosition().getY()] instanceof Baum)){
                        rotkaeppchen.geheLinks();
                        weg.add(rotkaeppchen.getPosition());
                    }
                    schritte++;
                    
                    break;
            }
            // Falls neue Position von rotkäppchen mit Wolf übereinstimmt, wird ihr schaden von gesundheit abgezogen
            if (rotkaeppchen.getPosition().equals(wolf.getPosition())){
                rotkaeppchen.gesundheitVerringern(wolf.getSchaden());
                // falls rotkäppchen nicht mehr lebendig ist, wird die Bewegung gestoppt 
                if (rotkaeppchen.istNochLebendig() == false){
                    bewegen = false;
                }
            }
            // Falls neue Position von rotkäppchen einer Gefahr übereinstimmt, wird ihr schaden von gesundheit abgezogen
            if (rotkaeppchen.getPosition().equals(gefahr.getPosition())){
                rotkaeppchen.gesundheitVerringern(karte[rotkaeppchen.getPosition().getX()][rotkaeppchen.getPosition().getY()].getSchaden());
                // falls rotkäppchen nicht mehr lebendig ist, wird die Bewegung gestoppt
                if (rotkaeppchen.istNochLebendig() == false){
                    bewegen = false;
                }
            }

            // Falls rotkäppchens Position mit Omas position übereinstimmt, wird rotkäppchens gesundheitwert auf 100 gesetzt,
            // und die Bewegung wird gestoppt
            if (rotkaeppchen.getPosition().equals(oma.getPosition())){
                rotkaeppchen.setGesundheit(rotkaeppchen.getGesundheit());
                bewegen = false;
            }
            // Falls Rotkäppchen 500 Schritte gegangen ist, wird die Bewegung gestoppt
            if (schritte == 1000){
                bewegen = false;
            }

        }
        return weg;
    }
    // Wald auf Konsole ausgeben um zu sehen wie er aussieht
    public void printWald(){
        // Rahmen: linke obere Ecke
        System.out.print("+");
        // Rahmen: erste Zeile
        for(int i = 0; i < x; i++){
            System.out.print("-");
        }
        // Rahmen: rechte obere Ecke
        System.out.println("+");
        for(int j = 0; j < y; j++) {
            // Rahmen: linker Rand
            System.out.print("|");
            // Die eigentliche Karte
            for(int i = 0; i < x; i++) {
                if (karte[i][j] != null ) {
                    System.out.print(karte[i][j].getName());
                }else{
                    System.out.print(" ");
                }
            }
            // Rahmen: rechter Rand
            System.out.println("|");
        }
        // Rahmen: linke untere Ecke
        System.out.print("+");
        // Rahmen: letzte Zeile
        for(int i = 0; i < x; i++){
        System.out.print("-");
        }
        // Rahmen: rechte untere Ecke
        System.out.println("+");
    }

    
    // 1. ausgeben des Waldes über printWald
    // 2. aufrufen von wegFinden mit Position von Oma als ziel
    // ggf. wegFinden mit Rotkäppchens Startposition als Ziel aufrufen, falls Rotkäppchen Oma gefunden hat
    
    public void start(){
        printWald();
        wegFinden(oma.getPosition());

        // Falls rotkäppchen Oma gefunden hat
        if (rotkaeppchen.getPosition().equals(oma.getPosition())){
            System.out.println("Rotkaeppchen ist bei Oma angekommen.");
            //Dialog zwischen Rotkäppchen und Oma
            rotkaeppchen.sprechen(oma,1);
            // Heimweg von Rotkäppchen, zur Position mit der sie initialisiert wurde
            Position zuHause = new Position (0,0);
            wegFinden(zuHause);
            // Falls Rotkäppchen zu Hause angekommen ist
            if (rotkaeppchen.getPosition().equals(zuHause)){
                System.out.println("Rotkaeppchen ist wieder zu Hause angekommen.");
            } 
            // Falls Rotkäppchen nicht zu hause angekommen ist, aber noch lebendig
            else if (rotkaeppchen.istNochLebendig()){
                System.out.println("Rotkaeppchen hat sich auf dem Heimweg verlaufen.");
            }
            // falls rotkäppchen nicht zu hause angekommen ist und auch nicht mehr lebendig ist
            else {
                System.out.println("Rotkaeppchen ist nicht wieder zu Hause angekommen.");
            }
        }
        // falls rotkäppchen auf dem weg zur Oma nicht mehr lebendig ist
        else if (rotkaeppchen.istNochLebendig() == false){
            System.out.println("Rotkaeppchen ist nicht bei der Oma angekommen.");
        }
        // Falls Rotkäppchen nicht bei ihrer Oma angekommen ist, aber noch lebendig
        else {
            System.out.println("Rotkaeppchen hat sich auf dem Weg zur Oma verlaufen.");
        }
        
    }


}