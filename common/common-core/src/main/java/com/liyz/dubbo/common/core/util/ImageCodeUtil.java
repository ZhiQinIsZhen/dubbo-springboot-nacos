package com.liyz.dubbo.common.core.util;

import lombok.experimental.UtilityClass;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 注释:图片验证码工具类
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/4/27 16:30
 */
@UtilityClass
public class ImageCodeUtil {

    public static final String DEFAULT_HEADER_TOKEN = "imageToken";
    public static final String DEFAULT_CONTENT_TYPE = "image/jpg";
    public static final int DEFAULT_WIGHT = 100;
    public static final int DEFAULT_HIGH = 40;

    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    /**
     * 生成随机验证码文件,并返回验证码值
     *
     * @param w
     * @param h
     * @param outputFile
     * @param imageCode
     * @return
     * @throws IOException
     */
    public static String outputVerifyImage(int w, int h, File outputFile, String imageCode) throws IOException {
        outputImage(w, h, outputFile, imageCode);
        return imageCode;
    }

    /**
     * 输出随机验证码图片流,并返回验证码值
     *
     * @param w
     * @param h
     * @param os
     * @param imageCode
     * @return
     * @throws IOException
     */
    public static String outputVerifyImage(int w, int h, OutputStream os, String imageCode) throws IOException {
        outputImage(w, h, os, imageCode);
        return imageCode;
    }

    /**
     * 生成指定验证码图像文件
     *
     * @param w
     * @param h
     * @param outputFile
     * @param code
     * @throws IOException
     */
    public static void outputImage(int w, int h, File outputFile, String code) throws IOException {
        if (outputFile == null) {
            return;
        }
        File dir = outputFile.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            outputFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(outputFile);
            outputImage(w, h, fos, code);
            fos.close();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 输出指定验证码图片流
     *
     * @param w
     * @param h
     * @param os
     * @param code
     * @throws IOException
     */
    public static void outputImage(int w, int h, OutputStream os, String code) throws IOException {
        int verifySize = code.length();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Random rand = new Random();
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color[] colors = new Color[5];
        Color[] colorSpaces = new Color[]{Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW};
        float[] fractions = new float[colors.length];

        for(int i = 0; i < colors.length; ++i) {
            colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
            fractions[i] = rand.nextFloat();
        }

        Arrays.sort(fractions);
        g2.setColor(Color.GRAY);
        g2.fillRect(0, 0, w, h);
        Color c = getRandColor(200, 250);
        g2.setColor(c);
        g2.fillRect(0, 2, w, h - 4);
        Random random = new Random();
        g2.setColor(getRandColor(160, 200));

        int area;
        int fontSize;
        int x;
        int y;
        for(int i = 0; i < 20; ++i) {
            area = random.nextInt(w - 1);
            fontSize = random.nextInt(h - 1);
            x = random.nextInt(6) + 1;
            y = random.nextInt(12) + 1;
            g2.drawLine(area, fontSize, area + x + 40, fontSize + y + 20);
        }

        float yawpRate = 0.05F;
        area = (int)(yawpRate * (float)w * (float)h);

        int i;
        for(fontSize = 0; fontSize < area; ++fontSize) {
            x = random.nextInt(w);
            y = random.nextInt(h);
            i = getRandomIntColor();
            image.setRGB(x, y, i);
        }

        shear(g2, w, h, c);
        g2.setColor(getRandColor(100, 160));
        fontSize = h - 4;
        Font font = new Font("Algerian", 2, fontSize);
        g2.setFont(font);
        char[] chars = code.toCharArray();

        for(i = 0; i < verifySize; ++i) {
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(0.7853981633974483D * rand.nextDouble() * (double)(rand.nextBoolean() ? 1 : -1), (double)(w / verifySize * i + fontSize / 2), (double)(h / 2));
            g2.setTransform(affine);
            g2.drawChars(chars, i, 1, (w - 10) / verifySize * i + 5, h / 2 + fontSize / 2 - 10);
        }

        g2.dispose();
        ImageIO.write(image, "jpg", os);
    }

    private static Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    private static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    private static void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private static void shearX(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1) * Math
                    .sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    private static void shearY(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1) * Math
                    .sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }

    }
}
