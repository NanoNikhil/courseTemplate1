package com.course.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.course.dao.CourseRepository;
import com.course.dto.CourseRequestDTO;
import com.course.dto.CourseResponseDTO;
import com.course.dto.ServiceResponse;
import com.course.entity.Course;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public CourseResponseDTO createCourseSer(CourseRequestDTO courseRequestDTO) {
		Course course=new Course();
		course.setName(courseRequestDTO.getName());
		course.setTrainerName(courseRequestDTO.getTrainerName());
		course.setDuration(courseRequestDTO.getDuration());
		course.setStartDate(courseRequestDTO.getStartDate());
		course.setCourseType(courseRequestDTO.getCourseType());
		course.setFees(courseRequestDTO.getFees());
		course.setCertificateAvailable(courseRequestDTO.isCertificateAvailable());
		course.setDescription(courseRequestDTO.getDescription());
		courseRepository.save(course);
		
		CourseResponseDTO courseResponseDTO=new CourseResponseDTO(course.getCourseId(),course.getName(),course.getTrainerName()
				,course.getDuration(),course.getStartDate(),course.getCourseType(),course.getFees(),course.isCertificateAvailable(),
				course.getDescription());
		return courseResponseDTO;
	}

	public List<CourseResponseDTO> getAllcoursesSer() {
		List<Course>  courses=courseRepository.findAll();
		 List<CourseResponseDTO> courseResponseDTOs=new ArrayList<>();
		Iterator<Course> iterators =courses.iterator();
		while(iterators.hasNext()) {
			 Course course = iterators.next();
			CourseResponseDTO courseResponseDTO=new CourseResponseDTO(course.getCourseId(),course.getName(),course.getTrainerName()
					,course.getDuration(),course.getStartDate(),course.getCourseType(),course.getFees(),course.isCertificateAvailable(),
					course.getDescription());	
			 courseResponseDTOs.add(courseResponseDTO);}
		/*
		 * List<CourseResponseDTO> courseResponseDTOs = courses.stream() .map(course ->
		 * new
		 * CourseResponseDTO(course.getCourseId(),course.getName(),course.getTrainerName
		 * () ,course.getDuration(),course.getStartDate(),course.getCourseType(),course.
		 * getFees(),course.isCertificateAvailable(),
		 * course.getDescription())).collect(Collectors.toList());
		 */
		return courseResponseDTOs;
	}

	public CourseResponseDTO getByIdCourseSer(Integer courseId) {
		
		Course course=courseRepository.findById(courseId).orElseThrow(()->new EmployeeNotFoundException("Course id "+courseId+"NOT_FOUND"));
			
		CourseResponseDTO courseResponseDTO=new CourseResponseDTO(course.getCourseId(),course.getName(),course.getTrainerName()
				,course.getDuration(),course.getStartDate(),course.getCourseType(),course.getFees(),course.isCertificateAvailable(),
				course.getDescription());	
		
		return courseResponseDTO;
	}

	public CourseResponseDTO updateCourseSer(Integer courseId, CourseRequestDTO courseRequestDTO) {
		Course course=courseRepository.findById(courseId).orElseThrow(()->new EmployeeNotFoundException("Course id "+courseId+"NOT_FOUND"));
		course.setName(courseRequestDTO.getName());
		courseRepository.save(course);
		
		CourseResponseDTO courseResponseDTO=new CourseResponseDTO(course.getCourseId(),course.getName(),course.getTrainerName()
				,course.getDuration(),course.getStartDate(),course.getCourseType(),course.getFees(),course.isCertificateAvailable(),
				course.getDescription());
		return courseResponseDTO;
	}

	public String deleteCourseSer(Integer courseId) {
		Course course=courseRepository.findById(courseId).orElseThrow(()->new EmployeeNotFoundException("Course id "+courseId+"NOT_FOUND"));
		courseRepository.deleteById(courseId);
		return "Deleted Id :"+course.getCourseId()+" Course "+course.getName();
	}
	
	
}
