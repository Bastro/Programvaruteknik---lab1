package hig.jonasoster.inl1;

import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {

		LocalDate date = LocalDate.of(2005, 13, 15);
		
		DataSourceImp dsi = new DataSourceImp("Temperature", "C");
		dsi.addData(date, 2.0);
		double test = dsi.getData().get(date);
		System.out.println(test);
	}

}
