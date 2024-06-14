package org.example.commands.template;

import picocli.CommandLine;

import java.nio.file.Path;
import java.util.concurrent.Callable;

public abstract class AbsTemplateCommand implements Callable<Integer> {
    @CommandLine.Option(names = {"-r", "--root"}, description = "Root of your repository")
    protected Path root = Path.of("./");
}
