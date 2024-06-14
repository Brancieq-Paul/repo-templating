package org.example.commands.template.add;

import org.example.Consts;
import org.example.FileUtils;
import org.example.commands.template.AbsTemplateCommand;
import org.example.commands.template.TemplateInitCommand;
import org.example.picocli.PropertiesFileVersionProvider;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@CommandLine.Command(name = "templated", mixinStandardHelpOptions = true, versionProvider = PropertiesFileVersionProvider.class)
public class TemplateAddTemplatedFileCommand extends AbsTemplateCommand {
    @CommandLine.Option(names = {"-f", "--file"}, required = true)
    File file;

    @Override public Integer call() {
        System.out.println("Init if needed");
        root = root.toAbsolutePath();
        TemplateInitCommand.init(root);
        System.out.println("Add file to the list");
        try {
            FileUtils.addToJsonArray(root.resolve(Consts.templatedPath).toFile(), root.relativize(Path.of(file.getCanonicalPath())).toString(), false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Done !");
        return 0;
    }
}
