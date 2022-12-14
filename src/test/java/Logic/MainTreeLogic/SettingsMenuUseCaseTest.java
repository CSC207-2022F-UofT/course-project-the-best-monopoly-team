package Logic.MainTreeLogic;

import Entities.State;
import Logic.MainTreeNodeLogic.SettingsMenuUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SettingsMenuUseCaseTest {

    @Test
    public void testSettingsMenuUseCaseCreateState(){
        SettingsMenuUseCase settingsMenuUseCase = new SettingsMenuUseCase();
        State actual = settingsMenuUseCase.create_state(0);
        Assertions.assertEquals(actual.getId(), "Settings Menu");
        Assertions.assertTrue(actual.isBackEnable());
    }

}
