package com.paully104.reitzmmo.Party_System;

/**
 * Created by Paul on 7/31/2016.
 */
public class Party_Queue {
    //So it works like this in my mind
    //Party leader sends invite to player with passcode
    //Player needs to do /party accept passcode to join
    private String creator;
    private String invited;
    private int passcode;

    public Party_Queue(String creator,String invited,int passcode)
    {
          this.creator = creator;
          this.invited = invited;
          this.passcode = passcode;

    }
    public int getPasscode()
    {
        return  this.passcode;
    }
    public String getCreator()
    {
        return  this.creator;
    }
    public String getInvited()
    {
        return  this.invited;
    }




}
