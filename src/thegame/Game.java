package thegame;

/**
 *
 * @author 12171117
 */


//This class is to create a game which the user is to play
//This class uses IView interface methods to allow the user to interact with the game and perform functions according what the users clicks on
//Creates a new AnimalBehaviour object that can be passed to the constructor of the DecisionTree
//Creates a new DecisionTree and passes the new AnimalBehaviour object into the DecisionTree’s constructor.  
public class Game {

    private final DecisionTree tree; //a game is composed of a decision tree
    private final IView view;

    public Game(IView view) {
        this.view = view;
        tree = new DecisionTree(new AnimalBehaviour(view)); //creates a decision tree which take
    }

    //to load a previously saved game from a text file when the user clicks on load in the GUI.
    public void load(String fname) {
        try {
            tree.load(fname);
            view.display("Tree has been successfully loaded.");
        } catch (Exception ex) {
            view.display("Tree could not be loaded from the file name provided.");
            System.out.println(ex);
        }
    }

    //to save a game when the user clicks on save.
    public void save(String fname) {
        try {
            tree.save(fname);
            view.display("Tree has been successfully saved.");
        } catch (Exception e) {
            view.display("Tree could not be saved in the file name provided.");
            System.out.println(e);
        }
    }

    //to display the tree when the user clicks on display in the GUI.
    public String display() {
        return tree.display();
    }

    //to display how to play the game in the text area of the GUI.
    public void help() {
        view.display("Think of an animal. If my tree is non-empty, I will ask some yes/no questions to try to determine what it is.");
    }

    // to play the game using the decision tree’s execute() method 
    //determines and displays who won according to the result returned from the execute method.
    public void play() {
        help();//display the help message 
        boolean again = true;
        while (again) {
            if (tree.execute()) {
                again = view.choose("You won! Play again?");
            } else {
                again = view.choose("I won! Play again?");
            }
        }

    }
}
