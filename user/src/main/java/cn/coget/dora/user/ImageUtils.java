package cn.coget.dora.user;

import com.google.common.collect.Lists;
import com.sun.org.apache.regexp.internal.RE;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

/**
 * 图片工具
 * <p>
 * author: sin
 * time: 2020/7/10 11:15 下午
 */
public class ImageUtils {

    private static final int[] rgb1 = new int[]{0, 0, 0};
    private static final int[] rgb2 = new int[]{240, 240, 240};

    public static void main(String[] args) throws IOException {

        InputStream inputStream1 = new FileInputStream("/Users/sin/github/dora/user/src/main/resources/tmp/1594435053119.jpg");
//        InputStream inputStream2 = new FileInputStream("/Users/sin/github/dora/user/src/main/resources/img2/1594434786674.jpg");

//        float res = compare(getData(ImageIO.read(inputStream1)), getData(ImageIO.read(inputStream2)));
//        System.err.println(res);
        BufferedImage bufferedImage = ImageIO.read(inputStream1);
        ImageUtils imageUtils = new ImageUtils();
//        int size = imageUtils.start(bufferedImage, 0);
//        System.err.println("旋转 " + size);
        imageUtils.start2(bufferedImage);
    }

    public int start(BufferedImage bufferedImage, int index) throws IOException {
        System.err.println("开始查找: " + index);

        int[] rgb1 = new int[]{0, 0, 0};
        int[] rgb2 = new int[]{240, 240, 240};

//        int x = bufferedImage.getWidth();
//        int y = bufferedImage.getHeight();

//        String tmpPath = "/Users/sin/github/dora/user/src/main/resources/tmp/";
//        String tmp = tmpPath + File.separator + System.currentTimeMillis() + ".png";
//        ImageIO.write(this.Rotate(bufferedImage, 90), "png", new File(tmp));

        // x y
//        int xFindCount = this.f1(bufferedImage, rgb1, rgb2);
//        int yFindCount = this.f2(bufferedImage, rgb1, rgb2);
//        System.err.println("xFindCount:"  + xFindCount);
//        System.err.println("yFindCount:"  + yFindCount);
//
//
//        if (index != 0 && xFindCount > yFindCount) {
//            System.err.println("可以尝试点击!");
//            return index;
//        } else {
//            // 旋转
//            System.err.println("开始旋转!");
//            bufferedImage = this.Rotate(bufferedImage, 45);
//            String tmpPath = "/Users/sin/github/dora/user/src/main/resources/tmp/";
//            String tmp = tmpPath + File.separator + System.currentTimeMillis() + ".png";
//            ImageIO.write(bufferedImage, "png", new File(tmp));
//
//            return this.start(bufferedImage, ++index);
//        }
        return 1;
    }


    public boolean start2(BufferedImage bufferedImage) throws IOException {
        int[] rgb1 = new int[]{0, 0, 0};
        int[] rgb2 = new int[]{240, 240, 240};

        // x y
        int[] xFindCount = this.f1(bufferedImage, rgb1, rgb2);
        int[] yFindCount = this.f2(bufferedImage, rgb1, rgb2);
        System.err.println("xFindCount:"  + xFindCount[0] + " " + xFindCount[1]);
        System.err.println("yFindCount:"  + yFindCount[0] + " " + yFindCount[1]);

        if (xFindCount[0] < yFindCount[0]) {
            if (xFindCount[1] < yFindCount[1]) {
                return true;
            } else {
                return false;
            }
        } else {
            if (xFindCount[0] > yFindCount[0] && xFindCount[1] < yFindCount[1]) {
                return true;
            } else {
                return false;
            }
        }
    }


