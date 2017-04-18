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
public class PlainTextEvent extends Event
{
    private String text;

    public PlainTextEvent(String text)
    {
        super(EventEnum.PLAIN_TEXT);
        this.text = text;
    }

    public String getText()
    {
        return this.text;
    }
}
