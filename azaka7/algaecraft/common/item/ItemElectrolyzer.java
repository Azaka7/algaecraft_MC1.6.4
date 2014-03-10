package azaka7.algaecraft.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import azaka7.algaecraft.common.AlgaeCraftMain;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemElectrolyzer extends Item {
	
	public static String iconName = "redstoneElectrolyzer";
	public static Icon iconImg;

	public ItemElectrolyzer(int par1) {
		super(par1);
		this.maxStackSize = 1;
		this.setMaxDamage(200);
		this.setCreativeTab(AlgaeCraftMain.modTab);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		iconImg = par1IconRegister.registerIcon("azaka7:"+iconName);
    }
	public Icon getIconFromDamage(int par1)
    {
    	return iconImg;
    }
	
	public boolean hasContainerItem()
    {
        return true;
    }
	
	public ItemStack getContainerItemStack(ItemStack itemStack)
    {
		itemStack.setItemDamage(itemStack.getItemDamage()+1);
		return itemStack;
    }
	
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack)
    {
		return false;
    }
	
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }

}
