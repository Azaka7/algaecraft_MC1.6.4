package azaka7.algaecraft.common.block;

import java.util.ArrayList;
import java.util.Random;

import azaka7.algaecraft.common.AlgaeCraftMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class BlockWaterBreathPlant extends Block {
	
	private Icon seedling;
	private Icon empty;
	private Icon full;
	
	public BlockWaterBreathPlant(int par1) {
		super(par1, Material.water);
		float f = 0.5F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
		MinecraftForge.EVENT_BUS.register(this);
		this.setLightValue(0.8F);
        this.setTickRandomly(true);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		seedling = par1IconRegister.registerIcon("azaka7:waterBreathPlantSeedling");
		empty = par1IconRegister.registerIcon("azaka7:waterBreathPlantEmpty");
		full = par1IconRegister.registerIcon("azaka7:waterBreathPlantFull");
    }
	
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        int count = quantityDropped(metadata, fortune, world.rand);
        for(int i = 0; i < count; i++)
        {
            int id = idDropped(metadata, world.rand, fortune);
            int amount = metadata == 1 ? 2 : 1;
            if (id > 0)
            {
                ret.add(new ItemStack(id, amount, damageDropped(metadata)));
            }
        }
        return ret;
    }
	
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
		if(player.capabilities.isCreativeMode){return super.removeBlockByPlayer(world, player, x, y, z);}
        return world.getBlockMetadata(x, y, z) > 1 ? world.setBlock(x, y, z, blockID, 1, 2) : super.removeBlockByPlayer(world, player, x, y, z);
    }
	
	/*public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
    {
		super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
		if(par1World.getBlockMetadata(par3, par4, par5) > 1){
			par1World.setBlockMetadataWithNotify(par3, par4, par5, 1, 2);
		}
    }*/
	
	@ForgeSubscribe
	public void fertilized(BonemealEvent event){
		if(event.world.getBlockId(event.X, event.Y, event.Z) != AlgaeCraftMain.blockPlantBreath.blockID){
			return;
		}
		int x = event.X; int y = event.Y; int z = event.Z;
		World world = event.world;
		int metadata = world.getBlockMetadata(x, y, z);
		boolean flag = false;
		if (metadata <= 1){
			world.setBlockMetadataWithNotify(x, y, z, metadata+1, 2);
			flag = true;
		}
		
		if(flag && !event.entityPlayer.capabilities.isCreativeMode){
			event.entityPlayer.inventory.getCurrentItem().stackSize--;
		}
	}
	
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return (par1 <= 1) ? this.blockID : AlgaeCraftMain.itemPlantBreath.itemID;
    }
	
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
        boolean ret = super.canSilkHarvest(world, player, x, y, z, metadata);
        return ret && (metadata == 1);
    }
	
	public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        int var6 = par1World.getBlockId(par2, par3-1, par4);
        int var7 = par1World.getBlockId(par2, par3+1, par4);
        boolean biome = (par1World.getBiomeGenForCoords(par2, par4) == BiomeGenBase.ocean || (AlgaeCraftMain.deepOcean != null && par1World.getBiomeGenForCoords(par2, par4) == AlgaeCraftMain.deepOcean));
        if((var6 == AlgaeCraftMain.blockSediment.blockID||var6 == Block.dirt.blockID||var6 == Block.sand.blockID||var6 == Block.cobblestone.blockID||var6 == Block.cobblestoneMossy.blockID||var6 == Block.sandStone.blockID)&&(var7 == Block.waterMoving.blockID||var7 == Block.waterStill.blockID)){
        	if(biome && !(BlockFilter.isBlockFiltered(par1World, par2, par3, par4, 1)||BlockFilter.isBlockFiltered(par1World, par2, par3, par4, 2))){
        		return true;
        	}
        	if(BlockFilter.isBlockFiltered(par1World, par2, par3, par4, 0)||BlockFilter.isBlockFiltered(par1World, par2, par3, par4, 3)){
        		return true;
        	}
        }
        return false;
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
    	if(par5Random.nextInt(12)==0 && par5Random.nextBoolean()){
    		int metadata = par1World.getBlockMetadata(par2, par3, par4);
    		this.tryToGrow(par1World,metadata,par2,par3,par4, par5Random);
    	}
        super.updateTick(par1World, par2, par3, par4, par5Random);
    }
	
	public boolean isBlockReplaceable(World world, int x, int y, int z)
    {
        return false;
    }
	
	private boolean tryToGrow(World world, int metadata, int x, int y, int z, Random r){
		boolean flag = false;
		if (metadata <= 1){
			world.setBlockMetadataWithNotify(x, y, z, metadata+1, 2);
			flag = true;
		}
		return flag;
    }
	
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        if(super.canPlaceBlockAt(par1World, par2, par3, par4)){
        	return this.canBlockStay(par1World, par2, par3, par4);
        }
        return false;
    }
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
    	if(!this.canBlockStay(par1World, par2, par3, par4)){
    		this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(this.blockID,1,par1World.getBlockMetadata(par2, par3, par4)));
    		par1World.setBlock(par2, par3, par4, Block.waterStill.blockID, 0, 2);
    	}
    }
	
	public boolean canHarvestBlock(EntityPlayer player, int meta)
    {
        return true;
    }
	
	public Icon getIcon(int par1, int par2)
    {
        return par2 == 0 ? seedling : (par2 == 1 ? empty : full);
    }
	
    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return 1;
    }

}
