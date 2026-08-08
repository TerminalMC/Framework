# Changelog

This project uses the versioning scheme `<major>.<minecraft>.<minor>[-<alpha|beta>.<build>]`,
according to the following rules:

- `major` is incremented on backwards-incompatible API changes, or major feature changes under the
  project owner's interpretation of the word.
- `minecraft` denotes the Minecraft version that the release is built against, and is initialized to
  `1` for Minecraft `v1.0.0` and incremented by `1` for each release version thereafter.
- `minor` is initialized to `0` on the first release for any Minecraft version, is reset to `0` when
  `major` is incremented, and is incremented by `1` on any other change.
- `-<alpha|beta>.build` is used to indicate that a version should be considered less stable and less
  well-tested than normal. `build` is reset to `0` when changing from `alpha` to `beta` and is
  incremented by `1` on each release.

Unreleased changes should be listed in the "Unreleased" changelog entry. Immediately prior to a
release, the "Unreleased" header should be replaced with the release version and the release date
(UTC), and a new "Unreleased" entry should be created above the renamed entry.

Each changelog entry should be populated with entries in the following order:

- Security updates, in any form.
- Minecraft target version updates, in the form "Updated to mc\<version>"
- Additions, in the form "Added \<feature>".
- Changes, in any form (e.g., "\<feature> now \<new behavior>").
- Deprecations, in the form "Deprecated \<API surface>".
- Removals, in the form "Removed \<feature>".
- Fixes, in the form "Fixed an issue \<causing> \<problem description>".
- Translation updates, in the form "Updated \<language> translation".

Additional notes:

- The first line of a changelist entry should be a concise, single-sentence summary. If additional
  information is to be provided, nested bullets should be used.
- If a changelist entry is associated with an external contribution, the contributor's username and
  the contribution number in parentheses should be appended to the summary.
  - e.g., "Added an option to do something (someone) (#23)."
- If a changelist entry is associated with a ticket, the ticket number in parentheses should be
  appended to the summary.
  - e.g., "Fixed an issue causing entities to disappear (#45)."
- The changelog entry for the first beta release following an alpha series, and for the first full
  release following an alpha/beta series, should include two top-level bullets: "Changes since last
  \<alpha|beta>" and "Changes since last full release".
- If a release is yanked, the changelog entry should not be removed but should have "[YANKED]"
  appended to the header.

___

## Unreleased

