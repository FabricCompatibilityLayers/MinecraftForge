/**
 * This software is provided under the terms of the Minecraft Forge Public License v1.1.
 */

package net.minecraft.src.forge;

@Deprecated //Now consolidated into IITemRenderer
public enum ItemRenderType
{
    ENTITY, // Render type for in-world EntityItems
    EQUIPPED, // Render type for an item equipped in-hand
    INVENTORY; // Render type for items shown in a GUI inventory slot
}
