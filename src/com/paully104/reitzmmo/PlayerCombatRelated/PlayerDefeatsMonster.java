package com.paully104.reitzmmo.PlayerCombatRelated;

import com.paully104.reitzmmo.ConfigFiles.API;
import com.paully104.reitzmmo.Hologram.Hologram;
import com.paully104.reitzmmo.Party_System.Party;
import com.paully104.reitzmmo.Party_System.Party_API;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;

/**
 * Created by Paul on 3/22/2016.
 */
public class PlayerDefeatsMonster implements Listener {

    private final int PartyEXPMaxDistance = API.partyConfig.getInt("PartyEXPMaxDistance");
    private boolean debug = API.debugConfig.getBoolean("PartyEXP");

    @EventHandler
    public void MonsterDeathCausedBByPlayer(EntityDeathEvent e)
    {


        if(e.getEntity().getKiller() instanceof  Player)
        {
            //Get Entities
            Entity dead = e.getEntity();
            Entity player = e.getEntity().getKiller();
            String playerName = player.getName();
            int monster_level = 0;
            if(debug){System.out.println("PARTYEXPDISTANCE: " + PartyEXPMaxDistance);}
            if(Party_API.Party_Leaders.containsKey(playerName))
            {
                //Party leader kills the mob

                Party party = Party_API.Party_Leaders.get(playerName);
                List<String> members = party.get_MembersList();
                System.out.println(party.get_MembersList());

                for(String people : members)
                {
                    Player partyMember = Bukkit.getPlayer(people);
                    if(partyMember == null)
                    {
                        System.out.println("Player error");

                    }//player is the killer make sure party member is within 100 blocks
                    if(debug){ System.out.println("Distance #1 " + dead.getLocation().distance(partyMember.getLocation()));}
                    if (dead.getLocation().distance(partyMember.getLocation()) <= PartyEXPMaxDistance)
                    {
                        System.out.println(people);
                        Integer currentexp = API.Players.get(people).getData().getInt("Combat-EXP");
                        System.out.println(currentexp);
                        String monster_level_from_name = dead.getCustomName().replaceAll("\\D+", "");
                        monster_level = Integer.parseInt(monster_level_from_name);
                        int new_exp = currentexp + (monster_level * 2);
                        API.Players.get(people).getData().set("Combat-EXP", new_exp);
                        CheckPlayerCombatLevelUp test = new CheckPlayerCombatLevelUp();
                        test.CheckLevelUp(partyMember);
                    }
                    else
                    {
                        System.out.println("Player is to far #2");
                        System.out.println("Player:" + partyMember.getName());
                        System.out.println("Distance" + dead.getLocation().distance(partyMember.getLocation()));
                        System.out.println("Location:" + partyMember.getLocation());
                        //Player is too far for exp!
                    }

                }
                Hologram hologram = new Hologram();
                Location monster = dead.getLocation().add(0.0, 0.0, 0.0);
                hologram.setHologram((Player) player, player.getWorld(), monster, monster_level * 2);

            }
            else if(Party_API.inParty.containsKey(playerName))
            {
                //party member kills mob
                String leader = Party_API.inParty.get(playerName);

                Party party = Party_API.Party_Leaders.get(leader);
                List<String> members = party.get_MembersList();
                System.out.println(party.get_MembersList());

                for(String people : members)
                {

                    Player partyMember = Bukkit.getPlayer(people);
                    if(partyMember == null)
                    {
                        System.out.println("Player error");

                    }
                    if(debug){System.out.println("Distance #1 " + dead.getLocation().distance(partyMember.getLocation()));}
                    //player is the killer make sure party member is within 100 blocks
                    if (dead.getLocation().distance(partyMember.getLocation()) <= PartyEXPMaxDistance) {
                        System.out.println(people);
                        Integer currentexp = API.Players.get(people).getData().getInt("Combat-EXP");
                        System.out.println(currentexp);
                        String monster_level_from_name = dead.getCustomName().replaceAll("\\D+", "");
                        monster_level = Integer.parseInt(monster_level_from_name);
                        int new_exp = currentexp + (monster_level * 2);
                        API.Players.get(people).getData().set("Combat-EXP", new_exp);
                        CheckPlayerCombatLevelUp test = new CheckPlayerCombatLevelUp();
                        test.CheckLevelUp(partyMember);
                    }
                    else
                    {
                        System.out.println("Player is to far #2");
                        System.out.println("Player:" + partyMember.getName());
                        System.out.println("Distance" + dead.getLocation().distance(partyMember.getLocation()));
                        System.out.println("Location:" + partyMember.getLocation());
                        //player is too far away
                    }
                }
                Hologram hologram = new Hologram();
                Location monster = dead.getLocation().add(0.0,0.0,0.0);
                hologram.setHologram((Player)player,player.getWorld(),monster,monster_level*2);

            }
            else
            {

                Integer currentexp = API.Players.get(player.getName()).getData().getInt("Combat-EXP");
                String monster_level_from_name = dead.getCustomName().replaceAll("\\D+", "");
                monster_level = Integer.parseInt(monster_level_from_name);
                int new_exp = currentexp + (monster_level*2);
                API.Players.get(player.getName()).getData().set("Combat-EXP", new_exp);
                CheckPlayerCombatLevelUp test = new CheckPlayerCombatLevelUp();
                test.CheckLevelUp((Player)player);
                Hologram hologram = new Hologram();
                Location monster = dead.getLocation().add(0.0,0.0,0.0);
                hologram.setHologram((Player)player,player.getWorld(),monster,monster_level*2);
                if(debug){System.out.println("Player location: " + player.getLocation());}
                if(debug){ System.out.println("Headheight location: " + ((Player) player).getEyeLocation());}

            }
            //Get Experience
            //Integer currentexp = API.Players.get(player.getName()).getData().getInt("Combat-EXP");
            //String monster_level_from_name = dead.getCustomName().replaceAll("\\D+", "");
            //int monster_level = Integer.parseInt(monster_level_from_name);
            //int new_exp = currentexp + (monster_level*2);
            //API.Players.get(player.getName()).getData().set("Combat-EXP", new_exp);
            //CheckPlayerCombatLevelUp test = new CheckPlayerCombatLevelUp();
            //test.CheckLevelUp((Player)player);
            //Hologram hologram = new Hologram();
            //Location monster = dead.getLocation().add(0.0,0.0,0.0);
            //hologram.setHologram((Player)player,player.getWorld(),monster,monster_level*2);
            //System.out.println("Player location: " + player.getLocation());
            //System.out.println("Headheight location: " + ((Player) player).getEyeLocation());


        }

    }

}
