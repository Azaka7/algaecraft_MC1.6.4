package azaka7.algaecraft.common.container;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import azaka7.algaecraft.common.entity.EntityLobster;
import azaka7.algaecraft.common.entity.ModelLobster;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityRendererLobsterCage extends TileEntitySpecialRenderer {
	
	private ModelBase model;
	private final ModelLobster lobsterModel = new ModelLobster();
	private final ModelLobsterCage modelCage = new ModelLobsterCage();
	private static final ResourceLocation mainTexture = new ResourceLocation("azaka7:textures/blocks/LobsterCageBlock.png");

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
			double d2, float f) {
		
		if(tileentity instanceof TileEntityCage){
			this.renderContained((TileEntityCage) tileentity, d0, d1, d2, f);
		}
		/*
		// TODO Auto-generated method stub
		if(((TileEntityCage)tileentity).containedMob !=null){
			String mob = ((TileEntityCage)tileentity).containedMob;
			RenderLiving model = EnumCageable.getRenderFromEnum(EnumCageable.getEnumFromName(mob));
			
			if(model != null){
				System.out.println(EnumCageable.getEntityFromType(EnumCageable.getEnumFromName(mob)) !=null);
			model.doRender((EnumCageable.getEntityFromType(EnumCageable.getEnumFromName(mob))), d0+0.5, d1+0.25, d2+0.5, f, f);
			}
			else{System.out.println("no model");
			}
			//ModLoader.getMinecraftInstance().renderEngine.bindTexture(par1Str);
			//Use that and other stuff to just plain render the model here. The bindTexture sets up the GL with the texture.
		}*/
		
	}
	
	public void renderContained(TileEntityCage tileentity, double d0, double d1, double d2, float f){
		GL11.glPushMatrix();
		this.bindTexture(mainTexture);
		GL11.glTranslatef((float)d0+0.5F, (float)d1-0.5F, (float)d2+0.5F);
		modelCage.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GL11.glPopMatrix();
		
		tileentity.blockMetadata = -1;
		if(tileentity.getBlockMetadata() == 0){return;}
		
		if(tileentity.getBlockMetadata() == 1){
			
			if(this.model != lobsterModel){
			this.model = lobsterModel;
			}
			GL11.glPushMatrix();
			this.bindTexture(EntityLobster.mainTexture);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glTranslatef((float)d0+0.5F, (float)d1+1.66666F, (float)d2+0.5F);
			GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(this.getAngleFromPosition(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord), 0.0F, 1.0F, 0.0F);
			model.render(null, 0, 0, 0, 0, 0, 0.0625F);
			GL11.glPopMatrix();
			
		}
		/*
		EntityLobster lobster = new EntityLobster(tileentity.worldObj);
		lobster.setPositionAndRotation(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord, 0, 0);
		lobster.setEntityHealth(lobster.getMaxHealth());
		lobster.initCreature();
		RenderLobster renderLobster = new RenderLobster(new ModelLobster(), 0.8F);
		renderLobster.doRender(lobster, tileentity.xCoord, tileentity.yCoord, tileentity.zCoord, 0, 0);
		*/
		//this.bindTextureByName("/mods/azaka7/textures/blocks/LobsterSkin.png");
/*
        float f2 = this.interpolateRotation(par1EntityLiving.prevRenderYawOffset, par1EntityLiving.renderYawOffset, par9);
        float f3 = this.interpolateRotation(0, 0, 0);
        float f4 = par1EntityLiving.prevRotationPitch + (par1EntityLiving.rotationPitch - par1EntityLiving.prevRotationPitch) * par9;
        this.renderLivingAt(par1EntityLiving, d0, d1, d2);
        float f5 = this.handleRotationFloat(par1EntityLiving, par9);
        this.rotateCorpse(par1EntityLiving, f5, f2, par9);
        float f6 = 0.0625F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        this.preRenderCallback(par1EntityLiving, par9);
        GL11.glTranslatef(0.0F, -24.0F * f6 - 0.0078125F, 0.0F);
        float f7 = par1EntityLiving.prevLimbYaw + (par1EntityLiving.limbYaw - par1EntityLiving.prevLimbYaw) * par9;
        float f8 = par1EntityLiving.limbSwing - par1EntityLiving.limbYaw * (1.0F - par9);
     */ 
		/*if(tileentity.containedMob == null){return;}
		ModelBase model = this.modelFromTile(tileentity);
		if(model == null){return;}
		GL11.glPushMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.15F);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
        model.render(null, 0, 0, 0, 0, 0, 0.0625F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
        GL11.glPopMatrix();
        GL11.glDepthMask(true);*/
	}
	
	public float getAngleFromPosition(double x, double y, double z){
		
		return (float) (180*Math.abs(Math.sin((3*x+z)/4))+180*Math.abs(Math.cos((3*z+x)/4))+360*Math.sin((3*y+x+z)/5));
	}

}
