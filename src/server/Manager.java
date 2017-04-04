/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import events.EHLOEvent;
import states.State;
import events.EventEnum;
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
