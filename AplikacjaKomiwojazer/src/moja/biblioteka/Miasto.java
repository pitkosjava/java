package moja.biblioteka;

public class Miasto {

    private int x;
    private int y;
    private int index;

    public Miasto() {
        this.x = (int) (Math.random() * 200);
        this.y = (int) (Math.random() * 200);
    }

    public Miasto(int x, int y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }

    // Gets the distance to given city
    public double odlegloscZmiasta(Miasto miasto) {
        int xDistance = Math.abs(getX() - miasto.getX());
        int yDistance = Math.abs(getY() - miasto.getY());
        double distance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));

        return distance;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.x;
        hash = 23 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Miasto other = (Miasto) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + index;
    }

}
