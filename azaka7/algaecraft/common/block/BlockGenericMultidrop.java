package azaka7.algaecraft.common.block;

import java.util.ArrayList;
import java.util.Random;

import azaka7.algaecraft.common.AlgaeCraftMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockGenericMultidrop extends Block {
	
	private ItemStack[] dropList;
	private ItemStack[] valuables;
	private String iconName;
	public Icon icon;

	public BlockGenericMultidrop(int par1, Material par2Material, String icon, ItemStack[] par3List, ItemStack[] par4List) {
		super(par1, par2Material);
		dropList = par3List;
		valuables = par4List;
		iconName = icon;
		this.setCreativeTab(AlgaeCraftMain.modTab);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		icon = par1IconRegister.registerIcon("azaka7:"+iconName);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
    	return icon;
    }
    
    protected boolean canSilkHarvest()
    {
    	return true;
    }
    
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        
        ItemStack mainStack = this.dropList[world.rand.nextInt(dropList.length)];
        ret.add(mainStack.copy());
        
        if(world.rand.nextInt(12) < (fortune^2) || world.rand.nextInt(24) == 0){
            ItemStack valuable = valuables[world.rand.nextInt(valuables.length)];
            ret.add(valuable.copy());
        }

        return ret;
    }

}
