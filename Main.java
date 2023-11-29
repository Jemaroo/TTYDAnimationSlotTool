import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main 
{   
    public static String inputFilePath = "";
    public static String commandType = "";
    public static int commandModifier = 1;
    public static String outputFilePath = "";

    /**
     * @Author Jemaroo
     * @Function Will attempt to read the given file, and set data into a new file using the specified changes
     */
    public static void main(String[] args)
    {
        inputFilePath = args[0];
        commandType = args[1];
        commandModifier = Integer.parseInt(args[2]);
        outputFilePath = args[3];
        File inputFile = new File(inputFilePath);

        //Import File
        byte[] data = readData(inputFile);

        String[] formattedData = new String[data.length];

        for(int i = 0; i < data.length; i++)
        {
            formattedData[i] = formatByteString(String.format("%x", data[i]));
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
     * @Function Will attempt to write a new file using the specified changes
     */
    private static void writeNewFile(byte[] data, String[] formattedData, File inputFile)
    {
        try
        {
            File output = new File((outputFilePath + "\\" + inputFile.getName()));

            RandomAccessFile newFile = new RandomAccessFile(output, "rw");

            switch(commandType)
            {
                case "Shapes+": 
                {
                    commandAddShapes(data, formattedData, newFile);
                    break;
                }
                case "Shapes-": 
                {
                    commandRemoveShapes(data, formattedData, newFile);
                    break;
                }
                case "Polygons+": 
                {
                    commandAddPolygons(data, formattedData, newFile);
                    break;
                }
                case "Polygons-": 
                {
                    commandRemovePolygons(data, formattedData, newFile);
                    break;
                }
                case "VertexPositions+": 
                {
                    commandAddVertexPositions(data, formattedData, newFile);
                    break;
                }
                case "VertexPositions-": 
                {
                    commandRemoveVertexPositions(data, formattedData, newFile);
                    break;
                }
                case "VertexPositionIndices+": 
                {
                    commandAddVertexPositionIndices(data, formattedData, newFile);
                    break;
                }
                case "VertexPositionIndices-": 
                {
                    commandRemoveVertexPositionIndices(data, formattedData, newFile);
                    break;
                }
                case "VertexNormalIndices+": 
                {
                    commandAddVertexNormalIndices(data, formattedData, newFile);
                    break;
                }
                case "VertexNormalIndices-": 
                {
                    commandRemoveVertexNormalIndices(data, formattedData, newFile);
                    break;
                }
                case "VertexColors+": 
                {
                    commandAddVertexColors(data, formattedData, newFile);
                    break;
                }
                case "VertexColors-": 
                {
                    commandRemoveVertexColors(data, formattedData, newFile);
                    break;
                }
                case "VertexColorIndices+": 
                {
                    commandAddVertexColorIndices(data, formattedData, newFile);
                    break;
                }
                case "VertexColorIndices-": 
                {
                    commandRemoveVertexColorIndices(data, formattedData, newFile);
                    break;
                }
                case "VertexTextureCoordinate0Indices+": 
                {
                    commandAddTextureCoordinate0Indices(data, formattedData, newFile);
                    break;
                }
                case "VertexTextureCoordinate0Indices-": 
                {
                    commandRemoveTextureCoordinate0Indices(data, formattedData, newFile);
                    break;
                }
                case "VertexTextureCoordinates+": 
                {
                    commandAddVertexTextureCoordinates(data, formattedData, newFile);
                    break;
                }
                case "VertexTextureCoordinates-": 
                {
                    commandRemoveVertexTextureCoordinates(data, formattedData, newFile);
                    break;
                }
                case "TextureCoordinateTransforms+": 
                {
                    commandAddTextureCoordinateTransforms(data, formattedData, newFile);
                    break;
                }
                case "TextureCoordinateTransforms-": 
                {
                    commandRemoveTextureCoordinateTransforms(data, formattedData, newFile);
                    break;
                }
                case "Samplers+": 
                {
                    commandAddSamplers(data, formattedData, newFile);
                    break;
                }
                case "Samplers-": 
                {
                    commandRemoveSamplers(data, formattedData, newFile);
                    break;
                }
                case "Textures+": 
                {
                    commandAddTextures(data, formattedData, newFile);
                    break;
                }
                case "Textures-": 
                {
                    commandRemoveTextures(data, formattedData, newFile);
                    break;
                }
                case "Subshapes+": 
                {
                    commandAddSubshapes(data, formattedData, newFile);
                    break;
                }
                case "Subshapes-": 
                {
                    commandRemoveSubshapes(data, formattedData, newFile);
                    break;
                }
                case "VisibilityGroups+": 
                {
                    commandAddVisibilityGroups(data, formattedData, newFile);
                    break;
                }
                case "VisibilityGroups-": 
                {
                    commandRemoveVisibilityGroups(data, formattedData, newFile);
                    break;
                }
                case "GroupTransformData+": 
                {
                    commandAddGroupTransformData(data, formattedData, newFile);
                    break;
                }
                case "GroupTransformData-": 
                {
                    commandRemoveGroupTransformData(data, formattedData, newFile);
                    break;
                }
                case "Groups+": 
                {
                    commandAddGroups(data, formattedData, newFile);
                    break;
                }
                case "Groups-": 
                {
                    commandRemoveGroups(data, formattedData, newFile);
                    break;
                }
                case "AnimationSlots+": 
                {
                    commandAddAnimationSlots(data, formattedData, newFile);
                    break;
                }
                case "AnimationSlots-": 
                {
                    commandRemoveAnimationSlots(data, formattedData, newFile);
                    break;
                }
                case "AnimationData+": 
                {
                    commandAddAnimationData(data, formattedData, newFile);
                    break;
                }
                default:
                {
                    System.out.println("There was an Error Reading the Command");
                    break;
                }
            }
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("There was an Error Opening the Output File");
        }
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to add given shapes amount
     */
    private static void commandAddShapes(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to remove given shapes amount
     */
    private static void commandRemoveShapes(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to add given polygon amount
     */
    private static void commandAddPolygons(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to remove given polygon amount
     */
    private static void commandRemovePolygons(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to add given Vertex Position amount
     */
    private static void commandAddVertexPositions(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to remove given Vertex Position amount
     */
    private static void commandRemoveVertexPositions(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to add given Vertex Position Indices amount
     */
    private static void commandAddVertexPositionIndices(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to remove given Vertex Position Indices amount
     */
    private static void commandRemoveVertexPositionIndices(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to add given Vertex Normal Indices amount
     */
    private static void commandAddVertexNormalIndices(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to remove given Vertex Normal Indices amount
     */
    private static void commandRemoveVertexNormalIndices(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to add given Vertex Colors amount
     */
    private static void commandAddVertexColors(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to remove given Vertex Colors amount
     */
    private static void commandRemoveVertexColors(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to add given Vertex Color Indices amount
     */
    private static void commandAddVertexColorIndices(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to remove given Vertex Color Indices amount
     */
    private static void commandRemoveVertexColorIndices(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to add given Texture Coordinate 0 Indices amount
     */
    private static void commandAddTextureCoordinate0Indices(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to remove given Texture Coordinate 0 Indices amount
     */
    private static void commandRemoveTextureCoordinate0Indices(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to add given Vertex Texture Coordinates amount
     */
    private static void commandAddVertexTextureCoordinates(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to remove given Vertex Texture Coordinates amount
     */
    private static void commandRemoveVertexTextureCoordinates(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to add given Texture Coordinates Transforms amount
     */
    private static void commandAddTextureCoordinateTransforms(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to remove given Texture Coordinates Transforms amount
     */
    private static void commandRemoveTextureCoordinateTransforms(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to add given Samplers amount
     */
    private static void commandAddSamplers(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to remove given Samplers amount
     */
    private static void commandRemoveSamplers(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to add given Textures amount
     */
    private static void commandAddTextures(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to remove given Textures amount
     */
    private static void commandRemoveTextures(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to add given Subshapes amount
     */
    private static void commandAddSubshapes(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to remove given Subshapes amount
     */
    private static void commandRemoveSubshapes(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to add given Visibility Groups amount
     */
    private static void commandAddVisibilityGroups(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to remove given Visibility Groups amount
     */
    private static void commandRemoveVisibilityGroups(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to add given GroupTransformData amount
     */
    private static void commandAddGroupTransformData(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to remove given GroupTransformData amount
     */
    private static void commandRemoveGroupTransformData(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to add given Groups amount
     */
    private static void commandAddGroups(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to remove given Groups amount
     */
    private static void commandRemoveGroups(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to add given Animation Slots amount
     */
    private static void commandAddAnimationSlots(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to remove given Animation Slots amount
     */
    private static void commandRemoveAnimationSlots(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to add given Animation Data amount
     */
    private static void commandAddAnimationData(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to write an empty shape to the new file
     */
    private static void AddEmptyShape(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        try
        {
            //Name
            newFile.writeBytes("EMPTYSHAPE");
            for(int j = 0; j < 54; j++) {newFile.write(0);}

            //Data
            for(int j = 0; j < 104; j++) {newFile.write(0);}
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("There was an Error Opening the Output File");
        }
        catch (IOException e) 
        {
            System.out.println("There was an Error Opening the Output File");
        }
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to write an empty polygon to the new file
     */
    private static void AddEmptyPolygon(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to write an empty Vertex Position to the new file
     */
    private static void AddEmptyVertexPosition(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to write an Vertex Position Indices to the new file
     */
    private static void AddEmptyVertexPositionIndices(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to write an Vertex Normal Indices to the new file
     */
    private static void AddEmptyVertexNormalIndices(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to write an Vertex Color to the new file
     */
    private static void AddEmptyVertexColor(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to write an Vertex Color Indices to the new file
     */
    private static void AddEmptyVertexColorIndices(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to write an Texture Coordinate 0 Indices to the new file
     */
    private static void AddEmptyTextureCoordinate0Indices(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to write an Vertex Texture Coordinates to the new file
     */
    private static void AddEmptyVertexTextureCoordinates(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to write an Texture Coordinates Transform to the new file
     */
    private static void AddEmptyTextureCoordinateTransform(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to write an Sampler to the new file
     */
    private static void AddEmptySampler(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to write an Texture to the new file
     */
    private static void AddEmptyTexture(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to write an Subshape to the new file
     */
    private static void AddEmptySubshape(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to write an Visibility Group to the new file
     */
    private static void AddEmptyVisibilityGroup(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to write an GroupTransformData to the new file
     */
    private static void AddEmptyGroupTransformData(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to write an Group to the new file
     */
    private static void AddEmptyGroup(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to write an Animation Slot to the new file
     */
    private static void AddEmptyAnimationSlot(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will attempt to write an Animaiton Data to the new file
     */
    private static void AddEmptyAnimationData(byte[] data, String[] formattedData, RandomAccessFile newFile)
    {
        //TODO
    }

    /**
     * @Author Jemaroo
     * @Function Will correct the format of the string of the byte
     */
    private static String formatByteString(String data)
    {
        if(data.equals("0"))
        {
            return "00";
        }
        else if(Integer.parseInt(data, 16) < 16)
        {
            return ("0" + data);
        }
        else
        {
            return data;
        }
    }
}