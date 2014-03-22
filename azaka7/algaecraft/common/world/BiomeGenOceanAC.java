package azaka7.algaecraft.common.world;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.EntityRegistry;
import azaka7.algaecraft.common.AlgaeCraftMain;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraft.world.biome.SpawnListEntry;

public class BiomeGenOceanAC extends BiomeGenBase
{
	
	public static List fishList;
	
    public BiomeGenOceanAC(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        //this.spawnableCaveCreatureList = BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.ambient);
        //this.spawnableWaterCreatureList = BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.waterCreature);
        //this.spawnableMonsterList = BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.monster);
        fishList = new ArrayList();
    }
    
    @Override
    public List getSpawnableList(EnumCreatureType type)
    {
    	if(type == AlgaeCraftMain.ambientWater){return this.fishList;}
    	return super.getSpawnableList(type);
    }
    
    public void realignSpawnLists(){
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableMonsterList.clear();
        
    	List spawnlist = BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.waterCreature);
    	for(int i = 0; i < spawnlist.size(); i++){
    		SpawnListEntry entry = (SpawnListEntry) spawnlist.get(i);
    		EntityRegistry.addSpawn(entry.entityClass, entry.itemWeight, entry.minGroupCount, entry.maxGroupCount, AlgaeCraftMain.ambientWater, this);
    	}
    	
    	spawnlist = BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.ambient);
    	for(int i = 0; i < spawnlist.size(); i++){
    		SpawnListEntry entry = (SpawnListEntry) spawnlist.get(i);
    		EntityRegistry.addSpawn(entry.entityClass, entry.itemWeight, entry.minGroupCount, entry.maxGroupCount, EnumCreatureType.ambient, this);
    	}
    	
    	spawnlist = BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.creature);
    	for(int i = 0; i < spawnlist.size(); i++){
    		SpawnListEntry entry = (SpawnListEntry) spawnlist.get(i);
    		EntityRegistry.addSpawn(entry.entityClass, entry.itemWeight, entry.minGroupCount, entry.maxGroupCount, EnumCreatureType.creature, this);
    	}
    	
    	spawnlist = BiomeGenBase.ocean.getSpawnableList(EnumCreatureType.monster);
    	for(int i = 0; i < spawnlist.size(); i++){
    		SpawnListEntry entry = (SpawnListEntry) spawnlist.get(i);
    		EntityRegistry.addSpawn(entry.entityClass, entry.itemWeight, entry.minGroupCount, entry.maxGroupCount, EnumCreatureType.monster, this);
    	}
    	
    }
}
