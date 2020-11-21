package util;

import util.StringUtils;

import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

public class DocumentUtils
{

  /**
    Uses files' contents to generate and return an arraylist of documents.

    @param filesContent: ArrayList of contents of the files
    @param fieldNames: Array of names of the fields in the files' contents
    @param fieldDelims: Delimiters of fields in the files' contents
    @return: ArrayList of Documents with fields given from files' contents given
  */
  public static ArrayList<Document> generateDocsFromFiles(ArrayList<String> filesContent, String[] fieldNames, String[] fieldDelims)
  {
    ArrayList<Document> docs = new ArrayList<Document>();

    for(int i = 0; i < filesContent.size(); i++)
    {
      String id = "" + (i + 1);
      docs.add(generateDocFromFile(filesContent.get(i), fieldNames, fieldDelims, id));
    }

    return docs;
  }

  /**
    Uses file's contents to generate and return the document.

    @param fileContent: Contents of the files
    @param fieldNames: Array of names of the fields in the file's contents
    @param fieldDelims: Delimiters of fields in the file's contents
    @return: ArrayList of Documents with fields given from file's contents given
  */
  private static Document generateDocFromFile(String fileContent, String[] fieldNames, String[] fieldDelims, String id)
  {
    String[] fields = new String[fieldNames.length];
    fields[0] = id;

    int i;
    for(i = 1; i < fields.length - 1; i++)
      fields[i] = StringUtils.getContentBetween(fileContent, fieldDelims[i], fieldDelims[i+1]);

    fields[i] = StringUtils.getContentAfter(fileContent, fieldDelims[fieldDelims.length - 1]);

    return initDoc(fieldNames, fields);
  }

  /**
    Craetes a document with fieldNames and fields.

    @param fieldNames: names of the fields the document should have
    @param fields: Content of the fields the document should have
    @return: Document object with fieldNames and fields given (as TextFields)
  */
  private static Document initDoc(String[] fieldNames, String[] fields)
  {
    Document doc = new Document();

    for(int i = 0; i < fieldNames.length; i++)
      doc.add(new TextField(fieldNames[i], fields[i], Field.Store.YES));

    return doc;
  }

}
