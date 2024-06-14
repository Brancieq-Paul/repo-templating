package org.example;

import org.example.commands.SetupCommand;
import org.example.commands.TemplateCommand;
import org.example.commands.template.TemplateResetCommand;
import org.example.commands.template.TemplateSaveCommand;
import org.example.picocli.PropertiesFileVersionProvider;
import picocli.CommandLine;

@CommandLine.Command(name = "app", mixinStandardHelpOptions = true, versionProvider = PropertiesFileVersionProvider.class, subcommands = {
        HelloCommand.class, ListFilesCommand.class, TemplateCommand.class, TemplateSaveCommand.class, TemplateResetCommand.class, SetupCommand.class })
public class Main {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }
}
