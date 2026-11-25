package cat.institutmarianao.medicine.stock.service;

import java.util.List;

import cat.institutmarianao.medicine.stock.model.Medicine;

// Service interface defining business operations for Medicine management
public interface MedicineService {

	List<Medicine> findAll();

	Medicine findById(String id);

	void save(Medicine medicine);

	void deleteById(String id);

	List<Medicine> findByCategory(String category);

	// Business operation: updates stock quantity applying domain validation rules
	void processStockMovement(String medicineId, long quantity, int sign);
}