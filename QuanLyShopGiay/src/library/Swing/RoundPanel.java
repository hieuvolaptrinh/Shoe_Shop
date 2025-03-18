package library.Swing;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class RoundPanel extends JPanel {

    public String color1, color2;

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public RoundPanel() {
        setOpaque(false);
    }

    public void setBackground(String color1, String color2) {
        this.color1 = color1;
        this.color2 = color2;
    }
    
    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (color1 != null && color2 != null) {
            GradientPaint gp = new GradientPaint(0, 0, Color.decode(color1), this.getWidth(), this.getHeight(), Color.decode(color2));
            g2.setPaint(gp);
        }else{
            g2.setColor(getBackground());
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.dispose();
//        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        super.paint(grphcs);
    }
}
