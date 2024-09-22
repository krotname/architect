package name.krot.spacebatleserver.action;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class RotateCommand implements Command {

    private Rotatable rotatable;
    private int deltaAngular;
//    private int instantaneousVelocityVector;  // todo меняет вектор мгновенной скорости

    @Override
    public void execute() {
        int resultAngular = rotatable.getAngular() + deltaAngular;

        if (resultAngular > 359) {
            resultAngular -= 360;
        }
        if (resultAngular < 0) {
            resultAngular += 360;
        }

        rotatable.setAngular(resultAngular);
    }

    public static RotateCommand createCommand(Rotatable rotatable, int deltaAngular) {
        return new RotateCommand(rotatable, deltaAngular);
    }
}
