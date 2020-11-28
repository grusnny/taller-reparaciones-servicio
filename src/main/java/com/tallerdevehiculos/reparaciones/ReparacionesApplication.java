package com.tallerdevehiculos.reparaciones;

import com.tallerdevehiculos.reparaciones.entity.Repair;
import com.tallerdevehiculos.reparaciones.repository.RepairRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://tallerdevehiculos.s3-website-us-east-1.amazonaws.com/"})
@RestController
@RequestMapping(value = "/repairs")
@SpringBootApplication
public class ReparacionesApplication {
    @Autowired

    private RepairRepository repository;

    @GetMapping("/{rId}")
    public Repair GetRepair(@PathVariable String rId){
        return repository.findRepairById(rId);
    }
    @GetMapping("/licenseplate/{licenseplate}")
    public List<Repair> GetRepairByLicensePlate(@PathVariable String licenseplate){
        return repository.findRepairByPlate(licenseplate);
    }
    @PostMapping
    public Repair PostVehicle(@RequestBody Repair repair){
        return repository.addRepair(repair);
    }
    @PutMapping
    public String UpdateVehicle(@RequestBody Repair repair){
        return repository.editVehicle(repair);
    }
    @DeleteMapping
    public String DeleteVehicle(@RequestBody Repair repair){
        return repository.deleteRepair(repair);
    }
    @GetMapping
    public PaginatedScanList<Repair> GetAllVehicles(){
        return repository.findAllVehicles();
    }


    public static void main(String[] args) {
        SpringApplication.run(ReparacionesApplication.class, args);
    }

}
