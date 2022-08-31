
package ex6_1;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * A Snazzy Graph for an adjacency matrix (can be used as directed graph)
 * eg: edge A->B is not the same as B -> A
 * @author sehall
 * @param <E>
 */
public class AdjacencyMatrixGraph<E>  implements GraphADT<E>
{
    private Map<Vertex<E>,Integer> vertices;
    private Edge[][] edgeMatrix;
    private final int INITIAL_CAP = 10;
    private int counter;
    
    public AdjacencyMatrixGraph()
    {
        vertices = new LinkedHashMap<>();
        counter = 0;
        edgeMatrix = new MatrixEdge[INITIAL_CAP][INITIAL_CAP]; 
    }
    
    
    public String toString()
    {   String s="";
        for(int i=0;i<edgeMatrix.length;i++)
       {
           for(int j=0;j<edgeMatrix[i].length;j++)
           {
                if(edgeMatrix[i][j] == null)
                   s+="[null-null]\t"; 
                else s+="["+edgeMatrix[i][j]+"]\t";
              
           }
           s+="\n";
       }
        return s;
    }
    
    @Override
   public void clear()
   {
       vertices.clear();
       edgeMatrix = new Edge[INITIAL_CAP][INITIAL_CAP];
   }
   // returns true if the graph has no vertices nor edges
    @Override
   public boolean isEmpty()
   {
       return (vertices.size()  == 0);
   }
   // returns a view of the vertices as a Set
    @Override
   public Set<Vertex<E>> vertexSet()
   {
       Set<Vertex<E>> vertexSet = Collections.unmodifiableSet(vertices.keySet());
       return vertexSet;
   }
   // returns a view of the edges as a Set
    @Override
   public Set<Edge<E>> edgeSet()
   {
       Set<Edge<E>> edgeSet = new LinkedHashSet<Edge<E>>();
       
       for(int i=0;i<edgeMatrix.length;i++)
       {
           for(int j=0;j<edgeMatrix[i].length;j++)
           {
               if(edgeMatrix[i][j] != null)
               {
                   edgeSet.add(edgeMatrix[i][j]);
               }
           }
       }
       return Collections.unmodifiableSet(edgeSet);
   }
   // method which adds the graph as a subgraph into this graph
    @Override
   public <F extends E> void addGraph(GraphADT<F> graph)
   {
       Set<Vertex<F>> vertexSet = graph.vertexSet();
       for(Vertex<F> vertex:vertexSet)
           addVertex(vertex.getUserObject());
       
       Set<Edge<F>> edgeSet = graph.edgeSet();
       for(Edge<F> edge:edgeSet)
       {   Vertex[] adjVertices = edge.endVertices();
           addEdge(adjVertices[0], adjVertices[1]);
       }
   }
   // adds and returns a new isolated vertex to the graph
    @Override
   public Vertex<E> addVertex(E element)
   {
       Vertex<E> newVertex = new MatrixVertex(element);
       if(!vertices.containsKey(newVertex))
       {
           vertices.put(newVertex, counter);
           counter++;
       }  
      
       return newVertex;
   }
   // adds and returns a new undirected edge between two vertices
    @Override
   public Edge<E> addEdge(Vertex<E> vertex0, Vertex<E> vertex1)
   {
       if(!vertices.containsKey(vertex0) || !vertices.containsKey(vertex1))
       {
           throw new IllegalArgumentException("One of these vertices not in the Graph");
       }
       else
       {
           int i = vertices.get(vertex0);
           int j = vertices.get(vertex1);
           Edge<E> edge = new MatrixEdge(vertex0, vertex1);
           if(edgeMatrix[i][j] == null)
           {
               edgeMatrix[i][j] = edge;
               edgeMatrix[j][i] = edge;
           }
           else throw new IllegalArgumentException("Edge already exists between v0 and v1");
           
           return edge;
       }
       
   }
   // removes the specified vertex from the graph
   public <F> boolean removeVertex(Vertex<F> vertex)
   {
       if(vertices.containsKey(vertex))
       {
           int counter = vertices.get(vertex);
           vertices.remove(vertex);
           
           for(int i=0;i<edgeMatrix.length;i++)
           {
               edgeMatrix[counter][i] = null;
               edgeMatrix[i][counter] = null;
           } 
           return true;
       }
       else return false;
   }
   // removes the specified undirected edge from the graph
    @Override
   public <F> boolean removeEdge(Edge<F> edge)
   {
       Vertex[] adjVertices = edge.endVertices();
       
       int i=vertices.get(adjVertices[0]);
       int j=vertices.get(adjVertices[1]);

       if(edgeMatrix[i][j] == null) 
           return false;
       edgeMatrix[i][j] = null;
       return true;
   }
   // returns whether the specified vertex is in the graph
    @Override
   public boolean containsVertex(Vertex<?> vertex)
   {
       return vertices.containsKey(vertex);
   }
   // returns whether the specified edge is in the graph
    @Override
   public boolean containsEdge(Edge<?> edge)
   {
       Vertex[] adjacentVertices = edge.endVertices();
       
       int i=vertices.get(adjacentVertices[0]);
       int j=vertices.get(adjacentVertices[1]);

       if(edgeMatrix[i][j] == null) 
           return false;
       else return true;
   }
   // inner class that implements a vertex for the AdjacencyListGraph
   private class MatrixVertex<E> implements Vertex<E>
   {
      private E element;
      
