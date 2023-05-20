package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainWindow extends JFrame {
    private JButton btnLogin;
    private JButton btnExit;
    private JLabel lblWelcome;
    private JPanel panel;

    public MainWindow() {
        super("OpenResearch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);

        panel = new JPanel();
        panel.setLayout(null);

        lblWelcome = new JLabel("Welcome to OpenResearch");
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblWelcome);

        btnLogin = new JButton("Log In");
        panel.add(btnLogin);

        btnExit = new JButton("Exit");
        panel.add(btnExit);

        add(panel);
        setLocationRelativeTo(null); // Center the window

        // Add a component listener to handle frame resizing
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateComponentPositions();
            }
        });

        setVisible(true);
        updateComponentPositions();
    }

    private void updateComponentPositions() {
        int frameWidth = getWidth();
        int frameHeight = getHeight();

        lblWelcome.setBounds(0, frameHeight / 2 - 50, frameWidth, 30);

        int buttonWidth = 100;
        int buttonHeight = 50;
        int buttonSpacing = 10;
        int buttonsX = (frameWidth - buttonWidth * 2 - buttonSpacing) / 2;
        int buttonsY = frameHeight / 2;

        btnLogin.setBounds(buttonsX, buttonsY, buttonWidth, buttonHeight);
        btnExit.setBounds(buttonsX + buttonWidth + buttonSpacing, buttonsY, buttonWidth, buttonHeight);
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnExit() {
        return btnExit;
    }
}