package states;

import events.*;
import server.ThreadCommunication;
import utils.Utils;

/**
 * Created by yannick on 18/04/17.
 */
public class WaitingMail extends State
{

    public WaitingMail()
    {
        super(StateEnum.WAITING_MAIL);
    }

    @Override
    public StateAnswer launchEHLO(EHLOEvent ehlo)
    {
        return null;
    }

    @Override
    public StateAnswer launchMAIL(MAILEvent mail)
    {
        String answer = "";
        if(Utils.CheckIfAddressExists(mail.getAddress()))
        {
            ThreadCommunication.from.set(mail.getAddress());
            answer = "250 OK\r\n";
        }
        else
        {
            answer = "550 No such user\r\n";
        }
        return new StateAnswer(new WaitingRcptData(), answer);
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
        Utils.ResetBufferMemory();
        return new StateAnswer(null, "250 OK\r\n");
    }

    @Override
    public StateAnswer launchQUIT(QUITEvent quit)
    {
        return Utils.GenerateQuitAnswer();
    }
}
