import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

//import java.nio.file.Files;
//import java.nio.file.StandardOpenOption;
//import java.util.ArrayList;
//import java.nio.file.Path;
//import java.nio.file.Paths;


//Input -> inputPath slotCount outputPath
//Open and Read File
//Output Byte Data before animation calls
//Output and Modify Animation Calls
//Output Animation Data


public class Main 
{   
    public static String inputFilePath = "input\\a_mario";
    public static String outputFilePath = "output";
    public static int slotCount = 1;

    /**
     * @Author Jemaroo
     * @Function Will attempt to read the given file, and set data into a new file using a specified slot count
     */
    public static void main(String[] args)
    {
        //inputFilePath = args[0];
        //slotCount = Integer.parseInt(args[1]);
        //outputFilePath = args[2];
        File inputFile = new File(inputFilePath);

        //Import File
        byte[] data = readData(inputFile);

        String[] formattedData = new String[data.length];

        for(int i = 0; i < data.length; i++)
        {
            formattedData[i] = String.format("%x", data[i]);
        }

        //Write data into new file using new slot count
        writeNewFile(data, formattedData, inputFile);

        System.out.println("Done!");
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to read the given file
     */
    private static byte[] readData(File file)
    {
        try 
        {
            FileInputStream f1 = new FileInputStream(file);

            byte[] data = new byte[(int)file.length()];

            f1.read(data);

            f1.close();

            return data;
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("There was an Error Opening the Input File");
        }
        catch (IOException e)
        {
            System.out.println("There was an Error Reading the Input File");
        }
    
        return new byte[0];
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to write a new file with the specified amount of animation slots
     */
    private static void writeNewFile(byte[] data, String[] formattedData, File inputFile)
    {
        try
        {
            File output = new File((outputFilePath + "\\" + inputFile.getName()));

            RandomAccessFile newFile = new RandomAccessFile(output, "rw");

            //Determining how many animations are present
            String animationCount = "";

            if(formattedData[328].equals("0"))
            {
                animationCount = animationCount + "00";
            }
            else
            {
                animationCount = animationCount + formattedData[328];
            }

            if(formattedData[329].equals("0"))
            {
                animationCount = animationCount + "00";
            }
            else
            {
                animationCount = animationCount + formattedData[329];
            }

            if(formattedData[330].equals("0"))
            {
                animationCount = animationCount + "00";
            }
            else
            {
                animationCount = animationCount + formattedData[330];
            }

            if(formattedData[331].equals("0"))
            {
                animationCount = animationCount + "00";
            }
            else
            {
                animationCount = animationCount + formattedData[331];
            }

            int animationCountFormatted = Integer.parseInt(animationCount, 16);

            //Variables for animation placement
            String animationCallStart = "";

            if(formattedData[428].equals("0"))
            {
                animationCallStart = animationCallStart + "00";
            }
            else if(Integer.parseInt(formattedData[428], 16) < 16)
            {
                animationCallStart = animationCallStart + "0" + formattedData[428];
            }
            else
            {
                animationCallStart = animationCallStart + formattedData[428];
            }

            if(formattedData[429].equals("0"))
            {
                animationCallStart = animationCallStart + "00";
            }
            else if(Integer.parseInt(formattedData[429], 16) < 16)
            {
                animationCallStart = animationCallStart + "0" + formattedData[429];
            }
            else
            {
                animationCallStart = animationCallStart + formattedData[429];
            }

            if(formattedData[430].equals("0"))
            {
                animationCallStart = animationCallStart + "00";
            }
            else if(Integer.parseInt(formattedData[430], 16) < 16)
            {
                animationCallStart = animationCallStart + "0" + formattedData[430];
            }
            else
            {
                animationCallStart = animationCallStart + formattedData[430];
            }

            if(formattedData[431].equals("0"))
            {
                animationCallStart = animationCallStart + "00";
            }
            else if(Integer.parseInt(formattedData[431], 16) < 16)
            {
                animationCallStart = animationCallStart + "0" + formattedData[431];
            }
            else
            {
                animationCallStart = animationCallStart + formattedData[431];
            }

            int animationCallStartFormatted = Integer.parseInt(animationCallStart, 16);

            //Writing data before animation calls
            for(int i = 0; i < animationCallStartFormatted; i++)
            {
                newFile.write(data[i]);
            }

            //Checking if slot number given is valid
            if(slotCount <= 1)
            {
                //Writing Name and Padding
                for(int i = animationCallStartFormatted; i < animationCallStartFormatted + 60; i++)
                {
                    newFile.write(data[i]);
                }

                //Writing new Offset
                //TODO
            }








            newFile.close();
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("There was an Error Opening the Input File");
        }
        catch (IOException e)
        {
            System.out.println("There was an Error Reading the Input File");
        }
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