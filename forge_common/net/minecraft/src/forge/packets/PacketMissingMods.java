/**
 * This software is provided under the terms of the Minecraft Forge Public License v1.1.
 */

package net.minecraft.src.forge.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketMissingMods extends PacketModList
{

    public PacketMissingMods(boolean server)
    {
        super(!server);
    }

    @Override
    public int getID()
    {
        return ForgePacket.MOD_MISSING;
    }

}
