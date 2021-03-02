import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;

public class MainFrame extends JFrame {
    private int width = 500;
    private int height = 450;
    private int time=0;

    private JMenuBar menu = new JMenuBar();
    private JMenu ballsMenu = new JMenu("Мячи");
    private JMenu controlMenu = new JMenu("Управление");

    private Field field=new Field();

    private JMenuItem newBallMenuItem;

    private JMenuItem frictionMenuItem;
    private JMenuItem sandPaperCountMenuItem;
    private JMenuItem snowBallCountMenuItem;

    private JCheckBoxMenuItem checkfrictionMenuItem;
    private JCheckBoxMenuItem magnettoMenuItem;
    private JCheckBoxMenuItem sandPaperMenuItem;
    private JCheckBoxMenuItem snowBallMenuItem;
    private JCheckBoxMenuItem weAreTeamMenuItem;
    private JCheckBoxMenuItem charismaMenuItem;
    private JCheckBoxMenuItem grossFeederMenuItem;

    private JButton speedPlus = new JButton("-");
    private JButton speedMinus = new JButton("+");
    private JButton pause = new JButton("Pause");

    MainFrame() {
        field.setPreferredSize(new Dimension(width, height));
        setTitle("lAB 6");
        setJMenuBar(menu);
        menu.add(ballsMenu);
        menu.add(controlMenu);

        newBallMenuItem = ballsMenu.add(newBallMenuItemAction);

        frictionMenuItem=ballsMenu.add(frictionMenuItemAction);
        frictionMenuItem.setEnabled(false);

        sandPaperCountMenuItem=ballsMenu.add(sandPaperCountMenuItemAction);
        sandPaperCountMenuItem.setEnabled(false);

        snowBallCountMenuItem=ballsMenu.add(SnowBallCountMenuItemAction);
        snowBallCountMenuItem.setEnabled(false);

        checkfrictionMenuItem = new JCheckBoxMenuItem("Трение");
        controlMenu.add(checkfrictionMenuItem);

        magnettoMenuItem = new JCheckBoxMenuItem("Магнетизм");
        controlMenu.add(magnettoMenuItem);

        sandPaperMenuItem = new JCheckBoxMenuItem("Наждачка");
        controlMenu.add(sandPaperMenuItem);

        snowBallMenuItem = new JCheckBoxMenuItem("Снежный ком");
        controlMenu.add(snowBallMenuItem);

        weAreTeamMenuItem = new JCheckBoxMenuItem("Мы-Команда");
        controlMenu.add(weAreTeamMenuItem);

        charismaMenuItem = new JCheckBoxMenuItem("Харизма");
        controlMenu.add(charismaMenuItem);

        grossFeederMenuItem = new JCheckBoxMenuItem("Обжора");
        controlMenu.add(grossFeederMenuItem);

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(Box.createHorizontalStrut(width / 2));
        buttonBox.add(speedMinus);
        buttonBox.add(Box.createHorizontalStrut(5));
        buttonBox.add(speedPlus);
        buttonBox.add(Box.createHorizontalStrut(5));
        buttonBox.add(pause);

        checkfrictionMenuItem.addItemListener(new checkFrictionMenuItemListener());
        magnettoMenuItem.addItemListener(new magnettoMenuItemListener());
        sandPaperMenuItem.addItemListener(new sandPaperMenuItemListener());
        snowBallMenuItem.addItemListener(new snowBallMenuItemListener());
        weAreTeamMenuItem.addItemListener(new weAreTeamMenuItemListener());
        charismaMenuItem.addItemListener(new charismaMenuItemListener());
        grossFeederMenuItem.addItemListener(new grossFeederMenuItemListener());
        speedMinus.addActionListener(new SpeedMinusListener());
        speedPlus.addActionListener(new SpeedPlusListener());
        pause.addActionListener(new PauseListener());

        add(field, BorderLayout.CENTER);
        add(buttonBox, BorderLayout.SOUTH);
        pack();
    }

