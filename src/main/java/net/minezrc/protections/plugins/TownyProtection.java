package net.minezrc.protections.plugins;

import net.minezrc.protections.Protection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.object.TownyPermission;
import com.palmergames.bukkit.towny.utils.CombatUtil;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;

public class TownyProtection implements Protection {

	private Towny towny;
	
	@Override
	public boolean canBuild(Player player, Location loc) {
		return PlayerCacheUtil.getCachePermission(player, loc, 1,
				(byte) 0, TownyPermission.ActionType.BUILD);
	}

	@Override
	public boolean canPvp(Player player, Player target) {
		return CombatUtil.preventDamageCall(towny, player, target);
	}

	@Override
	public boolean canLoad() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("Towny");
		if (plugin != null && plugin instanceof Towny) {
			return true;
		}
		return false;
	}

	@Override
	public void load() {
		this.towny = (Towny) Bukkit.getPluginManager().getPlugin("Towny");
	}

}
