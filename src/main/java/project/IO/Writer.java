package project.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import project.vertices.Hashtag;
import project.vertices.TweetArc;
import project.vertices.Vertex;

import project.graphs.TwitterGraph;
import project.graphs.HashtagGraph;

public class Writer {

  BufferedWriter bufferedWriter;

  public Writer(File file) throws IOException {
    bufferedWriter = new BufferedWriter(new FileWriter(file));
  }

  public void writeTwitterGraphToFile(TwitterGraph twitterGraph) throws IOException {
    if (!twitterGraph.getDirection()) {
      twitterGraph.invert();
    }
    for (Map.Entry<Vertex, List<TweetArc>> entry : twitterGraph.getAdjVertices().entrySet()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(entry.getKey().getData()).append("\t")
          .append(entry.getKey().getCalculatedStance()).append("\t{");
      for (TweetArc arc : entry.getValue()) {
        stringBuilder.append(arc.getVertex()).append(" ").append(arc.getStrength()).append(",");
      }
      if (stringBuilder.charAt(stringBuilder.length() - 1) == ',') {
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
      }
      stringBuilder.append("}\n");
      bufferedWriter.append(stringBuilder.toString());
    }
  }

  public void writeHashtagGraphToFile(HashtagGraph hashtagGraph) throws IOException {

    // TODO: Set writer to hashtag graph format

    for (Map.Entry<Hashtag, ArrayList<Vertex>> entry : hashtagGraph.getHashtagGraph().entrySet()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(entry.getKey().getName()).append("\t");
      for (Vertex v : entry.getValue()) {
        stringBuilder.append(v.getName()).append(",");
      }
      if (stringBuilder.charAt(stringBuilder.length() - 1) == ',') {
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
      }
      stringBuilder.append("\n");
      bufferedWriter.append(stringBuilder.toString());
    }
  }

  public void writeInvert(HashMap<Vertex, ArrayList<Hashtag>> graph) throws IOException {
    for (Map.Entry<Vertex, ArrayList<Hashtag>> entry : graph.entrySet()) {
      String line = entry.getKey().getName() + " {";
      for (Hashtag tag : entry.getValue()) {
        line += tag.getName() + ", ";
      }
      bufferedWriter.append(line.substring(0, line.length() - 2) + "}");
    }
  }

  public void close() throws IOException {
    this.bufferedWriter.close();
  }

}
