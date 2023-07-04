# Changelog - ![Latest Release](https://img.shields.io/github/v/release/llgava/neelix?logo=github&logoColor=959da5&labelColor=353c43&color=0091c2&Current&label=Release)

## [1.6.0: Custom Inventories](https://github.com/llgava/neelix/releases/tag/v1.6.0) - (07-03-2023)
##### [Commit History](https://github.com/llgava/neelix/compare/v1.0.0...v1.6.0)

### **Added**
+ Generic items.
  + Generic items can handle values depending on its type.
+ New inventory handlers.
  + OpenNeelixInventoryHandle: A block of code to fire when a player opens a Neelix inventory.
  + CloseNeelixInventoryHandle: A block of code to fire when a player closes a Neelix inventory.
  + FullNeelixInventoryHandle: A combination of the 2 handlers above.
+ Utilities methods.
  + NeelixUtils#getServerVersion(): Returns a String of current server version in the following format: 1.13.0.
  + NeelixUtils#isMinimumServerVersion(String minVersion): Returns if the server is in a minimum version.
  + NeelixUtils#parseMessage(String message, String... values): Can parse values for String that contains {x}.

### **Changes**
+ Completely rewritten inventory system
+ Failure events changes.

### **Removed**
+ Removed initialization method.
  + Now it's not necessary initialize or create a new instance between your plugin and Neelix.

> The Wiki is currently in **🚧 WIP** status!

## [1.0.0: Custom Inventories](https://github.com/llgava/neelix/releases/tag/v1.0.0) - (01-08-2023)
##### [Commit History](https://github.com/llgava/neelix/compare/v0.0.1-SNAPSHOT...v1.0.0)

### **Added**
+ Add custom inventories.
  + Now it's possible to create **Simple** and **Paginated** inventories.
+ Add custom inventories handler.
+ Add a `Neelix#initialize(JavaPlugin plugin)` to set up Neelix configurations.
+ Add `Creating Custom Inventories` section on wiki.
+ Add an [Example Plugin](https://github.com/llgava/neelix/tree/main/example-plugin) module.

### **Changes**
+ All the Neelix source code is located at [api](https://github.com/llgava/neelix/tree/main/api) module.
+ Update java docs.