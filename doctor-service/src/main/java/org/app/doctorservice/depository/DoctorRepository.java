package org.app.doctorservice.depository;

import org.app.doctorservice.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,String> {

//    Page<Doctor> getAllDoctors(Pageable pageable, String sortBy, String sortDirection);

}