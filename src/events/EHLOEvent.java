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
public class EHLOEvent extends Event
{
    String domainName;
    
    public EHLOEvent(String domain)
    {
        super(EventEnum.EHLO);
        this.domainName = domain;
    }

    public String getDomainName()
    {
        return domainName;
    }
}
