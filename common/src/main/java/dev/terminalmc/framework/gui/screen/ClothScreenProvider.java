package dev.terminalmc.framework.gui.screen;

import com.mojang.blaze3d.platform.InputConstants;
import dev.terminalmc.framework.config.Config;
import me.shedaniel.clothconfig2.api.*;
import me.shedaniel.clothconfig2.gui.entries.StringListListEntry;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.*;

import static dev.terminalmc.framework.util.Localization.localized;

public class ClothScreenProvider {
    /**
     * Builds and returns a Cloth Config options screen.
     * @param parent the current screen.
     * @return a new options {@link Screen}.
     * @throws NoClassDefFoundError if the Cloth Config API mod is not
     * available.
     *
     * <p> Most entry-builder options are displayed, those not required are
     * marked as // op</p>
     *
     * <p>Optional always-available entry-builder options are:
     * <li>
     *     {@code setTooltipSupplier}, which allows you to set the tooltip based
     *     on the option value.
     * </li>
     * <li>
     *     {@code setErrorSupplier}, which allows you to provide custom error
     *     conditions and error text.
     * </li>
     * <li>
     *     {@code setRequirement}, which tells Cloth Config when to display the
     *     option.
     * </li>
     * <li>
     *     {@code setDisplayRequirement}, which tells Cloth Config when to allow
     *     the user to change the option value.
     * </li>
     * <li>
     *     {@code requireRestart}, self-explanatory.
     * </li>
     * </p>
     */
    static Screen getConfigScreen(Screen parent) {
        Config.Options options = Config.get().options;

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(localized("screen", "options"))
                .setSavingRunnable(Config::save);
        ConfigEntryBuilder eb = builder.entryBuilder();

        // First category
        ConfigCategory firstCat = builder.getOrCreateCategory(localized("option", "first"));

        // Yes/No button
        firstCat.addEntry(eb.startBooleanToggle(
                localized("option", "first.boolean_example"), options.booleanExample)
                .setTooltip(localized("option", "first.boolean_example.tooltip"))
                .setDefaultValue(Config.Options.defaultBooleanExample)
                .setSaveConsumer(val -> options.booleanExample = val)
                .build());

        // Colored Custom/Custom button
        firstCat.addEntry(eb.startBooleanToggle(
                localized("option", "first.boolean_example"), options.booleanExample)
                .setTooltip(localized("option", "first.boolean_example.tooltip"))
                .setDefaultValue(Config.Options.defaultBooleanExample)
                .setSaveConsumer(val -> options.booleanExample = val)
                .setYesNoTextSupplier(val -> val // op
                        ? localized("option", "first.boolean_example.true")
                        .withStyle(ChatFormatting.GREEN)
                        : localized("option", "first.boolean_example.false")
                        .withStyle(ChatFormatting.RED))
                .build());

        // Integer slider with value text formatting (also available for Long)
        firstCat.addEntry(eb.startIntSlider(
                localized("option", "first.int_example"), options.intExample, 0, 10)
                .setTooltip(localized("option", "first.int_example.tooltip"))
                .setDefaultValue(Config.Options.defaultIntExample)
                .setSaveConsumer(val -> options.intExample = val)
                .setTextGetter(val -> localized("option", "first.int_example.value", val)) // op
                .build());

        // Double field with range (also available for Integer, Float, Long)
        firstCat.addEntry(eb.startDoubleField(
                localized("option", "first.double_example"), options.doubleExample)
                .setTooltip(localized("option", "first.double_example.tooltip"))
                .setDefaultValue(Config.Options.defaultDoubleExample)
                .setSaveConsumer(val -> options.doubleExample = val)
                .setMin(0d) // op
                .setMax(10d) // op
                .build());

        // String field (lenient)
        firstCat.addEntry(eb.startStrField(
                localized("option", "first.lenient_string_example"), options.lenientStringExample)
                .setTooltip(localized("option", "first.lenient_string_example.tooltip"))
                .setDefaultValue(Config.Options.defaultLenientStringExample)
                .setSaveConsumer(val -> options.lenientStringExample = val)
                .build());

        // String field (strict) with dropdown suggestion provider
        firstCat.addEntry(eb.startStringDropdownMenu(
                localized("option", "first.strict_string_example"), options.strictStringExample)
                .setTooltip(localized("option", "first.strict_string_example.tooltip"))
                .setDefaultValue(Config.Options.defaultStrictStringExample)
                .setSaveConsumer(val -> options.strictStringExample = val)
                .setSelections(Config.Options.strictStringExampleValues)
                .setErrorSupplier(val -> {
                    if (Config.Options.strictStringExampleValues.contains(val)) return Optional.empty();
                    else return Optional.of(localized("option", "first.strict_string_example.error"));
                })
                .build());

        // Enum dropdown
        firstCat.addEntry(eb.startDropdownMenu(
                localized("option", "first.enum_example"), options.enumExample,
                        Config.TriState::valueOf)
                .setTooltip(localized("option", "first.enum_example.tooltip"))
                .setDefaultValue(Config.Options.defaultEnumExample)
                .setSaveConsumer(val -> options.enumExample = val)
                .setSelections(List.of(Config.TriState.values()))
                .setSuggestionMode(false) // Disable typing
                .build());

        // Enum cycling button
        firstCat.addEntry(eb.startEnumSelector(
                localized("option", "first.enum_example"),
                        Config.TriState.class, options.enumExample)
                .setTooltip(localized("option", "first.enum_example.tooltip"))
                .setDefaultValue(Config.Options.defaultEnumExample)
                .setSaveConsumer(val -> options.enumExample = val)
                .setEnumNameProvider(val ->
                        localized("option", "first.enum_example.value", val.name())) // op
                .build());

        // Object (in this case, string) list cycling button
        firstCat.addEntry(eb.startSelector(
                localized("option", "first.item_example"),
                        Config.Options.strictStringExampleValues.toArray(), options.strictStringExample)
                .setTooltip(localized("option", "first.item_example.tooltip"))
                .setDefaultValue(Config.Options.defaultStrictStringExample)
                .setSaveConsumer(val -> options.strictStringExample = (String)val)
                .setNameProvider(val -> Component.literal((String)val)) // op
                .build());

        // Second category
        ConfigCategory secondCat = builder.getOrCreateCategory(localized("option", "second"));

        // Collapsible list of strings (also available for Integer, Float, Double, Long)
        secondCat.addEntry(eb.startStrList(
                localized("option", "second.string_list_example"), options.stringListExample)
                .setTooltip(localized("option", "second.string_list_example.tooltip"))
                .setDefaultValue(Config.Options.defaultStringListExample)
                .setSaveConsumer(val -> options.stringListExample = val)
                .setCreateNewInstance((entry) -> new StringListListEntry.StringListCell(
                        Config.Options.defaultStringListExampleValue, entry)) // op
                .setInsertInFront(false) // op, default false
                .setExpanded(true) // op, default false
                .build());

        // Third category
        ConfigCategory thirdCat = builder.getOrCreateCategory(localized("option", "third"));

        // Multiline text
        thirdCat.addEntry(eb.startTextDescription(
                localized("option", "third.message"))
                .build());

        // Collapsible group of options
        SubCategoryBuilder thirdCatFirstGroup = eb.startSubCategory(
                localized("option", "third.first"))
                .setTooltip(localized("option", "third.first.tooltip"))
                .setExpanded(true); // op, default false

        thirdCatFirstGroup.add(eb.startColorField(
                localized("option", "third.first.rgb_example"), options.rgbExample)
                .setTooltip(localized("option", "third.first.rgb_example.tooltip"))
                .setDefaultValue(Config.Options.defaultRgbExample)
                .setSaveConsumer(val -> options.rgbExample = val)
                .setAlphaMode(false) // op, default false
                .build());

        thirdCatFirstGroup.add(eb.startColorField(
                        localized("option", "third.first.argb_example"), options.argbExample)
                .setTooltip(localized("option", "third.first.argb_example.tooltip"))
                .setDefaultValue(Config.Options.defaultArgbExample)
                .setSaveConsumer(val -> options.argbExample = val)
                .setAlphaMode(true) // op, default false
                .build());

        thirdCatFirstGroup.add(eb.startKeyCodeField(
                localized("option", "third.first.key_example"),
                        InputConstants.getKey(options.keyExample, options.keyExample))
                .setTooltip(localized("option", "third.first.key_example.tooltip"))
                .setDefaultValue(InputConstants.getKey(Config.Options.defaultKeyExample,
                        Config.Options.defaultKeyExample))
                .setKeySaveConsumer(val -> options.keyExample = val.getValue())
                .setAllowKey(true) // op, default true
                .setAllowMouse(true) // op, default true
                .build());

        // Item field with dropdown
        // Cloth Config does not have a dedicated item option like YACL
        Set<String> items = new HashSet<>(BuiltInRegistries.ITEM.keySet()
                .stream().map(ResourceLocation::toString).toList());
        thirdCatFirstGroup.add(eb.startStringDropdownMenu(
                        localized("option", "third.first.item_example"), options.itemExample)
                .setTooltip(localized("option", "third.first.item_example.tooltip"))
                .setDefaultValue(Config.Options.defaultItemExample)
                .setSaveConsumer(val -> options.itemExample = val)
                .setSelections(items)
                .setErrorSupplier(val -> {
                    if (items.contains(val)) return Optional.empty();
                    else return Optional.of(localized("option", "third.first.item_example.error"));
                })
                .build());

        thirdCat.addEntry(thirdCatFirstGroup.build());

        return builder.build();
    }
}
