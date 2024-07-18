package dev.terminalmc.framework.gui.screen;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import dev.isxander.yacl3.gui.YACLScreen;
import dev.terminalmc.framework.Framework;
import dev.terminalmc.framework.config.Config;
import net.minecraft.ResourceLocationException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.awt.Color;
import java.util.List;

import static dev.terminalmc.framework.util.Localization.localized;

public class YaclScreenProvider {
    /**
     * Builds and returns a YACL options screen.
     * @param parent the current screen.
     * @return a new options {@link Screen}.
     * @throws NoClassDefFoundError if the YACL mod is not available.
     *
     * <p>All controller options are displayed, those not required are marked as
     * {@code // op}</p>
     *
     * <p>Optional always-available option-builder options are:</p>
     * <ul>
     *     <li>{@code flags}, which tell YACL when to start using the new value.
     *     </li>
     *     <li>{@code listeners}, which are notified immediately when the option
     *     value is changed.
     *     </li>
     *     <li>{@code instant}, which tells YACL to apply the new value
     *     immediately, rather than waiting for save.
     *     </li>
     *     <li>{@code available}, which tells YACL whether to allow the user to
     *     change the option value.
     *     </li>
     * </ul>
     *
     * <p>Other notes:</p>
     * <ul>
     *     <li>{@link ListOption} allows creation of a dynamic list of options
     *     using any controller type, but cannot contain an {@link OptionGroup}.
     *     </li>
     *     <li>{@link OptionGroup} allows creation of a collapsible group of
     *     options using any controller type, but cannot contain a
     *     {@link ListOption}.
     *     </li>
     *     <li>In cases requiring configuration of a list of complex objects,
     *     one solution is to have each instance be configured by an
     *     {@link OptionGroup}, with {@link ButtonOption}s to add and remove
     *     instances.
     *     </li>
     *     <li>If the complex object itself requires a {@link ListOption}, it
     *     should be added to the {@link ConfigCategory.Builder} immediately
     *     after the {@link OptionGroup} is added, so that it will appear to be
     *     attached.
     *     </li>
     * </ul>
     */
    static Screen getConfigScreen(Screen parent) {
        Config.Options options = Config.get().options;

        YetAnotherConfigLib.Builder builder = YetAnotherConfigLib.createBuilder()
                .title(localized("screen", "options"))
                .save(Config::save);

        // First category
        ConfigCategory.Builder firstCat = ConfigCategory.createBuilder()
                .name(localized("option", "first"))
                .tooltip(localized("option", "first.tooltip")); // op

        // On/Off button
        firstCat.option(Option.<Boolean>createBuilder()
                .name(localized("option", "first.boolean_example"))
                .description(OptionDescription.of(
                        localized("option", "first.boolean_example.tooltip")))
                .binding(Config.Options.defaultBooleanExample,
                        () -> options.booleanExample,
                        val -> options.booleanExample = val)
                .controller(BooleanControllerBuilder::create)
                .build());

        // Colored Yes/No button
        firstCat.option(Option.<Boolean>createBuilder()
                .name(localized("option", "first.boolean_example"))
                .description(OptionDescription.of(
                        localized("option", "first.boolean_example.tooltip")))
                .binding(Config.Options.defaultBooleanExample,
                        () -> options.booleanExample,
                        val -> options.booleanExample = val)
                .controller(option -> BooleanControllerBuilder.create(option)
                        .coloured(true) // op
                        .yesNoFormatter()) // op
                .build());

        // Colored Custom/Custom button
        firstCat.option(Option.<Boolean>createBuilder()
                .name(localized("option", "first.boolean_example"))
                .description(OptionDescription.of(
                        localized("option", "first.boolean_example.tooltip")))
                .binding(Config.Options.defaultBooleanExample,
                        () -> options.booleanExample,
                        val -> options.booleanExample = val)
                .controller(option -> BooleanControllerBuilder.create(option)
                        .coloured(true) // op
                        .formatValue(val -> val // op
                                ? localized("option", "first.boolean_example.true")
                                : localized("option", "first.boolean_example.false")))
                .build());

        // Check box
        firstCat.option(Option.<Boolean>createBuilder()
                .name(localized("option", "first.boolean_example"))
                .description(OptionDescription.of(
                        localized("option", "first.boolean_example.tooltip")))
                .binding(Config.Options.defaultBooleanExample,
                        () -> options.booleanExample,
                        val -> options.booleanExample = val)
                .controller(TickBoxControllerBuilder::create) // No options
                .build());

        // Integer slider with value text formatting (also available for Float, Double, Long)
        firstCat.option(Option.<Integer>createBuilder()
                .name(localized("option", "first.int_example"))
                .description(OptionDescription.of(
                        localized("option", "first.int_example.tooltip")))
                .binding(Config.Options.defaultIntExample,
                        () -> options.intExample,
                        val -> options.intExample = val)
                .controller(option -> IntegerSliderControllerBuilder.create(option)
                        .range(0, 10)
                        .step(1)
                        .formatValue(val -> // op
                                localized("option", "first.int_example.value", val)))
                .build());

        // Double field with range (also available for Integer, Float, Long)
        firstCat.option(Option.<Double>createBuilder()
                .name(localized("option", "first.double_example"))
                .description(OptionDescription.of(
                        localized("option", "first.double_example.tooltip")))
                .binding(Config.Options.defaultDoubleExample,
                        () -> options.doubleExample,
                        val -> options.doubleExample = val)
                .controller(option -> DoubleFieldControllerBuilder.create(option)
                        .min(0d) // op
                        .max(10d) // op
                        .formatValue(val -> // op
                                localized("option", "first.int_example.value", val)))
                .build());

        // String field (lenient)
        firstCat.option(Option.<String>createBuilder()
                .name(localized("option", "first.lenient_string_example"))
                .description(OptionDescription.of(
                        localized("option", "first.lenient_string_example.tooltip")))
                .binding(Config.Options.defaultLenientStringExample,
                        () -> options.lenientStringExample,
                        val -> options.lenientStringExample = val)
                .controller(StringControllerBuilder::create) // No options
                .build());

        // String field (strict) with dropdown suggestion provider
        firstCat.option(Option.<String>createBuilder()
                .name(localized("option", "first.strict_string_example"))
                .description(OptionDescription.of(
                        localized("option", "first.strict_string_example.tooltip")))
                .binding(Config.Options.defaultStrictStringExample,
                        () -> options.strictStringExample,
                        val -> options.strictStringExample = val)
                .controller(option -> DropdownStringControllerBuilder.create(option)
                        .values(Config.Options.strictStringExampleValues)
                        .allowAnyValue(false) // op, default false
                        .allowEmptyValue(false)) // op, default false
                .build());

        // Enum dropdown
        firstCat.option(Option.<Config.TriState>createBuilder()
                .name(localized("option", "first.enum_example"))
                .description(OptionDescription.of(
                        localized("option", "first.enum_example.tooltip")))
                .binding(Config.Options.defaultEnumExample,
                        () -> options.enumExample,
                        val -> options.enumExample = val)
                .controller(EnumDropdownControllerBuilder::create) // formatValue op
                .build());

        // Enum cycling button
        firstCat.option(Option.<Config.TriState>createBuilder()
                .name(localized("option", "first.enum_example"))
                .description(OptionDescription.of(
                        localized("option", "first.enum_example.tooltip")))
                .binding(Config.Options.defaultEnumExample,
                        () -> options.enumExample,
                        val -> options.enumExample = val)
                .controller(option -> EnumControllerBuilder.create(option)
                        .enumClass(Config.TriState.class)
                        .formatValue(val -> Component.literal(val.name()))) // op
                .build());

        // Object (in this case, string) list cycling button
        firstCat.option(Option.<String>createBuilder()
                .name(localized("option", "first.strict_string_example"))
                .description(OptionDescription.of(
                        localized("option", "first.strict_string_example.tooltip")))
                .binding(Config.Options.defaultStrictStringExample,
                        () -> options.strictStringExample,
                        val -> options.strictStringExample = val)
                .controller(option -> CyclingListControllerBuilder.create(option)
                        .values(Config.Options.strictStringExampleValues)
                        .formatValue(val -> Component.literal(val)))
                .build());

        // Second category
        ConfigCategory.Builder secondCat = ConfigCategory.createBuilder()
                .name(localized("option", "second"))
                .tooltip(localized("option", "second.tooltip")); // op

        // Collapsible list of options (in this case, strings)
        secondCat.group(ListOption.<String>createBuilder()
                .name(localized("option", "second.string_list_example"))
                .description(OptionDescription.of(
                        localized("option", "second.string_list_example.tooltip")))
                .binding(Config.Options.defaultStringListExample,
                        () -> options.stringListExample,
                        val -> options.stringListExample = val)
                .controller(StringControllerBuilder::create)
                .initial(Config.Options.defaultStringListExampleValue)
                .minimumNumberOfEntries(1) // op
                .maximumNumberOfEntries(10) // op
                .insertEntriesAtEnd(false) // op, default false
                .collapsed(false) // op, default false
                .build());

        // Third category
        ConfigCategory.Builder thirdCat = ConfigCategory.createBuilder()
                .name(localized("option", "third"))
                .tooltip(localized("option", "third.tooltip")); // op

        // Multiline text
        thirdCat.option(LabelOption.createBuilder()
                .lines(List.of(localized("option", "third.message")))
                .build());

        // Action button
        thirdCat.option(ButtonOption.createBuilder()
                .name(localized("option", "third.action"))
                .description(OptionDescription.of(
                        localized("option", "third.action.tooltip")))
                .action((screen, option) -> {
                    screen.finishOrSave();
                    screen.onClose();
                    Minecraft.getInstance().setScreen(ClothScreenProvider.getConfigScreen(parent));
                })
                .text(localized("option", "third.action.text")) // op
                .build());

        // Collapsible group of options
        OptionGroup.Builder thirdCatFirstGroup = OptionGroup.createBuilder()
                .name(localized("option", "third.first"))
                .description(OptionDescription.of(
                        localized("option", "third.first.tooltip")))
                .collapsed(false); // op, default false

        // RGB hex color field with color-selection GUI
        thirdCatFirstGroup.option(Option.<Color>createBuilder()
                .name(localized("option", "third.first.rgb_example"))
                .description(OptionDescription.of(
                        localized("option", "third.first.rgb_example.tooltip")))
                .binding(fromRgb(Config.Options.defaultRgbExample),
                        () -> fromRgb(options.rgbExample),
                        val -> options.rgbExample = toRgb(val))
                .controller(option -> ColorControllerBuilder.create(option)
                        .allowAlpha(false)) // op, default false
                .build());

        // ARGB hex color field with color-selection GUI
        thirdCatFirstGroup.option(Option.<Color>createBuilder()
                .name(localized("option", "third.first.argb_example"))
                .description(OptionDescription.of(
                        localized("option", "third.first.argb_example.tooltip")))
                .binding(fromArgb(Config.Options.defaultArgbExample),
                        () -> fromArgb(options.argbExample),
                        val -> options.argbExample = val.getRGB())
                .controller(option -> ColorControllerBuilder.create(option)
                        .allowAlpha(true)) // op, default false
                .build());

        // Item field with dropdown
        thirdCatFirstGroup.option(Option.<Item>createBuilder()
                .name(localized("option", "third.first.item_example"))
                .description(OptionDescription.of(
                        localized("option", "third.first.item_example.tooltip")))
                .binding(asItem(Config.Options.defaultItemExample),
                        () -> asItem(options.itemExample),
                        val -> options.itemExample = asString(val))
                .controller(ItemControllerBuilder::create) // No options
                .build());

        thirdCat.group(thirdCatFirstGroup.build());

        // Assemble
        builder.category(firstCat.build());
        builder.category(secondCat.build());
        builder.category(thirdCat.build());

        YetAnotherConfigLib yacl = builder.build();
        return yacl.generateScreen(parent);
    }

