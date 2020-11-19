package inforetrieval;

import java.util.ArrayList;
import java.nio.file.Paths;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
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

  public static void getResultsOfQueries(ArrayList<String> queries, String indexDir, int maxResults) throws IOException, ParseException
  {
    Analyzer analyzer = new StandardAnalyzer();
    QueryParser parser = new QueryParser("content", analyzer);

    // create objects to read and search across the index
    Directory directory = FSDirectory.open(Paths.get(indexDir));
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);

    for(int i = 0; i < queries.size(); i++)
    {
      String queryStr = queries.get(i).trim();
      System.out.println("Query " + (i+1) + ": " + queryStr);

      if (queryStr.length() > 0)
      {
        // parse the query with the parser
        Query query = parser.parse(queryStr);

        // Get the set of results
        ScoreDoc[] hits = isearcher.search(query, maxResults).scoreDocs;

        // Print the results
        System.out.println("Documents: " + hits.length);
        for (int j = 0; j < hits.length; j++)
        {
          Document hitDoc = isearcher.doc(hits[j].doc);
          System.out.println(j + ") " + hitDoc.get("title") + " " + hits[j].score);
        }
      }
    }

  }

}
