/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import json_parser.ParserJSON;
import model.Mail;
import model.User;
import org.json.simple.JSONObject;
import server.ThreadCommunication;
import states.Closed;
import states.StateAnswer;

/**
 *
 * @author yannick
 */
public abstract class Utils
{
    public static boolean UserInList(ArrayList<User> list, String user)
    {
        for(User u : list) 
        {
            if(u.getName().equals(user))
            {
                return true;
            }
        }
        return false;
    }

    public static void WriteNewMailToJSON()
    {
        for(String recipient : ThreadCommunication.recipients.get())
        {
            ThreadCommunication.mail.get().setReceptor(recipient);
            ParserJSON.addMail(Utils.ConvertMailToJSONObject(ThreadCommunication.mail.get()));
        }
    }

    public static JSONObject ConvertMailToJSONObject(Mail mail)
    {
        JSONObject mailJson = new JSONObject();
        JSONObject from = new JSONObject();
        JSONObject to = new JSONObject();
        from.put("name", mail.getExpeditorName());
        from.put("adress", mail.getExpeditor());
        to.put("name", mail.getReceptorName());
        to.put("adress", mail.getReceptor());

        mailJson.put("from", from);
        mailJson.put("to", to);
        mailJson.put("subject", mail.getSubject());
        mailJson.put("date", mail.getDate());
        mailJson.put("message-id", mail.getMessageID());
        mailJson.put("balise", MailTagEnum.UNREAD);
        mailJson.put("body", mail.getBody());
        return mailJson;
    }

    public static StateAnswer GenerateQuitAnswer()
    {
        return new StateAnswer(new Closed(), "221 Bye\r\n");
    }

    public static void ResetBufferMemory()
    {
        ThreadCommunication.recipients.set(new ArrayList());
        ThreadCommunication.from.set("");
        ThreadCommunication.mail.set(null);
    }

    public static String GenerateHelpMessage()
    {
        return "-ERR Commande non reconnue\r\nVoici la liste des commandes disponibles:\r\n"
        + "APOP <user> <pass> - Permet de se connecter\r\n"
        + "STAT - Informations sur la boite\r\n"
        + "...\r\n";
    }
}
