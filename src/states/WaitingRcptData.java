package states;

import events.*;

/**
 * Created by yannick on 18/04/17.
 */
public class WaitingRcptData extends State
{
    public WaitingRcptData()
    {
        super(StateEnum.WAITING_RCPT_OR_DATA);
    }

    @Override
    public StateAnswer launchEHLO(EHLOEvent ehlo)
    {
        return null;
    }

    @Override
    public StateAnswer launchMAIL(MAILEvent mail)
    {
        return null;
    }

    @Override
    public StateAnswer launchRCPT(RCPTEvent rcpt)
    {
        return null;
    }

    @Override
    public StateAnswer launchDATA(DATAEvent data)
    {
        return null;
    }

    @Override
    public StateAnswer launchPlainText(PlainTextEvent plainText)
    {
        return null;
    }

    @Override
    public StateAnswer launchRSET(RSETEvent rsets)
    {
        return null;
    }

    @Override
    public StateAnswer launchQUIT(QUITEvent quit)
    {
        return null;
    }
}
