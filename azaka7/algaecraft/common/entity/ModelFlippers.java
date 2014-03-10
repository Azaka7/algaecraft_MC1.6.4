package azaka7.algaecraft.common.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFlippers extends ModelBiped
{
    ModelRenderer flipperLeft;
    ModelRenderer flipperRight;
    
    float flipperLeftX;
    float flipperLeftY;
    float flipperLeftZ;
    
    float flipperRightX;
    float flipperRightY;
    float flipperRightZ;
  
  public ModelFlippers()
  {
	  super(0F);
	  ini(0F);
  }
  
  public ModelFlippers(float f) {
	super(f);
	ini(f);
}
  
  public void ini(float f){
	  
	    textureWidth = 64;
	    textureHeight = 32;
	    
		bipedHeadwear.cubeList.clear();
	    
	      flipperLeft = new ModelRenderer(this, 44, 0);
	      flipperLeft.addBox(0F, 0F, 0F, 4, 1, 6, f);
	      flipperLeft.setRotationPoint(4F, 23F, 0F);
	      flipperLeft.setTextureSize(64, 32);
	      flipperLeft.mirror = true;
	      setRotation(flipperLeft, 0F, 0F, 0F);
	      flipperRight = new ModelRenderer(this, 44, 0);
	      flipperRight.addBox(0F, 0F, 0F, 4, 1, 6,f);
	      flipperRight.setRotationPoint(0F, 23F, 0F);
	      flipperRight.setTextureSize(64, 32);
	      flipperRight.mirror = true;
	      setRotation(flipperRight, 0F, 0F, 0F);
	      
	      flipperLeftX = flipperLeft.rotationPointX;
	      flipperLeftY = flipperLeft.rotationPointY;
	      flipperLeftZ = flipperLeft.rotationPointZ;
	      
	      flipperRightX = flipperRight.rotationPointX;
	      flipperRightY = flipperRight.rotationPointY;
	      flipperRightZ = flipperRight.rotationPointZ;
	      
  }

public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setFlipperRotationAngles(f, f1, f2, f3, f4, f5, entity);
    flipperLeft.render(f5);
    flipperRight.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setFlipperRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
	  flipperLeft.rotateAngleX = -super.bipedLeftLeg.rotateAngleX;
	  flipperLeft.rotateAngleY = (float) (super.bipedLeftLeg.rotateAngleY+Math.PI);
	  flipperLeft.rotateAngleZ = super.bipedLeftLeg.rotateAngleZ;
	  
	  flipperLeft.rotationPointY = (float) (((flipperLeftY-super.bipedLeftLeg.rotationPointY)*Math.cos(Math.abs(super.bipedLeftLeg.rotateAngleX)))+super.bipedLeftLeg.rotationPointY);
	  
	  flipperLeft.rotationPointZ = (float) (((flipperLeftY-super.bipedLeftLeg.rotationPointY)*Math.sin(super.bipedLeftLeg.rotateAngleX))+flipperLeftZ);
	  
	  flipperRight.rotateAngleX = -super.bipedRightLeg.rotateAngleX;
	  flipperRight.rotateAngleY = (float) (super.bipedRightLeg.rotateAngleY+Math.PI);
	  flipperRight.rotateAngleZ = super.bipedRightLeg.rotateAngleZ;
	  
	  flipperRight.rotationPointY = (float) (((flipperRightY-super.bipedRightLeg.rotationPointY)*Math.cos(Math.abs(super.bipedRightLeg.rotateAngleX)))+super.bipedRightLeg.rotationPointY);
	  
	  flipperRight.rotationPointZ = (float) (((flipperRightY-super.bipedRightLeg.rotationPointY)*Math.sin(super.bipedRightLeg.rotateAngleX))+flipperRightZ);
	  

      if (this.isSneak)
        {
    	  this.flipperLeft.rotationPointZ += 3F;
    	  this.flipperRight.rotationPointZ += 3F;
    	  this.flipperLeft.rotationPointY -= 3F;
    	  this.flipperRight.rotationPointY -= 3F;
        }
  }

}
