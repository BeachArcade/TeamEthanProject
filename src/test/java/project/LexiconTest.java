package project;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

class LexiconTest {

  @Test
  void testLexicon() throws FileNotFoundException {
    Lexicon lexicon = new Lexicon();
    System.out.println(lexicon);
  }
}