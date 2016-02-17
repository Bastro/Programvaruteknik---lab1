package hig.jonasoster.inl1;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DataCollectionBuilder {
	private DataSource xData;
	private DataSource yData;
	private Resolution resolution;
	private Map<String, MatchedDataPair> finalResult;
	
	public DataCollectionBuilder(DataSource xData, DataSource yData, Resolution resolution) {
		this.xData = xData;
		this.yData = yData;
		this.resolution = resolution;
		finalResult = new HashMap<String, MatchedDataPair>();
	}

	public String getTitle() {
		return "Name: "  + xData.getName() + ", Name: " + yData.getName();
	}
	
	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}
	
	public Resolution getResolution() {
		return resolution;
	}
	
	public DataCollection getResult() {
		Map<String, List <MatchedDataPair>> resultData = checkMatchedDataPair();
		
		for (Entry<String, List<MatchedDataPair>> values : resultData.entrySet()) {
			List<MatchedDataPair> list = values.getValue();
			double sumX = 0.0;
			double sumY = 0.0;
			int counter = 0;
			
			for (MatchedDataPair pair : list) {
				sumX = sumX + pair.getxValue();
				sumY = sumY + pair.getyValue();
				counter++;
			}
			
			/*System.out.println(sumY);
			System.out.println(sumX);
			System.out.println(counter);*/
			
			finalResult.put(values.getKey(), new MatchedDataPair((sumX / counter), (sumY / counter)));
	    }
	
		DataCollection dc = new DataCollection(getTitle(), xData.toString(), yData.toString(), finalResult);
		
		return dc;
	}
	
	private Map<String, List <MatchedDataPair>> checkMatchedDataPair() {
		Map<String, List <MatchedDataPair>> resultData = new HashMap<String, List <MatchedDataPair>>();
		
		for (Entry<LocalDate, Double> xValue : xData.getData().entrySet()) {
		    for (Entry<LocalDate, Double> yValue : yData.getData().entrySet()) {	
		    	// Check if match
		    	if (getLocalDate(xValue.getKey()).equals(getLocalDate(yValue.getKey()))) {
		    		
		    		System.out.println(getLocalDate(xValue.getKey()));
		    		
		    		if (resultData.get(getLocalDate(xValue.getKey())) != null) {
		    			//System.out.println("if");
		    			List<MatchedDataPair> matchedPair = resultData.get(getLocalDate(xValue.getKey()));
		    			matchedPair.add(new MatchedDataPair(xValue.getValue(), yValue.getValue()));
		    		} else {
		    			//System.out.println("else");
		    			List<MatchedDataPair> matchedPair = new ArrayList<MatchedDataPair>();
		    			matchedPair.add(new MatchedDataPair(xValue.getValue(), yValue.getValue()));
		    			resultData.put(getLocalDate(xValue.getKey()), matchedPair);
		    		}
		    	}
		    }
		}
		return resultData;
	}
	
	private String getLocalDate(LocalDate localdate) {
		String date;
		
		switch (resolution) {
			case YEAR:
				date = localdate.getYear() + "-";
				break;
			case QUARTER:
				date = localdate.getYear() + "-" + ((localdate.getMonthValue() / 3) + 1);
				break;
			case MONTH:
				date = localdate.getYear() + "-" + ((localdate.getMonthValue() / 3) + 1) + "-" + localdate.getMonthValue();
				break;
			case WEEK:
				date = localdate.getYear() + "-" + ((localdate.getMonthValue() / 3) + 1) + "-" + localdate.getMonthValue()
				+ "-";
				break;
			case DAY:
				date = localdate.getYear() + "-" + ((localdate.getMonthValue() / 3) + 1) + "-" + localdate.getMonthValue()
				+ "-" + localdate.getDayOfYear();
				break;
			default:
				date = localdate.getYear() + "";
				break;
		}
		return date;
	}
}
