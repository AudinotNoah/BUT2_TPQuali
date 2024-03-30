public class Position {

    private int x;
    private int y;

    /**
     * Getter de la coordonnée x.
     * @return La coordonnée x de la position.
     */
    public int getX(){
        return x;
    }

    /**
     * Getter de la coordonnée y.
     * @return La coordonnée y de la position.
     */
    public int getY(){
        return y;
    }

    /**
     * Setter de la coordonnée x.
     * @param pX La nouvelle valeur de la coordonnée x.
     */
    public void setX(int pX){
        x = pX;
    }

    /**
     * Setter de la coordonnée y.
     * @param pY La nouvelle valeur de la coordonnée y.
     */
    public void setY(int pY){
        y = pY;
    }

    /**
     * Setter pour définir simultanément les coordonnées x et y.
     * @param pX La nouvelle valeur de la coordonnée x.
     * @param pY La nouvelle valeur de la coordonnée y.
     */
    public void setAll(int pX, int pY){
        x = pX;
        y = pY;
    }
}
