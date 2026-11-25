package cat.institutmarianao.medicine.stock.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cat.institutmarianao.medicine.stock.model.Medicine;
import cat.institutmarianao.medicine.stock.repository.MedicineRepository;
import cat.institutmarianao.medicine.stock.service.MedicineService;

// @Service marks this class as a Spring-managed business logic component
@Service
public class MedicineServiceImpl implements MedicineService {

	private final MedicineRepository medicineRepository;

	// Constructor injection of the repository interface
	public MedicineServiceImpl(MedicineRepository medicineRepository) {
		this.medicineRepository = medicineRepository;
	}

	@Override
	public List<Medicine> findAll() {
		return medicineRepository.findAll();
	}

	@Override
	public Medicine findById(String id) {
		return medicineRepository.findById(id);
	}

	@Override
	public void save(Medicine medicine) {
		medicineRepository.save(medicine);
	}

	@Override
	public void deleteById(String id) {
		medicineRepository.deleteById(id);
	}

	@Override
	public List<Medicine> findByCategory(String category) {
		return medicineRepository.findByCategory(category);
	}

	// Business operation: updates stock quantity applying domain validation rules
	// It performs several repository calls to retrieve and persist the Medicine
	// entity, applying necessary validations
	@Override
	public void processStockMovement(String medicineId, long quantity, int sign) {
		// Retrieve the medicine entity by its ID
		Medicine medicine = medicineRepository.findById(medicineId);

		// Validate that the medicine exists
		if (medicine == null) {
			throw new IllegalArgumentException("Medicine not found with ID: " + medicineId);
		}

		// Normalize the sign to either +1 or -1 to ensure consistent stock movement
		int normalizedSign = (sign >= 0) ? 1 : -1;
		long signedQuantity = quantity * normalizedSign;

		// Business rule: stock quantity cannot become negative
		if (medicine.getStockQuantity() + signedQuantity < 0) {
			throw new IllegalArgumentException(
					"Insufficient stock available. Current stock: " + medicine.getStockQuantity());
		}

		// Update the stock quantity and persist the changes
		medicine.setStockQuantity(medicine.getStockQuantity() + signedQuantity);
		medicineRepository.save(medicine);
	}
}