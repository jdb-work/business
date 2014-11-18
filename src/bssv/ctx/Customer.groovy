package bssv.ctx

import bssv.meta.Ctx

@Category(Ctx)
class CustomerCategory {

    public String mail() {
        (this as Customer).@email
    }
}

class Customer extends Ctx {

    String id

    //todo: from conf
    def static final wsdl = "https://oakdbs01:8182/DV910/CustomerManager?wsdl"

    def final oldreq = {
        'orac:getCustomer' {
            entity {
                entityId(id) //todo: inject into live context
            }
        }
    }

    def final req = {'''
        <orac:getCustomer>
            <entity>
                <entityId>
                    ${_id}
                </entityId>
            </entity>
        </orac:getCustomer>
    '''}

    /*
        'orac:getCustomer' {
            entity {
                entityId(id) //todo: inject into live context
            }
        }
    */

    def email
    Address address

    class Address {
        String street
        String detail
        String state
        String city
        String zip
    }

    def amounts = [
        financeChargesPrior: 0.0,
        financeChargesYTD: 0.0,
        invoiceCurrent: 0.0,
        invoicePrior: 0.0,
        appliedLast: 0.0,
        creditLimit: 0.0,
        highBalance: 0.0,
        open: 0.0,
        du: 0.0
    ]
}