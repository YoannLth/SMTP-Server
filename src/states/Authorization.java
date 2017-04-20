/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import events.DATAEvent;
import events.EHLOEvent;
import events.MAILEvent;
import events.PlainTextEvent;
import events.QUITEvent;
import events.RCPTEvent;
import events.RSETEvent;
import utils.Utils;

/**
 *
 * @author yannick
 */
public class Authorization extends State
{

    public Authorization()
    {
        super(StateEnum.AUTHORIZATION);
    }

    @Override
    public StateAnswer launchEHLO(EHLOEvent ehlo)
    {
        String domainName = "test.com"; //To change
        State nextState = null;
        String answer;
        if(ehlo.getDomainName().equals(domainName))
        {
            answer = ("250 "+domainName+" Hello\r\n");
            nextState = new WaitingMail();
        }
        else
        {
            answer = "ERROR\r\n";//PUT ERROR CODE INSTEAD OF ERROR
        }
        
        return new StateAnswer(nextState, answer);
    }

    @Override
    public StateAnswer launchMAIL(MAILEvent mail)
    {
        return Utils.GenerateGivenCommandNotHandlingInGivenStateMessage("MAIL", this);
    }

    @Override
    public StateAnswer launchRCPT(RCPTEvent rcpt)
    {
        return Utils.GenerateGivenCommandNotHandlingInGivenStateMessage("RCPT", this);
    }

    @Override
    public StateAnswer launchDATA(DATAEvent data)
    {
        return Utils.GenerateGivenCommandNotHandlingInGivenStateMessage("DATA", this);
    }

    @Override
    public StateAnswer launchPlainText(PlainTextEvent plainText)
    {
        return Utils.GenerateGivenCommandNotHandlingInGivenStateMessage("Plain text", this);
    }

    @Override
    public StateAnswer launchRSET(RSETEvent rsets)
    {
        return Utils.GenerateGivenCommandNotHandlingInGivenStateMessage("RSET", this);
    }

    @Override
    public StateAnswer launchQUIT(QUITEvent quit)
    {
        return Utils.GenerateQuitAnswer();
    }
    
}
