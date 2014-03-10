package azaka7.fogClarityAPI;

import net.minecraft.entity.EntityLivingBase;

public interface IClarityDefiner {
	
	public boolean isTypeEffectiveInCase(EnumFogType type, EntityLivingBase entity);
	
	public float getClarityMultiplierFromTypeAndCase(EnumFogType type, EntityLivingBase entity);
	
	public boolean shouldColorizeFogInCase(EnumFogType type, EntityLivingBase entity);
	
	public FogColor getColorForTypeAndCase(EnumFogType type, EntityLivingBase entity);
	
}
