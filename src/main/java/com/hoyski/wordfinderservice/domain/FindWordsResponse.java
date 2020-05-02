package com.hoyski.wordfinderservice.domain;

import java.util.List;

public class FindWordsResponse
{
  private int          indexOfFirst;
  private List<String> words;
  private int          totalFound;

  /**
   * Zero-based index of the first word in {@code words}. Zero if none were
   * skipped.
   * 
   * @return
   */
  public int getIndexOfFirst()
  {
    return indexOfFirst;
  }

  public void setIndexOfFirst(int indexOfFirst)
  {
    this.indexOfFirst = indexOfFirst;
  }

  public List<String> getWords()
  {
    return words;
  }

  public void setWords(List<String> words)
  {
    this.words = words;
  }

  public int getTotalFound()
  {
    return totalFound;
  }

  public void setTotalFound(int totalFound)
  {
    this.totalFound = totalFound;
  }
}
