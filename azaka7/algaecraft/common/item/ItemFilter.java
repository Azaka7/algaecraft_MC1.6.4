package azaka7.algaecraft.common.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.block.BlockFilter;

public class ItemFilter extends ItemBlock {

	public ItemFilter(int par1) {
		super(par1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
	}
	
	public int getMetadata(int par1)
    {
        return par1;
    }
	
	private String getNameFromMetadata(int i){
    	return ((BlockFilter) AlgaeCraftMain.blockFilter).nameList[i&3];
    }
    
    public String getItemDisplayName(ItemStack par1ItemStack)
    {
    	return this.getNameFromMetadata(par1ItemStack.getItemDamage())+" Filter";
    }

}
