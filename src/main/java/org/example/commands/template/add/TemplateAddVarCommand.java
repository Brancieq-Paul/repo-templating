package org.example.commands.template.add;

import org.example.Consts;
import org.example.FileUtils;
import org.example.commands.template.AbsTemplateCommand;
import org.example.commands.template.TemplateInitCommand;
import org.example.picocli.PropertiesFileVersionProvider;
import picocli.CommandLine;

@CommandLine.Command(name = "var", mixinStandardHelpOptions = true, versionProvider = PropertiesFileVersionProvider.class)
public class TemplateAddVarCommand extends AbsTemplateCommand {
    @CommandLine.Option(names = {"-k", "--key"}, required = true)
    String key;

    @CommandLine.Option(names = {"-v", "--value"}, defaultValue = "")
    String value;

    @Override public Integer call() {
        System.out.println("Init if needed");
        TemplateInitCommand.init(root);
        System.out.println("Add var placeholder");
        if (value.isBlank()) {
            value = "{{" + key + "}}";
        }
        FileUtils.addToJsonObject(root.resolve(Consts.varsJsonPath).toFile(), key, value);
        return 0;
    }
}
