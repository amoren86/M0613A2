package cat.institutmarianao.medicine.stock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cat.institutmarianao.medicine.stock.model.Medicine;
import cat.institutmarianao.medicine.stock.repository.MedicineRepository;

// Example controller illustrating direct interaction between Controller and Repository
@Controller
@RequestMapping("/repository-example")
public class RepositoryExampleController {

	// WARNING: Direct dependency injection of the repository is not a recommended
	// practice in production code!!
	private final MedicineRepository medicineRepository;

	// Direct dependency injection of MedicineRepository
	// Spring will look for a @Repository bean of type MedicineRepository and inject
	// it into this constructor
	public RepositoryExampleController(MedicineRepository medicineRepository) {
		this.medicineRepository = medicineRepository;
	}

	// Handles GET requests to list all medicines stored in the repository
	@GetMapping("/medicines")
	public ModelAndView showAllMedicines() {
		ModelAndView modelView = new ModelAndView("medicines");
		modelView.addObject("medicines", medicineRepository.findAll());
		return modelView;
	}

	// Handles GET requests to display details for a specific medicine by ID
	@GetMapping("/medicine")
	// Do not need to use @RequestParam("id") because the parameter name matches the
	// request parameter name
	public ModelAndView showMedicineDetail(@RequestParam String id) {
		ModelAndView modelView = new ModelAndView("medicine");
		Medicine medicine = medicineRepository.findById(id);
		modelView.addObject("medicine", medicine);
		return modelView;
	}
}
