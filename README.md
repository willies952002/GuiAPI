# GuiAPI
GuiAPI (Guided User Interface API) is a Spigot API that is designed to make programming GUIs *very, very* easy!

## What makes GuiAPI special?
Well, GuiAPI is an open source, public API that allows you to make code easily runnable, so you don't have to handle the events, check the item clicked, etc. So everything is *much, much* easier!

## Creating an Inventory
To create an Inventory, all you need to do is use the GuiAPI constructor. Like so:
```java
Inventory inv = new GUI()
	.name("Test GUI")
	.rows(1)
	.setItem(new ItemStack(Material.DIRT), 0, 
		new Runnable() {
			public void run() {
				InventoryClickEvent e = GuiAPI.event;
				Player player = (Player) e.getWhoClicked();
				player.sendMessage("You clicked the dirt block in slot 0 :D");
				//run your code here
			}
		})
	.setItem(new ItemStack(Material.STONE), 1,
		new Runnable() {
			public void run() {
				InventoryClickEvent e = GuiAPI.event;
				Player player = (Player) e.getWhoClicked();
				player.sendMessage("You clicked the stone block in slot 1 :D");
				//run some other code here
			}
		})
.build();
```
*Note: name() must be specified before the items.*

This will create an Inventory that can be used to open for a player:
```java
player.openInventory(inv);
```

## Maven Setup
```xml
<repository>
  <id>swedz-repo</id>
  <name>Swedz's Repository</name>
  <url>http://swedz.net/repo/</url>
</repository>
...
<dependency>
  <groupId>me.Swedz</groupId>
  <artifactId>GuiAPI</artifactId>
  <version>0.1</version>
  <scope>compile</scope>
</dependency>
```
