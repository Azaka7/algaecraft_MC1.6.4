package azaka7.fogClarityAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.EntityLivingBase;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.Mod;

@Mod(modid = "fogclarityAPI", name = "Fog Clarity API", version = "1.0.1")
public class FogClarityAPI {
	
	//private static Map classMap = new HashMap<? extends IClarityDefiner,EnumFogType[]>();
	private static List implementerList = new ArrayList<IClarityDefiner>();
	
	public static void registerClarityDefiner(IClarityDefiner definer){
		implementerList.add(definer);
	}
	
	public static boolean checkModClarityDefiners(EnumFogType type, EntityLivingBase entity){
		boolean flag = false;
		for(int i = 0; i < implementerList.size() && !flag; i++){
			IClarityDefiner definer = (IClarityDefiner) implementerList.get(i);
			if(definer.isTypeEffectiveInCase(type, entity)){
				GL11.glFogf(GL11.GL_FOG_DENSITY, definer.getClarityMultiplierFromTypeAndCase(type, entity));
				flag = true;
			}
		}
		return flag;
	}
	
	public static FogColor getColorFromClarityDefiners(EnumFogType type, EntityLivingBase entity){
		boolean flag = false;
		FogColor color = null;
		for(int i = 0; i < implementerList.size() && !flag; i++){
			IClarityDefiner definer = (IClarityDefiner) implementerList.get(i);
			if(definer.shouldColorizeFogInCase(type, entity)){
				//GL11.glFogf(GL11.GL_FOG_DENSITY, definer.getClarityMultiplierFromTypeAndCase(type, entity));
				color = definer.getColorForTypeAndCase(type, entity);
				flag = true;
			}
		}
		return color;
	}
	
	
	
}
