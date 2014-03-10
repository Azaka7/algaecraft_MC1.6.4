package azaka7.algaecraft.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.CommonProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSeaweed extends Item {
	
	private Icon seaweedImg;
	
	public ItemSeaweed(int par1) {
		super(par1);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		seaweedImg = par1IconRegister.registerIcon("azaka7:seaweedItem");
    }
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
		return seaweedImg;
    }
	public Icon getIconFromDamage(int par1)
    {
    	return getBlockTextureFromSideAndMetadata(0,par1);
    }
	
	public String getTextureFile(){
		return CommonProxy.itemsImg;
	}
	
	public String getItemDisplayName(ItemStack par1ItemStack)
    {
		return "Seaweed";
    }
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par7 != 1)
        {
            return false;
        }
        else if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
        {
            if (AlgaeCraftMain.blockSeaweed.canPlaceBlockAt(par3World, par4, par5+1, par6))
            {
                par3World.setBlock(par4, par5 + 1, par6, AlgaeCraftMain.blockSeaweed.blockID, 0, 2);
                --par1ItemStack.stackSize;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

}
