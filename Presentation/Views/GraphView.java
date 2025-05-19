package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
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

        graphPanel.setPreferredSize(new Dimension(650, 400));
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
        private static final int BAR_WIDTH = 14;
        private static final int PADDING = 50;
        private static final int MAX_SIZE = 100;
        private static final String Xaxis = "00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37";
        private static final String Yaxis = "30 29 28 27 26 25 24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 09 08 07 06 05 04 03 02 01 00";
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
            Graphics2D g3 = (Graphics2D) g;
            int width = 650;
            int height = 400;
            int graphHeight = height - 2 * PADDING;
            int graphWidth = width - 2 * PADDING;

            g2.setColor(Color.BLACK);
            g2.drawLine(PADDING, height - PADDING, width, height - PADDING); // X-axis
            g2.drawLine(PADDING, PADDING, PADDING, height - PADDING);
            //Esto es para hacer los numeros de abajo, por ahora no se como.
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            int j = 30;
            for (int i = 1; i <= 36; i++) {
                j = 30 - i;
                if(i < 10) {
                    sb1.append("0"+(i) + " ");
                }
                else{
                    sb1.append((i) + " ");
                }
                if(j < 10 && j >= 0 ){
                    sb2.append("0"+(i) + " ");
                }
                else if (j >= 10){
                    sb2.append((i) + " ");
                }

            }

            String str = sb1.toString();
            String str2 = sb2.toString();

            g2.drawString(str, PADDING, height - PADDING + 10);

            AffineTransform at = AffineTransform.getQuadrantRotateInstance(3);
            g2.setTransform(at);
            g2.drawString(str2,-PADDING ,PADDING);
            AffineTransform at = AffineTransform.getQuadrantRotateInstance(1);
            g2.setTransform(at);
            for (int i = 0; i < data.size(); i++) {
                int x = PADDING + i * BAR_WIDTH;

                int barHeight = data.get(i) * 10;
                int y = height - PADDING - barHeight;

                g2.setColor(Color.BLUE);
                g2.fillRect(x, y, BAR_WIDTH, barHeight); // Bar
                g2.setColor(Color.WHITE);
                g2.drawRect(x+1, y+1, 1, barHeight);
            }
        }
    }



}