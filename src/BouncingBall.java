import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BouncingBall{

    private Field field;
    private int radius;
    private double speedX;
    private double speedY;
    private  double speed;
    private Color color;
    private double x;
    private double y;
    private boolean isOnBorder=false;


    public BouncingBall(Field field) {
        int maxRadius=40;
        int minRadius=3;

        int maxSpeed=15;

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

                    if(speed>=-200){
                        if(field.GetIsFriction()){
                        speed-= field.GetFriction();
                        }
                    }

                    field.canMove();
                    if (x + speedX <= radius) {
                        speedX = -speedX;
                        x = radius;
                        isOnBorder=true;
                    } else
                    if (x + speedX >= field.getWidth() - radius) {
                        speedX = -speedX;
                        x = new Double(field.getWidth() - radius).intValue();
                        isOnBorder=true;
                    } else
                    if (y + speedY <= radius) {
                        speedY = -speedY;
                        y = radius;
                        isOnBorder=true;
                    } else
                    if (y + speedY >= field.getHeight() - radius) {
                        speedY = -speedY;
                        y = new Double(field.getHeight() - radius).intValue();
                        isOnBorder=true;
                    } else {
                        if(field.GetIsMagneto()&&isOnBorder){

                        }else{
                        x += speedX; y += speedY;
                        isOnBorder=false;
                        }
                    }
                    if(16-speed+field.GetTimeMachine()>0) {
                        Thread.sleep((int) (Math.round(16 - speed + field.GetTimeMachine())));
                    }else{
                       Thread.sleep(1);
                    }
                }
            } catch (InterruptedException ex) {

            }
        }
    };


}
