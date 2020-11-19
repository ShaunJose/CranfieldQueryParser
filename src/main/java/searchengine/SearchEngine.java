package searchengine;

import util.FileUtils;
import util.DocumentUtils;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.document.Document;

public class SearchEngine
{
  /**
    Gets Cranfield documents, indexes it etc TODO
  */
  public static void main(String[] args) throws IOException
  {
    ArrayList<String> filesContent = FileUtils.getSeparateFileContents("data/cran/cran.all.1400", ".I [0-9]+\n");

    String[] fieldNames = {"title", "author", "background", "content"};
    String[] fieldDelimiters = {".T\n", ".A\n", ".B\n", ".W\n"};
    ArrayList<Document> docs = DocumentUtils.generateDocsFromFiles(filesContent, fieldNames, fieldDelimiters);

    for(int i = 0 ; i < filesContent.size(); i++)
    {
      System.out.println((i + 1) + ": " + filesContent.get(i));
    }

    System.out.println(docs.size());

    System.out.println(docs.get(0).getField("title"));
    System.out.println(docs.get(0).getField("author"));
    System.out.println(docs.get(0).getField("background"));
    System.out.println(docs.get(0).getField("content"));

    System.out.println(docs.get(10).getField("title"));
    System.out.println(docs.get(10).getField("author"));
    System.out.println(docs.get(10).getField("background"));
    System.out.println(docs.get(10).getField("content"));

    System.out.println(docs.get(1399).getField("title"));
    System.out.println(docs.get(1399).getField("author"));
    System.out.println(docs.get(1399).getField("background"));
    System.out.println(docs.get(1399).getField("content"));
  }
}
