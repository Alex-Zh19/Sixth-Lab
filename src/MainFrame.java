import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    int width=500;
    int height=450;
    private JMenuBar menu=new JMenuBar();
    private JMenu ballsMenu=new JMenu("Мячи");
    private JMenu controlMenu=new JMenu("Управление");

    private JPanel field=new JPanel();

    private JMenuItem newBallMenuItem;

    private JCheckBoxMenuItem magnettoMenuItem;
    private JCheckBoxMenuItem sandPaperMenuItem;
    private JCheckBoxMenuItem snowBallMenuItem;
    private JCheckBoxMenuItem weAreTeamMenuItem;
    private JCheckBoxMenuItem charismaMenuItem;
    private JCheckBoxMenuItem grossFeederMenuItem;

    private JButton speedPlus=new JButton("-");
    private JButton speedMinus=new JButton("+");

    MainFrame(){
        field.setPreferredSize(new Dimension(width,height));
        setTitle("lAB 6");
        setJMenuBar(menu);
        menu.add(ballsMenu);
        menu.add(controlMenu);

        newBallMenuItem = ballsMenu.add(newBallMenuItemAction);

        magnettoMenuItem=new JCheckBoxMenuItem("Магнетизм");
        controlMenu.add(magnettoMenuItem);

        sandPaperMenuItem=new JCheckBoxMenuItem("Наждачка");
        controlMenu.add(sandPaperMenuItem);

        snowBallMenuItem=new JCheckBoxMenuItem("Снежный ком");
        controlMenu.add(snowBallMenuItem);

        weAreTeamMenuItem=new JCheckBoxMenuItem("Мы-Команда");
        controlMenu.add(weAreTeamMenuItem);

        charismaMenuItem=new JCheckBoxMenuItem("Харизма");
        controlMenu.add(charismaMenuItem);

        grossFeederMenuItem=new JCheckBoxMenuItem("Обжора");
        controlMenu.add(grossFeederMenuItem);

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(Box.createHorizontalStrut(width/2));
        buttonBox.add(speedMinus);
        buttonBox.add(Box.createHorizontalStrut(5));
        buttonBox.add(speedPlus);

        add(field,BorderLayout.CENTER);
        add(buttonBox,BorderLayout.SOUTH);
        pack();
    }

Action newBallMenuItemAction=new AbstractAction("Добавить мяч") {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("fucl");
    }
};


}
