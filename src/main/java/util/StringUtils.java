package util;

import java.util.ArrayList;
import java.util.Arrays;

class StringUtils
{

  /**
    Splits string with delimiter and returns an arraylist of the splits.

    @param str: String to be split
    @param delim: Delimiter to split string by
    @return: ArrayList of split up strings
  */
  public static ArrayList<String> splitWithDelimiter(String str, String delim)
  {
    ArrayList<String> splits = new ArrayList<String>(Arrays.asList(str.split(delim)));

    String firstElem = splits.get(0);
    if(firstElem == null || firstElem.isEmpty())
      splits.remove(0);

    return splits;
  }

  /**
    Splits a string from the beginning of a substring to the ending, exclusive of the beginning and ending strings.

    @param str: String in which content has to be found
    @param beginStr: String after which content begins
    @param endStr: String before which content ends
    @return: Substring b/w beginStr and endStr
  */
  public static String getContentBetween(String str, String beginStr, String endStr)
  {
    if(!str.contains(beginStr) || !str.contains(endStr))
      return null;

    int start = str.indexOf(beginStr) + beginStr.length();
    int end = str.indexOf(endStr);

    return str.substring(start, end);
  }

}
