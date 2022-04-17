package project.Graphs;

import project.IO.Reader;
import project.Vertexes.Hashtag;
import project.Vertexes.TweetArc;
import project.Vertexes.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HashtagGraph {
  private HashMap<Hashtag, ArrayList<Vertex>> hashtagGraph;
  private ArrayList<Hashtag> hashtags = new ArrayList<>();
  private boolean direction = true;


  public HashtagGraph() throws FileNotFoundException {
    Reader reader = new Reader(new File("VaxData/vax tweets"));
    for (Hashtag tag : hashtags) {
      ArrayList<Vertex> users = reader.getHashtags(tag);
      hashtagGraph.put(tag, users);
    }
  }

  public void invert() {
    ArrayList<Hashtag> tags = new ArrayList<>();
    ArrayList<Vertex> users = new ArrayList<>();
    HashMap<Vertex, ArrayList<Hashtag>> invertGraph = new HashMap<>();
    // Add all users and hashtags to the arrays
    for(Map.Entry<Hashtag, ArrayList<Vertex>> entry: hashtagGraph.entrySet()){
      // Add tag
      if(!tags.contains(entry.getKey()))
        tags.add(entry.getKey());
      // Add user
      for(Vertex v : entry.getValue()){
        if(!users.contains(v))
          users.add(v);
      }
    }
    // add users to the inverted map
    for(Vertex v: users){
      invertGraph.put(v, new ArrayList<>());
      //Add all hashtags to the list
      ArrayList<Hashtag> newTags = new ArrayList<>();
      for(Hashtag h : tags){
        for(Vertex otherV : hashtagGraph.get(h)){
          if(v.compareTo(otherV) == 0){
            if(!newTags.contains(h))
              newTags.add(h);
          }
        }
      }

    }



  }

  public void add(Hashtag user, List<?> retweets) {}

  public void remove(Hashtag user) {}
}
