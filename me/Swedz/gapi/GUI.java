package me.Swedz.gapi;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("static-access")
public class GUI {
	protected static String name;
	protected static int rows;
	protected static InventoryType inv_type;
	protected static HashMap<ItemStack, Integer> items;
	protected static HashMap<ItemStack, Runnable> runnables;
	
	public GUI() {
		this.name = "Chest";
		this.rows = 1;
		this.inv_type = InventoryType.CHEST;
		this.items = new HashMap<ItemStack, Integer>();
		this.runnables = new HashMap<ItemStack, Runnable>();
	}
	
	public GUI type(InventoryType value) {
		this.inv_type = value;
		return this;
	}
	
	public GUI name(String value) {
		this.name = value;
		return this;
	}
	
	public GUI rows(int value) {
		this.rows = value;
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
	
	public Inventory build() {
		Inventory inv;
		if(this.inv_type != InventoryType.CHEST) {
			inv = Bukkit.createInventory(null, this.inv_type, this.name);
		} else {
			inv = Bukkit.createInventory(null, this.rows*9, this.name);
		}
		
		for(ItemStack li : this.items.keySet()) {
			int slot = this.items.get(li);
			inv.setItem(slot, li);
		} return inv;
	}
}
