package org.example.commands.template.apply;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.example.Consts;
import org.example.FileUtils;
import org.example.commands.template.AbsTemplateCommand;
import org.example.commands.template.TemplateInitCommand;
import org.example.commands.template.TemplateSaveCommand;
import org.example.picocli.PropertiesFileVersionProvider;
import picocli.CommandLine;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@CommandLine.Command(name = "sections", mixinStandardHelpOptions = true, versionProvider = PropertiesFileVersionProvider.class)
public class TemplateApplySectionsCommand extends AbsTemplateCommand {
    @Override public Integer call() {
        System.out.println("Init if needed");
        TemplateInitCommand.init(root);
        System.out.println("Save template if needed");
        TemplateSaveCommand.saveIfNeeded(root);
        System.out.println("Apply sections");
        applySections(root);
        return 0;
    }

    public static void applySections(Path root) {
        JsonObject sectionsSelection = FileUtils.readJsonObject(root.resolve(Consts.sectionsJsonPath).toFile());
        Map<String, String> varsMap = new HashMap<>();
        sectionsSelection.asMap().entrySet().stream().filter(entry -> entry.getValue().isJsonPrimitive()
                && entry.getValue().getAsJsonPrimitive().isBoolean()
                && entry.getValue().getAsJsonPrimitive().getAsBoolean()).forEach(
                        entry -> varsMap.put(entry.getKey(), FileUtils.readFile(
                                root.resolve(Consts.sectionsDirectoryPath).resolve(entry.getKey() + ".txt").toFile())
                        )
        );
        JsonArray templatedList = FileUtils.readJsonArray(root.resolve(Consts.templatedPath).toFile());
        Stream<JsonElement> stream = StreamSupport.stream(templatedList.spliterator(), true);
        stream.filter(value -> value.isJsonPrimitive() && value.getAsJsonPrimitive().isString()).forEach(value ->
                FileUtils.replacePlaceholdersInFile(new File(value.getAsJsonPrimitive().getAsString()), varsMap)
        );
    }
}
