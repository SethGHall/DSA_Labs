package ex6_2;

/**
   A class that implements a path in a graph (which holds 
   elements of type E) between two vertices (with distinct edges)
   using a linked list as the data structure
   @author Andrew Ensor
*/
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedPath<E> implements Path<E>
{
   private Vertex<E> startVertex, endVertex;
   private List<Edge<E>> edges;
   
   // creates a new path with length 0 starting at specified vertex
   public LinkedPath(Vertex<E> startVertex)
   {  if (startVertex==null)
         throw new NullPointerException();
      this.startVertex = startVertex;
      this.endVertex = startVertex;
      this.edges = new LinkedList<Edge<E>>();
   }
   
   // returns the vertex at the start of this path
   public Vertex<E> getStartVertex()
   {  return startVertex;
   }
   
   // returns the vertex at the end of this path
   public Vertex<E> getEndVertex()
   {  return endVertex;
   }
   
   // returns an iterator of the edges in order from the start vertex
   public Iterator<Edge<E>> iterator()
   {  return Collections.unmodifiableCollection(edges).iterator();
   }
   
   // returns the number of edges in this path
   public int length()
   {  return edges.size();
   }
   
   // appends specified edge to the start of the path if possible
   public boolean appendStart(Edge<E> edge)
   {  Vertex<E>[] endVertices = edge.endVertices();
      if (edges.contains(edge))
         return false; // edge is already used in the path
      else if (startVertex.equals(endVertices[0]))
      {  edges.add(0, edge);
         startVertex = endVertices[1];
      }
      else if (startVertex.equals(endVertices[1]))
      {  edges.add(0, edge);
         startVertex = endVertices[0];
      }
      else
         return false; // edge was not incident to start vertex
      return true;
   }
   
   // appends specified edge to the end of the path if possible
   public boolean appendEnd(Edge<E> edge)
   {  Vertex<E>[] endVertices = edge.endVertices();
      if (edges.contains(edge))
         return false; // edge is already used in the path
      if (endVertex.equals(endVertices[0]))
      {  edges.add(edge);
         endVertex = endVertices[1];
      }
      else if (endVertex.equals(endVertices[1]))
      {  edges.add(edge);
         endVertex = endVertices[0];
      }
      else
         return false; // edge was not incident to end vertex
      return true;
   }
   
   // removes (and returns) the edge at the start of the path or null
   public Edge<E> removeStart()
   {  if (edges.size()==0)
         return null;
      else
      {  Edge<E> edge = edges.remove(0); // first edge in list
         endVertex = edge.oppositeVertex(endVertex);
         return edge;
      }
   }
   
   // removes (and returns) the edge at the end of the path or null
   public Edge<E> removeEnd()
   {  if (edges.size()==0)
         return null;
      else
      {  Edge<E> edge=edges.remove(edges.size()-1);//last edge in list
         startVertex = edge.oppositeVertex(startVertex);
         return edge;
      }
   }
   
   // returns true if specified vertex is in the path
   public boolean containsVertex(Vertex<E> vertex)
   {  for (Edge<E> edge : edges)
      {  Vertex<E>[] endVertices = edge.endVertices();
         if (vertex.equals(endVertices[0]) || 
            vertex.equals(endVertices[1]))
            return true;
      }
      return false;
   }
   
   // returns true if specified edge is in the path
   public boolean containsEdge(Edge<E> edge)
   {  return edges.contains(edge);
   }
   
   // returns a string representation of the path
   public String toString()
   {  String output = "Path from " + startVertex + " to "
         + endVertex + ": ";
      for (Edge<E> edge : edges)
         output += "" + edge;
      return output;
   }
   
   // returns whether this path has same edges as specified path
   public boolean equals(Path<E> path)
   {  Iterator<Edge<E>> iterator1 = edges.iterator();
      Iterator<Edge<E>> iterator2 = path.iterator();
      while(iterator1.hasNext() && iterator2.hasNext())
      {  if (!iterator1.next().equals(iterator2.next()))
            return false;
      }
      return !(iterator1.hasNext() || iterator2.hasNext());
   }
}
