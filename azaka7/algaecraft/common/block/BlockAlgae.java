package azaka7.algaecraft.common.block;

import java.util.Random;

import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.CommonProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

public class BlockAlgae extends BlockFlower {
	
	private Icon algaeImg;
	
	public BlockAlgae(int par1, int texture) {
		super(par1, Material.grass);
		float var3 = 0.5F;
        float var4 = 0.015625F;
		this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var4, 0.5F + var3);
		this.setStepSound(soundSnowFootstep);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		algaeImg = par1IconRegister.registerIcon("azaka7:algae");
    }
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
    {
		return algaeImg;
    }
	
	public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
		boolean isSwamp = false;
		if(AlgaeCraftMain.biomeIDSwampList.length > 1){
			for(int n = 0; n<AlgaeCraftMain.biomeIDSwampList.length; n++){
				if(AlgaeCraftMain.biomeIDSwampList[n]==par1World.getBiomeGenForCoords(par2, par4).biomeID|| BiomeGenBase.swampland.biomeID==par1World.getBiomeGenForCoords(par2, par4).biomeID){
					isSwamp=true;
					break;
				}
			}
		}
		else{
			BiomeGenBase[] swamps = BiomeDictionary.getBiomesForType(BiomeDictionary.Type.SWAMP);
			for(int n=0;n<swamps.length;n++){
				if(swamps[n].biomeID==par1World.getBiomeGenForCoords(par2, par4).biomeID || BiomeGenBase.swampland.biomeID==par1World.getBiomeGenForCoords(par2, par4).biomeID){
					isSwamp=true;
					break;
				}
			}
		}
		if(par1World.getBlockMaterial(par2, par3-1, par4) == Material.water){
			if(isSwamp && !(BlockFilter.isBlockFiltered(par1World, par2, par3, par4, 0)||BlockFilter.isBlockFiltered(par1World, par2, par3, par4, 2)))
			{
			return true;
			}
			if(BlockFilter.isBlockFiltered(par1World, par2, par3, par4, 1)||BlockFilter.isBlockFiltered(par1World, par2, par3, par4, 3)){
				return true;
			}
			
		}
		return false;
    }
	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.updateTick(par1World, par2, par3, par4, par5Random);
        if (par5Random.nextInt(8) == 0){
        	if(this.canBlockStay(par1World, par2+1, par3, par4)&&par1World.getBlockId(par2+1, par3, par4)==0){
        		par1World.setBlock(par2+1, par3, par4, this.blockID, 0, 2);
        	}
        }
        if (par5Random.nextInt(8) == 0){
        	if(this.canBlockStay(par1World,par2-1, par3, par4)&&par1World.getBlockId(par2-1, par3, par4)==0){
        		par1World.setBlock(par2-1, par3, par4, this.blockID, 0, 2);
        	}
        }
        if (par5Random.nextInt(8) == 0){
        	if(this.canBlockStay(par1World,par2, par3, par4+1)&&par1World.getBlockId(par2, par3, par4+1)==0){
        		par1World.setBlock(par2, par3, par4+1, this.blockID, 0, 2);
        	}
        }
        if (par5Random.nextInt(8) == 0){
        	if(this.canBlockStay(par1World,par2, par3, par4-1)&&par1World.getBlockId(par2, par3, par4-1)==0){
        		par1World.setBlock(par2, par3, par4-1, this.blockID, 0, 2);
        	}
        }
    }
	
	protected boolean canThisPlantGrowOnThisBlockID(int par1)
    {
        return par1 == Block.waterStill.blockID;
    }
	
	public int getRenderType()
    {
        return AlgaeCraftMain.algaeModelID;
    }
	
	public int getRenderBlockPass()
    {
        return 1;
    }
	
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return (par1IBlockAccess.getBlockId(par2, par3, par4)==0 || !Block.blocksList[par1IBlockAccess.getBlockId(par2, par3, par4)].isOpaqueCube()) && !(par1IBlockAccess.getBlockId(par2, par3, par4)==this.blockID) || par5==1;
    }
	
	@SideOnly(Side.CLIENT)
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
		//if(!(par5Entity.posY-2 > par3+0.025)){
		//par5Entity.setVelocity(par5Entity.motionX/1.15, par5Entity.motionY/1.1, par5Entity.motionZ/1.15);
		//}
		par5Entity.motionX /= 1.15;
		par5Entity.motionY /= 1.1;
		par5Entity.motionZ /= 1.15;
	}
	
	public String getTextureFile(){
		return CommonProxy.blocksImg;
	}

}
