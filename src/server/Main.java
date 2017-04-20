package server;

import json_parser.ParserJSON;

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
        ParserJSON.initJSONFiles();
        Object tmp = ParserJSON.getUsers();
        SMTPServer smtpServer = new SMTPServer();
        smtpServer.initializeServer();
    }
    
}
