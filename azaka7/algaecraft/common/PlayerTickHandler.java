package azaka7.algaecraft.common;

import java.util.EnumSet;

import azaka7.algaecraft.client.KeyBindHandler;
import azaka7.algaecraft.common.item.ItemBCD;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PlayerTickHandler implements ITickHandler{
	
	private final EnumSet<TickType> ticksToGet;
	public static Side side;
	
	public PlayerTickHandler(EnumSet<TickType> set, Side s){
		ticksToGet = set;
		side = s;
	}
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		
		playerTick((EntityPlayer)tickData[0]);
		
	}

	private void playerTick(EntityPlayer entityPlayer) {
		//PlayerSCUBAMovementHandler.updatePlayerMovement(entityPlayer);
		if(ItemBCD.flyingPlayers.contains(entityPlayer.username)){
			boolean flag = false;
			if(entityPlayer.inventory.armorInventory[2] != null){
				ItemStack stack = entityPlayer.inventory.armorInventory[2];
				if(stack.getItem().getClass() == AlgaeCraftMain.itemScubaBCD.getClass()){
					ItemBCD item = (ItemBCD) stack.getItem();
					if(item.getBCDState(stack) == 1){
						flag = true;
					}
				}
			}
			if(!flag){
				entityPlayer.capabilities.isFlying = false;
				if(side==Side.CLIENT){entityPlayer.capabilities.setFlySpeed(0.05F);}
				ItemBCD.flyingPlayers.remove(entityPlayer.username);
				/*System.out.println("@@@@*-AlgaeCraft has detected possible illegal movement:");
				System.out.println("@@@@*-Player "+entityPlayer.username+" was BCD-flying without BCD!");
				System.out.println("@@@@*-Or player took BCD off in neutral.");*/
			}
		}
		if((side != Side.CLIENT) || (entityPlayer.worldObj.isRemote && side != Side.CLIENT)){
			return;
		}
		//if(!FMLCommonHandler.instance().getEffectiveSide().isClient()){return;}
		if(KeyBindHandler.bcdControlDown.isPressed()){
			ItemStack stack = entityPlayer.inventory.armorItemInSlot(2);
			if(stack != null && stack.itemID == AlgaeCraftMain.itemScubaBCD.itemID){
				ItemBCD item = (ItemBCD) stack.getItem();
				item.changeDo(stack, entityPlayer, false, side);
			}
		}
		else if(KeyBindHandler.bcdControlUp.isPressed()){
			ItemStack stack = entityPlayer.inventory.armorItemInSlot(2);
			if(stack != null && stack.itemID == AlgaeCraftMain.itemScubaBCD.itemID){
				ItemBCD item = (ItemBCD) stack.getItem();
				item.changeDo(stack, entityPlayer, true, side);
			}
		}
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return ticksToGet;
	}

	@Override
	public String getLabel() {
		return "AlgaeCraftPlayerTick";
	}
	
	

}
