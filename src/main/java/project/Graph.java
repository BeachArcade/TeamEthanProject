package project;

import org.w3c.dom.Node;
import sun.security.provider.certpath.Vertex;

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
    //public void getNode();
    public void getEdges();
    public void invert();

    Map<Vertex, List<Arc>> adjVertices = new HashMap <Vertex, List<Arc>>();


}
