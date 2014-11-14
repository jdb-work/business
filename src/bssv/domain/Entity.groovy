package bssv.domain
import groovy.transform.Immutable
import groovy.transform.TypeChecked
@TypeChecked
@Immutable
public abstract class Entity {
    static final def xml
    static final def id
    static def svcMethod
}
abstract class EntityCtx {
    def String svcMethod
}
@TypeChecked
@Immutable
final class Customer extends Entity {
    def address
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

