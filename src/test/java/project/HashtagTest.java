package test.java.project;

import org.junit.jupiter.api.Test;
import project.Graphs.HashtagGraph;
import project.Vertexes.Hashtag;
import project.Vertexes.Vertex;

import java.io.FileNotFoundException;
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
    public void testHGraph() throws FileNotFoundException {
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
      public void testInvert() throws FileNotFoundException {
        HashtagGraph graph = new HashtagGraph();
        HashMap<Vertex, ArrayList<Hashtag>> graph2= graph.invert();
        for (Map.Entry<Vertex, ArrayList<Hashtag>> entry : graph2.entrySet()) {
          System.out.print(entry.getKey().getName() + " [" + entry.getValue().size() + "]");
          if (entry.getValue().size() > 3) {
            System.out.print("{");
            for (int i = 0; i < 3; i++) {
              System.out.print(entry.getValue().get(i).getName() + ", ");
            }
            System.out.println("}");
          }
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
}
