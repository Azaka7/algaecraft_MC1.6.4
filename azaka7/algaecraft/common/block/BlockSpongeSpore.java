package azaka7.algaecraft.common.block;

import java.util.Random;

import azaka7.algaecraft.common.AlgaeCraftMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BlockSpongeSpore extends Block {
	
	private int baseBlock;
	
	public BlockSpongeSpore(int par1id, int par2tex){
		super(par1id, Material.water);
		this.setTickRandomly(true);
		this.setStepSound(soundGrassFootstep);
		baseBlock = par2tex;
	}
	//@SideOnly(Side.CLIENT)
    //public void func_94332_a(IconRegister par1IconRegister)
    //{
		//algaeImg = par1IconRegister.func_94245_a("/img/algae");
    //}
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
    {
		return Block.blocksList[baseBlock].getBlockTextureFromSide(par1);
    }
	
	public boolean isBlockReplaceable(World world, int x, int y, int z)
    {
    	return false;
    }
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		if(par5EntityPlayer.inventory.getCurrentItem()==null){
			return true;
		}
		if(par5EntityPlayer.inventory.getCurrentItem().itemID== Item.dyePowder.itemID && par5EntityPlayer.inventory.getCurrentItem().getItemDamage() == 15){
			if(par1World.getBlockId(par2, par3, par4)==this.blockID&&par1World.getBlockMetadata(par2, par3, par4)<=1){
    			par1World.setBlock(par2, par3, par4, this.blockID, par1World.getBlockMetadata(par2, par3, par4)+1, 2);
    			if(!par5EntityPlayer.capabilities.isCreativeMode){
    				par5EntityPlayer.inventory.getCurrentItem().stackSize--;
    			}
    		}
    		else if(par1World.getBlockId(par2, par3, par4)==this.blockID&&par1World.getBlockMetadata(par2, par3, par4)==2){
    			par1World.setBlock(par2, par3, par4, baseBlock, 0, 2);
    			if(!par5EntityPlayer.capabilities.isCreativeMode){
    				par5EntityPlayer.inventory.getCurrentItem().stackSize--;
    			}
    		}
		}
		return true;
    }
	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
		if (!par1World.isRemote)
        {
        if (!this.canBlockStay(par1World, par2, par3, par4))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlock(par2, par3, par4, Block.waterStill.blockID, 0, 2);
        }
        if((par5Random.nextInt(10-(Math.round(par1World.getBlockLightValue(par2, par3, par4)/5)))==0)){
    		if(par1World.getBlockId(par2, par3, par4)==this.blockID&&par1World.getBlockMetadata(par2, par3, par4)<=1){
    			par1World.setBlock(par2, par3, par4, this.blockID, par1World.getBlockMetadata(par2, par3, par4)+1, 2);
    		}
    		else if(par1World.getBlockId(par2, par3, par4)==this.blockID&&par1World.getBlockMetadata(par2, par3, par4)==2){
    			par1World.setBlock(par2, par3, par4, baseBlock, 0, 2);
    		}
    	}
        }
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        int var6 = par1World.getBlockId(par2, par3-1, par4);
        int var7 = par1World.getBlockId(par2, par3+1, par4);
        boolean biome = false;
        for(int i = 0; i < AlgaeCraftMain.biomeIDOceanList.length; i++){
        	if(par1World.getBiomeGenForCoords(par2, par4).biomeID == AlgaeCraftMain.biomeIDOceanList[i]){
        		biome = true;
        		break;
        	}
        }
        boolean block = false;
        for(int i = 0; i < AlgaeCraftMain.canPlantSpongeOn.length; i++){
        	if(var6 == AlgaeCraftMain.canPlantSpongeOn[i]){
        		block = true;
        		break;
        	}
        }
        if(block&&(var7 == Block.waterMoving.blockID||var7 == Block.waterStill.blockID)){
        	if(biome && !(BlockFilter.isBlockFiltered(par1World, par2, par3, par4, 1)||BlockFilter.isBlockFiltered(par1World, par2, par3, par4, 2))){
        		
        		return true;
        	}
        	if(BlockFilter.isBlockFiltered(par1World, par2, par3, par4, 0)||BlockFilter.isBlockFiltered(par1World, par2, par3, par4, 3)){
        		return true;
        	}
        }
        return false;
    }
    
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        if(super.canPlaceBlockAt(par1World, par2, par3, par4)){
        	return this.canBlockStay(par1World, par2, par3, par4);
        }
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return AlgaeCraftMain.spongeModelID;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
    	int m = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
    	float par5 = 0;
    	float par6 = 0;
    	float par7 = 1;
    	float parH = 1;
    	float par8 = 1;
    	
    	if (m==0){//0.375F, 0.0F, 0.375F, 0.625F, 0.25F, 0.625F
    		par5 = 0.375F;
        	par6 = 0.375F;
        	par7 = 0.625F;
        	parH = 0.25F;
        	par8 = 0.625F;
    	}
    	else if (m==1){//0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F
    		par5 = 0.25F;
        	par6 = 0.25F;
        	par7 = 0.75F;
        	parH = 0.5F;
        	par8 = 0.75F;
    	}
    	else if (m==2){//0.125F, 0.0F, 0.125F, 0.875F, 0.75F, 0.875F
    		par5 = 0.125F;
        	par6 = 0.125F;
        	par7 = 0.875F;
        	parH = 0.75F;
        	par8 = 0.875F;
    	}
    	
    	this.setBlockBounds(par5, 0.0F, par6, par7, parH, par8);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!this.canBlockStay(par1World, par2, par3, par4))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlock(par2, par3, par4, 0, 0, 2);
        }
    }

    public static int func_72219_c(int par0)
    {
        return (par0 & 12) >> 2;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        if(par5 == 0){
        	this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(this, 1));
        }
        if(par5 == 1){
        	this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(this, 2));
        }
        if(par5 == 2){
        	this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(this == AlgaeCraftMain.blockSpongeRedSpore ? AlgaeCraftMain.itemSpongeRed : AlgaeCraftMain.itemSponge, 1));
        	this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(this, 1));
        }
    }

}
