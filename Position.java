public class Position {

    private int x;
    private int y;

    public Position (int x, int y){
        this.x = x;
        this.y = y;
    }

    // Getter/Setter für Positions Attribute
	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}
    // equals Methode überschreiben, um zwei Objekte vom Typ Position zu vergleichen
    @Override
    public boolean equals (Object o){
        boolean equal = false;       
        if (o instanceof Position){
            Position q = (Position) o;
            equal =  this.x == q.getX() && this.y == q.getY();    
        }
        return equal;
    }
   

}