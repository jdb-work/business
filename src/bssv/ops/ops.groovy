package bssv.ops

import bssv.domain.*

class SvcCtx {
    def String resp
    def final bodyPart
    def final int id = 250780
    def final String username = "mlewis"
    def final String password = "great2014"
    def final String svcmethod = "CustomerManager"
}

@Category(Customer)
class CustomerOps {

    static {
        def ctx =
        )
        new Consumer(
            ctx: new SvcCtx(
                bodyPart:
                    body {
                        'orac:getCustomer' {
                            entity {
                                entityId(_id)
                            }
                        }
                    }).call()
    }


    def Address address() {
        return Customer
    }

    def id(String self) {
        return self?."250780"
    }
}


