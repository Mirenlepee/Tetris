package P1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana_Estadistica extends JFrame {
    private JLabel lblTitle;
    private JLabel lblTotalTime;
    private JLabel lblDailyTime;
    private JLabel lblGamesPlayed;
    private JLabel lblMaxPoints;
    private JLabel lblMinPoints;
    private JLabel lblTotalPoints;
    private JLabel lblDailyAverage;
    private JButton btnBack;

    public Ventana_Estadistica() {
        setSize(600, 800);
        setTitle("Estadística");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 2));

        lblTitle = new JLabel("Estadísticas");

        lblTitle.setFont(new Font("Cambria", Font.BOLD, 24));
        lblTotalTime = new JLabel("Total Time: ");
        lblDailyTime = new JLabel("Daily Time: ");
        lblGamesPlayed = new JLabel("Games Played: ");
        lblMaxPoints = new JLabel("Max Points: ");
        lblMinPoints = new JLabel("Min Points: ");
        lblTotalPoints = new JLabel("Total Points: ");
        lblDailyAverage = new JLabel("Daily Average: ");
        btnBack = new JButton("Back");

      
        JTextField txtTotalTime = new JTextField();
        txtTotalTime.setEditable(false);

        JTextField txtDailyTime = new JTextField();
        txtDailyTime.setEditable(false);

        JTextField txtGamesPlayed = new JTextField();
        txtGamesPlayed.setEditable(false);

        JTextField txtMaxPoints = new JTextField();
        txtMaxPoints.setEditable(false);

        JTextField txtMinPoints = new JTextField();
        txtMinPoints.setEditable(false);

        JTextField txtTotalPoints = new JTextField();
        txtTotalPoints.setEditable(false);

        JTextField txtDailyAverage = new JTextField();
        txtDailyAverage.setEditable(false);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(lblTitle);
        add(new JLabel());
        add(lblTotalTime);
        add(txtTotalTime);
        add(lblDailyTime);
        add(txtDailyTime);
        add(lblGamesPlayed);
        add(txtGamesPlayed);
        add(lblMaxPoints);
        add(txtMaxPoints);
        add(lblMinPoints);
        add(txtMinPoints);
        add(lblTotalPoints);
        add(txtTotalPoints);
        add(lblDailyAverage);
        add(txtDailyAverage);
        add(btnBack);
        
        txtTotalTime.setText("Texto de Prueba");
        
        setVisible(true);
    }
}
