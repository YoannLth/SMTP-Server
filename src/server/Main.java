package server;

import java.io.IOException;

/**
 *
 * @author Yoann LATHUILIERE
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        SMTPServer smtpServer = new SMTPServer();
        smtpServer.initializeServer();
    }
    
}
