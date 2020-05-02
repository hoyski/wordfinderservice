package com.hoyski.wordfinderservice.domain;

public class FindWordsRequest
{
  private String characters;
  private int    minimumWordLength;
  private String pattern;
  private int    indexOfFirst;
  private int    maxToReturn;

  public String getCharacters()
  {
    return characters;
  }

  /**
   * The characters from which to make words
   * 
   * @param characters
   */
  public void setCharacters(String characters)
  {
    this.characters = characters;
  }

  /**
   * The minimum length of words to return
   * 
   * @return
   */
  public int getMinimumWordLength()
  {
    return minimumWordLength;
  }

  public void setMinimumWordLength(int minimumWordLength)
  {
    this.minimumWordLength = minimumWordLength;
  }

  /**
   * Pattern for words to match. Must be comprised of letters in
   * {@code characters} or underscores. An underscore matches any character.
   * 
   * @return
   */
  public String getPattern()
  {
    return pattern;
  }

  public void setPattern(String pattern)
  {
    this.pattern = pattern;
  }

  public int getIndexOfFirst()
  {
    return indexOfFirst;
  }

  /**
   * Zero-based index of the first word to return. All words prior are omitted
   * from the returned list of words.
   * 
   * @param indexOfFirst
   */
  public void setIndexOfFirst(int indexOfFirst)
  {
    this.indexOfFirst = indexOfFirst;
  }

  public int getMaxToReturn()
  {
    return maxToReturn;
  }

  /**
   * The maximum number of words to return
   * 
   * @param maxToReturn
   */
  public void setMaxToReturn(int maxToReturn)
  {
    this.maxToReturn = maxToReturn;
  }
}
