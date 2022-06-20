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

//this interface specifies the methods required to implement the behaviour for processing an empty tree, processing a leaf node and processing a non-leaf node.
public interface IBehaviour {
    
    public Node emptyTree();
    
    public boolean processNonLeafNode(Node n);
    
    public boolean processLeafNode(Node n);
    
}
