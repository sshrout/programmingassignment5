package programmingassignment5;

import java.io.*;
import java.util.*;

public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {

    protected TreeNode<E> root;
    protected int size = 0;

    /**
     * Create a default binary tree
     */
    public BinarySearchTree() {
    }

    /**
     * Create a binary tree from an array of objects
     */
    public BinarySearchTree(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            insert(objects[i]);
        }
    }

    /**
     * Returns true if the element is in the tree
     */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                return true; // Element is found
            }
        }
        return false;
    }

    /**
     * Search for an item and update stats array
     * @param e item to search for
     * @param stats array containing stats that require update
     * @return boolean indicating if item was found
     * @requires valid e and stats 
     * @ensures a boolean is returned indicating if word is found and stats array is updated
     */
        public boolean search(E e, long[] stats) {
        TreeNode<E> current = root; // Start from the root
        long i = 0;
        while (current != null) {
            i += 1;
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                stats[2] += i;
                return true; // Element is found
             
            
            }
        }
           stats[3] += i;
        return false;
    }
    /**
     * Return a node containing a specified value
     *
     * @param e the value to locate
     * @return the node with the specified value
     * @requires a valid node value
     * @ensures a node is returned associated with the specified value
     */
    public TreeNode<E> returnNode(E e) {
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                return current;
            }
        }
        return null;
    }

    /**
     * Insert element o into the binary tree Return true if the element is
     * inserted successfully. Uses an iterative algorithm
     */
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e); // Create a new root
        } else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false; // Duplicate node not inserted
                }
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true; // Element inserted
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<E>(e);
    }

    /**
     * Inorder traversal from the root
     */
    public void inorder() {
        inorder(root);
    }

    /**
     * Inorder traversal from a subtree
     */
    protected void inorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    /**
     * Postorder traversal from the root
     */
    public void postorder() {
        postorder(root);
    }

    /**
     * Postorder traversal from a subtree
     */
    protected void postorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    /**
     * Preorder traversal from the root
     */
    public void preorder() {
        preorder(root);
    }

    /**
     * Preorder traversal from a subtree
     */
    protected void preorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /**
     * Inner class tree node
     */
    public static class TreeNode<E extends Comparable<E>> {

        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    /**
     * Get the number of nodes in the tree
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Returns an ArrayList containing elements in the path from the root
     * leading to the specified element, returns an empty ArrayList if no such
     * element exists.
     */
    public ArrayList<E> path(E e) {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root
        list.add(current.element);
        boolean elementFound = false;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
                list.add(current.element);
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
                list.add(current.element);
            } else // element matches current.element
            {
                elementFound = true;
                return list;
            }
        }

        list.clear();

        return list;

    }

    /**
     * Get number of leaves associated with the current tree
     *
     * @return the number of leaves found for the current tree and its children
     * @ensures the number of leaves for the current tree and its children are
     * returned
     */
    public int getNumberOfLeaves() {
        return getNumberOfLeaves(root);
    }

    /**
     * Get number of leaves associated with a node
     *
     * @param current the node from which to determine the number of leaves of
     * current node and its children
     * @return the number of leaves found for the current node and its children
     * @ensures the number of leaves for the current node and its children are
     * returned
     * @requires a valid TreeNode is provided
     */
    public int getNumberOfLeaves(TreeNode<E> current) {
        if (current.left == null && current.right == null) {
            return 1;
        }

        int ans = 0;
        if (current.left != null) {
            ans += getNumberOfLeaves(current.left);
        }
        if (current.right != null) {
            ans += getNumberOfLeaves(current.right);
        }

        return ans;

    }

    /**
     * Get left subtree of a node in preorder
     *
     * @param e an element value
     * @return an ArrayList containing the child elements of the left node of
     * the specified element
     * @ensures an ArrayList is returned containing the left child elements of
     * the specified element in preorder
     * @requires a valid element value is provided
     */
    /* Returns an ArrayList containing all elements in preorder of the specified element’s left sub-tree, returns an empty ArrayList if no  such element exists. */
    public ArrayList<E> leftSubTree(E e) {
        //left for you to implement in Lab 7
        TreeNode<E> node = returnNode(e);
        ArrayList<E> returnTree = preOrderSubTree(node.left);
        return returnTree;
    }

    /**
     * Get right subtree of a node in preorder
     *
     * @param e an element value
     * @return an ArrayList containing the child elements of the right node of
     * the specified element
     * @ensures an ArrayList is returned containing the right child elements of
     * the specified element in preorder
     * @requires a valid element value is provided
     */
    public ArrayList<E> rightSubTree(E e) {
        //left for you to implement in Lab 7
        TreeNode<E> node = returnNode(e);
        ArrayList<E> returnTree = preOrderSubTree(node.right);
        return returnTree;
    }

    /**
     * Get a subtree of a node in preorder
     *
     * @param n the treenode for which the child elements should be returned in
     * preorder
     * @return an ArrayList of tree elements in preorder from specified treenode
     * @requires a valid treenode is provided
     * @ensures an ArrayList is returned containing the child elements in
     * preorder
     */
    public ArrayList<E> preOrderSubTree(TreeNode<E> n) {

        ArrayList<E> ans = new ArrayList<E>();
        if (n == null) {
            return ans;
        }
        ans.add(n.element);
        ans.addAll(preOrderSubTree(n.left));
        ans.addAll(preOrderSubTree(n.right));
        return ans;

    }

