import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    @ExtendWith(MockitoExtension.class)
    @Test
    void constructorThrowsExceptionWhenNull() {

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorThrowsExceptionEmptyList() {

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }


    @Test
    void getHorses() {
        List<Horse> expectedHorses = new ArrayList<>();
        for (int i = 0; i <= 30; i++) {
            expectedHorses.add(new Horse("" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(expectedHorses);
        assertEquals(expectedHorses, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i <= 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse hors : horses) {
            Mockito.verify(hors).move();
        }
    }

    @Test
    void getWinner(){
        Horse horse1 = new Horse("A", 5, 8.4);
        Horse horse2 = new Horse("B", 12, 20.4);
        Horse horse3 = new Horse("C", 8, 15.1);

        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3));
        assertSame(horse2, hippodrome.getWinner());
    }




}