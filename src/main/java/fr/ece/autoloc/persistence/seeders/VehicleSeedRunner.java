package fr.ece.autoloc.persistence.seeders;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import fr.ece.autoloc.domain.VehicleCategory;
import fr.ece.autoloc.persistence.repositories.VehicleRepository;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Component
@Profile("dev")
public class VehicleSeedRunner implements CommandLineRunner {

    private final VehicleRepository vehicleRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(VehicleSeedRunner.class);

    public VehicleSeedRunner(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (vehicleRepository.count() > 0) {
            logger.info("Vehicles already exist in the database. Skipping seeding ...");
            return;
        }

        logger.info("Seeding vehicles ...");

        var seedData = objectMapper.readValue(
            new ClassPathResource("seed/vehicles.json").getInputStream(),
            new TypeReference<List<VehicleSeedData>>() {
        });

        var vehicles = seedData.stream()
            .map(data -> fr.ece.autoloc.domain.Vehicle.create(
                data.registrationNumber(),
                data.make(),
                data.model(),
                data.category(),
                data.dailyRate()))
            .toList();

        vehicleRepository.saveAll(vehicles);
        logger.info("Seeded {} vehicles successfully.", vehicles.size());
    }

    record VehicleSeedData(
            String registrationNumber,
            String make,
            String model,
            VehicleCategory category,
            BigDecimal dailyRate) {
    }
}
