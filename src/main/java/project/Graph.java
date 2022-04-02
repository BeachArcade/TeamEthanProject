package project;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Graph interface
 *
 * @author Ethan Hammond
 * @version 1.0
 */

public interface Graph {
    //TODO: What to return? map or vector
    public void getVertex(Vertex user);

    public List<Arc> getEdges(Vertex user);

    public void invert();

    public void add(Vertex user, List<Arc> retweets);

    public void remove(Vertex user);

    Map<Vertex, List<Arc>> adjVertices = new HashMap<Vertex, List<Arc>>();


}
