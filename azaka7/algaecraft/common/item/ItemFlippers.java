package azaka7.algaecraft.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.entity.ModelFlippers;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemFlippers extends ItemArmor {
	
	private String itemImg;
	private String armorImg;
	private Icon itemIcon;

	public ItemFlippers(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, String itemName, String armorName) {
		super(par1, par2EnumArmorMaterial, par3, 3);
		itemImg = itemName;
		armorImg = armorName;
		this.setCreativeTab(AlgaeCraftMain.modTab);
	}
	
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack)
    {
		if(player.capabilities.isCreativeMode && player.capabilities.isFlying){
			return;
		}
		if(player.isInWater()){
			player.motionX *= 1.08F;
			player.motionZ *= 1.08F;
			if(player.motionY >0){
				player.motionY *= 1.12F;
			}
		}
		else{
			player.motionX *= 0.649F;
			player.motionZ *= 0.649F;
		}
    }
	
	@SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
    {
		//if(armorSlot == 2){
		ModelBiped model = new ModelFlippers(0.05F);
		model.isSneak = entityLiving.isSneaking();
        return model;
		//}
		//return null;
    }
	
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
        return "azaka7:textures/armor/"+armorImg+".png";
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		itemIcon = par1IconRegister.registerIcon("azaka7:"+itemImg);
    }
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1)
    {
		return this.itemIcon;
    }
	
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		if(AlgaeCraftMain.itemFlippers.itemID == par1ItemStack.itemID){
			return AlgaeCraftMain.itemRubberBall.itemID == par2ItemStack.itemID || super.getIsRepairable(par1ItemStack,par2ItemStack) ;
		}
		else{
			return super.getIsRepairable(par1ItemStack, par2ItemStack);
		}
	}

}
