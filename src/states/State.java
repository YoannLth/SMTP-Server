/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import events.EventEnum;

import java.util.ArrayList;
import java.util.List;

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
}
