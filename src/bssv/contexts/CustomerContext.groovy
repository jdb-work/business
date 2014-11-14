package bssv.contexts

class CustomerContext extends EntityContext {
    GString reqfun = $GetCustomer
    Closure reqpar = {
        'orac:getCustomer' {
            entity {
                entityId(id)
            }
        }
    }
    def CustomerContext() {
        super(reqfun, reqpar)
    }
}