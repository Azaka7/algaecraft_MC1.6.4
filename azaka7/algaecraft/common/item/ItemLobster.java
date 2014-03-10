package azaka7.algaecraft.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.entity.EntityLobster;

public class ItemLobster extends Item {

	Icon img;
	
	public ItemLobster(int par1) {
		super(par1);
		this.setMaxStackSize(1);
		this.setCreativeTab(AlgaeCraftMain.modTab);
	}
	
	public void registerIcons(IconRegister par1IconRegister)
    {
		img = par1IconRegister.registerIcon("azaka7:lobsterItem");
    }
	
	public Icon getIconFromDamage(int par1)
    {
    	return img;
    }
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
		if (!par3World.isRemote)
        {
			EntityLobster lob = new EntityLobster(par3World);
			lob.setLocationAndAngles(par4+0.5, par5+1, par6+0.5, this.wrapAngleTo180_float(par3World.rand.nextFloat() * 360.0F), 0.0F);
			lob.rotationYawHead = lob.rotationYaw;
			lob.renderYawOffset = lob.rotationYaw;
			par3World.spawnEntityInWorld(lob);
			lob.playLivingSound();
			if (!par2EntityPlayer.capabilities.isCreativeMode){
				par2EntityPlayer.inventory.setInventorySlotContents(par2EntityPlayer.inventory.currentItem, new ItemStack(Item.silk));
			}
        }
		return false;
    }
	
	public static float wrapAngleTo180_float(float par0)
    {
        par0 %= 360.0F;

        if (par0 >= 180.0F)
        {
            par0 -= 360.0F;
        }

        if (par0 < -180.0F)
        {
            par0 += 360.0F;
        }

        return par0;
    }


}
