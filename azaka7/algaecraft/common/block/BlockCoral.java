package azaka7.algaecraft.common.block;

import java.util.List;
import java.util.Random;

import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.CommonProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BlockCoral extends Block {
	
	public final String[] coralList = new String[]{"orange", "purple", "brain", "blue"};
	public Icon[] iconList = new Icon[16];
	
	public BlockCoral(int id)
    {
        super(id, Material.water);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setLightValue(0.4F);
        this.setTickRandomly(true);
    }
	
	public String getTextureFile(){
		return CommonProxy.blocksImg;
	}
    
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		if(par5EntityPlayer.inventory.getCurrentItem()==null){
			int meta = this.getDamageValue(par1World, par2, par3, par4);
			if(meta >= 0 &&  meta <= 3){
				par1World.setBlockMetadataWithNotify(par2, par3, par4, meta+4, 3);
				this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(this.blockID,1,meta+4));
			}
			return true;
		}
		if(par5EntityPlayer.inventory.getCurrentItem().itemID== Item.dyePowder.itemID && par5EntityPlayer.inventory.getCurrentItem().getItemDamage() == 15){
			Random rand = new Random();
			if(this.tryToGrow(par1World, par1World.getBlockMetadata(par2, par3, par4), par2, par3, par4, rand)
			&&!par5EntityPlayer.capabilities.isCreativeMode){
				par5EntityPlayer.inventory.getCurrentItem().stackSize--;
			}
		}
		return true;
    }
    
    public int getDamageValue(World par1World, int par2, int par3, int par4)
    {
        return par1World.getBlockMetadata(par2, par3, par4);
    }
    
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	if(!this.canBlockStay(par1World, par2, par3, par4)){
    		this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(this.blockID,1,par1World.getBlockMetadata(par2, par3, par4)));
    		par1World.setBlock(par2, par3, par4, Block.waterStill.blockID, 0, 2);
    	}
    	else if(par1World.getBlockId(par2, par3-1, par4) ==0){
    		this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(this.blockID,1,par1World.getBlockMetadata(par2, par3, par4)));
    		par1World.setBlock(par2, par3, par4, Block.waterStill.blockID, 0, 2);
    	}
    	if(par5Random.nextInt(10)==0 && par5Random.nextBoolean()){
    		int metadata = par1World.getBlockMetadata(par2, par3, par4);
    		this.tryToGrow(par1World,metadata,par2,par3,par4, par5Random);
    	}
        super.updateTick(par1World, par2, par3, par4, par5Random);
    }
    
    private boolean tryToGrow(World par1World, int par2Metadata, int x, int y, int z, Random r){
    	if(this.canBlockStay(par1World, x, y, z) && par2Metadata <= 7 && par2Metadata >= 4){
    		par1World.setBlock(x, y, z, this.blockID, par2Metadata-4, 2);
    		return true;
    	}
    	else if(this.canBlockStay(par1World, x, y, z) && par2Metadata <= 3 && par2Metadata >= 0 && r.nextBoolean()){
    		//System.out.println("Secrets await....");
    		return false;
    	}
    	return false;
    }
    
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
        for(int i = 0; i < AlgaeCraftMain.canPlantCoralOn.length; i++){
        	if(var6 == AlgaeCraftMain.canPlantCoralOn[i]){
        		block = true;
        		break;
        	}
        }
        if(block && (var7 == Block.waterMoving.blockID||var7 == Block.waterStill.blockID)){
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
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
    	for (int i = 0; i < this.coralList.length; ++i)
        {
            iconList[i] = par1IconRegister.registerIcon("azaka7:coral_"+coralList[i]);
        }
    	for (int i = coralList.length; i < 2*coralList.length; ++i)
        {
            iconList[i] = par1IconRegister.registerIcon("azaka7:coral_"+coralList[i-coralList.length]+"_small");
        }
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return iconList[par2];
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1;
    }

    /**
     * Takes a dye damage value and returns the block damage value to match
     */
    public static int getBlockFromDye(int par0)
    {
        return ~par0 & 15;
    }

    /**
     * Takes a block damage value and returns the dye damage value to match
     */
    public static int getDyeFromBlock(int par0)
    {
        return ~par0 & 15;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int var4 = 0; var4 < 8; ++var4)
        {
            par3List.add(new ItemStack(par1, 1, var4));
        }
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
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
    	return AlgaeCraftMain.coralModelID;
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
    	
    	if (m==6){//0.375F, 0.0F, 0.375F, 0.625F, 0.25F, 0.625F
    		par5 = 0.375F;
        	par6 = 0.375F;
        	par7 = 0.625F;
        	parH = 0.25F;
        	par8 = 0.625F;
    	}
    	else if (m==2){//0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F
    		par5 = 0.25F;
        	par6 = 0.25F;
        	par7 = 0.75F;
        	parH = 0.5F;
        	par8 = 0.75F;
    	}
    	else{//0.125F, 0.0F, 0.125F, 0.875F, 0.75F, 0.875F
    		par5 = 0.25F;
        	par6 = 0.25F;
        	par7 = 0.75F;
        	parH = 0.75F;
        	par8 = 0.75F;
    	}
    	
    	this.setBlockBounds(par5, 0.0F, par6, par7, parH, par8);
    }
    
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
    	if(!this.canBlockStay(par1World, par2, par3, par4)){
    		this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(this.blockID,1,par1World.getBlockMetadata(par2, par3, par4)));
    		par1World.setBlock(par2, par3, par4, Block.waterStill.blockID, 0, 2);
    	}
    }
    
    public boolean isBlockReplaceable(World world, int x, int y, int z)
    {
    	return false;
    }
}
