package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReaderTest {
  File file;
  Reader reader;

  @BeforeEach
  void setUp() throws FileNotFoundException {
    file = new File("VaxData/100VaxTweets.txt");
    reader = new Reader(file);
  }

  @Test
  void nextLine(){
    String line = reader.nextLine();
    System.out.println(line);
    assert (line.equals("1447357947466506244\t@VishnuFNO\tRT @djlange: Millions across the world are now protesting against the introduction of a Vax digital ID passport app.  If you don't understa…"));
  }

  @Test
  void nextLong(){
    long l = 1447357947466506244L;
    assert (reader.nextLong() == l);
  }

  @Test
  void next(){
    String line;
    while((line = reader.next()) != null){
      System.out.println(line);
    }
  }

}