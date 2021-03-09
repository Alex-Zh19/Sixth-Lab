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

    private double distanceBetweenBillyAndBall=0;

    public synchronized void SetFistance(double d){
        distanceBetweenBillyAndBall=d;
    }
    public synchronized double GetD(){
        return distanceBetweenBillyAndBall;
    }

    private double friction=0;
    private boolean isFriction=false;

    private double timeMachine=0;

    private boolean isMagneto=false;

    private double sandPaper=0;
    private boolean isSandPaper=false;

    private double snowBallX=0;
    private double snowBallY=0;
    private boolean isSnowBall=false;

    private String name;
    private boolean isTeam=false;

    private int xCord;
    private int yCord;
    private boolean isCharisma=false;

    private boolean isGrossFeeder=false;

    private boolean isStopSmallBalls=false;
    private boolean isStopSlowBalls=false;

    Runnable repaintCycle=new Runnable() {
        @Override
        public void run() {
          repaint();
        }
    };
    Field(){
        setBackground(Color.WHITE);
        schedule=Executors.newSingleThreadScheduledExecutor();
        schedule.scheduleAtFixedRate(repaintCycle,0,1,TimeUnit.MILLISECONDS);
    }

    public void AddBall(){
        balls.add(new BouncingBall(this));
    }
    public void AddBall(int radius){
        balls.add(new BouncingBall(this,radius));
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
    public synchronized void resume() {
        pause = false;
        notifyAll();
    }


    //friction
    public synchronized boolean GetIsFriction(){
        return isFriction;
    }

    public void SetFriction(double friction) {
        if(friction>0&&friction<=60){
            this.friction = sandPaper;
        }
        else{
            this.friction = 0;
        }
    }

    public synchronized double GetFriction(){
        return friction;
    }

    public void FrictionOn(){
        isFriction=true;
    }
    public void FrictionOff(){
        isFriction=false;
    }


    //sandPaper
    public void SetSandPaper(double sandPaper) {
        if(sandPaper>0&&sandPaper<=10){
        this.sandPaper = sandPaper;
        }
        else{
            this.sandPaper = 0;
        }
        System.out.println(this.sandPaper);
    }

    public synchronized double GetSandPaper(){
        return sandPaper;
    }

    public  void SandPaperOn() {
        isSandPaper=true;
    }
    public  void SandPaperOff() {
        isSandPaper=false;
    }
    public synchronized boolean GetIsSandPaper(){
        return isSandPaper;
    }

    //snowBall
    public void SetSnowBallX(double snowBallX) {
        if(snowBallX>0&&snowBallX<=60){
            this.snowBallX = snowBallX;
        }
        else{
            this.snowBallX = 0;
        }
    }

    public void SetSnowBallY(double snowBallY) {
        if(snowBallY>0&&snowBallY<=10){
            this.snowBallY = snowBallY;
        }
        else{
            this.snowBallY = 0;
        }
    }
    public synchronized double GetSnowBallX(){
        return snowBallX;
    }
    public synchronized double GetSnowBallY(){
        return snowBallY;
    }

    public void SnowBallOn(){
        isSnowBall=true;
    }
    public void SnowBallOff(){
        isSnowBall=false;
    }
    public synchronized boolean GetIsSnowBall(){
        return isSnowBall;
    }


    //magneto
    public  void MagnetoOn() {
        isMagneto=true;
    }
    public  void MagnetoOff() {
        isMagneto=false;
    }

    public synchronized boolean GetIsMagneto(){
        return isMagneto;
    }

    //plus minus time
    public void SetTimeMachine(double time){
        this.timeMachine=time;
    }

    public synchronized double GetTimeMachine(){
        return timeMachine;
    }


    //we are tram
    public void SetName(String name){
        this.name=name;
    }

    public synchronized String GetName(){
        return name;
    }

    public void IsTeamOn(){
        isTeam=true;
    }

    public void IsTeamOff(){
        isTeam=false;
    }
    public synchronized boolean GetIsTeam(){
        return isTeam;
    }


    //charisma
    public void SetCords(int x,int y){
        xCord=x;
        yCord=y;
    }
    public synchronized int GetX(){
        return xCord;
    }
    public synchronized int GetY(){
        return yCord;
    }

    public void IsCharismaOn(){
        isCharisma=true;
    }
    public void IsCharismaOff(){
        isCharisma=false;
    }
    public synchronized boolean GetIsCharisma(){
        return isCharisma;
    }

    //grossFeeder
    public void IsGrossFeederOn(){
        isGrossFeeder=true;
    }
    public void IsGrossFeederOff(){
        isGrossFeeder=false;
    }

    public synchronized boolean GetIsGrossFeeder(){
        return isGrossFeeder;
    }

    //big billy
    public synchronized BouncingBall FindBigBilly(){
        for(BouncingBall ball:balls){
            if(ball.GetName().equals("Big Billy")){
                return ball;
            }
        }
        return null;
    }


    //stopSmallBalls
    public void IsStopSmallBallsOn(){
        isStopSmallBalls=true;
    }
    public void IsStopSmallBallsOff(){
        isStopSmallBalls=false;
    }
    public synchronized boolean GetIsStopSmallBalls(){
        return isStopSmallBalls;
    }

    //stopSlowBalls
    public void IsStopSlowBallsOn(){
        isStopSlowBalls=true;
    }
    public void IsStopSlowBallsOff(){
        isStopSlowBalls=false;
    }
    public synchronized boolean GetIsStopSlowBalls(){
        return isStopSlowBalls;
    }



}
