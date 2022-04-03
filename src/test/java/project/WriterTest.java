package test.java.project;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.TwitterGraph;
import project.Writer;

class WriterTest {
  Writer writer;

  @BeforeEach
  void setUp() throws IOException {
    writer = new Writer(new File("VaxData/Sprint3/Graphs/outputFile.txt"));
  }

  @AfterEach
  void tearDown() throws IOException {
    writer.close();
  }

  @Test
  void checkWriter() throws IOException {
    TwitterGraph twitterGraph = new TwitterGraph();
    writer.writeToFile(twitterGraph);
  }


}