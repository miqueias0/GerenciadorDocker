package br.com.mike.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@Readiness
public class CustomHealthCheck implements HealthCheck{

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.up("Bom dia");
    }
}
