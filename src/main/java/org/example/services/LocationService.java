package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.model.Location;
import org.example.repositories.LocationRepository;
import org.example.repositories.LocationRepositoryImpl;
import org.example.repositories.SessionRepository;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;



    public void saveLocation(Location location) {
        locationRepository.save(location);
    }

    public void removeLocation(Location location) {
        locationRepository.remove(location);
    }

    public Optional<List<Location>> getAllLocationsByUsername(String username) {
        return locationRepository.findAllLocationsByUsername(username);
    }


    public Optional<Integer> getLocationIDByUsernameAndLocationName(String username, String name) {
        return locationRepository.findLocationIDByUsernameAndLocationName(username, name);
    }
    public Optional<Location> getLocationByID(int locationID) {
        return locationRepository.findLocationByLocationID(locationID);
    }
}
