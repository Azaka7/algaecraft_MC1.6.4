package azaka7.algaecraft.common.container;

import net.minecraft.client.model.ModelBase;
//Date: 12/2/2013 4:46:22 PM
//Template version 1.1
//Java generated by Techne
//Keep in mind that you still need to fill in some blanks
//- ZeuX
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAirCompressor extends ModelBase
{
//fields
 ModelRenderer Base;
 ModelRenderer Back;
 ModelRenderer Top;
 ModelRenderer Arm;
 ModelRenderer Pipe1;
 ModelRenderer Pipe2;
 ModelRenderer Nodule;
 ModelRenderer TankSmall;
 ModelRenderer TankLarge;
 
 private int metadata;

public ModelAirCompressor()
{
 textureWidth = 64;
 textureHeight = 128;
 
   Base = new ModelRenderer(this, 0, 0);
   Base.addBox(0F, 0F, 0F, 16, 1, 16);
   Base.setRotationPoint(-8F, 23F, -8F);
   Base.setTextureSize(64, 128);
   Base.mirror = false;
   setRotation(Base, 0F, 0F, 0F);
   Back = new ModelRenderer(this, 0, 17);
   Back.addBox(0F, 0F, 0F, 14, 11, 6);
   Back.setRotationPoint(-7F, 12F, 1F);
   Back.setTextureSize(64, 128);
   Back.mirror = false;
   setRotation(Back, 0F, 0F, 0F);
   Top = new ModelRenderer(this, 0, 34);
   Top.addBox(0F, 0F, 0F, 10, 3, 7);
   Top.setRotationPoint(-5F, 9F, 1F);
   Top.setTextureSize(64, 128);
   Top.mirror = false;
   setRotation(Top, 0F, 0F, 0F);
   Arm = new ModelRenderer(this, 0, 44);
   Arm.addBox(0F, 0F, 0F, 4, 2, 13);
   Arm.setRotationPoint(-2F, 8F, -6F);
   Arm.setTextureSize(64, 128);
   Arm.mirror = false;
   setRotation(Arm, 0F, 0F, 0F);
   Pipe1 = new ModelRenderer(this, 60, 17);
   Pipe1.addBox(0F, 0F, 0F, 1, 11, 1);
   Pipe1.setRotationPoint(1F, 12F, 7F);
   Pipe1.setTextureSize(64, 128);
   Pipe1.mirror = true;
   setRotation(Pipe1, 0F, 0F, 0F);
   Pipe2 = new ModelRenderer(this, 60, 17);
   Pipe2.addBox(0F, 0F, 0F, 1, 11, 1);
   Pipe2.setRotationPoint(-2F, 12F, 7F);
   Pipe2.setTextureSize(64, 128);
   Pipe2.mirror = true;
   setRotation(Pipe2, 0F, 0F, 0F);
   Nodule = new ModelRenderer(this, 56, 29);
   Nodule.addBox(0F, 0F, 0F, 2, 1, 2);
   Nodule.setRotationPoint(-1F, 10F, -5F);
   Nodule.setTextureSize(64, 128);
   Nodule.mirror = true;
   setRotation(Nodule, 0F, 0F, 0F);
   TankSmall = new ModelRenderer(this, 0, 59);
   TankSmall.addBox(0F, 0F, 0F, 6, 12, 6);
   TankSmall.setRotationPoint(-3F, 11F, -7F);
   TankSmall.setTextureSize(64, 128);
   TankSmall.mirror = true;
   setRotation(TankSmall, 0F, 0F, 0F);
   TankLarge = new ModelRenderer(this, 24, 59);
   TankLarge.addBox(0F, 0F, 0F, 8, 12, 8);
   TankLarge.setRotationPoint(-4F, 11F, -8F);
   TankLarge.setTextureSize(64, 128);
   TankLarge.mirror = true;
   setRotation(TankLarge, 0F, 0F, 0F);
}

public ModelAirCompressor(int blockMetadata) {
	this();
	metadata = blockMetadata;
}

public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
{
 super.render(entity, f, f1, f2, f3, f4, f5);
 setRotationAngles(f, f1, f2, f3, f4, f5);
 Base.render(f5);
 Back.render(f5);
 Top.render(f5);
 Arm.render(f5);
 Pipe1.render(f5);
 Pipe2.render(f5);
 Nodule.render(f5);
 
 if(metadata == 1 || metadata == 4 || metadata == 7 || metadata == 10){
	 TankSmall.render(f5);	 
 }
 else if(metadata == 2 || metadata == 5 || metadata == 8 || metadata == 11){
	 TankLarge.render(f5); 
 }
 
}

private void setRotation(ModelRenderer model, float x, float y, float z)
{
 model.rotateAngleX = x;
 model.rotateAngleY = y;
 model.rotateAngleZ = z;
 if((metadata & 3) == 3){}
}

public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
{
 super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
}

}
