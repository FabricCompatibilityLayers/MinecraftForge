/**
 * This software is provided under the terms of the Minecraft Forge Public
 * License v1.1.
 */
package net.minecraft.src.reforged;

public class ReforgedHooks {

	public static void touch() {
		// touch :)
	}

	public static final int majorVersion=1;
	public static final int minorVersion=0;
	public static final int revisionVersion=0;
	static {
		System.out.printf("Reforged V%d.%d.%d Initialized\n",majorVersion,minorVersion,revisionVersion);
	}
}
