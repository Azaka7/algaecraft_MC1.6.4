package azaka7.algaecraft.common.container;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityRendererAirCompressor extends TileEntitySpecialRenderer {
	private static final ResourceLocation mainTexture = new ResourceLocation("azaka7:textures/blocks/ModelAirPressurizerTexture.png");
	private static ModelBase model;
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
			double d2, float f) {
		if(tileentity instanceof TileEntityAirCompressor){
			this.renderCompressor((TileEntityAirCompressor) tileentity, d0, d1, d2, f);
		}
	}
	
	public static int getTankFromMetadata(int metadata){
		if(metadata == 1 || metadata == 4 || metadata == 7 || metadata == 10){
			 return 1;	 
		 }
		 if(metadata == 2 || metadata == 5 || metadata == 8 || metadata == 11){
			 return 2; 
		 }
		return 0;
	}
	
	//make itemblock that places appropriate metadata.
	public void renderCompressor(TileEntityAirCompressor tileentity, double d0, double d1, double d2, float f){
		tileentity.updateContainingBlockInfo();
		int i = tileentity.getBlockMetadata();
		model = new ModelAirCompressor(i);
		GL11.glPushMatrix();
		this.bindTexture(mainTexture);
		GL11.glTranslatef((float)d0+0.5F, (float)d1+1.5F, (float)d2+0.5F);
		GL11.glRotatef(180, 0.0F, 0.0F, 1.0F);
		float short1 = 0;
		if (i == 0 || i == 1 || i == 2)
        {
            short1 = 0;
        }

        if (i == 6 || i == 7 ||i == 8)
        {
            short1 = 180;
        }

        if (i == 9 || i == 10 || i == 11)
        {
            short1 = -90;
        }

        if (i == 3 || i == 4 || i == 5)
        {
            short1 = 90;
        }
		GL11.glRotatef(short1, 0.0F, 1.0F, 0.0F);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);

		GL11.glPopMatrix();
		
		int fullAmount = (this.getTankFromMetadata(i) == 0) ? 0 : ((this.getTankFromMetadata(i) == 1) ? 200 : 800);
		this.renderLivingLabel(this.tileEntityRenderer.entityLivingPlayer, ""+(fullAmount - tileentity.tankDamage)+"/"+fullAmount, d0, d1, d2, 3, tileentity);
		
	}
	
	protected void renderLivingLabel(EntityLivingBase par1EntityLivingBase, String par2Str, double par3, double par5, double par7, int par9, TileEntityAirCompressor tileentity)
    {
		float pitch = -this.tileEntityRenderer.entityLivingPlayer.rotationYawHead;
        float yaw = -this.tileEntityRenderer.entityLivingPlayer.rotationPitch;
        double d0 = tileentity.xCoord+0.5F - par1EntityLivingBase.posX;
        double d1 = tileentity.yCoord+0.5F - par1EntityLivingBase.posY;
        double d2 = tileentity.zCoord+0.5F - par1EntityLivingBase.posZ;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;
		
        if (d3 <= (double)(par9 * par9))
        {
        	d0 = tileentity.xCoord - par1EntityLivingBase.posX;
            d1 = tileentity.yCoord - par1EntityLivingBase.posY;
            d2 = tileentity.zCoord - par1EntityLivingBase.posZ;
            
            FontRenderer fontrenderer = this.tileEntityRenderer.getFontRenderer();
            float f = 1.6F;
            float f1 = 0.016666668F * f;
            GL11.glPushMatrix();
    		GL11.glTranslatef((float)d0+0.5F, (float)d1+1.5F, (float)d2+0.5F);
    		GL11.glRotatef(180, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef((float)0.0F, (float)0.25F, (float)0);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            //GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            //GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-pitch, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(yaw, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(180, 0.0F, 0.0F, 1.0F);
            GL11.glScalef(-f1, -f1, f1);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDepthMask(false);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            Tessellator tessellator = Tessellator.instance;
            byte b0 = 0;

            GL11.glDisable(GL11.GL_TEXTURE_2D);
            tessellator.startDrawingQuads();
            int j = fontrenderer.getStringWidth(par2Str) / 2;
            tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
            tessellator.addVertex((double)(-j - 1), (double)(-1 + b0), 0.0D);
            tessellator.addVertex((double)(-j - 1), (double)(8 + b0), 0.0D);
            tessellator.addVertex((double)(j + 1), (double)(8 + b0), 0.0D);
            tessellator.addVertex((double)(j + 1), (double)(-1 + b0), 0.0D);
            tessellator.draw();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            fontrenderer.drawString(par2Str, -fontrenderer.getStringWidth(par2Str) / 2, b0, 553648127);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDepthMask(true);
            fontrenderer.drawString(par2Str, -fontrenderer.getStringWidth(par2Str) / 2, b0, -1);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
        }
    }

}
