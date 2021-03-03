package GuiModule;

import demos.Airport;

public class LinearSearch {
	public String search(String toFind,Airport[] airports) {
		int i=0;
		while(i<airports.length) {
			if(toFind.contentEquals(airports[i].getCity())) {
				Object x=airports[i].getCode3();
				return x.toString();
			}
			i++;
		}
		return "No city Found";
	}
}
