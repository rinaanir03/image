package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class otherModel {
    private static String picturePath ="";
    static BufferedImage image;
    static int width;
    static int height;
    static JFrame f;
    static int mask[][] = {{1,1,1}, {1,1,1}, {1,1,1}};
    static double maskGauss[][] = {{1,1,1,1,1}, {1,2,4,2,1}, {1,4,8,4,1}, {1,2,4,2,1}, {1,1,1,1,1}};


    public static void openPicture() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Select an image");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG images", "jpg");
        jfc.addChoosableFileFilter(filter);

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            picturePath = jfc.getSelectedFile().getPath();
            System.out.println(jfc.getSelectedFile().getPath());
        }
    }

    public static boolean picturePathIsEmpty() {
        return picturePath.isEmpty();
    }

    public static void boxFiltr() {
        try {
            if(!picturePath.isEmpty()) {

                File input = new File(picturePath);
                image = ImageIO.read(input);
                width = image.getWidth();
                height = image.getHeight();

                for(int i = 1; i < height-1; i++){
                    for(int j = 1; j < width-1; j++){

                        int x, y, z;
                        x = 0;
                        y = 0;
                        z = 0;

                        for(int k=-1; k<=1; k++) {
                            for(int l=-1; l<=1; l++) {
                                Color c = new Color(image.getRGB(j+k, i+l));
                                int red = (int)(c.getRed());
                                int green = (int)(c.getGreen());
                                int blue = (int)(c.getBlue());

                                x += red * mask[k+1][l+1];
                                y += green * mask[k+1][l+1];
                                z += blue * mask[k+1][l+1];

                            }
                        }

                        x = x/9;
                        y = y/9;
                        z = z/9;

                        if (x>255) {
                            x=255;
                        }else if (x < 0){
                            x=0;
                        }
                        if (y>255) {
                            y=255;
                        }else if (y < 0){
                            y=0;
                        }
                        if (z>255) {
                            z=255;
                        }else if (z < 0){
                            z=0;
                        }

                        Color newColor = new Color(x, y, z);
                        image.setRGB(j,i,newColor.getRGB());
                    }
                }
                File ouptut = new File(picturePath.replace(".jpg", "_box_filtr.jpg"));
                ImageIO.write(image, "jpg", ouptut);
                f = new JFrame();
                JOptionPane.showMessageDialog(f, "Success!");
            }
        } catch (Exception e) {}
    }

    public static void gaussFiltr() {
        try {
            if(!picturePath.isEmpty()) {

                File input = new File(picturePath);
                image = ImageIO.read(input);
                width = image.getWidth();
                height = image.getHeight();


                for(int i = 2; i < height-2; i++){
                    for(int j = 2; j < width-2; j++){

                        double x, y, z;
                        x = 0;
                        y = 0;
                        z = 0;

                        double sum = 0;

                        for(int k=-2; k<=2; k++) {
                            for(int l=-2; l<=2; l++) {
                                Color c = new Color(image.getRGB(j+k, i+l));
                                int red = (int)(c.getRed());
                                int green = (int)(c.getGreen());
                                int blue = (int)(c.getBlue());

                                x += red * maskGauss[k+2][l+2];
                                y += green * maskGauss[k+2][l+2];
                                z += blue * maskGauss[k+2][l+2];

                                sum += maskGauss[k+2][l+2];
                            }
                        }

                        x = x/sum;
                        y = y/sum;
                        z = z/sum;

                        if (x>255) {
                            x=255;
                        }else if (x < 0){
                            x=0;
                        }
                        if (y>255) {
                            y=255;
                        }else if (y < 0){
                            y=0;
                        }
                        if (z>255) {
                            z=255;
                        }else if (z < 0){
                            z=0;
                        }

                        Color newColor = new Color((int)x, (int)y, (int)z);
                        image.setRGB(j,i,newColor.getRGB());
                    }
                }
                File ouptut = new File(picturePath.replace(".jpg", "_gauss_filter.jpg"));
                ImageIO.write(image, "jpg", ouptut);
                f = new JFrame();
                JOptionPane.showMessageDialog(f, "Success!");
            }
        } catch (Exception e) {}

    }

    public static void sharpen(double[][]maska, String filtr) {
        try {
            if(!picturePath.isEmpty()) {

                File input = new File(picturePath);
                image = ImageIO.read(input);
                width = image.getWidth();
                height = image.getHeight();

                int[][] R = new int[width][height];
                int[][] G = new int[width][height];
                int[][] B = new int[width][height];

                for(int i = 1; i < height-1; i++){
                    for(int j = 1; j < width-1; j++){

                        int x, y, z;
                        x = 0;
                        y = 0;
                        z = 0;

                        for(int k=-1; k<=1; k++) {
                            for(int l=-1; l<=1; l++) {
                                Color c = new Color(image.getRGB(j+k, i+l));
                                int red = (int)(c.getRed());
                                int green = (int)(c.getGreen());
                                int blue = (int)(c.getBlue());

                                x += red * maska[k+1][l+1];
                                y += green * maska[k+1][l+1];
                                z += blue * maska[k+1][l+1];

                            }
                        }


                        x = Math.abs(x);
                        y = Math.abs(y);
                        z = Math.abs(z);

                        if (x>255) {
                            x=255;
                        }else if (x < 0){
                            x=0;
                        }
                        if (y>255) {
                            y=255;
                        }else if (y < 0){
                            y=0;
                        }
                        if (z>255) {
                            z=255;
                        }else if (z < 0){
                            z=0;
                        }

                        R[j][i] = x;
                        G[j][i] = y;
                        B[j][i] = z;

                    }
                }
                for(int f = 0; f < height; f++){
                    for(int t = 0; t < width; t++){
                        Color newColor = new Color(R[t][f], G[t][f], B[t][f]);
                        image.setRGB(t,f,newColor.getRGB());
                    }
                }
                File output;
                switch(filtr){
                    case "robH":
                        output = new File(picturePath.replace(".jpg", "_robertsH_filtr.jpg"));
                        ImageIO.write(image, "jpg", output);
                        break;
                    case "preH":
                        output = new File(picturePath.replace(".jpg", "_prewitH_filtr.jpg"));
                        ImageIO.write(image, "jpg", output);
                        break;
                    case "sobH":
                        output = new File(picturePath.replace(".jpg", "_sobelH_filtr.jpg"));
                        ImageIO.write(image, "jpg", output);
                        break;
                    case "robV":
                        output = new File(picturePath.replace(".jpg", "_robertsV_filtr.jpg"));
                        ImageIO.write(image, "jpg", output);
                        break;
                    case "preV":
                        output = new File(picturePath.replace(".jpg", "_prewitV_filtr.jpg"));
                        ImageIO.write(image, "jpg", output);
                        break;
                    case "sobV":
                        output = new File(picturePath.replace(".jpg", "_sobelV_filtr.jpg"));
                        ImageIO.write(image, "jpg", output);
                        break;
                    case "lap":
                        output = new File(picturePath.replace(".jpg", "_laplace_filtr.jpg"));
                        ImageIO.write(image, "jpg", output);
                        break;
                }
                f = new JFrame();
                JOptionPane.showMessageDialog(f, "Success!");

            }
        } catch (Exception e) {}
    }

    public static void minFiltr() {
        try {
            if(!picturePath.isEmpty()) {

                int rmin, gmin, bmin;
                File input = new File(picturePath);
                image = ImageIO.read(input);
                width = image.getWidth();
                height = image.getHeight();

                int[][] R = new int[width][height];
                int[][] G = new int[width][height];
                int[][] B = new int[width][height];

                for(int i = 1; i < height-1; i++){
                    for(int j = 1; j < width-1; j++){

                        rmin = 255;
                        bmin = 255;
                        gmin = 255;

                        for(int k = -1; k <= 1; k++) {
                            for(int l = -1; l <= 1; l++) {
                                Color c = new Color(image.getRGB(j+k, i+l));
                                int red = (int)(c.getRed());
                                int green = (int)(c.getGreen());
                                int blue = (int)(c.getBlue());

                                if (rmin > red) rmin = red;
                                if (gmin > green) gmin = green;
                                if (bmin > blue) bmin = blue;

                            }
                        }

                        //System.out.println(rmin + " " + gmin +  " " + bmin);
                        R[j][i] = rmin;
                        G[j][i] = gmin;
                        B[j][i] = bmin;

                        if (rmin>255) {
                            rmin=255;
                        }else if (rmin < 0){
                            rmin=0;
                        }
                        if (gmin>255) {
                            gmin=255;
                        }else if (gmin < 0){
                            gmin=0;
                        }
                        if (bmin>255) {
                            bmin=255;
                        }else if (bmin < 0){
                            bmin=0;
                        }


                    }
                }
                for(int f = 0; f < height; f++){
                    for(int t = 0; t < width; t++){
                        Color newColor = new Color(R[t][f], G[t][f], B[t][f]);
                        image.setRGB(t,f,newColor.getRGB());
                    }
                }
                File ouptut = new File(picturePath.replace(".jpg", "_min_filtr.jpg"));
                ImageIO.write(image, "jpg", ouptut);
                f = new JFrame();
                JOptionPane.showMessageDialog(f, "Success!");
            }
        } catch (Exception e) {}
    }

    public static void maxFiltr() {
        try {
            if(!picturePath.isEmpty()) {

                int rmin, gmin, bmin;
                File input = new File(picturePath);
                image = ImageIO.read(input);
                width = image.getWidth();
                height = image.getHeight();

                int[][] R = new int[width][height];
                int[][] G = new int[width][height];
                int[][] B = new int[width][height];

                for(int i = 1; i < height-1; i++){
                    for(int j = 1; j < width-1; j++){

                        rmin = 0;
                        bmin = 0;
                        gmin = 0;

                        for(int k = -1; k <= 1; k++) {
                            for(int l = -1; l <= 1; l++) {
                                Color c = new Color(image.getRGB(j+k, i+l));
                                int red = (int)(c.getRed());
                                int green = (int)(c.getGreen());
                                int blue = (int)(c.getBlue());

                                if (rmin < red) rmin = red;
                                if (gmin < green) gmin = green;
                                if (bmin < blue) bmin = blue;

                            }
                        }

                        R[j][i] = rmin;
                        G[j][i] = gmin;
                        B[j][i] = bmin;

                        if (rmin>255) {
                            rmin=255;
                        }else if (rmin < 0){
                            rmin=0;
                        }
                        if (gmin>255) {
                            gmin=255;
                        }else if (gmin < 0){
                            gmin=0;
                        }
                        if (bmin>255) {
                            bmin=255;
                        }else if (bmin < 0){
                            bmin=0;
                        }
                    }
                }
                for(int f = 0; f < height; f++){
                    for(int t = 0; t < width; t++){
                        Color newColor = new Color(R[t][f], G[t][f], B[t][f]);
                        image.setRGB(t,f,newColor.getRGB());
                    }
                }
                File ouptut = new File(picturePath.replace(".jpg", "_max_filtr.jpg"));
                ImageIO.write(image, "jpg", ouptut);
                f = new JFrame();
                JOptionPane.showMessageDialog(f, "Success!");
            }
        } catch (Exception e) {}
    }

    public static void medianaFiltr() {
        try {
            if(!picturePath.isEmpty()) {

                int x, y, z;
                File input = new File(picturePath);
                image = ImageIO.read(input);
                width = image.getWidth();
                height = image.getHeight();

                int[][] R = new int[width][height];
                int[][] G = new int[width][height];
                int[][] B = new int[width][height];

                int[] maskR=new int[9];
                int[] maskG=new int[9];
                int[] maskB=new int[9];

                for(int i = 1; i < height-1; i++) {
                    for(int j = 1; j < width-1; j++){
                        x = 0;
                        y = 0;
                        z = 0;

                        int d = 0;

                        for(int k = -1; k <= 1; k++) {
                            for(int l = -1; l <= 1; l++) {
                                Color c = new Color(image.getRGB(j+k, i+l));
                                int red = (int)(c.getRed());
                                int green = (int)(c.getGreen());
                                int blue = (int)(c.getBlue());

                                maskR[d] = red;
                                maskG[d] = green;
                                maskB[d] = blue;
                                d+=1;
                            }
                        }
                        Arrays.sort(maskR);
                        Arrays.sort(maskG);
                        Arrays.sort(maskB);

                        x = maskR[4];
                        y = maskG[4];
                        z = maskB[4];
                        //System.out.println(x + " " + y + " " + z);
                        R[j][i] = x;
                        G[j][i] = y;
                        B[j][i] = z;
                    }
                }
                for(int f = 0; f < height; f++){
                    for(int t = 0; t < width; t++){
                        Color newColor = new Color(R[t][f], G[t][f], B[t][f]);
                        image.setRGB(t,f,newColor.getRGB());
                    }
                }
                File ouptut = new File(picturePath.replace(".jpg", "_mediana_filtr.jpg"));
                ImageIO.write(image, "jpg", ouptut);
                f = new JFrame();
                JOptionPane.showMessageDialog(f, "Success!");}
        } catch (Exception e) {}
    }
}
