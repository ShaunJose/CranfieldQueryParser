package util;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.io.IOException;
import java.lang.OutOfMemoryError;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

public class FileUtils
{

  public static ArrayList<String> readFieldsFromFile(String filepath, String[] stringFieldNames, String[] textFieldNames, String docDelimiter) throws IOException
  {
    String filesContents = readAll(filepath);

    ArrayList<String> docContents = new ArrayList<String>(Arrays.asList(filesContents.split(docDelimiter)));

    docContents.remove(0);

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
  private static Document initDocument(Field[] fields)
  {
    Document doc = new Document();

    for(Field field : fields)
      doc.add(field);

    return doc;
  }

  // public static ArrayList<Document> readAllDocuments(String filepath, String[] stringFieldNames, String[] textFieldNames, String docDelimiter)
  // {
	// 	ArrayList<Document> documents = new ArrayList<Document>();
  //
  //   return null;
  // }

}
