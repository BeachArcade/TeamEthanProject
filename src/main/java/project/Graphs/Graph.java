package project.graphs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.vertices.Arc;
import project.vertices.Vertex;

/**
 * Graph interface
 *
 * @author Ethan Hammond
 * @version 1.0
 */

public interface Graph<T> {
  Map<Vertex, List<Arc>> adjVertices = new HashMap<Vertex, List<Arc>>();

  Vertex getVertex(Vertex user);

  List<T> getEdges(Vertex user);

  void invert();

  void add(Vertex user, List<T> retweets);

  void remove(Vertex user);
}
