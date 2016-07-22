package warehouse;

public class Service extends Component {

	private CatalogueEntry entry;
	
	public Service(CatalogueEntry e){
		this.entry = e;
	}
	
	public String getName(){
		return this.entry.getName();
	}
	
	public long getNumber(){
		return this.entry.getNumber();
	}
	
	@Override
	public double cost() {
		return this.entry.getCost();
	}

}
