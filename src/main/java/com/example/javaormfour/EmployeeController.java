package com.example.javaormfour;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;


    //get
    @GetMapping
    public List<Employee> getAll() {
        //sql_select_list
        var output =  repository.findAll();
        return output;
    }

    @GetMapping("/total")
    public long getTotal() {
        //sql_count
        var output =  repository.count();
        return output;
    }

    //routing_id
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }


    @GetMapping("/info_first")
    String getFirstInString() throws JsonProcessingException {

        var items = new ArrayList<String>();
        items.add("id");
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        var pageable = PageRequest.of(0,1,sort);
        var employee = repository.findAll(pageable).getContent().get(0);

        ObjectMapper mapper = new ObjectMapper();
        //json_convert
        String json = mapper.writeValueAsString(employee);
        return json;
    }


    @GetMapping("/info_header")
    String getHeader(@RequestHeader Map<String, String> headers,@RequestBody Map<String,String> body) {
        //get_header
        var token = headers.get("authorization");
        return token;
    }

    //post
    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        //sql_create
        return repository.save(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
        //sql_update
        Employee existing = repository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setName(employee.getName());
        existing.setDepartment(employee.getDepartment());
        existing.setSalary(employee.getSalary());
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
