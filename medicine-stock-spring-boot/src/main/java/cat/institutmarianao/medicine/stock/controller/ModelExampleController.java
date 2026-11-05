package cat.institutmarianao.medicine.stock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cat.institutmarianao.medicine.stock.model.Medicine;

// Controller for demonstrating model passing to Thymeleaf views
@Controller
@RequestMapping("/model-example")
public class ModelExampleController {

	// GET request to /example/medicine
	@GetMapping("/medicine")
	public ModelAndView showMedicineDetail() {
		// 1. Create a dummy model instance
		Medicine medicine = new Medicine("MED-001", "Paracetamol 1g", 3.50);
		medicine.setDescription("Analgesic and antipyretic medication used to treat pain and fever.");
		medicine.setProducer("Bayer AG");
		medicine.setCategory("Analgesic");
		medicine.setStockQuantity(150);
		medicine.setStockInOrder(50);
		medicine.setActive(true);

		// 2. Select the view name "medicine" (templates/medicine.html)
		ModelAndView modelView = new ModelAndView("medicine");

		// 3. Attach the domain model object to the view with key "medicine"
		modelView.addObject("medicine", medicine);

		return modelView;
	}
}