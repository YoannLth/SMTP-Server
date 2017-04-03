/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

/**
 *
 * @author yannick
 */
public enum StateEnum
{
    AUTHORIZATION,
    WAITING_MAIL,
    WAITING_RCPT_OR_DATA,
    DATA_RECEIVED,
    CLOSED
}
