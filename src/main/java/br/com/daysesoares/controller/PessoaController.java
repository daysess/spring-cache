package br.com.daysesoares.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.daysesoares.model.Pessoa;
import br.com.daysesoares.service.PessoaService;

@RestController
@RequestMapping("pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaService service;
	
	@PostMapping
	public ResponseEntity<Pessoa> create(@RequestBody Pessoa obj){
		obj = service.newPessoa(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pessoa> getPessoaPorId(@PathVariable Integer id){
		Pessoa obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> updatePessoa(@RequestBody Pessoa obj, @PathVariable Integer id){
		obj = service.updateById(obj, id);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePessoa(@PathVariable("id") Integer id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
		
	
	/* @DeleteMapping("/{id}") 
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") int id) { 
        employeeService.deleteEmployee(id); 
        return new ResponseEntity<>(HttpStatus. ACCEPTED ); 
    }
    @PutMapping("/update") 
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee Employee) { 
        return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus. OK );
*/
	/*
	 * @CachePut(value = "employee", key = "#employee.id") 
    public Employee updateEmployee(Employee Employee) { 
        System. out .println("Atualize o registro com id : " + empregado.getId()); 
        return EmployeeRepository.save(employee); 
    } 

    @CacheEvict(value = "employee", key = "#id") 
    public void deleteEmployee(int id) { 
        System..println("Delete o registro com id : " + id); 
        funcionárioRepository.deleteById(id); 
    } 
	 * @GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
		Chamado obj = service.findById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(obj));
	}
	
	@GetMapping("/hellou") 
	@Cacheable("ola")
    public String getPessoa() { 
		System.out.println("Olá");
        return "Olá"; 
    } 
	
	@GetMapping("/cancel") 
	@CacheEvict("ola")
    public String getCancel() { 
		System.out.println("Cancelado");
        return "Cancelado"; 
    } 
    */
	
}
