# spring-cache
## Projeto que demonstra o funcionamento do Spring Cache

* Dependency utilizada:
``` 
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-cache</artifactId>
    </dependency>

```

### Para ativar o cache no spring:
Anotação:
- @EnableCaching

### Para armazenar o valor em cache:
Anotação:
- @Cacheable("pessoa")

### Para excluir todos os caches do valor "pessoa":
Anotação:
- @CacheEvict(value="pessoa", allEntries=true)

### Para excluir valor do cache por id
Anotação:
- @CacheEvict(value="pessoa", key="#id")

### Para atualizar cache existente por id
Anotação:
- @CachePut(value="pessoa", key="#id")


## Links de referência
* [Baeldung](https://www.baeldung.com/spring-cache-tutorial)
* [Medium](https://medium.com/vedity/spring-boot-caching-mechanism-8ef901147e60)