package project;

import java.util.ArrayList;
import java.util.HashMap;

public class TwitterGraph implements Graph{
    //Node class
    private class Node{
        String data;
        ArrayList<String> children = new ArrayList<String>()
    }
    private class edge{
        int number;
        int direction;
    }

}
