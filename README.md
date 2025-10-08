# BBS Player Mod (Fork)

**This is a fork of McHorse's BBS mod, modified to be a playback-only version.**

## About This Fork

This version of BBS mod has been modified to remove all editing capabilities while maintaining full playback functionality. This creates a "player-only" experience where users can view and play back animations and replays created with the full BBS mod, but cannot create or edit content themselves.

### Changes from Original BBS Mod

#### Disabled Features:
- **Dashboard UI**: The main editing interface has been disabled (no keybind to open)
- **Morphing Menu**: Morphing editor and morph selection UI disabled
- **Recording**: Replay recording and video recording features disabled
- **Replay Editor**: Cannot edit, create, or modify replays
- **Item Editor**: Model block and gun item editors disabled
- **Teleport Functions**: Teleport-to-actor functions disabled
- **Gun Zoom**: Gun zoom mechanics disabled
- **Editing Commands**: Most server commands disabled (morph, config, cheats, etc.)

#### Enabled Features:
- **Film Playback**: Full playback of films/animations created with the original mod
- **Play/Pause Controls**: Keyboard shortcuts for playing and pausing films
- **Film Commands**: `/bbs films play` and `/bbs films stop` commands (requires OP)
- **All Rendering**: Complete rendering capabilities for viewing content
- **Model Loading**: Full support for loading and displaying models, textures, and animations

### Command Usage

All commands now require **operator permissions (level 2)**:

```
/bbs films play <target> <film> [camera]
/bbs films stop <target> <film>
```

**Examples:**
```
/bbs films play @a my_animation true    - Plays "my_animation" to all players with camera
/bbs films stop @a my_animation         - Stops the animation
```

### Keybinds

**All keybinds have been disabled.** Films can only be controlled via server commands by operators.

No keyboard shortcuts are available in this player-only version.

---

## Original BBS Mod Information

BBS mod is a Minecraft mod for Fabric 1.20.4 and 1.20.1 (works on Forge as well) for creating animations within Minecraft. It has more features than that, but overall its main task is to facilitate making animated content within Minecraft. For more information, see BBS mod's [Modrinth](https://modrinth.com/mod/bbs-mod/) or [CurseForge](https://www.curseforge.com/minecraft/mc-mods/bbs-mod) pages.

This repository is a fork of the source code of BBS mod. The original `1.20.4` code is in the `master` branch.

See `LICENSE.md` for information about the license.