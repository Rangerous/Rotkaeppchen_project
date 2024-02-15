public class Wolf extends VerwunschenerWald{

    public Wolf(Position position){
        super(position);
        this.schaden = 5;
    }
    // String Markierung von Wolf auf der Karte
    @Override
    public String getName(){
        return "W";
    }
}