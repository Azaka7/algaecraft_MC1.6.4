package azaka7.algaecraft.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemGeneric extends Item {
	
	private String iconName;
	private String[] customDataStrings;

	public ItemGeneric(int par1, String s, CreativeTabs tab) {
		super(par1);
		this.iconName = s;
		this.setCreativeTab(tab);
		customDataStrings = null;
	}
	
	public ItemGeneric(int par1, String s, CreativeTabs tab, String[] data) {
		super(par1);
		this.iconName = s;
		this.setCreativeTab(tab);
		customDataStrings = data;
	}
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		if(customDataStrings != null){
			for(int c = 0; c < customDataStrings.length; c++){
				par3List.add(customDataStrings[c]);
			}
		}
	}
	
	public void registerIcons(IconRegister par1IconRegister)
    {
		this.itemIcon = par1IconRegister.registerIcon("azaka7:"+this.iconName);
    }
}
