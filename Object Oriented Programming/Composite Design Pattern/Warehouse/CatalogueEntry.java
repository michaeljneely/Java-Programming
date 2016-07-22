package warehouse;

public class CatalogueEntry {

	private String name;
	private long number;
	private double cost;
	
	public CatalogueEntry(String nm, long num, double cst){
		this.name = nm;
		this.number = num;
		this.cost = cst;
	}

	public String getName() {
		return name;
	}

	public long getNumber() {
		return number;
	}

	public double getCost() {
		return cost;
	}
}
