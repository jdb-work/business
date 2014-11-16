package bssv.domain

import groovy.transform.Immutable
import groovy.transform.TypeChecked
import bssv.contexts.*

public abstract class Entity {
    def SvcCtx ctx
}

@TypeChecked
class Customer
extends Entity {
    def CustmrCtx ctx
    def Address address
    public Customer(CustmrCtx ctx) {
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



