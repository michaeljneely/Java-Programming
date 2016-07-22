package warehouse;

import java.util.ArrayList;

public class Assembly extends Component {

	private ArrayList<Component>components;
	
	public Assembly(){
		this.components = new ArrayList<Component>();
	}
	
	public void add(Component c){
		this.components.add(c);
	}
	
	@Override
	public double cost() {
		double cost = 0.0;
		for(Component comp : components){
			cost += comp.cost();
		}
		return cost;
	}

}
