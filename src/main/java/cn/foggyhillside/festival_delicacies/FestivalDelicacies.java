package cn.foggyhillside.festival_delicacies;

import cn.foggyhillside.festival_delicacies.blocks.screen.StoveScreen;
import cn.foggyhillside.festival_delicacies.events.loot.CompostableAddition;
import cn.foggyhillside.festival_delicacies.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(FestivalDelicacies.MODID)
public class FestivalDelicacies
{
    public static final String MODID = "festival_delicacies";

    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> FestivalDelicaciesTab = CREATIVE_MODE_TABS.register("festival_delicacies", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.festival_delicacies"))
            .icon(() -> ModItems.SWEET_ZONGZI.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ModItems.STOVE.get());
                output.accept(ModItems.POT.get());
                output.accept(ModItems.UNCOOKED_SWEET_ZONGZI.get());
                output.accept(ModItems.UNCOOKED_MEAT_ZONGZI.get());
                output.accept(ModItems.SWEET_ZONGZI.get());
                output.accept(ModItems.MEAT_ZONGZI.get());
                output.accept(ModItems.QINGTUAN.get());
                output.accept(ModItems.RED_BEAN_PASTE.get());
                output.accept(ModItems.RED_BEAN.get());
                output.accept(ModItems.RICE.get());
                output.accept(ModItems.JUJUBE.get());
                output.accept(ModItems.PRESERVED_MEAT.get());
                output.accept(ModItems.BAMBOO_LEAF.get());
                output.accept(ModItems.ARTEMISIA_ARGYI.get());
                output.accept(ModItems.CHINESE_CABBAGE_SEEDS.get());
                output.accept(ModItems.CHINESE_CABBAGE.get());
                output.accept(ModItems.CHINESE_CABBAGE_LEAF.get());
                output.accept(ModItems.GARLIC.get());
                output.accept(ModItems.GREENONION.get());
                output.accept(ModItems.EGGPLANT.get());
                output.accept(ModItems.EGGPLANT_SEEDS.get());
                output.accept(ModItems.GARLIC_CHIVE.get());
                output.accept(ModItems.GARLIC_CHIVE_SEEDS.get());
                output.accept(ModItems.FENNEL.get());
                output.accept(ModItems.FENNEL_SEEDS.get());
                output.accept(ModItems.PORK_CABBAGE_BOILED_DUMPLING.get());
                output.accept(ModItems.PORK_KELP_BOILED_DUMPLING.get());
                output.accept(ModItems.PORK_POTATO_BOILED_DUMPLING.get());
                output.accept(ModItems.PORK_FENNEL_BOILED_DUMPLING.get());
                output.accept(ModItems.MUTTON_BOILED_DUMPLING.get());
                output.accept(ModItems.CHICKEN_MUSHROOM_BOILED_DUMPLING.get());
                output.accept(ModItems.COD_BOILED_DUMPLING.get());
                output.accept(ModItems.SALMON_BOILED_DUMPLING.get());
                output.accept(ModItems.EGGPLANT_EGG_BOILED_DUMPLING.get());
                output.accept(ModItems.MUSHROOM_BOILED_DUMPLING.get());
                output.accept(ModItems.FUNGUS_BOILED_DUMPLING.get());
                output.accept(ModItems.GARLIC_CHIVE_EGG_BOILED_DUMPLING.get());
                output.accept(ModItems.DANDELION_LEAF_BOILED_DUMPLING.get());
                output.accept(ModItems.PUFFERFISH_BOILED_DUMPLING.get());
                output.accept(ModItems.RABBIT_MEAT_BOILED_DUMPLING.get());
                output.accept(ModItems.PORK_CARROT_WONTON.get());
                output.accept(ModItems.PORK_MUSHROOM_WONTON.get());
                output.accept(ModItems.PORK_CABBAGE_WONTON.get());
            }).build());

    public FestivalDelicacies()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModMenuTypes.MENU_TYPES.register(modEventBus);
        ModRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
        ModRecipeTypes.RECIPE_TYPES.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvent{
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
            MenuScreens.register(ModMenuTypes.STOVE_MENU.get(), StoveScreen::new);
        }
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        CompostableAddition.addCompostable();
    }
}
