package tel_ran.generation.events;

import tel_ran.collections.Array;

public class Distribution {
	Array events = new Array();
	
	static public int getRandomNumber(int min, int max){
		if(max < min){
			int tmp = min;
			min = max;
			max = tmp;
		}                 //return float from 0 to 1
		return (int)(min + Math.random() * (max - min + 1));
	}
	public void addEvent(Event event){
		events.add(event);
	}
	public Event getEvent(){
		//
		fixDistribution();
		
		//border граница события
		int nEvents = events.size() - 1;
		int prob = getRandomNumber(0, 100);
		for(int i = 0, border = ((Event)events.get(0)).getProbability(); i < nEvents; 
				border += ((Event)events.get(++i)).getProbability()){
			if(prob < border)
				return (Event)events.get(i);
		}
		
		return (Event)events.get(nEvents);
	}
	private void fixDistribution() {
		int size = events.size();
		int sumProbability = 0;
		for(int i = 0; i < size; i++){
			sumProbability += ((Event)events.get(i)).getProbability();
		}
		float k = (float)100 / sumProbability;
		int newSumProbability = 0;
		for(int i = 0; i < size - 1; i++){
			int probNew = (int)(((Event)events.get(i)).getProbability() * k);
			((Event)events.get(i)).setProbability(probNew);
			newSumProbability += probNew;
		}
		int probNew = 100 - newSumProbability;
		((Event)events.get(size - 1)).setProbability(probNew);
	}
	public Event getEventOfIndex(int i) {
		return (Event)events.get(i);
	}
}
