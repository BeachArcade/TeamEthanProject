package project;

import org.w3c.dom.Node;

import java.util.ArrayList;

public interface Graph {
    public void getNode();
    public void getEdges();
    public void invert();
    private ArrayList<Node> children;


}
