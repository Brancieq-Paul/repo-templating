package org.example.commands;

import org.example.commands.template.TemplateInitCommand;
import org.example.commands.template.TemplateResetCommand;
import org.example.commands.template.TemplateSaveCommand;
import org.example.commands.template.add.TemplateAddCommand;
import org.example.commands.template.apply.TemplateApplyCommand;
import org.example.picocli.PropertiesFileVersionProvider;
import picocli.CommandLine;

@CommandLine.Command(name = "template", subcommands = {TemplateInitCommand.class, TemplateAddCommand.class,
        TemplateSaveCommand.class, TemplateResetCommand.class, TemplateApplyCommand.class},
        mixinStandardHelpOptions = true, versionProvider = PropertiesFileVersionProvider.class)
public class TemplateCommand {
}
