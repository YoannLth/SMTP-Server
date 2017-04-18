/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

/**
 *
 * @author yannick
 */
public class RCPTEvent extends Event
{
    public String receipient;

    public RCPTEvent(String addressRcpt)
    {
        super(EventEnum.RCPT);
        this.receipient = addressRcpt;
    }

    public String getReceipient()
    {
        return this.receipient;
    }
}
