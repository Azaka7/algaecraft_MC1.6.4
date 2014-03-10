package azaka7.algaecraft.common.item;

import java.util.List;

import azaka7.algaecraft.common.AlgaeCraftMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;

public class ItemWetsuit extends ItemArmor {
	
	private static final String[] colors = new String[]{"black","red","green","brown","blue","purple","cyan","lightgray","gray","pink","lime","yellow","lightblue","magenta","orange","white"};
	private static Icon[] icons = new Icon[16];
	
	public ItemWetsuit(int par1, EnumArmorMaterial par2EnumArmorMaterial,
			int par3) {
		super(par1, par2EnumArmorMaterial, par3, 2);
		this.setCreativeTab(AlgaeCraftMain.modTab);
		// TODO Auto-generated constructor stub
	}
	
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
        return "azaka7:textures/armor/wetsuit_"+colors[this.getSuitColor(stack)]+".png";
    }
	
	@SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
    {
		ModelBiped model = new ModelBiped(0.005F);
		model.isSneak = entityLiving.isSneaking();
		model.heldItemRight = entityLiving.getHeldItem() != null ? 1 : 0;
        return model;
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		for(int i = 0; i<16;i++){
		icons[i] = par1IconRegister.registerIcon("azaka7:wetsuit_"+colors[i]+"_item");
		}
    }

	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
    	return icons[this.getSuitColor(stack)];
    }
	
	public Icon getIconFromDamage(int par1)
    {
		return icons[0];
    }
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add("Color: "+colors[this.getSuitColor(par1ItemStack)]);
	}
	
	public ItemStack colorize(ItemStack suit, int color){
		if(suit.stackTagCompound==null){
			suit.stackTagCompound = new NBTTagCompound();
		}
		suit.stackTagCompound.setInteger("dyecolor", color);
		return suit;
	}
	
	public int getSuitColor(ItemStack suit){
		if(suit.stackTagCompound==null){
			suit.stackTagCompound = new NBTTagCompound();
			return 0;
		}
		if(!suit.stackTagCompound.hasKey("dyecolor")){
			suit.stackTagCompound.setInteger("dyecolor", 0);
		}
		return suit.stackTagCompound.getInteger("dyecolor");
	}
	
	/**
     * Return whether the specified armor ItemStack has a color.
     */
    public boolean hasColor(ItemStack par1ItemStack)
    {
        return true;
    }

    /**
     * Return the color for the specified armor ItemStack.
     */
    public int getColor(ItemStack par1ItemStack)
    {
        switch(getSuitColor(par1ItemStack)){
        case 1: return 0x993333;
        case 2: return 0x667F33;
        case 3: return 0x664C33;
        case 4: return 0x334CB2;
        case 5: return 0x7F3FB2;
        case 6: return 0x4C7F99;
        case 7: return 0x999999;
        case 8: return 0x4C4C4C;
        case 9: return 0xF27FA5;
        case 10: return 0x7FCC19;
        case 11: return 0xE5E533;
        case 12: return 0x6699D8;
        case 13: return 0xB24CD8;
        case 14: return 0xD87F33;
        case 15: return 0xFFFFFF;
        default: return -1;
        }
    }
    
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		if(AlgaeCraftMain.itemWetsuit.itemID == par1ItemStack.itemID){
			return AlgaeCraftMain.itemNeopreneTextile.itemID == par2ItemStack.itemID || super.getIsRepairable(par1ItemStack,par2ItemStack) ;
		}
		else{
			return super.getIsRepairable(par1ItemStack, par2ItemStack);
		}
	}

}
