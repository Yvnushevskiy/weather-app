package org.example.repositories;

import org.example.model.Location;
import org.example.model.User;

import java.util.List;
import java.util.Optional;

public interface LocationRepository {
    void save(Location location);

    void remove(Location location);

    Optional<List<Location>> findAllLocationsByUsername(String username);

    Optional<Integer> findLocationIDByUsernameAndLocation(String Username, Location location);
}
