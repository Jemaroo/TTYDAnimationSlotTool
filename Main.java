import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;


//Input -> inputPath slotCount
//Open and Read File
//Output Byte Data before animation calls
//Output and Modify Animation Calls
//Output Animation Data


public class Main 
{   
    public static String inputFilePath = "";
    public static int slotCount = 1;

    /**
     * @Author Jemaroo
     * @Function TODO
     */
    public static void main(String[] args)
    {
        inputFilePath = args[0];
        slotCount = Integer.parseInt(args[1]);
        File root = new File(inputFilePath);
    }

    /**
     * @Author baeldung
     * @Function Cut the Hex value in 2 char groups, Convert it to base 16 Integer using Integer.parseInt(hex, 16) and cast to char, Append all chars in a StringBuilder
     * @Source https://www.baeldung.com/java-convert-hex-to-ascii
     */
    private static String hexToAscii(String hexStr) 
    {
        StringBuilder output = new StringBuilder("");
        
        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        
        return output.toString();
    }
}