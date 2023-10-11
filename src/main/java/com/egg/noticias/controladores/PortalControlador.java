

package com.egg.noticias.controladores;

import com.egg.noticias.entidades.Noticia;
import com.egg.noticias.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")                //localhost:8080/
public class PortalControlador {
    
    @Autowired
    private NoticiaServicio ns;
    
    @GetMapping("/")
    public String index(ModelMap modelo){
        
        List<Noticia> noticias = ns.listarNoticias();
        
        modelo.addAttribute("noticias", noticias);
        
        return "index.html";
    }
    
    
    
}
