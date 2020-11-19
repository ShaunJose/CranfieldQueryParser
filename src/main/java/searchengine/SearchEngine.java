package searchengine;

import util.FileUtils;
import util.DocumentUtils;
import constants.Constants;
import indexer.Indexer;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;

public class SearchEngine
{
  /**
    Gets Cranfield documents, indexes it etc TODO
  */
  public static void main(String[] args) throws IOException
  {
    ArrayList<String> filesContent = FileUtils.getSeparateFileContents(Constants.CRAN_FILEPATH, Constants.DOC_DELIM);

    ArrayList<Document> docs = DocumentUtils.generateDocsFromFiles(filesContent, Constants.FIELD_NAMES, Constants.FIELD_DELIMS);

    IndexWriter iwriter = Indexer.initIndexWithDocuments(docs, Constants.INDEX_DIRECTORY);

    ArrayList<String> queries = FileUtils.getSeparateFileContents(Constants.CRAN_QRY_FILEPATH, Constants.QUERY_DELIM);
    //
    // for(int i = 0 ; i < filesContent.size(); i++)
    // {
    //   System.out.println((i + 1) + ": " + filesContent.get(i));
    // }

    for(int i = 0 ; i < 10; i++)
    {
      System.out.println((i + 1) + ": " + queries.get(i));
    }
    //
    // System.out.println(docs.size());
    //
    // System.out.println(docs.get(0).getField("title"));
    // System.out.println(docs.get(0).getField("author"));
    // System.out.println(docs.get(0).getField("background"));
    // System.out.println(docs.get(0).getField("content"));
    //
    // System.out.println(docs.get(10).getField("title"));
    // System.out.println(docs.get(10).getField("author"));
    // System.out.println(docs.get(10).getField("background"));
    // System.out.println(docs.get(10).getField("content"));
    //
    // System.out.println(docs.get(1399).getField("title"));
    // System.out.println(docs.get(1399).getField("author"));
    // System.out.println(docs.get(1399).getField("background"));
    // System.out.println(docs.get(1399).getField("content"));
    System.out.println(queries.size());
  }
}
