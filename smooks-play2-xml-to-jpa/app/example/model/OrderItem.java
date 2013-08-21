package example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

@Entity
public class OrderItem extends Model {
	
	@Id 
	public Long id;
	
    public Long productId;
    public int quantity;
    public double price;
    
    @ManyToOne
    public CustomerOrder customerOrder;
}
