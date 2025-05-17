package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphView extends JFrame {
    private JButton returnButton, userProfileButton;
    private JLabel titleLabel;
    private GraphPanel graphPanel = new GraphPanel();

    public JButton getReturnButton() {
        return returnButton;
    }

    public JButton getUserProfileButton() {
        return userProfileButton;
    }

    public GraphView() {
        setTitle("Parking LS - Graph");
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
        c.add(panel, BorderLayout.CENTER);

        graphPanel.setPreferredSize(new Dimension(400, 300));
        c.add(graphPanel, BorderLayout.PAGE_END);

    }
    public void refreshGraph() {
        graphPanel.repaint();
    }


    public void updateData(ArrayList<Integer> data){
        graphPanel.setData(data);
    }

    /*************************
     * Clase privada, para hacer cambios en la clase JPanel ya que no se permite librerias externas.
     * El atributo data, se obtiene desde fuera y equivale a las barras del grafico
     * BAR_WIDTH y PADDING son constantes que se requieren para no colocar el grafico en alguna esquina o
     * tocando el limite del JFrame
    * **********************/
    private class GraphPanel extends JPanel {
        private ArrayList<Integer> data = new ArrayList<>();
        private static final int BAR_WIDTH = 5;
        private static final int PADDING = 50;
        private static final int MAX_SIZE = 100;

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            if(data != null) {
                drawBarGraph(g);
            }
        }
        public void setData(ArrayList<Integer> data){
            this.data = data;
        }

        public void drawBarGraph(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = 400;
            int height = 300;
            int graphHeight = height - 2 * PADDING;
            int graphWidth = width - 2 * PADDING;

            g2.setColor(Color.BLACK);
            g2.drawLine(PADDING, height - PADDING, width - PADDING, height - PADDING); // X-axis
            g2.drawLine(PADDING, PADDING, PADDING, height - PADDING);
            /* //Esto es para hacer los numeros de abajo, por ahora no se como.
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= data.size(); i++) {
                sb.append(i);
            }
            String str = sb.toString();
            g2.drawString(str, PADDING, height - PADDING + 10);
             */
            for (int i = 0; i < data.size(); i++) {
                int x = PADDING + i * BAR_WIDTH;

                int barHeight = data.get(i) * 10;
                int y = height - PADDING - barHeight;

                g2.setColor(Color.BLUE);
                g2.fillRect(x, y, BAR_WIDTH, barHeight); // Bar
            }
        }
    }



}