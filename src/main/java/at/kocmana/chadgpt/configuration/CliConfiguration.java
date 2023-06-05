package at.kocmana.chadgpt.configuration;

import at.kocmana.chadgpt.ChatController;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.command.annotation.CommandScan;
import org.springframework.shell.command.annotation.EnableCommand;

@EnableCommand(ChatController.class)
@CommandScan(basePackageClasses = ChatController.class)
@Configuration
public class CliConfiguration {
}