//    /* Returns an ArrayList containing all elements in preorder of the specified element’s right sub-tree, returns an empty ArrayList if no  such element exists. */
//    public ArrayList<E> rightSubTree(E e){
//    //left for you to implement in Lab 7
//    }
//    
//    /* Returns the inorder predecessor of the specified element, returns null if tree is empty or element 'e' is not in the tree. */
//    public E inorderPredecessor(E e){
//    //left for you to implement in Lab 7
//    }
    /**
     * Delete an element from the binary tree. Return true if the element is
     * deleted successfully Return false if the element is not in the tree
     */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break; // Element is in the tree pointed by current
            }
        }
        if (current == null) {
            return false; // Element is not in the tree
        }    // Case 1: current has no left children
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else {
                if (e.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        } else {
            // Case 2 & 3: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }
            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else // Special case: parentOfRightMost == current
            {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true; // Element inserted
    }

    /**
     * Obtain an iterator. Use inorder.
     */
    public java.util.Iterator iterator() {
        return inorderIterator();
    }

    /**
     * Obtain an inorder iterator
     */
    public java.util.Iterator inorderIterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    class InorderIterator implements java.util.Iterator {
        // Store the elements in a list

        private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /**
         * Inorder traversal from the root
         */
        private void inorder() {
            inorder(root);
        }

        /**
         * Inorder traversal from a subtree
         */
        private void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        /**
         * Next element for traversing?
         */
        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }
            return false;
        }

        /**
         * Get the current element and move cursor to the next
         */
        public Object next() {
            return list.get(current++);
        }

        /**
         * Remove the current element and refresh the list
         */
        public void remove() {
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    /**
     * Remove all elements from the tree
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Determine if two trees are the same
     *
     * @param tree the tree to compare against the current tree
     * @return boolean indicating if tree is equal to the current true
     * @ensures a value is returned indicating if a tree is equal to the current
     * tree
     * @requires a valid tree is provided for comparison
     */
    public boolean sameTree(BinarySearchTree tree) {
        return sameTree(this.root, tree.root);
    }

    /**
     * Determine if two tree nodes are the same
     *
     * @param tree1 first tree node to compare
     * @param tree2 second tree note to compare
     * @return a boolean value indicating if the two nodes are equal
     * @ensures a value is returned indicating if two nodes are equal
     * @requires tree1 and tree2 are valid TreeNodes
     */
    public boolean sameTree(TreeNode<E> tree1, TreeNode<E> tree2) {
        if (tree1.element == null && tree2.element == null) {
            return true;
        }
        if (tree1.element.equals(tree2.element) == false) {
            return false;
        } else {
            boolean leftResult = false;
            boolean rightResult = false;
            if (tree1.left != null && tree2.left != null) {
                leftResult = sameTree(tree1.left, tree2.left);
            } else if (tree1.left != null || tree2.left != null) {
                return false;
            }
            else {
            return true;
            }

            if (tree1.right != null & tree2.right != null) {
                rightResult = sameTree(tree1.right, tree2.right);
            } else if (tree1.right != null || tree2.right != null) {
                return false; }
                else {
                return true;
                        }
            

            if (leftResult && rightResult) {
                return true;
            } else {
                return false;
            }

        }

    }

}