- Fixed a DoS exploit using IFF packets (#19).
- Updated to mc99.8.7.
- Added an option to adjust the target flight speed.
- Flying now decreases saturation.
- Deprecated `setHeight`.
  - `setAltitudeAGL` and `setAltitudeASL` should now be used.
- Removed the air temperature indicator from the HUD.
- Fixed an issue causing collision avoidance to erroneously detect certain entities.
- Updated German translation (jemand513) (#28)

## 1.102.0 [2026-04-09]

- Updated to mc26.1.2.

## 1.101.0 [2026-04-01]

- Updated to mc26.1.1.

## 1.100.0 [2026-03-24]

- Updated to mc26.1.

## 1.99.0 [2025-12-09]

- Updated to mc1.21.11.

## 1.98.0 [2025-10-07]

- Updated to mc1.21.10.

## 1.97.0 [2025-09-30]

- Updated to mc1.21.9.

## 1.96.0 [2025-07-17]

- Updated to mc1.21.8.

## 1.95.0 [2025-06-30]

- Updated to mc1.21.7.

## 1.94.0 [2025-06-17]

- Updated to mc1.21.6.

## 1.93.0 [2025-03-25]

- Updated to mc1.21.5.

## 1.92.0 [2024-12-03]

- Updated to mc1.21.4.

## 1.91.0 [2024-10-23]

- Updated to mc1.21.3.

## 1.90.0 [2024-10-22]

- Updated to mc1.21.2.

## 1.89.0 [2024-08-08]

- Updated to mc1.21.1.

## 1.88.0 [2024-06-13]

- Updated to mc1.21.

## 1.87.0 [2024-04-29]

- Updated to mc1.20.6.

## 1.86.0 [2024-04-23]

- Updated to mc1.20.5.

## 1.85.0 [2023-12-07]

- Updated to mc1.20.4.

## 1.84.0 [2023-12-05]

- Updated to mc1.20.3.

## 1.83.0 [2023-09-21]

- Updated to mc1.20.2.

## 1.82.0 [2023-06-12]

- Updated to mc1.20.1.

## 1.81.0 [2023-06-07]

- Updated to mc1.20.

## 1.80.0 [2023-03-14]

- Updated to mc1.19.4.

## 1.79.0 [2022-12-07]

- Updated to mc1.19.3.

## 1.78.0 [2022-08-05]

- Updated to mc1.19.2.

## 1.77.0 [2022-07-27]

- Updated to mc1.19.1.

## 1.76.0 [2022-06-07]

- Updated to mc1.19.

## 1.75.0 [2022-02-28]

- Updated to mc1.18.2.

## 1.74.0 [2021-12-10]

- Updated to mc1.18.1.

## 1.73.0 [2021-11-30]

- Updated to mc1.18.

## 1.72.0 [2021-07-06]

- Updated to mc1.17.1

## 1.71.0 [2021-06-08]

- Updated to mc1.17.

## 1.70.0 [2021-01-15]

- Updated to mc1.16.5.

## 1.69.0 [2020-11-02]

- Updated to mc1.16.4.

## 1.68.0 [2020-09-10]

- Updated to mc1.16.3.

## 1.67.0 [2020-08-11]

- Updated to mc1.16.2.

## 1.66.0 [2020-06-24]

- Updated to mc1.16.1.

## 1.65.0 [2020-06-23]

- Updated to mc1.16.

## 1.64.0 [2020-01-21]

- Updated to mc1.15.2.

## 1.63.0 [2019-12-17]

- Updated to mc1.15.1.

## 1.62.0 [2019-12-10]

- Updated to mc1.15.

## 1.61.0 [2019-07-19]

- Updated to mc1.14.4.

## 1.60.0 [2019-06-24]

- Updated to mc1.14.3.

## 1.59.0 [2019-05-27]

- Updated to mc1.14.2.

## 1.58.0 [2019-05-13]

- Updated to mc1.14.1.

## 1.57.0 [2019-04-23]

- Updated to mc1.14.

## 1.56.0 [2018-10-22]

- Updated to mc1.13.2.

## 1.55.0 [2018-08-22]

- Updated to mc1.13.1.

## 1.54.0 [2018-07-18]

- Updated to mc1.13.

## 1.53.0 [2017-09-18]

- Updated to mc1.12.2.

## 1.52.0 [2017-08-03]

- Updated to mc1.12.1,

## 1.51.0 [2017-06-07]

- Updated to mc1.12.

## 1.50.0 [2016-12-21]

- Updated to mc1.11.2.

## 1.49.0 [2016-12-20]

- Updated to mc1.11.1.

## 1.48.0 [2016-11-14]

- Updated to mc1.11.

## 1.47.0 [2016-06-23]

- Updated to mc1.10.2.

## 1.46.0 [2016-06-22]

- Updated to mc1.10.1.

## 1.45.0 [2016-06-08]

- Updated to mc1.10.

## 1.44.0 [2016-05-10]

- Updated to mc1.9.4.

## 1.43.0 [2016-05-10]

- Updated to mc1.9.3.

## 1.42.0 [2016-03-30]

- Updated to mc1.9.2.

## 1.41.0 [2016-03-30]

- Updated to mc1.9.1.

## 1.40.0 [2016-02-29]

- Updated to mc1.9.

## 1.39.0 [2015-12-09]

- Updated to mc1.8.9.

## 1.38.0 [2015-07-28]

- Updated to mc1.8.8.

## 1.37.0 [2015-06-05]

- Updated to mc1.8.7.

## 1.36.0 [2015-05-25]

- Updated to mc1.8.6.

## 1.35.0 [2015-05-22]

- Updated to mc1.8.5.

## 1.34.0 [2015-04-17]

- Updated to mc1.8.4.

## 1.33.0 [2015-02-20]

- Updated to mc1.8.3.

## 1.32.0 [2015-02-19]

- Updated to mc1.8.2.

## 1.31.0 [2014-11-24]

- Updated to mc1.8.1

## 1.30.0 [2014-09-02]

- Updated to mc1.8.

## 1.29.0 [2014-06-26]

- Updated to mc1.7.10.

## 1.28.0 [2014-04-14]

- Updated to mc1.7.9.

## 1.27.0 [2014-04-11]

- Updated to mc1.7.8.

## 1.26.0 [2014-04-09]

- Updated to mc1.7.7.

## 1.25.0 [2014-04-09]

- Updated to mc1.7.6.

## 1.24.0 [2014-02-26]

- Updated to mc1.7.5.

## 1.23.0 [2013-12-10]

- Updated to mc1.7.4.

## 1.22.0 [2013-10-25]

- Updated to mc1.7.2.

## 1.21.0 [2013-09-19]

- Updated to mc1.6.4.

## 1.20.0 [2013-07-08]

- Updated to mc1.6.2.

## 1.19.0 [2013-07-01]

- Updated to mc1.6.1.

## 1.18.0 [2013-05-02]

- Updated to mc1.5.2.

## 1.17.0 [2013-03-21]

- Updated to mc1.5.1.

## 1.16.0 [2013-03-13]

- Updated to mc1.5.

## 1.15.0 [2013-01-09]

- Updated to mc1.4.7.

## 1.14.0 [2012-12-20]

- Updated to mc1.4.6.

## 1.13.0 [2012-11-19]

- Updated to mc1.4.5.

## 1.12.0 [2012-11-14]

- Updated to mc1.4.4.

## 1.11.0 [2012-10-25]

- Updated to mc1.4.2.

## 1.10.0 [2012-08-16]

- Updated to mc1.3.2.

## 1.9.0 [2012-08-01]

- Updated to mc1.3.1.

## 1.8.0 [2012-04-04]

- Updated to mc1.2.5.

## 1.7.0 [2012-03-22]

- Updated to mc1.2.4.

## 1.6.0 [2012-03-02]

- Updated to mc1.2.3.

## 1.5.0 [2012-03-01]

- Updated to mc1.2.2.

## 1.4.0 [2012-03-01]

- Updated to mc1.2.1.

## 1.3.0 [2012-01-12]

- Updated to mc1.1.

## 1.2.0 [2011-11-24]

- Updated to mc1.0.1.

## 1.1.0 [2011-11-18]

- Updated to mc1.0.0.
