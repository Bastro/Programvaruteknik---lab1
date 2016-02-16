package hig.jonasoster.inl1;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DataSourceImp implements DataSource {
	private String name;
	private String unit;
	private Map<LocalDate, Double> data;
	
	public DataSourceImp(String name, String unit) {
		this.name = name;
		this.unit = unit;
		data = new HashMap<LocalDate, Double>();
	}
	
	public DataSourceImp(String name, String unit, Map<LocalDate, Double> data) {
		this.name = name;
		this.unit = unit;
		this.data = data;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getUnit() {
		return unit;
	}
	
	public void addData(LocalDate date, Double value) {
		data.put(date, value);
	}

	@Override
	public Map<LocalDate, Double> getData() {
		return data;
	}

}
