package name.krot.spacebatleserver.action;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@RequiredArgsConstructor
public class LogExceptionCommand implements Command{ //4
    private final Exception exception;

    @Override
    public void execute() {
        log.info(exception.getMessage());
    }
}
