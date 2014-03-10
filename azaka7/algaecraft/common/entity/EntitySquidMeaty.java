package azaka7.algaecraft.common.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import azaka7.algaecraft.common.AlgaeCraftMain;

public class EntitySquidMeaty extends EntitySquid
{
    public EntitySquidMeaty(World par1World)
    {
        super(par1World);
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D);}
    
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
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

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean par1, int par2)
    {
        int var3 = this.rand.nextInt(3 + par2) + 1;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            super.entityDropItem(new ItemStack(Item.dyePowder, 1, 0), 0.0F);
        }
        var3 = this.rand.nextInt(3 + par2) + 1;
        for (int var4 = 0; var4 < var3; ++var4)
        {
            this.entityDropItem(new ItemStack(AlgaeCraftMain.itemSquidRaw, 1, 0), 0.0F);
        }
    }
}