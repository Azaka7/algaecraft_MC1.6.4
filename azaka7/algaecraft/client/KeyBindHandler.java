package azaka7.algaecraft.client;
import java.util.EnumSet;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;

import azaka7.algaecraft.common.item.ItemBCD;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.LanguageRegistry;
public class KeyBindHandler extends KeyHandler
{
	 private EnumSet tickTypes = EnumSet.of(TickType.PLAYER);
	 public static KeyBinding bcdControlUp = new KeyBinding("key.bcdControl.up",Keyboard.KEY_X);
	 public static KeyBinding bcdControlDown = new KeyBinding("key.bcdControl.down",Keyboard.KEY_Z);
	 
	 public static boolean bcdControlUpPressed = false;
	 public static boolean bcdControlDownPressed = false;
	 
	 public KeyBindHandler()
	 {
		 super(new KeyBinding[]{bcdControlUp, bcdControlDown}, new boolean[]{false,false});
		 LanguageRegistry.instance().addStringLocalization("key.bcdControl.up", "Up-Cycle BCD");
		 LanguageRegistry.instance().addStringLocalization("key.bcdControl.down", "Down-Cycle BCD");
	 }
	 
	 @Override
	 public String getLabel()
	 {
		 return "AlgaeCraftKeybinding";
	 }
	 @Override
	 public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
	 {
		 System.out.println(kb.keyDescription+" was pressed");
		 if(kb == bcdControlUp){
			 bcdControlUpPressed = true;
		 }
		 if(kb == bcdControlDown){
			 bcdControlDownPressed = true;
		 }
			/*if(kb == bcdControlUp && !tickEnd){
				ItemStack stack = getPlayer().inventory.armorItemInSlot(2);
				if(stack != null && stack.itemID == AlgaeCraftMain.itemScubaBCD.itemID){
					ItemBCD item = (ItemBCD) stack.getItem();
					item.changeDo(stack, thePlayer, true);
				}
			}
			else if(kb == bcdControlDown && !tickEnd){
				ItemStack stack = getPlayer().inventory.armorItemInSlot(2);
				if(stack != null && stack.itemID == AlgaeCraftMain.itemScubaBCD.itemID){
					ItemBCD item = (ItemBCD) stack.getItem();
					item.changeDo(stack, thePlayer, false);
				}
			}*/
	 }
	 @Override
	 public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd)
	 {
		 if(kb == bcdControlUp){
			 bcdControlUpPressed = false;
		 }
		 if(kb == bcdControlDown){
			 bcdControlDownPressed = false;
		 }
		 //What to do when key is released/up
	 }
	 @Override
	 public EnumSet<TickType> ticks()
	 {
		 return tickTypes;
	 }
}

/*
 * public static KeyBinding bcdControl = new KeyBinding("bcdControl",Keyboard.KEY_B);
	private World theWorld;
	private EntityPlayer thePlayer;
 * 
 * System.out.println(kb.keyDescription+" was pressed");
		if(kb == bcdControl){
			ItemStack stack = thePlayer.inventory.getCurrentItem();
			if(stack != null && stack.itemID == AlgaeCraftMain.itemScubaBCD.itemID){
				ItemBCD item = (ItemBCD) stack.getItem();
				item.changeDo(stack, thePlayer);
			}
		}*/
