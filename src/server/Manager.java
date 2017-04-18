/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import events.*;
import states.State;
import states.StateAnswer;
import utils.Utils;

/**
 *
 * @author yannick
 */
public class Manager
{
    
    
    public StateAnswer HandleCommand(String receivedMessage, State currentState)
    {
        //Split the received message by space, to know what is the command
        //and what are the args
        String[] message_split = receivedMessage.split(" ");
        EventEnum command;
        
        try
        {
            command = EventEnum.valueOf(message_split[0].toUpperCase());
        }
        catch(IllegalArgumentException e)
        {
            //Envoyer ERR
            return new StateAnswer(null, Utils.GenerateHelpMessage());
        }
        
        StateAnswer response = null;
        String returnedMessage = "";
                
        switch(command)
        {
            case EHLO:
                System.out.println("EHLO received");
                response = currentState.launchEHLO(new EHLOEvent(message_split[1]));
                break;

            case MAIL:
                System.out.println("MAIL received");
                response = currentState.launchMAIL(new MAILEvent());
                break;

            case RCPT:
                System.out.println("RCPT received");
                response = currentState.launchRCPT(new RCPTEvent());
                break;

            case DATA:
                System.out.println("DATA received");
                response = currentState.launchDATA(new DATAEvent());
                break;

            case RSET:
                System.out.println("RSET received");
                response = currentState.launchRSET(new RSETEvent());
                break;

            case QUIT:
                System.out.println("QUIT received");
                response = currentState.launchQUIT(new QUITEvent());
                break;
            
            default:
                System.out.println("Switch case not handled yet");
                returnedMessage = Utils.GenerateHelpMessage();
                break;
        }
        
        if(response == null)
        {
            return new StateAnswer(null, returnedMessage);
        }
        
        return response;
    }
}
