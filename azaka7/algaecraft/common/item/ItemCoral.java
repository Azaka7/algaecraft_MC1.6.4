package azaka7.algaecraft.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.CommonProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCoral extends Item {
	
	public final String[] coralList = new String[]{"orange", "purple", "brain", "blue"};
	public Icon[] iconList = new Icon[16];
	
	public ItemCoral(int par1) {
		super(par1);
		this.setMaxDamage(0);
        this.setHasSubtypes(true);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		String file;
    	for (int i = 0; i < this.coralList.length*2; ++i)
        {
    		file = "coral_";
    		if(i >= coralList.length){
    			file = file + coralList[i-coralList.length] + ((i >= 4 && i <= 7) ? "_small" : "") + ((i == 2 || i == 6) ? "I" : "");
                iconList[i] = par1IconRegister.registerIcon("azaka7:"+file);
    		}
    		else{
    			file = file + coralList[i] + ((i >= 4 && i <= 7) ? "_small" : "") + ((i == 2 || i == 6) ? "I" : "");
    			iconList[i] = par1IconRegister.registerIcon("azaka7:"+file);
    		}
        }
    }
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        return iconList[par2];
    }
	
	public String getTextureFile(){
		return CommonProxy.blocksImg;
	}
	
	public Icon getIconFromDamage(int par1)
    {
    	return getBlockTextureFromSideAndMetadata(0,par1);
    }
	
	public int getMetadata(int par1)
    {
        return par1;
    }

    public String getItemNameIS(ItemStack par1ItemStack)
    {
        return super.getUnlocalizedName()+"."+this.getNameFromMetadata(par1ItemStack.getItemDamage());
    }
    
    private String getNameFromMetadata(int i){
    	String s;
    	switch(i){
    	case 0: s = "Orange";break;
    	case 1: s = "Purple";break;
    	case 2: s = "Brain";break;
    	case 3: s = "Blue";break;
    	case 4: s = "Small Orange";break;
    	case 5: s = "Small Purple";break;
    	case 6: s = "Small Brain";break;
    	case 7: s = "Small Blue";break;
    	default: s = "Non-Existing";break;
    	}
    	return s;
    }
    
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par7 != 1)
        {
            return false;
        }
        else if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
        {
            if (AlgaeCraftMain.blockCoral.canPlaceBlockAt(par3World, par4, par5+1, par6))
            {
                par3World.setBlock(par4, par5 + 1, par6, AlgaeCraftMain.blockCoral.blockID, par1ItemStack.getItemDamage(), 2);
                --par1ItemStack.stackSize;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
    
    public String getItemDisplayName(ItemStack par1ItemStack)
    {
    	return this.getNameFromMetadata(par1ItemStack.getItemDamage())+" Coral";
    }
    
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	for(int i = 0; i<8; i++){
        par3List.add(new ItemStack(par1, 1, i));
    	}
    }
}
