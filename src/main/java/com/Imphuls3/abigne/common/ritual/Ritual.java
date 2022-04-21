package com.Imphuls3.abigne.common.ritual;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class Ritual {
    ResourceLocation name = null;
    /*List<IRequirement> stepRequirements = new ArrayList<>(), continuousRequirements = new ArrayList<>();
*/
    public Ritual() {

    }

    public Ritual setName(String domain, String path) {
        this.name = new ResourceLocation(domain, path);
        return this;
    }

    public Ritual setName(ResourceLocation name) {
        this.name = name;
        return this;
    }

    public ResourceLocation getName() {
        return this.name;
    }

    /*public Ritual addRequirement(IRequirement requirement) {
        stepRequirements.add(requirement);
        return this;
    }

    public Ritual addInvariant(IRequirement requirement) {
        continuousRequirements.add(requirement);
        return this;
    }

    public List<IRequirement> getRequirements() {
        return stepRequirements;
    }*/

    public enum SetupResult {
        FAIL,
        PASS,
        SUCCEED
    }


    //creates two results for rituals, either pass or end
    public enum RitualResult {
        PASS,
        END
    }

    /*public SetupResult setup(Level world, BlockPos pos, int step) {
        if (step >= stepRequirements.size()) return SetupResult.SUCCEED;
        for (IRequirement req : continuousRequirements) {
            Requirement info = req.isMet(this, world, pos);
            if (!info.isMet()) return SetupResult.FAIL;
            else req.whenMet(this, world, pos, info);
        }
        IRequirement req = stepRequirements.get(step);
        Requirement info = req.isMet(this, world, pos);
        if (!info.isMet()) return SetupResult.FAIL;
        else req.whenMet(this, world, pos, info);
        return SetupResult.PASS;
    }*/

    public RitualResult tick(Level world, BlockPos pos) {
        return RitualResult.PASS;
    }

    public RitualResult start(Level world, BlockPos pos) {
        return RitualResult.PASS;
    }

    public AABB getSearchBounds(BlockPos pos) {
        return getDefaultBounds(pos);
    }

    //default bounds for block entities of a ritual
    public static AABB getDefaultBounds(BlockPos pos) {
        return new AABB(pos.getX() - 8, pos.getY() - 3, pos.getZ() - 8,
                pos.getX() + 8, pos.getY() + 11, pos.getZ() + 8);
    }

    //gets block entities within a certain bounding box
    public static <T> List<T> getBlockEntitiesWithinAABB(Class<T> type, Level world, AABB bounds) {
        List<T> blockEntityList = new ArrayList<>();
        for (int i = (int)Math.floor(bounds.minX); i < (int)Math.ceil(bounds.maxX) + 16; i += 16) {
            for (int k = (int)Math.floor(bounds.minZ); k < (int)Math.ceil(bounds.maxZ) + 16; k += 16) {
                ChunkAccess chunk = world.getChunk(new BlockPos(i, 0, k));
                Set<BlockPos> blockEntities = chunk.getBlockEntitiesPos();
                for (BlockPos pos : blockEntities) if (bounds.contains(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5)) {
                    BlockEntity t = world.getBlockEntity(pos);
                    if (type.isInstance(t)) blockEntityList.add((T)t);
                }
            }
        }
        return blockEntityList;
    }
}