    Action newBallMenuItemAction = new AbstractAction("Добавить мяч") {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            field.AddBall();
            frictionMenuItem.setEnabled(true);
            sandPaperCountMenuItem.setEnabled(true);
            snowBallCountMenuItem.setEnabled(true);
        }
    };

    Action frictionMenuItemAction=new AbstractAction("Установить трение") {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String input;
            input = JOptionPane.showInputDialog(MainFrame.this,
                    "Введите значение трения", "Трение",
                    JOptionPane.QUESTION_MESSAGE);

            try{
            Double in=0.0;
            try{
            in=Double.parseDouble(input); }catch (NullPointerException e){
                    System.out.println("you aren't enter number");
            }
            field.SetFriction(in);
            }
            catch (NumberFormatException ex){
                System.out.println("wrong number");
            }

        }
    };

    Action sandPaperCountMenuItemAction=new AbstractAction("Наждачка") {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String input;
            input = JOptionPane.showInputDialog(MainFrame.this,
                    "Введите значение наждачки", "Наждачка",
                    JOptionPane.QUESTION_MESSAGE);

            try{
                Double in=0.0;
                try{
                in=Double.parseDouble(input);}catch (NullPointerException e){
                    System.out.println("you aren't enter number");
                }
                field.SetSandPaper(in);
            }
            catch (NumberFormatException ex){
                System.out.println("wrong number");
            }
        }
    };

    Action SnowBallCountMenuItemAction=new AbstractAction("Снежный ком") {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String input1;
            input1 = JOptionPane.showInputDialog(MainFrame.this,
                    "Введите значение кома X", "Снежный ком",
                    JOptionPane.QUESTION_MESSAGE);
            String input2;
            input2 = JOptionPane.showInputDialog(MainFrame.this,
                    "Введите значение кома Y", "Снежный ком",
                    JOptionPane.QUESTION_MESSAGE);
            try{
                Double in1=0.0;
                try{
                in1=Double.parseDouble(input1);}catch (NullPointerException e){
                    System.out.println("you aren't enter number");
                }
                field.SetSnowBallX(in1);

                Double in2=0.0;
                try{
                in2=Double.parseDouble(input2);
                }catch (NullPointerException e){
                    System.out.println("you aren't enter number");
                }
                field.SetSnowBallY(in2);
            }
            catch (NumberFormatException ex){
                System.out.println("wrong number");
            }
        }
    };

    class checkFrictionMenuItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            JCheckBoxMenuItem helper=(JCheckBoxMenuItem) itemEvent.getSource();
            if(helper.isSelected()){
                System.out.println("checkFriction on");
                field.FrictionOn();
            }
            else{
                System.out.println("checkFriction off");
                field.FrictionOff();
            }
        }
    }

     class magnettoMenuItemListener implements ItemListener {
         @Override
         public void itemStateChanged(ItemEvent itemEvent) {
             JCheckBoxMenuItem helper=(JCheckBoxMenuItem) itemEvent.getSource();
             if(helper.isSelected()){
                 System.out.println("magnetto on");
                 field.MagnetoOn();
             }
             else{
                 System.out.println("magnetto off");
                 field.MagnetoOff();
             }
         }
     }
    class sandPaperMenuItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            JCheckBoxMenuItem helper=(JCheckBoxMenuItem) itemEvent.getSource();
            if(helper.isSelected()){
                System.out.println("sandPaper on");
                field.SandPaperOn();
            }
            else{
                System.out.println("sandPaper off");
                field.SandPaperOff();
            }
        }
    }
    class snowBallMenuItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            JCheckBoxMenuItem helper=(JCheckBoxMenuItem) itemEvent.getSource();
            if(helper.isSelected()){
                System.out.println("snowBall on");
                field.SnowBallOn();
            }
            else{
                System.out.println("snowBall off");
                field.SnowBallOff();
            }
        }
    }
    class weAreTeamMenuItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            JCheckBoxMenuItem helper=(JCheckBoxMenuItem) itemEvent.getSource();
            if(helper.isSelected()){
                System.out.println("weAreTeam on");
            }
            else{
                System.out.println("weAreTeam off");
            }
        }
    }
    class charismaMenuItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            JCheckBoxMenuItem helper=(JCheckBoxMenuItem) itemEvent.getSource();
            if(helper.isSelected()){
                System.out.println("charisma on");
            }
            else{
                System.out.println("charisma off");
            }
        }
    }

    class grossFeederMenuItemListener implements ItemListener {
    @Override
     public void itemStateChanged(ItemEvent itemEvent) {
        JCheckBoxMenuItem helper=(JCheckBoxMenuItem) itemEvent.getSource();
        if(helper.isSelected()){
            System.out.println("grossFeeder on");
        }
        else{
            System.out.println("grossFeeder off");
        }
     }
    }

    class SpeedPlusListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ++time;
            field.SetTimeMachine(time);
        }
    }
    class SpeedMinusListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            --time;
            field.SetTimeMachine(time);
        }
    }
    class PauseListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(pause.getText().equals("Pause")){
            field.pause();
            pause.setText("Continue");
            }else{
                field.resume();
                pause.setText("Pause");
            }

        }
    }
}
