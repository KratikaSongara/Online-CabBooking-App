package com.masai.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.Admin;
import com.masai.entity.Cab;
import com.masai.entity.Customer;
import com.masai.entity.Driver;
import com.masai.entity.TripBooking;
import com.masai.exception.AdminExceptions;
import com.masai.exception.NotFoundException;
import com.masai.repository.AdminDao;
import com.masai.repository.CabDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.DriverDao;

import com.masai.repository.TripDao;


@Service
public class AdminServiceImp implements AdminService {
	@Autowired
	private AdminDao adminDao;

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired 
	private DriverDao driverDao;
	
	@Autowired
	private CabDao cabDao;
	
	@Autowired
	private TripDao tripDao;

	@Override
	public Admin saveAdmin(Admin admin) throws AdminExceptions {
		System.out.println(admin);
		return adminDao.save(admin);
	}

	@Override
	public Admin update(Admin admin) throws AdminExceptions {
		Optional<Admin> opt = adminDao.findById(admin.getUserId());
		if (opt.isPresent()) {
			//Admin existAdmin = opt.get();
			return adminDao.save(admin);
		}
		throw new AdminExceptions("Invalid Id");
	}

	@Override
	public Admin delete(Integer id) throws AdminExceptions {
		Admin existingAdmin = adminDao.findById(id).orElseThrow(() -> new AdminExceptions("Invalid Id"));
		adminDao.delete(existingAdmin);

		return existingAdmin;
	}

	@Override //all trips detail of customer
	public List<TripBooking> getAllTrips(Integer customerid) throws AdminExceptions {
		//customer exception
		Optional<Customer> opt = customerDao.findById(customerid);
		if(opt.isPresent()) {
			List<TripBooking> trips = tripDao.findAll();
			return trips;
		
		}
		throw new AdminExceptions("Invalid Id");
	}

	@Override

	public List<TripBooking> getTripsDriverwise() {
		
		List<TripBooking> list = tripDao.findByDriverAscs();
		
		if(list.size() > 0)
			return list;
		else
			throw new AdminExceptions("No trips found");
		
	}


	@Override
	public List<TripBooking> getTripsCustomerwise() {
		List<TripBooking> list = tripDao.findByCustomeridAsce();
		if(list.size() > 0)
			return list;
		else
			throw new AdminExceptions("No trips found");
		 
	}

	@Override
	public List<TripBooking> getTripsDatewise() throws AdminExceptions {
		List<TripBooking> list = tripDao.findByFromdate_timeAsce();
		if(list.size() > 0)
			return list;
		else
			throw new AdminExceptions("No trips found");
	}

	@Override
	public List<TripBooking> getTripsDatewiseAndCustomer(Integer customerId, LocalDate date) throws AdminExceptions {
		List<TripBooking> list = tripDao.findByCustomerIdAndFromdate_time(customerId, date);
		if(list.size() > 0)
			return list;
		else
			throw new AdminExceptions("No trips found for customer id "+customerId+" and date : "+date);
	}


}
