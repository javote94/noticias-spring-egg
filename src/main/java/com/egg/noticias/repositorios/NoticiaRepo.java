
package com.egg.noticias.repositorios;

import com.egg.noticias.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepo extends JpaRepository<Noticia, Long>{
    
}
