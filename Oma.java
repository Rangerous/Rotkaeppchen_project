public class Oma extends VerwunschenerWald implements Person {

    public Oma (Position position){
        super (position);
    }
    // String Markierung von Oma auf der Karte
    @Override
    public String getName (){
        return "O";
    }
    // Dialog zwischen Oma und Rotkäppchen, Oma spricht nur wenn der Zähler auf 2 ist, d.h. wenn Rotkäppchen schon etwas gesagt hat
    @Override
    public void sprechen(Person konversationspartner,int zaehler){
        if (zaehler == 2){
            System.out.println("Hallo, Rotkaeppchen");
            zaehler++;
            konversationspartner.sprechen(this,zaehler);
        }
        
    }
}