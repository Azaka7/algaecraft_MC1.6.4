package azaka7.algaecraft.common.block;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.container.TileEntityAirCompressor;
import azaka7.algaecraft.common.item.ItemAirTank;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAirCompressor extends BlockContainer {

	public BlockAirCompressor(int par1) {
		super(par1, Material.iron);
		this.setHardness(Block.anvil.blockHardness);
		this.setCreativeTab(AlgaeCraftMain.modTab);
		this.setLightOpacity(0);
		//net.minecraft.launchwrapper.Launch launch;
	}
	
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
		ItemStack itemstack = ((TileEntityAirCompressor) par1World.getBlockTileEntity(par2, par3, par4)).removeTank(par1World);
		
		float f = par1World.rand.nextFloat() * 0.8F + 0.1F;
        float f1 = par1World.rand.nextFloat() * 0.8F + 0.1F;
        float f2 = par1World.rand.nextFloat() * 0.8F + 0.1F;
		
		EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.itemID, 1, itemstack.getItemDamage()));

        if (itemstack.hasTagCompound())
        {
            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
        }

        float f3 = 0.05F;
        entityitem.motionX = (double)((float)par1World.rand.nextGaussian() * f3);
        entityitem.motionY = (double)((float)par1World.rand.nextGaussian() * f3 + 0.2F);
        entityitem.motionZ = (double)((float)par1World.rand.nextGaussian() * f3);
        par1World.spawnEntityInWorld(entityitem);
		
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityAirCompressor();
	}
	
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	public Icon getIcon(int par1, int par2)
	{
        return Block.blockIron.getIcon(par1, par2);
    }
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	public int getRenderType()
    {
        return 5;
    }
	
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
		return false;
    }
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		if(par5EntityPlayer.inventory.getCurrentItem() != null && par5EntityPlayer.inventory.getCurrentItem().getItem() instanceof ItemAirTank && par5EntityPlayer.inventory.getCurrentItem().stackSize != 0){
			if(par5EntityPlayer.isSneaking()){return false;}
			int metadata = par1World.getBlockMetadata(par2, par3, par4);
			/*if(metadata == 0 || metadata == 3 || metadata == 6 || metadata == 9){
				((TileEntityAirCompressor) par1World.getBlockTileEntity(par2, par3, par4)).setTank(par1World, par5EntityPlayer.inventory.getCurrentItem());
				par5EntityPlayer.inventory.mainInventory[par5EntityPlayer.inventory.currentItem].stackSize = 0;
				return true;
			}*/
			/*else*/ if(metadata == 1 || metadata == 4 || metadata == 7 || metadata == 10){
				//TODO check over this!
				ItemStack otherStack = ((TileEntityAirCompressor) par1World.getBlockTileEntity(par2, par3, par4)).removeTank(par1World);
				((TileEntityAirCompressor) par1World.getBlockTileEntity(par2, par3, par4)).setTank(par1World, par5EntityPlayer.inventory.getCurrentItem());
				par5EntityPlayer.inventory.mainInventory[par5EntityPlayer.inventory.currentItem] = otherStack;
				return true;
			}
		}
		else if(par5EntityPlayer.inventory.getCurrentItem() == null){
			if(!par5EntityPlayer.isSneaking()){return false;}
			int metadata = par1World.getBlockMetadata(par2, par3, par4);
			if(metadata == 1 || metadata == 4 || metadata == 7 || metadata == 10){
				ItemStack newstack = (((TileEntityAirCompressor) par1World.getBlockTileEntity(par2, par3, par4)).removeTank(par1World));
				//par5EntityPlayer.inventory.addItemStackToInventory(newstack);
				if (!par5EntityPlayer.inventory.addItemStackToInventory(newstack))
                {
                    par1World.spawnEntityInWorld(new EntityItem(par1World, (double)par2 + 0.5D, (double)par3 + 1.5D, (double)par4 + 0.5D, newstack));
                }
				return true;
			}
		}
        return false;
    }
	
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        byte b0 = 0;
        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            b0 = 0;
        }

        if (l == 1)
        {
            b0 = 3;
        }

        if (l == 2)
        {
            b0 = 6;
        }

        if (l == 3)
        {
            b0 = 9;
        }

        par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
    }

}
