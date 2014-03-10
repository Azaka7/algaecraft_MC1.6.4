package azaka7.algaecraft.common.container;

import net.minecraft.entity.EntityCreature;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import azaka7.algaecraft.common.entity.EntityLobster;

public class TileEntityCage extends TileEntity {

	public TileEntityCage(){
	}
	
	public void setWorld(World world){
		this.worldObj = world;
	}
	
	public void setCreature(EntityCreature entity){
		if(entity instanceof EntityLobster){
			this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 1, 2);
		}
		this.onInventoryChanged();
	}

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.writeToNBT(par1NBTTagCompound);
    	/*
    	System.out.println("writeToNBT");
    	if(this.containedMob !=null){
    		System.out.println("mob!=null");
    		NBTTagCompound entity = new NBTTagCompound();
    		entity.setName("storedEntity");
    		entity.setInteger("id", EntityList.getEntityID(containedMob));
    		entity.setFloat("yaw", containedMob.rotationYaw);
    		entity.setFloat("pitch", containedMob.rotationPitch);
    		entity.setInteger("health", this.containedMob.getHealth());
    		par1NBTTagCompound.setCompoundTag("mob", entity);
    		par1NBTTagCompound.setString("mobName", EntityList.getEntityString(this.containedMob));
    	}
    	else{
    		System.out.println("mob=null");
    		NBTTagCompound entity = new NBTTagCompound();
    		entity.setName("storedEntity");
    		entity.setInteger("id", 0);
    		entity.setFloat("yaw", 0);
    		entity.setFloat("pitch", 0);
    		entity.setInteger("health", 0);
    		par1NBTTagCompound.setCompoundTag("mob", entity);
    		par1NBTTagCompound.setString("mobName","noMob");
    	}
    	*/
    }
    
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.readFromNBT(par1NBTTagCompound);
    	/*
    	System.out.println("readFromNBT");
    	NBTTagCompound compound = par1NBTTagCompound.getCompoundTag("mob");
    	//if(compound.getInteger("id")==0){this.containedMob = null;return;}
    	
    	EntityCreature entity = null;
		//try {
		//	entity = (EntityCreature) (EntityList.createEntityByID(compound.getInteger("id"), this.worldObj));
		//} catch (Exception e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
    	String mob = par1NBTTagCompound.getString("mobName");
    	System.out.println(mob);
    	if(mob != null && mob != "noMob"){
    		try {
    			entity = (EntityCreature) (EntityList.createEntityByName(par1NBTTagCompound.getString("mobName"), this.worldObj));
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else {System.out.println("no mobName in NBT");}
    	if (entity != null){
    		System.out.println("entity != null");
    		System.out.println(entity.isDead);
    		//entity.setLocationAndAngles(par1NBTTagCompound.getInteger("x"), par1NBTTagCompound.getInteger("y"), par1NBTTagCompound.getInteger("z"), compound.getFloat("yaw"), compound.getFloat("pitch"));
    		//entity.rotationYawHead = entity.rotationYaw;
			//entity.renderYawOffset = entity.rotationYaw;
    		//entity.setEntityHealth(compound.getInteger("health"));
        	//entity.initCreature();
    		this.setCreature(entity);
    		System.out.println("!=null: "+(this.containedMob!=null));
    		System.out.println(this.containedMob.isDead);
    		System.out.println(""+this.containedMob.posX+"."+this.containedMob.posY+"."+this.containedMob.posZ);
    		//entity.setDead();
    		System.out.println(this.containedMob.isDead);
    	}
    	else{this.containedMob = null;System.out.println("entity == null");}
    	*/
    }
}
