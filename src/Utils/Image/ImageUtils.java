/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Image;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageUtils {
        public static void convertLabelImageToGrayscale(JLabel label) {
        if (label.getIcon() instanceof ImageIcon) {
            ImageIcon icon = (ImageIcon) label.getIcon();
            Image originalImage = icon.getImage();

            // Convert Image to BufferedImage
            BufferedImage buffered = new BufferedImage(
                originalImage.getWidth(null),
                originalImage.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
            );

            Graphics g = buffered.getGraphics();
            g.drawImage(originalImage, 0, 0, null);
            g.dispose();

            // Convert to grayscale
            BufferedImage grayscale = new BufferedImage(
                buffered.getWidth(),
                buffered.getHeight(),
                BufferedImage.TYPE_BYTE_GRAY
            );

            Graphics2D g2d = grayscale.createGraphics();
            g2d.drawImage(buffered, 0, 0, null);
            g2d.dispose();

            // Set new grayscale image to label
            label.setIcon(new ImageIcon(grayscale));
        }
    }

}
