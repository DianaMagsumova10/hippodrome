import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    public void nameNullExceptionAndExceptionMassageTrue() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 5, 5)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }


    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n\n"})
    public void nameEqualsBlankSpaceOrTabAndExceptionMassageTrue(String str) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(str, 5, 5)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void speedNegativeNumberExceptionAndExceptionMassageTrue() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Maha", -5, 5)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void distanceNegativeNumberExceptionAndExceptionMassageTrue() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Maha", 5, -5)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getNameReturnString() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Masha", 5, 5);
        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);
        String nameValue = (String) name.get(horse);
        assertEquals("Masha", nameValue);

    }


    @Test
    public void getSpeedReturnInt() {
        Horse horse = new Horse("Masha", 5, 5);
        assertEquals(5, horse.getSpeed());
    }

    @Test
    public void getDistanceReturnIntOrZero() {
        Horse horse = new Horse("Masha", 5, 5);
        assertEquals(5, horse.getDistance());

        Horse horseWithoutDistance = new Horse("Masha", 5);
        assertEquals(0, horseWithoutDistance.getDistance());

    }

    @Test
    public void moveDoGetRandomDoubleWithParameter() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class);) {
            new Horse("Masha", 5, 5).move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.9, 0.3, 0.4, 0.6})
    void moveGetDistanceCalculatedByFormula(double value) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Masha", 5, 5);
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(value);
            horse.move();
            assertEquals(5 + 5 * value, horse.getDistance());

        }

    }


}