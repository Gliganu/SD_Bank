package domainLayer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="transactions")
public class Transaction {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "from_username")
	private User fromUser;
	
	@ManyToOne
	@JoinColumn(name = "to_username")
	private User toUser;
	
	private long date;
	
	private int amount;
	
	public Transaction(){
		
	}
	
	public Transaction(User fromUser, User toUser, int amount) {
		
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.amount = amount;
		this.date = System.currentTimeMillis();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + (int) (date ^ (date >>> 32));
		result = prime * result + ((fromUser == null) ? 0 : fromUser.hashCode());
		result = prime * result + id;
		result = prime * result + ((toUser == null) ? 0 : toUser.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (amount != other.amount)
			return false;
		if (date != other.date)
			return false;
		if (fromUser == null) {
			if (other.fromUser != null)
				return false;
		} else if (!fromUser.equals(other.fromUser))
			return false;
		if (id != other.id)
			return false;
		if (toUser == null) {
			if (other.toUser != null)
				return false;
		} else if (!toUser.equals(other.toUser))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", fromUser=" + fromUser + ", toUser=" + toUser + ", date=" + date
				+ ", amount=" + amount + "]";
	}

	
	
	
	
	
	
	
	
	
	
}
