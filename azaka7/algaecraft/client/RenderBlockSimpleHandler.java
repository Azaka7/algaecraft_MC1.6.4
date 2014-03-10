package azaka7.algaecraft.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import azaka7.algaecraft.client.ClientProxy.EnumDefinedRenderer.*;
import azaka7.algaecraft.common.AlgaeCraftMain;

public class RenderBlockSimpleHandler implements ISimpleBlockRenderingHandler {
	
	private int renderID;
	private boolean render3DInventory;
	
	public RenderBlockSimpleHandler(int id, boolean inv3d, ClientProxy.EnumDefinedRenderer type){
		if(id < 0){
			id = RenderingRegistry.getNextAvailableRenderId();
		}
		renderID = id;
		render3DInventory = inv3d;
		switch(type){
		case ALGAE: AlgaeCraftMain.algaeModelID = id; break;
		case SPONGE: AlgaeCraftMain.spongeModelID = id; break;
		case SEAWEED: AlgaeCraftMain.seaweedModelID = id; break;
		case CORAL: AlgaeCraftMain.coralModelID = id; break;
		default: break;
		}
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return ClientProxy.instance.renderWorldBlock(renderer, world, x, y, z, block, modelId);
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return render3DInventory;
	}

	@Override
	public int getRenderId() {
		return renderID;
	}

}
