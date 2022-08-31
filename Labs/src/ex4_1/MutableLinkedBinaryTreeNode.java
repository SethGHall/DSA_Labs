package ex4_1;

/**
   A class that implements a tree node using a list
   to store references to the child node
*/
import java.util.ArrayList;
import java.util.Enumeration; // old version of an Iterator
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class MutableLinkedBinaryTreeNode<E> implements MutableTreeNode
{
   private E element;
   private MutableTreeNode parent;
   private MutableTreeNode leftChild;
   private MutableTreeNode rightChild;


   public MutableLinkedBinaryTreeNode()
   {  this(null);
   }

   public MutableLinkedBinaryTreeNode(E element)
   {  this.element = element;
      parent = null;
      leftChild = null;
      rightChild = null;
     
   }

   // returns an Enumeration of the child nodes
   public Enumeration<MutableTreeNode> children()
   {  List<MutableTreeNode> list = new LinkedList<MutableTreeNode>();
      if(rightChild != null) list.add(rightChild);
      if(leftChild != null) list.add(leftChild);
      return (Enumeration<MutableTreeNode>) (new Enumerator(list.iterator()));
   }

   // returns that this node allows children
   public boolean getAllowsChildren()
   {  return true;
   }

   // return the child at specified index
   public TreeNode getChildAt(int childIndex)throws IndexOutOfBoundsException
   {  if(childIndex > 1 || childIndex < 0)
         throw new IndexOutOfBoundsException("NOT IN RANGE OF CHILDREN, 0 or 1");
      else if(childIndex == 1) return rightChild;
      else return leftChild;
   }

   // returns the number of children of this node
   public int getChildCount()
   {  int count = 0;
      if(leftChild != null) count++;
      if(rightChild != null) count++;
      return count;
   }

   // returns the index of node or -1 if node not found
   public int getIndex(TreeNode node)
   {  if (node==null)
         throw new IllegalArgumentException();
      else if(leftChild != null && leftChild.equals(node)) return 0;
      else if(rightChild != null && rightChild.equals(node)) return 1;
      else return -1;
   }

   // return the parent node or null if this node is the root
   public TreeNode getParent()
   {  return parent;
   }

   // returns whether this node is a leaf
   public boolean isLeaf()
   {  return (getChildCount()==0);
   }

   // add the child node at specified index, updating this node
   // and child node to reflect the change
   public void insert(MutableTreeNode child, int index)
      throws IllegalArgumentException
   {  if(index == 0 && leftChild != null) throw new IllegalArgumentException("Already a child node There");
      else if(index == 1 && rightChild != null) throw new IllegalArgumentException("Already a child node There");
      else if(index == 0)
      {  child.removeFromParent();
         leftChild = child;
         leftChild.setParent(this);
      }
      else if(index == 1)
      {   child.removeFromParent();
         rightChild = child;
         rightChild.setParent(this);
      }
      else throw new IllegalArgumentException("Index out of bounds");
   }

   // removes the child at index from this node, updating this
   // node and child node to reflect the change
   public void remove(int index)
   {  //MutableTreeNode child = children.get(index); // update list
      if (index == 0 && leftChild != null)
      {  leftChild.setParent(null);
         leftChild = null;
      }
      else if(index == 1 && rightChild != null)
      {  rightChild.setParent(null);
         rightChild = null;
      }
   }

   // remove the specified child from this node, updating this
   // node and child node to reflect the change
   public void remove(MutableTreeNode node)
   {  if (node != null && node.equals(leftChild)) // node found and removed
      {  node.setParent(null);
         leftChild = null;
      }
      else if (node != null && node.equals(rightChild)) // node found and removed
      {  node.setParent(null);
         rightChild = null;
      }
   }

   // remove this node from its parent, updating this
   // node and its parent node
   public void removeFromParent()
   {  if (this.parent!=null)
      {  parent.remove(this);
         this.parent = null;
      }
   }

   // sets the parent of this node to be newParent
   // but does not update newParent
   public void setParent(MutableTreeNode newParent)
   {  //removeFromParent(); // remove this node from its existing parent
      this.parent = newParent;
   }

   // set the element held in this node
   public void setUserObject(Object object)
   {  this.element = (E)object; // unchecked, could throw exception
   }

   public E getUserObject()
   {  return element;
   }

   // returns a string representation of the node and its child nodes
   // in preorder notation
   public String toString()
   {  String output = "" + this.element;
      if (!isLeaf())
      {  output += "[";
         output += ""+leftChild;
         output += ","+rightChild;
         output += "]";
      }
      return output;
   }

   public static void main(String[] args)
   {  // create some sample nodes
      MutableTreeNode root = new MutableLinkedBinaryTreeNode<>("A");
      MutableTreeNode nodeB = new MutableLinkedBinaryTreeNode<>("B");
      MutableTreeNode nodeC = new MutableLinkedBinaryTreeNode<>("C");
      MutableTreeNode nodeD = new MutableLinkedBinaryTreeNode<>("D");
      MutableTreeNode nodeE = new MutableLinkedBinaryTreeNode<>("E");
      MutableTreeNode nodeF = new MutableLinkedBinaryTreeNode<>("F");
      MutableTreeNode nodeG = new MutableLinkedBinaryTreeNode<>("G");
      MutableTreeNode nodeZ = new MutableLinkedBinaryTreeNode<>("Z");
      // build the tree

      nodeB.insert(nodeD, 1);
     // nodeC.insert(nodeD, 1);
      nodeD.insert(nodeF, 0);
      nodeD.insert(nodeG, 1);

      root.insert(nodeB, 0);
      root.insert(nodeC, 1);
      nodeC.insert(nodeE, 0);
       nodeC.insert(nodeZ, 1);

      System.out.println("Original Tree: " + root);
     // System.out.println("Original Tree: 2nd Tier: " + nodeB + "  and " +nodeC);
     root.remove(nodeC);
     System.out.println("Lets detach Node C from root:");
      
      System.out.println("Modified Tree after C removed: " + root);
      System.out.println("Subtree of NodeC: " + nodeC);
      System.out.println("Lets Now attach Node C to Node B as its left child:");
      nodeB.insert(nodeC,0);
      System.out.println("Added C to B: " + root);
   }
   // utility class to wrap an Iterator object as an Enumeration object
    class Enumerator<E> implements Enumeration<E>
    {
       private Iterator<E> iterator;

       public Enumerator(Iterator<E> iterator)
       {  this.iterator = iterator;
       }

       public boolean hasMoreElements()
       {  return iterator.hasNext();
       }

       public E nextElement()
       {  return iterator.next();
       }
    }
}


