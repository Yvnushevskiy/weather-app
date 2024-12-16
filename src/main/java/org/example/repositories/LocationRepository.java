package org.example.repositories;

import org.example.model.Location;

public interface LocationRepository {
    void save(Location location);

    void remove(Location location);
}
