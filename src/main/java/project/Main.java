package project;

import project.Graphs.HashtagGraph;
import project.IO.Writer;

import java.io.File;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
      HashtagGraph graph;
      makeNewFile("VaxData/hashtagGraph.txt");
      makeNewFile("VaxData/invertedHashtagGraph.txt");
      System.out.println("Hashtag graph!");
    if (args.length != 0) graph = new HashtagGraph(args[0]);
    else graph = new HashtagGraph();
    System.out.println("Graph written to VaxData/hashtagGraph.txt");
    Writer writer = new Writer(new File("VaxData/invertedHashtagGraph.txt"));
    writer.writeInvert(graph.invert());
    System.out.println("Inverted graph to VaxData/invertedHashtagGraph.txt");
  }
  private static void makeNewFile(String str){
      File file = new File(str);
      boolean succ;
      try{
          succ = file.createNewFile();
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
}
