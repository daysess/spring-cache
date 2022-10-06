package br.com.daysesoares.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.daysesoares.model.Pessoa;
import br.com.daysesoares.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository repository;

	public Pessoa newPessoa(Pessoa obj) {
		return repository.save(obj);
	}

	@Cacheable("pessoa")
	public Pessoa findById(Integer id) {
		System.out.println("TESTE CACHE - PESSOA: "+id);
		Optional<Pessoa> pessoa = repository.findById(id);
		simularTempo();
		if (!pessoa.isPresent()) {
			return null;
		}
		return pessoa.get();
	}
	
	private void simularTempo() {
		try {
			long time = 3000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@CachePut(value = "pessoa", key = "#id") 
	public Pessoa updateById(Pessoa obj, Integer id) {
		Optional<Pessoa> pesquisa = repository.findById(id);
		if(pesquisa.isPresent() && id.equals(pesquisa.get().getId())) {
			obj.setId(pesquisa.get().getId());
			obj = repository.save(obj);
		}else {
			obj = new Pessoa();
		}
		
		return obj;
	}

	@CacheEvict(value = "pessoa", key = "#id") 
	public void deleteById(Integer id) {
		System.out.println("Delete pessoa, ID: "+id);
		repository.deleteById(id);		
	}

	
	/* @CacheEvict(value = "employee", key = "#id") 
    public void deleteEmployee(int id) { 
        System..println("Delete o registro com id : " + id); 
        funcionárioRepository.deleteById(id); 
    }
	 * @CachePut(value = "employee", key = "#employee.id") 
    public Employee updateEmployee(Employee Employee) { 
        System. out .println("Atualize o registro com id : " + empregado.getId()); 
        return EmployeeRepository.save(employee); 
    } */

	
	
}
