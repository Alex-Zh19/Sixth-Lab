import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainFrame extends JFrame {
    int width = 500;
    int height = 450;
    private JMenuBar menu = new JMenuBar();
    private JMenu ballsMenu = new JMenu("Мячи");
    private JMenu controlMenu = new JMenu("Управление");

    private JPanel field = new JPanel();

    private JMenuItem newBallMenuItem;

    private JMenuItem frictionMenuItem;

    private JCheckBoxMenuItem magnettoMenuItem;
    private JCheckBoxMenuItem sandPaperMenuItem;
    private JCheckBoxMenuItem snowBallMenuItem;
    private JCheckBoxMenuItem weAreTeamMenuItem;
    private JCheckBoxMenuItem charismaMenuItem;
    private JCheckBoxMenuItem grossFeederMenuItem;

    private JButton speedPlus = new JButton("-");
    private JButton speedMinus = new JButton("+");

    MainFrame() {
        field.setPreferredSize(new Dimension(width, height));
        setTitle("lAB 6");
        setJMenuBar(menu);
        menu.add(ballsMenu);
        menu.add(controlMenu);

        newBallMenuItem = ballsMenu.add(newBallMenuItemAction);
        frictionMenuItem=ballsMenu.add(frictionMenuItemAction);

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

        magnettoMenuItem.addItemListener(new magnettoMenuItemListener());
        sandPaperMenuItem.addItemListener(new sandPaperMenuItemListener());
        snowBallMenuItem.addItemListener(new snowBallMenuItemListener());
        weAreTeamMenuItem.addItemListener(new weAreTeamMenuItemListener());
        charismaMenuItem.addItemListener(new charismaMenuItemListener());
        grossFeederMenuItem.addItemListener(new grossFeederMenuItemListener());
        speedMinus.addActionListener(new SpeedMinusListener());
        speedPlus.addActionListener(new SpeedPlusListener());

        add(field, BorderLayout.CENTER);
        add(buttonBox, BorderLayout.SOUTH);
        pack();
    }

    Action newBallMenuItemAction = new AbstractAction("Добавить мяч") {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("add boll pressed");
        }
    };

    Action frictionMenuItemAction=new AbstractAction("Установить трение") {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String input;
            input = JOptionPane.showInputDialog(MainFrame.this,
                    "Введите значение трения", "Трение",
                    JOptionPane.QUESTION_MESSAGE);
            Integer in;
            try{
             in=Integer.parseInt(input);}
            catch (NumberFormatException ex){
               in=0;
               System.out.println("wrong number");
            }
               System.out.println(in);
        }
    };

     class magnettoMenuItemListener implements ItemListener {
         @Override
         public void itemStateChanged(ItemEvent itemEvent) {
             JCheckBoxMenuItem helper=(JCheckBoxMenuItem) itemEvent.getSource();
             if(helper.isSelected()){
                 System.out.println("magnetto on");
             }
             else{
                 System.out.println("magnetto off");
             }
         }
     }
    class sandPaperMenuItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            JCheckBoxMenuItem helper=(JCheckBoxMenuItem) itemEvent.getSource();
            if(helper.isSelected()){
                System.out.println("sandPaper on");
            }
            else{
                System.out.println("sandPaper off");
            }
        }
    }
    class snowBallMenuItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            JCheckBoxMenuItem helper=(JCheckBoxMenuItem) itemEvent.getSource();
            if(helper.isSelected()){
                System.out.println("snowBall on");
            }
            else{
                System.out.println("snowBall off");
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
            System.out.println("speedPlus button pressed");
        }
    }
    class SpeedMinusListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("speedMinus button pressed");
        }
    }
}
