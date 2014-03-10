package azaka7.algaecraft.common.item;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.CommonProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAlgae extends Item {
	private Icon algaeImg;
	public ItemAlgae(int par1, int img) {
		super(par1);
		//this.iconIndex = img;
		this.setCreativeTab(AlgaeCraftMain.modTab);
	}
	
	public String getTextureFile(){
		return CommonProxy.itemsImg;
	}
	
	/*public String getItemDisplayName(ItemStack par1ItemStack)
    {
		return "Algae";
    }*/
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		algaeImg = par1IconRegister.registerIcon("azaka7:algaeBall");
    }
	public Icon getIconFromDamage(int par1)
    {
		return this.getBlockTextureFromSideAndMetadata(0, par1);
    }
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
		return algaeImg;
    }
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        MovingObjectPosition var4 = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

        if (var4 == null)
        {
            return par1ItemStack;
        }
        else
        {
            if (var4.typeOfHit == EnumMovingObjectType.TILE)
            {
                int var5 = var4.blockX;
                int var6 = var4.blockY;
                int var7 = var4.blockZ;

                if (!par2World.canMineBlock(par3EntityPlayer, var5, var6, var7))
                {
                    return par1ItemStack;
                }

                if (!par3EntityPlayer.canPlayerEdit(var5, var6, var7, var4.sideHit, par1ItemStack))
                {
                    return par1ItemStack;
                }

                if (par2World.getBlockMaterial(var5, var6, var7) == Material.water && par2World.getBlockMetadata(var5, var6, var7) == 0 && par2World.isAirBlock(var5, var6 + 1, var7)&&AlgaeCraftMain.blockAlgae.canBlockStay(par2World, var5, var6+1, var7))
                {
                    par2World.setBlock(var5, var6 + 1, var7, AlgaeCraftMain.blockAlgae.blockID, 0, 2);

                    if (!par3EntityPlayer.capabilities.isCreativeMode)
                    {
                        --par1ItemStack.stackSize;
                    }
                }
            }

            return par1ItemStack;
        }
    }
}
