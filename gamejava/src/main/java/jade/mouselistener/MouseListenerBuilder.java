package jade.mouselistener;

public class MouseListenerBuilder {
    private double posX = 0;
    private double posY = 0;

    public MouseListenerBuilder(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public MouseListenerBuilder setPosX(double posX) {
        this.posX = posX;
        return this;
    }

    public MouseListenerBuilder setPosY(double posY) {
        this.posY = posY;
        return this;
    }

    public MouseListener build() {
        return new MouseListener(posX, posY);
    }
}