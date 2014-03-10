package azaka7.algaecraft.common.item;

import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.CommonProxy;
import azaka7.algaecraft.common.block.BlockSpongeSpore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemSpongeSpore extends Item {
	
	private Icon spongeImg;
	private BlockSpongeSpore spongeBlock;
	private String nameAdd;
	
	public ItemSpongeSpore(int par1, String name, BlockSpongeSpore block) {
		super(par1);
		this.setCreativeTab(AlgaeCraftMain.modTab);
		nameAdd = name;
		spongeBlock = block;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		spongeImg = par1IconRegister.registerIcon("azaka7:sponge"+nameAdd+"Seed");
    }
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
		return spongeImg;
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
		return nameAdd+(nameAdd != "" ? " " : "")+"Sponge Spores";
    }
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par7 != 1)
        {
            return false;
        }
        else if (par2EntityPlayer.capabilities.allowEdit && spongeBlock instanceof BlockSpongeSpore)
        {
            if (spongeBlock.canPlaceBlockAt(par3World, par4, par5+1, par6))
            {
                par3World.setBlock(par4, par5 + 1, par6, spongeBlock.blockID, 0, 2);
                --par1ItemStack.stackSize;//563,68,-1551
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