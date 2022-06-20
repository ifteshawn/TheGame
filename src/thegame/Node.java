/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thegame;


/**
 *
 * @author 12171117
 */

// The node class is used to create nodes to hold questions and animals entered by the user which are then inserted into the decision tree as the game progresses.
public class Node {

    private String data;
    private int label;
    private Node left;
    private Node right;

    public Node(String data) {
        this.data = data;
    }

    // constructor for node which may not have may not have any children.
    public Node(String data, int label) {
        this.data = data;
        this.label = label;
    }

    // constructor for root node.
    public Node(String data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    // This method is to retrieve the animal stored in the current node and ask the user if that's the animal the user is thinking of
    public String getQuestion() {
        return "Is your animal a(n) " + getData();
    }

    //this method extends the tree from the current node when a new animal node and a new question node have to be added to the tree.
    //it creates new nodes for the new question and new animal entered by the user.
    //It replaces the animal stored in current node with the new question, attaches the old animal to the left of thecurrent node and attaches the new animal to the right
    public void extend(String data, String leftAnimal, String rightAnimal) {
        leftAnimal = this.data;
        this.data = data;
        this.setLeft(new Node(leftAnimal));
        this.setRight(new Node(rightAnimal));
    }

    //this method checks if the current node is a leaf node or not
    public boolean isLeaf() {
        if (this.left == null && this.right == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @return the label
     */
    public int getLabel() {
        return label;
    }

    /**
     * @return the left
     */
    public Node getLeft() {
        return left;
    }

    /**
     * @return the right
     */
    public Node getRight() {
        return right;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     * @param right the right to set
     */
    public void setRight(Node right) {
        this.right = right;
    }

}
