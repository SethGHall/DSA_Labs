package ex6_1;

/**
   An interface that represents a path in a graph which holds 
   elements of type E between two vertices (with distinct edges)
   @see GraphADT.java
*/
import java.util.Iterator;

public interface Path<E>
{  
   // returns the vertex at the start of this path
   public Vertex<E> getStartVertex();
   
   // returns the vertex at the end of this path
   public Vertex<E> getEndVertex();
   
   // returns an iterator of the edges in order from the start vertex
   public Iterator<Edge<E>> iterator();
   
   // returns the number of edges in this path
   public int length();
   
   // appends specified edge to the start of the path if possible
   public boolean appendStart(Edge<E> edge);
   
   // appends specified edge to the end of the path if possible
   public boolean appendEnd(Edge<E> edge);
   
   // removes (and returns) the edge at the start of the path or null
   public Edge<E> removeStart();
   
   // removes (and returns) the edge at the end of the path or null
   public Edge<E> removeEnd(); 
   
   // returns true if specified vertex is in the path
   public boolean containsVertex(Vertex<E> vertex);
   
   // returns true if specified edge is in the path
   public boolean containsEdge(Edge<E> edge);
}
