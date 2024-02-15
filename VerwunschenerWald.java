public abstract class VerwunschenerWald {

    protected Position position;
    protected int schaden = 0;

    public VerwunschenerWald (Position position){
        this.position = position;
        //this.schaden = schaden;
    }

    public Position getPosition(){
        return this.position;
    }

    public int getSchaden(){
        return this.schaden;
    }
    // abstrakte Methode getName, wird in den Unterklassen definiert
    abstract public String getName ();
}