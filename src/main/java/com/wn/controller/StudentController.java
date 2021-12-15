package com.wn.controller;

import com.wn.entity.Student;
import com.wn.mapper.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
 
    @Autowired
    private StudentRepository studentRepository;
 
    /**
     * 批量添加
     * @param students
     * @return
     */
    @PostMapping("/batchAdd")
    public void add(@RequestBody List<Student> students){
        studentRepository.saveAll(students);
    }
 
    /**
     * 添加
     * @param student
     * @return
     */
    @PostMapping("/add")
    public void add(@RequestBody Student student){
        studentRepository.save(student);
    }
 
    /**
     * 修改
     * @param student
     * @return
     */
    @PostMapping("/update")
    public void updateById(@RequestBody Student student){
        studentRepository.save(student);
    }
 
    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public void deleteById(@PathVariable String id){
        studentRepository.deleteById(id);
    }
 
    /**
     * 获取所有信息
     * @return
     */
    @GetMapping("/get")
    public Object getAll(){
        Iterable<Student> iterable = studentRepository.findAll();
        List<Student> list = new ArrayList<>();
        iterable.forEach(list :: add);
        return list;
    }
 
    /**
     * 查询指定ID
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Object getById(@PathVariable String id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        }
        return null;
    }
 
 
    /**
     * 普通搜索
     * @param keyword
     * @return
     */
    @GetMapping("/search/name")
    public Object searchName(String keyword){
        List<Student> students = studentRepository.findByNameLike(keyword);
        return students;
    }
 
    /**
     * 自定义匹配
     * 普通搜索
     * @param keyword
     * @return
     */
    @GetMapping("/search/name/custom")
    public Object searchTitleCustom(String keyword){
        List<Student> students = studentRepository.findByNameCustom(keyword);
        return students;
    }
 

}