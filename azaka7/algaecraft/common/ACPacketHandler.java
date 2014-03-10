package azaka7.algaecraft.common;

import azaka7.algaecraft.common.item.ItemBCD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class ACPacketHandler implements IPacketHandler {
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		PacketType type = PacketType.translateID(packet.data[0]);
		
		if(type == PacketType.BCD){
			boolean increase = packet.data[1] == 1;
			boolean flag = false;
			EntityPlayer entityPlayer = (EntityPlayer) player;
			if(entityPlayer.inventory.armorInventory[2] != null){
				ItemStack stack = entityPlayer.inventory.armorInventory[2];
				if(stack.getItem().getClass() == AlgaeCraftMain.itemScubaBCD.getClass()){
					ItemBCD item = (ItemBCD) stack.getItem();
					item.changeDo(stack, entityPlayer, increase, Side.SERVER);
				}
			}
		}
	}
	
	public static Packet250CustomPayload constructRegistrationPacket(){
		return new Packet250CustomPayload("REGISTER",new byte[]{Byte.valueOf("ALGAECRAFT")});
	}
	
	public static Packet250CustomPayload constructUnRegistrationPacket(){
		return new Packet250CustomPayload("UNREGISTER",new byte[]{Byte.valueOf("ALGAECRAFT")});
	}
	
	public static Packet250CustomPayload constructBCDPacket(boolean increase){
		byte b1;
		try{
			b1 = (byte) (increase ? 1 : 0);
		}
		catch(Exception e){
			b1 = 0;
		}
		return new Packet250CustomPayload("ALGAECRAFT", new byte[]{PacketType.BCD.id,b1});
	}
	
	public enum PacketType{
		BCD((byte) 0);
		
		public byte id;
		PacketType(byte b){
			id = b;
		}
		
		public static PacketType translateID(byte b){
			for(int i = 0; i < PacketType.values().length; i++){
				if(PacketType.values()[i].id == b){
					return PacketType.values()[i];
				}
			}
			return BCD;
		}
	}

}
