package com.example.demospringboot.service;

import com.example.demospringboot.entity.Staff;
import com.example.demospringboot.repository.StaffRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff getStaffById(Long id) {
        return staffRepository.findById(id).orElse(null);
    }

    public Staff addStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff updateStaff(Long id, Staff updated) {
        Staff existing = getStaffById(id);

        if (existing != null) {
            existing.setNama(updated.getNama());
            existing.setUmur(updated.getUmur());
            existing.setDivisi(updated.getDivisi());
            existing.setGajiPokok(updated.getGajiPokok());

            return staffRepository.save(existing);
        }
        return null;
    }


    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }
}
