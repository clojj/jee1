package com.airhacks.workshops.business.mybusiness.boundary;

import com.airhacks.workshops.business.mybusiness.entity.RootEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MyRepository {

    public static final String CONFIRMATION_ID = "confirm-id";

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private DeletionHelper deletionHelper;

    public void persist(List<RootEntity> rootEntities) {
        rootEntities.forEach(entityManager::persist);
    }

    public JsonObject persist(RootEntity rootEntity) {
        RootEntity entity = entityManager.merge(rootEntity);
        return convert(entity);
    }

    public List<RootEntity> loadAll() {
        return entityManager.createQuery("select r from RootEntity r", RootEntity.class).getResultList();
    }

    public RootEntity find(long registrationId) {
        return entityManager.createQuery("select r from RootEntity r", RootEntity.class).getSingleResult();
    }

    public void deleteById(long id) {
        List<Long> childIds = entityManager.createQuery("select c.id from ChildEntity c where c.rootEntity.id = :pid").setParameter("pid", id).getResultList();
        for (Long childId : childIds) {
            entityManager.createQuery("delete from ChildEntity c where c.id = :id").setParameter("id", childId).executeUpdate();
        }
        entityManager.createQuery("delete from RootEntity r where r.id = :id").setParameter("id", id).executeUpdate();

//        deletionHelper.delete(entityManager, RootEntity.class, id);
    }

    public void removeById(long id) {
        RootEntity rootEntity = entityManager.getReference(RootEntity.class, id);
        entityManager.remove(rootEntity);
    }

    /**
     * Because the application only offers JAX-RS endpoint and may offer
     * WebSockets in the future, the conversion from domain objects (entities)
     * to JsonObjects happens in the protocol-neutral boundary.
     *
     * You could also convert entities into JsonObject in the JAX-RS / WebSocket
     * endpoints in case you need the domain objects for a serverside Java web
     * framework.
     * @param rootEntity
     */
    JsonObject convert(RootEntity rootEntity) {
        return Json.createObjectBuilder().
                add(CONFIRMATION_ID, rootEntity.getId()).build();
    }
}
