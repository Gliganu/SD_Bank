package domainLayer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.NumberFormat;

@Entity
public class MoneyTransfer {

	@Id
	@GeneratedValue
	private int id;
	
	private int fromAccountId;
	private int toAccountId;
	
	@Min(value = 0)
	private int sum;
	
	
	public MoneyTransfer() {
	
	}
	
	
	
	public MoneyTransfer(int fromAccountId, int toAccountId, int sum) {
		super();
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.sum = sum;
	}



	public int getFromAccountId() {
		return fromAccountId;
	}
	public void setFromAccountId(int fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	public int getToAccountId() {
		return toAccountId;
	}
	public void setToAccountId(int toAccountId) {
		this.toAccountId = toAccountId;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	@Override
	public String toString() {
		return "MoneyTransfer [fromAccountId=" + fromAccountId + ", toAccountId=" + toAccountId + ", sum=" + sum + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fromAccountId;
		result = prime * result + sum;
		result = prime * result + toAccountId;
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
		MoneyTransfer other = (MoneyTransfer) obj;
		if (fromAccountId != other.fromAccountId)
			return false;
		if (sum != other.sum)
			return false;
		if (toAccountId != other.toAccountId)
			return false;
		return true;
	}
	
	
	
}
