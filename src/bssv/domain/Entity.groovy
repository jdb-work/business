package bssv.domain

import groovy.transform.Immutable
import groovy.transform.TypeChecked
import bssv.contexts.*

public abstract class Entity {
    def EntityContext ctx
}

@TypeChecked
class Customer
extends Entity {
    def CustomerContext ctx
    def Address address
    public Customer(CustomerContext ctx) {
        this.ctx == ctx
    }
}

@TypeChecked
@Immutable
class Address {
    String street
    String detail
    String city
    String state
    String zip
}



