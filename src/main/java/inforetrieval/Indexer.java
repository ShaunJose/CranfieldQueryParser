package inforetrieval;

import java.util.ArrayList;
import java.nio.file.Paths;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;

public class Indexer
{

  public static IndexWriter initIndexWithDocuments(ArrayList<Document> docs, String indexDir) throws IOException
  {
    Analyzer analyzer = new EnglishAnalyzer();

    Directory directory = FSDirectory.open(Paths.get(indexDir));

    IndexWriterConfig config = new IndexWriterConfig();
    config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
    IndexWriter iwriter = new IndexWriter(directory, config);

		iwriter.addDocuments(docs);

		iwriter.close();
		directory.close();

    return iwriter;
  }

}
