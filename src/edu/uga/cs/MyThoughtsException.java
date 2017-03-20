package edu.uga.cs;


/**
 * This class represents a basic exception to be thrown and handled by the myThoughts system.
 */
public class MyThoughtsException extends Exception
{
    private static final long serialVersionUID = 1L;

    /**
     * Create a new MyThoughtsException object.
     * @param message the message explaining the exception
     */
    public MyThoughtsException(String message)
    {
      super(message);
    }

    /**
     * Create a new MyThoughtsException object.
     * @param cause the cause of the exception
     */
    public MyThoughtsException(Throwable cause)
    {
      super(cause);
    }
}
