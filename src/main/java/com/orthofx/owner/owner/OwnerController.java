package com.orthofx.owner.owner;

//import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OwnerController {

	@Autowired
	private OwnerService ownerService;
	
	@RequestMapping("/owners")
	public List<Owner> getAllOwners() {
		return ownerService.getAllOwners();
	}
	
	@RequestMapping("/owners/{id}")
	public Owner getOwner(@PathVariable int id) {
		return ownerService.getOwner(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/owners")
	public void addOwner(@RequestBody Owner owner) {
		ownerService.addOwner(owner);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/owners/{id}")
	public void updateOwner(@RequestBody Owner owner, @PathVariable int id) {
		ownerService.updateOwner(id, owner);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/owners/{id}")
	public void deleteOwner(@PathVariable int id) {
		ownerService.deleteOwner(id);
	}
}
