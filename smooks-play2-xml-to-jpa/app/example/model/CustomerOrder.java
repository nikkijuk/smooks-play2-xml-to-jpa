package example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.ebean.Model;

@Entity
public class CustomerOrder extends Model {

	@Id
	public Long id;
	
	/**
	 * Cascades saves, which means dependant object will be saved as main object is saved.
	 */
	@OneToOne (cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	public Header header;

	/** Saves on collections are cascaded.  
	 * Fetches are eager, which might result some extra access to db.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customerOrder", fetch=FetchType.EAGER)
	public List<OrderItem> orderItems = new ArrayList<OrderItem> ();
	
	/**
	 * find static member variable to help searches
	 */
	public static Finder<Long, CustomerOrder> find = new Finder<Long, CustomerOrder>(
			Long.class, CustomerOrder.class);

	
}
