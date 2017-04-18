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
            answer = ("250 "+domainName+" Hello");
            nextState = new WaitingMail();
        }
        else
        {
            answer = "ERROR";//PUT ERROR CODE INSTEAD OF ERROR
        }
        
        return new StateAnswer(null, answer);
    }

    @Override
    public StateAnswer launchMAIL(MAILEvent mail)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StateAnswer launchRCPT(RCPTEvent rcpt)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StateAnswer launchDATA(DATAEvent data)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StateAnswer launchPlainText(PlainTextEvent plainText)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StateAnswer launchRSET(RSETEvent rsets)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StateAnswer launchQUIT(QUITEvent quit)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
