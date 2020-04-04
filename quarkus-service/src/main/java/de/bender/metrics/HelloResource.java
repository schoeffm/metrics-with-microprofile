package de.bender.metrics;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.SimplyTimed;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {

    @Inject
    @ConfigProperty(name = "de.bender.hello.wait.lowerBound", defaultValue = "100")
    long lowerBound;

    @Inject
    @ConfigProperty(name = "de.bender.hello.wait.upperBound", defaultValue = "1000")
    long upperBound;


    @Path("/timedHello")
    @GET
    @Timed
    public Response timedHello() {
        final long pausedFor = executeHeavyComputation();
        return Response
                .ok(toReponseBody(pausedFor))
                .build();
    }

    @Path("/simplyHello")
    @GET
    @SimplyTimed
    public Response simplyTimedHello() {
        final long pausedFor = executeHeavyComputation();
        return Response
                .ok(toReponseBody(pausedFor))
                .build();
    }

    JsonObject toReponseBody(long pausedFor) {
        return Json.createObjectBuilder()
                .add("result", "success")
                .add("executionTimeInMillis", pausedFor)
                .build();
    }

    long executeHeavyComputation() {
        long randomPeriod = lowerBound + (long) (Math.random() * (upperBound - lowerBound));
        Threads.pause(randomPeriod);
        return randomPeriod;
    }
}