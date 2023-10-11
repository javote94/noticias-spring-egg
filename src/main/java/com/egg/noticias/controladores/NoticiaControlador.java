

package com.egg.noticias.controladores;

import com.egg.noticias.entidades.Noticia;
import com.egg.noticias.excepciones.MiException;
import com.egg.noticias.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/noticia")           //localhost:8080/noticia
public class NoticiaControlador {
    
    @Autowired
    private NoticiaServicio ns;
    
    @GetMapping("/cargar")        //localhost:8080/noticia/cargar
    public String carga(){
        return "noticia-carga.html";
    }
    
    @PostMapping("/cargado")            //localhost:8080/noticia/cargado
    public String cargado(@RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo){
        
        try {
            ns.crearNoticia(titulo, cuerpo);
            modelo.put("exito", "La noticia fue cargada correctamente");
            
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "noticia-carga.html";
            
        }
        
        List<Noticia> noticias = ns.listarNoticias();
        modelo.addAttribute("noticias", noticias);
        return "index.html";
    }
    
    @GetMapping("/lista")            //localhost:8080/noticia/lista
    public String listar(ModelMap modelo) {
        List<Noticia> noticias = ns.listarNoticias();
        modelo.addAttribute("noticias", noticias);
        return "noticia-lista.html";
    }
    
    @GetMapping("/modificar/{id}")    //localhost:8080/noticia/modificar/id
    public String modificar(@PathVariable Long id, ModelMap modelo){
        modelo.put("noticia", ns.getOne(id));
        return "noticia-modificar.html";
    }
    
    @PostMapping("/modificar/{id}")  //localhost:8080/noticia/modificar/id
    public String modificar(@PathVariable Long id, String titulo, String cuerpo, Boolean alta, ModelMap modelo) throws MiException{
        
        try {
            ns.modificarNoticia(id, alta, titulo, cuerpo);
            return "redirect:../lista";
            
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "noticia-modificar.html";
        }
    }
    
    @GetMapping("/leer/{id}")         //localhost:8080/noticia/leer/id
    public String leer(@PathVariable Long id, ModelMap modelo){
        modelo.put("noticia", ns.getOne(id));
        return "noticia.html";
    }
}
