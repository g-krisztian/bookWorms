package transformers;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookworms.library.domain.Customer;

import Enities.CustomerEntity;

public class CustomerTransformer {

	@Autowired
	private UserDataTransformer userDataTransformer;
	@Autowired
	private BorrowTransformer borrowTransformer;

	@Autowired
	private BookTransformer bookTransformer;

	public CustomerEntity transform(Customer customer) {
		return new CustomerEntity(userDataTransformer.transform(customer.getUserData()),
				borrowTransformer.transform(customer.getBorrows()),
				bookTransformer.transform(customer.getSubscriptions()), customer.getActive());
	}

	public Customer transform(CustomerEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
