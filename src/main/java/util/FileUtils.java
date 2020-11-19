package util;

import util.StringUtils;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.io.IOException;
import java.lang.OutOfMemoryError;
import java.util.ArrayList;

public class FileUtils
{

  /**
    Returns the filecontents of the given filepath, split with the delimiter given, as an ArrayList<String>

    @param filepath: path of the file to be read
    @param docDelimiter: delimiter to split file by
    @return: ArrayList with the split fileContents, if file exists
  */
  public static ArrayList<String> getSeparateFileContents(String filepath, String docDelimiter) throws IOException
  {
    String filesContents = readAll(filepath);

    ArrayList<String> docContents = StringUtils.splitWithDelimiter(filesContents, docDelimiter);

    return docContents;
  }

  /**
    Reads all contents from a file and returns it, if the file exists.

    @param path: filepath of the file to be read
    @return: String with filecontents, if the file exists
  */
  private static String readAll(String path) throws InvalidPathException, IOException, OutOfMemoryError, SecurityException
  {
    return new String(Files.readAllBytes(Paths.get(path)));
  }

}
