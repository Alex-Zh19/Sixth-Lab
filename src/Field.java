import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



public class Field extends JPanel {
    private ArrayList<BouncingBall>balls=new ArrayList<>();
    private boolean pause;
    private ScheduledExecutorService schedule=null;
    private double friction=0;
    private double timeMachine=0;

    Runnable repaintCycle=new Runnable() {
        @Override
        public void run() {
          repaint();
        }
    };
    Field(){
        setBackground(Color.WHITE);
        schedule=Executors.newSingleThreadScheduledExecutor();
        schedule.scheduleAtFixedRate(repaintCycle,0,20,TimeUnit.MILLISECONDS);
    }

    public void AddBall(){
        balls.add(new BouncingBall(this));
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g;
        for (BouncingBall ball: balls) {
            ball.paint(canvas);
        } }

    public synchronized void pause() {
        pause = true;
    }

    public synchronized void canMove()
            throws InterruptedException {
        if (pause) {
          wait();
        }
    }

    public synchronized double GetFriction(){
        return friction;
    }

    public void SetFriction(double friction) {
        if(friction>0&&friction<=60){
        this.friction = friction;
        }
        else{
            this.friction = 0;
        }
    }

    public void SetTimeMachine(double time){
        this.timeMachine=time;
    }

    public synchronized double GetTimeMachine(){
        return timeMachine;
    }
    public synchronized void resume() {
        pause = false;
        notifyAll();
    }


}
