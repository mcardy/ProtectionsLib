package net.minezrc.protections;

import java.util.List;

import net.minezrc.protections.plugins.FactionsProtection;
import net.minezrc.protections.plugins.TownyProtection;
import net.minezrc.protections.plugins.WorldGuardProtection;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * ProtectionsLib main class
 * 
 * @author minnymin3
 */
public class ProtectionsLib extends JavaPlugin {

	private List<Protection> protectionList;

	@Override
	public void onEnable() {
		registerProtection(new FactionsProtection());
		registerProtection(new TownyProtection());
		registerProtection(new WorldGuardProtection());
	}

	public void registerProtection(Protection protection) {
		if (protection.canLoad()) {
			protection.load();
			protectionList.add(protection);
		}
	}

	public boolean canBuild(Player player, Block block) {
		for (Protection protection : protectionList) {
			if (!protection.canBuild(player, block.getLocation())) {
				return false;
			}
		}
		return true;
	}

	public boolean canBuild(Player player, Location loc) {
		for (Protection protection : protectionList) {
			if (!protection.canBuild(player, loc)) {
				return false;
			}
		}
		return true;
	}

	public boolean canPvp(Player player, Player target) {
		for (Protection protection : protectionList) {
			if (!protection.canPvp(player, target)) {
				return false;
			}
		}
		return true;
	}

}
