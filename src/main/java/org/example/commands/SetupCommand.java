package org.example.commands;

import org.example.commands.template.AbsTemplateCommand;
import org.example.commands.template.TemplateInitCommand;
import org.example.commands.template.TemplateSaveCommand;
import org.example.commands.template.apply.TemplateApplySectionsCommand;
import org.example.commands.template.apply.TemplateApplyVarsCommand;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "setup", description = "Main command to use. Setup the repository with specified sections and variables.")
public class SetupCommand extends AbsTemplateCommand {
    @Override public Integer call() {
        System.out.println("Init if needed");
        TemplateInitCommand.init(root);
        System.out.println("Save template if needed");
        TemplateSaveCommand.saveIfNeeded(root);
        System.out.println("Apply sections");
        TemplateApplySectionsCommand.applySections(root);
        System.out.println("Apply vars");
        TemplateApplyVarsCommand.applyVars(root);
        System.out.println("Done !");
        return 0;
    }
}
