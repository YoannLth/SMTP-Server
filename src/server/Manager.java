/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import events.*;
import states.State;
import states.StateAnswer;
import states.StateEnum;
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
                if(message_split.length == 2)
                {
                    response = currentState.launchEHLO(new EHLOEvent(message_split[1]));
                }
                else
                {
                    returnedMessage = "EHLO take one argument";
                }
                break;

            case MAIL:
                System.out.println("MAIL received");
                if(message_split.length == 3)
                {
                    String from = message_split[2].substring(1, message_split[2].length()-1);
                    System.out.println("FROM: "+from);
                    response = currentState.launchMAIL(new MAILEvent(from));
                }
                else
                {
                    returnedMessage = "MAIL take one argument";
                }
                break;

            case RCPT:
                System.out.println("RCPT received");
                if(message_split.length == 3)
                {
                    String to = message_split[2].substring(1, message_split[2].length()-1);
                    System.out.println("TO: "+to);
                    response = currentState.launchRCPT(new RCPTEvent(to));
                }
                else
                {
                    returnedMessage = "RCPT take one argument";
                }
                break;

            case DATA:
                System.out.println("DATA received");
                if(message_split.length == 1)
                {
                    response = currentState.launchDATA(new DATAEvent());
                }
                else
                {
                    returnedMessage = "DATA take no argument";
                }
                break;

            case RSET:
                System.out.println("RSET received");
                if(message_split.length == 1)
                {
                    response = currentState.launchRSET(new RSETEvent());
                }
                else
                {
                    returnedMessage = "RSET take no argument";
                }
                break;

            case QUIT:
                System.out.println("QUIT received");
                if(message_split.length == 1)
                {
                    response = currentState.launchQUIT(new QUITEvent());
                }
                else
                {
                    returnedMessage = "QUIT take no argument";
                }
                break;
            
            default:
                if(currentState.equals(StateEnum.DATA_RECEIVED))
                {
                    response = currentState.launchPlainText(new PlainTextEvent(receivedMessage));
                }
                else
                {
                    System.out.println("Switch case not handled yet");
                    returnedMessage = Utils.GenerateHelpMessage();
                }
                break;
        }
        
        if(response == null)
        {
            return new StateAnswer(null, returnedMessage);
        }
        
        return response;
    }
}
