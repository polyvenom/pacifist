package com.tom.pacifist;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public final class LootInjections {

    private LootInjections() {}

    public static void register() {
        LootTableEvents.MODIFY.register((key, builder, source, lookup) -> {
            if (!source.isBuiltin()) return;

            if (key.equals(BuiltInLootTables.NETHER_BRIDGE)) {
                addSingleItemPool(builder, BuiltInLootTables.NETHER_BRIDGE, 0.05f, Items.WITHER_SKELETON_SKULL);
            } else if (key.equals(BuiltInLootTables.ANCIENT_CITY)) {
                addSingleItemPool(builder, BuiltInLootTables.ANCIENT_CITY, 0.10f, Items.PHANTOM_MEMBRANE);
            } else if (key.equals(BuiltInLootTables.END_CITY_TREASURE)) {
                addSingleItemPool(builder, BuiltInLootTables.END_CITY_TREASURE, 0.20f, Items.SHULKER_SHELL);
            }
        });
    }

    private static void addSingleItemPool(LootTable.Builder builder, ResourceKey<LootTable> ignored, float chance, net.minecraft.world.item.Item item) {
        LootPool pool = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0f))
                .add(LootItem.lootTableItem(item))
                .when(LootItemRandomChanceCondition.randomChance(chance))
                .build();
        builder.pool(pool);
    }
}
