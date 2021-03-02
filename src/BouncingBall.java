import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BouncingBall{

    private Field field;
    private int radius;
    private double speedX;
    private double speedY;
    private  int speed;
    private Color color;
    private double x;
    private double y;

    public BouncingBall(Field field) {
        int maxRadius=40;
        int minRadius=3;

        int maxSpeed=19;


        this.field = field;

        radius = new Double(Math.random()*(maxRadius - minRadius)).intValue()
                + minRadius;


         speed = new Double(Math.round(5*maxSpeed / radius)).intValue();
        if (speed>maxSpeed) {
            speed = maxSpeed;}

        double angle = Math.random()*2*Math.PI;

        speedX = 3*Math.cos(angle);
        speedY = 3*Math.sin(angle);

        color = new Color((float)Math.random(), (float)Math.random(),
                (float)Math.random());

        x = Math.random()*(field.getSize().getWidth() - 2*radius) + radius;
        y = Math.random()*(field.getSize().getHeight() - 2*radius) + radius;


        Thread thisThread = new Thread(myBalls);

        thisThread.start();
    }

    public void paint(Graphics2D canvas) {
        canvas.setColor(color);
        canvas.setPaint(color);
        Ellipse2D.Double ball = new Ellipse2D.Double(x - radius, y - radius,
                2*radius, 2*radius);
        canvas.draw(ball);
        canvas.fill(ball);
    }
    Runnable myBalls=new Runnable() {
        @Override
        public void run() {
            try {
                while (true) {
                    field.canMove();
                    if (x + speedX <= radius) {

                        speedX = -speedX; x = radius;
                    } else
                    if (x + speedX >= field.getWidth() - radius) {

                        speedX = -speedX; x = new Double(field.getWidth() - radius).intValue();
                    } else
                    if (y + speedY <= radius) {
                        speedY = -speedY; y = radius;
                    } else
                    if (y + speedY >= field.getHeight() - radius) {

                        speedY = -speedY; y = new Double(field.getHeight() - radius).intValue();
                    } else {

                        x += speedX; y += speedY;
                    }

                    Thread.sleep(20-speed);
                }
            } catch (InterruptedException ex) {

            }
        }
    };


}
