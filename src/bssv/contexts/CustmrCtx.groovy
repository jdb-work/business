package bssv.contexts

class CustmrCtx extends SvcCtx {
    def GString reqfn = $GetCustomer
    def Closure req = {
        'orac:getCustomer' {
            entity {
                entityId(_id)
            }
        }
    }
    def CustmrCtx() {
        super(reqfn, req)
    }
}