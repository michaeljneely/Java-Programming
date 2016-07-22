package warehouse;

public class Part extends Component {

	private CatalogueEntry entry;
	
	public Part(CatalogueEntry e){
		this.entry = e;
	}
	
	@Override
	public double cost() {
		return this.entry.getCost();
	}
	
	public String getName(){
		return this.entry.getName();
	}
	
	public long getNumber(){
		return this.entry.getNumber();
	}

}