    public int[] f1(BufferedImage bufferedImage, int[] rgb1, int[] rgb2) {
        int[] rgb = new int[3];
        int maxCount = 0;
        List<Integer[]> points2 = Lists.newArrayList();

        List<Integer> maxS = Lists.newArrayList();

        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            int findCount = 0;
            List<Integer[]> points = Lists.newArrayList();
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                // i,j位置的Color值
                int pixel = bufferedImage.getRGB(x, y);
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
//                System.err.println(rgb[0] + "," + rgb[1] + "," + rgb[2]);
                // 位置
                points.add(new Integer[]{x, y});
                // 匹配
                if (this.match(rgb, rgb1, rgb2)) {
                    findCount++;
                }
            }

            maxS.add(findCount);
            if (findCount >= maxCount) {
                maxCount = findCount;
                points2 = points;
            }
        }

        for (Integer[] integers : points2) {
            bufferedImage.setRGB(integers[0], integers[1], 2202060);
        }

        String tmpPath = "/Users/sin/github/dora/user/src/main/resources/tmp/";
        String tmp = tmpPath + File.separator + System.currentTimeMillis() + ".png";
//        try {
//            ImageIO.write(bufferedImage, "png", new File(tmp));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        int ss = 0;
        for (Integer max : maxS) {
            if (max >= maxCount - 30 && max <= maxCount) {
                ss++;
            }
        }
        System.err.println("ss " + ss);
        return new int[]{maxCount, ss};
    }

    public int[] f2(BufferedImage bufferedImage, int[] rgb1, int[] rgb2) {
        int[] rgb = new int[3];
        int maxCount = 0;
        List<Integer[]> points2 = Lists.newArrayList();

        List<Integer> maxS = Lists.newArrayList();
        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            int findCount = 0;
            List<Integer[]> points = Lists.newArrayList();
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                // i,j位置的Color值
                int pixel = bufferedImage.getRGB(x, y);
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
//                System.err.println(rgb[0] + "," + rgb[1] + "," + rgb[2]);
                // 位置
                points.add(new Integer[]{x, y});
                // 匹配
                if (this.match(rgb, rgb1, rgb2)) {
                    findCount++;
                }
            }

            maxS.add(findCount);

            if (findCount >= maxCount) {
                maxCount = findCount;
                points2 = points;
            }
        }

        for (Integer[] integers : points2) {
            bufferedImage.setRGB(integers[0], integers[1], 2202060);
        }

        String tmpPath = "/Users/sin/github/dora/user/src/main/resources/tmp/";
        String tmp = tmpPath + File.separator + System.currentTimeMillis() + ".png";
        try {
            ImageIO.write(bufferedImage, "png", new File(tmp));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int ss = 0;
        for (Integer max : maxS) {
            if (max >= maxCount - 30 && max <= maxCount) {
                ss++;
            }
        }
        System.err.println("ss " + ss);

        return new int[]{maxCount, ss};
    }


    /**
     * 对图片进行旋转
     *
     * @param src   被旋转图片
     * @param angel 旋转角度
     * @return 旋转后的图片
     */
    public BufferedImage Rotate(Image src, int angel) {
        int src_width = src.getWidth(null);
        int src_height = src.getHeight(null);
        // 计算旋转后图片的尺寸
        Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(
                src_width, src_height)), angel);
        BufferedImage res = null;
        res = new BufferedImage(rect_des.width, rect_des.height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = res.createGraphics();
        // 进行转换
        g2.translate((rect_des.width - src_width) / 2,
                (rect_des.height - src_height) / 2);
        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);

        g2.drawImage(src, null, null);
        return res;
    }

    /**
     * 计算旋转后的图片
     *
     * @param src   被旋转的图片
     * @param angel 旋转角度
     * @return 旋转后的图片
     */
    public static Rectangle CalcRotatedSize(Rectangle src, int angel) {
        // 如果旋转的角度大于90度做相应的转换
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }

        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);

        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new Rectangle(new Dimension(des_width, des_height));
    }



    public boolean startRotate(BufferedImage bufferedImage) {
        int x = bufferedImage.getWidth();
        int y = bufferedImage.getHeight();
        // x y
        int[] point1 = this.findColor(bufferedImage, x, y, rgb1, rgb2, 1);
        System.err.println(point1[0] + "," + point1[1]);
        // x y
        int[] point3 = this.findColorReverse2(bufferedImage, x, y, rgb1, rgb2, 1);
        System.err.println(point3[0] + "," + point3[1]);

        int a1 = point1[1];
        int a2 = y - point3[1];
        if (a1 > a2) {
            System.err.println("click");
            return true;
        }
        return false;
    }

    public int[] findColor(BufferedImage bufferedImage, int x1, int y1, int[] rgb1, int[] rgb2, int maxCount) {
        int[] rgb = new int[3];
        int[] point = new int[2];
        int findCount = 0;
        for (int y = 0; y < y1; y++) {
            for (int x = 0; x < x1; x++) {
                // i,j位置的Color值
                int pixel = bufferedImage.getRGB(x, y);
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
//                System.err.println(rgb[0] + "," + rgb[1] + "," + rgb[2]);
                // 位置
                point[0] = x;
                point[1] = y;
                // 匹配
                if (this.match(rgb, rgb1, rgb2)) {
                    findCount++;
                    if (findCount >= maxCount) {
                        return point;
                    }
                }
            }
        }
        return point;
    }

    public int[] findColorReverse2(BufferedImage bufferedImage, int x1, int y1, int[] rgb1, int[] rgb2, int maxCount) {
        int[] rgb = new int[3];
        int[] point = new int[2];
        int findCount = 0;
        for (int y = y1 - 1; y >= 0; y--) {
            for (int x = 0; x < x1; x++) {
                // i,j位置的Color值
                int pixel = bufferedImage.getRGB(x, y);
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
//                System.err.println(rgb[0] + "," + rgb[1] + "," + rgb[2]);
                // 位置
                point[0] = x;
                point[1] = y;
                // 匹配
                if (this.match(rgb, rgb1, rgb2)) {
                    findCount++;
                    if (findCount >= maxCount) {
                        return point;
                    }
                }
            }
        }
        return point;
    }


    public BufferedImage cropImage(InputStream inputStream, int x, int y, int w, int h) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        return bufferedImage.getSubimage(x, y, w, h);
    }

    public boolean match(int[] rgb, int[] rgb1, int[] rgb2) {
        if (rgb[0] >= rgb1[0] && rgb[0] <= rgb2[0]
                && rgb[1] >= rgb1[1] && rgb[1] <= rgb2[1]
                && rgb[2] >= rgb1[2] && rgb[2] <= rgb2[2]) {
            return true;
        }
        return false;
    }


    public static int[] getData(BufferedImage bufferedImage) {
        try{
            BufferedImage img = bufferedImage;
            BufferedImage slt = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            slt.getGraphics().drawImage(img, 0, 0, 100, 100, null);
            // ImageIO.write(slt,"jpeg",new File("slt.jpg"));
            int[] data = new int[256];
            for (int x = 0; x < slt.getWidth(); x++) {
                for (int y = 0; y < slt.getHeight(); y++) {
                    int rgb = slt.getRGB(x, y);
                    Color myColor = new Color(rgb);
                    int r = myColor.getRed();
                    int g = myColor.getGreen();
                    int b = myColor.getBlue();
                    data[(r + g + b) / 3]++;
                }
            }
            // data 就是所谓图形学当中的直方图的概念
            return data;
        }catch(Exception exception){
            System.out.println("有文件没有找到,请检查文件是否存在或路径是否正确");
            return null;
        }
    }

    public static float compare(int[] s, int[] t) {
        try{
            float result = 0F;
            for (int i = 0; i < 256; i++) {
                int abs = Math.abs(s[i] - t[i]);
                int max = Math.max(s[i], t[i]);
                result += (1 - ((float) abs / (max == 0 ? 1 : max)));
            }
            return (result / 256) * 100;
        }catch(Exception exception){
            return 0;
        }
    }
}
