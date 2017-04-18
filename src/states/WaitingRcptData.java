package states;

import events.*;
import server.ThreadCommunication;
import utils.Utils;

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
        ThreadCommunication.recipients.get().add(rcpt.getReceipient());
        return new StateAnswer(null, "250 OK");
    }

    @Override
    public StateAnswer launchDATA(DATAEvent data)
    {
        return new StateAnswer(new DataReceived(), "354 End message with <CR><LF>.<CR><LF>");
    }

    @Override
    public StateAnswer launchPlainText(PlainTextEvent plainText)
    {
        return null;
    }

    @Override
    public StateAnswer launchRSET(RSETEvent rsets)
    {
        Utils.ResetBufferMemory();
        return new StateAnswer(new WaitingMail(), "250 OK");
    }

    @Override
    public StateAnswer launchQUIT(QUITEvent quit)
    {
        return Utils.GenerateQuitAnswer();
    }
}
