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
public class MAILEvent extends Event
{
    private String address;

    public MAILEvent(String addressMail)
    {
        super(EventEnum.MAIL);
        this.address = addressMail;
    }

    public String getAddress()
    {
        return this.address;
    }
}
