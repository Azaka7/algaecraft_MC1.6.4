package azaka7.algaecraft.common.item;

import java.util.ArrayList;
import java.util.List;

import azaka7.algaecraft.common.ACPacketHandler;
import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.PlayerSCUBAMovementHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.multiplayer.NetClientHandler;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class ItemBCD extends ItemArmor {
	
	private String itemImg;
	private String armorImg;
	private Icon itemIcon;
	
	public static List<String> flyingPlayers = new ArrayList<String>();
	
	public ItemBCD(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, String itemName, String armorName) {
		super(par1, par2EnumArmorMaterial, par3, 1);
		itemImg = itemName;
		armorImg = armorName;
		this.setCreativeTab(AlgaeCraftMain.modTab);
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		if(!par3EntityPlayer.isSneaking()){
			return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
		}
		if(getAirMaximum(par1ItemStack) != 0){
			if(!par3EntityPlayer.inventory.addItemStackToInventory(this.setStackAirTankReturnOld(par1ItemStack, null))){
				par3EntityPlayer.dropPlayerItem(this.setStackAirTankReturnOld(par1ItemStack, null));
			}
			return par1ItemStack;
		}
		return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
    }
	
	@SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
    {
		//if(armorSlot == 2){
		float f = this.getBCDState(itemStack)*0.45F;
		ModelBiped model = new ModelBiped(0.51F+f);
		model.isSneak = entityLiving.isSneaking();
        return model;
		//}
		//return null;
    }
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		String s = "Off, but something went wrong.";
		switch(this.getBCDState(par1ItemStack)){
		case 0: s = "Off"; break;
		case 1: s = "Neutral"; break;
		case 2: s = "Float"; break;
		}
		par3List.add("BCD State: "+s);
		int amount = getAirAmount(par1ItemStack);
		int max = getAirMaximum(par1ItemStack);
		amount = max - amount;
		s="No Air Tank!";
		if(amount - max > max && max>0){s = "Too Much Air! ("+(amount-max)+"/"+max+")";}
		else if(max>0){
			s = amount+"/"+max+" Breaths";
		}
		par3List.add("Air Tank: " +s);
	}
	
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
        return "azaka7:textures/armor/"+armorImg+".png";
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		itemIcon = par1IconRegister.registerIcon("azaka7:"+itemImg);
    }
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1)
    {
		return this.itemIcon;
    }
	
	public boolean useAir(ItemStack stack, int amount){
		setUpStackNBT(stack);
		NBTTagCompound tag = stack.getTagCompound().getCompoundTag("bcd");
		if(!tag.hasKey("air")){
			tag.setInteger("air", 0);
		}
		tag.setInteger("air", tag.getInteger("air")+amount);
		if(tag.getInteger("air")>tag.getInteger("airMax")){
			tag.setInteger("air", tag.getInteger("airMax"));
			return false;
		}
		return true;
	}
	
	public ItemStack setStackAirTankReturnOld(ItemStack bcdStack, ItemStack tankStack){
		setUpStackNBT(bcdStack);
		NBTTagCompound tag = bcdStack.getTagCompound().getCompoundTag("bcd");
		if(!tag.hasKey("air")){
			tag.setInteger("air", 0);
		}
		if(!tag.hasKey("airMax")){
			tag.setInteger("airMax", 0);
		}
		int constructedMetadata = getAirAmount(bcdStack);
		ItemStack tankStack2;
		if(tag.getInteger("airMax") == 200){
		tankStack2 = new ItemStack(AlgaeCraftMain.itemAirTankSmall.itemID, 1, constructedMetadata);
		}
		else{
			tankStack2 = new ItemStack(AlgaeCraftMain.itemAirTankSmall.itemID, 0, 200);
		} 
		if(tankStack==null){
			nullifyAirtank(bcdStack);
		}
		else{
		tag.setInteger("air", tankStack.getItemDamage());
		tag.setInteger("airMax", tankStack.getMaxDamage());
		}
		return tankStack2;
	}
	
	public void nullifyAirtank(ItemStack bcdStack){
		setUpStackNBT(bcdStack);
		NBTTagCompound tag = bcdStack.getTagCompound().getCompoundTag("bcd");
		tag.setInteger("air", 0);
		tag.setInteger("airMax", 0);
	}
	
	public void setUpStackNBT(ItemStack par1ItemStack){
		NBTTagCompound tag = par1ItemStack.getTagCompound();
		if(tag == null){
			tag = new NBTTagCompound();
			par1ItemStack.setTagCompound(tag);
		}
		NBTTagCompound tag2 = tag.getCompoundTag("bcd");
		if(!tag.hasKey("bcd")){
			tag.setCompoundTag("bcd", tag2);
		}
		/*NBTTagCompound stateInfo = new NBTTagCompound();
		stateInfo.setInteger("airMax", 0);
		stateInfo.setInteger("air", 0);
		stateInfo.setInteger("state", -1);
		stack.setTagCompound(stateInfo);*/
	}
	
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack)
    {
		//default fly speed is 0.05
		if(player.isInWater() && !player.capabilities.isCreativeMode){
			if(player.getAir() + 60 <= 300 && this.useAir(itemStack, 1)){
				player.setAir(player.getAir() + 60);
			}
			if(player.getAir() + 60 <= 300 && this.useAir(itemStack, 1)){
				player.setAir(player.getAir() + 60);
			}
			
			if(getBCDState(itemStack) == 1){
				player.capabilities.isFlying = true;
				if(FMLCommonHandler.instance().getSide() == Side.CLIENT){
				player.capabilities.setFlySpeed(0.012F);}
				player.motionY*=0.6;
				if(!flyingPlayers.contains(player.username)){
					flyingPlayers.add(player.username);
				}
				//PlayerSCUBAMovementHandler.updateVertMult(0.6F, 1);
				//PlayerSCUBAMovementHandler.updatePlayerMovement(player);
			}
			else{
				player.capabilities.isFlying = false;
				if(FMLCommonHandler.instance().getSide() == Side.CLIENT){
				player.capabilities.setFlySpeed(0.05F);}
				if(flyingPlayers.contains(player.username)){
					flyingPlayers.remove(player.username);
				}
				else if(player.capabilities.isFlying && !player.capabilities.isCreativeMode){
					System.out.println("@@@@*-AlgaeCraft has detected illegal movement:");
					System.out.println("@@@@*-Player "+player.username+" was flying without BCD registration!");
				}
				//PlayerSCUBAMovementHandler.updateVertMult(1F, 1);
				//PlayerSCUBAMovementHandler.updatePlayerMovement(player);
			}
			if(getBCDState(itemStack) == 2){
				//player.motionY+=0.09;
				player.motionY+=0.03;
				//PlayerSCUBAMovementHandler.updateVertAdd(0.03F, 1);
				//PlayerSCUBAMovementHandler.updatePlayerMovement(player);
			}
			else{
				//PlayerSCUBAMovementHandler.updateVertAdd(0F, 1);
			}
		}
		else if(!player.capabilities.isCreativeMode){
			player.capabilities.isFlying = false;
			if(FMLCommonHandler.instance().getSide() == Side.CLIENT){
				player.capabilities.setFlySpeed(0.05F);
			}
		}
    }
	
	public int getBCDState(ItemStack par1ItemStack){
		setUpStackNBT(par1ItemStack);
		NBTTagCompound tag = par1ItemStack.getTagCompound().getCompoundTag("bcd");
		if(!tag.hasKey("state")){
			tag.setInteger("state", 0);
		}
		return tag.getInteger("state");
		/*if(par1ItemStack.getItem().getClass() != this.getClass()){return -1;}
		NBTTagCompound stateInfo = par1ItemStack.getTagCompound();
		if(par1ItemStack.getTagCompound() == null){
			//System.out.println((par1ItemStack.getTagCompound() == null)+":"+(!par1ItemStack.getTagCompound().hasKey("state")));
			setUpStackNBT(par1ItemStack);
		}
		return par1ItemStack.getTagCompound().getInteger("state");*/
	}
	
	public int getBCDIntAttribute(ItemStack par1ItemStack, String par2String){
		setUpStackNBT(par1ItemStack);
		NBTTagCompound tag = par1ItemStack.getTagCompound().getCompoundTag("bcd");
		if(!tag.hasKey(par2String)){
			tag.setInteger(par2String, 0);
		}
		return tag.getInteger(par2String);
	}
	
	public int getAirAmount(ItemStack par1ItemStack){
		return getBCDIntAttribute(par1ItemStack, "air");
	}
	public int getAirMaximum(ItemStack par1ItemStack){
		return getBCDIntAttribute(par1ItemStack, "airMax");
		/*
		if(par1ItemStack.getItem().getClass() != this.getClass()){return 0;}
		NBTTagCompound stateInfo = par1ItemStack.getTagCompound();
		if(par1ItemStack.getTagCompound() == null){
			setUpStackNBT(par1ItemStack);
		}
		return par1ItemStack.getTagCompound().getInteger("airMax");*/
	}
	
	public void changeDo(ItemStack par1ItemStack, EntityPlayer player, boolean increase, Side side){
		//NetworkRegistry.instance().activateChannel();
		if(side == Side.CLIENT && player.worldObj.isRemote){
		//	ModLoader.getMinecraftInstance().getNetHandler().addToSendQueue(ACPacketHandler.constructRegistrationPacket());
		}
		
		NBTTagCompound tag = par1ItemStack.getTagCompound();
		if(tag == null){
			tag = new NBTTagCompound();
			par1ItemStack.setTagCompound(tag);
		}
		NBTTagCompound tag2 = tag.getCompoundTag("bcd");
		if(!tag.hasKey("bcd")){
			tag.setCompoundTag("bcd", tag2);
		}
		
		if(increase){
			boolean shouldUseAir = tag2.getInteger("state")+1 <= 2;
			if(shouldUseAir){
				tag2.setInteger("state", tag2.getInteger("state") + (useAir(par1ItemStack, 1) ? 1 : 0));
				//useAir(par1ItemStack, 1);
				if(side == Side.CLIENT&& player.worldObj.isRemote){
					Minecraft.getMinecraft().getNetHandler().addToSendQueue(ACPacketHandler.constructBCDPacket(increase));
				}
			}
			else{
				tag2.setInteger("state", 0);
				for(int n = 0; n < 8; n++){
					float f = player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat();
					float f1 = player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat();
					float f2 = player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat();
					player.worldObj.spawnParticle("bubble", player.posX + (double)f, player.posY + (double)f1, player.posZ + (double)f2, player.motionX, player.motionY, player.motionZ);
				}
				if(side == Side.CLIENT&& player.worldObj.isRemote){
					Minecraft.getMinecraft().getNetHandler().addToSendQueue(ACPacketHandler.constructBCDPacket(increase));
				}
			}
		}
		else{
			if(tag2.getInteger("state")-1 < 0){
				tag2.setInteger("state", (useAir(par1ItemStack, 2) ? 2 : (useAir(par1ItemStack, 1) ? 1 : 0)));
				if(side == Side.CLIENT&& player.worldObj.isRemote){
					Minecraft.getMinecraft().getNetHandler().addToSendQueue(ACPacketHandler.constructBCDPacket(increase));
				}
			}
			else{
				tag2.setInteger("state", tag2.getInteger("state") - 1);
				for(int n = 0; n < 8; n++){
					float f = player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat();
					float f1 = player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat();
					float f2 = player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat();
					player.worldObj.spawnParticle("bubble", player.posX + (double)f, player.posY + (double)f1, player.posZ + (double)f2, player.motionX, player.motionY, player.motionZ);
				}
				if(side == Side.CLIENT&& player.worldObj.isRemote){
					Minecraft.getMinecraft().getNetHandler().addToSendQueue(ACPacketHandler.constructBCDPacket(increase));
				}
			}
		}
		
		
		//tag2.setInteger("state", tag2.getInteger("state") + (increase ? 1 : -1));
		//if(increase){useAir(par1ItemStack,1);}
		if(tag2.getInteger("state") < 0){tag2.setInteger("state", 2);}
		if(tag2.getInteger("state") > 2){tag2.setInteger("state", 0);}
		
		String s = "Off, but something went wrong.";
		switch(tag2.getInteger("state")){
		case 0: s = "Off"; break;
		case 1: s = "Neutral"; break;
		case 2: s = "Float"; break;
		}
		if(side == Side.CLIENT){
		player.addChatMessage("BCD State: "+s);
		}
		
		//////////////
		if(side == Side.CLIENT&& player.worldObj.isRemote){
		//	ModLoader.getMinecraftInstance().getNetHandler().addToSendQueue(ACPacketHandler.constructUnRegistrationPacket());
		}
		/*getBCDState(par1ItemStack);
		NBTTagCompound stateInfo = par1ItemStack.getTagCompound();
		stateInfo.setInteger("state", stateInfo.getInteger("state")+(increase ? 1 : -1));
		if(stateInfo.getInteger("state") > 1){stateInfo.setInteger("state", -1);}
		if(stateInfo.getInteger("state") < -1){stateInfo.setInteger("state", 1);}
		par1ItemStack.setTagCompound(stateInfo);
		String s = "Off, but something went wrong.";
		switch(stateInfo.getInteger("state")){
		case -1: s = "Off"; break;
		case 0: s = "Neutral"; break;
		case 1: s = "Float"; break;
		}
		player.addChatMessage("BCD State: "+s);*/
	}
	
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		if(AlgaeCraftMain.itemScubaBCD.itemID == par1ItemStack.itemID){
			return AlgaeCraftMain.itemRubberBall.itemID == par2ItemStack.itemID || super.getIsRepairable(par1ItemStack,par2ItemStack) ;
		}
		else{
			return super.getIsRepairable(par1ItemStack, par2ItemStack);
		}
	//return AlgaeCraftMain.itemScubaBCD.itemID == par2ItemStack.itemID ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

}