    // Special option utils
    private static Item asItem(String s) {
        try {
            return BuiltInRegistries.ITEM.get(ResourceLocation.parse(s));
        } catch (ResourceLocationException e) {
            return BuiltInRegistries.ITEM.get(BuiltInRegistries.ITEM.getDefaultKey());
        }
    }

    private static String asString(Item i) {
        return BuiltInRegistries.ITEM.getKey(i).toString();
    }

    private static Color fromRgb(int i) {
        return new Color((i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF);
    }

    private static int toRgb(Color c) {
        // java.awt.Color::getRGB includes the alpha channel
        return (c.getRed() << 16) + (c.getGreen() << 8) + c.getBlue();
    }

    private static Color fromArgb(int i) {
        return new Color((i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF, (i >> 24) & 0xFF);
    }

    /**
     * Creates a new YACL screen and switches to it.
     *
     * <p>Intended for use by {@link ButtonOption} instances that modify the
     * config, such as by adding or removing config objects configured by
     * {@link OptionGroup} instances.</p>
     *
     * @param screen the current screen.
     * @param parent the current screen's parent.
     */
    private static void reload(YACLScreen screen, Screen parent) {
        int tab = screen.tabNavigationBar == null ? 0
                : screen.tabNavigationBar.getTabs().indexOf(screen.tabManager.getCurrentTab());
        if (tab == -1) tab = 0;
        screen.finishOrSave();
        screen.onClose(); // In case finishOrSave doesn't close it.
        YACLScreen newScreen = (YACLScreen)ConfigScreenProvider.getConfigScreen(parent);
        try {
            newScreen.tabNavigationBar.selectTab(tab, false);
        } catch (IndexOutOfBoundsException e) {
            Framework.LOG.warn("YACL reload hack attempted to select tab {} but max index was {}",
                    tab, newScreen.tabNavigationBar.getTabs().size() - 1);
        }
        Minecraft.getInstance().setScreen(newScreen);
    }
}
