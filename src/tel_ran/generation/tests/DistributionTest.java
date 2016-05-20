package tel_ran.generation.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tel_ran.generation.events.Distribution;
import tel_ran.generation.events.Event;

public class DistributionTest {
	Distribution distribution;
	private static final int N_NUMBERS = 10000;
	private static final int MIN = 10;
	private static final int MAX = 20;
	private static final int N_TRIALS = 100;

	@Before
	public void setUp() throws Exception {
		distribution = new Distribution();
		distribution.addEvent(new EventInteger(100, 0));
		distribution.addEvent(new EventInteger(100, 1));
		distribution.addEvent(new EventInteger(100, 2));
		distribution.addEvent(new EventInteger(150, 3));
		distribution.addEvent(new EventInteger(50, 4));
	}

	@Test
	public void test() {
		for(int i = 0; i < N_NUMBERS; i++){
			int number = Distribution.getRandomNumber(MIN, MAX);
			assertTrue(number <= MAX && number >= MIN);			
		}
	}
	@Test
	public void testGetEvent(){
		int numbers[] = {0,0,0,0,0};//counters
		for(int i = 0; i < N_TRIALS; i++){
			Event event = distribution.getEvent();
			numbers[((EventInteger)event).getValue()]++;
		}
		int numLen = numbers.length;
		for(int i = 0; i < numLen ; i++){
			String str = new String();
			for(int j = 0; j < numbers[i]; j++){
				str += "*";
			}                    
			System.out.println(((EventInteger)distribution.getEventOfIndex(i)).value + 
					str + numbers[i]);
		}
	}

}
