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
        String answer = "";
        if(Utils.CheckIfAddressExists(rcpt.getReceipient()))
        {
            ThreadCommunication.recipients.get().add(rcpt.getReceipient());
            answer = "250 OK\r\n";
        }
        else
        {
            answer = "550 No such user\r\n";
        }
        return new StateAnswer(null, answer);
    }

    @Override
    public StateAnswer launchDATA(DATAEvent data)
    {
        return new StateAnswer(new DataReceived(), "354 End message with <CR><LF>.<CR><LF>\r\n");
    }

    @Override
    public StateAnswer launchPlainText(PlainTextEvent plainText)
    {
        return Utils.GenerateGivenCommandNotHandlingInGivenStateMessage("PLAIN TEXT", this);
    }

    @Override
    public StateAnswer launchRSET(RSETEvent rsets)
    {
        Utils.ResetBufferMemory();
        return new StateAnswer(new WaitingMail(), "250 OK\r\n");
    }

    @Override
    public StateAnswer launchQUIT(QUITEvent quit)
    {
        return Utils.GenerateQuitAnswer();
    }
}
