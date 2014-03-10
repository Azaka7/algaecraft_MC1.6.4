package azaka7.algaecraft.client;

import java.util.EnumSet;

import azaka7.algaecraft.common.AlgaeCraftMain;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;

public class RenderTickHandler implements ITickHandler {
	
	//protected static final ResourceLocation maskOverlay = new ResourceLocation("textures/misc/pumpkinblur.png");
	public static Side side;
	private Minecraft mc = Minecraft.getMinecraft();
	
	public RenderTickHandler(Side s){
		side = s;
	}
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		/*if(mc.thePlayer == null || mc.thePlayer.inventory == null){return;}
		ItemStack helm = mc.thePlayer.inventory.armorInventory[3];
		if(helm != null && helm.itemID == AlgaeCraftMain.itemScubaGoggles.itemID){
			ScaledResolution scaledresolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
			int k = scaledresolution.getScaledWidth();
			int l = scaledresolution.getScaledHeight();
			
			renderPumpkinBlur(k,l);
		}*/
		
	}
	/*
	protected void renderPumpkinBlur(int par1, int par2)
    {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        mc.func_110434_K().func_110577_a(maskOverlay);
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
    }*/

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		/*if(mc.thePlayer == null || mc.thePlayer.inventory == null || mc.currentScreen !=null){return;}
		ItemStack helm = mc.thePlayer.inventory.armorInventory[3];
		if(mc.gameSettings.thirdPersonView == 0 && helm != null && helm.itemID == AlgaeCraftMain.itemScubaGoggles.itemID){
			ScaledResolution scaledresolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
			int k = scaledresolution.getScaledWidth();
			int l = scaledresolution.getScaledHeight();
			
			renderPumpkinBlur(k,l);
		}*/
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER);
	}

	@Override
	public String getLabel() {
		return "AlgaeCraftRenderTick";
	}

}
