package states;

import events.*;
import server.ThreadCommunication;
import utils.Utils;

/**
 * Created by yannick on 18/04/17.
 */
public class DataReceived extends State
{
    public DataReceived()
    {
        super(StateEnum.DATA_RECEIVED);
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
        String answer = "";
        State nextState = null;
        if(plainText.getText().equals("."))
        {
            //ADD TO JSON THE MAIL
            Utils.ResetBufferMemory();
            answer = "250 OK";
            nextState = new WaitingMail();
        }
        else
        {
            ThreadCommunication.mail.set(ThreadCommunication.mail.get()+plainText.getText());
        }

        return new StateAnswer(nextState, answer);
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
