package azaka7.algaecraft.common.item;

import java.util.Iterator;
import java.util.List;

import azaka7.algaecraft.common.AlgaeCraftMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class ItemFlask extends Item {
	
	public static String[] itemNames = new String[]{"Empty","Water","Salt Water","Sodium Hydroxide","CraftingNaOH","CraftingSaltWater"};
	public static String[] iconNames = new String[]{"Empty","Fresh","Salt","NaOH","NaOH","Salt"};
	public static Icon[] itemIcons = new Icon[iconNames.length];
	
	public ItemFlask(int par1) {
		super(par1);
		this.maxStackSize = 1;
		this.setHasSubtypes(true);
		//this.setContainerItem(this);
        //this.setMaxDamage(3);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		for(int i = 0; i<iconNames.length;i++){
		itemIcons[i] = par1IconRegister.registerIcon("azaka7:flask"+iconNames[i]);
		}
    }
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
		int damage = par1ItemStack.getItemDamage();
		if(damage > 3){
			return "item.itemFlask";
		}
		return "item.itemFlask."+iconNames[damage];
    }
	
	public Icon getIconFromDamage(int par1)
    {
		return itemIcons[par1];
    }
	
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		for(int i = 0; i < itemIcons.length; i++){
			if(i!=4 && i!=5){
				par3List.add(new ItemStack(par1, 1, i));
			}
		}
    }
	
	public boolean hasContainerItem()
    {
        return true;
    }
	
	public ItemStack getContainerItemStack(ItemStack itemStack)
    {
		if(itemStack.getItemDamage()==4){
			itemStack.setItemDamage(3);
		}
		else if(itemStack.getItemDamage()==5){
			itemStack.setItemDamage(2);
		}
		else{
			itemStack.setItemDamage(0);
		}
		return itemStack;
    }
	
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack)
    {
		if(par1ItemStack.getItemDamage()==3){
			return false;
		}
		if(par1ItemStack.getItemDamage()==2){
			return false;
		}
		
        return true;
    }
	
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.drink;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack, int pass)
    {
    	if(par1ItemStack.getItemDamage()==5){
			par1ItemStack = null;
			return false;
		}
    	return super.hasEffect(par1ItemStack, pass);
    }
    
    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
            --par1ItemStack.stackSize;
        }

        if (!par2World.isRemote)
        {
        	PotionEffect potioneffect = getEffectFromContained(par1ItemStack.getItemDamage());
        	par3EntityPlayer.addPotionEffect(new PotionEffect(potioneffect));
        }
        if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
            if (par1ItemStack.stackSize <= 0)
            {
                return new ItemStack(this, 1, 0);
            }

            par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(this, 1, 0));
        }

        return par1ItemStack;
    }
    
    private PotionEffect getEffectFromContained(int damage){
    	switch(damage){
    	case 1: return new PotionEffect(Potion.fireResistance.id, 20, 0, true);
    	case 2: return new PotionEffect(Potion.hunger.id, 200, 1, true);
    	case 3: return new PotionEffect(Potion.wither.id, 60, 2, true);
    	default: return null;
    	}
    }
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		if(par1ItemStack.getItemDamage() >= 1){
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
			return par1ItemStack;
		}
		
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

        if (movingobjectposition == null)
        {
            return par1ItemStack;
        }
        else
        {
            if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE)
            {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;

                if (!par2World.canMineBlock(par3EntityPlayer, i, j, k))
                {
                    return par1ItemStack;
                }

                if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
                {
                    return par1ItemStack;
                }

                if (par2World.getBlockMaterial(i, j, k) == Material.water)
                {
                    --par1ItemStack.stackSize;
                    
                    int aspect = 1;
                    
                    int[] idList = AlgaeCraftMain.biomeIDOceanList.clone();
                    for(int n = 0; n < idList.length; n++){
                    	if(idList[n] == par2World.getBiomeGenForCoords(i, k).biomeID){
                    		aspect = 2;
                    	}
                    	else if(par2World.getBiomeGenForCoords(i, k).biomeID == AlgaeCraftMain.deepOcean.biomeID){
                    		aspect = 2;
                    	}
                    }

                    if (par1ItemStack.stackSize <= 0)
                    {
                        return new ItemStack(this,1,aspect);
                    }

                    if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(this, 1, aspect)))
                    {
                        par3EntityPlayer.dropPlayerItem(new ItemStack(this, 1, aspect));
                    }
                }
            }

            return par1ItemStack;
        }
    }

}
