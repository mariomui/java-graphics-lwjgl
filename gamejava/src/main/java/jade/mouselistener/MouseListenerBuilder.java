package jade.mouselistener;

public class MouseListenerBuilder {
    private double posX = 0;
    private double posY = 0;

    public MouseListenerBuilder() {
        this.setPosX(0);
        this.setPosY(0);
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
        return new MouseListener(this.posX, this.posY);
    }
}