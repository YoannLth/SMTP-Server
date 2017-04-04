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
public abstract class State
{
    StateEnum currentState;
    
    public State(StateEnum state)
    {
        this.currentState = state;
    }
    
    public String getStateName()
    {
        return this.currentState.toString();
    }
    
    public abstract StateAnswer launchEHLO(EHLOEvent ehlo);
    public abstract StateAnswer launchMAIL(MAILEvent mail);
    public abstract StateAnswer launchRCPT(RCPTEvent rcpt);
    public abstract StateAnswer launchDATA(DATAEvent data);
    public abstract StateAnswer launchPlainText(PlainTextEvent plainText);
    public abstract StateAnswer launchRSET(RSETEvent rsets);
    public abstract StateAnswer launchQUIT(QUITEvent quit);
}
