package org.example.commands.template.add;

import org.example.Consts;
import org.example.FileUtils;
import org.example.commands.template.AbsTemplateCommand;
import org.example.commands.template.TemplateInitCommand;
import org.example.picocli.PropertiesFileVersionProvider;
import picocli.CommandLine;

@CommandLine.Command(name = "section", mixinStandardHelpOptions = true, versionProvider = PropertiesFileVersionProvider.class)
public class TemplateAddSectionCommand extends AbsTemplateCommand {
    @CommandLine.Option(names = {"-k", "--key"}, required = true)
    String key;

    @Override public Integer call() {
        System.out.println("Init if needed");
        TemplateInitCommand.init(root);
        System.out.println("Add section file if not exist");
        FileUtils.fileShouldExist("repo_templating/sections/" + key + ".txt");
        System.out.println("Add section boolean selection");
        FileUtils.addToJsonObject(root.resolve(Consts.sectionsJsonPath).toFile(), key, false);
        return 0;
    }
}
