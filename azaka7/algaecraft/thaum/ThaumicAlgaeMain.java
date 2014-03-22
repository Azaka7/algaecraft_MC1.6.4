package azaka7.algaecraft.thaum;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.oredict.OreDictionary;
import azaka7.algaecraft.common.AlgaeCraftMain;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

//TODO Change mod requirements before final release!
@Mod(modid = "thaumicalgaecraft", name = "Thaumic Algae", version = "AC 1.4.3 and TC 4.0.5b for MC 1.6.4",dependencies = "required-after:algaecraft"/*;required-after:Thaumcraft"*/)
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class ThaumicAlgaeMain {
	
	private static final ResourceLocation abyssumIcon = new ResourceLocation("azaka7:textures/thaumicalgae/abyssum.png");
	public static final Aspect ABYSS = new Aspect("abyssum", 0x080864, new Aspect[]{Aspect.WATER,Aspect.DARKNESS}, abyssumIcon, 771);
	
	public static int cauldronID;
	public static final int cauldronMeta = 0;
	
	@EventHandler
	public void init(FMLPreInitializationEvent event){
		LanguageRegistry.instance().addStringLocalization("tc.aspect.abyssum", "Depths");
		//System.out.println("@@@@@@@@ config location");
		//System.out.println(event.getSuggestedConfigurationFile().getAbsolutePath());
		//Configuration thaumcraftConfig = new Configuration(new File(event.getModConfigurationDirectory(),"Thaumcraft"));
		//cauldronID = thaumcraftConfig.get("block", "BlockMetalDevice", 2408).getInt();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		
		//TODO Use this to make the sponges work (water and 1000units):FluidContainerRegistry.registerFluidContainer(FluidStack stack, ItemStack filledContainer, ItemStack emptyContainer);
		//TODO Make plants extend BlockCrops and specify fertilize(world, x, y, z) to make hoe of growth work.
		
		FMLInterModComms.sendMessage("Thaumcraft", "harvestStandardCrop", new ItemStack(AlgaeCraftMain.blockGuayule, 1, 3));
		FMLInterModComms.sendMessage("Thaumcraft", "harvestStandardCrop", new ItemStack(AlgaeCraftMain.blockGuayule, 1, 4));
		
		ThaumcraftApi.registerEntityTag("fishyfish", this.constructList(new Aspect[]{Aspect.WATER, Aspect.BEAST, Aspect.SENSES}, new int[]{2,1,1}));
		ThaumcraftApi.registerEntityTag("lobster", this.constructList(new Aspect[]{Aspect.WATER, Aspect.BEAST, Aspect.FLESH}, new int[]{2,2,2}));
		
		ThaumcraftApi.addSmeltingBonus(new ItemStack(AlgaeCraftMain.itemQuickLime), new ItemStack(Item.glowstone,0,0));
		ThaumcraftApi.addSmeltingBonus(new ItemStack(AlgaeCraftMain.itemRubberBall), new ItemStack(AlgaeCraftMain.itemGuayuleBranch,0,0));
		
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockAlgae.blockID, 0, constructList(
				new Aspect[]{Aspect.PLANT,Aspect.SLIME},
				new int[]   {1           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemAlgaeCooked.itemID, 0, constructList(
				new Aspect[]{Aspect.PLANT,Aspect.HUNGER},
				new int[]   {1           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemSquidFried.itemID, 0, constructList(
				new Aspect[]{Aspect.PLANT,Aspect.WATER,Aspect.FLESH,Aspect.HUNGER},
				new int[]   {1           ,1           ,1           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockCoral.blockID, new int[]{0,1,2,3}, constructList(
				new Aspect[]{Aspect.PLANT,Aspect.WATER,Aspect.BEAST},
				new int[]   {1           ,2           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockCoral.blockID, new int[]{4,5,6,7}, constructList(
				new Aspect[]{Aspect.SEED,Aspect.WATER},
				new int[]   {1           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemKnife.itemID, new int[]{0,1}, constructList(
				new Aspect[]{Aspect.TREE,Aspect.METAL,Aspect.WEAPON,Aspect.CRAFT},
				new int[]   {1           ,3          ,1            ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemKnifeGold.itemID, new int[]{0,1}, constructList(
				new Aspect[]{Aspect.TREE,Aspect.METAL,Aspect.WEAPON,Aspect.CRAFT,Aspect.GREED},
				new int[]   {1           ,3          ,1            ,3           ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemRedironElectrolyzer.itemID, new int[]{0,1}, constructList(
				new Aspect[]{Aspect.WATER,Aspect.METAL,Aspect.MECHANISM,Aspect.ENERGY,Aspect.EXCHANGE},
				new int[]   {1           ,6           ,6               ,7            ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemFlask.itemID, 0, constructList(
				new Aspect[]{Aspect.VOID,Aspect.CRYSTAL},
				new int[]   {1           ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemFlask.itemID, 1, constructList(
				new Aspect[]{Aspect.WATER,Aspect.CRYSTAL},
				new int[]   {1           ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemFlask.itemID, 2, constructList(
				new Aspect[]{Aspect.WATER,Aspect.CRYSTAL},
				new int[]   {2           ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemFlask.itemID, 3, constructList(
				new Aspect[]{Aspect.WATER,Aspect.CRYSTAL,Aspect.CRAFT,Aspect.POISON,Aspect.DEATH},
				new int[]   {1           ,2             ,1           ,2            ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemFlask.itemID, new int[]{4,5}, constructList(
				new Aspect[]{Aspect.EXCHANGE},
				new int[]   {10}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockGuayule.blockID, this.hexdecMeta, constructList(
				new Aspect[]{Aspect.PLANT,Aspect.TOOL},
				new int[]   {2           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemGuayuleBranch.itemID, 0, constructList(
				new Aspect[]{Aspect.PLANT,Aspect.TOOL,Aspect.HARVEST},
				new int[]   {1           ,1          ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockLimestone.blockID, 0, constructList(
				new Aspect[]{Aspect.STONE,Aspect.WATER},
				new int[]   {2           ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockLimestone.blockID, 1, constructList(
				new Aspect[]{Aspect.STONE,Aspect.WATER,Aspect.CRAFT},
				new int[]   {2           ,2           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockLimestone.blockID, 2, constructList(
				new Aspect[]{Aspect.STONE,Aspect.WATER,Aspect.MAGIC},
				new int[]   {2           ,2           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockLimestone.blockID, 3, constructList(
				new Aspect[]{Aspect.STONE,Aspect.WATER,Aspect.ORDER},
				new int[]   {2           ,2           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockLimestoneSlab.blockID, this.hexdecMeta, constructList(
				new Aspect[]{Aspect.STONE,Aspect.WATER},
				new int[]   {1           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockLimestoneSlabDouble.blockID, this.hexdecMeta, constructList(
				new Aspect[]{Aspect.STONE,Aspect.WATER},
				new int[]   {2           ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockLimestoneStairs.blockID, this.hexdecMeta, constructList(
				new Aspect[]{Aspect.STONE,Aspect.WATER},
				new int[]   {3           ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockLimestoneStairsBrick.blockID, this.hexdecMeta, constructList(
				new Aspect[]{Aspect.STONE,Aspect.WATER,Aspect.CRAFT},
				new int[]   {3           ,2           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemLobster.itemID, 0, constructList(
				new Aspect[]{Aspect.BEAST,Aspect.WATER,Aspect.FLESH},
				new int[]   {2           ,2           ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemLobster.itemID, 0, constructList(
				new Aspect[]{Aspect.BEAST,Aspect.WATER,Aspect.FLESH},
				new int[]   {2           ,2           ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemLobsterBoiled.itemID, 0, constructList(
				new Aspect[]{Aspect.HUNGER,Aspect.CRAFT,Aspect.WATER,Aspect.FLESH},
				new int[]   {2            ,1           ,1           ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemSquidRaw.itemID, 0, constructList(
				new Aspect[]{Aspect.BEAST,Aspect.WATER,Aspect.FLESH},
				new int[]   {1           ,1           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemLobsterRaw.itemID, 0, constructList(
				new Aspect[]{Aspect.BEAST,Aspect.WATER,Aspect.FLESH},
				new int[]   {1           ,1           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemSquidCooked.itemID, 0, constructList(
				new Aspect[]{Aspect.HUNGER,Aspect.WATER,Aspect.FLESH},
				new int[]   {1           ,1           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemLobsterCooked.itemID, 0, constructList(
				new Aspect[]{Aspect.HUNGER,Aspect.WATER,Aspect.FLESH},
				new int[]   {1           ,1           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockLobsterCage.blockID, this.hexdecMeta, constructList(
				new Aspect[]{Aspect.BEAST,Aspect.WATER,Aspect.TRAP,Aspect.TREE,Aspect.METAL},
				new int[]   {1           ,1           ,2          ,2          ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemRubberRaw.itemID, 0, constructList(
				new Aspect[]{Aspect.MOTION,Aspect.TOOL,Aspect.POISON},
				new int[]   {1           ,1           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemChipRediron.itemID, 0, constructList(
				new Aspect[]{Aspect.MECHANISM,Aspect.METAL,Aspect.ENERGY},
				new int[]   {5               ,5           ,5}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockSeaweed.blockID, this.hexdecMeta, constructList(
				new Aspect[]{Aspect.PLANT,Aspect.WATER},
				new int[]   {1           ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemSeaweedCooked.itemID, 0, constructList(
				new Aspect[]{Aspect.PLANT,Aspect.WATER,Aspect.HUNGER},
				new int[]   {1           ,1           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockSediment.blockID, this.hexdecMeta, constructList(
				new Aspect[]{Aspect.EARTH,Aspect.WATER,Aspect.DARKNESS,Aspect.GREED,Aspect.SLIME,this.ABYSS},
				new int[]   {1           ,1           ,1              ,1           ,1           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemSponge.itemID, 0, constructList(
				new Aspect[]{Aspect.WATER,Aspect.VOID,Aspect.AIR},
				new int[]   {1           ,2          ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemSpongeRed.itemID, 0, constructList(
				new Aspect[]{Aspect.WATER,Aspect.VOID,Aspect.SENSES},
				new int[]   {1           ,2          ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemSponge.itemID, 1, constructList(
				new Aspect[]{Aspect.WATER,Aspect.VOID},
				new int[]   {3           ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemSpongeRed.itemID, 1, constructList(
				new Aspect[]{Aspect.WATER,Aspect.VOID},
				new int[]   {3           ,2}
				));
		ThaumcraftApi.registerObjectTag(Block.sponge.blockID, this.hexdecMeta, constructList(
				new Aspect[]{Aspect.PLANT,Aspect.WATER,Aspect.VOID},
				new int[]   {1           ,2           ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockSponge.blockID, this.hexdecMeta, constructList(
				new Aspect[]{Aspect.SEED,Aspect.WATER},
				new int[]   {1          ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockSpongeRed.blockID, this.hexdecMeta, constructList(
				new Aspect[]{Aspect.PLANT,Aspect.WATER,Aspect.VOID,Aspect.SENSES},
				new int[]   {1           ,2           ,2          ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockSpongeRedSpore.blockID, this.hexdecMeta, constructList(
				new Aspect[]{Aspect.SEED,Aspect.WATER,Aspect.SENSES},
				new int[]   {1          ,1           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemSushiRaw.itemID, 0, constructList(
				new Aspect[]{Aspect.WATER,Aspect.PLANT,Aspect.FLESH,Aspect.HUNGER},
				new int[]   {1           ,1           ,1           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemSushiCooked.itemID, 0, constructList(
				new Aspect[]{Aspect.WATER,Aspect.PLANT,Aspect.FLESH,Aspect.HUNGER},
				new int[]   {1           ,1           ,1           ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockAirCompressor.blockID, new int[]{0,3,6,9}, constructList(
				new Aspect[]{Aspect.METAL,Aspect.MECHANISM,Aspect.AIR,Aspect.EXCHANGE,Aspect.MOTION,Aspect.ENERGY},
				new int[]   {20          ,15              ,5         ,3              ,10           ,5}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockAirCompressor.blockID, new int[]{1,4,7,10}, constructList(
				new Aspect[]{Aspect.METAL,Aspect.MECHANISM,Aspect.AIR,Aspect.EXCHANGE,Aspect.MOTION,Aspect.ENERGY},
				new int[]   {28          ,15              ,10        ,3              ,12           ,5}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemAirTankSmall.itemID, 0, constructList(
				new Aspect[]{Aspect.METAL,Aspect.MOTION,Aspect.TOOL,Aspect.VOID,Aspect.AIR},
				new int[]   {8           ,1            ,1          ,4          ,8}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemScubaBCD.itemID, OreDictionary.WILDCARD_VALUE, constructList(
				new Aspect[]{Aspect.MOTION,Aspect.TOOL,Aspect.CLOTH,Aspect.METAL,Aspect.FLIGHT,Aspect.WATER,Aspect.AIR},
				new int[]   {5            ,5          ,4           ,3           ,1            ,3           ,3}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemWetsuit.itemID, OreDictionary.WILDCARD_VALUE, constructList(
				new Aspect[]{Aspect.CLOTH,Aspect.MOTION,Aspect.ARMOR,Aspect.TOOL,Aspect.WATER,Aspect.SENSES},
				new int[]   {5           ,6            ,1           ,4          ,4           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemFlippers.itemID, OreDictionary.WILDCARD_VALUE, constructList(
				new Aspect[]{Aspect.MOTION,Aspect.TOOL,Aspect.CLOTH},
				new int[]   {5            ,3          ,3}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemScubaGoggles.itemID, OreDictionary.WILDCARD_VALUE, constructList(
				new Aspect[]{Aspect.MOTION,Aspect.TOOL,Aspect.CLOTH,Aspect.CRYSTAL,Aspect.SENSES},
				new int[]   {3            ,3          ,3           ,2             ,3}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockFilter.blockID, 0, constructList(
				new Aspect[]{Aspect.METAL,Aspect.MECHANISM,Aspect.ENERGY,Aspect.WATER,Aspect.MOTION,Aspect.PLANT},
				new int[]   {15          ,5               ,5            ,5           ,4            ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockFilter.blockID, 1, constructList(
				new Aspect[]{Aspect.METAL,Aspect.MECHANISM,Aspect.ENERGY,Aspect.WATER,Aspect.MOTION,Aspect.SLIME},
				new int[]   {15          ,5               ,5            ,5           ,4            ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockFilter.blockID, 2, constructList(
				new Aspect[]{Aspect.METAL,Aspect.MECHANISM,Aspect.ENERGY,Aspect.WATER,Aspect.MOTION},
				new int[]   {15          ,5               ,5            ,7           ,4}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockFilter.blockID, 3, constructList(
				new Aspect[]{Aspect.METAL,Aspect.MECHANISM,Aspect.ENERGY,Aspect.WATER,Aspect.MOTION,Aspect.ELDRITCH},
				new int[]   {15          ,5               ,5            ,5           ,4            ,2}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.blockPlantBreath.blockID, this.hexdecMeta, constructList(
				new Aspect[]{Aspect.WATER,Aspect.PLANT,Aspect.AIR,Aspect.LIGHT,this.ABYSS},
				new int[]   {2           ,1           ,1         ,1           ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemPlantBreath.itemID, 0, constructList(
				new Aspect[]{Aspect.PLANT,Aspect.AIR,Aspect.HARVEST,this.ABYSS},
				new int[]   {1           ,1         ,1             ,1}
				));
		ThaumcraftApi.registerObjectTag(AlgaeCraftMain.itemQuickLime.itemID, 0, constructList(
				new Aspect[]{Aspect.STONE,Aspect.ENERGY,Aspect.ENTROPY},
				new int[]   {1           ,1            ,1}
				));
		
	}
	
	private int[] hexdecMeta = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
	
	private AspectList constructList(Aspect[] aspects, int[] counts){
		AspectList list = new AspectList();
		for(int i = 0; i < aspects.length && i < counts.length; i++){
			list.add(aspects[i], counts[i]);
		}
		return list;
	}
	
	/*ForgeSubscribe
	public void itemUse(PlayerInteractEvent event){
		ItemStack inUse = event.entityPlayer.inventory.getCurrentItem();
		if(inUse.getItem().itemID == AlgaeCraftMain.itemSponge.itemID && inUse.getItemDamage() == 1){
			if(event.entityPlayer.worldObj.getBlockId(event.x, event.y, event.z) == cauldronID){
				if(event.entityPlayer.worldObj.getBlockMetadata(event.x, event.y, event.z) == cauldronMeta){
					TileEntity tile = event.entityPlayer.worldObj.getBlockTileEntity(event.x, event.y, event.z);
					FluidTank tank = new FluidTank(0);
					NBTTagCompound tags = new NBTTagCompound();
					tile.writeToNBT(tags);
					tank = tank.readFromNBT(tags);
					if(tank.getFluid().amount != 0){
						tank.setFluid(new FluidStack(tank.getFluid().getFluid(),tank.getFluidAmount()+1));
						tank.writeToNBT(tags);
						tile.readFromNBT(tags);
					}
					
				}
			}
		}
	}*/
	

}
