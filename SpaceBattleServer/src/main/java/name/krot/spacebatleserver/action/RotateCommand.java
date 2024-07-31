package name.krot.spacebatleserver.action;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RotateCommand implements Command{

    private final Rotatable rotatable;
    private final int angular;

    @Override
    public void execute() {
        rotatable.setAngular(angular);
    }
}
