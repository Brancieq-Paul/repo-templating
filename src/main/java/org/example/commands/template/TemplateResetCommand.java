package org.example.commands.template;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.example.Consts;
import org.example.FileUtils;
import org.example.picocli.PropertiesFileVersionProvider;
import picocli.CommandLine;

import java.io.File;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@CommandLine.Command(name = "reset", mixinStandardHelpOptions = true, versionProvider = PropertiesFileVersionProvider.class)
public class TemplateResetCommand extends AbsTemplateCommand {
    @Override public Integer call() {
        System.out.println("Init repo templating if needed");
        TemplateInitCommand.init(root);
        System.out.println("Reset template");
        reset(root);
        System.out.println("Done !");
        return 0;
    }

    public static void reset(Path root) {
        JsonArray fileStringJsonArray = FileUtils.readJsonArray(root.resolve(Consts.templatedPath).toFile());
        Stream<JsonElement> stream = StreamSupport.stream(fileStringJsonArray.spliterator(), true);
        stream.filter(value -> value.isJsonPrimitive() && value.getAsJsonPrimitive().isString()).forEach(value -> {
                    File file = new File(root.resolve(value.getAsString()).toString());
                    File savedFile = new File(root.resolve(Consts.savePath.toString()).resolve(value.getAsString()).toString());
                    if (!savedFile.exists()) {
                        System.out.println(file.getPath() + " can not be reset because it does not exist in save");
                        return;
                    }
                    FileUtils.fileShouldExist(file);
                    FileUtils.writeFile(file, FileUtils.readFile(savedFile));
                }
        );
    }
}
