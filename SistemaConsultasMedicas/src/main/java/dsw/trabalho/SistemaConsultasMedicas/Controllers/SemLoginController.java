package dsw.trabalho.SistemaConsultasMedicas.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SemLoginController {
    @GetMapping("/")
    public String home() {
        return "layout";
    }

}
