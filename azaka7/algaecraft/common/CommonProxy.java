package azaka7.algaecraft.common;

import java.util.EnumSet;

import azaka7.algaecraft.common.container.TileEntityAirCompressor;
import azaka7.algaecraft.common.container.TileEntityCage;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy implements IGuiHandler, IScheduledTickHandler {
	
	public static final String itemsImg = "/azaka7/algaecraft/ACItems.png";
	public static final String blocksImg = "/azaka7/algaecraft/ACBlocks.png";
	
	public void registerRenders(){
		GameRegistry.registerTileEntity(TileEntityCage.class, "lobsterCageRender");
		GameRegistry.registerTileEntity(TileEntityAirCompressor.class, "blockAirCompressorRender");
		}
	
	//public void registerRenderInformation(){}
	
	//public boolean renderWorldBlock(RenderBlocks renderer, IBlockAccess world, int x, int y, int z, Block block, int modelID){return false;}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nextTickSpacing() {
		// TODO Auto-generated method stub
		return 5;
	}
	
	
	public void registerTickRegistry(){
		//TickRegistry.registerScheduledTickHandler(this, Side.SERVER);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@ForgeSubscribe
	public void customMobDrop(LivingDropsEvent event){
		if(event.entityLiving.getClass() == EntitySquid.class){
			//System.out.println("squidly died");
			ItemStack stack = new ItemStack(AlgaeCraftMain.itemSquidRaw, event.entity.worldObj.rand.nextInt(3));
			//event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posX, event.entityLiving.posX, stack));
			event.entityLiving.dropItem(stack.itemID, stack.stackSize);
		}
		//System.out.println("Checked");
	}
}
