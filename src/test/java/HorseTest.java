import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {
    @Test
    void constructorThrowsExceptionWhenFirstParameterIsNull() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(null, 0, 0);
                }
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    void constructorThrowsExceptionWhenFirstParameterIsEmpty(String name) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(name, 0, 0);
                }
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorThrowsExceptionWhenSecondParameterIsNegativeNumber() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("Name", -1, 0);
                }
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorThrowsExceptionWhenThirdParameterIsNegativeNumber() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("Name", 0, -5);
                }
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
        String expected = "Name";
        Horse horse = new Horse(expected, 0, 0);
        String actual = horse.getName();
        assertEquals(expected, actual);
    }

    @Test
    void getSpeed() {
        double expected = 0;
        Horse horse = new Horse("Name", 0, 0);
        double actual = horse.getSpeed();
        assertEquals(expected, actual);
    }

    @Test
    void getDistance() {
        double expected = 0;
        Horse horse = new Horse("Name", 0, 0);
        double actual = horse.getDistance();
        assertEquals(expected, actual);
    }

    @Test
    void getDistanceReturnsZero() {
        Horse horse = new Horse("Name", 0);
        double actual = horse.getDistance();
        assertEquals(0, actual);
    }

    @Test
    void moveCallsGetRandomDouble(){
        try (MockedStatic<Horse> utilities = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Name", 0);
            horse.move();
            utilities.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.9})
    void moveCalculatesDistanceCorrectly(double randomDouble){
        Horse horse = new Horse("Name", 5);
        try (MockedStatic<Horse> utilities = Mockito.mockStatic(Horse.class)) {
            utilities.when(()-> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomDouble);
            double expectedDistance = horse.getDistance() + horse.getSpeed() * randomDouble;
            horse.move();
            double actualDistance = horse.getDistance();
            assertEquals(expectedDistance, actualDistance);
        }
    }
}