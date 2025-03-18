/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.File;
import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel imgLabel = new JLabel();

        // Đường dẫn đến tệp ảnh
        String imagePath = "D:\\Dowload\\HÌNH2.png";

        try {
            File file = new File(imagePath);
            String imageUrl = file.toURI().toURL().toString();

            // Tạo một ImageIcon từ URL
            ImageIcon imageIcon = new ImageIcon(imageUrl);

            // Thiết lập ImageIcon cho JLabel
            imgLabel.setIcon(imageIcon);

            // Thêm JLabel vào JPanel
            panel.add(imgLabel);

            // Thêm JPanel vào JFrame
            frame.add(panel);

            // Cấu hình JFrame và hiển thị nó
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
