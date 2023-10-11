

package com.egg.noticias.servicios;

import com.egg.noticias.entidades.Noticia;
import com.egg.noticias.excepciones.MiException;
import com.egg.noticias.repositorios.NoticiaRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticiaServicio {
    
    @Autowired
    public NoticiaRepo nr;
    
    @Transactional
    public void crearNoticia(String titulo, String cuerpo) throws MiException{
        validar(titulo, cuerpo);
        Noticia noticia = new Noticia();
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        nr.save(noticia);
    }
    
    @Transactional
    public void modificarNoticia(Long id, Boolean alta, String titulo, String cuerpo) throws MiException{
        
        validar(titulo, cuerpo);
        
        Optional<Noticia> respuesta = nr.findById(id);
        
        if(respuesta.isPresent()){
            Noticia noticia = respuesta.get();
            noticia.setAlta(alta);
            noticia.setCuerpo(cuerpo);
            noticia.setTitulo(titulo);
            nr.save(noticia);
        }
    }
    
    public void cambiarEstado(Long id){
        Optional<Noticia> respuesta = nr.findById(id);
         if(respuesta.isPresent()){
            Noticia noticia = respuesta.get();
            noticia.setAlta(!noticia.getAlta());
            nr.save(noticia);
        }
    }
    
    public Noticia buscarNoticiaPorId(Long id){
        
        Noticia noticia = nr.findById(id).get();
        
        return noticia;
    }
    
    public List<Noticia> listarNoticias(){
        List<Noticia> noticias = new ArrayList();
        noticias  = nr.findAll();
        return noticias;
    }
    
    public Noticia getOne(Long id) {
        return nr.getOne(id);
    }
    
    private void validar(String titulo, String cuerpo) throws MiException{
        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("El título de la noticia no puede estar vacío o nulo");
        }
        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new MiException("El cuerpo de la noticia no puede estar vacío o nulo");
        }
    }

}
