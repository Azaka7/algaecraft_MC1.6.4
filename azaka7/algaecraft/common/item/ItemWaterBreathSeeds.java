package azaka7.algaecraft.common.item;

import azaka7.algaecraft.common.AlgaeCraftMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemWaterBreathSeeds extends ItemBlock {
	
	private String imgName;
	
	public ItemWaterBreathSeeds(int par1, String iconName) {
		super(par1);
		imgName = iconName;
		this.setCreativeTab(AlgaeCraftMain.modTab);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
    {
		this.itemIcon = par1IconRegister.registerIcon("azaka7:"+this.imgName);
    }
	
	public Icon getIconFromDamage(int par1)
    {
    	return this.itemIcon;
    }
	
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }

}
