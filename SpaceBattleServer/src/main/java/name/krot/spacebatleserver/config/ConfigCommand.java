package name.krot.spacebatleserver.config;

import name.krot.spacebatleserver.action.Command;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class ConfigCommand {

    @Bean
    public Consumer<Command> command(){
        return Command::execute;
    }
}
