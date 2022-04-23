package project;

import java.io.File;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.IO.Reader;

class ReaderTest {

  Reader tweetReader;
  Reader userReader;
  Reader graphReader;

  @BeforeEach
  void setUp() throws FileNotFoundException {
    File tweetsFile = new File("VaxData/100VaxTweets.txt");
    tweetReader = new Reader(tweetsFile);
    File usersFile = new File("VaxData/100VaxUsersTweets.txt");
    userReader = new Reader(usersFile);
    graphReader = new Reader(new File("VaxData/Sprint3/Graphs/outputFile.txt"));
  }

  @Test
  void nextLine() {
    String line = tweetReader.nextLine();
    System.out.println(line);
    assert (line.equals(
        "1447357947466506244\t@VishnuFNO\tRT @djlange: Millions across the world are now protesting against the introduction of a Vax digital ID passport app.  If you don't understa…"));
  }

  @Test
  void nextLong() {
    long l = 1447357947466506244L;
    assert (tweetReader.nextLong() == l);
  }

  @Test
  void next() {
    String line;
    while ((line = userReader.nextLine()) != null) {
      System.out.println(line.split("\t")[0]);
    }
    assert (true);
  }

  @Test
  void getUsersFromVaxTweets() {
    String line;
    while ((line = tweetReader.nextLine()) != null) {
      String user = line.split("\t")[1];
      String content = line.split("\t")[2];
      if (content.startsWith("RT")) {
        String retweededUser = content.split(":", 0)[0];
        String retweetedUserstweet = content.split(":")[1];
        retweededUser = retweededUser.substring(3);
        System.out.println(user + " " + retweededUser + " " + retweetedUserstweet);
      } else {
        System.out.println(user);
      }
    }
  }

  @Test
  public void GetHashtags() {
    String line = "1447357947466506244\t@VishnuFNO\tRT @djlange: #Millions across the world are now protesting against the introduction of a Vax digital ID passport app.  If you don't understa…";

    String[] content = line.split("\t")[2].split(":")[1].split(" ");
    for (String word : content) {
      if (word.startsWith("#")) {
        System.out.println(word);
      }
    }
  }

  @Test
  public void readLexicon() {
    String line = "0. covid [problem, ref:covid]";
    String tag = line.split(" ", 2)[1].split(" ",2)[0];
    String[] str = line.split(" ", 2)[1].split(" ",2)[1].replace("[", "").replace("]","").split(", ");
    for(String s: str){
      System.out.println(s);
    }
  }

}