import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main 
{   
    public static String inputFilePath = "";
    public static String outputFilePath = "";
    public static int slotCount = 1;

    /**
     * @Author Jemaroo
     * @Function Will attempt to read the given file, and set data into a new file using a specified slot count
     */
    public static void main(String[] args)
    {
        inputFilePath = args[0];
        slotCount = Integer.parseInt(args[1]);
        outputFilePath = args[2];
        File inputFile = new File(inputFilePath);

        //Import File
        byte[] data = readData(inputFile);

        String[] formattedData = new String[data.length];

        for(int i = 0; i < data.length; i++)
        {
            formattedData[i] = String.format("%x", data[i]);

            if(formattedData[i].equals("0"))
            {
                formattedData[i] = "00";
            }
            else if(Integer.parseInt(formattedData[i], 16) < 16)
            {
                formattedData[i] = "0" + formattedData[i];
            }
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

            animationCount = animationCount + formattedData[328] + formattedData[329] + formattedData[330] + formattedData[331];

            int animationCountFormatted = Integer.parseInt(animationCount, 16);

            //Variables for animation placement
            String animationCallStart = "";

            animationCallStart = animationCallStart + formattedData[428] + formattedData[429] + formattedData[430] + formattedData[431];

            int animationCallStartFormatted = Integer.parseInt(animationCallStart, 16);

            int animationDataStartFormatted = animationCallStartFormatted + (animationCountFormatted * 64);

            //System.out.println("Animation Call Start: " + animationCallStart);
            //System.out.println("Animation Call Start Formatted: " + animationCallStartFormatted);
            //System.out.println("Animation Data Start Formatted: " + animationDataStartFormatted);

            //Writing data before animation count
            for(int i = 0; i < 331; i++)
            {
                newFile.write(data[i]);
            }

            if(slotCount <= 1)
            {
                //Writing new animation count
                newFile.writeByte(1);
            }
            else
            {
                //Writing new animation count
                newFile.writeByte(slotCount);
            }

            //Writing data after animation count and before animation calls
            for(int i = 332; i < animationCallStartFormatted; i++)
            {
                newFile.write(data[i]);
            }

            //Determining Modifier for slots
            int slotModifier = slotCount - animationCountFormatted;

            //Checking if slot number given is valid or 1
            if(slotCount <= 1)
            {
                //Writing Name and Padding
                for(int i = animationCallStartFormatted; i < animationCallStartFormatted + 60; i++)
                {
                    newFile.write(data[i]);
                }

                //Writing Animation Offset
                String combinedAnimationOffsetValues = getOffsetString(formattedData, animationCallStartFormatted, 0);

                int hexedCombinedAnimationOffsetValues = Integer.parseInt(combinedAnimationOffsetValues, 16);
                int removedByteAmount = (Integer.parseInt(animationCount, 16) - 1) * 64;
                hexedCombinedAnimationOffsetValues -= removedByteAmount;

                writeSlotOffset(newFile, hexedCombinedAnimationOffsetValues);      
            }
            //Checking if slot number given is equal to number already present
            else if (slotCount == Integer.parseInt(animationCount, 16))
            {
                int animationDataOffsetModifier = 0;

                //Writing all current animations
                for(int i = 0; i < Integer.parseInt(animationCount, 16); i++)
                {
                    //Writing Name and Padding
                    for(int j = animationCallStartFormatted + animationDataOffsetModifier; j < animationCallStartFormatted + animationDataOffsetModifier + 60; j++)
                    {
                        newFile.write(data[j]);
                    }

                    //Writing Animation Offset
                    String combinedAnimationOffsetValues = getOffsetString(formattedData, animationCallStartFormatted, animationDataOffsetModifier);

                    int hexedCombinedAnimationOffsetValues = Integer.parseInt(combinedAnimationOffsetValues, 16);

                    System.out.println("Passthrough " + i + "'s animationDataOffsetModifier: " + animationDataOffsetModifier);
                    System.out.println("Passthrough " + i + "'s hexCombinedAnimationOffsetValues: " + hexedCombinedAnimationOffsetValues);

                    int addedByteAmount = (slotCount - Integer.parseInt(animationCount, 16)) * 64;
                    hexedCombinedAnimationOffsetValues += addedByteAmount;

                    System.out.println("Passthrough " + i + "'s removedByteAmount: " + addedByteAmount);
                    System.out.println("Passthrough " + i + "'s hexCombinedAnimationOffsetValues: " + hexedCombinedAnimationOffsetValues);
                    System.out.println("Passthrough " + i + "'s hexCombinedAnimationOffsetValues: " + Integer.toHexString(hexedCombinedAnimationOffsetValues));

                    writeSlotOffset(newFile, hexedCombinedAnimationOffsetValues);

                    animationDataOffsetModifier += 64;
                }
            }
            //Adding Slots
            else if (slotModifier > 0)
            {
                int animationDataOffsetModifier = 0;

                //Writing all current animations
                for(int i = 0; i < Integer.parseInt(animationCount, 16); i++)
                {
                    //Writing Name and Padding
                    for(int j = animationCallStartFormatted + animationDataOffsetModifier; j < animationCallStartFormatted + animationDataOffsetModifier + 60; j++)
                    {
                        newFile.write(data[j]);
                    }

                    //Writing Animation Offset
                    String combinedAnimationOffsetValues = getOffsetString(formattedData, animationCallStartFormatted, animationDataOffsetModifier);

                    int hexedCombinedAnimationOffsetValues = Integer.parseInt(combinedAnimationOffsetValues, 16);
                    int addedByteAmount = (slotCount - Integer.parseInt(animationCount, 16)) * 64;
                    hexedCombinedAnimationOffsetValues += addedByteAmount;

                    writeSlotOffset(newFile, hexedCombinedAnimationOffsetValues);

                    animationDataOffsetModifier += 64;
                }

                //Writing additional animations slots
                String combinedAnimationOffsetValues = getOffsetString(formattedData, animationCallStartFormatted, 0);

                int hexedCombinedAnimationOffsetValues = Integer.parseInt(combinedAnimationOffsetValues, 16);
                int addedByteAmount = (slotCount - Integer.parseInt(animationCount, 16)) * 64;
                hexedCombinedAnimationOffsetValues += addedByteAmount;

                for(int i = 1; i < slotModifier + 1; i++)
                {
                    if(i < 10)
                    {
                        newFile.writeBytes("EMPTY" + i);
                        for(int j = 0; j < 54; j++) {newFile.write(0);}
                        writeSlotOffset(newFile, hexedCombinedAnimationOffsetValues);
                    }
                    else if(i < 100)
                    {
                        newFile.writeBytes("EMPTY" + i);
                        for(int j = 0; j < 53; j++) {newFile.write(0);}
                        writeSlotOffset(newFile, hexedCombinedAnimationOffsetValues);
                    }
                    else
                    {
                        newFile.writeBytes("EMPTY" + i);
                        for(int j = 0; j < 52; j++) {newFile.write(0);}
                        writeSlotOffset(newFile, hexedCombinedAnimationOffsetValues);
                    }
                }
            }
            //Removing Slots
            else if (slotModifier < 0)
            {
                int animationDataOffsetModifier = 0;

                //Writing all current animations up to amount
                for(int i = 0; i < slotCount; i++)
                {
                    //Writing Name and Padding
                    for(int j = animationCallStartFormatted + animationDataOffsetModifier; j < animationCallStartFormatted + animationDataOffsetModifier + 60; j++)
                    {
                        newFile.write(data[j]);
                    }

                    //Writing Animation Offset
                    String combinedAnimationOffsetValues = getOffsetString(formattedData, animationCallStartFormatted, animationDataOffsetModifier);

                    int hexedCombinedAnimationOffsetValues = Integer.parseInt(combinedAnimationOffsetValues, 16);
                    int removedByteAmount = (Integer.parseInt(animationCount, 16) - slotCount) * 64;
                    hexedCombinedAnimationOffsetValues -= removedByteAmount;

                    writeSlotOffset(newFile, hexedCombinedAnimationOffsetValues);

                    animationDataOffsetModifier += 64;
                }
            }

            //Writing rest of data
            for(int i = animationDataStartFormatted; i < data.length; i++)
            {
                newFile.write(data[i]);
            }

            newFile.close();
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("There was an Error Opening the Output File");
        }
        catch (IOException e)
        {
            System.out.println("There was an Error Reading the Output File");
        }
    }

    /**
     * @Author Jemaroo
     * @Function Will take in information about an animation slot, and format it into a string
     */
    private static String getOffsetString(String[] formattedData, int animationCallStartFormatted, int animationDataOffsetModifier)
    {
        String combinedAnimationOffsetValues = "";
        
        if(formattedData[animationCallStartFormatted + animationDataOffsetModifier + 60].equals("0")) {combinedAnimationOffsetValues += "00";}
        else {combinedAnimationOffsetValues += formattedData[animationCallStartFormatted + animationDataOffsetModifier + 60];}

        if(formattedData[animationCallStartFormatted + animationDataOffsetModifier + 61].equals("0")) {combinedAnimationOffsetValues += "00";}
        else {combinedAnimationOffsetValues += formattedData[animationCallStartFormatted + animationDataOffsetModifier + 61];}

        if(formattedData[animationCallStartFormatted + animationDataOffsetModifier + 62].equals("0")) {combinedAnimationOffsetValues += "00";}
        else {combinedAnimationOffsetValues += formattedData[animationCallStartFormatted + animationDataOffsetModifier + 62];}

        if(formattedData[animationCallStartFormatted + animationDataOffsetModifier + 63].equals("0")) {combinedAnimationOffsetValues += "00";}
        else {combinedAnimationOffsetValues += formattedData[animationCallStartFormatted + animationDataOffsetModifier + 63];}

        return combinedAnimationOffsetValues;
    }

    /**
     * @Author Jemaroo
     * @Function Will write the animation data offset out with data given
     */
    private static void writeSlotOffset(RandomAccessFile newFile, int hexedCombinedAnimationOffsetValues)
    {
        try
        {
            if(Integer.toHexString(hexedCombinedAnimationOffsetValues).length() == 1)
            {
                newFile.writeByte(0); newFile.writeByte(0); newFile.writeByte(0);
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(0) + ""), 16));
            }
            else if(Integer.toHexString(hexedCombinedAnimationOffsetValues).length() == 2)
            {
                newFile.writeByte(0); newFile.writeByte(0); newFile.writeByte(0);
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(0) + "" + Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(1)), 16));
            }
            else if(Integer.toHexString(hexedCombinedAnimationOffsetValues).length() == 3)
            {
                newFile.writeByte(0); newFile.writeByte(0); 
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(0) + ""), 16));
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(1) + "" + Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(2)), 16));
            }
            else if(Integer.toHexString(hexedCombinedAnimationOffsetValues).length() == 4)
            {
                newFile.writeByte(0); newFile.writeByte(0); 
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(0) + "" + Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(1)), 16));
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(2) + "" + Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(3)), 16));
            }
            else if(Integer.toHexString(hexedCombinedAnimationOffsetValues).length() == 5)
            {
                newFile.writeByte(0); 
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(0) + ""), 16));
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(1) + "" + Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(2)), 16));
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(3) + "" + Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(4)), 16));
            }
            else if(Integer.toHexString(hexedCombinedAnimationOffsetValues).length() <= 6)
            {
                newFile.writeByte(0); 
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(0) + "" + Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(1)), 16));
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(2) + "" + Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(3)), 16));
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(4) + "" + Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(5)), 16));
            }
            else if(Integer.toHexString(hexedCombinedAnimationOffsetValues).length() <= 7)
            {
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(0) + ""), 16));
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(1) + "" + Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(2)), 16));
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(3) + "" + Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(4)), 16));
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(5) + "" + Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(6)), 16));
            }
            else
            {
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(0) + "" + Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(1)), 16));
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(2) + "" + Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(3)), 16));
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(4) + "" + Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(5)), 16));
                newFile.writeByte(Integer.parseInt((Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(6) + "" + Integer.toHexString(hexedCombinedAnimationOffsetValues).charAt(7)), 16));
            }
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("There was an Error Opening the Output File");
        }
        catch (IOException e)
        {
            System.out.println("There was an Error Reading the Output File");
        }
    }
}