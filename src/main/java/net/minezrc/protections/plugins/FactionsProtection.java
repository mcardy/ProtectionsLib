package net.minezrc.protections.plugins;

import net.minezrc.protections.Protection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.BoardColls;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.UPlayer;
import com.massivecraft.mcore.ps.PS;

public class FactionsProtection implements Protection {

	@Override
	public boolean canBuild(Player player, Location loc) {
		UPlayer uplayer = UPlayer.get(player);
		Faction faction = BoardColls.get().getFactionAt(PS.valueOf(loc));
		if (faction != null) {
			if (faction.getUPlayers().contains(uplayer)) {
				return true;
			}
			return false;
		}
		return true;
	}

	@Override
	public boolean canPvp(Player player, Player target) {
		UPlayer uplayer = UPlayer.get(player);
		UPlayer utarget = UPlayer.get(target);
		if (uplayer.getFaction() != null
				&& uplayer.getFaction().getUPlayers().contains(utarget)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean canLoad() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("Factions");
		if (plugin != null && plugin instanceof Factions) {
			return true;
		}
		return false;
	}

	@Override
	public void load() {
	}

}
