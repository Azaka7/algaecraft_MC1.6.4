package azaka7.algaecraft.common.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.CommonProxy;
import azaka7.algaecraft.common.entity.EntitySquidMeaty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ItemKnife extends ItemSword {
	
	private Icon knifeImg;
	private String type;
	
	public ItemKnife(int par1, EnumToolMaterial material, String icon) {
		super(par1, material);
		this.maxStackSize = 1;
		this.setMaxDamage(150);
		this.setCreativeTab(AlgaeCraftMain.modTab);
		this.type = icon;
	}
	
	//public int getMaxDamage(){return 150;}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		knifeImg = par1IconRegister.registerIcon("azaka7:knife"+type);
    }
	public Icon getIconFromDamage(int par1)
    {
    	return knifeImg;
    }
	
	public String getTextureFile(){
		return CommonProxy.itemsImg;
	}
	
	public boolean hasContainerItem()
    {
        return true;
    }
	
	public ItemStack getContainerItemStack(ItemStack itemStack)
    {
		itemStack.setItemDamage(itemStack.getItemDamage()+1);
		return itemStack;
    }
	
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack)
    {
		return false;
    }
	
	 public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
	    {
		if(par2EntityLiving.worldObj.isRemote){
	    }
	    else if(par2EntityLiving instanceof EntitySquid && !(par2EntityLiving instanceof EntitySquidMeaty) && par2EntityLiving.getHealth()>0){
			par2EntityLiving.entityDropItem(new ItemStack(AlgaeCraftMain.itemSquidRaw, 2, 0), 0.0F);
			par2EntityLiving.attackEntityFrom(DamageSource.causeMobDamage(par3EntityLiving), 6);
		}
	    par1ItemStack.damageItem(1, par3EntityLiving);
	    return true;
	    }
	 
	 public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	    {
	        return false;
	    }
}
