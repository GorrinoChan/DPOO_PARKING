package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class AdminInfoSlots extends JFrame {
    private JButton returnButton;
    private JButton cancelButton, userProfileButton;
    private JLabel titleLabel;
    private int selectedRow;
    private JTable slotTable;
    private JScrollPane scrollPane;

    public void setSlotTable(JTable table) {
        this.slotTable = table;
    }
    public JButton getReturnButton() {
        return returnButton;
    }
    public int getSelectedRow() {
        return selectedRow;
    }
    public JButton getCancelButton() { return cancelButton; }

    public JButton getUserProfileButton() {
        return userProfileButton;
    }

    public AdminInfoSlots(int selectedRow) {
        this.selectedRow = selectedRow;  // Asignamos la fila seleccionada
        setTitle("Parking LS - AdminInfoSlots");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 0, 4, 0);

        returnButton = new JButton("<");
        returnButton.setBorderPainted(false);
        returnButton.setContentAreaFilled(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(returnButton, gbc);

        userProfileButton = new JButton("User");
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(4, 20, 4, 0);
        panel.add(userProfileButton, gbc);

        gbc.insets = new Insets(4, 0, 4, 0);
        titleLabel = new JLabel("PARKING LS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(titleLabel, gbc);

        cancelButton = new JButton("Cancel");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(cancelButton, gbc);

        c.add(panel, BorderLayout.NORTH);
        slotTable = new JTable();
        scrollPane = new JScrollPane(slotTable);
        c.add(scrollPane, BorderLayout.CENTER);
    }

}