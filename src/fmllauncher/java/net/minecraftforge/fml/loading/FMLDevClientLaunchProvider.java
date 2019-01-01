/*
 * Minecraft Forge
 * Copyright (c) 2016-2018.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 2.1
 * of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package net.minecraftforge.fml.loading;

import cpw.mods.modlauncher.api.IEnvironment;
import cpw.mods.modlauncher.api.ILaunchHandlerService;
import cpw.mods.modlauncher.api.ITransformingClassLoader;
import net.minecraftforge.api.distmarker.Dist;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import static net.minecraftforge.fml.loading.LogMarkers.CORE;

public class FMLDevClientLaunchProvider extends FMLCommonLaunchHandler implements ILaunchHandlerService
{

    private static final Logger LOGGER = LogManager.getLogger();
    private Path compiledClasses;

    @Override
    public String name()
    {
        return "fmldevclient";
    }

    @Override
    public Path[] identifyTransformationTargets()
    {
        return LibraryFinder.commonLibPaths(new Path[] {FMLLoader.getForgePath()});
    }

    public Path getForgePath(final String mcVersion, final String forgeVersion) {
        // In forge dev, we just find the path for ForgeVersion for everything
        compiledClasses = LibraryFinder.findJarPathFor("net/minecraftforge/versions/forge/ForgeVersion.class", "forge");
        return compiledClasses;
    }

    public Path[] getMCPaths(final String mcVersion, final String forgeVersion) {
        // In forge dev, we just find the path for ForgeVersion for everything
        return new Path[] { compiledClasses, compiledClasses };
    }

    @Override
    public Callable<Void> launchService(String[] arguments, ITransformingClassLoader launchClassLoader)
    {
        return () -> {
            LOGGER.debug(CORE, "Launching minecraft in {} with arguments {}", launchClassLoader, arguments);
            super.beforeStart(launchClassLoader);
            launchClassLoader.addTargetPackageFilter(getPackagePredicate());
            Class.forName("net.minecraft.client.main.Main", true, launchClassLoader.getInstance()).getMethod("main", String[].class).invoke(null, (Object)arguments);
            return null;
        };
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setup(IEnvironment environment, final Map<String, ?> arguments)
    {
        LOGGER.debug(CORE, "No jar creation necessary. Launch is dev environment");
        // we're injecting forge into the exploded dir finder
        final Path forgemodstoml = LibraryFinder.findJarPathFor("META-INF/mods.toml", "forgemodstoml");
        ((Map<String, List<Pair<Path,Path>>>) arguments).computeIfAbsent("explodedTargets", a->new ArrayList<>()).
                add(Pair.of(compiledClasses, forgemodstoml));
    }

    @Override
    public Dist getDist()
    {
        return Dist.CLIENT;
    }
}