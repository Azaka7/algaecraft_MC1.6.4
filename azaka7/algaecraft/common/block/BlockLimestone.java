package azaka7.algaecraft.common.block;

import java.util.List;

import azaka7.algaecraft.common.AlgaeCraftMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockLimestone extends Block {
	
	private String[] nameList = new String[]{"","Brick","Chiseled","Tile"};
	private Icon[] iconList = new Icon[4];
	
	public BlockLimestone(int par1) {
		super(par1, Material.rock);
		this.setCreativeTab(AlgaeCraftMain.modTab);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
    	for (int i = 0; i < this.nameList.length; ++i)
        {
            iconList[i] = par1IconRegister.registerIcon("azaka7:limestone"+nameList[i]);
        }
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
    	if(par1 == 1 || par1 == 0){
    		if(par2 == 1 || par2 == 3){
    			return iconList[par2 & 3];
    		}
    		return iconList[0];
    	}
    	return iconList[par2 & 3];
    }
    
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int var4 = 0; var4 < 4; ++var4)
        {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }
    
    public int damageDropped(int par1)
    {
        return par1;
    }

}
