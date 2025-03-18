/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.Swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 *
 * @author anreal
 */
public class roundJtexbox extends JTextField{

    public roundJtexbox() {
        setBorder(BorderFactory.createEmptyBorder());
    }

    
   @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Vẽ hình chữ nhật bo tròn với góc bo tròn 15 pixel
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, width - 1, height - 1, 15, 15);

        // Giải phóng đối tượng Graphics2D
        g2d.dispose();
    }
}
