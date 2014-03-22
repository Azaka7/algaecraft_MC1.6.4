package azaka7.algaecraft.common.block;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import azaka7.algaecraft.common.AlgaeCraftMain;
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

public class BlockGuayule extends Block {
	
	public static String[] iconNames = new String[]{"Small","Full"};
	public static Icon[] iconList = new Icon[iconNames.length];
	
	public BlockGuayule(int par1) {
		super(par1, Material.plants);
		this.setCreativeTab(AlgaeCraftMain.modTab);
        this.setTickRandomly(true);
        this.setLightOpacity(0);
	}
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
    	for (int i = 0; i < this.iconNames.length; ++i)
        {
            iconList[i] = par1IconRegister.registerIcon("azaka7:guayule"+iconNames[i]);
        }
    }
	
	public boolean isBlockReplaceable(World world, int x, int y, int z)
    {
    	return false;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
    	if(par2 < 3){return iconList[0];}
        return iconList[1];
    }
    
    public int getRenderType()
    {
    	return 1;
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
    
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);
    }

    @Override 
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        
        ret.add(new ItemStack(AlgaeCraftMain.blockGuayule, 1, 0));
        
        if (metadata >= 3)
        {
        	ret.add(new ItemStack(AlgaeCraftMain.itemGuayuleBranch, 1, 0));
            for (int n = 0; n < 2 + fortune; n++)
            {
                if (world.rand.nextInt(8) <= metadata)
                {
                    ret.add(new ItemStack(AlgaeCraftMain.itemGuayuleBranch, 1, 0));
                    if(world.rand.nextInt(4) == 0){
                    	ret.add(new ItemStack(AlgaeCraftMain.blockGuayule, 1, 0));
                    }
                }
            }
        }

        return ret;
    }

    public int quantityDropped(Random par1Random)
    {
        return 1;
    }
    
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return this.blockID;
    }
    
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	if (!this.canBlockStay(par1World, par2, par3, par4))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlock(par2, par3, par4, 0, 0, 2);
        }
    	if(par1World.rand.nextInt(6)==0){
    		int metadata = par1World.getBlockMetadata(par2, par3, par4);
    		if(metadata < 3){
    			par1World.setBlockMetadataWithNotify(par2, par3, par4, metadata+1, 3);
    		}
    		else if(par1World.rand.nextInt(12) == 0){
    			if(metadata == 3 && par1World.rand.nextInt(4) == 0){
    				par1World.setBlock(par2, par3, par4, Block.deadBush.blockID, 0, 3);
    			}
    			else{
    				par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
    			}
    		}
    	}
    	/*
    	if(!this.canBlockStay(par1World, par2, par3, par4)){
    		int metadata = par1World.getBlockMetadata(par2, par3, par4);
    		par1World.setBlock(par2, par3, par4, 0);
    		par1World.setBlockToAir(par2, par3, par4);
    		this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(this));
    		if(metadata == 3){
    			this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(AlgaeCraftMain.itemGuayuleBranch));
    			if(par5Random.nextBoolean()){this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(AlgaeCraftMain.itemGuayuleBranch));}
    			if(par5Random.nextInt(3)==0){
    			this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(this));
    			}
    		}
    	}
    	
    	if(par1World.rand.nextInt(6)==0){
    		int metadata = par1World.getBlockMetadata(par2, par3, par4);
    		if(metadata < 3){
    			par1World.setBlockMetadataWithNotify(par2, par3, par4, metadata+1, 3);
    		}
    		else if(par1World.rand.nextInt(50) == 0){
    			par1World.setBlock(par2, par3, par4, Block.deadBush.blockID, 0, 3);
    		}
    	}
    	
        super.updateTick(par1World, par2, par3, par4, par5Random);*/
    }
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		if(par5EntityPlayer.inventory.getCurrentItem()==null){
			if(par1World.getBlockMetadata(par2, par3, par4)==3 || par1World.getBlockMetadata(par2, par3, par4)==4){
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 3);
				this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(AlgaeCraftMain.itemGuayuleBranch.itemID,par1World.rand.nextInt(2)+1,0));
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
    private boolean tryToGrow(World par1World, int par2Metadata, int x, int y, int z, Random r){
    	int metadata = par1World.getBlockMetadata(x, y, z);
		if(metadata < 3){
			par1World.setBlockMetadataWithNotify(x, y, z, 3, 3);
			return true;
		}
		return false;
    }
    
    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return this.canBlockStay(par1World, par2, par3, par4);
    }
    
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
    	if(!this.canBlockStay(par1World, par2, par3, par4)){
    		int metadata = par1World.getBlockMetadata(par2, par3, par4);
    		par1World.setBlock(par2, par3, par4, 0);
    		par1World.setBlockToAir(par2, par3, par4);
    		this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(this));
    		if(metadata == 3){
    			this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(AlgaeCraftMain.itemGuayuleBranch));
    			if(par1World.rand.nextBoolean()){this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(AlgaeCraftMain.itemGuayuleBranch));}
    			if(par1World.rand.nextInt(3)==0){
    			this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(this));
    			}
    		}
    	}
    }
    
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
    	int var6 = par1World.getBlockId(par2, par3-1, par4);
    	if(var6 == Block.sand.blockID || var6 == Block.gravel.blockID || var6 == Block.dirt.blockID){
    		//BiomeGenBase biome = par1World.getBiomeGenForCoords(par2, par4);
    		//if(!biome.isHighHumidity() && !biome.getEnableSnow() && biome.temperature >= BiomeGenBase.desert.temperature){
    			return true;
    		//}
    	}
		return false;
    	
    }
    public int damageDropped(int par1)
    {
        return 0;
    }
    
    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
    	float par5 = 0.25F;
    	float par6 = 0.25F;
    	float par7 = 0.75F;
    	float parH = 0.75F;
    	float par8 = 0.75F;
    	this.setBlockBounds(par5, 0.0F, par6, par7, parH, par8);
    }
    /*
    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
    {
        par2EntityPlayer.addStat(StatList.mineBlockStatArray[this.blockID], 1);
        par2EntityPlayer.addExhaustion(0.025F);
        int i1 = EnchantmentHelper.getFortuneModifier(par2EntityPlayer);
        this.dropBlockAsItem(par1World, par3, par4, par5, par6, i1);
        int metadata = par6;
        if(metadata == 3){
			this.dropBlockAsItem_do(par1World, par3, par4, par5, new ItemStack(AlgaeCraftMain.itemGuayuleBranch));
			if(par1World.rand.nextBoolean()){this.dropBlockAsItem_do(par1World, par3, par4, par5, new ItemStack(AlgaeCraftMain.itemGuayuleBranch));}
			if(par1World.rand.nextInt(3)==0){
			this.dropBlockAsItem_do(par1World, par3, par4, par5, new ItemStack(this));
			}
		}
    }*/

}
