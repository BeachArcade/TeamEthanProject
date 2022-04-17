package project.IO;

import java.io.File;
import java.io.IOException;
import project.Graphs.TwitterGraph;

public class GetGraph {

  public static void main(String[] args) throws IOException {
    System.out.println("Missing arguments: <option> <output filepath> <input filepath>");
    System.out.println("Choose an option");
    System.out.println("1.) Create new graph");
    System.out.println("2.) Create and invert new graph");
    System.out.println("3.) Load Graph");
    System.out.println("4.) Load and invert graph");

    if (args[0].equals("1")) {
      TwitterGraph twitterGraph = new TwitterGraph();
      Writer write = new Writer(new File(args[1]));
      write.writeTwitterGraphToFile(twitterGraph);
      write.close();
    } else if (args[0].equals("2")) {
      TwitterGraph twitterGraph = new TwitterGraph();
      twitterGraph.invert();
      Writer write = new Writer(new File(args[1]));
      write.writeTwitterGraphToFile(twitterGraph);

    } else if (args[0].equals("3")) {
      Writer writer = new Writer(new File(args[1]));
      Reader reader = new Reader(new File(args[2]));
      TwitterGraph twitterGraph = new TwitterGraph();
      reader.loadTwitterGraph(twitterGraph);
      writer.writeTwitterGraphToFile(twitterGraph);

    } else if (args[0].equals("4")) {
      Writer writer = new Writer(new File(args[1]));
      Reader reader = new Reader(new File(args[2]));
      TwitterGraph twitterGraph = new TwitterGraph();
      reader.loadTwitterGraph(twitterGraph);
      twitterGraph.invert();
      writer.writeTwitterGraphToFile(twitterGraph);
    }
  }
}