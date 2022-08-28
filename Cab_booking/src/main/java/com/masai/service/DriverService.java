package com.masai.service;

import java.util.List;

import com.masai.entity.Driver;
import com.masai.exception.DriverNotFoundException;
import com.masai.exception.InvalidId;

public interface DriverService {
public Driver insertDriver(Driver driver);
public Driver viewDriverById(Integer id) throws InvalidId;
public Driver updateDriver(Integer id,Integer license, Boolean available)throws DriverNotFoundException;
public String deleteDriverById(Integer id)throws DriverNotFoundException;
public List<Driver> viewBestDriver()throws DriverNotFoundException;
}
