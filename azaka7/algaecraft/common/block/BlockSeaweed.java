package azaka7.algaecraft.common.block;

import java.util.Random;

import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.CommonProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BlockSeaweed extends Block {
	
	private Icon seaweedImg;
	
	public BlockSeaweed(int par1, int par2) {
		super(par1, Material.water);
		this.setLightOpacity(2);
		this.setTickRandomly(true);
		this.setStepSound(soundGrassFootstep);
	}
	
	public boolean isBlockReplaceable(World world, int x, int y, int z)
    {
    	return false;
    }
	
	public String getTextureFile(){
		return CommonProxy.blocksImg;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		seaweedImg = par1IconRegister.registerIcon("azaka7:seaweed");
    }
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
    {
		return seaweedImg;
    }
	
	public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
		int blockIDBelow = par1World.getBlockId(par2, par3-1, par4);
		//boolean belowIsHard = (blockIDBelow == Block.cobblestone.blockID || blockIDBelow == Block.stone.blockID || blockIDBelow == Block.cobblestoneMossy.blockID || blockIDBelow == Block.gravel.blockID || blockIDBelow == Block.sandStone.blockID);
		boolean aboveIsWater = (par1World.getBlockId(par2, par3+1, par4) == Block.waterStill.blockID || par1World.getBlockId(par2, par3+1, par4) == Block.waterMoving.blockID);
		boolean belowIsSeaweed = (par1World.getBlockId(par2, par3-1, par4)==this.blockID);
		boolean aboveIsSeaweed = (par1World.getBlockId(par2, par3+1, par4)==this.blockID);
		boolean biome = false;
        for(int i = 0; i < AlgaeCraftMain.biomeIDOceanList.length; i++){
        	if(par1World.getBiomeGenForCoords(par2, par4).biomeID == AlgaeCraftMain.biomeIDOceanList[i]){
        		biome = true;
        		break;
        	}
        }
        boolean belowIsHard = false;
        for(int i = 0; i < AlgaeCraftMain.canPlantSeaweedOn.length; i++){
        	if(blockIDBelow == AlgaeCraftMain.canPlantSeaweedOn[i]){
        		belowIsHard = true;
        		break;
        	}
        }
        if((belowIsHard||belowIsSeaweed)&&(aboveIsWater||aboveIsSeaweed)){
        	if(biome &&!(BlockFilter.isBlockFiltered(par1World, par2, par3, par4, 1)||BlockFilter.isBlockFiltered(par1World, par2, par3, par4, 2))){
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
		return this.canBlockStay(par1World, par2, par3, par4);
	}
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
		if(!par1World.isRemote && !this.canBlockStay(par1World, par2, par3, par4)){
			this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(this));
			par1World.setBlock(par2, par3, par4, Block.waterStill.blockID, 0, 3);
		}
    }
	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
        	if(!this.canBlockStay(par1World, par2, par3, par4)){
        		this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(this));
    			par1World.setBlock(par2, par3, par4, Block.waterStill.blockID, 0, 3);
    		}
        	if((par5Random.nextInt(8)==1) && (par5Random.nextInt(200)>(par5Random.nextInt(50)+150))){
        		if(this.canPlaceBlockAt(par1World, par2, par3+1, par4)){
        			par1World.setBlock(par2, par3+1, par4, this.blockID, 0, 3);
        		}
        	}
        }
    }
	
	public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
		if (!par1World.isRemote){
			if(!par6EntityPlayer.capabilities.isCreativeMode){
			super.dropBlockAsItem(par1World, par2, par3, par4, par5, 1);}
		}
	}
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return this.blockID;
    }
	
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6){
		par1World.setBlock(par2, par3, par4, Block.waterStill.blockID, 0, 3);
	}
	
	public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }
	public boolean isOpaqueCube()
    {
        return false;
    }
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	public int getRenderType()
    {
        //return 6;
		return AlgaeCraftMain.seaweedModelID;
    }
	
	public int getRenderBlockPass()
    {
        return 0;
    }
	

}
