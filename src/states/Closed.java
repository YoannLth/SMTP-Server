package states;

import events.*;
import utils.Utils;

/**
 * Created by yannick on 18/04/17.
 */
public class Closed extends State
{
    public Closed()
    {
        super(StateEnum.CLOSED);
    }

    @Override
    public StateAnswer launchEHLO(EHLOEvent ehlo)
    {
        return Utils.GenerateGivenCommandNotHandlingInGivenStateMessage("EHLO", this);
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
        return Utils.GenerateGivenCommandNotHandlingInGivenStateMessage("PLAIN TEXT", this);
    }

    @Override
    public StateAnswer launchRSET(RSETEvent rsets)
    {
        return Utils.GenerateGivenCommandNotHandlingInGivenStateMessage("RSET", this);
    }

    @Override
    public StateAnswer launchQUIT(QUITEvent quit)
    {
        return Utils.GenerateGivenCommandNotHandlingInGivenStateMessage("QUIT", this);
    }
}
