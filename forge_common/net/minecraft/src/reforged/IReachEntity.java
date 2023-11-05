/**
 * This software is provided under the terms of the Minecraft Forge Public
 * License v1.1.
 */
package net.minecraft.src.reforged;

import net.minecraft.src.ItemStack;

public interface IReachEntity
{

    public abstract boolean reachEntityItemMatches(ItemStack itemstack);

    public abstract float getReachEntity(ItemStack itemstack);
}
