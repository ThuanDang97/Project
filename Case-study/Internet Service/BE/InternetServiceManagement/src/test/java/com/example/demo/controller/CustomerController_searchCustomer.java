package com.example.demo.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerController_searchCustomer {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    //    @Test
//    public void testCreateStudent14() throws Exception {
//
//        StudentDto studentDto = new StudentDto();
//        studentDto.setName("");
//        studentDto.setDateOfBirth("");
//        studentDto.setGender(1);
//        studentDto.setGrade(5.0);
//        ClassStudent classStudent = new ClassStudent();
//        classStudent.setId(3);
//        studentDto.setClassStudent(classStudent);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders
//                        .post("/studentRest/create")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(studentDto)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().is4xxClientError());
//    }

//    @Test
//    public void createStudent_18() throws Exception {
//        StudentDto studentDto = new StudentDto();
//        studentDto.setName("Nguyen Van B");
//        studentDto.setGrade(9.0);
//        studentDto.setDateOfBirth("2000-10-06");
//        studentDto.setGender(1);
//        ClassStudentDto classStudentDto = new ClassStudentDto();
//        classStudentDto.setId(2);
//        studentDto.setClassStudentDto(classStudentDto);
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/studentRest/create")
//                        .content(this.objectMapper.writeValueAsString(studentDto))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//
//    }

}
