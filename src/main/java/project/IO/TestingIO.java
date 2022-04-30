package project.io;

import java.util.StringTokenizer;

/**
 * Placeholder IO Class
 */
public class TestingIO {

  // Find out if a tweet is retweeted or not;
  public boolean filterRT(String str) {
    StringTokenizer tokenizer = new StringTokenizer(str, "\t");
    tokenizer.nextToken();
    tokenizer.nextToken();
    return (tokenizer.nextToken().startsWith("RT "));

  }
}
