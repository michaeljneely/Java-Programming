package wallet;

public class Converter {
	
	String[] currencies = {"AUD","USD","GBP","CAD"};
	double[] rates = {0.93, 0.66, 1.4,0.7};
	
	/*
	 * This method takes a currency object as a parameter 
	 * it looks ups its rate of exchange against the euro
	 * and then sets its euro value 
	 * 
	*/
	public void setEuroValue(Currency curr){
		String currency = curr.getCurrencyName();
		double rate = find(currency);
		double value = curr.getAmount() * rate;
		curr.setEuroValue(value);
	}
	
	
	/*
	 * This method takes a currency name as a parameter
	 * It returns its rate of exchange against the euro 
	 * 
	*/
	private double find(String currencyName){
		for (int i = 0; i< currencies.length; i++){
			if(currencies[i].equals(currencyName)){
				return rates[i];
			}
		}
		return -1;
	}
	
}
