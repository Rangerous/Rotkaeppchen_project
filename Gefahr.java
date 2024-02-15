public class Gefahr extends VerwunschenerWald{

    public Gefahr(Position position){
        super(position);
        this.schaden = 55;
    }
    // String Markierung von einer Gefahr auf der Karte
    @Override
    public String getName(){
        return "G";
    }
}