// a class whose purpose is to develop a custom exception that
// can be thrown when needed
public class InvalidException extends Exception {
    public InvalidException(String message)
    {}
    public InvalidException()
    {
        super("Error: the number that you entered is invalid.");
    }
}
