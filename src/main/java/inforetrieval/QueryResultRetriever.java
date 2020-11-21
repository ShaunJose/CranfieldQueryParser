package inforetrieval;

import util.StringUtils;

import java.util.ArrayList;
import java.nio.file.Paths;
import java.io.PrintWriter;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;

public class QueryResultRetriever
{

  /**
    Runs queries through document indexes stored in the index Directory, and generates a text file containing the results per query with the ranking and score of the document relevance for the query.

    @param queries: Arraylist of preprocessed queries
    @param indexDir: directory where the indexes are stored
    @param maxResults: Maximum number of results to display per query
    @param resultsFilepath: File to store the results in
    @return: None
  */
  public static void generateResultsOfQueries(ArrayList<String> queries, String indexDir, int maxResults, String resultsFilepath) throws IOException, ParseException
  {
    Analyzer analyzer = new StandardAnalyzer(); //SimpleAnalyzer, EnglishAnalyzer
    QueryParser parser = new QueryParser("content", analyzer);

    // Create objects to read and search across the index generated previously
    Directory directory = FSDirectory.open(Paths.get(indexDir));
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);

    // Create a filewriter to write the results in the results file
    PrintWriter fileWriter = new PrintWriter(resultsFilepath, "UTF-8");
    String gap = "     ";

    // Preprocess query and get matching results from the index
    for(int queryIndex = 0; queryIndex < queries.size(); queryIndex++)
    {
      String queryId = "" + (queryIndex + 1);
      String queryStr = StringUtils.cleanup(queries.get(queryIndex));

      if (queryStr.length() > 0)
      {
        // Parse query
        Query query = parser.parse(queryStr);

        // Number of matches
        ScoreDoc[] hits = isearcher.search(query, maxResults).scoreDocs;

        // Write results of each document for each query: "queryId, Q0, documentId, rank, score, STANDARD" to results file
        for (int j = 0; j < hits.length; j++)
        {
          Document hitDoc = isearcher.doc(hits[j].doc);
          String docRank = "" + (j + 1);
          String line = queryId + gap + "Q0" + gap + hitDoc.get("id") + gap + docRank + gap + hits[j].score + gap + "STANDARD";
          fileWriter.println(line);
        }
      }
    }

    //close reader, directory and writer
    ireader.close();
    directory.close();
    fileWriter.close();

  }

}
