/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thegame;

import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author 12171117
 */
//This class is used to create the decision tree once the game starts. 
//this decision tree then adds new nodes with questions and animal data to itself.
//this tree is then traversed left or right recursively according to user responses to progress through the game.
public class DecisionTree {

    private Node root;
    private final IBehaviour behaviour; //this class uses the IBehaviour inteface methods to progress through the game

    public DecisionTree() {
        root = null;
        behaviour = null;
    }

    public DecisionTree(IBehaviour behaviour) {
        this.behaviour = behaviour;
    }

    //this is the first execute method which calls emptyTree() method to create the root node if tree is empty.
    //if root is not null then its to call the second private execute method.
    public boolean execute() {
        if (root == null) {
            root = behaviour.emptyTree();
            return true;
        } else {
            if (execute(root)) {
                return true;
            } else {
                return false;
            }
        }
    }

    //this execute method is to recursively traverse through the tree to play the game with the node passed in as the starting point.
    //this method is to perform appropriate operations depending on the status of the nodes.
    //i.e. if the node passed in is leaf node then it calls processLeafNode(n) method, otherwise it calls processNonLeaf(n) method.
    private boolean execute(Node n) {
        boolean endResult;
        if (!n.isLeaf()) {
            boolean direction = behaviour.processNonLeafNode(n);
            if (direction == false) {
                endResult = execute(n.getLeft());
            } else {
                endResult = execute(n.getRight());
            }
//            return direction;
        } else {
            endResult = behaviour.processLeafNode(n);
        }
        return endResult;
    }

    //Generate indented preorder display of tree structure
    public String display() {
        StringBuilder sb = new StringBuilder();
        display(sb, 0, "root:", root);
        return sb.toString();
    }

    // recursive method to perform preorder traversal and build the String
    private void display(StringBuilder sb, int level, String direction, Node node) {
        if (node == null) {
            return;
        }
        level = level + 2;
        for (int i = 0; i < level; i++) {
            sb.append(" ");
        }
        sb.append(String.format("%s %s %d\n", direction, node.getData(), node.getLabel()));
        display(sb, level, "left: ", node.getLeft());
        display(sb, level, "right:", node.getRight());
    }

    //this method labels each node using inorser traversal.
    //this is to help saving and loading the game progress to and from a text file
    private int label(Node n, int count) {
        if (n != null) {
            int next = label(n.getLeft(), count);
            n.setLabel(next++);
            next = label(n.getRight(), next);
            return next;
        }
        return count;
    }

    //this method takes in the name of the text file to be saved
    //it then first labels the nodes of the decision tree 
    //then calls the second save method to save the game into a text file.
    public void save(String name) throws Exception {
        label(root, 1);
        Formatter formatter = new Formatter(name);
        save(root, formatter);
        formatter.close();

    }

    //this method takes in the formatter object created in the first save method and the root node passed into the label method.
    //then writes the its nodes with their data and labels into a text file using the preorderHelper method.
    private void save(Node node, Formatter formatter) {
        preorderHelper(node, formatter);
    }

    //this method is to read the previously saved game data from a text file
    //and then call the insert method to build the decision tree by inserting nodes in order.
    public void load(String fname) throws Exception {
        Scanner sc = new Scanner(new File("animal.txt"));
        int label = sc.nextInt();
        String data = sc.nextLine().trim();
        Node t = new Node(data, label);
        while (sc.hasNextLine()) {
            label = sc.nextInt();
            data = sc.nextLine().trim();
            Node n = new Node(data, label);
            root = insert(n, t);
        }
    }

    //this method is to insert the nodes passed into it which are read using the load method .
    //it inserts nodes according to the labels assigned to the nodes. it inserts nodes to the left of a root node if label value is lower than root node.
    //inserts nodes to the right of the root if label value of the node is higher than the root node label value.
    private Node insert(Node n, Node t) {
        if (t == null) {
            return n;
        } else {
            if (n.getLabel() < t.getLabel()) {
                t.setLeft(insert(n, t.getLeft()));
            } else {
                t.setRight(insert(n, t.getRight()));
            }
        }
        return t;
    }

    //this method is to recursively traverse each node in a preorder way
    //it is to help the save method to write game data into textfile using predorder traversal.
    private void preorderHelper(Node node, Formatter formatter) {
        if (node == null) {
            return;
        }

        formatter.format("%d\t %s\n", node.getLabel(), node.getData());
        preorderHelper(node.getLeft(), formatter); // traverse left subtree
        preorderHelper(node.getRight(), formatter); // traverse right subtree
    } 

}
