package com.rooney.james.model;

import javax.persistence.Transient;
import java.io.Serializable;

public abstract class DomainObject implements Serializable {

    private static final long serialVersionUID = 4236340635451868083L;

    public abstract boolean equals(Object o);

    public abstract int hashCode();

    public abstract String toString();

    public abstract Serializable getId();

    @Transient
    public boolean isNew() {
        return (getId() == null);
    }

    @Transient
    public boolean equalsById(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( !(o instanceof DomainObject) ) {
            return false;
        }
        return this.getId().equals(((DomainObject) o).getId());
    }
}
