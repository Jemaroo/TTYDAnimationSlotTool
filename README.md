# TTYDAnimationSlotTool
A tool to modify the amount of various slots an animation file has

NOTICE (11/20/23): An expansion is planned allowing to increase and decrease the amounts of other fields such as textures or groups.

UPDATED (10/20/23): Full Release Version

Huge thanks to PistonMiner for documentation and tools on TTYD's animation formats https://github.com/PistonMiner/ttyd-tools

============USAGE============

Requirements: An updated version of Java and a text editor such as Notepad++

1: Download the Latest Release

2: Should Contain: Run.bat and TTYDAnimationSlotTool.jar.

3: Create an "input" and an "output" folder in the root of the folder.

4: Paste the character animation file in the input folder.

5: Edit Run.bat in a text editor and change the following fields:
  - INPUT FILE DESTINATION (ie input\\a_mario)
  - COMMAND TYPE:
              - Shapes+
              - Shapes-
              - Polygons+
              - Polygons-
              - VertexPositions+
              - VertexPositions-
              - VertexPositionIndices+
              - VertexPositionIndices-
              - VertexNormalIndices+
              - VertexNormalIndices-
              - VertexColors+
              - VertexColors-
              - VertexColorIndices+
              - VertexColorIndices-
              - VertexTextureCoordinate0Indices+
              - VertexTextureCoordinate0Indices-
              - VertexTextureCoordinates+
              - VertexTextureCoordinates-
              - TextureCoordinateTransforms+
              - TextureCoordinateTransforms-
              - Samplers+
              - Samplers-
              - Textures+
              - Textures-
              - Subshapes+
              - Subshapes-
              - VisibilityGroups+
              - VisibilityGroups-
              - GroupTransformData+
              - GroupTransformData-
              - Groups+
              - Groups-
              - AnimationSlots+
              - AnimationSlots-
              - AnimationData+
              - AnimationData-
  - COMMAND MODIFIER (ie 5)
  - OUTPUT FILE DESTINATION (ie output)
It should look something like: input\\a_mario Groups+ 3 output
Note that these are case-sensitive.

6: Run Run.bat

7: The new modified file should be in the output folder.

=========V1.0 USAGE==========

Requirements: An updated version of Java and a text editor such as Notepad++

1: Download the Latest Release

2: Should Contain: Run.bat and TTYDAnimationSlotTool.jar.

3: Create an "input" and an "output" folder in the root of the folder.

4: Paste ONLY the character animation file in the input folder.

5: Edit Run.bat in a text editor and change the following fields:
  - INPUT FILE DESTINATION (ie input\\a_mario)
  - ANIMATION SLOT COUNT (ie 5)
  - OUTPUT FILE DESTINATION (ie output)

6: Run Run.bat

7: The new modified file should be in the output folder.
