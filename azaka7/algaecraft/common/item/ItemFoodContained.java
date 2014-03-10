package azaka7.algaecraft.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.CommonProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFoodContained extends ItemFood {
	
	private Icon imgIcon;
	private String imgName;
	private ItemStack containerItem;
	
	public ItemFoodContained(int par1, String img, ItemStack container,int healamount, float saturation, boolean par3) {
		super(par1, healamount, saturation, par3);
		this.setMaxStackSize(1);
		imgName = img;
		containerItem = container;
		this.setCreativeTab(AlgaeCraftMain.modTab);
	}
	
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
        return this.containerItem;
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
