public class Coordinates {
    private Double x; //Значение поля должно быть больше -48, Поле не может быть null
    private Double y;

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
