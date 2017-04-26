package com.paully104.reitzmmo.Command_Handlers;

import com.paully104.reitzmmo.Party_System.Party;
import com.paully104.reitzmmo.Party_System.Party_API;
import com.paully104.reitzmmo.Party_System.Party_Queue;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Random;

/**
 * Created by Paul on 7/29/2016.
 */
public class Party_Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 0) {
            sender.sendMessage(ChatColor.GOLD + "~RParty Commands~");
            sender.sendMessage(ChatColor.GOLD + "1. /Rparty create");
            sender.sendMessage(ChatColor.GOLD + "2. /Rparty add");
            sender.sendMessage(ChatColor.GOLD + "3. /Rparty remove");
            sender.sendMessage(ChatColor.GOLD + "4. /Rparty disband");
            sender.sendMessage(ChatColor.GOLD + "5. /Rparty members");
            sender.sendMessage(ChatColor.GOLD + "t. /Rparty leave");
            return true;
        }

        if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 1 &&
                args[0].equalsIgnoreCase("create")) {
            //CREATE PARTY
            if(!(Party_API.inParty.containsKey(sender.getName())) && !(Party_API.Party_Leaders.containsKey(sender.getName())))
            {//You are not in a party and you are not already hosting a party
                Party party = new Party(sender.getName());
                Party_API.Party_Leaders.put(sender.getName(), party);
                sender.sendMessage(ChatColor.GREEN+"You have created a party!");
                sender.sendMessage(ChatColor.WHITE+"Use /rparty add to invite!");
            }
            else
            {
                sender.sendMessage(ChatColor.RED+"[ERROR]You are already in a party!");
            }
            return true;
        }
        if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 1 &&
                args[0].equalsIgnoreCase("disband")) {
            //PARTY DISBAND
            if((Party_API.Party_Leaders.containsKey(sender.getName()))) {
                //you are the party leader, you can disband
                sender.sendMessage(ChatColor.YELLOW + "disbanding party...");
                Party party = Party_API.Party_Leaders.remove(sender.getName());
            }
            else
            {
                sender.sendMessage(ChatColor.RED+"[ERROR]You are not a party leader!");
            }

            return true;
        }
        if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 1 &&
                args[0].equalsIgnoreCase("members")) {

            if((Party_API.Party_Leaders.containsKey(sender.getName())))
            {
                //You are in a party and the leader
                sender.sendMessage(ChatColor.WHITE + "~listing all party members~");
                Party party = Party_API.Party_Leaders.get(sender.getName());
                sender.sendMessage(party.get_Members());
            }
            else if(Party_API.inParty.containsKey(sender.getName()))
            {
                sender.sendMessage(ChatColor.WHITE + "~listing all party members~");
                String leader = Party_API.inParty.get(sender.getName());
                Party party = Party_API.Party_Leaders.get(leader);
                sender.sendMessage(party.get_Members());
            }
            else
            {
                sender.sendMessage(ChatColor.RED+"[ERROR]You are not in a party!");
            }

            return true;
        }
        if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 2 &&
                args[0].equalsIgnoreCase("add")) {
            System.out.println("Add Party");
            sender.sendMessage(ChatColor.WHITE + "sending invite to player: " + args[1]);
            Random r = new Random();
            int passcode = r.nextInt(100) + 1;
            Party_Queue queue = new Party_Queue(sender.getName(),Bukkit.getPlayer(args[1]).getName(),passcode);
            Bukkit.getPlayer(args[1]).sendMessage(ChatColor.GREEN+"Party invite from: " + sender.getName());
            Bukkit.getPlayer(args[1]).sendMessage(ChatColor.WHITE+"Passcode: " + passcode);
            Bukkit.getPlayer(args[1]).sendMessage(ChatColor.WHITE+"Use /rparty join " + passcode + " to join!");
            Party_API.Password_Queue.put(Bukkit.getPlayer(args[1]).getName(),queue);
            //Party party = Party_API.Party_Leaders.get(sender.getName());
            //party.set_Member(args[0]);
            //Party_API.Party_Leaders.put(sender.getName(),party);

            return true;
        }

        if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 2 &&
                args[0].equalsIgnoreCase("join")) {
            if(!(Party_API.inParty.containsKey(sender.getName())))
            {
                sender.sendMessage(ChatColor.YELLOW + "~Attempting to join party~");
                Party_Queue queue = Party_API.Password_Queue.get(sender.getName());
                int passcode = queue.getPasscode();
                if (Integer.parseInt(args[1]) == passcode) {
                    sender.sendMessage(ChatColor.GREEN+"Passcode was correct joining " + queue.getCreator() + "'s" +  " party");
                    Party_API.Party_Leaders.get(queue.getCreator()).set_Member(sender.getName());
                    Bukkit.getPlayer(queue.getCreator()).sendMessage(ChatColor.GREEN+sender.getName() + " has joined your party!");
                    Party_API.Password_Queue.remove(sender.getName());
                    Party_API.inParty.put(sender.getName(), queue.getCreator());

                } else {
                    sender.sendMessage(ChatColor.RED+"[ERROR]Incorrect passcode!");

                }
            }
            else
            {
                sender.sendMessage(ChatColor.RED+"You /are already in a party!");

            }
            //Party party = Party_API.Party_Leaders.get(sender.getName());
            //party.set_Member(args[0]);
            //Party_API.Party_Leaders.put(sender.getName(),party);

            return true;
        }

        if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 2 &&
                args[0].equalsIgnoreCase("remove")) {

            if((Party_API.Party_Leaders.containsKey(sender.getName()))) {
                sender.sendMessage(ChatColor.RED + "removing member...");
                Bukkit.getPlayer(args[1]).sendMessage(ChatColor.RED+sender.getName() + " has remove you from party!");
                Party party = Party_API.Party_Leaders.get(sender.getName());
                party.Remove_Member(args[1]);
                Party_API.Party_Leaders.put(sender.getName(), party);
                Party_API.inParty.remove(args[1]);
            }
            else
            {
                sender.sendMessage(ChatColor.RED+"You can't do that!");

            }

            return true;
        }

        if ((cmd.getName().equalsIgnoreCase("RParty") || cmd.getName().equalsIgnoreCase("rparty")) && args.length == 1 &&
                args[0].equalsIgnoreCase("leave")) {
            if((Party_API.inParty.containsKey(sender.getName()))) {
                sender.sendMessage(ChatColor.RED + "leaving group...");
                String leader = Party_API.inParty.get(sender.getName());
                Party party = Party_API.Party_Leaders.get(leader);
                Bukkit.getPlayer(Party_API.inParty.get(ChatColor.RED+sender.getName())).sendMessage(sender.getName() + " has left the party");
                party.Remove_Member(sender.getName());
                Party_API.Party_Leaders.put(leader, party);
                Party_API.inParty.remove(sender.getName());
            }
            else
            {
                sender.sendMessage(ChatColor.RED+"No party to leave!");

            }

            return true;
        }


        return false;
    }

}

