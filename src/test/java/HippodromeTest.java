import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {
    @Test
   public void nullArgumentInConstructor() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }
    @Test
   public void emptyListInConstructorException() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>())
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
   public void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30 ; i++) {
            horses.add(new Horse("Horse"+i, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
   public void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse hors : horses) {
            verify(hors).move();
        }
    }
    @Test
    void getWinner() {
        Horse horse = new Horse("Horse", 3, 3);
        Horse horse1 = new Horse("Horse", 3, 4);
        Horse horse2 = new Horse("Horse", 3, 5);
        Horse horse3 = new Horse("Horse", 3, 6);
        Horse horse4 = new Horse("Horse", 3, 7);
        Hippodrome hippodrome = new Hippodrome(List.of(horse,horse1, horse2, horse3, horse4));
        assertSame(horse4, hippodrome.getWinner());

    }
}