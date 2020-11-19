package util;

import util.StringUtils;

import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

//TODO: change access modifiers globally
//TODO: all function specs
//TODO: constants
public class DocumentUtils
{

  public static ArrayList<Document> generateDocsFromFiles(ArrayList<String> filesContent, String[] fieldNames, String[] fieldDelims)
  {
    ArrayList<Document> docs = new ArrayList<Document>();

    for (String fileContent : filesContent)
      docs.add(generateDocFromFile(fileContent, fieldNames, fieldDelims));

    return docs;
  }

  private static Document generateDocFromFile(String fileContent, String[] fieldNames, String[] fieldDelims)
  {
    String[] fields = new String[fieldDelims.length];

    int i;
    for(i = 0; i < fields.length - 1; i++)
      fields[i] = StringUtils.getContentBetween(fileContent, fieldDelims[i], fieldDelims[i+1]);

    fields[i] = StringUtils.getContentAfter(fileContent, fieldDelims[fieldDelims.length - 1]);

    return initDoc(fieldNames, fields);
  }

  private static Document initDoc(String[] fieldNames, String[] fields)
  {
    Document doc = new Document();

    for(int i = 0; i < fieldNames.length; i++)
      doc.add(new TextField(fieldNames[i], fields[i], Field.Store.YES));

    return doc;
  }

}
