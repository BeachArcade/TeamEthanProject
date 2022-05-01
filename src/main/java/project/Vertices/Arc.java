package project.Vertices;

/**
 * Arc interface
 *
 * @author Ethan Hammond
 * @version 1.0
 */

public interface Arc extends Comparable<Arc> {

  String getVertex();

  int strength();

}
