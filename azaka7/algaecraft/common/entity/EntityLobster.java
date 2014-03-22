package azaka7.algaecraft.common.entity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.container.TileEntityCage;

public class EntityLobster extends EntityWaterMob
{
    public static final int[] oppositeSide = new int[] {1, 0, 3, 2, 5, 4};
    public static final int[] offsetsXForSide = new int[] {0, 0, 0, 0, -1, 1};
    public static final int[] offsetsYForSide = new int[] { -1, 1, 0, 0, 0, 0};
    public static final int[] offsetsZForSide = new int[] {0, 0, -1, 1, 0, 0};
    public static final String[] facings = new String[] {"DOWN", "UP", "NORTH", "SOUTH", "WEST", "EAST"};
	private float randomMotionSpeed = 0.0F;
	private float randomMotionVecX = 0.0F;
    private float randomMotionVecY = 0.0F;
    private float randomMotionVecZ = 0.0F;
    
    public static final ResourceLocation mainTexture = new ResourceLocation("azaka7:textures/blocks/LobsterSkin.png");
    /**
     * A cooldown before this entity will search for another Silverfish to join them in battle.
     */
    private int allySummonCooldown;

    public EntityLobster(World par1World)
    {
        super(par1World);
        this.setSize(0.3F, 0.7F);
        this.stepHeight = 1.05F;
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.56999D);
    }
    
    protected void dropFewItems(boolean par1, int par2)
    {
        int var3 = this.rand.nextInt(2 + par2) + 1;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            super.entityDropItem(new ItemStack(AlgaeCraftMain.itemLobsterRaw,1), 0.0F);
        }
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }
    
    public boolean isInWater()
    {
        return this.worldObj.handleMaterialAcceleration(this.boundingBox.expand(0.0D, -0.6000000238418579D, 0.0D), Material.water, this);
    }
    
    public boolean handleWaterMovement()
    {
    	if(this.worldObj.getBlockId((int)Math.round(this.posX), (int)Math.round(this.posY - 1), (int)Math.round(this.posZ)) != Block.waterStill.blockID && this.motionY == 0){
    		this.motionY = -0.1F;
    	}
    	//if(this.worldObj.getBlockMaterial((int)(Math.floor(this.posX)), (int)(Math.floor(this.posY)), (int)(Math.floor(this.posZ)))==Material.water){
    	if(this.isInWater()){
    		if(this.motionY<0){
    			this.fallDistance = 0;
    		}
    	}
    	return this.isInWater();//this.worldObj.handleMaterialAcceleration(this.boundingBox, Material.water, this);
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
    	double d0 = 8.0D;
    	return this.worldObj.getClosestVulnerablePlayerToEntity(this, d0);
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.silverfish.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.silverfish.hit";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.silverfish.kill";
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
        	//fix when lobster hole is made
        	/*
            if (this.allySummonCooldown <= 0 && (par1DamageSource instanceof EntityDamageSource || par1DamageSource == DamageSource.magic))
            {
                this.allySummonCooldown = 20;
            }
            */

            return super.attackEntityFrom(par1DamageSource, par2);
        }
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity par1Entity, float par2)
    {
    	if (this.attackTime <= 0 && par2 < 2.0F && par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 12;
            this.attackEntityAsMob(par1Entity);
        }
    }
    
    public boolean attackEntityAsMob(Entity par1Entity)
    {
        int i = this.getAttackStrength(par1Entity);

        if (this.isPotionActive(Potion.damageBoost))
        {
            i += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
        }

        if (this.isPotionActive(Potion.weakness))
        {
            i -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
        }

        int j = 0;
        
        boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    	
        if (flag)
        {
            if (j > 0)
            {
                par1Entity.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)j * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)j * 0.5F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            if (par1Entity instanceof EntityLiving)
            {
                EnchantmentThorns.func_92096_a(this, (EntityLiving)par1Entity, this.rand);
            }
        }

        return flag;
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("mob.silverfish.step", 0.15F, 1.0F);
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return 0;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.renderYawOffset = this.rotationYaw;
        super.onUpdate();
    }
    public void onLivingUpdate()
    {
        super.onLivingUpdate();/*
        if (this.isInWater())
        {
            if (!this.worldObj.isRemote)
            {
                this.motionX = (double)(this.randomMotionVecX * this.randomMotionSpeed);
                this.motionY = (double)(this.randomMotionVecY * this.randomMotionSpeed);
                this.motionZ = (double)(this.randomMotionVecZ * this.randomMotionSpeed);
            }
            this.renderYawOffset += (-((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI - this.renderYawOffset) * 0.1F;
            this.rotationYaw = this.renderYawOffset;
        }*/
        int i = this.rand.nextInt(3)-2;
        int x = (int)Math.round(this.posX)+i;
        i = this.rand.nextInt(3)-2;
        int y = (int)Math.round(this.posY)+i;
        i = this.rand.nextInt(3)-2;
        int z = (int)Math.round(this.posZ)+i;
        if(this.worldObj.getBlockId(x, y, z) == AlgaeCraftMain.blockLobsterCageID){
        	if(worldObj.getBlockMetadata(x, y, z) ==0){
        		((TileEntityCage) worldObj.getBlockTileEntity(x, y, z)).setCreature(this);
        		this.setDead();
        	}
        }
        
    }

    protected void updateEntityActionState()
    {
        super.updateEntityActionState();

        if (!this.worldObj.isRemote)
        {
            int i;
            int j;
            int k;
            int l;

            if (this.entityToAttack == null && !this.hasPath())
            {
                i = floor_double(this.posX);
                j = floor_double(this.posY + 0.5D);
                k = floor_double(this.posZ);
                int l1 = this.rand.nextInt(6);
                l = this.worldObj.getBlockId(i + offsetsXForSide[l1], j + offsetsYForSide[l1], k + offsetsZForSide[l1]);

                this.updateWanderPath();
            }
            else if (this.entityToAttack != null && (!this.hasPath()))
            {
            	this.entityToAttack = null;
            }
        }
    }

    /**
     * Takes a coordinate in and returns a weight to determine how likely this creature will try to path to the block.
     * Args: x, y, z
     */
    public float getBlockPathWeight(int par1, int par2, int par3)
    {
        return this.worldObj.getBlockId(par1, par2 - 1, par3) == Block.stone.blockID ? 10.0F : super.getBlockPathWeight(par1, par2, par3);
    }

    /**
     * Checks to make sure the light is not too bright where the mob is spawning
     */
    protected boolean isValidLightLevel()
    {
        return true;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        if (super.getCanSpawnHere())
        {
            EntityPlayer entityplayer = this.worldObj.getClosestPlayerToEntity(this, 5.0D);
            return entityplayer == null; //&& this.worldObj.getBlockMaterial((int)Math.floor(this.posX), (int)Math.floor(this.posY-1), (int)Math.floor(this.posZ)).isSolid();
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns the amount of damage a mob should deal.
     */
    public int getAttackStrength(Entity par1Entity)
    {
    	return 1;
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }
    
    public static int floor_double(double par0)
    {
        int i = (int)par0;
        return par0 < (double)i ? i - 1 : i;
    }
    
    /*protected void jump()
    {
    	if(this.isInWater()){
        this.motionY = 4.71999998688697815D;
    	}
    	else{
    		this.motionY = 0.41999998688697815D;
    	}

        if (this.isPotionActive(Potion.jump))
        {
            this.motionY += (double)((float)(this.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
        }

        if (this.isSprinting())
        {
            float f = this.rotationYaw * 0.017453292F;
            this.motionX -= (double)(MathHelper.sin(f) * 0.2F);
            this.motionZ += (double)(MathHelper.cos(f) * 0.2F);
        }

        this.isAirBorne = true;
        ForgeHooks.onLivingJump(this);
    }*/
}
