package project.IO;

import project.Graphs.TwitterGraph;
import project.Vertexes.TweetArc;
import project.Vertexes.Vertex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Writer {
  BufferedWriter bufferedWriter;

  public Writer(File file) throws IOException {
    bufferedWriter = new BufferedWriter(new FileWriter(file));
  }

  public void writeToFile(TwitterGraph twitterGraph) throws IOException {
    if (!twitterGraph.getDirection()){
      twitterGraph.invert();
    }
    for (Map.Entry<Vertex, List<TweetArc>> entry : twitterGraph.getAdjVertices().entrySet()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(entry.getKey().getData()).append("\t").append(entry.getKey().getCalculatedStance()).append("\t{");
      for (TweetArc arc : entry.getValue()) {
        stringBuilder.append(arc.getVertex()).append(" ").append(arc.getStrength()).append(",");
      }
      if (stringBuilder.charAt(stringBuilder.length()-1) == ','){
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
      }
      stringBuilder.append("}\n");
      bufferedWriter.append(stringBuilder.toString());
    }
  }

  public void close() throws IOException {
    this.bufferedWriter.close();
  }

}
