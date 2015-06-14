package com.airhacks.workshops.business.mybusiness.boundary;

import com.airhacks.workshops.business.mybusiness.entity.RootEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Stateless
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Path("myresource")
public class MyResource {

    @Inject
    MyRepository myRepository;

    @POST
    public Response store(RootEntity request, @Context UriInfo info) {
        JsonObject entity = myRepository.persist(request);

        long id = entity.getInt(MyRepository.CONFIRMATION_ID);
        URI uri = info.getAbsolutePathBuilder().path("/" + id).build();
        return Response.created(uri).entity(entity).build();
    }

    @GET
    @Path("{id}")
    public RootEntity find(@PathParam("id") long id) {
        return myRepository.find(id);
    }

/*
    @GET
    public Response all() {
        JsonArray registrationList = this.myRepository.allAsJson();
        if (registrationList == null || registrationList.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(registrationList).build();
    }

    @GET
    @Path("{id}/dummy")
    public Registration dummy(@PathParam("id") int registrationId) {
        return new Registration(registrationId);
    }

*/
}
