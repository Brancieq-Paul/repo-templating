package org.example.commands.template.apply;

import org.example.commands.template.add.TemplateAddSectionCommand;
import org.example.commands.template.add.TemplateAddTemplatedFileCommand;
import org.example.commands.template.add.TemplateAddVarCommand;
import org.example.picocli.PropertiesFileVersionProvider;
import picocli.CommandLine;

@CommandLine.Command(name = "apply", subcommands = {TemplateApplyVarsCommand.class,
        TemplateApplySectionsCommand.class}, mixinStandardHelpOptions = true, versionProvider = PropertiesFileVersionProvider.class)
public class TemplateApplyCommand {
}

