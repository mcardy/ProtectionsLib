package net.minezrc.protections;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Protection {

	public boolean canBuild(Player player, Location loc);
	public boolean canPvp(Player player, Player target);
	
	public boolean canLoad();
	public void load();
	
}
