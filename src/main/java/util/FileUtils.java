package util;

import util.StringUtils;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.io.IOException;
import java.lang.OutOfMemoryError;
import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

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

  /**
    Creates a Document object with the fields given and returns it

    @param fields: Array of Field objects which need to be added to the document
    @return: Document with fields given
  */
  // private static Document initDocument(Field[] fields)
  // {
  //   Document doc = new Document();
  //
  //   for(Field field : fields)
  //     doc.add(field);
  //
  //   return doc;
  // }

  // public static ArrayList<Document> readAllDocuments(String filepath, String[] fieldNames, String docDelimiter)
  // {
	// 	ArrayList<Document> documents = new ArrayList<Document>();
  //
  //   return null;
  // }

}
