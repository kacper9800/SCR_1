package pl.java.synch_problems;

import javax.swing.*;

public class View {


    public View() {
        JFrame jFrame = this.createFrame();
        jFrame.add(this.createButton("Button", 130, 100, 100, 40));
        jFrame.setSize(400, 500);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }

    public JFrame createFrame() {
        JFrame jFrame = new JFrame();
        return new JFrame();
    }

    public JButton createButton(String label, Integer x, Integer y, Integer width, Integer height) {
        JButton button = new JButton(label);
        button.setBounds(x, y, width, height);
        return button;
    }
}
