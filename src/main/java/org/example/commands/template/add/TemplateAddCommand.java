package org.example.commands.template.add;

import org.example.picocli.PropertiesFileVersionProvider;
import picocli.CommandLine;

@CommandLine.Command(name = "add", subcommands = {TemplateAddTemplatedFileCommand.class, TemplateAddVarCommand.class,
        TemplateAddSectionCommand.class}, mixinStandardHelpOptions = true, versionProvider = PropertiesFileVersionProvider.class)
public class TemplateAddCommand {
}

