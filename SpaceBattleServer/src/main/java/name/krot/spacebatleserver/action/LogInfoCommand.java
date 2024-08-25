package name.krot.spacebatleserver.action;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Builder
@RequiredArgsConstructor
@Slf4j
public class LogInfoCommand implements Command{

    private final String massage;

    @Override
    public void execute() {
      log.info(massage);
    }
}
