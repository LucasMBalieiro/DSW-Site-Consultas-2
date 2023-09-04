package dsw.trabalho.SistemaConsultasMedicas.Controllers;


import dsw.trabalho.SistemaConsultasMedicas.Service.Spec.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/lista")
public class EspeciallidadesController {

    @Autowired
    private IMedicoService medicoService;

    @GetMapping("/medicos")
    public String listar(@RequestParam(value = "especialidade", required = false) String especialidade, ModelMap model) {
        if (especialidade != null && !especialidade.isEmpty()) {
            model.addAttribute("especialidade", medicoService.buscarPorEspecialidade(especialidade));
        } else {
            model.addAttribute("especialidade", medicoService.buscarTodos());
        }
        model.addAttribute("cidades", medicoService.buscarTodasEspecialidades());
        return "lista_especialidades";
    }
}
}
