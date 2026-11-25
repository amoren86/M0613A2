package cat.institutmarianao.medicine.stock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cat.institutmarianao.medicine.stock.model.Medicine;
import cat.institutmarianao.medicine.stock.service.MedicineService;
import jakarta.validation.Valid;

//Main controller for managing medicine web requests using the Service layer
@Controller
@RequestMapping("/medicines")
public class MedicineController {

	private final MedicineService medicineService;

	// Constructor injection of the MedicineService interface
	public MedicineController(MedicineService medicineService) {
		this.medicineService = medicineService;
	}

	// Configures data binding rules for the 'newMedicine' form-backing bean
	@InitBinder("newMedicine")
	public void initialiseBinder(WebDataBinder binder) {
		// Disallows binding of 'stockInOrder' during medicine creation
		binder.setDisallowedFields("stockInOrder");
	}

	// Handles GET requests to /medicines, /medicines/ and /medicines/all
	@GetMapping({ "", "/", "/all" })
	public ModelAndView showAllMedicines() {
		ModelAndView modelView = new ModelAndView("medicines");
		modelView.addObject("medicines", medicineService.findAll());
		return modelView;
	}

	// Handles GET requests to /medicines/medicine?id=MED-001
	@GetMapping("/medicine")
	public ModelAndView showMedicineDetail(@RequestParam String id) {
		ModelAndView modelView = new ModelAndView("medicine");
		Medicine medicine = medicineService.findById(id);
		modelView.addObject("medicine", medicine);
		return modelView;
	}

	// Path Variable (single): filters medicines by category (e.g.,
	// /medicines/Analgesic)
	@GetMapping("/{category}")
	public ModelAndView showMedicinesByCategory(@PathVariable String category) {
		ModelAndView modelView = new ModelAndView("medicines");
		modelView.addObject("medicines", medicineService.findByCategory(category));
		return modelView;
	}

	// Path Variables (multiple) + Redirection: updates stock and redirects to
	// /medicines
	@GetMapping("/stock-movement/{medicineId}/{quantity}/{sign}")
	public String processStockMovement(@PathVariable String medicineId, @PathVariable long quantity,
			@PathVariable int sign) {
		medicineService.processStockMovement(medicineId, quantity, sign);
		return "redirect:/medicines";
	}

	// Displays the empty form for adding a new medicine (GET)
	@GetMapping("/add")
	public ModelAndView showAddMedicineForm() {
		ModelAndView modelView = new ModelAndView("addMedicine");
		Medicine newMedicine = new Medicine();
		modelView.addObject("newMedicine", newMedicine);
		return modelView;
	}

	// Processes and validates the submitted form (POST)
	@PostMapping("/add")
	public String processAddMedicineForm(@Valid @ModelAttribute("newMedicine") Medicine newMedicineToAdd,
			BindingResult result) {

		// Checks if any disallowed field was submitted in the HTTP request
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: "
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}

		// If validation fails, return to the form view preserving user input and errors
		if (result.hasErrors()) {
			return "addMedicine";
		}

		medicineService.save(newMedicineToAdd);
		return "redirect:/medicines/all";
	}
}