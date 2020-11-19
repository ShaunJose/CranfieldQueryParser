package searchengine;

import util.FileUtils;

import java.io.IOException;
import java.util.ArrayList;

public class SearchEngine
{
  public static void main(String[] args) throws IOException
  {
    ArrayList<String> contents = FileUtils.getSeparateFileContents("data/cran/cran.all.1400", ".I [0-9]+\n");

    for(int i = 0 ; i < contents.size(); i++)
    {
      System.out.println((i + 1) + ": " + contents.get(i));
    }
  }
}
