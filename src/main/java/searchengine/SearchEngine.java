package searchengine;

import util.FileUtils;
import util.DocumentUtils;
import constants.Constants;
import inforetrieval.Indexer;
import inforetrieval.QueryResultRetriever;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.ParseException;

public class SearchEngine
{
  /**
    Gets Cranfield documents, indexes it etc TODO
  */
  public static void main(String[] args) throws IOException, ParseException
  {
    ArrayList<String> filesContent = FileUtils.getSeparateFileContents(Constants.CRAN_FILEPATH, Constants.DOC_DELIM);

    ArrayList<Document> docs = DocumentUtils.generateDocsFromFiles(filesContent, Constants.FIELD_NAMES, Constants.FIELD_DELIMS);

    IndexWriter iwriter = Indexer.initIndexWithDocuments(docs, Constants.INDEX_DIRECTORY);

    ArrayList<String> queries = FileUtils.getSeparateFileContents(Constants.CRAN_QRY_FILEPATH, Constants.QUERY_DELIM);

    QueryResultRetriever.generateResultsOfQueries(queries, Constants.INDEX_DIRECTORY, Constants.MAX_RESULTS_PER_QRY);
  }
}
