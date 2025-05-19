package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*************************
 * Clase privada, para hacer cambios en la clase JPanel ya que no se permite librerias externas.
 * El atributo data, se obtiene desde fuera y equivale a las barras del grafico
 * BAR_WIDTH y PADDING son constantes que se requieren para no colocar el grafico en alguna esquina o
 * tocando el limite del JFrame
 * **********************/
public class GraphPanel extends JPanel {
    private ArrayList<Integer> data = new ArrayList<>();
    private static final int BAR_WIDTH = 12; //1 number has a width of 6, so 2 has a width of 12
    private static final int SPACE_WIDTH = 4;
    private static final int PADDING = 50;

    private static final int WIDTH_PADDING = 50;

    public GraphPanel() {}

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
        int width = getWidth() - WIDTH_PADDING;
        int height = getHeight();
        g2.setColor(Color.BLACK);
        g2.drawLine(PADDING, height - PADDING, width, height - PADDING); // X-axis
        g2.drawLine(PADDING, PADDING - g2.getFontMetrics().getHeight(), PADDING, height - PADDING); // Y-axis

        StringBuilder sb1 = new StringBuilder();
        for (int i = 1; i <= 36; i++) {
            if(i < 10) {
                sb1.append("0" + (i) + " ");
            }
            else{
                sb1.append((i) + " ");
            }
        }

        String str = sb1.toString();
        g2.drawString(str, PADDING, height - PADDING + 10);
        StringBuilder sb2 = new StringBuilder();
        for (int i = 20; i >= 1; i= i -1) {
            if(i < 10) {
                sb2.append("0"+(i) + " ");
            }
            else{
                sb2.append((i) + " ");
            }
        }
        String str2 = sb2.toString();
        String[] tokens = str2.split(" ");      // ["00","01",…,"36",""] (last empty from trailing space)
        int x = PADDING - 15;      // e.g. just to the right of your graph
        int yStart = PADDING - g2.getFontMetrics().getHeight() + 10;// top margin
        int lineHeight = g2.getFontMetrics().getHeight();

        for (int k = 0; k < tokens.length; k++) {
            String tok = tokens[k];
            if (tok.isEmpty()) continue;        // skip trailing empty token
            int y = yStart + k * lineHeight;
            g2.drawString(tok, x, y);
        }
        for (int i = 0; i < data.size(); i++) {
            x = PADDING + i * (BAR_WIDTH+ SPACE_WIDTH);

            int barHeight = data.get(i) * 10;
            int y = height - PADDING - barHeight;
            g2.setColor(Color.BLUE);
            g2.fillRect(x, y, BAR_WIDTH, barHeight); // Bar
            g2.setColor(Color.WHITE);
            g2.drawRect(x+BAR_WIDTH, y, SPACE_WIDTH, barHeight);
        }
    }
}

