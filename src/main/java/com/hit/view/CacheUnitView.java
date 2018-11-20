package main.java.com.hit.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CacheUnitView
        extends java.util.Observable
        implements View {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JPanel MessagesPanel;
    private JPanel controlPanel;
    private JLabel msglabel;
    private String str;
    private   JLabel l1,l2,l3,l4,l5;

    public CacheUnitView() {
        str = null;
        l1 = new JLabel("");
        l2 = new JLabel("");
        l3 = new JLabel("");
        l4 = new JLabel("");
        l5 = new JLabel("");
        l1.setFont(new Font("Calibri", Font.PLAIN, 20));
        l2.setFont(new Font("Calibri", Font.PLAIN, 20));
        l3.setFont(new Font("Calibri", Font.PLAIN, 20));
        l4.setFont(new Font("Calibri", Font.PLAIN, 20));
        l5.setFont(new Font("Calibri", Font.PLAIN, 20));
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    createAndShowGUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                showGridLayout();

            }
        });
    }

    @Override
    public <T> void updateUIData(T t) {
        str = (String) t;
    }

    private void createAndShowGUI() throws IOException {

        mainFrame = new JFrame("CacheUnit project, by Hadas and Eilon");
        mainFrame.setSize(400,550);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.getContentPane().setBackground(Color.white );
        mainFrame.setIconImage( new ImageIcon("lib\\img\\icon.png").getImage());
        mainFrame.addWindowListener(new WindowAdapter() { //close the socket when exitting the program
            public void windowClosing(WindowEvent windowEvent){
                setChanged();
                notifyObservers("disconnect");
                System.exit(0);
            }
        });

        headerLabel = new JLabel("",JLabel.CENTER );
        headerLabel.setIcon(new ImageIcon("lib\\img\\1.jpg"));


        controlPanel = new JPanel();
        controlPanel.setBackground(Color.white);
        controlPanel.setLayout(new FlowLayout());

        MessagesPanel = new JPanel();
        MessagesPanel.setBackground(Color.white);
        MessagesPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(MessagesPanel);
        mainFrame.setVisible(true);
    }

    private void showGridLayout(){

        //for message panel
        JPanel msgPanel = new JPanel();
        msgPanel.setBackground(Color.white);
        GridLayout msgLayout = new GridLayout(5,0);
        msgPanel.setLayout(msgLayout);
        msgPanel.add(l1);
        msgPanel.add(l2);
        msgPanel.add(l3);
        msgPanel.add(l4);
        msgPanel.add(l5);

        //for the buttons panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        GridLayout layout = new GridLayout(0,2);
        layout.setHgap(10);
        layout.setVgap(10);
        panel.setLayout(layout);
        BufferedImage buttonIcon1 = null;
        BufferedImage buttonIcon2 = null;
        try {
            buttonIcon1 = ImageIO.read(new File("lib\\img\\file.jpg"));
            buttonIcon2 = ImageIO.read(new File("lib\\img\\statics.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JButton button1 = new JButton(new ImageIcon(buttonIcon1));
        JButton button2 = new JButton(new ImageIcon(buttonIcon2));
        button1.setOpaque(false);
        button1.setContentAreaFilled(false);
        button1.setBorderPainted(false);
        button2.setOpaque(false);
        button2.setContentAreaFilled(false);
        button2.setBorderPainted(false);


        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser j = new JFileChooser("lib\\Requests\\");
                int returnValue = j.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = j.getSelectedFile();
                    setChanged();
                    notifyObservers(selectedFile);
                  l1.setText(str);
                    l2.setText("");
                    l3.setText("");
                    l4.setText("");
                    l5.setText("");
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers("statics");
                String [] strArray =  str.replaceAll("_"," ").split("\n");
                l1.setText(strArray[0]);
                l2.setText(strArray[1]);
                l3.setText(strArray[2]);
                l4.setText(strArray[3]);
                l5.setText(strArray[4]);

            }
        });

        panel.add(button1);
        panel.add(button2);


        controlPanel.add(panel);
        MessagesPanel.add(msgPanel);
        mainFrame.setVisible(true);
    }
}
