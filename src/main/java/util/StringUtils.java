package util;

import java.util.ArrayList;
import java.util.Arrays;

class StringUtils
{

  public static ArrayList<String> splitWithDelimiter(String str, String delim)
  {
    ArrayList<String> splits = new ArrayList<String>(Arrays.asList(str.split(delim)));

    String firstElem = splits.get(0);
    if(firstElem == null || firstElem.isEmpty())
      splits.remove(0);

    return splits;
  }

  public static String splitWithDelimiters(String str, String delim1, String delim2)
  {
    if(!str.contains(delim1) || !str.contains(delim2))
      return null;

    int start = str.indexOf(delim1) + delim1.length();
    int end = str.indexOf(delim2);

    return str.substring(start, end);
  }

}
