import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BouncingBall{

    private Field field;
    private Thread thisThread;
    private String name;

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

        name="ball "+(int)(Math.random()*10);
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


         thisThread= new Thread(myBalls);

        thisThread.start();
    }
    public BouncingBall(Field field,int radius) {

        name="Big Billy";
        int maxSpeed=15;

        this.field = field;

        this.radius = radius;

        speed = new Double(Math.round(5*maxSpeed / radius)).intValue();
        if (speed>maxSpeed) {
            speed = maxSpeed;}

        double angle = Math.random()*2*Math.PI;

        speedX = 3*Math.cos(angle);
        speedY = 3*Math.sin(angle);

        color = new Color((float)Math.random(), (float)Math.random(),
                (float)Math.random());

        x= Math.random()*(field.getSize().getWidth() - 2*radius) + radius;
        y = Math.random()*(field.getSize().getHeight() - 2*radius) + radius;


        thisThread= new Thread(myBalls);

        thisThread.start();
    }

    public String GetName(){
        return this.name;
    }


    public void paint(Graphics2D canvas) {
        canvas.setColor(color);
        canvas.setPaint(color);
        Ellipse2D.Double ball = new Ellipse2D.Double(x - radius, y - radius,
                2*radius, 2*radius);
            canvas.draw(ball);
            if(name.equals("Big Billy")){

            }else{
            canvas.fill(ball);
            }
            canvas.setColor(Color.BLACK);
            canvas.drawString(name,(int)Math.round(x)+radius,(int)Math.round(y));



    }
    Runnable myBalls=new Runnable() {
        @Override
        public void run() {
            RunMethod();
        }
    };

    private void SnowBallMethod(){
            if(radius<=50){
                int path=(int)Math.round(Math.sqrt(speedX*speedX+speedY*speedY));
                radius+=(int)Math.round((path* field.GetSnowBallY())/ field.GetSnowBallX());
            }
    }


    private void RunMethod(){
        try {
            while (true) {
                if(speed>=-200){
                    if(field.GetIsFriction()){
                        speed-= field.GetFriction();
                    }
                }
                if(field.GetIsGrossFeeder()) {
                    double billyX = field.FindBigBilly().x;
                    double billyY = field.FindBigBilly().y;
                    double billySpeedX=field.FindBigBilly().speedX;
                    double billySpeedY=field.FindBigBilly().speedY;
                    int billyRadius = field.FindBigBilly().radius;
                    if (Math.sqrt(((x+speedX-billyX-billySpeedX)*(x+speedX-billyX-billySpeedX))+((y+speedY-billyY-billySpeedY)*
                            (y+speedY-billyY-billySpeedY)))<billyRadius-radius
                            &&x!=billyX &&y!=billyY) {

                        field.canMove();
                        if (x+speedX>=billyX+billyRadius+billySpeedX&&x>billyX&&y<billyY) {
                            System.out.println("if 1");
                            speedX=-speedX;
                            x=billyX+billyRadius;
                        }else if(x+speedX>=billyX+billyRadius+billySpeedX-radius&&x>billyX&&y<billyY){
                            System.out.println("if 2");
                            speedX=-speedX;
                            x=billyX+billyRadius-radius;
                        }else if(y + speedY<=billyY-billyRadius+billySpeedY&&x>billyX&&y<billyY){
                            System.out.println("if 3");
                            speedY=-speedY;
                            y=billyY-billyRadius;
                        } else if (y + speedY<=billyY-billyRadius+billySpeedY-radius&&x>billyX&&y<billyY){
                            System.out.println("if 4");
                            speedY=-speedY;
                            y=billyY-billyRadius-radius;

                        }
                        //2
                        else if(x+speedX<=billyX-billyRadius+billySpeedX&&x<billyX&&y<billyY){
                            System.out.println("if 5");
                            speedX=-speedX;
                            x=billyX-billyRadius;
                        }else if(x+speedX<=billyX-billyRadius+billySpeedX+radius&&x<billyX&&y<billyY){
                            System.out.println("if 6");
                            speedX=-speedX;
                            x=billyX-billyRadius+radius;
                        }else if(y+speedY<=billyY-billyRadius+billySpeedY&&x<billyX&&y<billyY){
                            System.out.println("if 7");
                            speedY=-speedY;
                            y=billyY-billyRadius;
                        }else if(y+speedY<=billyY-billyRadius+billySpeedY+radius&&x<billyX&&y<billyY) {
                            System.out.println("if 8");
                            speedY=-speedY;
                            y=billyY-billyRadius+radius;

                        }


                        //3
                        else if(x+speedX<=billyX-billyRadius+billySpeedX&&x<billyX&&y>billyY){
                            System.out.println("if 9");
                            speedX=-speedX;
                            x=billyX-billyRadius;
                        }else if(x+speedX<=billyX-billyRadius+billySpeedX+radius&&x<billyX&&y>billyY){
                            System.out.println("if 10");
                            speedX=-speedX;
                            x=billyX-billyRadius+radius;
                        }else if(y+speedY>=billyY+billyRadius+billySpeedY&&x<billyX&&y>billyY){
                            System.out.println("if 11");
                            speedY=-speedY;
                            y=billyY+billyRadius;
                        }else if(y+speedY>=billyY+billyRadius+billySpeedY-radius&&x<billyX&&y>billyY) {
                            System.out.println("if 12");
                            speedY=-speedY;
                            y=billyY+billyRadius-radius;
                        }


                        //4
                        if (x+speedX>=billyX+billyRadius+billySpeedX&&x>billyX&&y>billyY) {
                            System.out.println("if 13");
                            speedX=-speedX;
                            x=billyX+billyRadius;
                        }else if(x+speedX>=billyX+billyRadius+billySpeedX-radius&&x>billyX&&y>billyY){
                            System.out.println("if 14");
                            speedX=-speedX;
                            x=billyX+billyRadius-radius;
                        }else if(y+speedY>=billyY+billyRadius+billySpeedY&&x>billyX&&y>billyY){
                            System.out.println("if 15");
                            speedY=-speedY;
                            y=billyY+billyRadius;
                        }else if(y+speedY>=billyY+billyRadius+billySpeedY-radius&&x>billyX&&y>billyY) {
                            System.out.println("if 16");
                            speedY=-speedY;
                            y=billyY+billyRadius-radius;
                        }

                        else {
                            if (field.GetIsCharisma()) {
                                if (field.GetX() >= billyX + billyRadius || field.GetY() >= billyY + billyRadius) {

                                } else {
                                    x = field.GetX();
                                    y = field.GetY();
                                }
                            } else {
                                if (field.GetIsTeam()) {
                                    WeAreTeamMethod();
                                } else {

                                    if (field.GetIsSnowBall()) {
                                        SnowBallMethod();
                                    }
                                    x += speedX;
                                    y += speedY;

                                }
                            }
                        }
                    } else {
                        //  System.out.println("here");
                        RunMethodLogic();
                    }
                }else{
                    RunMethodLogic();
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

    private void RunMethodLogic(){
        if(speed>=-200){
            if(field.GetIsFriction()){
                speed-= field.GetFriction();
            }
        }
        try {
            field.canMove();
        }catch (InterruptedException e){

        }
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
            if(field.GetIsCharisma()){
                x= field.GetX();
                y= field.GetY();
            }
            else{
                if(field.GetIsTeam()){
                    WeAreTeamMethod();
                }else{
                    if(isOnBorder&&field.GetIsMagneto()){
                        //nothing
                    }else if(field.GetIsSandPaper()&&isOnBorder){
                        radius-=field.GetSandPaper();
                        if(radius<=0){
                            thisThread.interrupt();
                        }
                        if(field.GetIsSnowBall()){
                            SnowBallMethod();}
                        x += speedX; y += speedY;
                        isOnBorder=false;
                    }
                    else{
                        if(field.GetIsSnowBall()){
                            SnowBallMethod();
                        }
                        x += speedX; y += speedY;
                        isOnBorder=false;
                    }
                }
            }
        }
    }

    private void WeAreTeamMethod(){
        if(this.name.equals(field.GetName())){
            if(isOnBorder&&field.GetIsMagneto()){
                //nothing
            }else if(field.GetIsSandPaper()&&isOnBorder){
                radius-=field.GetSandPaper();
                if(radius<=0){
                    thisThread.interrupt();
                }
                if(field.GetIsSnowBall()){
                    SnowBallMethod();}
                x += speedX; y += speedY;
                isOnBorder=false;
            }
            else{
                if(field.GetIsSnowBall()){
                    SnowBallMethod();
                }
                x += speedX; y += speedY;
                isOnBorder=false;
            }
        }else{

        }
    }

}
