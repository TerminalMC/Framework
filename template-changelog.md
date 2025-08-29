# Template Changelog

This file tracks changes to the template project, and should be deleted when creating a new project
from the template.

If not deleted manually, this file will be deleted when running the `rebrandProject` Gradle task.

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
