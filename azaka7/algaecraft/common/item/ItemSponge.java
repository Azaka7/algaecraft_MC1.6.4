package azaka7.algaecraft.common.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import azaka7.algaecraft.common.CommonProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSponge extends Item {
	
	private String[] imgs = new String[]{"Dry","Wet"};
	private Icon[] spongeImg = new Icon[imgs.length];
	private String baseName;
	
	private boolean isWet;
	
	public ItemSponge(int par1ID, String name) {
		super(par1ID);
		this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setMaxStackSize(16);
        baseName=name;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		for(int i = 0; i <imgs.length; i++){
			spongeImg[i] = par1IconRegister.registerIcon("azaka7:sponge"+baseName+imgs[i]);
		}
    }
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
		int i = par2;
		i = (i >= spongeImg.length) ? 0 : i;
		return spongeImg[i];
    }
	
	public String getTextureFile(){
		return CommonProxy.itemsImg;
	}
	
	public String getItemDisplayName(ItemStack par1ItemStack)
    {
		String state = (par1ItemStack.getItemDamage()==1) ? "Wet":"Dry";
        return (""+state+" "+baseName+" Sponge");
    }
	
	public Icon getIconFromDamage(int par1)
    {
		return this.getBlockTextureFromSideAndMetadata(0, par1);
    }
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        float var4 = 1.0F;
        double var5 = par3EntityPlayer.prevPosX + (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * (double)var4;
        double var7 = par3EntityPlayer.prevPosY + (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * (double)var4 + 1.62D - (double)par3EntityPlayer.yOffset;
        double var9 = par3EntityPlayer.prevPosZ + (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * (double)var4;
        this.isWet = (par1ItemStack.getItemDamage() == 1);
        boolean isNotWet = !this.isWet;
        MovingObjectPosition var12 = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, isNotWet);

        if (var12 == null)
        {
            return par1ItemStack;
        }
        else
        {
            if (var12.typeOfHit == EnumMovingObjectType.TILE)
            {
                int var13 = var12.blockX;
                int var14 = var12.blockY;
                int var15 = var12.blockZ;

                if (!par2World.canMineBlock(par3EntityPlayer, var13, var14, var15))
                {
                    return par1ItemStack;
                }

                if (isNotWet)
                {
                    if (!par3EntityPlayer.canPlayerEdit((int)(Math.round(var5)), (int)Math.round(var7), (int)Math.round(var9), var12.sideHit, par1ItemStack))
                    {
                        return par1ItemStack;
                    }

                    if (par2World.getBlockMaterial(var13, var14, var15) == Material.water && par2World.getBlockMetadata(var13, var14, var15) == 0)
                    {
                        par2World.setBlock(var13, var14, var15, 0, 0, 3);

                        if (par3EntityPlayer.capabilities.isCreativeMode)
                        {
                            return par1ItemStack;
                        }

                        if (--par1ItemStack.stackSize <= 0)
                        {
                            //return new ItemStack(this, 1, 1);
                        }

                        if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(this,1,1)))
                        {
                            par3EntityPlayer.dropPlayerItem(new ItemStack(this, 1, 1));
                        }

                        return par1ItemStack;
                    }
                }
                else
                {
                    if (var12.sideHit == 0)
                    {
                        --var14;
                    }

                    if (var12.sideHit == 1)
                    {
                        ++var14;
                    }

                    if (var12.sideHit == 2)
                    {
                        --var15;
                    }

                    if (var12.sideHit == 3)
                    {
                        ++var15;
                    }

                    if (var12.sideHit == 4)
                    {
                        --var13;
                    }

                    if (var12.sideHit == 5)
                    {
                        ++var13;
                    }

                    if (!par3EntityPlayer.capabilities.allowEdit)
                    {
                        return par1ItemStack;
                    }

                    if (this.cauldronInteraction(par1ItemStack, par2World, par3EntityPlayer)
                    		|| this.func_77875_a(par2World, var5, var7, var9, var13, var14, var15) && !par3EntityPlayer.capabilities.isCreativeMode)
                    {
                    	if (--par1ItemStack.stackSize <= 0)
                        {
                            //return new ItemStack(this, 1, 1);
                        }

                        if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(this,1,0)))
                        {
                            par3EntityPlayer.dropPlayerItem(new ItemStack(this, 1, 0));
                        }/*
                    	if(par1ItemStack.stackSize-1 <= 0){
                    		//return new ItemStack(this, 1, 0);
                    	}
                    	else{
                    		par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(this, 1, 0));
                        	par1ItemStack.stackSize--;
                    		return par1ItemStack;
                    	}*/
                    }
                }
            }

            return par1ItemStack;
        }
    }
	
	public boolean cauldronInteraction(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		MovingObjectPosition var12 = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, !this.isWet);
		int x = var12.blockX;
        int y = var12.blockY;
        int z = var12.blockZ;
        
        if(par2World.getBlockId(x, y, z)==Block.cauldron.blockID && par2World.getBlockMetadata(x, y, z) < 3){
        	par2World.setBlockMetadataWithNotify(x, y, z, 3, 2);
        	return true;
        }
        return false;
	}
	
	public boolean func_77875_a(World par1World, double par2, double par4, double par6, int par8, int par9, int par10)
    {
        if (!this.isWet)
        {
            return false;
        }
        else if (!par1World.isAirBlock(par8, par9, par10) && par1World.getBlockMaterial(par8, par9, par10).isSolid())
        {
            return false;
        }
        else
        {
            if (par1World.provider.isHellWorld && this.isWet)
            {
                par1World.playSoundEffect(par2 + 0.5D, par4 + 0.5D, par6 + 0.5D, "random.fizz", 0.5F, 2.6F + (par1World.rand.nextFloat() - par1World.rand.nextFloat()) * 0.8F);

                for (int var11 = 0; var11 < 8; ++var11)
                {
                    par1World.spawnParticle("largesmoke", (double)par8 + Math.random(), (double)par9 + Math.random(), (double)par10 + Math.random(), 0.0D, 0.0D, 0.0D);
                }
            }
            else
            {
                par1World.setBlock(par8, par9, par10, Block.waterMoving.blockID, 0, 3);
            }

            return true;
        }
    }
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
    }

}
