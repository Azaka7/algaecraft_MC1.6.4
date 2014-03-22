package azaka7.algaecraft.client;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.biome.BiomeGenBase;
import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.fogClarityAPI.EnumFogType;
import azaka7.fogClarityAPI.FogClarityAPI;
import azaka7.fogClarityAPI.FogColor;
import azaka7.fogClarityAPI.IClarityDefiner;

public class FogHandler implements IClarityDefiner {
	
	public static void register(){
		//FogClarityAPI.registerClarityDefiner(new FogHandler());
	}

	@Override
	public boolean isTypeEffectiveInCase(EnumFogType type, EntityLivingBase entity) {
		if(entity instanceof EntityPlayer){
			if(((EntityPlayer) entity).inventory.armorInventory[3]!= null && ((EntityPlayer) entity).inventory.armorInventory[3].getItem() == AlgaeCraftMain.itemScubaGoggles){
				return type == EnumFogType.WATER;
			}
		}
		return false;
	}

	@Override
	public float getClarityMultiplierFromTypeAndCase(EnumFogType type, EntityLivingBase entity) {
		if(type == EnumFogType.WATER){
			float f = EnchantmentHelper.getRespiration(entity)*0.01F;
			return 0.04F - f;
		}
		return 1.0F;
	}

	@Override
	public boolean shouldColorizeFogInCase(EnumFogType type, EntityLivingBase entity) {
		return this.isTypeEffectiveInCase(type, entity);
	}

	@Override
	public FogColor getColorForTypeAndCase(EnumFogType type,EntityLivingBase entity) {
		float g = 0;
		float f = EnchantmentHelper.getRespiration(entity)*0.05F;
		for(int s = 0; s < AlgaeCraftMain.biomeIDSwampList.length; s++){
			if(AlgaeCraftMain.biomeIDSwampList[s] == entity.worldObj.getBiomeGenForCoords((int) Math.round(entity.posX),(int) Math.round(entity.posZ)).biomeID){
				g = 0.3F;
				break;
			}
		}
		return new FogColor(0.4F,0.4F + g,0.7F + f);
	}

}
