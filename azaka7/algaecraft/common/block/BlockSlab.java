package azaka7.algaecraft.common.block;

import java.util.List;

import azaka7.algaecraft.common.AlgaeCraftMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.BlockStep;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockSlab extends BlockHalfSlab {
	
	private String[] imgList = new String[]{};
	private String[] nameList = new String[]{};
	private String name;
	private Icon[] iconList = new Icon[8];
	
	public BlockSlab(int par1, boolean par2, Material par3Material,String n, String[] images, String[] unlocalized) {
		super(par1, par2, par3Material);
		nameList = images;
		imgList = unlocalized;
		name = n;
		// TODO Auto-generated constructor stub
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
    	for (int i = 0; i < this.nameList.length && i < 8; ++i)
        {
            iconList[i] = par1IconRegister.registerIcon("azaka7:limestone"+nameList[i]);
        }
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
    	if((par2 & 7) > iconList.length){
    		return iconList[0];
    	}
    	return iconList[par2&7];
    }

	@Override
	public String getFullSlabName(int i) {
		String s = "";
		if((i & 7) < imgList.length){
			s = imgList[i&7];
		}
		return "blockSlab."+name+s;
	}
	
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		if(this.isDoubleSlab){return;}
        for (int var4 = 0; var4 < imgList.length; ++var4)
        {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }
	
	private static boolean isBlockSingleSlab(int par0)
    {
		return (par0 == AlgaeCraftMain.blockLimestoneSlab.blockID);
    }

}
