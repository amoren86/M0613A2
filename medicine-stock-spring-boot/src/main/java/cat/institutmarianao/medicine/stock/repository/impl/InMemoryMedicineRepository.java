package cat.institutmarianao.medicine.stock.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cat.institutmarianao.medicine.stock.model.Medicine;
import cat.institutmarianao.medicine.stock.repository.MedicineRepository;

// @Repository marks this class as a Spring-managed data access component
@Repository
public class InMemoryMedicineRepository implements MedicineRepository {

	// In-memory data store using a HashMap
	private final Map<String, Medicine> medicines = new HashMap<>();

	public InMemoryMedicineRepository() {
		initSampleData();
	}

	private void initSampleData() {
		Medicine m1 = new Medicine("MED-001", "Paracetamol 1g", 3.50);
		m1.setDescription("Analgesic and antipyretic medication used to treat pain and fever.");
		m1.setProducer("Bayer AG");
		m1.setCategory("Analgesic");
		m1.setStockQuantity(150);
		m1.setStockInOrder(50);
		m1.setActive(true);

		Medicine m2 = new Medicine("MED-002", "Ibuprofen 600mg", 4.20);
		m2.setDescription("Non-steroidal anti-inflammatory drug (NSAID).");
		m2.setProducer("Pfizer");
		m2.setCategory("Anti-inflammatory");
		m2.setStockQuantity(80);
		m2.setStockInOrder(20);
		m2.setActive(true);

		Medicine m3 = new Medicine("MED-003", "Amoxicillin 500mg", 6.80);
		m3.setDescription("Penicillin antibiotic used to treat bacterial infections.");
		m3.setProducer("Cinfa");
		m3.setCategory("Antibiotic");
		m3.setStockQuantity(45);
		m3.setStockInOrder(100);
		m3.setActive(true);

		save(m1);
		save(m2);
		save(m3);
	}

	@Override
	public List<Medicine> findAll() {
		return new ArrayList<>(medicines.values());
	}

	@Override
	public Medicine findById(String id) {
		return medicines.get(id);
	}

	@Override
	public void save(Medicine medicine) {
		if (medicine != null && medicine.getId() != null) {
			medicines.put(medicine.getId(), medicine);
		}
	}

	@Override
	public void deleteById(String id) {
		medicines.remove(id);
	}
}