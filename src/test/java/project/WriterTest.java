package project;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.Graphs.TwitterGraph;
import project.IO.Writer;

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
    writer.writeTwitterGraphToFile(twitterGraph);
  }


}