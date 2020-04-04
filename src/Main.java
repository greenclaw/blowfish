import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.*;
import java.util.Arrays;

/**
 * Created by rom on 02.11.16.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Encryption [1]");
        System.out.println("Decryption [2]");
        System.out.println("Quit [Any other digit]");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        char[] k;

        // This is an example how to encrypt the image file
        if (s.equals("1")) {
            System.out.println("Enter source filename, default 'source.jpg':");
            String inputFile = br.readLine();
            k = keyMenu();
            if (inputFile.equals("")) {
                inputFile = "source.jpg";
            }
            byte[] text = Blowfish.readBytes(inputFile);
            byte[] c = Blowfish.encipher(k, text);
            System.out.println("Enter output filename, default 'cipher.txt':");
            String outputFile = br.readLine();
            if (outputFile.equals("")) {
                outputFile = "cipher.txt";
            }
            Blowfish.writeBytes(outputFile, c);
            System.out.println("Cipher text stored to '" + outputFile + "'");
        // Here we decrypt encrypted image
        } else if (s.equals("2")) {
            System.out.println("Enter the key filename, default 'key.txt':");
            String keyFile = br.readLine();
            if (keyFile.equals("")) {
                keyFile = "key.txt";
            }
            k = Blowfish.readKeyFromFile(keyFile);
            System.out.println("Enter cipher text path, default 'cipher.txt'");
            String inputFile = br.readLine();
            if (inputFile.equals("")) {
                inputFile = "cipher.txt";
            }
            System.out.println("Enter output filename, default 'output.jpg'");
            String outputFile = br.readLine();
            if (outputFile.equals("")) {
                outputFile = "output.jpg";
            }
            byte[] text = Blowfish.readBytes(inputFile);
            byte[] c = Blowfish.decipher(k, text);
            Blowfish.writeBytes(outputFile, c);
        } else {
            return;
        }
    }


    private static char[] keyMenu() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Read key from file, default 'key.txt' [1]");
        System.out.println("Random key [other digit]");
        String s = br.readLine();
        char[] k;
        if (s.equals("1") || s.equals("")) {
            String keyFile = "key.txt";
            k = Blowfish.readKeyFromFile(keyFile);
        } else {
            k = Blowfish.generateKey();
            Blowfish.writeToFile(k, "key.txt");
        }
        return k;
    }

}
