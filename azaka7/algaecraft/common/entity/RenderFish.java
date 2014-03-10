package azaka7.algaecraft.common.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFish extends RenderLiving
{
	ModelBase fishPlain = new ModelFish();
	ModelBase fishPuffer = new ModelPufferFish();
	
    public RenderFish(ModelFish modelFish, float f)
    {
        super(modelFish, f);
    }

    /**
     * Return the Fish's maximum death rotation.
     */
    protected float getFishDeathRotation(EntityFish par1EntityFish)
    {
        return 180.0F;
    }

    /**
     * Renders the Fish.
     */
    public void renderFish(EntityFish par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
    	if(par1Entity.getFishType() == 3){
    		this.mainModel = fishPuffer;
    	}
    	else{
    		this.mainModel = fishPlain;
    	}
        super.doRenderLiving(par1Entity, par2, par4, par6, par8, par9);
    }
    
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.scalePuffer((EntityFish)par1EntityLivingBase, par2);
    }
    
    protected void scalePuffer(EntityFish par1EntityFish, float par2)
    {
    	//TODO Test this out!
    	if(par1EntityFish.getFishType() == 3){
    		if(par1EntityFish.scared){
    			GL11.glScalef(0.75F, 0.75F, 0.75F);
    		}
    		else{
    			GL11.glScalef(0.55F, 0.55F, 0.7F);
    		}
    	}
    }

    /**
     * Disallows the Fish to render the renderPassModel.
     */
    protected int shouldFishRenderPass(EntityFish par1EntityFish, int par2, float par3)
    {
        return -1;
    }

    protected float getDeathMaxRotation(EntityLiving par1EntityLiving)
    {
        return this.getFishDeathRotation((EntityFish)par1EntityLiving);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return this.shouldFishRenderPass((EntityFish)par1EntityLiving, par2, par3);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderFish((EntityFish)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderFish((EntityFish)par1Entity, par2, par4, par6, par8, par9);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		if(!(entity instanceof EntityFish)){
			return EntityFish.codTex;
		}
		switch(((EntityFish) entity).getFishType()){
		case 1: return EntityFish.salmonTex;
		case 2: return EntityFish.clownTex;
		case 3: return EntityFish.pufferTex;
		default: return EntityFish.codTex;
		}
	}
}
