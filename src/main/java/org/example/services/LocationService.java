package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.repositories.LocationRepository;
import org.example.repositories.LocationRepositoryImpl;
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final UserService userService;


}
