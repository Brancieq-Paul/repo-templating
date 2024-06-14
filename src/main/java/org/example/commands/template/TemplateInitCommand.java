package org.example.commands.template;

import org.example.Consts;
import org.example.FileUtils;
import org.example.picocli.PropertiesFileVersionProvider;
import picocli.CommandLine;

import java.nio.file.Path;

@CommandLine.Command(name = "init", mixinStandardHelpOptions = true, versionProvider = PropertiesFileVersionProvider.class)
public class TemplateInitCommand extends AbsTemplateCommand {
    @Override public Integer call() {
        System.out.println("Init repo templating");
        init(root);
        System.out.println("Done !");
        return 0;
    }

    public static void init(Path root) {
        FileUtils.ensureJsonObject(root.resolve(Consts.varsJsonPath).toFile());
        FileUtils.ensureJsonObject(root.resolve(Consts.sectionsJsonPath).toFile());
        FileUtils.ensureJsonArray(root.resolve(Consts.templatedPath).toFile());
    }
}
