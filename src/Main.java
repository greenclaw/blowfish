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

        System.out.println(Integer.toBinaryString(32));
        System.out.println("Зашифровать [1]");
        System.out.println("Расшифровать [2]");
        System.out.println("Выход [Друой символ]");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        char[] k;
        int i = Integer.parseInt(s);

        if (i == 1) {
            k = keyMenu();
            String inputFile = "source.jpg";
            byte[] text = Blowfish.readBytes(inputFile);
            byte[] c = Blowfish.encipher(k, text);
            Blowfish.writeBytes("cipher.txt", c);
        } else if (i == 2) {
            String keyFile = "key.txt";
            k = Blowfish.readKeyFromFile(keyFile);
            String inputFile = "cipher.txt";
            byte[] text = Blowfish.readBytes(inputFile);
            byte[] c = Blowfish.decipher(k, text);
            Blowfish.writeBytes("output.jpg", c);
        } else {
            return;
        }
    }


    private static char[] keyMenu() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Случайный ключ [1]");
        System.out.println("Считать из файла [Другой символ]");
        String s = br.readLine();
        int i = Integer.parseInt(s);
        char[] k;
        if (i == 1) {
            k = Blowfish.generateKey();
            Blowfish.writeToFile(k, "key.txt");
        } else {
            String keyFile = "key.txt";
            k = Blowfish.readKeyFromFile(keyFile);
        }
        return k;
    }

//    private static byte[] readBytes(String fileName) throws IOException {
//
//    }
//
//    private static byte[] bytesToChar(char[] chars) {
//
//        for(char c : chars) {
//
//        }
//
//    }

}
