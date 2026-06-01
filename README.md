# The Pacifist

A small Fabric mod for Minecraft 26.1.2 that lets a player reach the limits of the game without killing a single hostile mob. **Designed for peaceful difficulty** — every drop has a non-mob path. Brush spawners, weep tears from crying obsidian, stonecut wool into string, raid chests for skulls and shells.

Companion mod: [**The Vegetarian**](https://github.com/polyvenom/vegetarian), which handles passive-mob drops. Use either alone or together.

## What it does

Zero new items. Every output is vanilla — this mod is pure mechanics and recipes.

### Stonecutter conversions

- **Cobweb → 1 string** (no shears wear)
- **Any wool → 4 string**

### Crafting recipes

- **Gunpowder ×2** — 1 dried kelp block + 1 bone block + 1 coal (or charcoal)
- **Slime ball** — 1 kelp + 1 honeycomb
- **Spider eye** — 1 red tulip + 1 brown mushroom
- **Blaze rod** — 2 blaze powder stacked vertically (for brewing stands)

### Block interactions

- **Brush a blaze spawner** in a Nether Fortress → 1-2 blaze powder drops, particles + sound, 10-minute cooldown per spawner. Spawners exist on peaceful difficulty even though they spawn nothing.
- **Right-click crying obsidian with an empty bottle** → 15% chance of a ghast tear. Bottle is consumed either way; the RNG and bottle cost are the throttle.

### Chest loot

Vanilla loot tables get extra entries (injected non-destructively, so the mod plays nice with other content mods):

- **Wither Skeleton Skull** — 5% chance in Nether Fortress chests
- **Phantom Membrane** — 10% chance in Ancient City chests
- **Shulker Shell** — 20% chance in End City Treasure chests

## Peaceful progression

The mod doesn't add a peaceful boss bypass — those are handled by vanilla. Ender Dragon, Wither, Elder Guardian, and Warden all spawn on peaceful and remain hostile. Once you have the recipes above and access to crying obsidian (Ruined Portals, Bastion Remnants) and Nether Fortresses, the full game is reachable without engaging any standard hostile mob.

## Install

1. Install [Fabric Loader](https://fabricmc.net/use/installer/) for Minecraft 26.1.2.
2. Install [Fabric API](https://modrinth.com/mod/fabric-api).
3. Drop `pacifist-1.0.0.jar` into your `mods` folder.
4. Launch.

Server-side: install on the server too. Block interactions and loot table modifications are server-side.

## Compatibility

- **Minecraft**: 26.1.2
- **Fabric Loader**: 0.19.2+
- **Fabric API**: required
- Loot injections use Fabric's `LootTableEvents.MODIFY`, which composes safely with other mods touching the same tables

## Future work

- Trident — currently no peaceful path; planned for a future release via fishing or ocean-ruin loot injection
- Totem of Undying — currently no peaceful path; planned for a future release via woodland mansion loot injection

## Building

```sh
./gradlew build
```

Output jar lands in `build/libs/`.

## License

CC0 1.0 Universal — public domain.
