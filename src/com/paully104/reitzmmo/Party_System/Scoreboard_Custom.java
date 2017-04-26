package com.paully104.reitzmmo.Party_System;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.*;

/**
 * Created by Paul on 8/11/2016.
 */
public class Scoreboard_Custom {

    private String player;
    private ScoreboardManager manager = Bukkit.getScoreboardManager();
    private Scoreboard board;
    private Team team;
    private Objective playerHealth;
    private Objective partyMember1;
    private Objective partyMember2;
    private Objective partyMember3;
    private Objective partyMember4;


    public Scoreboard_Custom(String player)
    {
        this.player = player;

    }

    public void createScoreboard()
    {
        board = manager.getNewScoreboard();
    }
    public void registerNewTeam()
    {
        team = board.registerNewTeam(player);
        team.addPlayer(Bukkit.getOfflinePlayer(player));

    }
    public void registerHealth()
    {
        playerHealth = board.registerNewObjective("Health", "health");
        playerHealth.setDisplaySlot(DisplaySlot.BELOW_NAME);
        String health = " / " + Bukkit.getPlayer(this.player).getMaxHealth();
        playerHealth.setDisplayName(health);

    }
    public void setScoreboardOnPlayer()
    {
        Bukkit.getPlayer(player).setScoreboard(board);
    }


    public String getPlayer()
    {
        return  this.player;
    }
    public Scoreboard getScoreboard() { return this.board;}
}
