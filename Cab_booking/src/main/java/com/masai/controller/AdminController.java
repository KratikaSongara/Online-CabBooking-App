package com.masai.controller;



import java.time.LocalDate;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Admin;
import com.masai.entity.Cab;
import com.masai.entity.TripBooking;
import com.masai.service.AdminService;
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/")
	public ResponseEntity<Admin> insertAdminHandler(@RequestBody Admin admin) {
		Admin savedAdmin = adminService.saveAdmin(admin);
		return new ResponseEntity<Admin>(savedAdmin,HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{adminId}")
	public ResponseEntity<Admin> deleteMappingHandler(@PathVariable("adminId") Integer adminId) {
		Admin returnAdmin = adminService.delete(adminId); 
		return new ResponseEntity<Admin>(returnAdmin,HttpStatus.OK);
	}
	
	@GetMapping("/trips/{customerId}")
	public ResponseEntity<List<TripBooking>>  getAllTripsHandler(@PathVariable("customerId") Integer customerId){
		
		List<TripBooking> trips= adminService.getAllTrips(customerId);
		return new ResponseEntity<List<TripBooking>>(trips,HttpStatus.OK);
	}
	
	@GetMapping("/trips/driverwise")
	public ResponseEntity<List<TripBooking>> getTripsDriverwiseHandler(){
		
		List<TripBooking> trips= adminService.getTripsDriverwise();
		return new ResponseEntity<List<TripBooking>>(trips,HttpStatus.OK);
	}
	
	
	@PutMapping("/")
	public ResponseEntity<String> updateAdminHandler(@RequestBody Admin admin) {
		Admin updatedAdmin = adminService.update(admin);
		return new ResponseEntity<String>("admin updated "+updatedAdmin,HttpStatus.ACCEPTED); 
	}
	
	
	
	
	@GetMapping("/customertrips")
	public List<TripBooking> getTripsCustomerwiseHandler(){
		List<TripBooking> list = adminService.getTripsCustomerwise();
		return list;
	}
	
	@GetMapping("/datewisetrips")
	public List<TripBooking> getTripsDatewiseHandler(){
		List<TripBooking> list = adminService.getTripsDatewise();
		return list;
	}
	
	@GetMapping("trips/{customerId}/{date}")
	public List<TripBooking> getTripsDatewiseAndCustomerHandler(@PathVariable("customerId") Integer customerId, @PathVariable("date") String date){
		LocalDate date1 = LocalDate.parse(date);
		List<TripBooking> list = adminService.getTripsDatewiseAndCustomer(customerId, date1);
		return list;

	}
}
