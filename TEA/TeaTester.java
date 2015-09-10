
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author sheff
 */
public class TeaTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TEAAlgo tea = new TEAAlgo();
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("alice.bmp"));
            byte[] dataBuffer = ((DataBufferByte)img.getRaster().getDataBuffer()).getData();
            
            for (int i = 79; i < dataBuffer.length; i++) {
                byte[] block = Arrays.copyOfRange(dataBuffer, i, i+7);
                block = tea.encrypt(block);
                for (int j = 0; j < block.length; j++) {
                    dataBuffer[i] = block[j];
                }
            }
            
            File outputfile = new File("saved.bmp");
            ImageIO.write(img, "bmp", outputfile);
            
        } catch (IOException e) {
            System.out.println(e);
        }
        
    }

    
}
