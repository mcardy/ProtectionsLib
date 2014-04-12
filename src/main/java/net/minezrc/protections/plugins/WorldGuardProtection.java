package net.minezrc.protections.plugins;

import net.minezrc.protections.Protection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;

public class WorldGuardProtection implements Protection {

	WorldGuardPlugin plugin;

	@Override
	public boolean canBuild(Player player, Location loc) {
		return plugin.canBuild(player, loc);
	}

	@Override
	public boolean canPvp(Player player, Player target) {
		if (plugin.getRegionManager(target.getWorld())
				.getApplicableRegions(target.getLocation())
				.allows(DefaultFlag.PVP)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canLoad() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
		if (plugin != null && plugin instanceof WorldGuardPlugin) {
			return true;
		}
		return false;
	}

	@Override
	public void load() {
		plugin = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin(
				"WorldGuard");
	}

}
