package me.Swedz.gapi;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class GUI {
	protected String name;
	protected int rows;
	protected InventoryType inv_type;
	protected boolean autocancel;
	protected HashMap<ItemStack, Integer> items;
	protected HashMap<ItemStack, Runnable> runnables;
	
	public GUI() {
		this.name = "Chest";
		this.rows = 1;
		this.inv_type = InventoryType.CHEST;
		this.autocancel = false;
		this.items = new HashMap<ItemStack, Integer>();
		this.runnables = new HashMap<ItemStack, Runnable>();
	}
	
	public GUI name(String value) {
		this.name = value+"§r";
		return this;
	}
	
	public GUI rows(int value) {
		this.rows = value;
		return this;
	}
	
	public GUI type(InventoryType value) {
		this.inv_type = value;
		return this;
	}
	
	public GUI autoCancel(boolean value) {
		this.autocancel = value;
		return this;
	}
	
	public GUI setItem(ItemStack item, int slot, Runnable runnable) {
		this.items.put(item, slot);
		this.runnables.put(item, runnable);
		
		HashMap<Integer, Runnable> runnables = new HashMap<Integer, Runnable>();
		if(ClickEvent.items.get(this.name) != null) {
			runnables = ClickEvent.items.get(this.name);
		} runnables.put(slot, runnable);
		ClickEvent.items.put(this.name, runnables);
		
		return this;
	}
	
	public Inventory build(Plugin plugin) {
		Inventory inv;
		if(this.inv_type != InventoryType.CHEST) {
			inv = Bukkit.createInventory(null, this.inv_type, this.name);
		} else {
			inv = Bukkit.createInventory(null, this.rows*9, this.name);
		}
		
		if(this.autocancel) {
			HashMap<String, Boolean> autoCancel = new HashMap<String, Boolean>();
			if(ClickEvent.autoCancel.containsKey(this.name)) {
				autoCancel = ClickEvent.autoCancel;
			} autoCancel.put(this.name, true);
			ClickEvent.autoCancel = autoCancel;
		}
		
		for(ItemStack li : this.items.keySet()) {
			int slot = this.items.get(li);
			inv.setItem(slot, li);
		}
		
		if(!GuiAPI.registered) {
			Bukkit.getServer().getPluginManager().registerEvents(new ClickEvent(), plugin);
			GuiAPI.registered = true;
		}
		
		return inv;
	}
}
