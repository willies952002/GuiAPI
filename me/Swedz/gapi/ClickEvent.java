package me.Swedz.gapi;

import java.util.HashMap;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ClickEvent implements Listener {
	public static HashMap<String, HashMap<Integer, Runnable>> items = new HashMap<String, HashMap<Integer, Runnable>>();
	public static HashMap<String, Boolean> autoCancel = new HashMap<String, Boolean>();
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Inventory inv = e.getClickedInventory();
		if(inv != null && items.containsKey(inv.getName())) {
			GuiAPI.event = e;
			if(autoCancel.containsKey(inv.getName())) {
				e.setCancelled(autoCancel.get(inv.getName()));
			}
			
			HashMap<Integer, Runnable> slot_runnable = items.get(inv.getName());
			int slot_clicked = e.getSlot();
			if(slot_runnable.containsKey(slot_clicked) && slot_runnable.get(slot_clicked) != null) {
				slot_runnable.get(slot_clicked).run();
			}
			
			GuiAPI.event = null;
		}
	}
}
