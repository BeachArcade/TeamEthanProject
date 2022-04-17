package project.Graphs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import project.Vertexes.Arc;
import project.Vertexes.TweetArc;
import project.Vertexes.Vertex;

/**
 * Graph interface
 *
 * @author Ethan Hammond
 * @version 1.0
 */

public interface Graph {

  Map<Vertex, List<Arc>> adjVertices = new HashMap<Vertex, List<Arc>>();

  //TODO: What to return? map or vector
  Vertex getVertex(Vertex user);

  List<TweetArc> getEdges(Vertex user);

  void invert();

  void add(Vertex user, List<?> retweets);

  void remove(Vertex user);


}
