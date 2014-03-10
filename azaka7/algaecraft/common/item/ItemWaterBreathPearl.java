package azaka7.algaecraft.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import azaka7.algaecraft.common.AlgaeCraftMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWaterBreathPearl extends Item {
	
	private Icon seaweedImg;
	
	public ItemWaterBreathPearl(int par1) {
		super(par1);
		this.maxStackSize = 16;
		this.setHasSubtypes(true);
        this.setMaxDamage(0);
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		if(par3EntityPlayer.getAir() < 300 && !par2World.isRemote){
			if(par3EntityPlayer.getAir() + 120 <= 300){
				par3EntityPlayer.setAir(par3EntityPlayer.getAir() + 120);
			}
			else{
				par3EntityPlayer.setAir(300);
			}
			
			if(!par3EntityPlayer.capabilities.isCreativeMode){
				par1ItemStack.stackSize--;
			}
			float f = par2World.rand.nextFloat() - par2World.rand.nextFloat();
            float f1 = par2World.rand.nextFloat() - par2World.rand.nextFloat();
            float f2 = par2World.rand.nextFloat() - par2World.rand.nextFloat();
		}
        return par1ItemStack;
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		seaweedImg = par1IconRegister.registerIcon("azaka7:waterBreathPearl");
    }
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
		return seaweedImg;
    }
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1)
    {
    	return getBlockTextureFromSideAndMetadata(0,par1);
    }
	@SideOnly(Side.CLIENT)
	public String getItemDisplayName(ItemStack par1ItemStack)
    {
		return "Aeros Bulb";
    }
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(AlgaeCraftMain.potionWaterBreath);
        par3List.add(AlgaeCraftMain.potionWaterBreathLong);
        par3List.add(AlgaeCraftMain.potionWaterBreathSplash);
    }

}
