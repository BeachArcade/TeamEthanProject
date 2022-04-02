package project;

import java.util.*;

public class TwitterGraph implements Graph{

    private HashMap<Vertex, List<Arc>> adjVertices = new HashMap <Vertex, List<Arc>>();

    @Override
    //Return all edges of a given vertex
    public List<Arc> getEdges(Vertex user) {
        return adjVertices.get(user);
    }

    @Override
    //Make vertexes for all the
    public void invert() {

    }

    @Override
    //Maps a user with its retweets
    public void add(Vertex user, List<Arc> retweets) {
        adjVertices.put(user, retweets);
    }
    //Overloaded method to map a single retweet
    public void add(Vertex user, Arc retweet){
       if(adjVertices.containsKey(user))
           adjVertices.get(user).add(retweet);
       else{
           ArrayList<Arc> list= new ArrayList<Arc>();
           list.add(retweet);
           adjVertices.put(user, list);
       }
    }

    @Override
    public void remove(Vertex user) {
        adjVertices.remove(user);

    }


    @Override
    public void getVertex(Vertex user){

    }

    public String toString(){
        String str = "";
        for(Map.Entry entry: adjVertices.entrySet()){
            str += entry.getKey() + ": ";
            for(Arc arc :(List<Arc>)entry.getValue()){
                str += arc.toString() + ",";
            }
            str += "\n";
        }
        return str;

    }
}
