package com.hoyski.wordfinderservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hoyski.wordfinder.FoundWords;
import com.hoyski.wordfinder.WordFinder;
import com.hoyski.wordfinderservice.domain.FindWordsRequest;
import com.hoyski.wordfinderservice.domain.FindWordsResponse;

@RestController
public class WordFinderController
{
  private static final Logger log = LoggerFactory.getLogger(WordFinderController.class);

  private WordFinder          wordFinder;

  public WordFinderController()
  {
    wordFinder = new WordFinder();
  }

  @RequestMapping(value = "/findwords", method = RequestMethod.GET)
  public FindWordsResponse findWordsViaGet(@RequestParam String characters,
      @RequestParam(required = false, defaultValue = "3") Integer minNumChars,
      @RequestParam(required = false) String pattern,
      @RequestParam(required = false, defaultValue = "0") Integer indexOfFirst,
      @RequestParam(required = false, defaultValue = "1000") Integer maxToReturn)
  {
    log.debug("In findWordsViaGet: Finding words for characters {}", characters);

    long start = System.currentTimeMillis();

    if (minNumChars == null)
    {
      minNumChars = 0;
    }

    FoundWords        foundWords = wordFinder.findWords(characters, minNumChars, pattern,
        indexOfFirst, maxToReturn);

    long              duration   = System.currentTimeMillis() - start;

    FindWordsResponse response   = buildResponse(foundWords);

    log.debug("Found {} words in {} ms. Returning {} words", foundWords.getTotalMatches(), duration,
        foundWords.getFoundWords().size());

    return response;
  }

  @RequestMapping(value = "/findwords", method = RequestMethod.POST)
  public FindWordsResponse findWordsViaPost(@RequestBody FindWordsRequest findWordReq)
  {
    log.debug("Finding words for characters {}", findWordReq.getCharacters());

    FoundWords        foundWords = wordFinder.findWords(findWordReq.getCharacters(),
        findWordReq.getMinimumWordLength(), findWordReq.getPattern(), findWordReq.getIndexOfFirst(),
        findWordReq.getMaxToReturn());

    FindWordsResponse response   = buildResponse(foundWords);

    return response;
  }

  private FindWordsResponse buildResponse(FoundWords foundWords)
  {
    FindWordsResponse response = new FindWordsResponse();

    response.setWords(foundWords.getFoundWords());
    response.setIndexOfFirst(foundWords.getIndexOfFirst());
    response.setTotalFound(foundWords.getTotalMatches());

    return response;
  }
}
