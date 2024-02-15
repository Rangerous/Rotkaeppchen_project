public class Baum extends VerwunschenerWald {

    public Baum (Position position){
        super (position);
    }
    // String Markierung von einem Baum auf der Karte
    @Override
    public String getName (){
        return "B";
    }
}