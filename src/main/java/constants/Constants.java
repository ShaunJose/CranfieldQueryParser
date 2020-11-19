package constants;

public class Constants
{

  public static final String CRAN_FILEPATH = "data/cran/cran.all.1400";
  public static final String DOC_DELIM = ".I [0-9]+\n";
  public static final String[] FIELD_NAMES = {"title", "author", "background", "content"};
  public static final String[] FIELD_DELIMS = {".T\n", ".A\n", ".B\n", ".W\n"};
  public static final String INDEX_DIRECTORY = "index/";
  public static final String CRAN_QRY_FILEPATH = "data/cran/cran.qry";
  public static final String QUERY_DELIM = ".I [0-9]+\r\n.W\r\n";
  public static final int MAX_RESULTS_PER_QRY = 10;

}
