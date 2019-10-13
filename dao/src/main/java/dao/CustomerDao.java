package dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookworms.library.domain.Customer;

import Enities.CustomerEntity;
import transformers.CustomerTransformer;

public class CustomerDao {
	@Autowired
	private CustomerTransformer customerTransformer;

	public Customer createCustomer(Customer customer) {
		CustomerEntity entity = customerTransformer.transform(customer);
		// TODO persist
		return customerTransformer.transform(entity);
	}
}
