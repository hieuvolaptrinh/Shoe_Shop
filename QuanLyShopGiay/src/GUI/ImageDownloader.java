/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class ImageDownloader extends JFrame {

    public ImageDownloader() {
        setTitle("Image Downloader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JButton chooseButton = new JButton("Choose Image");
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose Image File");
                int returnValue = fileChooser.showOpenDialog(ImageDownloader.this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    downloadImage(selectedFile);
                }
            }
        });

        getContentPane().add(chooseButton);
    }

    private void downloadImage(File file) {
        try {
            byte[] imageData = Files.readAllBytes(file.toPath());
            String fileName = file.getName();
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            File directory = new File("D:\\OneDrive - University of Technology and Education\\Desktop\\QuanLyShopGiay\\Images\\Shoe image\\Air Jordan Legacy 312 Low\\Red Blue Green Yellow");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String savePath = "D:\\OneDrive - University of Technology and Education\\Desktop\\QuanLyShopGiay\\Images\\Shoe image\\Air Jordan Legacy 312 Low\\Red Blue Green Yellow\\nhap.png";

            Files.write(new File(savePath).toPath(), imageData);

            JOptionPane.showMessageDialog(this, "Image downloaded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error downloading image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ImageDownloader().setVisible(true);
            }
        });
    }
}
