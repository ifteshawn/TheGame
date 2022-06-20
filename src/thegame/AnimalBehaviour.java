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
//implements the IBehaviour interface methods to provide the behaviour required to use the decision tree to play the animal game. 
public class AnimalBehaviour implements IBehaviour {

    private final IView view;

    public AnimalBehaviour(IView view) {
        this.view = view;
    }

    //to ask the player for the animal, 
    //create a new Node with the player’s animal and returns the reference to this new Node which is then assigned to the root of the tree.
    @Override
    public Node emptyTree() {
        Node node = new Node(view.ask("What is your animal?"));
        return node;
    }

    //it asks the user if the curretn anuimal in the node is the user's animal.
    //if it is the correct animal then it returns false which indicates the computer won.
    //or else it allows to extend the tree with a new question and animal if the computer does not correctly identify the player’s animal.
    @Override
    public boolean processLeafNode(Node n) {
        boolean result = true;
        if (view.choose(n.getQuestion()) == true) { //if true - the player answered "yes" and the comp guessed animal correctly
            result = false;

        } else { //player answered "no", comp made incorrect guess - player won
            String newAnimal = view.ask("You won! What is your animal?");
            String newQuestion = view.ask("Provide a yes/no question that distinguishes between " + newAnimal + " and " + n.getData() + ".\n"
                    + "Yes = " + newAnimal + "; " + "no = " + n.getData());
            n.extend(newQuestion, n.getData(), newAnimal);
        }

        return result;
    }

    //this method is to display the question in the node by using the view’s choose() method. This allows the user to answer yes or no. 
    //It returns a boolean (true if player answers yes, false if player answers no). 
    @Override
    public boolean processNonLeafNode(Node n) {
        boolean result = view.choose(n.getData());
        if (result) {
            return true;
        } else {
            return false;
        }
    }

}
