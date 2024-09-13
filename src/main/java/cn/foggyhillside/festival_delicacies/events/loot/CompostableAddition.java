package cn.foggyhillside.festival_delicacies.events.loot;

import cn.foggyhillside.festival_delicacies.registry.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class CompostableAddition {

    public static void addCompostable(){
        add(0.3F, ModItems.JUJUBE.get());
    }

    private static void add(float f, ItemLike itemLike) {
        ComposterBlock.COMPOSTABLES.put(itemLike.asItem(), f);
    }

}