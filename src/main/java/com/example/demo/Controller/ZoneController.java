package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Specialite;
import com.example.demo.entity.Ville;
import com.example.demo.entity.Zone;
import com.example.demo.service.VilleService;
import com.example.demo.service.ZoneService;

@RestController
@RequestMapping("api/zones")
@CrossOrigin("*")
public class ZoneController {
	
	
	@Autowired
	ZoneService zoneService;
	@Autowired
	VilleService villeService;
	
	@GetMapping("/all")
	public List<Zone> findAll() {
		return zoneService.findAll();
	}
	@GetMapping("/findbyid/{id}")
	public Zone findById(@PathVariable int id) {
		return zoneService.findById(id);
	}
	
	@GetMapping("/ville/zones/{nom}")
	public List<Zone> findByVilleNom(@PathVariable String nom){
		return zoneService.findByVilleNom(nom);
	}

	@PostMapping("/save")
	public Zone save(@RequestBody Zone t) {
		return zoneService.save(t);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id) {
		zoneService.delete(id);
	}

	@PutMapping("/update/{id}")
	public Zone update(@PathVariable int id, @RequestBody Zone sp) {
		Zone existingzone = zoneService.findById(id);
		Ville v = villeService.findById(sp.getVille().getId());
        if (existingzone!=null) {
        	existingzone.setNom(sp.getNom());
        	existingzone.setVille(v);
            return zoneService.save(existingzone);
        }
        return null;
		
	}
	
	

}
