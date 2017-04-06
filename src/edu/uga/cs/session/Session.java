package edu.uga.cs.session;

/*******************************************
 * Based on the modified code from Matthew Eavenson
 *
 * @file    Session.java
 * @author  Matthew Everson
 * @see     LICENSE (MIT style license file)
 * @version 0.8
 * @date    Sat May 2 18:00:51 EDT 2011
 */

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.object.*;


/***************************************************************
 * This class creates a new session and handle different
 * operations related to session.
 */
public class Session
    extends Thread
{
    private Connection conn;
    private Person person;
    private String id;
    private Date expiration;

    /***********************************************************
     * Constructs a new session for a current connection
     * @param conn
     */
    public Session( Connection conn )
    {
        this.conn = conn;
        extendExpiration();
    }

    /***********************************************************
     * Gets the JDBC connection information for the current session.
     * @return current connection
     */
    public Connection getConnection()
    {
        extendExpiration();
        return conn;
    }

    /***********************************************************
     * Gets the GVUser for which the session is created.
     * @return the loggedIn user
     */
    public Person getUser()
    {
        extendExpiration();
        return person;
    }

    /***********************************************************
     * Sets the loggedIn user to the new created session.
     * @param  person the user to be associated with the session.
     */
    public void setUser(Person person) throws MyThoughtsException
    {
        extendExpiration();
        this.person = person;
    }

    /***********************************************************
     * Gets the id for the current session.
     * @return the id for the current session
     */
    public String getSessionId()
    {
        extendExpiration();
        return id;
    }

    /***********************************************************
     * Sets the id for the current session.
     * @param id The String id for the session
     */
    public void setSessionId( String id )
    {
        this.id = id;
    }

    /***********************************************************
     * Gets the Date/time at which the session will expire
     * @return expiration date of this session
     */
    public Date getExpiration()
    {
        return expiration;
    }

    /***********************************************************
     * Sets the Date/time at which the session will expire
     * @param expiration the expiration date for this session
     */
    public void setExpiration(Date expiration)
    {
        this.expiration = expiration;
    }

    /***********************************************************
     * Extends the expiration time for the session by 30 minutes
     */
    private void extendExpiration(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 30);
        this.expiration = c.getTime();
    }

    /***********************************************************
     * Runs the new session as a thread
     */
    @Override
    public void run()
    {
        long diff = expiration.getTime() - System.currentTimeMillis();
        while (diff > 0) {
            //System.out.println("Session not expired.  Sleeping for "+(diff/1000)+" seconds...");
            try {
                sleep(diff);
            }
            catch( Exception e ) {
                //e.printStackTrace();
                break;
            }
            diff = expiration.getTime() - System.currentTimeMillis();
        }
        //System.out.println("Removing "+usr.name+"'s session");
        try {
            SessionManager.removeSession( this );
        }
        catch( MyThoughtsException e ) {
            //log.error( e.toString(), e );
            try {
                throw e;
            }
            catch (MyThoughtsException e1) {
                e1.printStackTrace();
            }
        }
    }

}
