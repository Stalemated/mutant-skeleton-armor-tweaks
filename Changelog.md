# Changelog

## 3.0.0+1.20.1

### Features
- Added YACL dependency
  - This implementation allows players to change the config from the game, in the Mods menu
  - The config can successfully be changed freely in:
    - Singleplayer 
    - Servers, only if the player is OP
- Added S-Lib dependency (2.1.0)
- Changed tooltip lines to be registered with Custom Tooltip API, adding it as a soft dependency

### Fixes
- Fixed config not syncing with the server's, allowing players to set a different config than the server's and using that to exploit disabled effects by enabling them client side
- Restored the Eldritch End compat from 2.1.0, due to a newer version of that mod not releasing

### Technical Changes
- Cleaned up the config and the mod now uses S-Lib as a manager for synced configs across servers
- Refactored the entire project to work as an Architectury project, allowing for multi loader releases (Fabric & Forge 1.20.1)
- Refactored the project structure, allowing for better readability of the code. It now aligns with SOLID principles as well as clean code guidelines.