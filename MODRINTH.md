# The Pacifist — Modrinth Listing

Copy-paste content for the Modrinth project page. The description body below is the markdown that goes into the long-form project description.

## Form metadata

| Field | Value |
| --- | --- |
| Title | The Pacifist |
| Slug | `pacifist` |
| Summary (short, ≤256 chars) | Non-lethal alternatives to every hostile-mob drop. Designed for peaceful difficulty — reach the limits of the game without killing a single mob. Zero new items. |
| Categories | `game-mechanics`, `adventure`, `mobs` |
| Loaders | Fabric |
| Minecraft versions | 26.1.2 |
| Environment — Client side | Required |
| Environment — Server side | Required |
| License | CC0-1.0 (Public Domain) |
| Source link | https://github.com/polyvenom/pacifist |
| Issue tracker | https://github.com/polyvenom/pacifist/issues |
| Donation | Ko-fi (polyvenom) |

## Description body

---

A small Fabric mod for Minecraft 26.1.2 that lets you reach the limits of the game without killing a single hostile mob. **Designed for peaceful difficulty** — every drop has a non-mob path.

Zero new items. Every output is vanilla — this mod is pure mechanics and recipes.

## What it does

### ✂️ Stonecutter conversions

- **Cobweb → 1 string**
- **Any wool → 4 string**

### 🧪 Crafting recipes

- **Gunpowder ×2** — 1 dried kelp block + 1 bone block + 1 coal (or charcoal)
- **Slime ball** — 1 kelp + 1 honeycomb
- **Spider eye** — 1 red tulip + 1 brown mushroom
- **Blaze rod** — 2 blaze powder stacked vertically (for brewing stands)

### 🔥 Block interactions

- **Brush a blaze spawner** in a Nether Fortress → 1-2 blaze powder, particles + sound, 10-minute cooldown per spawner. The spawner block exists on peaceful even though no blazes spawn.
- **Bottle on crying obsidian** → 15% chance of a ghast tear. Bottle consumed either way. Crying obsidian is found in Ruined Portals and Bastion Remnants.

### 💀 Chest loot injections

Vanilla loot tables get extra entries — non-destructive, so the mod plays nice with other content mods.

- **Wither Skeleton Skull** — 5% in Nether Fortress chests
- **Phantom Membrane** — 10% in Ancient City chests
- **Shulker Shell** — 20% in End City Treasure chests

## Peaceful progression

The mod doesn't trivialize bosses — Ender Dragon, Wither, Elder Guardian, and Warden all still spawn on peaceful and remain hostile. With the recipes and interactions above plus access to crying obsidian and Nether Fortresses, the full game is reachable without engaging any standard hostile mob.

## Future work

- Trident and Totem of Undying — currently no peaceful path; planned for a future release via fishing / mansion loot injection

## Companion mod

Pair with **[The Vegetarian](https://modrinth.com/mod/vegetarian)** to also cover passive-mob drops. The two mods are independent — use either or both.

## Compatibility

- Minecraft 26.1.2 + Fabric Loader 0.19.2+
- Requires Fabric API
- Loot injections use Fabric's `LootTableEvents.MODIFY` — composes safely with other mods

## Source

[github.com/polyvenom/pacifist](https://github.com/polyvenom/pacifist) — CC0 public domain.
