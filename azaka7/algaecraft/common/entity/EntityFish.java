package azaka7.algaecraft.common.entity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.BaseAttribute;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import azaka7.algaecraft.common.AlgaeCraftMain;

public class EntityFish extends EntityWaterMob
{
	
	public static final ResourceLocation codTex = new ResourceLocation("azaka7:textures/entities/fish_cod_tex.png");
	public static final ResourceLocation salmonTex = new ResourceLocation("azaka7:textures/entities/fish_salmon_tex.png");
	public static final ResourceLocation clownTex = new ResourceLocation("azaka7:textures/entities/fish_clown_tex.png");
	public static final ResourceLocation pufferTex = new ResourceLocation("azaka7:textures/entities/fish_puffer_tex.png");
	
	//0:cod,1:salmon,2:clown,3:puffer
	public int fishType;
	//Used by puffer to say if it should expand
	public boolean scared;
	
    public EntityFish(World par1World)
    {
        super(par1World);
        this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
    	this.setSize(0.4F, 0.4F);
    	this.setType(-1);
    }
    
    public EntityFish(World par1World, int i){
    	super(par1World);
    	this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
    	/*if(i == -1){
    		int r = this.rand.nextInt(100);
    		if(r<60){
    			i = 0;
    		}
    		else if((r-=60) < 25){
    			i = 1;
    		}
    		else if((r-=25) < 13){
    			i = 3;
    		}
    		else{
    			i = 2;
    		}
    	}
    	else{
    		if(i>3){
    			i=0;
    		}
    	}
		this.fishType = i;*/
    	this.setType(i);
    	this.setSize(0.4F, 0.4F);
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(1.0D);
    }
    
