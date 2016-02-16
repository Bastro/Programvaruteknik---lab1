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
	}

	public String getTitle() {
		return "Unit: "  + xData.getUnit() + ", Unit: " + yData.getUnit();
	}
	
	public DataCollection getResult() {
		Map<String, List <MatchedDataPair>> resultData = checkMatchedDataPair();
		
		for (Entry<String, List<MatchedDataPair>> values : resultData.entrySet()) {
			List<MatchedDataPair> list = values.getValue();
			double sumX = 0.0;
			double sumY = 0.0;
			int counter = 0;
			
			for (MatchedDataPair pair: list) {
				sumX = pair.getxValue();
				sumY = pair.getyValue();
				counter++;
			}
			
			double averageX = (sumX / counter);
			double averageY = (sumY / counter);
			finalResult.put(values.getKey(), new MatchedDataPair(averageX, averageY));
	    }
	
		DataCollection dc = new DataCollection(getTitle(), xData.toString(), yData.toString(), finalResult);
		
		return dc;
	}
	
	private Map<String, List <MatchedDataPair>> checkMatchedDataPair() {
		Map<String, List <MatchedDataPair>> resultData = new HashMap<String, List <MatchedDataPair>>();
		
		for (Entry<LocalDate, Double> xValue : xData.getData().entrySet()) {
		    for (Entry<LocalDate, Double> yValue : yData.getData().entrySet()) {
		    	if (getLocalDate(xValue.getKey()).equals(getLocalDate(yValue.getKey()))) {
		    		if (resultData.get(getLocalDate(xValue.getKey())) != null) {
		    			List<MatchedDataPair> matchedPair = resultData.get(getLocalDate(xValue.getKey()));
		    			matchedPair.add(new MatchedDataPair(xValue.getValue(), yValue.getValue()));
		    		} else {
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
				+ "-" + localdate.getDayOfWeek();
				break;
			case DAY:
				date = localdate.getYear() + "-" + ((localdate.getMonthValue() / 3) + 1) + "-" + localdate.getMonthValue()
				+ "-" + localdate.getDayOfWeek() + "-" + localdate.getDayOfYear();
				break;
			default:
				date = localdate.getYear() + "";
				break;
		}
		return date;
	}
}
