package azaka7.algaecraft.common.item;

import azaka7.algaecraft.common.AlgaeCraftMain;
import azaka7.algaecraft.common.block.BlockGuayule;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.Icon;

public class ItemGuayule extends ItemBlock {

	public ItemGuayule(int par1) {
		super(par1);
		this.setMaxDamage(0);
		this.setCreativeTab(AlgaeCraftMain.modTab);
	}
	
	public Icon getIconFromDamage(int par1)
    {
    	return BlockGuayule.iconList[0];
    }
	
	

}
