package azaka7.algaecraft.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemFood;
import net.minecraft.util.Icon;
import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.CommonProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFoodBasic extends ItemFood {
	
	private Icon imgIcon;
	private String imgName;
	
	public ItemFoodBasic(int par1, String img, int healamount, float saturation, boolean par3) {
		super(par1, healamount, saturation, par3);
		imgName = img;
		this.setCreativeTab(AlgaeCraftMain.modTab);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		imgIcon = par1IconRegister.registerIcon("azaka7:"+imgName);
    }
	
	public Icon getIconFromDamage(int par1)
    {
		return imgIcon;
    }
	
	public String getTextureFile(){
		return CommonProxy.itemsImg;
	}

}
