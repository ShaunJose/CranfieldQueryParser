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

  public static void generateResultsOfQueries(ArrayList<String> queries, String indexDir, int maxResults) throws IOException, ParseException
  {
    Analyzer analyzer = new SimpleAnalyzer();
    QueryParser parser = new QueryParser("content", analyzer);

    // create objects to read and search across the index
    Directory directory = FSDirectory.open(Paths.get(indexDir));
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);

    PrintWriter fileWriter = new PrintWriter("results.txt", "UTF-8");

    String gap = "     ";

    for(int queryIndex = 0; queryIndex < queries.size(); queryIndex++)
    {
      String queryId = "" + (queryIndex + 1);
      String queryStr = StringUtils.cleanup(queries.get(queryIndex));

      if (queryStr.length() > 0)
      {
        // parse the query with the parser
        Query query = parser.parse(queryStr);

        // Get the set of results
        ScoreDoc[] hits = isearcher.search(query, maxResults).scoreDocs;

        // Print the results
        for (int j = 0; j < hits.length; j++)
        {
          Document hitDoc = isearcher.doc(hits[j].doc);
          String line = queryId + gap + "Q0" + gap + hitDoc.get("id") + gap + (j+1) + gap + hits[j].score + gap + "STANDARD";
          fileWriter.println(line);
        }
      }
    }

    ireader.close();
    directory.close();
    fileWriter.close();

  }

}
