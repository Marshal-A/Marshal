package com.emp.payroll.controller;

import java.util.List;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.exception.ResourceNotFoundException;
import com.emp.payroll.model.Schedule;
import com.emp.payroll.repository.ScheduleRepository;

import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins ="http://localhost:4200/")
@RestController
@RequestMapping("/employeepayroll/schedule/")
public class ScheduleController {
	
	@Autowired
	private ScheduleRepository attendanceRepository;
	
	//get all Employees
	@GetMapping("/schedules") 
	public List<Schedule> getAllEmployees(){
		return attendanceRepository.findAll();
	}
     @PostMapping("/schedules")
	public Schedule createAttendance(@RequestBody Schedule attendance) {
		return  attendanceRepository.save(attendance);
		
	}
     
     
     @GetMapping("/schedules/{empId}")
 	public ResponseEntity<Schedule> getEmployeeById(@PathVariable Long empId) {
 		Schedule employee = attendanceRepository.findById(empId)
 				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + empId));
 		return ResponseEntity.ok(employee);
 	}
 	
     @PutMapping("/schedules/{empId}")
 	public ResponseEntity<Schedule> updateEmployee(@PathVariable Long empId, @RequestBody Schedule employeeDetails){
 		Schedule employee = attendanceRepository.findById(empId)
 				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + empId));
 		
 		employee.setDate(employeeDetails.getDate());
 		employee.setShift(employeeDetails.getShift());
 		employee.setStartingTime(employeeDetails.getStartingTime());
 		employee.setEndTime(employeeDetails.getEndTime());
 		employee.setDuration(employeeDetails.getDuration());
 	
 		
 		Schedule updatedEmployee = attendanceRepository.save(employee);
 		return ResponseEntity.ok(updatedEmployee);
     }
     @DeleteMapping("/schedules/{empId}")
 	public String deleteEmployee(@PathVariable Long empId){
 		Schedule employee = attendanceRepository.findById(empId)
 				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + empId));
 		
 		attendanceRepository.delete(employee);
 		return "deleted";
 	}
}
















package com.emp.payroll.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="schedule")
public class Schedule {
	@Id
  private long empId;
	@Column(name = "Date")
	private String Date;
	@Column(name = "Shift")
  private String Shift;
	@Column(name = "startingTime")
	  private String startingTime;
	@Column(name = "endTime")
	  private String endTime;
	@Column(name = "Duration")
	  private String Duration;
	
	
	public Schedule() {
		  
	  }
	
	
	public Schedule(String date, String shift, String startingTime, String endTime, String duration) {
		super();
		Date = date;
		Shift = shift;
		this.startingTime = startingTime;
		this.endTime = endTime;
		Duration = duration;
	}


	public long getEmpId() {
		return empId;
	}


	public void setEmpId(long empId) {
		this.empId = empId;
	}


	public String getDate() {
		return Date;
	}


	public void setDate(String date) {
		Date = date;
	}


	public String getShift() {
		return Shift;
	}


	public void setShift(String shift) {
		Shift = shift;
	}


	public String getStartingTime() {
		return startingTime;
	}


	public void setStartingTime(String startingTime) {
		this.startingTime = startingTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public String getDuration() {
		return Duration;
	}


	public void setDuration(String duration) {
		Duration = duration;
	}


	
	 }
