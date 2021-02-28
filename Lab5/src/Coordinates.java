public class Coordinates {
    private final Double x; //Значение поля должно быть больше -48, Поле не может быть null
    private final Double y;

    public Coordinates(Double x, double y) {

        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }


}
