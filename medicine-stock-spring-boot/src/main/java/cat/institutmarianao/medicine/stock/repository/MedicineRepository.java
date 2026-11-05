package cat.institutmarianao.medicine.stock.repository;

import java.util.List;

import cat.institutmarianao.medicine.stock.model.Medicine;

// Interface contract for Medicine data access operations
public interface MedicineRepository {

	List<Medicine> findAll();

	Medicine findById(String id);

	void save(Medicine medicine);

	void deleteById(String id);
}