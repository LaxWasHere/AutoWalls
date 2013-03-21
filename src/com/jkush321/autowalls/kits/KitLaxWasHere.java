package com.jkush321.autowalls.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;


public class KitLaxWasHere extends Kit {
	
	
ItemStack[] contents = new ItemStack[]{ new ItemStack(Material.DIAMOND_SWORD, 1), new ItemStack(Material.COOKED_BEEF, 4), new ItemStack(Material.IRON_HELMET), new ItemStack(Material.DIAMOND_CHESTPLATE, 1), new ItemStack(Material.DIAMOND_LEGGINGS, 1), new ItemStack(Material.DIAMOND_BOOTS, 1) };
	
	public KitLaxWasHere()
	{
		Potion potion = new Potion(PotionType.INVISIBILITY);
		potion.setSplash(true);
		potion.setHasExtendedDuration(true);
		ItemStack bow = new ItemStack(Material.BOW);
		bow.addEnchantment(Enchantment.ARROW_DAMAGE, 10);
		bow.addEnchantment(Enchantment.ARROW_FIRE, 5);
		bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		
		contents = new ItemStack[]{ new ItemStack(Material.DIAMOND_SWORD, 1), new ItemStack(Material.COOKED_BEEF, 64), new ItemStack(Material.DIAMOND_HELMET), new ItemStack(Material.DIAMOND_CHESTPLATE, 1), new ItemStack(Material.DIAMOND_LEGGINGS, 1), new ItemStack(Material.DIAMOND_BOOTS, 1), bow, new ItemStack(Material.ARROW, 1) };
	}
	
	@Override
	public ItemStack[] getItemStack() {
		return contents;
	}

	@Override
	public int getRequiredPriority() {
		return 9001;
	}

}

