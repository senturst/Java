import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

    public class MakeCode
{


    public static void main(String[] args)
    {
        String content="http://www.baidu.com";
        String filename="qrcode";
        String filepath="./"+filename+".png";
        new MakeCode().drawQRCODE(content, filepath);
        System.out.println("生成完成!");
        System.out.println(content);

    }
    public void drawQRCODE(String content,String filepath){
        try {
            Qrcode qrcode=new Qrcode();

            qrcode.setQrcodeErrorCorrect('M');
            qrcode.setQrcodeEncodeMode('B');
            qrcode.setQrcodeVersion(15);
            int width= 235;
            int height=235;
            BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            Graphics2D g2=image.createGraphics();
            g2.setBackground(Color.WHITE);
            g2.clearRect(0,0,235,235);
            g2.setColor(Color.BLACK);

            byte[] contentbytes=content.getBytes("utf-8");
            boolean[][] codeout= qrcode.calQrcode(contentbytes);
            for (int i = 0; i <codeout.length; i++) {
                for (int j = 0; j < codeout.length; j++) {

                    if (codeout[j][i]) g2.fillRect(j*3+2,i*3+2,3,3);
                }
            }
            g2.dispose();
            image.flush();
            File imgFile = new File(filepath);
            ImageIO.write(image, "png", imgFile);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}