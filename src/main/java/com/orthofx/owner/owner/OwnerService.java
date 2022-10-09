package com.orthofx.owner.owner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OwnerService {

	private List<Owner> owners = new ArrayList<Owner>(Arrays.asList(
			new Owner(1, "Ram", "1234567890", "Jupiter"),
			new Owner(2, "Sita", "1234509876", "Dio"),
			new Owner(3, "Ram", "5432167890", "Ray")
			));

	public List<Owner> getAllOwners() {
		return owners;
	}

	public Owner getOwner(int id) {
		int index = -1;
		for (int i = 0; i <= owners.size(); i++) {
			if(owners.get(i).getId() == id) {
				index = i;
				break;
			}
			else {
				index = -1;
			}
		}
		return owners.get(index);
	}
	
	public void addOwner(Owner owner) {
		owners.add(owner);
	}

	public void updateOwner(int id, Owner owner) {
		for(int i = 0; i < owners.size(); i++) {
			Owner o = owners.get(i);
			if(o.getId() == id) {
				owners.set(i, owner);
				return;
			}
		}
	}

	public void deleteOwner(int id) {
		for(int i = 0; i < owners.size(); i++) {
			Owner o = owners.get(i);
			if(o.getId() == id) {
				owners.remove(i);
				return;
			}
		}
	}
	
}
