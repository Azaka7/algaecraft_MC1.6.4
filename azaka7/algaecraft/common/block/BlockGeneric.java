package azaka7.algaecraft.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class BlockGeneric extends Block {
	
	private String name;
	
	private String iconName;
	private Icon icon;
	
	public BlockGeneric(int par1, Material par2Material, float durable, float resist, String img, String local) {
		super(par1, par2Material);
		this.setHardness(durable);
		this.setResistance(par1);
		iconName = img;
		name = local;
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

}
