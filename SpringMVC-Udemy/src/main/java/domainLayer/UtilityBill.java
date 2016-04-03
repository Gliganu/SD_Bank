package domainLayer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bills")
public class UtilityBill {

	

	@Id
	@GeneratedValue
	private long idNumber;
	
	@JoinColumn(name = "username")
	@ManyToOne()
	private User user;
	
	
	private int ammountToPay;
	
	public UtilityBill() {
		
	}
	
	

	public UtilityBill(User user, int ammountToPay) {
		this.user = user;
		this.ammountToPay = ammountToPay;
	}


	

	public long getIdNumber() {
		return idNumber;
	}



	public void setIdNumber(long idNumber) {
		this.idNumber = idNumber;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getAmmountToPay() {
		return ammountToPay;
	}

	public void setAmmountToPay(int ammountToPay) {
		this.ammountToPay = ammountToPay;
	}

	@Override
	public String toString() {
		return "UtilityBill [idNumber=" + idNumber + ", user=" + user + ", ammountToPay=" + ammountToPay + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ammountToPay;
		result = prime * result + (int) (idNumber ^ (idNumber >>> 32));
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
		UtilityBill other = (UtilityBill) obj;
		if (ammountToPay != other.ammountToPay)
			return false;
		if (idNumber != other.idNumber)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	};
	
	
	
	
}
