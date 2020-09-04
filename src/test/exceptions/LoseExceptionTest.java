package exceptions;

import org.junit.jupiter.api.Test;

public class LoseExceptionTest {

    @Test
    public void testLoseException() {
        try {
            throw new LoseException();
        } catch (Exception e) {

        }
    }
}
