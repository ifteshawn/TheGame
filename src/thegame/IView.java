package thegame;

//this interface is to enforce FXDocumentController class to 

/**
 *
 * @author 12171117
 */

//this interface specifies the methods required to be implemented by the FXMLDocumentController to interact with the user. Such as asking questions, displaying messages and taking responses.
public interface IView {

    public void display(String s);

    public void append(String s);

    public String ask(String question);

    public boolean choose(String question);

    public String choose(String question, String choice1, String choice);
}
