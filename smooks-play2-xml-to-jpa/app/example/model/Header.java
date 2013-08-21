package example.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Header extends Model {
	
	@Id
	public Long id;
	
	public Date date;
	public long customerNumber;
	public String customerName;
}
