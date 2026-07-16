package com.studentSpringBoot.entity; 

import java.time.LocalDate; // Fixed capitalization
import java.util.List;       // Fixed capitalization
import org.hibernate.annotations.SoftDelete; 
import org.hibernate.annotations.SoftDeleteType; 
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column; 
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany; 
import jakarta.persistence.OneToOne; 
import jakarta.persistence.Table; 
import lombok.AllArgsConstructor; 
import lombok.Getter; 
import lombok.NoArgsConstructor; 
import lombok.Setter; 
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.studentSpringBoot.entity.BaseEntity;   // Specific import
//import com.studentSpringBoot.entity.Department;   // Specific import
//import com.studentSpringBoot.entity.User;         // Specific import
import com.studentSpringBoot.entity.Enrollment;

@AllArgsConstructor 
@NoArgsConstructor 
@Setter 
@Getter 
@ToString(exclude = {"user", "enrollments", "department"}) // Fixed spelling
@Entity 
@Table(name="students") 
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SoftDelete(columnName = "is_deleted", strategy = SoftDeleteType.DELETED) 
@AttributeOverride(name="id", column = @Column(name="reg_no")) 
public class Student extends BaseEntity { 

    @Column(name="first_name", nullable = false, length=30) 
    private String firstName; // Fixed capitalized type

    @Column(name="middle_name", nullable = false, length=30) 
    private String middleName; // Fixed capitalized type

    @Column(name="last_name", nullable = false, length=30) 
    private String lastName; // Fixed capitalized type

    @Column(name="mobile_no", nullable = false, unique = true, length=15) 
    private String mobileNo; // Fixed capitalized type

    @Column(name="roll_no", nullable = false, unique = true, length=6) 
    private String rollNo; // Fixed capitalized type

    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name="department_id", nullable=false) 
    private Department department;  

    @Column(length=500) 
    private String address; // Fixed capitalized type

    @Column(name="date_of_birth") 
    private LocalDate dob; // Fixed capitalized type

    @Column(name="enrollment_date") 
    private LocalDate enrollmentDate; // Fixed capitalized type

    @Column(name="document_url") 
    private String documentUrl; // Fixed capitalized type

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", referencedColumnName = "user_id", unique = true, nullable = false) 
    private User user; 
    
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL) 
    private List<Enrollment> enrollments; // Fixed capitalization and spelling
}
