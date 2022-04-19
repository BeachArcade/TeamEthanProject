package test.java.project;

import org.junit.jupiter.api.Test;
import project.Graphs.HashtagGraph;
import project.Vertexes.Hashtag;
import project.Vertexes.Vertex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HashtagTest {
    @Test
    public void testHashTag(){
        Hashtag h = new Hashtag("#VAX");
        System.out.println(h.getName());
    }
    @Test
    public void testHGraph() throws IOException {
        HashtagGraph graph = new HashtagGraph();
        for(Map.Entry<Hashtag, ArrayList<Vertex>> entry: graph.getHashtagGraph().entrySet()){
            System.out.print(entry.getKey().getName() + " [" + entry.getValue().size() + "]");
          if (entry.getValue().size() > 3) {
              System.out.println("{");
              for (int i = 0; i < 3; i++) {
                System.out.print(entry.getValue().get(i).getName() + ", ");
              }
            System.out.println("}");
          }
        }
    }

      @Test
      public void testInvert() throws IOException {
        HashtagGraph graph = new HashtagGraph();
          HashMap<Vertex,ArrayList<Hashtag>> inv = graph.invert();
          for (Map.Entry<Vertex, ArrayList<Hashtag>> entry : inv.entrySet()){
              System.out.print(entry.getKey().getName() + "{");
              for(Hashtag h : entry.getValue()){
                  System.out.print(h.getName() + ",");
              }
              System.out.println("} [" + entry.getValue().size() + "]");
          }
    }
    @Test
    public void testInvertFile() throws IOException {
        HashtagGraph graph = new HashtagGraph("VaxData/hashGraph.txt");
        HashMap<Vertex,ArrayList<Hashtag>> inv = graph.invert();
        for (Map.Entry<Vertex, ArrayList<Hashtag>> entry : inv.entrySet()){
            System.out.print(entry.getKey().getName() + "{");
            for(Hashtag h : entry.getValue()){
                System.out.print(h.getName() + ",");
            }
            System.out.println("} [" + entry.getValue().size() + "]");
        }
    }

    @Test
    public void testMisc(){
        ArrayList<Vertex> vertexes = new ArrayList<>();
        vertexes.add(new Vertex("Cereal"));
        vertexes.add(new Vertex("Cat"));
        vertexes.add(new Vertex("Cobra"));
        vertexes.add(new Vertex("Muffin"));
        vertexes.add(new Vertex("Spaghetti"));
        vertexes.add(new Vertex("Purple"));
        vertexes.add(new Vertex("Spelling"));
        vertexes.add(new Vertex("Noggin"));
        vertexes.add(new Vertex("Dinosaur"));
        System.out.println(vertexes.contains(new Vertex("Dinosaur")));
    }

    @Test
    public void testLoad() throws IOException {
        HashtagGraph graph = new HashtagGraph("VaxData/hashGraph.txt");
        for(Map.Entry<Hashtag, ArrayList<Vertex>> entry : graph.getHashtagGraph().entrySet()){
            System.out.println(entry.getKey().getName() + "\t\t\t[" + entry.getValue().size() + "]");
        }
    }
}
