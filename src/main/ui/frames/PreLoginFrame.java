package ui.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreLoginFrame extends FrameTemplate implements ActionListener {

    JButton loginButton;
    JButton signUpButton;
    JButton loadButton;

    public PreLoginFrame() {
        super("Welcome!");
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xfafafa));
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        this.add(panel);

        JLabel title = new JLabel();
        title.setText("Hi!");
        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);
        loginButton = new JButton("Log In");
        loginButton.addActionListener(this);
        signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(this);

        // temporary button to load from file -> menu item?
        loadButton = new JButton("Load from File");
        loadButton.addActionListener(this);

        // first go grid then go elements?
        panel.add(title);
        panel.add(loginButton);
        panel.add(signUpButton);
        panel.add(loadButton);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            System.out.println("Opening Login!");
            new LoginFrame();
            this.dispose();
        } else if (e.getSource() == signUpButton) {
            System.out.println("Opening Signup!");
            new SignupFrame();
            this.dispose();
        } else if (e.getSource() == loadButton) {
            System.out.println("Loading Data!");
        }
    }
}