      public MatrixVertex(E element)
      {  this.element = element;
      }
      
      // returns the element held in the vertex
      public E getUserObject()
      {  return element;
      }
      
      // sets the element held in the vertex
      public void setUserObject(E element)
      {  this.element = element;
      }
      
      public boolean equals(Vertex<E> other)
      {
          return other.getUserObject().equals(element);
      }
      
      
      // returns the edges connecting with this vertex as a Set
      public Set<Edge<E>> incidentEdges()
      {  
          int count = vertices.get(this);
          Set<Edge<E>> edgeSet = new LinkedHashSet<>();
          for(int i=0;i<edgeMatrix.length;i++)
          {
               if(edgeMatrix[counter][i] != null)
                   edgeSet.add(edgeMatrix[counter][i]);
               
               if(edgeMatrix[i][counter] != null)
                   edgeSet.add(edgeMatrix[i][counter]);
               
          } 
          
          return edgeSet;//adjacencyLists.get(this);
      }
      
      // returns vertices that are adjacent to this vertex as a Set
      public Set<Vertex<E>> adjacentVertices()
      { 
         Set<Vertex<E>> adjVertexSet = new LinkedHashSet<>();
         
         Set<Edge<E>> edgeSet = incidentEdges();
         
         for(Edge<E> edge:edgeSet)
            adjVertexSet.add(edge.oppositeVertex(this));

         return adjVertexSet;
      }
      
      // returns whether specified vertex is adjacent to this vertex
      @Override
      public boolean isAdjacent(Vertex<?> vertex)
      {  return adjacentVertices().contains(vertex);
      }
      
      // overridden method which returns a hash code for this vertex
      public int hashCode()
      {  if (element==null)
            return 0;
         else
            return element.hashCode();
      }
      
      public String toString()
      {  return "" + element;
      }
   }

   // inner class that implements a vertex for the AdjacencyListGraph
   private class MatrixEdge<E> implements Edge<E>
   {
      private Vertex<E> vertex1, vertex2;
      
      public MatrixEdge(Vertex<E> vertex1, Vertex<E> vertex2)
      {  this.vertex1 = vertex1;
         this.vertex2 = vertex2;
      }
      
      // returns the two end vertices for this edge as an array
      public Vertex<E>[] endVertices()
      {  Vertex<E>[] vertices=(Vertex<E>[])(new Vertex[2]);//unchecked
         vertices[0] = vertex1;
         vertices[1] = vertex2;
         return vertices;
      }
      
      // returns the end vertex opposite the specified vertex
      public Vertex<E> oppositeVertex(Vertex<E> vertex)
      {  if (vertex1.equals(vertex))
            return vertex2;
         else
            return vertex1;
      }
      
      public boolean equals(Edge<E> edge)
      {
          Vertex[] adjVertices = edge.endVertices();
          
          return (adjVertices[0].equals(vertex1) && adjVertices[1].equals(vertex2));
          
      }
      
      public String toString()
      {  return "(" + vertex1 + "-" + vertex2 + ")"; 
      }
   }
}
