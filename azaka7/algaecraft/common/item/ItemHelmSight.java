package azaka7.algaecraft.common.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.fogClarityAPI.EnumFogType;
import azaka7.fogClarityAPI.FogClarityAPI;
import azaka7.fogClarityAPI.FogColor;
import azaka7.fogClarityAPI.IClarityDefiner;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class ItemHelmSight extends ItemArmor {
	
	protected static final ResourceLocation maskOverlay = new ResourceLocation("azaka7:textures/armor/maskOverlay.png");
	
	private String armorImg;
	private String itemImg;
	private Icon itemIcon;
	
	public ItemHelmSight(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, String itemName, String armorName) {
		super(par1, par2EnumArmorMaterial, par3, 0);
        this.setCreativeTab(AlgaeCraftMain.modTab);
        armorImg = armorName;
        itemImg = itemName;
	}
	
	@SideOnly(Side.CLIENT)
    public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks, boolean hasScreen, int mouseX, int mouseY){
		int k = resolution.getScaledWidth();
		int l = resolution.getScaledHeight();
		
		renderMaskOverlay(k,l);
	}
	
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack)
    {
		if(!AlgaeCraftMain.usesFogClarityAPI && player.isInsideOfMaterial(Material.water)){
			player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 202, 0));
			player.getEntityData().setBoolean("AC_Inwater", true);
		}
		else if(!AlgaeCraftMain.usesFogClarityAPI && player.getEntityData().getBoolean("AC_Inwater")){
			player.getEntityData().setBoolean("AC_Inwater", false);
			if(player.isPotionActive(Potion.nightVision)){
				if(player.getActivePotionEffect(Potion.nightVision).duration <= 202){
					player.getActivePotionEffect(Potion.nightVision).duration = 0;
					//player.onChangedPotionEffect(new PotionEffect(Potion.nightVision.id,1), true);
				}
			}
		}
    }
	
	protected void renderMaskOverlay(int par1, int par2)
    {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        Minecraft.getMinecraft().getTextureManager().bindTexture(maskOverlay);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, (double)par2, -90.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV((double)par1, (double)par2, -90.0D, 1.0D, 1.0D);
        tessellator.addVertexWithUV((double)par1, 0.0D, -90.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
        return "azaka7:textures/armor/"+armorImg+".png";
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		itemIcon = par1IconRegister.registerIcon("azaka7:"+itemImg);
    }
	public Icon getIconFromDamage(int par1)
    {
		return this.itemIcon;
    }
	
	public boolean isValidArmor(ItemStack stack, int armorType, Entity entity)
    {
		return armorType == 0;
    }
	
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		if(AlgaeCraftMain.itemScubaGoggles.itemID == par1ItemStack.itemID){
			return AlgaeCraftMain.itemRubberBall.itemID == par2ItemStack.itemID || super.getIsRepairable(par1ItemStack,par2ItemStack) ;
		}
		else{
			return super.getIsRepairable(par1ItemStack, par2ItemStack);
		}
	}

}
