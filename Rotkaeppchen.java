public class Rotkaeppchen extends VerwunschenerWald implements Person {
    // Gesundheitswert von Rotkäppchen während dem Spiel
    private int gesundheit = 100;

    public Rotkaeppchen (Position position){
        super(position);
    }
    // Bewegungsmethoden von Rotkäppchen 
    // ein Feld nach oben
    public void  geheHoch (){
        this.position.setY(this.position.getY() - 1);
    }
    // ein Feld nach unten
    public void geheRunter (){
        this.position.setY(this.position.getY() + 1);
    }
    // ein Feld nach links
    public void geheLinks (){
        this.position.setX(this.position.getX() -1);
    }
    // ein Feld nach rechts
    public void geheRechts (){
        this.position.setX(this.position.getX() + 1);
    }
    // rotkäppches gesundheit minus dem übergegebenen wert verringern
    public void gesundheitVerringern (int wert) {
        if (this.gesundheit > 0){
            this.gesundheit -= wert;
        }
    }
    
    public int getGesundheit(){
        return this.gesundheit;
    }
    public void setGesundheit (int gesundheit){
        this.gesundheit = 100;
    }
    // gesundheit von rotkäppchen überprüfen
    public boolean istNochLebendig (){
        if (this.gesundheit > 0){
            return true;
        }
        else {
            return false;
        }
    }
    // String R als Merkmal für Rotkäppchen auf der Karte
    @Override
    public String getName (){
        return "R";
    }
    // Dialog zwischen Oma und Rotkäppchen wenn deren Positionen übereinstimmen
    
    
    @Override
    public void sprechen(Person konversationspartner,int zaehler){
        
        if (zaehler == 1){
            System.out.println("Hallo, Oma");
        }
        if (zaehler == 3){
            System.out.println("Tschuess, Oma");
            return;
        }
        zaehler++;
        konversationspartner.sprechen(this,zaehler);
    }
}