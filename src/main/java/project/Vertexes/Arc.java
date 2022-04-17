package project.Vertexes;

/**
 * Arc interface
 *
 * @author Ethan Hammond
 * @version 1.0
 */

public interface Arc extends Comparable<Arc> {
    public String getVertex();

    public int strength();

}
