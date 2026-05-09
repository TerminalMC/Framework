# Template Changelog

This file tracks changes to the template project, and should be deleted when creating a new project
from the template.

If not deleted manually, this file will be deleted when running the `rebrandProject` Gradle task.

## 18

- Split GitHub releases by subproject

Affected files:

- `build.gradle`

## 17

- Added schedule for check-build workflow
- Added FMJ environment to properties
- Added extra maven repos to properties
- Removed mixin refmap configuration

Affected files:

- `.github/workflows/check-build.yml`
- `.github/workflows/sync-labels.yml`
- `buildSrc/src/main/groovy/util/PropUtil.groovy`
- `buildSrc/src/main/groovy/multiloader-common.gradle`
- `common/src/main/resources/framework.mixins.json`
- `fabric/src/main/resources/framework.fabric.mixins.json`
- `fabric/src/main/resources/fabric.mod.json`
- `neoforge/src/main/resources/framework.neoforge.mixins.json`
- `gradle.properties`
- `settings.gradle`

## 16

- Updated workflows
- Removed loom.mixin configuration from Fabric build.gradle

Affected files:

- `.github/workflows/**`
- `fabric/build.gradle`

## 15

- Switched to Fabric Mixin
- Added Mixin min version property

Affected files:

- `buildSrc/src/main/groovy/multiloader-common.gradle`
- `common/src/main/resources/framework.mixins.json`
- `common/build.gradle`
- `fabric/src/main/resources/framework.fabric.mixins.json`
- `fabric/build.gradle`
- `neoforge/src/main/resources/framework.neoforge.mixins.json`
- `gradle.properties`
- `settings.gradle`

## 14

- Added MixinExtras min version property
- Added Mixin config plugin
- Updated rebrand task

Affected files:

- `buildSrc/src/main/groovy/multiloader-common.gradle`
- `common/src/main/java/dev/terminalmc/framework/util/MixinConfigPlugin.java`
- `common/src/main/resources/framework.mixins.json`
- `common/build.gradle`
- `fabric/src/main/resources/framework.fabric.mixins.json`
- `neoforge/src/main/resources/framework.neoforge.mixins.json`
- `build.gradle`
- `gradle.properties`
- `rebrand.gradle`
- `rebrand.properties`

## 13

- Added a workaround for snapshot versioning on CurseForge
- Added a 'disabled' fallback screen

Affected files:

- `common/src/main/java/dev/terminalmc/framework/gui/screen/ConfigScreenProvider.java`
- `build.gradle`

## 12

- Moved editor config comments to separate lines

Affected files:

- `.editorconfig`

## 11

- Updated service loader

Affected files:

- `common/src/main/java/dev/terminalmc/framework/config/Config.java`
- `common/src/main/java/dev/terminalmc/framework/platform/services/PlatformServices.java`
- `common/src/main/java/dev/terminalmc/framework/platform/Services.java`
- `fabric/src/main/java/dev/terminalmc/framework/platform/FabricServices.java`
-
`fabric/src/main/resources/META-INF/services/dev.terminalmc.framework.platform.services.PlatformServices`
- `neoforge/src/main/java/dev/terminalmc/framework/platform/NeoForgeServices.java`
-
`neoforge/src/main/resources/META-INF/services/dev.terminalmc.framework.platform.services.PlatformServices`

## 10

- Updated GitHub actions

Affected files:

- `.github/workflows/**`

## 9

- Fixed service loader potentially using the wrong classloader

Affected files:

- `common/src/main/java/dev/terminalmc/framework/platform/Services.java`

## 8

- Fixed placeholder screen close behavior

Affected files:

- `common/src/main/java/dev/terminalmc/framework/gui/screen/ConfigScreenProvider.java`

## 7

- Reordered publishing tasks to mitigate Modrinth errors
- Updated dependencies

Affected files:

- `.github/workflows/release.yml`
- `.github/workflows/release-subproject-fabric.yml`
- `.github/workflows/release-subproject-neoforge.yml`
- `gradle/wrapper/gradle-wrapper.properties`
- `build.gradle`
- `gradle.properties`

## 6

- Removed `windows-latest` from `check-build` workflow matrix

Affected files:

- `.github/workflows/check-build.yml`

## 5

- Fixed Modrinth links

Affected files:

- `common/src/main/java/dev/terminalmc/framework/gui/screen/ConfigScreenProvider.java`
- `gradle.properties`
- `README.md`

## 4

- Now, versioned properties are grouped together in gradle.properties
- Now, release workflows do not refer to subprojects or subtasks unnecessarily
- Now, Fabric entrypoint properties do not include the source package unnecessarily
- Now, neoforge.mods.toml is editorconfig-compliant
- Updated dependencies
- Enabled automatic AW and AT validation
- Disabled NeoForge server GUI

Affected files:

- `gradle.properties`
- `.github/workflows/release-**.yml`
- `buildSrc/src/main/groovy/multiloader-common.gradle`
- `neoforge/src/main/resources/META-INF/neoforge.mods.toml`
- `common/build.gradle`
- `fabric/build.gradle`
- `neoforge/build.gradle`

## 3

- Fixed editor config for Java record component wrapping

Affected files:

- `.editorconfig`

## 2

- Fixed always JiJ-ing MixinExtras on Fabric

Affected files:

- `fabric/build.gradle`

## 1

- Initialized template versioning
