package azaka7.algaecraft.common.container;

import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.item.ItemAirTank;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityAirCompressor extends TileEntity {
	public int tankDamage;
	public int timer;
	public NBTTagCompound tags;
	private boolean lastPowered;
	
	public TileEntityAirCompressor(){
		
	}
	
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
		this.readFromNBT(pkt.data);
    }
	
	public Packet getDescriptionPacket()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 5, nbttagcompound);
    }
	
	public void setTank(World world, ItemStack itemstack){
		//System.out.println("@@@@@@@@"+blockMetadata);
		if(itemstack.getItem() == null){
			return;
		}
		if(!(itemstack.getItem() instanceof ItemAirTank)){
			return;
		}
		if(itemstack.getItem() == AlgaeCraftMain.itemAirTankSmall){
			world.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, blockMetadata+1, 2);
			this.tankDamage = itemstack.getItemDamage();
		}
		//System.out.println(blockMetadata);
	}
	
	public ItemStack removeTank(World world){
		int id;
		int metadata = world.getBlockMetadata(xCoord, yCoord, zCoord);
		int oldDamage = tankDamage;
		
		if(metadata == 1 || metadata == 4 || metadata == 7 || metadata == 10){
			id = AlgaeCraftMain.itemAirTankSmall.itemID;
			world.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, getBlockMetadata()-1, 3);
			this.tankDamage = 0;
		}
		else if (metadata == 2 || metadata == 5 || metadata == 8 || metadata == 11){
			id = AlgaeCraftMain.itemAirtankLargeID;
			world.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, getBlockMetadata()-2, 3);
			this.tankDamage = 0;
		}
		else{
			return new ItemStack(0,0,0);
		}
		ItemStack stack = new ItemStack(id, 1, oldDamage);
		return stack;
		
	}
	
	public void setWorld(World world){
		this.worldObj = world;
	}
	public void updateEntity(){
		/*boolean doSomething = false;
		timer++;
		if(timer >= 100){
			//System.out.println("compressor updated");
			timer = 0;
			doSomething = true;
		}*/
		boolean thisPowered = (this.worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) > 0);
		if(thisPowered != lastPowered /*&& doSomething*/){
			//fill air tank +1
			timer++;
			if(timer >= 3){
				//System.out.println("compressor updated");
				timer = 0;

				this.tankDamage -= 1;
				if(tankDamage < 0){
					tankDamage = 0;
				}
			}
			lastPowered = thisPowered;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		tankDamage = par1NBTTagCompound.getInteger("tankDamage");
		tags = par1NBTTagCompound;
		this.blockMetadata = par1NBTTagCompound.getInteger("blockDamage");
		
		//System.out.println("readFromNBT: "+tankDamage);
	}
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("tankDamage", tankDamage);
		par1NBTTagCompound.setInteger("blockDamage", this.blockMetadata);

		//System.out.println("writeToNBT: "+tankDamage);
	}
}
