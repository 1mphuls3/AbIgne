package com.Imphuls3.abigne.core.systems.ritual;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

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


}
