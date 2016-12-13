import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class CodeJam {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Integer minCommonTag = Integer.valueOf(sc.nextLine());

		HashMap<String, Set<String>> cityKeymap = new HashMap<>();
		while (sc.hasNextLine() && sc.hasNext()) {
			String line = sc.nextLine();
			String split[] = line.split(":");
			String dest = split[0];
			String tags[] = split[1].split(",");

			Set<String> tagsSet = new HashSet<>();
			for (String tag : tags) {
				tagsSet.add(tag);
			}
			cityKeymap.put(dest, tagsSet);
		}
		//
		// LinkedHashMap<Integer, String> sortedMap =
		// map.entrySet().stream().sorted(Entry.comparingByValue()).collect(
		// Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1,
		// LinkedHashMap::new));
		//
		// Iterator<String> iter = map.keySet().iterator();
		// while ( iter.hasNext() ) {
		// Set<String> destList = map.get(iter.next());
		// if ( destList.size() == 1 ) {
		// iter.remove();
		// }
		// }
		HashMap<String, Set<String>> reverseMap = new HashMap<>();
		for (Iterator<String> i1 = cityKeymap.keySet().iterator(); i1.hasNext();) {
			String city = i1.next();
			Set<String> tagsSet = cityKeymap.get(city);
			for (String tag : tagsSet) {
				Set<String> citySet = reverseMap.get(tag);
				if (citySet == null) {
					citySet = new HashSet<>();
					reverseMap.put(tag, citySet);
				}
				citySet.add(city);
			}
		}

		// traverse city list
		for (String city : cityKeymap.keySet()) {
			HashMap<String, Set<String>> reverseMapForCity = new HashMap<>();
			for (String tagKey : reverseMap.keySet()) {
				Set<String> citySet = reverseMap.get(tagKey);
				if (citySet.contains(city)) {
					reverseMapForCity.put(tagKey, citySet);
				}
			}
			// tekrar geri �evirelim
			if (reverseMapForCity.size() > 0) {
				HashMap<String, Set<String>> reverseReverseMapForCity = new HashMap<>();
				for (String tagKey : reverseMapForCity.keySet()) {
					Set<String> citySet = reverseMapForCity.get(tagKey);
					for (String cityName : citySet) {
						Set<String> tagsSet = reverseReverseMapForCity.get(cityName);
						if (tagsSet == null) {
							tagsSet = new HashSet<>();
							reverseReverseMapForCity.put(cityName, tagsSet);
						}
						tagsSet.add(tagKey);
					}
				}

				// eleme islemi ve main city bulma i�lemi
				Iterator<Entry<String,Set<String>>> iter = reverseReverseMapForCity.entrySet().iterator();
				while(iter.hasNext()){
					Entry<String,Set<String>> entry = iter.next();
					if (reverseReverseMapForCity.get(entry.getKey()).size() < minCommonTag) {
						iter.remove();
					}
				}
				if(reverseReverseMapForCity.size() > 0 && reverseReverseMapForCity.containsKey(city)){
					Set<String> tagSet = reverseReverseMapForCity.remove(city);
					Set<String> citySet1=  reverseReverseMapForCity.keySet();
					
					for (String city1 : citySet1) {
						Set<String> cityList = new HashSet<>();
						cityList.add(city1);
						cityList.add(city);
						Set<String> citySet2 =  reverseReverseMapForCity.keySet();
						Set<String>  tags1 = reverseReverseMapForCity.get(city1);
						for (String city2 : citySet2) {
							if(!city1.equals(city2)){
								Set<String>  tags2 = reverseReverseMapForCity.get(city2);
								if(tags1.containsAll(tags2)){									
									cityList.add(city2);
								}
							}
						}
						System.out.println(cityList.toString()+" - "+reverseReverseMapForCity.get(city1));
					}
				}
			}
			for(String s : reverseMap.keySet()){
				Set<String> citySet = reverseMap.get(s);
				citySet.remove(city);
			}

		}

	}
}
