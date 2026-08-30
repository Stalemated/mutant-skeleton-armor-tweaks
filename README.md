# Mutant Skeleton Armor Tweaks

**Mutant Skeleton Armor Tweaks (MSAT)** is an add-on that aims to customize and balance the effects from the Mutant Skeleton armor set from Fuzs' Mutant Monsters mod.

---

## Features

### In game config
- Toggle each armor piece's default effect in the in game config (or the config file).
- Config is synced from servers to clients upon joining.
- Only OPed players can modify the mod's settings in game. Singleplayer worlds are not affected by this.

### Crossbow compatibility
- A config toggle allows the Mutant Skeleton Chestplate to buff crossbow draw speed. (Also buffs other weapons with the #c:ranged_weapons tag)

### Custom tooltips
- Only available when **[Custom Tooltip API](https://modrinth.com/mod/custom-tooltip-api)** is installed.
  - Effect descriptions are dynamically displayed (when the effects are active in the config) inside each armor piece's tooltip.
  - These descriptions are shown as a potion effect (legs, boots) or a line displayed when the player presses the corresponding keybind (`Shift` by default).

---

## Compatibility

* **Eldritch End:** Mutant Skeleton Skull compatible with Eldritch End's infusions.

---

## Building from Source

MSAT depends on **S-Lib** and **Custom Tooltip API (CTA)**, which must be published to your local Maven repository before compiling.

#### 1. Clone and Publish S-Lib
```bash
git clone https://github.com/Stalemated/s-lib.git
cd s-lib
# Publish to maven local
gradlew.bat publishToMavenLocal # (Windows)
./gradlew publishToMavenLocal   # (Linux / macOS)
cd ..
```

#### 2. Clone and Publish CTA
```bash
git clone https://github.com/Stalemated/custom-tooltip-api.git
cd custom-tooltip-api
# Publish to maven local
gradlew.bat publishToMavenLocal # (Windows)
./gradlew publishToMavenLocal   # (Linux / macOS)
cd ..
```

#### 3. Clone MSAT and Build
```bash
git clone https://github.com/Stalemated/mutant-skeleton-armor-tweaks-multiloader.git
cd mutant-skeleton-armor-tweaks-multiloader
# Build the mod
gradlew.bat build # (Windows)
./gradlew build   # (Linux / macOS)
```

Output JARs will be located in `[loader]/build/libs/`.

---

## Available Platforms

| Platform | Versions             |
|----------|----------------------|
| Fabric   | 1.20.1, 1.21.1 (WIP) |
| Forge    | 1.20.1               |
| NeoForge | 1.21.1 (WIP)         |

---

## Dependencies

- [Mutant Monsters](https://www.curseforge.com/minecraft/mc-mods/mutant-monsters)
- [Puzzles Lib](https://www.curseforge.com/minecraft/mc-mods/puzzles-lib)
- [S-Lib](https://www.curseforge.com/minecraft/mc-mods/s-lib)
- [Custom Tooltip API](https://www.curseforge.com/minecraft/mc-mods/custom-tooltip-api)
- [YACL](https://www.curseforge.com/minecraft/mc-mods/yacl)

### Fabric Only
- [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api)
- [ModMenu](https://www.curseforge.com/minecraft/mc-mods/modmenu)
