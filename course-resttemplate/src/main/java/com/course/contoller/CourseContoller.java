package com.course.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.dto.CourseRequestDTO;
import com.course.dto.CourseResponseDTO;
import com.course.dto.ServiceResponse;
import com.course.entity.Course;
import com.course.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/course")
public class CourseContoller {

	@Autowired
	private CourseService courseService;
	
	@PostMapping("/create")
	public ServiceResponse<CourseResponseDTO> createCourse(@RequestBody CourseRequestDTO courseRequestDTO){
		return new ServiceResponse<CourseResponseDTO>(HttpStatus.CREATED,courseService.createCourseSer(courseRequestDTO));
	}
	
	@Operation(summary = "Fetch All Courses" )
	@GetMapping("/all")
	public ServiceResponse<List<CourseResponseDTO>> getAllCourse(){
		return new ServiceResponse<List<CourseResponseDTO>>(HttpStatus.FOUND,courseService.getAllcoursesSer());
	}
	
	@GetMapping("/{courseId}")
	public ServiceResponse<CourseResponseDTO> getUsingIdCourse(@PathVariable Integer courseId){
		return new ServiceResponse<CourseResponseDTO>(HttpStatus.CREATED,courseService.getByIdCourseSer(courseId));
	}
	
	@PutMapping("/{courseId}")
	public ServiceResponse<CourseResponseDTO> getUsingIdCourse(@PathVariable Integer courseId,@RequestBody CourseRequestDTO courseRequestDTO){
		return new ServiceResponse<CourseResponseDTO>(HttpStatus.CREATED,courseService.updateCourseSer(courseId,courseRequestDTO));
	}
	

	@DeleteMapping("/{courseId}")
	public String deleteCourse(@PathVariable Integer courseId){
		return	 courseService.deleteCourseSer(courseId);
		  
	}
}
