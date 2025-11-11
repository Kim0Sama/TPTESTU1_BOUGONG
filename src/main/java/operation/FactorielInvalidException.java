package operation;

public class FactorielInvalidException extends Exception{
    private int n=0;
    public FactorielInvalidException (String message) throws FactorielInvalidException{
        super (message);
    }
}
