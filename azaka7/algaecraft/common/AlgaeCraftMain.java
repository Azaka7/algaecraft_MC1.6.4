package azaka7.algaecraft.common;

import java.io.File;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.src.BaseMod;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.oredict.OreDictionary;
import azaka7.algaecraft.client.ClientProxy;
import azaka7.algaecraft.common.block.*;
import azaka7.algaecraft.common.entity.EntityFish;
import azaka7.algaecraft.common.entity.EntityLobster;
import azaka7.algaecraft.common.entity.EntitySquidMeaty;
import azaka7.algaecraft.common.item.*;
import azaka7.algaecraft.common.world.BiomeGenOceanAC;
import azaka7.algaecraft.common.world.BiomeGenOceanDeep;
import azaka7.algaecraft.common.world.WorldGenFlowers;
import azaka7.algaecraft.common.world.WorldGenReef;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = "algaecraft", name = "AlgaeCraft", version = "1.4.3")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class AlgaeCraftMain implements ICraftingHandler, IWorldGenerator {
	@Instance("algaecraft")
	public static AlgaeCraftMain instance;
	
	@SidedProxy(clientSide = "azaka7.algaecraft.client.ClientProxy", serverSide = "azaka7.algaecraft.common.CommonProxy")
	public static CommonProxy proxy;
	
	public static CreativeTabs modTab = new TabAlgaeCraft();
	
	public static int itemSpongeID;
	public static int itemSeaweedCookedID;
	public static int itemAlgaeCookedID;
	public static int itemSquidRawID;
	public static int itemSquidCookedID;
	public static int itemSquidFriedID;
	public static int itemKnifeID;
	public static int itemLobsterID;
	public static int itemLobsterBoiledID;
	public static int itemLobsterRawID;
	public static int itemLobsterCookedID;
	public static int itemPlantBreathID;
	public static int itemSushiRawID;
	public static int itemSushiCookedID;
	public static int itemChipRedironID;
	public static int itemKnifeGoldID;
	
	public static int itemThermometerID;
	public static int itemPressureGaugeID;
	public static int itemControlBcdID;
	public static int itemDiveWatchID;
	public static int itemAirtankSmallID;
	public static int itemAirtankLargeID;
	
	public static int itemLeadRaw;
	public static int itemLeadIngot;
	public static int itemLeadWeight;
	public static int itemLeadSword;
	
	public static int itemRubberRawID;
	public static int itemRubberBlackID;
	public static int itemNeopreneTextileID;
	public static int itemGuayuleBranchID;
	public static int itemFlaskID;
	public static int itemRedironElectrolyzerID;
	
	public static int itemStandardDivingHelmID;
	public static int itemStandardDivingTorsoID;
	public static int itemStandardDivingLegsID;
	public static int itemStandardDivingShoesID;
	public static int standarDivingAirpipeID;
	
	public static int itemScubaGogglesID;
	public static int itemBcdID;
	public static int itemWetsuitID;
	public static int itemFlippersID;
	
	public static int itemScubaHelmPlainID;
	public static int itemScubaHelmIronID;
	public static int itemScubaHelmGoldID;
	public static int itemScubaHelmDiamondID;
	
	public static int itemBcdIronID;
	public static int itemBcdGoldID;
	public static int itemBcdDiamondID;
	
	public static int itemDrysuitPlainID;
	public static int itemDrysuitIronID;
	public static int itemDrysuitGoldID;
	public static int itemDrysuitDiamondID;
	
	public static int itemFlippersIronID;
	public static int itemFlippersGoldID;
	public static int itemFlippersDiamondID;
	
	public static int itemSpongeRedID;
	
	public static int itemQuickLimeID;
	
	public static int blockAlgaeID;
	public static int blockSpongeID;
	public static int blockSeaweedID;
	public static int blockCoralID;
	public static int blockLobsterCageID;
	public static int blockPlantBreathID;
	public static int blockFilterID;
	public static int blockLeadWornID;
	public static int blockLeadOreID;
	public static int blockLeadBlockID;//including lead brick block
	public static int blockSedimentID;
	public static int blockLimestoneID;
	public static int blockLimestoneSlabID;
	public static int blockLimestoneSlabDoubleID;
	public static int blockLimestoneStairsID;
	public static int blockLimestoneStairsBrickID;
	
	public static int blockSpongeRedID;
	public static int blockSpongeRedSporeID;
	
	public static int blockGuayuleID;
	public static int blockAirCompressorID;
	
	public static int[] reefType;
	public static boolean canReefHaveMossyCobble;
	public static boolean allowMagicCoral;
	public static boolean generateDeepOcean;
	public static int deepOceanBiomeID;
	public static int[] biomeIDSwampList;
	public static int[] biomeIDOceanList;
	public static int[] canPlantCoralOn;
	public static int[] canPlantSpongeOn;
	public static int[] canPlantSeaweedOn;
	
	public static String[] knifeRecipe;
	
	public static int entityMeatySquidID;
	public static int entityLobsterID;
	public static int entityFishID;
	
	public static int waterLightDecrement;
	public static boolean usesFogClarityAPI;
	
	public static boolean specialCoralRender;
	
	private static final Class<?>[][] paramTypes = new Class[][] {{EnumCreatureType.class, Class.class, int.class, Material.class, boolean.class, boolean.class}};
	public static final EnumCreatureType ambientWater = EnumHelper.addEnum(paramTypes, EnumCreatureType.class, "ambientWaterFish", EntityWaterMob.class, 256, Material.water, true, false);
	public static BiomeGenBase oceanAC;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		//File file = new File(event.getModConfigurationDirectory(),"shipLargeFloat.txt");
		//Configuration config1 = new Configuration(shipSchematics);
		
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		config.load();
		
		blockAlgaeID = config.getBlock("blockAlgae", 1250).getInt();
		blockSpongeID = config.getBlock("blockSponge", 1251).getInt();
		blockSeaweedID = config.getBlock("blockSeaweed", 1252).getInt();
		blockCoralID = config.getBlock("blockCoral", 1253).getInt();
		blockLobsterCageID = config.getBlock("blockLobsterCage",1254).getInt();
		blockPlantBreathID = config.getBlock("blockWaterBreathPlant",1255).getInt();
		blockFilterID = config.getBlock("blockFilter",1249).getInt();
		blockSedimentID = config.getBlock("blockSediment",255).getInt();
		blockLimestoneID = config.getBlock("blockLimestone",1248).getInt();
		blockLimestoneStairsID = config.getBlock("blockLimestoneStairs",1247).getInt();
		blockLimestoneStairsBrickID = config.getBlock("blockLimestoneBrickStairs",1246).getInt();
		blockLimestoneSlabID = config.getBlock("blockLimestoneSlab",1245).getInt();
		blockLimestoneSlabDoubleID = config.getBlock("blockLimestoneDoubleSlab",1244).getInt();
		blockSpongeRedID = config.getBlock("blockSpongeRed",1243).getInt();
		blockSpongeRedSporeID = config.getBlock("blockSpongeRedSpores",1242).getInt();
		blockGuayuleID = config.getBlock("blockGuayule",1241).getInt();
		blockAirCompressorID = config.getBlock("blockAirCompressor",1240).getInt();
		
		itemSpongeRedID = config.getItem("itemSpongeRed", 11233).getInt();
		itemSpongeID = config.getItem("itemSponge", 11234).getInt();
		itemSeaweedCookedID = config.getItem("itemSeaweedCooked", 11235).getInt();
		itemAlgaeCookedID = config.getItem("itemAlgaeCooked", 11236).getInt();
		itemSquidRawID = config.getItem("itemSquidRaw", 11237).getInt();
		itemSquidCookedID = config.getItem("itemSquidCooked", 11238).getInt();
		itemSquidFriedID = config.getItem("itemSquidFried", 11239).getInt();
		itemKnifeID = config.getItem("itemKnife", 11240).getInt();
		itemLobsterID = config.getItem("itemLobster", 11241).getInt();
		itemLobsterBoiledID = config.getItem("itemLobsterBoiled", 11242).getInt();
		itemLobsterRawID = config.getItem("itemLobsterRaw", 11243).getInt();
		itemLobsterCookedID = config.getItem("itemLobsterCooked", 11244).getInt();
		itemPlantBreathID = config.getItem("itemWaterBreathPlant", 11245).getInt();
		itemSushiRawID = config.getItem("itemSushiRaw", 11246).getInt();
		itemSushiCookedID = config.getItem("itemSushiCooked", 11247).getInt();
		itemChipRedironID = config.getItem("itemRedironChip", 11248).getInt();
		itemKnifeGoldID = config.getItem("itemKnifeGold",11249).getInt();
		
		itemScubaGogglesID = config.getItem("itemScubaMask",11250).getInt();
		itemBcdID = config.getItem("itemScubaBCD",11251).getInt();
		itemAirtankSmallID = config.getItem("itemAirTankSmall",11252).getInt();
		itemWetsuitID = config.getItem("itemWetsuit",11253).getInt();
		itemFlippersID = config.getItem("itemFlippers",11254).getInt();
		
		itemRubberRawID = config.getItem("itemRubberRaw",11255).getInt();
		itemRubberBlackID = config.getItem("itemRubberBall",11256).getInt();
		itemGuayuleBranchID = config.getItem("itemGuayuleBranch",11257).getInt();
		itemFlaskID = config.getItem("itemFlask",11258).getInt();
		itemRedironElectrolyzerID = config.getItem("itemRedironElectrolyzer",11259).getInt();
		itemNeopreneTextileID = config.getItem("itemNeopreneTextile",11260).getInt();
		
		itemQuickLimeID = config.getItem("itemQuickLime",11261).getInt();
		
		entityMeatySquidID = config.get("entities", "entityMeatySquid", 127).getInt();
		entityLobsterID = config.get("entities", "entityLobster", 126).getInt();
		entityFishID = config.get("entities", "entityFish", 125).getInt();
		
		String[] configRecipeMultistring = config.get("general", "knifeCrafting (see forum for syntax)", new String[]{"I-xI","Iy-I"}).getStringList();
		char[] s1 = configRecipeMultistring[0].toCharArray();
		if(s1[1]=='-'){s1[1]=' ';}else if(s1[1]!='x' && s1[1]!='y'){s1[1]='x';}
		if(s1[2]=='-'){s1[2]=' ';}else if(s1[2]!='x' && s1[2]!='y'){s1[2]='x';}
		char[] s2 = configRecipeMultistring[1].toCharArray();
		if(s2[1]=='-'){s2[1]=' ';}else if(s2[1]!='x' && s2[1]!='y'){s2[1]='y';}
		if(s2[2]=='-'){s2[2]=' ';}else if(s2[2]!='x' && s2[2]!='y'){s2[2]='x';}
		String r1 = String.valueOf(new char[]{s1[1],s1[2]}); String r2 = String.valueOf(new char[]{s2[1],s2[2]});
		knifeRecipe = new String[]{r1,r2};
		
		reefType = config.get(Configuration.CATEGORY_GENERAL, "reefTypes (0:cobble|1:sandstone|2:limestone)", new int[]{2}).getIntList();
		canReefHaveMossyCobble = config.get(Configuration.CATEGORY_GENERAL, "canReefHaveMossyCobble", true).getBoolean(true);
		allowMagicCoral = config.get(Configuration.CATEGORY_GENERAL, "allowMagicCoral", false).getBoolean(false);
		generateDeepOcean = config.get(Configuration.CATEGORY_GENERAL, "generateDeepOcean", true).getBoolean(true);
		
		int available = 23;
		for(int k = 0; k < BiomeGenBase.biomeList.length; k++){
			if(BiomeGenBase.biomeList[k] == null){
				available = k;
				break;
			}
			if(k == BiomeGenBase.biomeList.length){available = 23;}
		}
		deepOceanBiomeID = config.get(Configuration.CATEGORY_GENERAL, "deepOceanBiomeID", available).getInt(available);
		biomeIDSwampList = config.get(Configuration.CATEGORY_GENERAL, "algaeBiomeIDList", new int[]{BiomeGenBase.swampland.biomeID}).getIntList();
		biomeIDOceanList = config.get(Configuration.CATEGORY_GENERAL, "oceanBiomeIDList", new int[]{BiomeGenBase.ocean.biomeID,BiomeGenBase.frozenOcean.biomeID}).getIntList();
		
		canPlantCoralOn = config.get(Configuration.CATEGORY_GENERAL, "allowCoralGrowthOn", new int[]{AlgaeCraftMain.blockLimestoneID,Block.cobblestone.blockID, Block.cobblestoneMossy.blockID,Block.sandStone.blockID,Block.dirt.blockID,Block.sand.blockID,Block.gravel.blockID,Block.stone.blockID,Block.blockClay.blockID,Block.hardenedClay.blockID,Block.stainedClay.blockID}).getIntList();
		canPlantSeaweedOn = config.get(Configuration.CATEGORY_GENERAL, "allowSeaweedGrowthOn", new int[]{AlgaeCraftMain.blockLimestoneID,Block.cobblestone.blockID, Block.cobblestoneMossy.blockID,Block.sandStone.blockID,Block.dirt.blockID,Block.sand.blockID,Block.gravel.blockID,Block.stone.blockID,Block.blockClay.blockID,Block.hardenedClay.blockID,Block.stainedClay.blockID}).getIntList();
		canPlantSpongeOn = config.get(Configuration.CATEGORY_GENERAL, "allowSeaweedGrowthOn", new int[]{AlgaeCraftMain.blockLimestoneID,Block.cobblestone.blockID, Block.cobblestoneMossy.blockID,Block.sandStone.blockID,Block.dirt.blockID,Block.sand.blockID,Block.gravel.blockID,Block.stone.blockID,Block.blockClay.blockID,Block.hardenedClay.blockID,Block.stainedClay.blockID,Block.grass.blockID, Block.sponge.blockID}).getIntList();
		
		waterLightDecrement = config.get(Configuration.CATEGORY_GENERAL, "waterLightDecrement (default=2;vanilla=3)", 2).getInt(2);
		usesFogClarityAPI = config.get(Configuration.CATEGORY_GENERAL, "usesFogClarityAPI", false).getBoolean(false);
		
		specialCoralRender = config.get(Configuration.CATEGORY_GENERAL, "specialOutOfWaterCoralRendering", false).getBoolean(false);
		
		config.addCustomCategoryComment(Configuration.CATEGORY_GENERAL, "When Block Model IDs are set to -1, they are automatically chosen by an internal algorithm.");
		algaeModelID = config.get(Configuration.CATEGORY_GENERAL, "algaeModelID", -1).getInt();
		coralModelID = config.get(Configuration.CATEGORY_GENERAL, "coralModelID", -1).getInt();
		spongeModelID = config.get(Configuration.CATEGORY_GENERAL, "spongeModelID", -1).getInt();
		seaweedModelID = config.get(Configuration.CATEGORY_GENERAL, "seaweedModelID", -1).getInt();
		
		config.save();
	}
	
	public static BiomeGenBase deepOcean;
	public static BiomeGenBase shallowOcean;
	public static BiomeGenBase abysalOcean;
	public static ItemStack potionWaterBreath;
	public static ItemStack potionWaterBreathSplash;
	public static ItemStack potionWaterBreathLong;
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		proxy.registerRenders();
		//if(event.getSide() == Side.CLIENT){
			/*
			algaeModelID = RenderingRegistry.getNextAvailableRenderId();//ModLoader.getUniqueBlockModelID(this, false);
			spongeModelID = RenderingRegistry.getNextAvailableRenderId();//ModLoader.getUniqueBlockModelID(this, false);
			coralModelID = RenderingRegistry.getNextAvailableRenderId();//ModLoader.getUniqueBlockModelID(this, false);
			seaweedModelID = RenderingRegistry.getNextAvailableRenderId();//ModLoader.getUniqueBlockModelID(this, false);
		*/
		//}
		proxy.registerTickRegistry();
		
		itemSponge = new ItemSponge(itemSpongeID, "").setUnlocalizedName("drySponge").setCreativeTab(modTab);
		LanguageRegistry.addName(itemSponge, "Sponge Item");

		FluidContainerRegistry.registerFluidContainer(FluidRegistry.WATER, new ItemStack(itemSponge, 1, 1), new ItemStack(itemSponge, 1, 0));
		
		itemSpongeRed = new ItemSponge(itemSpongeRedID, "Red").setUnlocalizedName("drySpongeRed").setCreativeTab(modTab);
		LanguageRegistry.addName(itemSpongeRed, "Red Sponge Item");

		FluidContainerRegistry.registerFluidContainer(FluidRegistry.WATER, new ItemStack(itemSpongeRed, 1, 1), new ItemStack(itemSpongeRed, 1, 0));
		
		itemSeaweedCooked = new ItemFoodBasic(itemSeaweedCookedID, "seaweedCooked", 3, 0.3F, false).setUnlocalizedName("itemSeaweedCooked");
		LanguageRegistry.addName(itemSeaweedCooked, "Crispy Seaweed");
		
		itemAlgaeCooked = new ItemFoodBasic(itemAlgaeCookedID, "algaeChips", 2, 0.2F, false).setPotionEffect(Potion.hunger.getId(), 5, 0, 0.2F).setUnlocalizedName("itemAlgaeCooked");
		LanguageRegistry.addName(itemAlgaeCooked, "Algae Chips");
		
		itemSquidRaw = new ItemFoodBasic(itemSquidRawID, "rawSquid", 4, 0.5F, false).setPotionEffect(Potion.hunger.getId(), 6, 0, 0.3F).setUnlocalizedName("itemSquidRaw");
		LanguageRegistry.addName(itemSquidRaw, "Raw Squid Meat");
		
		itemSquidCooked = new ItemFoodBasic(itemSquidCookedID, "calamariSeared", 7, 0.7F, true).setUnlocalizedName("itemSquidCooked");
		LanguageRegistry.addName(itemSquidCooked, "Calamari Steak");
		
		itemSquidFried = new ItemFoodContained(itemSquidFriedID, "calamari", new ItemStack(Item.bowlEmpty,1,0), 9, 0.9F, false).setUnlocalizedName("itemSquidFried");
		LanguageRegistry.addName(itemSquidFried, "Fried Calamari Rings");
		
		itemKnife = new ItemKnife(itemKnifeID, EnumToolMaterial.STONE, "").setUnlocalizedName("itemKnife");
		LanguageRegistry.addName(itemKnife, "Cutting Knife");
		
		itemLobster = new ItemLobster(itemLobsterID).setUnlocalizedName("itemLobster");
		LanguageRegistry.addName(itemLobster, "Lobster");
		
		itemLobsterBoiled = new ItemGeneric(itemLobsterBoiledID,"lobsterBoiledItem", modTab, new String[]{"Mostly harmless."}).setUnlocalizedName("itemLobsterBoiled");
		LanguageRegistry.addName(itemLobsterBoiled, "Boiled Lobster");
		
		itemLobsterRaw = new ItemFoodBasic(itemLobsterRawID, "lobsterRaw", 2, 0.1F, false).setPotionEffect(Potion.hunger.getId(), 2, 0, 0.1F).setUnlocalizedName("itemLobsterRaw");
		LanguageRegistry.addName(itemLobsterRaw, "Raw Lobster Meat");
		
		itemLobsterCooked = new ItemFoodBasic(itemLobsterCookedID, "lobsterCooked", 8, 0.4F, true).setUnlocalizedName("itemLobsterCooked");
		LanguageRegistry.addName(itemLobsterCooked, "Cooked Lobster Meat");
		
		itemSushiRaw = new ItemFoodBasic(itemSushiRawID, "sushiRaw", 6, 0.6F, false).setUnlocalizedName("sushiRaw");
		LanguageRegistry.addName(itemSushiRaw, "Sushi (Raw Fish)");
		
		itemSushiCooked = new ItemFoodBasic(itemSushiCookedID, "sushiCooked", 9, 0.9F, true).setUnlocalizedName("sushiCooked");
		LanguageRegistry.addName(itemSushiCooked, "Sushi (Cooked Fish)");
		
		itemChipRediron = new ItemGeneric(itemChipRedironID, "chipRediron", modTab, new String[]{"It seems complicated..."}).setUnlocalizedName("itemRedironChip");
		LanguageRegistry.addName(itemChipRediron, "Red-Iron Chip");
		
		itemKnifeGold = new ItemKnife(itemKnifeGoldID, EnumToolMaterial.GOLD, "Gold").setUnlocalizedName("itemKnifeGold");
		LanguageRegistry.addName(itemKnifeGold, "Golden Knife");
		
		itemPlantBreath = new ItemWaterBreathPearl(itemPlantBreathID).setCreativeTab(modTab).setUnlocalizedName("itemWaterBreathPearl");
		LanguageRegistry.addName(itemPlantBreath, "Aeros Bulb");
		
		itemRubberRaw = new ItemGeneric(itemRubberRawID, "rubberRaw", modTab, new String[]{"Sticky..."}).setUnlocalizedName("rubberRaw");
		LanguageRegistry.addName(itemRubberRaw, "Raw Rubber");
		
		itemRubberBall = new ItemGeneric(itemRubberBlackID, "rubberBall", modTab, new String[]{"Bouncy!"}).setUnlocalizedName("rubberBlack");
		LanguageRegistry.addName(itemRubberBall, "Rubber Ball");
		
		itemNeopreneTextile = new ItemGeneric(itemNeopreneTextileID, "neopreneTextile", modTab).setUnlocalizedName("neopreneTextile");
		LanguageRegistry.addName(itemNeopreneTextile, "Neoprene Textile");
		
		itemGuayuleBranch = new ItemGeneric(itemGuayuleBranchID, "guayuleBranches", modTab, new String[]{"A fancy stick."}).setUnlocalizedName("itemGuayuleBranch");
		LanguageRegistry.addName(itemGuayuleBranch, "Guayule Branch");
		
		itemFlask = new ItemFlask(itemFlaskID).setCreativeTab(modTab).setUnlocalizedName("itemFlask");
		LanguageRegistry.addName(itemFlask, "Flask");
		LanguageRegistry.instance().addStringLocalization("item.itemFlask.Empty.name", "Empty Flask");
		LanguageRegistry.instance().addStringLocalization("item.itemFlask.Fresh.name", "Fresh Water Flask");
		LanguageRegistry.instance().addStringLocalization("item.itemFlask.Salt.name", "Salt Water Flask");
		LanguageRegistry.instance().addStringLocalization("item.itemFlask.NaOH.name", "Flask of Sodium Hydroxide");
		
		itemRedironElectrolyzer = new ItemElectrolyzer(itemRedironElectrolyzerID).setCreativeTab(modTab).setUnlocalizedName("itemRedironElectrolyzer");
		LanguageRegistry.addName(itemRedironElectrolyzer, "Redstone Electrolyzer");
		
		itemQuickLime = new ItemGeneric(itemQuickLimeID, "quicklime", modTab, new String[]{"Calcium Oxide"}).setUnlocalizedName("itemQuicklime");
		LanguageRegistry.addName(itemQuickLime, "Quicklime");
		GameRegistry.addRecipe(new ItemStack(Item.glowstone, 4), new Object[]{
			" l ", "lbl", " l ", Character.valueOf('l'), new ItemStack(itemQuickLime), Character.valueOf('b'), new ItemStack(Item.blazePowder)});
		
		//algaeModelID = ModLoader.getUniqueBlockModelID(this, false); **At top
		blockAlgae = new BlockAlgae(blockAlgaeID ,8).setUnlocalizedName("blockAlgae").setCreativeTab(modTab);
		GameRegistry.registerBlock(blockAlgae,"blockAlgae");
		LanguageRegistry.addName(blockAlgae, "Algae Block");
		
		//spongeModelID = ModLoader.getUniqueBlockModelID(this, false); **at top
		blockSponge = new BlockSpongeSpore(blockSpongeID,Block.sponge.blockID).setUnlocalizedName("blockSponge").setCreativeTab(modTab);
		GameRegistry.registerBlock(blockSponge,"blockSponge");
		LanguageRegistry.addName(blockSponge, "Sponge Spore Block");
		
		blockSeaweed = new BlockSeaweed(blockSeaweedID, 9).setCreativeTab(modTab).setUnlocalizedName("blockSeaweed");
		GameRegistry.registerBlock(blockSeaweed,"blockSeaweed");
		LanguageRegistry.addName(blockSeaweed, "Seaweed Block");
		
		//coralModelID = ModLoader.getUniqueBlockModelID(this, false); **At top
		blockCoral = new BlockCoral(blockCoralID).setUnlocalizedName("blockCoral").setCreativeTab(modTab);
		GameRegistry.registerBlock(blockCoral,"blockCoral");
		LanguageRegistry.addName(blockCoral, "Coral Block");
		
		blockLobsterCage = new BlockLobsterCage(blockLobsterCageID).setUnlocalizedName("blockLobsterCage");
		GameRegistry.registerBlock(blockLobsterCage,"blockLobsterCage");
		LanguageRegistry.addName(blockLobsterCage, "Lobster Cage");
		
		blockFilter = new BlockFilter(blockFilterID).setHardness(4.0F).setResistance(5.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("blockIron").setUnlocalizedName("blockFilter").setCreativeTab(modTab);
		GameRegistry.registerBlock(blockFilter,"blockFilter");
		LanguageRegistry.addName(blockFilter, "Water Filter");
		
		blockSediment = new BlockGenericMultidrop(blockSedimentID, Material.sand, "sediment", new ItemStack[]{new ItemStack(Block.sand), new ItemStack(Block.dirt), new ItemStack(Item.clay, 4), new ItemStack(Block.gravel)},new ItemStack[]{
        		new ItemStack(Item.goldNugget), 
        		new ItemStack(Item.goldNugget), 
        		new ItemStack(Item.goldNugget), 
        		new ItemStack(Item.goldNugget, 2), 
        		new ItemStack(Item.goldNugget, 2), 
        		new ItemStack(Item.goldNugget, 3), 
        		new ItemStack(Item.ingotGold), 
        		new ItemStack(Item.diamond),
        		new ItemStack(Item.arrow), 
        		new ItemStack(Item.arrow),
        		new ItemStack(Item.arrow, 2), 
        		new ItemStack(Item.arrow, 5), 
        		new ItemStack(Item.brick),
        		new ItemStack(Item.bone),
        		new ItemStack(Item.bone),
        		new ItemStack(Item.bone, 2),
        		new ItemStack(Item.compass),
        		new ItemStack(Item.silk),
        		new ItemStack(Item.silk),
        		new ItemStack(Item.slimeBall),
        		new ItemStack(Item.slimeBall),
        		new ItemStack(Item.slimeBall),
        		new ItemStack(Item.slimeBall),
        		new ItemStack(Item.bootsLeather, 1, 43),
        		new ItemStack(Item.poisonousPotato),
        		new ItemStack(Item.appleGold),
        		new ItemStack(Item.sign),
        		new ItemStack(Item.sign),
        		new ItemStack(Item.skull),
        		new ItemStack(Item.saddle),
        		new ItemStack(Item.rottenFlesh),
        		new ItemStack(Item.rottenFlesh),
        		new ItemStack(Item.enderPearl),
        		new ItemStack(Item.emerald),
        		new ItemStack(this.itemSponge),
        		new ItemStack(Item.shovelGold, 1, 17),
        		new ItemStack(Item.pocketSundial)}).setHardness(0.5F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("blockSediment");
		GameRegistry.registerBlock(blockSediment, "blockSediment");
		LanguageRegistry.addName(blockSediment, "Pelagic Sediment");
		
		blockPlantBreath = new BlockWaterBreathPlant(this.blockPlantBreathID).setCreativeTab(modTab).setUnlocalizedName("blockPlantBreath");
		GameRegistry.registerBlock(blockPlantBreath, "blockPlantBreath");
		LanguageRegistry.addName(blockPlantBreath, "Aeros Plantae");
		LanguageRegistry.addName(Item.itemsList[blockPlantBreath.blockID], "Aeros Plantae Seeds");
		
		blockLimestone = new BlockLimestone(blockLimestoneID).setStepSound(Block.soundStoneFootstep).setHardness(0.8F).setUnlocalizedName("blockLimestone");
		GameRegistry.registerBlock(blockLimestone, "blockLimestone");
		
		blockLimestoneStairs = new BlockStairsAC(blockLimestoneStairsID, blockLimestone, 0).setUnlocalizedName("blockLimestoneStairs");
		GameRegistry.registerBlock(blockLimestoneStairs, "blockLimestoneStairs");
		LanguageRegistry.addName(blockLimestoneStairs, "Limestone Stairs");
		
		blockLimestoneStairsBrick = new BlockStairsAC(blockLimestoneStairsBrickID, blockLimestone, 1).setUnlocalizedName("blockLimestoneStairsBrick");
		GameRegistry.registerBlock(blockLimestoneStairsBrick, "blockLimestoneStairsBrick");
		LanguageRegistry.addName(blockLimestoneStairsBrick, "Limestone Brick Stairs");
		
		blockLimestoneSlab = (BlockHalfSlab) new BlockSlab(blockLimestoneSlabID, false, Material.rock,"limestone", new String[]{"","Brick"}, new String[]{"plain","brick"}).setCreativeTab(modTab);
		GameRegistry.registerBlock(blockLimestoneSlab, "blockLimestoneSlab");
		LanguageRegistry.instance().addStringLocalization("blockSlab.limestoneplain.name", "Limestone Slab");
		
		blockLimestoneSlabDouble = (BlockHalfSlab) new BlockSlab(blockLimestoneSlabDoubleID, true, Material.rock,"limestone", new String[]{"","Brick"}, new String[]{"plain","brick"}).setCreativeTab(modTab);
		GameRegistry.registerBlock(blockLimestoneSlabDouble, "blockLimestoneSlabDouble");
		LanguageRegistry.instance().addStringLocalization("blockSlab.limestonebrick.name", "Limestone Brick Slab");
		
		blockSpongeRed = new BlockGeneric(blockSpongeRedID, Material.sponge, Block.sponge.blockHardness, Block.sponge.blockResistance, "spongeRed", "Red Sponge").setUnlocalizedName("blockSpongeRed").setCreativeTab(modTab);
		GameRegistry.registerBlock(blockSpongeRed, "blockSpongeRed");
		LanguageRegistry.addName(blockSpongeRed, "Red Sponge");
		
		blockSpongeRedSpore = new BlockSpongeSpore(blockSpongeRedSporeID, blockSpongeRed.blockID).setUnlocalizedName("blockSpongeRedSpore");
		GameRegistry.registerBlock(blockSpongeRedSpore, "blockSpongeRedSpore");
		LanguageRegistry.addName(blockSpongeRedSpore, "Red Sponge Spores");
		
		blockGuayule = new BlockGuayule(blockGuayuleID);
		GameRegistry.registerBlock(blockGuayule, ItemGuayule.class, "blockGuayule");
		LanguageRegistry.addName(blockGuayule, "Guayule");
		
		blockAirCompressor = new BlockAirCompressor(blockAirCompressorID).setUnlocalizedName("blockAirCompressor");
		GameRegistry.registerBlock(blockAirCompressor, "blockAirCompressor");
		LanguageRegistry.addName(Item.itemsList[blockAirCompressor.blockID], "Air Compressor");
		LanguageRegistry.addName(blockAirCompressor, "Air Compressor");
		
		Item.itemsList[blockAlgae.blockID] = new ItemAlgae(blockAlgae.blockID-256, 5).setUnlocalizedName("algae").setCreativeTab(modTab);
		LanguageRegistry.addName(Item.itemsList[blockAlgae.blockID], "Algae");
		Item.itemsList[blockSponge.blockID] = new ItemSpongeSpore(blockSponge.blockID - 256, "", (BlockSpongeSpore) this.blockSponge).setUnlocalizedName("spongeSpore");
		Item.itemsList[blockSeaweed.blockID] = new ItemSeaweed(blockSeaweed.blockID - 256).setUnlocalizedName("itemSeaweed").setCreativeTab(modTab);
		Item.itemsList[blockCoral.blockID] = new ItemCoral(blockCoral.blockID - 256).setUnlocalizedName("itemCoral").setCreativeTab(modTab);
		Item.itemsList[blockFilter.blockID] = new ItemFilter(blockFilter.blockID -256).setUnlocalizedName("itemFilter").setCreativeTab(modTab);
		Item.itemsList[blockPlantBreath.blockID] = new ItemBlockGeneric(blockPlantBreath.blockID -256, "waterBreathPlantSeeds").setUnlocalizedName("itemPlantBreath").setCreativeTab(modTab);
		Item.itemsList[blockLimestone.blockID] = new ItemBlockMetadata(blockLimestone.blockID -256, new String[]{"Rough ","","Chiseled ",""}, new String[]{""," Brick",""," Tiles"}, "Limestone").setUnlocalizedName("itemBlockLimestone").setCreativeTab(modTab);
		Item.itemsList[blockLimestoneSlab.blockID] = new ItemSlab(blockLimestoneSlab.blockID - 256, blockLimestoneSlab, blockLimestoneSlabDouble, false).setUnlocalizedName("itemBlockLimestoneSlab").setCreativeTab(modTab);
		Item.itemsList[blockLimestoneSlabDouble.blockID] = new ItemSlab(blockLimestoneSlabDouble.blockID - 256, blockLimestoneSlab, blockLimestoneSlabDouble, false).setUnlocalizedName("itemBlockLimestoneSlabDouble").setCreativeTab(modTab);
		Item.itemsList[blockSpongeRedSpore.blockID] = new ItemSpongeSpore(blockSpongeRedSpore.blockID - 256, "Red", (BlockSpongeSpore) this.blockSpongeRedSpore).setUnlocalizedName("spongeSporeRed");
		Item.itemsList[blockAirCompressor.blockID] = new ItemBlockGeneric(blockAirCompressor.blockID - 256, "airCompressorIcon").setUnlocalizedName("itemAirCompressor").setCreativeTab(modTab);
		///////////////////////////////////////////////////////////
		
		GameRegistry.addRecipe(new ItemStack(itemChipRediron), new Object[]{
			"yzy", "xxx", Character.valueOf('x'), new ItemStack(Item.ingotIron), Character.valueOf('y'), new ItemStack(Item.redstone), Character.valueOf('z'), new ItemStack(Item.netherQuartz)});
		GameRegistry.addRecipe(new ItemStack(blockFilter, 1, 2), new Object[]{
			"g g", "ibi", "cpc", Character.valueOf('g'), new ItemStack(Block.fenceIron), Character.valueOf('i'), new ItemStack(Block.pressurePlateIron), Character.valueOf('b'), new ItemStack(Item.bucketWater), Character.valueOf('c'), new ItemStack(itemChipRediron), Character.valueOf('p'), new ItemStack(Block.pistonBase)});
		GameRegistry.addRecipe(new ItemStack(blockFilter, 1, 0), new Object[]{
			"gmg", "ibi", "cpc", Character.valueOf('g'), new ItemStack(Block.fenceIron), Character.valueOf('i'), new ItemStack(Block.pressurePlateIron), Character.valueOf('b'), new ItemStack(Item.bucketWater), Character.valueOf('c'), new ItemStack(itemChipRediron), Character.valueOf('p'), new ItemStack(Block.pistonBase), Character.valueOf('m'), new ItemStack(blockSeaweed)});
		GameRegistry.addRecipe(new ItemStack(blockFilter, 1, 1), new Object[]{
			"gmg", "ibi", "cpc", Character.valueOf('g'), new ItemStack(Block.fenceIron), Character.valueOf('i'), new ItemStack(Block.pressurePlateIron), Character.valueOf('b'), new ItemStack(Item.bucketWater), Character.valueOf('c'), new ItemStack(itemChipRediron), Character.valueOf('p'), new ItemStack(Block.pistonBase), Character.valueOf('m'), new ItemStack(blockAlgae)});
		GameRegistry.addRecipe(new ItemStack(blockFilter, 1, 3), new Object[]{
			"gmg", "ibi", "cpc", Character.valueOf('g'), new ItemStack(Block.fenceIron), Character.valueOf('i'), new ItemStack(Block.pressurePlateIron), Character.valueOf('b'), new ItemStack(Item.bucketWater), Character.valueOf('c'), new ItemStack(itemChipRediron), Character.valueOf('p'), new ItemStack(Block.pistonBase), Character.valueOf('m'), new ItemStack(Item.enderPearl)});
		
		GameRegistry.addShapelessRecipe(new ItemStack(itemSponge,2,0), new Object[]{new ItemStack(Block.sponge)});
		GameRegistry.addShapelessRecipe(new ItemStack(blockSponge,2,0), new Object[]{new ItemStack(itemSponge)});
		GameRegistry.addShapelessRecipe(new ItemStack(itemSpongeRed,2,0), new Object[]{new ItemStack(blockSpongeRed)});
		GameRegistry.addShapelessRecipe(new ItemStack(blockSpongeRedSpore,2,0), new Object[]{new ItemStack(itemSpongeRed)});
		
		//recipes can be mirrored
		GameRegistry.addRecipe(new ItemStack(itemKnife), new Object[]{
			knifeRecipe[0], knifeRecipe[1], Character.valueOf('x'), new ItemStack(Item.ingotIron), Character.valueOf('y'), new ItemStack(Item.stick)});
		GameRegistry.addRecipe(new ItemStack(itemKnifeGold), new Object[]{
			knifeRecipe[0], knifeRecipe[1], Character.valueOf('x'), new ItemStack(Item.ingotGold), Character.valueOf('y'), new ItemStack(Item.stick)});
		
		//coral_dye
		GameRegistry.addRecipe(new ItemStack(Item.dyePowder,1,12), new Object[]{
			"x", Character.valueOf('x'), new ItemStack(blockCoral,1,7)});
		GameRegistry.addRecipe(new ItemStack(Item.dyePowder,1,5), new Object[]{
			"x", Character.valueOf('x'), new ItemStack(blockCoral,1,5)});
		GameRegistry.addRecipe(new ItemStack(Item.dyePowder,1,9), new Object[]{
			"x", Character.valueOf('x'), new ItemStack(blockCoral,1,6)});
		GameRegistry.addRecipe(new ItemStack(Item.dyePowder,1,14), new Object[]{
			"x", Character.valueOf('x'), new ItemStack(blockCoral,1,4)});
		//break_blockCoral
		GameRegistry.addRecipe(new ItemStack(blockCoral,2,7), new Object[]{
			"x", Character.valueOf('x'), new ItemStack(blockCoral,1,3)});
		GameRegistry.addRecipe(new ItemStack(blockCoral,2,5), new Object[]{
			"x", Character.valueOf('x'), new ItemStack(blockCoral,1,1)});
		GameRegistry.addRecipe(new ItemStack(blockCoral,2,6), new Object[]{
			"x", Character.valueOf('x'), new ItemStack(blockCoral,1,2)});
		GameRegistry.addRecipe(new ItemStack(blockCoral,2,4), new Object[]{
			"x", Character.valueOf('x'), new ItemStack(blockCoral,1,0)});
		
		for(int woodDamage = 0; woodDamage<16; woodDamage++){
		GameRegistry.addRecipe(new ItemStack(blockLobsterCage,1), new Object[]{
			"xyx", "y y", "xyx", Character.valueOf('x'), new ItemStack(Block.planks,1,woodDamage), Character.valueOf('y'), new ItemStack(Block.fenceIron)});
		}
		
		ItemStack breathPot = new ItemStack(373, 1, 8283);
		breathPot.setItemName("Potion of Water Breathing");
		breathPot.getTagCompound().setTag("CustomPotionEffects", new NBTTagList());
		NBTTagCompound breathPotInfo = new NBTTagCompound("waterBreathPotion");
		breathPotInfo.setByte("Id", (byte) Potion.waterBreathing.id);
		breathPotInfo.setByte("Amplifier", (byte) 0);
		breathPotInfo.setInteger("Duration", 400);
		breathPotInfo.setBoolean("Ambient", false);
		breathPot.getTagCompound().getTagList("CustomPotionEffects").appendTag(breathPotInfo);
		potionWaterBreath = breathPot.copy();
		breathPot.getTagCompound().getTagList("CustomPotionEffects").removeTag(0);
		breathPot.setItemDamage(8347);
		breathPotInfo.setInteger("Duration", 800);
		breathPot.getTagCompound().getTagList("CustomPotionEffects").appendTag(breathPotInfo);
		potionWaterBreathLong = breathPot.copy();
		breathPot.getTagCompound().getTagList("CustomPotionEffects").removeTag(0);
		breathPot.setItemName("Splash Potion of Water Breathing");
		breathPot.setItemDamage(16539);
		breathPotInfo.setInteger("Duration", 200);
		breathPot.getTagCompound().getTagList("CustomPotionEffects").appendTag(breathPotInfo);
		potionWaterBreathSplash = breathPot.copy();
		
		GameRegistry.addRecipe(new ItemStack(blockLimestone.blockID, 4, 1), new Object[]{
			"xx","xx",Character.valueOf('x'), new ItemStack(blockLimestone.blockID, 1, 0)
		});
		GameRegistry.addRecipe(new ItemStack(blockLimestoneSlab.blockID, 6, 0), new Object[]{
			"xxx",Character.valueOf('x'), new ItemStack(blockLimestone.blockID, 1, 0)
		});
		GameRegistry.addRecipe(new ItemStack(blockLimestoneSlab.blockID, 6, 1), new Object[]{
			"xxx",Character.valueOf('x'), new ItemStack(blockLimestone.blockID, 1, 1)
		});
		GameRegistry.addRecipe(new ItemStack(blockLimestoneSlab.blockID, 4, 1), new Object[]{
			"xxx",Character.valueOf('x'), new ItemStack(blockLimestone.blockID, 1, 0)
		});
		GameRegistry.addRecipe(new ItemStack(blockLimestone.blockID, 1, 2), new Object[]{
			"x","x",Character.valueOf('x'), new ItemStack(blockLimestoneSlab.blockID, 1, 0)
		});
		GameRegistry.addRecipe(new ItemStack(blockLimestone.blockID, 1, 3), new Object[]{
			"x","x",Character.valueOf('x'), new ItemStack(blockLimestoneSlab.blockID, 1, 1)
		});
		GameRegistry.addRecipe(new ItemStack(blockLimestone.blockID, 4, 3), new Object[]{
			"xx","xx",Character.valueOf('x'), new ItemStack(blockLimestone, 1, 1)
		});
		GameRegistry.addRecipe(new ItemStack(blockLimestoneStairs.blockID, 4, 1), new Object[]{
			"x  ","xx ","xxx",Character.valueOf('x'), new ItemStack(blockLimestone.blockID, 1, 0)
		});
		GameRegistry.addRecipe(new ItemStack(blockLimestoneStairsBrick.blockID, 4, 1), new Object[]{
			"x  ","xx ","xxx",Character.valueOf('x'), new ItemStack(blockLimestone.blockID, 1, 1)
		});
		for(int i1 = 0; i1 < 4; i1++){
			for(int i2 = 0; i2 < 4; i2++){
				for(int i3 = 0; i3 < 4; i3++){
					for(int i4 = 0; i4 < 4; i4++){
						GameRegistry.addRecipe(new ItemStack(blockLimestone.blockID, 1, 0), new Object[]{
							"ab","cd",
							Character.valueOf('a'), new ItemStack(blockCoral, 1, i1),
							Character.valueOf('b'), new ItemStack(blockCoral, 1, i2),
							Character.valueOf('c'), new ItemStack(blockCoral, 1, i3),
							Character.valueOf('d'), new ItemStack(blockCoral, 1, i4)
						});
						for(int i5 = 0; i5 < 4; i5++){
							for(int i6 = 0; i6 < 4; i6++){
								for(int i7 = 0; i7 < 4; i7++){
									for(int i8 = 0; i8 < 4; i8++){
										for(int i9 = 0; i9 < 4; i9++){
											GameRegistry.addRecipe(new ItemStack(blockLimestone.blockID, 1, 0), new Object[]{
												"abc","def","ghi",
												Character.valueOf('a'), new ItemStack(blockCoral, 1, i1+4),
												Character.valueOf('b'), new ItemStack(blockCoral, 1, i2+4),
												Character.valueOf('c'), new ItemStack(blockCoral, 1, i3+4),
												Character.valueOf('d'), new ItemStack(blockCoral, 1, i4+4),
												Character.valueOf('e'), new ItemStack(blockCoral, 1, i5+4),
												Character.valueOf('f'), new ItemStack(blockCoral, 1, i6+4),
												Character.valueOf('g'), new ItemStack(blockCoral, 1, i7+4),
												Character.valueOf('h'), new ItemStack(blockCoral, 1, i8+4),
												Character.valueOf('i'), new ItemStack(blockCoral, 1, i9+4)
											});
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		
		GameRegistry.addShapelessRecipe(potionWaterBreathLong.copy(), new Object[]{potionWaterBreath.copy(),new ItemStack(Item.redstone)});
		GameRegistry.addShapelessRecipe(potionWaterBreathSplash.copy(), new Object[]{potionWaterBreath.copy(),new ItemStack(Item.gunpowder)});
		
		GameRegistry.addShapelessRecipe(new ItemStack(itemSquidFried), new Object[]{
			new ItemStack(itemKnife, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(itemSquidCooked), new ItemStack(Block.mushroomBrown), new ItemStack(Item.bowlEmpty)});
		GameRegistry.addShapelessRecipe(new ItemStack(itemLobsterRaw,3), new Object[]{new ItemStack(itemLobster),new ItemStack(itemKnife,1,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(itemLobsterCooked,3), new Object[]{new ItemStack(itemLobsterBoiled),new ItemStack(itemKnife,1,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(itemSushiRaw,1), new Object[]{new ItemStack(itemSeaweedCooked),new ItemStack(Item.fishRaw),new ItemStack(itemKnife,1,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(itemSushiCooked,1), new Object[]{new ItemStack(itemSeaweedCooked),new ItemStack(Item.fishCooked),new ItemStack(itemKnife,1,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(potionWaterBreath.copy(), new Object[]{new ItemStack(Item.potion, 1, 16),new ItemStack(itemPlantBreath),new ItemStack(itemKnife,1,OreDictionary.WILDCARD_VALUE)});
		
		GameRegistry.addShapelessRecipe(new ItemStack(itemSquidFried), new Object[]{
			new ItemStack(itemKnifeGold, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(itemSquidCooked), new ItemStack(Block.mushroomBrown), new ItemStack(Item.bowlEmpty)});
		GameRegistry.addShapelessRecipe(new ItemStack(itemLobsterRaw,4), new Object[]{new ItemStack(itemLobster),new ItemStack(itemKnifeGold,1,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(itemLobsterCooked,4), new Object[]{new ItemStack(itemLobsterBoiled),new ItemStack(itemKnifeGold,1,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(itemSushiRaw,1), new Object[]{new ItemStack(itemSeaweedCooked),new ItemStack(Item.fishRaw),new ItemStack(itemKnifeGold,1,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(itemSushiCooked,1), new Object[]{new ItemStack(itemSeaweedCooked),new ItemStack(Item.fishCooked),new ItemStack(itemKnifeGold,1,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(potionWaterBreath.copy(), new Object[]{new ItemStack(Item.potion, 1, 16),new ItemStack(itemPlantBreath),new ItemStack(itemKnifeGold,1,OreDictionary.WILDCARD_VALUE)});
		
		GameRegistry.addShapelessRecipe(new ItemStack(itemNeopreneTextile,1), new Object[]{new ItemStack(Block.cloth, 1, OreDictionary.WILDCARD_VALUE),new ItemStack(itemRubberBall)});
		GameRegistry.addShapelessRecipe(new ItemStack(itemFlask, 1, 3), new Object[]{
			new ItemStack(itemFlask, 1, 2), new ItemStack(this.itemRedironElectrolyzer, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(itemRubberRaw, 2), new Object[]{
			new ItemStack(itemFlask, 1, 3), new ItemStack(itemGuayuleBranch), new ItemStack(itemGuayuleBranch)});
		
		GameRegistry.addShapedRecipe(new ItemStack(itemFlask), new Object[]{
			" x ","x x", Character.valueOf('x'), new ItemStack(Block.glass)
		});
		
		GameRegistry.addSmelting(blockAlgae.blockID, new ItemStack(itemAlgaeCooked), 0.2F);
		GameRegistry.addSmelting(blockSeaweed.blockID, new ItemStack(itemSeaweedCooked), 0.25F);
		GameRegistry.addSmelting(itemSquidRaw.itemID, new ItemStack(itemSquidCooked), 0.5F);
		GameRegistry.addSmelting(itemLobsterRaw.itemID, new ItemStack(itemLobsterCooked), 0.2F);
		GameRegistry.addSmelting(itemLobster.itemID, new ItemStack(itemLobsterBoiled), 0.6F);
		GameRegistry.addSmelting(itemRubberRaw.itemID, new ItemStack(itemRubberBall), 0.3F);
		GameRegistry.addSmelting(this.blockLimestone.blockID, new ItemStack(itemQuickLime), 0.3F);
		
		//DONE Implement IWorldGenerator and register this class.
		///*Then delete or comment this line.*/GameRegistry.registerWorldGenerator(new ModLoaderWorldGenerator(this));
		GameRegistry.registerWorldGenerator(this);
		GameRegistry.registerCraftingHandler(this);
		
		//BiomeGenBase.ocean.setMinMaxHeight(-1.8F, 0.4F);
		/*int available = 23;
		for(int k = 0; k < BiomeGenBase.biomeList.length; k++){
			if(BiomeGenBase.biomeList[k] == null){
				available = k;
				break;
			}
			if(k == BiomeGenBase.biomeList.length-1){available = 23;}
		}*/
		if(this.generateDeepOcean){
		deepOcean = (new BiomeGenOceanDeep(deepOceanBiomeID)).setColor(112).setBiomeName("Deep Ocean").setMinMaxHeight(-1.9F, 0.0F);//1440 1567
		BiomeDictionary.registerBiomeType(deepOcean, BiomeDictionary.Type.WATER);
		GameRegistry.addBiome(deepOcean);
		}
		else{deepOcean = null;}
		
		BiomeGenBase[] waters;
		if(deepOcean != null){
		waters = new BiomeGenBase[]{BiomeGenBase.ocean, BiomeGenBase.beach, BiomeGenBase.frozenOcean, BiomeGenBase.river, BiomeGenBase.frozenRiver, deepOcean};
		}
		else{
		waters = new BiomeGenBase[]{BiomeGenBase.ocean, BiomeGenBase.beach, BiomeGenBase.frozenOcean, BiomeGenBase.river, BiomeGenBase.frozenRiver};
		}
		//EntityRegistry.removeSpawn(EntitySquid.class, EnumCreatureType.waterCreature);
		//EntityRegistry.removeSpawn(EntitySquid.class, EnumCreatureType.waterCreature, waters);
		EntityRegistry.registerGlobalEntityID(EntitySquidMeaty.class, "meatySquid", entityMeatySquidID, 2243406, 7375002);
		
		//ModLoader.addEntityTracker(this, EntitySquidMeaty.class, 127, 56, 5, true);
		
		//EntityRegistry.addSpawn(EntitySquidMeaty.class, 10, 4, 4, EnumCreatureType.waterCreature);
		//EntityRegistry.addSpawn(EntitySquidMeaty.class, 10, 4, 4, EnumCreatureType.waterCreature, waters);
		//DONE Replace all ModLoader.addLocalization references with following sntax.
		LanguageRegistry.instance().addStringLocalization("entity.meatySquid.name", "AlgaeCraft Squid");
		
		
		EntityRegistry.registerGlobalEntityID(EntityLobster.class, "lobster", entityLobsterID, 0xCC2C18, 0x0C0101);
		
		//ModLoader.addEntityTracker(this, EntityLobster.class, 126, 56, 5, true);
		
		EntityRegistry.registerGlobalEntityID(EntityFish.class, "fishyfish", entityFishID, 0xCCCCFF, 0x8C8C8F);
		
		EntityRegistry.addSpawn(EntityFish.class, 30, 8, 16, EnumCreatureType.waterCreature, new BiomeGenBase[]{
			//BiomeGenBase.ocean,
			BiomeGenBase.beach,
			BiomeGenBase.frozenOcean,
			BiomeGenBase.swampland,
			BiomeGenBase.river,
			BiomeGenBase.mushroomIslandShore});
		
		
		//ModLoader.addEntityTracker(this, EntityFish.class, 125, 56, 5, true);
		
		//EntityRegistry.addSpawn(EntityLobster.class, 10, 4, 4, EnumCreatureType.waterCreature);
		//EntityRegistry.addSpawn(EntityLobster.class, 10, 4, 4, EnumCreatureType.waterCreature, waters);
		EntityRegistry.addSpawn(EntityLobster.class, 10, 4, 10, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.ocean,BiomeGenBase.beach});
		LanguageRegistry.instance().addStringLocalization("entity.lobster.name", "Lobster");
		LanguageRegistry.instance().addStringLocalization("entity.fishyfish.name", "Fish");
		
		Block.waterMoving.setLightOpacity(waterLightDecrement);
		Block.waterStill.setLightOpacity(waterLightDecrement);
		
		TickRegistry.registerTickHandler(new PlayerTickHandler(EnumSet.of(TickType.PLAYER), event.getSide()), Side.SERVER);
		//if(event.getSide() == Side.CLIENT){
			TickRegistry.registerTickHandler(new PlayerTickHandler(EnumSet.of(TickType.PLAYER), event.getSide()), Side.CLIENT);
		//}
		
		itemScubaGoggles = new ItemHelmSight(itemScubaGogglesID, materialThin, 0,"scubaItemMask","scubaEssentials").setUnlocalizedName("scubaGoggles");
		LanguageRegistry.addName(itemScubaGoggles, "Dive Mask");
		
		GameRegistry.addRecipe(new ItemStack(itemScubaGoggles), new Object[]{
			"n n", "rgr", Character.valueOf('n'), new ItemStack(itemNeopreneTextile), Character.valueOf('r'), new ItemStack(itemRubberBall), Character.valueOf('g'), new ItemStack(Block.thinGlass)});
		
		itemScubaBCD = new ItemBCD(itemBcdID, materialThin, 0, "itemBCD", "scubaBcdArmor").setUnlocalizedName("scubaBCD");
		LanguageRegistry.addName(itemScubaBCD, "Buoyancy Control Device");
		//avg air tank could hold 340 avg breaths
		itemAirTankSmall = new ItemAirTank(itemAirtankSmallID, 200, "itemAirtank").setUnlocalizedName("itemAirTankSmall").setCreativeTab(modTab);
		LanguageRegistry.addName(itemAirTankSmall, "Air Tank");
		
		itemWetsuit = new ItemWetsuit(itemWetsuitID, materialThin, 0);
		LanguageRegistry.addName(itemWetsuit, "Neoprene Wetsuit");
		for(int i = 0; i < 16; i ++){
			for(int damage = 0; damage < itemWetsuit.getMaxDamage(); damage++){
				ItemStack stack = new ItemStack(itemWetsuit,1,damage);
				((ItemWetsuit) itemWetsuit).colorize(stack,i);
				GameRegistry.addShapelessRecipe(stack, new Object[]{new ItemStack(itemWetsuit, 1, damage),new ItemStack(Item.dyePowder, 1, i)});
			}
		}
		
		GameRegistry.addRecipe(new ItemStack(itemWetsuit), new Object[]{
			"xxx", "xxx", "x x", Character.valueOf('x'), new ItemStack(itemNeopreneTextile)});
		GameRegistry.addRecipe(new ItemStack(itemScubaBCD), new Object[]{
			"nrn", "rbr", "iri", Character.valueOf('n'), new ItemStack(itemNeopreneTextile), Character.valueOf('r'), new ItemStack(itemRubberBall), Character.valueOf('b'), new ItemStack(Item.bucketEmpty), Character.valueOf('i'), new ItemStack(Item.ingotIron)});
		
		itemFlippers= new ItemFlippers(itemFlippersID, materialThin, 0, "flippersItem", "flippersArmor").setUnlocalizedName("scubaFlippers");
		LanguageRegistry.addName(itemFlippers, "Flippers");
		
		GameRegistry.addShapedRecipe(new ItemStack(blockAirCompressor), new Object[]{
			"chc","ptp","iri", Character.valueOf('c'), new ItemStack(itemChipRediron),
			Character.valueOf('h'),new ItemStack(Block.hopperBlock),
			Character.valueOf('p'),new ItemStack(Block.pistonBase),
			Character.valueOf('t'),new ItemStack(this.itemAirTankSmall,1,OreDictionary.WILDCARD_VALUE),
			Character.valueOf('i'),new ItemStack(Block.pressurePlateIron),
			Character.valueOf('r'),new ItemStack(Item.redstoneRepeater)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(itemAirTankSmall,1,itemAirTankSmall.getMaxDamage()), new Object[]{
			" r ","i i","iii", Character.valueOf('i'), new ItemStack(Item.ingotIron),
			Character.valueOf('r'),new ItemStack(itemRubberBall)
		});
		
		GameRegistry.addRecipe(new ItemStack(itemFlippers), new Object[]{
			"n n", "r r", Character.valueOf('n'), new ItemStack(itemNeopreneTextile), Character.valueOf('r'), new ItemStack(itemRubberBall)});
		
		GameRegistry.addRecipe(new ItemStack(itemRedironElectrolyzer), new Object[]{
			" l ", "gcg", "g g", Character.valueOf('g'), new ItemStack(Item.goldNugget), Character.valueOf('l'), new ItemStack(Block.lever), Character.valueOf('c'), new ItemStack(itemChipRediron)});
		
		
		OreDictionary.registerOre("itemRubber", new ItemStack(itemRubberBall));
		OreDictionary.registerOre("materialRubber", new ItemStack(itemRubberBall));
		OreDictionary.registerOre("ingotRubber", new ItemStack(itemRubberBall));
		OreDictionary.registerOre("itemFlaskOfSodiumHydroxide", new ItemStack(itemFlask, 1, 3));
		OreDictionary.registerOre("blockLimestone", new ItemStack(blockLimestone));
		OreDictionary.registerOre("stickWood", new ItemStack(itemGuayuleBranch));
		OreDictionary.registerOre("materialNeoprene", new ItemStack(itemNeopreneTextile));
		OreDictionary.registerOre("foodCalamari", new ItemStack(itemSquidCooked));
		
		/////////////////////////////////////////////////
		NetworkRegistry.instance().registerChannel(new ACPacketHandler(), "ALGAECRAFT");
		/////////////////////////////////////////////////
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		oceanAC  = new BiomeGenOceanAC(0).setColor(112).setBiomeName("Ocean").setMinMaxHeight(-1.0F, 0.4F);
		
		EntityRegistry.addSpawn(EntityFish.class, 50, 8, 16, ambientWater, new BiomeGenBase[]{
			oceanAC});
		//EntityRegistry.addSpawn(EntitySquidMeaty.class, 10, 4, 10, EnumCreatureType.waterCreature);

		if(this.generateDeepOcean){
			EntityRegistry.addSpawn(EntityFish.class, 30, 8, 16, ambientWater, new BiomeGenBase[]{
				this.deepOcean});
		}
		
		((BiomeGenOceanAC) oceanAC).realignSpawnLists();
	}
	
	public static Item itemSponge;
	public static Item itemAlgaeCooked;
	public static Item itemSeaweedCooked;
	public static Item itemSquidRaw;
	public static Item itemSquidCooked;
	public static Item itemSquidFried;
	public static Item itemKnife;
	public static Item itemLobster;
	public static Item itemLobsterBoiled;
	public static Item itemLobsterRaw;
	public static Item itemLobsterCooked;
	public static Item itemSushiRaw;
	public static Item itemSushiCooked;
	public static Item itemPlantBreath;
	public static Item itemChipRediron;
	public static Item itemKnifeGold;
	
	public static Item itemQuickLime;
	
	public static Item itemSpongeRed;
	
	public static Item itemScubaGoggles;
	public static Item itemScubaBCD;
	public static Item itemAirTankSmall;
	public static Item itemWetsuit;
	public static Item itemFlippers;
	
	public static Item itemRubberRaw;
	public static Item itemRubberBall;
	public static Item itemGuayuleBranch;
	public static Item itemFlask;
	public static Item itemRedironElectrolyzer;
	public static Item itemNeopreneTextile;
	
	public static Block blockAlgae;
	public static Block blockSponge;
	public static Block blockSeaweed;
	public static Block blockCoral;
	public static Block blockLobsterCage;
	public static Block blockPlantBreath;
	public static Block blockFilter;
	public static Block blockSediment;
	public static Block blockLimestone;
	public static Block blockLimestoneStairs;
	public static Block blockLimestoneStairsBrick;
	public static BlockHalfSlab blockLimestoneSlab;
	public static BlockHalfSlab blockLimestoneSlabDouble;
	
	public static Block blockSpongeRed;
	public static Block blockSpongeRedSpore;
	
	public static Block blockGuayule;
	public static Block blockAirCompressor;
	
	public static int algaeModelID;
	public static int spongeModelID;
	public static int coralModelID;
	public static int seaweedModelID;
	
	public static final EnumArmorMaterial materialThin = EnumHelper.addArmorMaterial("thin", 4, new int[]{1,2,1,1}, 10);
	public static final EnumArmorMaterial materialDryIron = EnumHelper.addArmorMaterial("dryIron", 14, new int[]{2,5,4,2}, 9);
	public static final EnumArmorMaterial materialDryGold = EnumHelper.addArmorMaterial("dryGold", 6, new int[]{2,4,2,1}, 20);
	public static final EnumArmorMaterial materialDryDiamond = EnumHelper.addArmorMaterial("dryDiamond", 30, new int[]{3,7,5,3}, 12);
	
	//DONE Test World Gen
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        if (chunkGenerator instanceof ChunkProviderGenerate)
        {
            this.generateSurface(world, random, chunkX << 4, chunkZ << 4);
        }
        else if (chunkGenerator instanceof ChunkProviderHell)
        {
            this.generateNether(world, random, chunkX << 4, chunkZ << 4);
        }
    }
	
	private void generateNether(World world, Random random, int i, int j) {
		
	}
	public void generateSurface(World var1, Random var2, int var3, int var4)
	{
		if(var1.getBiomeGenForCoords(var3, var4) == BiomeGenBase.desert){
			if(var2.nextInt(4)==0){
			//for(int n = 0; n < 6; n++){
				int x = var3+var2.nextInt(15);
				int y = 63+var2.nextInt(11);
				int z = var4+var2.nextInt(15);
				(new WorldGenFlowers(blockGuayule.blockID, 2)).generate(var1, var2, x, y, z);
			//}
			}
		}
		
		if(var1.getBiomeGenForCoords(var3, var4) == BiomeGenBase.desertHills){
			if(var2.nextInt(3)==0){
				int x = var3+var2.nextInt(15);
				int y = 63+var2.nextInt(21);
				int z = var4+var2.nextInt(15);
				(new WorldGenFlowers(blockGuayule.blockID, 2)).generate(var1, var2, x, y, z);
			}
		}
		
		for(int c = 6; c>0; c--)
		{
			int x = var3+var2.nextInt(15);
			int y = 46+var2.nextInt(12);
			int z = var4+var2.nextInt(15);
			if(this.blockSponge.canPlaceBlockAt(var1, x, y, z)){
				var1.setBlock(x, y, z, Block.sponge.blockID, 0, 2);
			}
		}
		for(int c = 6; c>0; c--)
		{
			int x = var3+var2.nextInt(15);
			int y = 48+var2.nextInt(12);
			int z = var4+var2.nextInt(15);
			if(this.blockSponge.canPlaceBlockAt(var1, x, y, z)){
				var1.setBlock(x, y, z, this.blockSponge.blockID, var2.nextInt(3), 2);
			}
		}
		for(int c = 3; c>0; c--)
		{
			int x = var3+var2.nextInt(15);
			int y = 46+var2.nextInt(12);
			int z = var4+var2.nextInt(15);
			if(this.blockSpongeRedSpore.canPlaceBlockAt(var1, x, y, z)){
				var1.setBlock(x, y, z, blockSpongeRed.blockID, 0, 2);
			}
		}
		for(int c = 3; c>0; c--)
		{
			int x = var3+var2.nextInt(15);
			int y = 48+var2.nextInt(12);
			int z = var4+var2.nextInt(15);
			if(this.blockSpongeRedSpore.canPlaceBlockAt(var1, x, y, z)){
				var1.setBlock(x, y, z, this.blockSpongeRedSpore.blockID, var2.nextInt(3), 2);
			}
		}
		boolean isSwamp = false;
		if(biomeIDSwampList.length > 1){
			for(int n = 0; n<biomeIDSwampList.length; n++){
				if(biomeIDSwampList[n]==var1.getBiomeGenForCoords(var3, var4).biomeID || BiomeGenBase.swampland.biomeID==var1.getBiomeGenForCoords(var3, var4).biomeID){
					isSwamp=true;
				}
			}
		}
		else{
			BiomeGenBase[] swamps = BiomeDictionary.getBiomesForType(BiomeDictionary.Type.SWAMP);
			for(int n=0;n<swamps.length;n++){
				if(swamps[n].biomeID==var1.getBiomeGenForCoords(var3, var4).biomeID || BiomeGenBase.swampland.biomeID==var1.getBiomeGenForCoords(var3, var4).biomeID){
					isSwamp=true;
				}
			}
		}
		
		for(int c = 6; c>0 && isSwamp; c--)
		{
			int x = var3+var2.nextInt(15);
			int y = 63+var2.nextInt(2);
			int z = var4+var2.nextInt(15);
			if(blockAlgae.canBlockStay(var1, x, y, z)){
				var1.setBlock(x, y, z, blockAlgae.blockID, 0, 2);
			}
			if(blockAlgae.canBlockStay(var1, x+1, y, z)){
				var1.setBlock(x+1, y, z, blockAlgae.blockID, 0, 2);
			}
			if(blockAlgae.canBlockStay(var1, x-1, y, z)){
				var1.setBlock(x-1, y, z, blockAlgae.blockID, 0, 2);
			}
			if(blockAlgae.canBlockStay(var1, x, y, z+1)){
				var1.setBlock(x, y, z+1, blockAlgae.blockID, 0, 2);
			}
			if(blockAlgae.canBlockStay(var1, x, y, z-1)){
				var1.setBlock(x, y, z-1, blockAlgae.blockID, 0, 2);
			}
		}
		
		boolean isOcean = false;
		List seaList = new ArrayList();
		seaList.add(BiomeGenBase.frozenOcean);
		seaList.add(BiomeGenBase.ocean);
		
		
		for(int i = 0;i<seaList.size();i++){
			if (seaList.get(i)==var1.getBiomeGenForCoords(var3, var4)){
				isOcean=true;
			}
		}
		
		boolean isOceanID = false;
		for(int i = 0;i<biomeIDOceanList.length;i++){
			if (biomeIDOceanList[i]==var1.getBiomeGenForCoords(var3, var4).biomeID){
				isOceanID=true;
			}
		}
		
		for(int c = 3; c>0 && isOceanID; c--)
		{
			int x = var3+var2.nextInt(15);
			int y = 46+var2.nextInt(10);
			int z = var4+var2.nextInt(15);
			if(isOceanID){
				Random rand = new Random();
				int type = reefType[rand.nextInt(reefType.length)];//listofreeftypes
				WorldGenReef gen = new WorldGenReef(new ItemStack[]{new ItemStack(blockSeaweed)},type);
				gen.generate(var1, var2, x, y, z);
			}
		}
		
		for(int c = 4; c>0 && isOceanID; c--)
		{
			int x = var3+var2.nextInt(15);
			int y = 46+var2.nextInt(10);
			int z = var4+var2.nextInt(15);
			if(isOceanID){
				Random rand = new Random();
				int type = reefType[rand.nextInt(reefType.length)];//listofreeftypes
				WorldGenReef gen = new WorldGenReef(new ItemStack[]{
						new ItemStack(blockCoral.blockID,0,0),
						new ItemStack(blockCoral.blockID,0,1),
						new ItemStack(blockCoral.blockID,0,2),
						new ItemStack(blockCoral.blockID,0,3),
						new ItemStack(blockCoral.blockID,0,0),
						new ItemStack(blockCoral.blockID,0,1),
						new ItemStack(blockCoral.blockID,0,2),
						new ItemStack(blockCoral.blockID,0,3),
						new ItemStack(blockCoral.blockID,0,4),
						new ItemStack(blockCoral.blockID,0,5),
						new ItemStack(blockCoral.blockID,0,6),
						new ItemStack(blockCoral.blockID,0,7),
						new ItemStack(blockSponge.blockID,0,0),
						new ItemStack(blockSpongeRedSpore,0,1)},
						type);
				gen.generate(var1, var2, x, y, z);
			}
		}
		
		if(deepOcean != null){
		for(int c = 1; c>0 && var1.getBiomeGenForCoords(var3, var4) == this.deepOcean; c--)
		{
			int x = var3+var2.nextInt(15);
			int y = 32+var2.nextInt(4);
			int z = var4+var2.nextInt(15);
			if(var1.getBiomeGenForCoords(var3, var4) == this.deepOcean && var2.nextInt(10) == 2){
				WorldGenFlowers gen = new WorldGenFlowers(this.blockPlantBreath.blockID, 0);
				gen.generate(var1, var2, x, y, z);
				gen = new WorldGenFlowers(this.blockPlantBreath.blockID, 1);
				gen.generate(var1, var2, x, y, z);
			}
		}
		}
	}
	
	public String getVersion(){
		return "1.4.3";
	}

	public void load() {
		
	}
	
	@SideOnly(Side.CLIENT)
	public boolean renderWorldBlock(RenderBlocks renderer, IBlockAccess world, int x, int y, int z, Block block, int modelID)
	{
		return ((ClientProxy) proxy).renderWorldBlock(renderer, world, x, y, z, block, modelID);
	}

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) {
		
		for(int i = 0; i < craftMatrix.getSizeInventory(); i++){
			ItemStack x = craftMatrix.getStackInSlot(i);
			if(x != null && x.getItem() == this.itemFlask && x.getItemDamage() == 3){
				if(item.itemID == this.itemRubberRaw.itemID){
					
					ItemStack newNaOH = new ItemStack(this.itemFlask, 1, 3);
					if(player.inventory.hasItemStack(newNaOH)){
						craftMatrix.setInventorySlotContents(i, new ItemStack(this.itemFlask, 1, 4));
						boolean flag = false;
						for(int c = 0;!flag && c < player.inventory.mainInventory.length; c++){
							if(player.inventory.getStackInSlot(c)!=null && player.inventory.getStackInSlot(c).itemID == newNaOH.itemID && player.inventory.getStackInSlot(c).getItemDamage() == newNaOH.getItemDamage()){
								player.inventory.mainInventory[c]=null;
								flag = true;
							}
						}
						x.setItemDamage(0);
						if(x.stackSize>=1){
							player.inventory.addItemStackToInventory(x);
						}
					}
				}
			}
			if(x != null && x.getItem() == this.itemFlask && x.getItemDamage() == 2){
				if(item.itemID == this.itemFlask.itemID && item.getItemDamage() == 3){
					ItemStack newSalt = new ItemStack(this.itemFlask, 1, 2);
					if(player.inventory.hasItemStack(newSalt)){
						craftMatrix.setInventorySlotContents(i, new ItemStack(this.itemFlask, 1, 5));
						boolean flag = false;
						for(int c = 0;!flag && c < player.inventory.mainInventory.length; c++){
							if(player.inventory.getStackInSlot(c)!=null && player.inventory.getStackInSlot(c).itemID == newSalt.itemID && player.inventory.getStackInSlot(c).getItemDamage() == newSalt.getItemDamage()){
								player.inventory.mainInventory[c]=null;
								flag = true;
							}
						}/*
						x.setItemDamage(0);
						if(x.stackSize>=1){
							player.inventory.addItemStackToInventory(x);
						}*/
					}
					else{
						craftMatrix.setInventorySlotContents(i, null);
					}
				}
			}/*
			if(x != null && x.getItem() == itemKnife){
				if(item.getItem() != x.getItem()){
				x.setItemDamage(x.getItemDamage()+1);
				if(x.stackSize==1 && x.getItemDamage() < itemKnife.getMaxDamage()){
					
						player.inventory.addItemStackToInventory(x);
					}
				}
			}
			if(x != null && x.getItem() == itemKnifeGold){
				if(item.getItem() != x.getItem()){
				x.setItemDamage(x.getItemDamage()+1);
				if(x.stackSize==1 && x.getItemDamage() < itemKnifeGold.getMaxDamage()){
					
						player.inventory.addItemStackToInventory(x);
					}
				}
			}*/
			/*
			if(x != null && x.getItem() == itemRedironElectrolyzer){
				if(item.getItem() != x.getItem()){
				//x.setItemDamage(x.getItemDamage()+1);
				if(x.stackSize==1 && x.getItemDamage() < itemRedironElectrolyzer.getMaxDamage()){
					
						//player.inventory.addItemStackToInventory(x);
					}
				}
			}*/
		}
		//if (player.worldObj.isRemote){return;}
		/*
		Random rand = new Random();
		for(int i = 0; i < craftMatrix.getSizeInventory(); i++){
			ItemStack x = craftMatrix.getStackInSlot(i);
			int ench = 0;
			if(x != null){
				ench = ench - getEnchantmentLevel(Enchantment.unbreaking.effectId, x);
			}
			if(x != null && x.getItem() == itemKnife){
				if(rand.nextInt(4) >= ench){
					x.damageItem((x.getItemDamage() + 1), player);
				}
				if(item.getItem() != x.getItem()){
				player.inventory.addItemStackToInventory(x);
				}
			}
		}*/
		
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
		
	}
	
	public static int getEnchantmentLevel(int par0, ItemStack par1ItemStack)
    {
        if (par1ItemStack == null)
        {
            return 0;
        }
        else
        {
            NBTTagList nbttaglist = par1ItemStack.getEnchantmentTagList();

            if (nbttaglist == null)
            {
                return 0;
            }
            else
            {
                for (int j = 0; j < nbttaglist.tagCount(); ++j)
                {
                    short short1 = ((NBTTagCompound)nbttaglist.tagAt(j)).getShort("id");
                    short short2 = ((NBTTagCompound)nbttaglist.tagAt(j)).getShort("lvl");

                    if (short1 == par0)
                    {
                        return short2;
                    }
                }

                return 0;
            }
        }
    }
}
