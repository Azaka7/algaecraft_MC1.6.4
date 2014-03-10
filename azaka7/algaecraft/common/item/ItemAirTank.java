package azaka7.algaecraft.common.item;

import java.util.List;

import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.container.TileEntityAirCompressor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemAirTank extends Item {
	
	/*Damage Key:
	 * last 4 digits are max damage
	 * prior digits are item damage
	 */
	private String itemImg;
	
	public ItemAirTank(int par1, int maxDamage, String itemName) {
		super(par1);
		this.setMaxDamage(maxDamage);
		this.hasSubtypes = false;
		this.maxStackSize = 1;
		itemImg = itemName;
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		if(par3EntityPlayer.inventory.armorItemInSlot(2) != null && par3EntityPlayer.inventory.armorItemInSlot(2).getItem().getClass() == ItemBCD.class){
			ItemBCD bcd = (ItemBCD) par3EntityPlayer.inventory.armorItemInSlot(2).getItem();
			ItemStack bcdStack = par3EntityPlayer.inventory.armorItemInSlot(2);
			//if(bcd.getAirMaximum(bcdStack)==0){
				return bcd.setStackAirTankReturnOld(bcdStack, par1ItemStack);
				//par1ItemStack.stackSize = 0;
				//return par1ItemStack;
			//}
			//return bcd.setStackAirTankReturnOld(par3EntityPlayer.inventory.armorItemInSlot(2), par1ItemStack);
		}
        return par1ItemStack;
    }
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
		if(world.getBlockId(x, y, z) == AlgaeCraftMain.blockAirCompressor.blockID){
			
			int metadata = world.getBlockMetadata(x, y, z);
			if(metadata == 0 || metadata == 3 || metadata == 6 || metadata == 9){
				((TileEntityAirCompressor) world.getBlockTileEntity(x, y, z)).setTank(world, stack);
				//par5EntityPlayer.inventory.mainInventory[par5EntityPlayer.inventory.currentItem].stackSize = 0;
				player.destroyCurrentEquippedItem();
				return true;
			}
		}
		return false;
    }
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add("Breaths: "+((getMaxDamage(par1ItemStack)-getDamage(par1ItemStack)))+"/"+(getMaxDamage(par1ItemStack)));
	}
	
	public void registerIcons(IconRegister par1IconRegister)
    {
		itemIcon = par1IconRegister.registerIcon("azaka7:"+itemImg);
    }
	public Icon getIconFromDamage(int par1)
    {
		return this.itemIcon;
    }
	/*
	@Override
	public int getDamage(ItemStack stack)
    {
		int tempID = stack.itemID;
		stack.itemID = 0;
		int stackDamage = stack.getItemDamage();
		stack.itemID = tempID;
        return (stackDamage - stackDamage%10000)/10000;
    }
	
	public int getMaxDamage(ItemStack stack)
    {
		int tempID = stack.itemID;
		stack.itemID = 0;
		int stackDamage = stack.getItemDamage();
		stack.itemID = tempID;
        return (stackDamage%10000);
    }
	
	public void setDamage(ItemStack stack, int damage)
    {
		int tempID = stack.itemID;
		stack.itemID = 0;
        stack.setItemDamage((damage*10000)+getMaxDamage(stack));

        if (stack.getItemDamage()< 0)
        {
            stack.setItemDamage(0);
        }
        stack.itemID = tempID;
    }*/

}
