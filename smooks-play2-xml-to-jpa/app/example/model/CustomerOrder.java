package example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.ebean.Model;

@Entity
public class CustomerOrder extends Model {

	@Id
	public Long id;
	
	@OneToOne (cascade = CascadeType.ALL)
	public Header header;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customerOrder")
	public List<OrderItem> orderItems = new ArrayList<OrderItem> ();
	
	/*
	 * Find static member variable to help searches
	 */
	public static Finder<Long, CustomerOrder> find = new Finder<Long, CustomerOrder>(
			Long.class, CustomerOrder.class);

	
}
