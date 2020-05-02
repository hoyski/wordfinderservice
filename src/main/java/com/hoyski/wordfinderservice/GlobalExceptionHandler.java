package com.hoyski.wordfinderservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String handle(IllegalArgumentException e)
  {
    log.debug("Caught {} with msg {}", e.getClass().getName(), e.getMessage());

    return e.getMessage();
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public String handle(Exception e)
  {
    log.error("Caught {} with msg {}", e.getClass().getName(), e.getMessage());

    return e.getMessage();
  }

}
