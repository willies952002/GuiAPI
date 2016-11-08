package me.Swedz.gapi;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class GuiAPI extends JavaPlugin {
	public static GuiAPI instance;
	public static InventoryClickEvent event;
	
	public void onEnable() {
		instance = this;
		Bukkit.getServer().getPluginManager().registerEvents(new ClickEvent(), instance);
	}
}
