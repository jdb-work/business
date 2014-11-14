package bssv.domain

abstract class Entity {
    protected def id
    protected def build(id) { this.id = id }
}

final class Customer extends Entity {
    Address address
    private final def build = { id ->
        super.build(id)
        this.address = new Address(
            street: "123 Main St",
            detail: "Apt A",
            city: "Savannah",
            state: "GA",
            zip: "31401"
        )
    }
}

@groovy.transform.TypeChecked
@groovy.transform.Immutable
class Address {
    String street
    String detail
    String city
    String state
    String zip
}

