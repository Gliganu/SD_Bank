package domainLayer;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue
	private long idNumber;

	@Min(value = 0)
	private int moneyAmount;
	private long creationDate;

	@JoinColumn(name = "username")
	@ManyToOne()
	private User user;

	public Account() {

	}

	public Account(int moneyAmount, User user) {
		this.creationDate = System.currentTimeMillis();
		this.moneyAmount = moneyAmount;
		this.user = user;
	}

	public long getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(long idNumber) {
		this.idNumber = idNumber;
	}

	public int getMoneyAmount() {
		return moneyAmount;
	}

	public void setMoneyAmount(int moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	public long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (creationDate ^ (creationDate >>> 32));
		result = prime * result + (int) (idNumber ^ (idNumber >>> 32));
		result = prime * result + moneyAmount;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Account other = (Account) obj;
		if (creationDate != other.creationDate)
			return false;
		if (idNumber != other.idNumber)
			return false;
		if (moneyAmount != other.moneyAmount)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [idNumber=" + idNumber + ", moneyAmount=" + moneyAmount + ", creationDate=" + creationDate
				+ ", user=" + user + "]";
	}

}
