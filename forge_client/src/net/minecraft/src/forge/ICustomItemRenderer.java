/**
 * This software is provided under the terms of the Minecraft Forge Public 
 * License v1.1.
 */
package net.minecraft.src.forge;

import net.minecraft.src.RenderBlocks;

public interface ICustomItemRenderer {
	public void renderInventory (RenderBlocks renderblocks, int itemID, int meta);
}
