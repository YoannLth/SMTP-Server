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
        String answer = "";
        State nextState = null;
        if(plainText.getText().equals("."))
        {
            Utils.WriteNewMailToJSON();
            Utils.ResetBufferMemory();
            answer = "250 OK\r\n";
            nextState = new WaitingMail();
        }
        else
        {
            String messageReceived = plainText.getText();
            String[] messageSplit = messageReceived.split(" ");
            if(messageSplit[0].equals("Date:"))
            {
                ThreadCommunication.mail.get().setDate(messageReceived.split("Date: ")[1]);
            }
            else if(messageSplit[0].equals("Subject:"))
            {
                ThreadCommunication.mail.get().setSubject(messageReceived.split("Subject: ")[1]);
            }
            else
            {
                ThreadCommunication.mail.get().addToBody(plainText.getText());
            }
        }

        return new StateAnswer(nextState, answer);
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
