package com.bear.reseeding.test.File;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TiffResizer2 {

    public static void resizeTiffs(String inputPath, String outputPath, int newWidth, int newHeight) {
        try {
            // 读取原始 TIFF 文件
            BufferedImage originalImage = ImageIO.read(new File(inputPath));
            // 获取原始图像的宽度和高度
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();

            // 计算缩放比例来调整图像大小 如果缩放比例小于10倍，则使用双线性插值 (Image.SCALE_SMOOTH) 进行缩放 否则使用最近邻插值 (Image.SCALE_FAST)
            double scale = Math.min(newWidth / (double) originalWidth, newHeight / (double) originalHeight);
            if (scale < 0.1) {
                scale = 0.1;
            }
            int scaledWidth = (int) (originalWidth * scale);
            int scaledHeight = (int) (originalHeight * scale);

            // 调整图像大小
            Image originalImages = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);




            // 创建一个新的支持透明度的 BufferedImage 来存储调整后的图像
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

            // 获取图形上下文
            Graphics2D g2d = resizedImage.createGraphics();
            // 将背景设置为透明
            g2d.setComposite(AlphaComposite.Clear);
            g2d.fillRect(0, 0, newWidth, newHeight);
            g2d.setComposite(AlphaComposite.SrcOver);
            // 绘制缩放后的图像
            g2d.drawImage(originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
            g2d.dispose();

            // 使用 LZW 或其他压缩算法写入 TIFF 文件
            // 注意：ImageIO 默认可能不支持 TIFF 的 LZW 压缩，这可能需要额外的库或自定义处理
            // 这里我们仅使用 ImageIO 的默认行为，它可能会使用 JPEG 或其他压缩
            ImageIO.write(resizedImage, "TIFF", new File(outputPath));

            System.out.println("TIFF file resized and saved to: " + outputPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String inputPath = "F:\\GeoServerData\\loveFact.tif";
        String outputPath = "F:\\GeoServerData\\loveFactoutput.tif";
        int newWidth = 1075; // 根据需要调整
        int newHeight = 927; // 根据需要调整，或者根据原始宽高比计算

        resizeTiffs(inputPath, outputPath, newWidth, newHeight);
    }
}
