import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIBackup extends JPanel implements ActionListener {

    private final int WIDTH = 600, HEIGHT = 600;
    private int intersectionCount = 0;

    private JFrame mainWindow;
    private JButton button;
    private JLabel numInterlabel;
    private JTextField numInterText;
    private Graph map;

    public GUIBackup() {
        // map = new Graph();

        mainWindow = new JFrame("Test window");
        mainWindow.setSize(WIDTH, HEIGHT);
        mainWindow.setResizable(false);
        mainWindow.setLocationRelativeTo(null);
        
        // drawIntersections(g);

        // paint(getGraphics());

        // if (intersectionCount > 0) {
        //     // drawIntersections();
        //     String[] listOfLocations = map.getAllLocations();
        //     int x = 10, y = 10, width = 40, heigh = 40;

        //     for (int i = 0; i < listOfLocations.length; i ++) {
        //         JButton tempButton = new JButton(listOfLocations[i]);
        //         tempButton.setBounds(x, y, width, heigh);
                
        //         mainWindow.add(tempButton);//new JButton(listOfLocations[i]).setBounds(x, y, width, heigh));

        //         x += 50;

        //         if (x == 500) x = 10; y += 50;
        
        //     }

        // }

        numInterlabel = new JLabel("Number of intersections: ");
        numInterlabel.setBounds(10, 500, 150, 30);
        mainWindow.add(numInterlabel);
        
        numInterText = new JTextField("");
        numInterText.setBounds(160, 500, 50, 30);
        mainWindow.add(numInterText);

        button = new JButton("Add");
        button.setBounds(230, 500, 80, 30);
        mainWindow.add(button);
        button.addActionListener(this);

        mainWindow.setLayout(null);
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void drawIntersections() {
        String[] listOfLocations = map.getAllLocations();
        int x = 10, y = 10, width = 40, heigh = 40;

        for (int i = 0; i < listOfLocations.length; i ++) {
            JButton tempButton = new JButton(listOfLocations[i]);
            tempButton.setBounds(x, y, width, heigh);
            
            mainWindow.add(tempButton);//new JButton(listOfLocations[i]).setBounds(x, y, width, heigh));

            x += 50;

            if (x >= 500) {
                x = 10;
                y += 50;
            }
        
        }

    }

    // public void paint(Graphics g) {
    //     g.drawRect(300, 300, 50, 50);

    // }

    // public void drawIntersections(Graphics g) {
    //     super.paint(g);
    //     Graphics2D g2d = (Graphics2D) g;

    //     g2d.drawRect(30, 50, 420, 120);

    //     g2d.draw(new Rectangle(30, 50, 420, 120));
        
    // }

    // @Override
    // public void paintComponent(Graphics g) {
    //     super.paintComponent(g);

    //     g.drawRect(20, 20, 100, 100);

    // }

    @Override
    public void actionPerformed(ActionEvent e) {
        // System.out.println(numInterText.getText());

        for (int i = 0; i < Integer.parseInt(numInterText.getText()); i ++) {
            // this.map.addVertex("Intersection " + this.intersectionCount);

            this.intersectionCount ++;
        
        }

        for (String item : map.getAllLocations()) {
            System.out.print(item + " ");

        }

        System.out.println(this.intersectionCount);

        this.drawIntersections();
        
    }

    public static void main(String[] args) {
        // mainGame a = new mainGame();
        // a.startA1();
        new GUIBackup();
        
    }

    
    
}