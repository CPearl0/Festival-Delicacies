package cn.foggyhillside.festival_delicacies.tag;

import cn.foggyhillside.festival_delicacies.FestivalDelicacies;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Block> IS_POT = modBlockTag("is_pot");

    public ModTags() {
    }

    private static TagKey<Block> modBlockTag(String path) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(FestivalDelicacies.MODID + ":" + path));
    }
}