    public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData)
    {
    	par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
    	this.setType(-1);
    	return par1EntityLivingData;
    }
    
    private void setType(int i){
    	if(i == -1){
    		if(this.worldObj.getBiomeGenForCoords((int)Math.round(this.posX), (int)Math.round(this.posY)) == BiomeGenBase.river){
    			i = 0;
    		}
    		else if(this.worldObj.getBiomeGenForCoords((int)Math.round(this.posX), (int)Math.round(this.posY)) == BiomeGenBase.swampland){
    			i = 1;
    		}
    		else if(this.worldObj.getBiomeGenForCoords((int)Math.round(this.posX), (int)Math.round(this.posY)) == BiomeGenBase.mushroomIslandShore){
    			if(this.worldObj.rand.nextBoolean()){
    			i = 2;
    			}else{i=3;}
    		}
    		else{
        		int r = this.rand.nextInt(100);
        		if(r<60){
        			i = 0;
        		}
        		else if((r-=60) < 25){
        			i = 1;
        		}
        		else if((r-=25) < 13){
        			i = 3;
        		}
        		else{
        			i = 2;
        		}
    		}
    	}
    	else{
    		if(i>3){
    			i=0;
    		}
    	}
		this.fishType = i;
    	//this.getEntityData().setInteger("fishtype", i);
		this.dataWatcher.updateObject(13, Byte.valueOf((byte)i));
    }
    
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(13, new Byte((byte)0));
    }
    
    public int getFishType(){
    	return this.dataWatcher.getWatchableObjectByte(13);
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return Block.soundSandFootstep.stepSoundName;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return Block.soundGrassFootstep.stepSoundName;
    }
    
    protected float getSoundVolume()
    {
        return 0.4F;
    }
    
    protected boolean canTriggerWalking()
    {
        return false;
    }
    
    public boolean handleWaterMovement()
    {
    	boolean aqueous = this.inWater;
    	this.inWater = true;
    	boolean handled = super.handleWaterMovement();
    	this.inWater = aqueous;
    	return this.inWater || handled;
    }
    
    protected int getExperiencePoints(EntityPlayer par1EntityPlayer)
    {
    	return 1;
    }
    /*
    public void onEntityUpdate()
    {
        int i = this.getAir();
        super.onEntityUpdate();
        System.out.println("Is in water?"+this.isInWater());
        if (this.isEntityAlive() && this.isInWater())
        {
            --i;
            this.setAir(i);

            if (this.getAir() <= -20)
            {
                this.setAir(0);
                this.attackEntityFrom(DamageSource.drown, 2.0F);
            }
        }
        else
        {
            this.setAir(300);
        }
    }*/

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean par1, int par2)
    {
    	if(this.isBurning()){
    		this.entityDropItem(new ItemStack(Item.fishCooked, 1, 0), 0.0F);
    	}
    	else{
    		this.entityDropItem(new ItemStack(Item.fishRaw, 1, 0), 0.0F);
    	}
    }
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("fishtype", this.fishType);
        par1NBTTagCompound.setBoolean("scared", this.scared);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setType(par1NBTTagCompound.getInteger("fishtype"));
        this.scared = par1NBTTagCompound.getBoolean("scared");
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return null;
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return 0;
    }

    /**
     * Checks if this entity is inside water (if inWater field is true as a result of handleWaterMovement() returning
     * true)
     */
    public boolean isInWater()
    {
        boolean flag = this.worldObj.handleMaterialAcceleration(this.boundingBox.expand(0.0D, /*-0.6000000238418579D*/0.0D, 0.0D), Material.water, this);
        //System.out.println("Is in water:"+flag);
        return flag;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    
    private float rotationVelocity;
    private float field_70871_bB;
    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;
    private float randomMotionSpeed;
    public float squidPitch;
    public float prevSquidPitch;
    public float squidYaw;
    public float prevSquidYaw;
    public float squidRotation;
    public float prevSquidRotation;
    public float tentacleAngle;
    public float prevTentacleAngle;
    
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        
        EntityPlayer nearestPlayerInRange = (EntityPlayer) this.worldObj.findNearestEntityWithinAABB(EntityPlayer.class, this.boundingBox.expand(1D, 1D, 1D), this);
        this.scared = nearestPlayerInRange != null;
        
        if(this.getFishType() == 3 && this.scared){
    		nearestPlayerInRange.addPotionEffect(new PotionEffect(Potion.poison.id, 100, 1, false));
    	}
        
        this.prevSquidPitch = this.squidPitch;
        this.prevSquidYaw = this.squidYaw;
        this.prevSquidRotation = this.squidRotation;
        this.prevTentacleAngle = this.tentacleAngle;
        this.squidRotation += this.rotationVelocity;

        if (this.squidRotation > ((float)Math.PI * 2F))
        {
            this.squidRotation -= ((float)Math.PI * 2F);

            if (this.rand.nextInt(10) == 0)
            {
                this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
            }
        }

        if (this.isInWater())
        {
            float f;

            if (this.squidRotation < (float)Math.PI)
            {
                f = this.squidRotation / (float)Math.PI;
                this.tentacleAngle = MathHelper.sin(f * f * (float)Math.PI) * (float)Math.PI * 0.25F;

                if ((double)f > 0.75D)
                {
                    this.randomMotionSpeed = 1.0F;
                    this.field_70871_bB = 1.0F;
                }
                else
                {
                    this.field_70871_bB *= 0.8F;
                }
            }
            else
            {
                this.tentacleAngle = 0.0F;
                this.randomMotionSpeed *= 0.9F;
                this.field_70871_bB *= 0.99F;
            }

            if (!this.worldObj.isRemote)
            {
                this.motionX = (double)(this.randomMotionVecX * this.randomMotionSpeed);
                this.motionY = (double)(this.randomMotionVecY * this.randomMotionSpeed);
                this.motionZ = (double)(this.randomMotionVecZ * this.randomMotionSpeed);
            }

            f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.renderYawOffset += (-((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI - this.renderYawOffset) * 0.1F;
            this.rotationYaw = this.renderYawOffset;
            this.squidYaw += (float)Math.PI * this.field_70871_bB * 1.5F;
            this.squidPitch += (-((float)Math.atan2((double)f, this.motionY)) * 180.0F / (float)Math.PI - this.squidPitch) * 0.1F;
        }
        else
        {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.squidRotation)) * (float)Math.PI * 0.25F;

            if (!this.worldObj.isRemote)
            {
                this.motionX = 0.0D;
                this.motionY -= 0.08D;
                this.motionY *= 0.9800000190734863D;
                this.motionZ = 0.0D;
            }

            this.squidPitch = (float)((double)this.squidPitch + (double)(-90.0F - this.squidPitch) * 0.02D);
        }
    }

    /**
     * Moves the entity based on the specified heading.  Args: strafe, forward
     */
    public void moveEntityWithHeading(float par1, float par2)
    {
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
    }

    protected void updateEntityActionState()
    {
        ++this.entityAge;

        if (this.entityAge > 100)
        {
            this.randomMotionVecX = this.randomMotionVecY = this.randomMotionVecZ = 0.0F;
        }
        else if (this.rand.nextInt(50) == 0 || !this.inWater || this.randomMotionVecX == 0.0F && this.randomMotionVecY == 0.0F && this.randomMotionVecZ == 0.0F)
        {
            float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
            this.randomMotionVecX = MathHelper.cos(f) * 0.2F;
            this.randomMotionVecY = -0.1F + this.rand.nextFloat() * 0.2F;
            this.randomMotionVecZ = MathHelper.sin(f) * 0.2F;
        }

        this.despawnEntity();
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return this.posY > 32.0D && this.posY < 60.0D && super.getCanSpawnHere() && this.isInWater();
    }
}