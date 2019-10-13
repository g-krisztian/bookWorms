package Enities;

import java.util.List;

import lombok.Builder;

@Builder
public class CustomerEntity {

	private final UserDataEntity userData;
	private final List<BorrowEntity> borrows;
	private final List<BookEntity> subscriptions;
	private final Boolean active;

	public CustomerEntity(UserDataEntity userData, List<BorrowEntity> borrows, List<BookEntity> subscriptions,
			Boolean active) {
		super();
		this.userData = userData;
		this.borrows = borrows;
		this.subscriptions = subscriptions;
		this.active = active;
	}

	public UserDataEntity getUserData() {
		return userData;
	}

	public List<BorrowEntity> getBorrows() {
		return borrows;
	}

	public List<BookEntity> getSubscriptions() {
		return subscriptions;
	}

	public Boolean isActive() {
		return active;
	}

}
