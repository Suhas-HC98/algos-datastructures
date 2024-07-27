package com.shared.info.pojo;

import lombok.Builder;

@Builder
public class EntityEntitlements {
    private String entityId;
    private String domicileCountry;

    public EntityEntitlements() {
    }

    public EntityEntitlements(String entityId, String domicileCountry) {
        this.entityId = entityId;
        this.domicileCountry = domicileCountry;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getDomicileCountry() {
        return domicileCountry;
    }

    public void setDomicileCountry(String domicileCountry) {
        this.domicileCountry = domicileCountry;
    }

    @Override
    public String toString() {
        return "EntityEntitlements{" +
                "entityId='" + entityId + '\'' +
                ", domicileCountry='" + domicileCountry + '\'' +
                '}';
    }
}
