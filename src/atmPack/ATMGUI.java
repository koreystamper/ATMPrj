package atmPack;

import javax.swing.*;

public class ATMGUI {

    public static void main(String arg[]){
        JMenu fileMenu;
        JMenuItem quitItem;
        JCheckBoxMenuItem suspendItem;
        JMenuBar menus;

        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        suspendItem = new JCheckBoxMenuItem ("Suspend");
        fileMenu.add(suspendItem);
        fileMenu.add(quitItem);
        menus = new JMenuBar();

        menus.add(fileMenu);

        JFrame gui = new JFrame();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("My 3 ATM");
        gui.setSize(600,400);
        JPanel panel = new JPanel();
        panel.add(new ATMPanelMain(quitItem, suspendItem));
        gui.add(panel);
        gui.pack();
        gui.setVisible(true);
    }
}
